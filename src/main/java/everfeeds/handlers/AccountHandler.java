package everfeeds.handlers;

import everfeeds.thrift.util.Scope;
import everfeeds.mongo.AccessD;
import everfeeds.mongo.TokenD;
import everfeeds.thrift.domain.Access;
import everfeeds.thrift.domain.Account;
import everfeeds.thrift.error.Forbidden;
import everfeeds.thrift.error.TokenExpired;
import everfeeds.thrift.error.TokenNotFound;
import everfeeds.thrift.service.AccountAPI;
import everfeeds.thrift.util.Type;
import org.apache.thrift.TException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dmitry Kurinskiy
 * @since 06.05.11 19:11
 */
public class AccountHandler extends Handler implements AccountAPI.Iface {
  @Override
  public Account getAccount(String token) throws TException, TokenNotFound, Forbidden, TokenExpired {
    TokenD tokenD = getTokenD(token);
    checkToken(tokenD, Scope.INFO);

    Account account = new Account();
    tokenD.account.syncToThrift(account);
    return account;
  }

  @Override
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

  @Override
  public Access saveAccessToken(String token, Access access, String accessToken, String accessSecret, List<String> accessParams) throws TException, TokenNotFound, Forbidden, TokenExpired {
    TokenD tokenD = getTokenD(token);
    checkToken(tokenD, Scope.INFO_WRITE);

    AccessD accessD = findAccessD(access);

    accessD.syncFromThrift(access);
    // Token is renewed
    accessD.type = Type.getByThrift(access.type);
    accessD.accessToken = accessToken;
    accessD.accessSecret = accessSecret;
    accessD.params = accessParams;
    accessD.expired = false;
    // Link an account
    accessD.account = tokenD.account;

    getDS().save(accessD);

    // Sync back
    accessD.syncToThrift(access);

    return access;
  }

  @Override
  public Access saveAccess(String token, Access access) throws TException, TokenNotFound, Forbidden, TokenExpired {
    TokenD tokenD = getTokenD(token);
    checkToken(tokenD, Scope.INFO_WRITE);

    AccessD accessD = findAccessD(access);

    if (accessD.account != tokenD.account) {
      throw new Forbidden("Access denied: accounts are not equal");
    }

    accessD.syncFromThrift(access);
    getDS().save(accessD);

    // Sync back
    accessD.syncToThrift(access);

    return access;
  }

  @Override
  public Account saveAccount(String token, Account account) throws TException, TokenNotFound, Forbidden, TokenExpired {
    TokenD tokenD = getTokenD(token);
    checkToken(tokenD, Scope.INFO_WRITE);

    tokenD.account.syncFromThrift(account);
    getDS().save(tokenD.account);
    tokenD.account.syncToThrift(account);

    return account;
  }
}
