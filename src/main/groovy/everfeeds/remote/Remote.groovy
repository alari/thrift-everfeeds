package everfeeds.remote

import everfeeds.mongo.CategoryD
import everfeeds.mongo.EntryD
import everfeeds.MongoDB
import com.google.code.morphia.Datastore

/**
 * @author Dmitry Kurinskiy
 * @since 14.05.11 12:11
 */
@Typed
abstract class Remote {
  protected Datastore getDs(){
    MongoDB.getDS();
  }

  public void store(CategoryD categoryD) {
    ds.save get(categoryD)
  }

  abstract public List<EntryD> get(CategoryD categoryD)
}
