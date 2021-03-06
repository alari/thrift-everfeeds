@Typed package everfeeds.remote.evernote

import everfeeds.remote.Remote
import everfeeds.mongo.EntryD
import everfeeds.mongo.FilterD
import everfeeds.mongo.TagD
import everfeeds.mongo.CategoryD
import everfeeds.mongo.AccessD
import com.evernote.edam.type.Tag
import everfeeds.dao.TagDAO
import com.evernote.edam.type.Notebook
import everfeeds.dao.CategoryDAO
import com.evernote.edam.type.Note
import com.evernote.edam.notestore.NoteFilter
import everfeeds.thrift.util.Type
import everfeeds.util.annotation.Accessor
import everfeeds.util.RemoteUtils

/**
 * @author Dmitry Kurinskiy
 * @since 31.05.11 14:49
 */
@Accessor(Type.EVERNOTE)
class EvernoteRemote extends Remote{
  private EvernoteRaw getRaw() {
    EvernoteRaw.getInstance()
  }

  @Override
  List<EntryD> pull(FilterD filterD, int max, int offset) {
    NoteFilter filter = new NoteFilter()

    // May filter from one notebook or from all
    if(filterD.categories?.size() == 1 && filterD.categoriesWith) {
      filter.notebookGuid = filterD.categories.first().identity
    }

    if(filterD.withTags?.size()) {
      filter.tagGuids = filterD.withTags*.identity
    }

    List<EntryD> entries = []

    EvernoteParser parser = new EvernoteParser()
    parser.access = filterD.access

    Map<String,CategoryD> categories
    getActualizedCategories(filterD.access).each{categories.put it.identity, it}

    raw.getNoteStore(filterD.access).findNotes(filterD.access.accessToken, filter, offset, max).notes.each {Note note->
      // Processing filters
      // For categories:
      if(filterD.categories?.size()
          && !filterD.categoriesWith ? filterD.categories.any{it.identity==note.notebookGuid} : filterD.categories.every {it.identity != note.notebookGuid}) return;
      // For tags
      if(filterD.withTags?.size() && filterD.withTags.any{!note.tagGuids.contains(it.identity)}) return;
      if(filterD.withoutTags?.size() && filterD.withoutTags.any{note.tagGuids.contains(it.identity)}) return;

      parser.original = note
      parser.category = categories.get(note.notebookGuid)
      entries.add parser.result
    }

    entries
  }

  @Override
  TagD push(TagD tagD) {
    if(!tagD.access) {
      throw new Exception("Tag access should be set")
    }

    Tag tag
    if(tagD.identity) {
      tag = raw.getNoteStore(tagD.access).getTag(tagD.access.accessToken, tagD.identity)
      tag.name = tagD.title
      tag.parentGuid = tagD.parent?.identity
      raw.getNoteStore(tagD.access).updateTag(tagD.access.accessToken, tag)
    } else {
      tag = new Tag()
      tag.name = tagD.title
      tag.parentGuid = tagD.parent?.identity
      tag = raw.getNoteStore(tagD.access).createTag(tagD.access.accessToken, tag)
      tagD.identity = tag.guid
      TagDAO.instance.save(tagD)
    }

    return tagD
  }

  @Override
  List<TagD> getActualizedTags(AccessD access) {
    List<Tag> tags = raw.getNoteStore(access).listTags(access.accessToken)
    RemoteUtils.actualizeTags(access, tags, {Tag t->t.guid}, {Tag t->t.name}, {Tag t->t.parentGuid})
  }

  @Override
  List<CategoryD> getActualizedCategories(AccessD access) {
    List<Notebook> notebooks = raw.getNoteStore(access).listNotebooks(access.accessToken)
    RemoteUtils.actualizeCategories(access, notebooks, {Notebook n->n.guid}, {Notebook n->n.name})
  }

  @Override
  CategoryD push(CategoryD categoryD) {
    if(!categoryD.access) {
      throw new Exception("Category access should be set")
    }

    Notebook notebook
    if(categoryD.identity) {
      notebook = raw.getNoteStore(categoryD.access).getNotebook(categoryD.access.accessToken, categoryD.identity)
      notebook.name = categoryD.title
      raw.getNoteStore(categoryD.access).updateNotebook(categoryD.access.accessToken, notebook)
    } else {
      notebook = new Notebook()
      notebook.name = categoryD.title
      notebook = raw.getNoteStore(categoryD.access).createNotebook(categoryD.access.accessToken, notebook)
      categoryD.identity = notebook.guid
      CategoryDAO.instance.save(categoryD)
    }

    return categoryD
  }

  @Override
  EntryD push(EntryD entryD) {
    Note note
    if(entryD.identity) {
      note = raw.getNoteStore(entryD.access).getNote(entryD.access.accessToken, entryD.identity, false, false, false, false)
    } else {
      note = new Note()
    }
    note.title = entryD.title
    note.content = EvernoteMarkup.instance.getEdamForHtml( entryD.content.html )
    note.notebookGuid = entryD.category?.identity
    note.tagGuids = entryD.tags*.identity

    if(note.guid) {
      note = raw.getNoteStore(entryD.access).updateNote(entryD.access.accessToken, note)
    } else {
      note = raw.getNoteStore(entryD.access).createNote(entryD.access.accessToken, note)
    }

    new EvernoteParser(note, entryD.access, entryD).result
  }
}
