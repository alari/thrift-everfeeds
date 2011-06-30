package everfeeds.remote

import com.google.code.morphia.Datastore
import everfeeds.MongoDB
import everfeeds.mongo.EntryD
import everfeeds.mongo.FilterD
import everfeeds.thrift.domain.Entry
import everfeeds.dao.AccessDAO
import everfeeds.mongo.TagD
import everfeeds.mongo.CategoryD
import everfeeds.remote.error.NotSupportedException
import everfeeds.remote.error.InvalidTokenException
import everfeeds.mongo.AccessD

/**
 * @author Dmitry Kurinskiy
 * @since 14.05.11 12:11
 */
@Typed
abstract class Remote {
  static final int PULL_MAX = 500

  protected Datastore getDs() {
    MongoDB.getDS();
  }

  public void saveEntries(FilterD filterD) {
    try {
      List<EntryD> entries = pull(filterD)
      ds.save(entries*.content)
      if (filterD.id) {
        ds.save(entries.collect {it.filters.add filterD; it})
      } else {
        ds.save entries
      }
    } catch(InvalidTokenException e) {
      filterD.access.expired = true
      AccessDAO.instance.save(filterD.access)
      null
    }
  }

  public List<Entry> pullToThrift(FilterD filterD) {
    try {
      pull(filterD).collect {Entry e = new Entry(); it.syncToThrift e; e}
    } catch(InvalidTokenException e) {
      filterD.access.expired = true
      AccessDAO.instance.save(filterD.access)
      []
    }
  }

  final public List<EntryD> pull(FilterD filterD) {
    pull(filterD, PULL_MAX, 0)
  }

  final protected boolean filterAfterParse(FilterD filterD, EntryD entry) {
    // Perform after-parse filtering
        if (filterD.withTags.size() && !entry.tags.intersect(filterD.withTags).size()) {
          return false;
        }
        if (filterD.withoutTags.size() && entry.tags.intersect(filterD.withoutTags).size()) {
          return false;
        }
        if (filterD.kinds.size()) {
          if (filterD.kindsWith && !filterD.kinds.contains(entry.kind)) return false;
          else if (filterD.kinds.contains(entry.kind)) return false;
        }
    true
  }

  abstract public List<TagD> getActualizedTags(AccessD access) throws InvalidTokenException

  abstract public List<CategoryD> getActualizedCategories(AccessD access) throws InvalidTokenException

  abstract public List<EntryD> pull(FilterD filterD, int max, int offset) throws InvalidTokenException

  abstract public TagD push(TagD tagD) throws InvalidTokenException, NotSupportedException

  abstract public CategoryD push(CategoryD categoryD) throws InvalidTokenException, NotSupportedException

  abstract public EntryD push(EntryD entryD) throws InvalidTokenException, NotSupportedException
}
