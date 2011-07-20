@Typed package everfeeds.handlers;


import everfeeds.thrift.domain.Access;
import everfeeds.thrift.domain.Filter;
import everfeeds.thrift.error.Forbidden;
import everfeeds.thrift.error.TokenExpired;
import everfeeds.thrift.error.TokenNotFound;
import everfeeds.thrift.util.Scope;
import everfeeds.thrift.util.Type;


import org.apache.thrift.TException
import everfeeds.dao.*
import everfeeds.mongo.*

//import everfeeds.adapters.FilterAdapter;

/**
 * @author Dmitry Kurinskiy
 * @since 06.05.11 19:11
 */
abstract public class Handler {
  protected AccessDAO getAccessDAO(){AccessDAO.getInstance()}
  protected AccountDAO getAccountDAO(){AccountDAO.getInstance()}
  protected ApplicationDAO getApplicationDAO(){ApplicationDAO.getInstance()}
  protected CategoryDAO getCategoryDAO(){CategoryDAO.getInstance()}
  protected EntryContentDAO getEntryContentDAO(){EntryContentDAO.getInstance()}
  protected EntryDAO getEntryDAO(){EntryDAO.getInstance()}
  protected FilterDAO getFilterDAO(){FilterDAO.getInstance()}
  protected TagDAO getTagDAO(){TagDAO.getInstance()}
  protected TokenDAO getTokenDAO(){TokenDAO.getInstance()}

  protected TokenD getTokenD(String id) throws TException, TokenNotFound, TokenExpired, Forbidden {
    TokenD token = tokenDAO.getByKey(id)
    if (token == null) {
      throw new TokenNotFound(String.format("Cannot find token for %s", id));
    }
    if (token.expired) {
      throw new TokenExpired(String.format("Token expired at %s", token.expires.toString()));
    }
    return token;
  }

  protected void checkToken(TokenD tokenD, Scope scope) throws Forbidden, TokenNotFound {
    if (tokenD == null) {
      throw new TokenNotFound("Token not found while is checked");
    }
    if (!tokenD.hasScope(scope)) {
      throw new Forbidden("Access denied: token has no scope '" + scope.toString() + "' which is required to perform this action");
    }
  }

  protected AccessD findAccessD(Access access) throws TException {
    AccessD accessD = accessDAO.getByThrift(access)

    if (accessD != null) {
      return accessD;
    }

    accessD = new AccessD();
    accessD.type = Type.getByThrift(access.type);
    accessD.identity = access.identity;
    accessD.title = access.title ?: access.identity
    accessD.screenName = access.screenName
    return accessD;
  }


  protected AccessD getAccessD(String token, String id) throws TException, Forbidden, TokenNotFound, TokenExpired {
    TokenD tokenD = getTokenD(token);

    return accessDAO.getByIdAndAccount(id, tokenD.account)
  }
}

