package everfeeds;

import com.google.code.morphia.AdvancedDatastore;
import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.mongodb.DB;
import com.mongodb.Mongo;

/**
 * @author Dmitry Kurinskiy
 * @since 06.05.11 15:18
 */
public class MongoDB {
  protected Mongo mongo;
  protected DB db;
  protected Datastore ds;
  protected AdvancedDatastore ads;

  protected Morphia morphia = new Morphia();

  static private MongoDB instance;

  static public MongoDB getInstance() {
    if (instance == null) {
      instance = new MongoDB();
    }
    return instance;
  }

  static public Datastore getDS() {
    return getInstance().ds;
  }

  static public AdvancedDatastore getAdvancedDS() {
    return getInstance().ads;
  }

  protected MongoDB() {
    this.db = this.mongo.getDB("everfeeds");
    this.ds = this.morphia.createDatastore(this.mongo, this.db.getName());
    this.ads = (AdvancedDatastore) ds;
  }
}
