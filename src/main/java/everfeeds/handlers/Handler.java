package everfeeds.handlers;

import com.google.code.morphia.Datastore;
import everfeeds.MongoDB;
import everfeeds.mongo.TokenD;

/**
 * @author Dmitry Kurinskiy
 * @since 06.05.11 19:11
 */
abstract public class Handler {
    protected TokenD getTokenD(String id) {
    return getDS().get(TokenD.class, id);
  }

  protected Datastore getDS(){
    return MongoDB.getDS();
  }
}
