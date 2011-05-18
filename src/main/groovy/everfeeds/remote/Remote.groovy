package everfeeds.remote

import everfeeds.mongo.EntryD
import everfeeds.MongoDB
import com.google.code.morphia.Datastore
import everfeeds.mongo.FilterD
import everfeeds.thrift.domain.Entry

/**
 * @author Dmitry Kurinskiy
 * @since 14.05.11 12:11
 */
@Typed
abstract class Remote {
  protected Datastore getDs(){
    MongoDB.getDS();
  }

  public void saveEntries(FilterD filterD) {
    if(filterD.id) {
      ds.save( pull(filterD).collect {it.filters.add filterD; it} )
    } else {
      ds.save pull(filterD)
    }
  }

  public List<Entry> pullToThrift(FilterD filterD) {
    pull(filterD).collect{Entry e = new Entry(); it.syncToThrift e; e}
  }

  abstract public List<EntryD> pull(FilterD filterD)
}
