package everfeeds.remote.evernote

import everfeeds.remote.Parser
import everfeeds.mongo.EntryContentD
import everfeeds.mongo.AuthorD
import everfeeds.thrift.util.Kind
import everfeeds.mongo.TagD
import com.evernote.edam.type.Note
import everfeeds.mongo.EntryD
import everfeeds.mongo.AccessD
import everfeeds.mongo.CategoryD

/**
 * @author Dmitry Kurinskiy
 * @since 07.06.11 20:25
 */
class EvernoteParser extends Parser{
  EvernoteParser() {super()}

  EvernoteParser(final original, AccessD access, EntryD entry = null) {
    super(original, access, entry)
  }

  public Note getOriginal(){
    (Note)original
  }

  @Override
  boolean getIsAuthor() {
    true
  }

  @Override
  boolean getIsPublicAvailable() {
    false
  }

  @Override
  boolean getIsFavorite() {
    false
  }

  @Override
  boolean getIsRead() {
    true
  }

  @Override
  String getIdentity() {
    getOriginal().guid
  }

  @Override
  String getTitle() {
    getOriginal().title
  }

  @Override
  String getDescription() {
    return null
  }

  @Override
  EntryContentD getContent() {
    EntryContentD ecd = entry.content ?: new EntryContentD()
    ecd.content = getOriginal().content ?: EvernoteRaw.instance.getNoteStore(access).getNoteContent(access.accessToken, getOriginal().guid)
    ecd
  }

  @Override
  AuthorD getAuthor() {
    new AuthorD(
        identity: access.identity,
        title: access.title,
        screenName: access.screenName,
    )
  }

  @Override
  Kind getKind() {
    Kind.NOTE
  }

  @Override
  Date getDatePlaced() {
    new Date(getOriginal().created)
  }

  @Override
  List<TagD> getTags() {
    List<TagD> tags = []
    getOriginal().tagGuids.each{
      tags.add tagsCache.get(it)
    }
    tags
  }
}
