@Typed package everfeeds.handlers;

import everfeeds.thrift.util.Scope;
import everfeeds.mongo.AccessD;
import everfeeds.mongo.TokenD;
import everfeeds.thrift.domain.Access;
import everfeeds.thrift.domain.Account;
import everfeeds.thrift.error.Forbidden;
import everfeeds.thrift.error.TokenExpired;
import everfeeds.thrift.error.TokenNotFound;
import org.apache.thrift.TException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dmitry Kurinskiy
 * @since 06.05.11 19:11
 */
public class AccountHandler extends Handler {

  public Account getAccount(String token) throws TException, TokenNotFound, Forbidden, TokenExpired {
    TokenD tokenD = getTokenD(token);
    checkToken(tokenD, Scope.INFO);

    Account account = new Account();
    tokenD.account.syncToThrift(account);
    return account;
  }

  public List<Access> getAccesses(String token) throws TException, TokenNotFound, Forbidden, TokenExpired {
    TokenD tokenD = getTokenD(token);
    checkToken(tokenD, Scope.INFO);

    List<Access> list = new ArrayList<Access>();
    for (AccessD a : getDS().createQuery(AccessD.class).field("account").equal(tokenD.account).asList()) {
      Access access = new Access();
      a.syncToThrift(access);
      list.add(access);
    }
    return list;
  }

  public Access saveAccess(String token, Access access) throws TException, TokenNotFound, Forbidden, TokenExpired {
    TokenD tokenD = getTokenD(token);
    checkToken(tokenD, Scope.INFO_WRITE);

    AccessD accessD = findAccessD(access);

    if (accessD.account.id != tokenD.account.id) {
      throw new Forbidden("Access denied: accounts are not equal");
    }

    accessD.syncFromThrift(access);
    getDS().save(accessD);

    // Sync back
    accessD.syncToThrift(access);

    return access;
  }

  public Account saveAccount(String token, Account account) throws TException, TokenNotFound, Forbidden, TokenExpired {
    TokenD tokenD = getTokenD(token);
    checkToken(tokenD, Scope.INFO_WRITE);

    tokenD.account.syncFromThrift(account);
    getDS().save(tokenD.account);
    tokenD.account.syncToThrift(account);

    return account;
  }
}
