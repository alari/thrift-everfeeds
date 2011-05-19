package everfeeds.remote

import com.google.code.morphia.Datastore
import everfeeds.MongoDB
import everfeeds.mongo.EntryD
import everfeeds.mongo.FilterD
import everfeeds.thrift.domain.Entry

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
      ds.save(filterD.access)
      null
    }
  }

  public List<Entry> pullToThrift(FilterD filterD) {
    try {
      pull(filterD).collect {Entry e = new Entry(); it.syncToThrift e; e}
    } catch(InvalidTokenException e) {
      filterD.access.expired = true
      ds.save(filterD.access)
      []
    }
  }

  abstract public List<EntryD> pull(FilterD filterD) throws InvalidTokenException
}
