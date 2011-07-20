@Typed package everfeeds;

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
  static public final String DB_NAME = "everfeeds";

  protected Mongo _mongo;
  protected DB db;
  protected Datastore ds;
  protected AdvancedDatastore ads;

  protected Morphia _morphia = new Morphia();

  static private MongoDB instance = new MongoDB();

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

  static public Mongo getMongo(){
    return getInstance()._mongo;
  }

  static public Morphia getMorphia(){
    return getInstance()._morphia;
  }

  protected MongoDB() {
    try {
      this._mongo = new Mongo();
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
    this.db = this._mongo.getDB(DB_NAME);
    this.ds = this._morphia.createDatastore(this._mongo, this.db.getName());
    this.ads = (AdvancedDatastore) ds;
  }
}
