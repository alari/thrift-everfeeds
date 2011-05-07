package everfeeds.handlers;

import com.google.code.morphia.Datastore;
import everfeeds.MongoDB;
import everfeeds.mongo.TokenD;
import org.apache.thrift.TException;

/**
 * @author Dmitry Kurinskiy
 * @since 06.05.11 19:11
 */
abstract public class Handler {
  protected TokenD getTokenD(String id) throws TException {
   TokenD token = getDS().get(TokenD.class, id);
    if(token.expired) {
      throw new TException("Token expired");
    }
    return token;
  }

  protected Datastore getDS() {
    return MongoDB.getDS();
  }

  protected void save(Object o){
    getDS().save(o);
  }
}
