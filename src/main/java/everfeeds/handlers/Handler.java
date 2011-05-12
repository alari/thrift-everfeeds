package everfeeds.handlers;

import com.google.code.morphia.Datastore;
import everfeeds.MongoDB;
import everfeeds.Scope;
import everfeeds.mongo.AccessD;
import everfeeds.mongo.ApplicationD;
import everfeeds.mongo.TokenD;
import everfeeds.thrift.domain.Access;
import everfeeds.thrift.error.Forbidden;
import everfeeds.thrift.error.NotFound;
import everfeeds.thrift.error.TokenExpired;
import everfeeds.thrift.error.TokenNotFound;
import org.apache.thrift.TException;

/**
 * @author Dmitry Kurinskiy
 * @since 06.05.11 19:11
 */
abstract public class Handler {
  protected TokenD getTokenD(String id) throws TException, TokenNotFound, TokenExpired, Forbidden {
    TokenD token = getDS().get(TokenD.class, id);
    if(token == null) {
      throw new TokenNotFound(String.format("Cannot find token for %s", id));
    }
    if (token.expired) {
      throw new TokenExpired(String.format("Token expired at %s", token.expires.toString()));
    }
    return token;
  }

  protected void checkToken(TokenD tokenD, Scope scope) throws Forbidden, TokenNotFound {
    if(tokenD == null) {
      throw new TokenNotFound("Token not found while is checked");
    }
    if(!tokenD.hasScope(scope)) {
      throw new Forbidden("Access denied: token has no scope '"+scope.toString()+"' which is required to perform this action");
    }
  }


  protected ApplicationD getApplicationD(String secret) throws TException, NotFound {
    ApplicationD applicationD;

    applicationD = getDS().createQuery(ApplicationD.class).filter("secret", secret).get();
    if (applicationD == null) {
      throw new NotFound("Application not found");
    }

    return applicationD;
  }

  protected AccessD findAccessD(Access access) throws TException {
    AccessD accessD;
    if (!access.id.equals("") && access.id != null) {
      accessD = getDS().get(AccessD.class, access.getId());
      if (accessD != null) {
        return accessD;
      }
    }

    accessD = getDS().createQuery(AccessD.class)
                  .filter("identity", access.identity)
                  .filter("type", access.type).get();
    if (accessD != null) {
      return accessD;
    }
    return new AccessD();
  }


  protected AccessD getAccessD(String token, String id) throws TException, Forbidden, TokenNotFound, TokenExpired {
    TokenD tokenD = getTokenD(token);

    return getDS().createQuery(AccessD.class).filter("id", id).filter("account", tokenD.account).get();
  }

  protected Datastore getDS() {
    return MongoDB.getDS();
  }
}
