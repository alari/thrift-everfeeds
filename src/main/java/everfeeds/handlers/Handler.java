package everfeeds.handlers;

import com.google.code.morphia.Datastore;
import everfeeds.MongoDB;
import everfeeds.mongo.AccessD;
import everfeeds.mongo.ApplicationD;
import everfeeds.mongo.TokenD;
import everfeeds.thrift.Access;
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


  protected ApplicationD getApplicationD(String secret) throws TException{
    ApplicationD applicationD;

    applicationD = getDS().createQuery(ApplicationD.class).filter("secret", secret).get();
    if(applicationD == null) {
      throw new TException("Application not found");
    }

    return applicationD;
  }

  protected AccessD findAccessD(Access access) throws TException{
    AccessD accessD;
    if (!access.id.equals("") && access.id != null) {
      accessD = getDS().get(AccessD.class, access.getId());
      if(accessD != null) {
        return accessD;
      }
    }

    accessD = getDS().createQuery(AccessD.class)
                  .filter("identity", access.identity)
                  .filter("type", access.type).get();
    if(accessD != null) {
      return accessD;
    }
    return new AccessD();
  }


  protected AccessD getAccessD(String token, String id) throws TException{
    TokenD tokenD = getTokenD(token);

    return getDS().createQuery(AccessD.class).filter("id", id).filter("account", tokenD.account).get();
  }

  protected Datastore getDS() {
    return MongoDB.getDS();
  }

  protected void save(Object o){
    getDS().save(o);
  }
}
