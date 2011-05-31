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

/**
 * @author Dmitry Kurinskiy
 * @since 14.05.11 12:11
 */
@Typed
abstract class Remote {
  protected Datastore getDs() {
    MongoDB.getDS();
  }

  public void saveEntries(FilterD filterD) {
    try {
      if (filterD.id) {
        ds.save(pull(filterD).collect {it.filters.add filterD; it})
      } else {
        ds.save pull(filterD)
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

  abstract public List<EntryD> pull(FilterD filterD) throws InvalidTokenException

  abstract public TagD push(TagD tagD) throws InvalidTokenException, NotSupportedException

  abstract public CategoryD push(CategoryD categoryD) throws InvalidTokenException, NotSupportedException

  abstract public EntryD push(EntryD entryD) throws InvalidTokenException, NotSupportedException
}
