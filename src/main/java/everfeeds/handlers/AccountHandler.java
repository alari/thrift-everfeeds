package everfeeds.handlers;

import everfeeds.mongo.AccessD;
import everfeeds.mongo.AccountD;
import everfeeds.mongo.TokenD;
import everfeeds.thrift.Access;
import everfeeds.thrift.Account;
import everfeeds.thrift.AccountAPI;
import org.apache.thrift.TException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dmitry Kurinskiy
 * @since 06.05.11 19:11
 */
public class AccountHandler extends Handler implements AccountAPI.Iface {
  @Override
  public Account getAccount(String token) throws TException {
    TokenD tokenD = getTokenD(token);

    Account account = new Account();
    tokenD.account.syncToThrift(account);
    return account;
  }

  @Override
  public List<Access> getAccesses(String token) throws TException {
    TokenD tokenD = getTokenD(token);

    List<Access> list = new ArrayList<Access>();
    for(AccessD a : getDS().createQuery(AccessD.class).field("account").equal(tokenD.account).asList()){
      Access access = new Access();
      a.syncToThrift(access);
      list.add(access);
    }
    return list;
  }

  @Override
  public Access saveAccessToken(String token, Access access, String accessToken, String accessSecret, String accessShardId) throws TException {
    TokenD tokenD = getTokenD(token);

    AccessD accessD;

    if (!access.id.equals("") && access.id != null) {
      accessD = getDS().get(AccessD.class, access.getId());
    } else {
      accessD = new AccessD();
    }
    accessD.syncFromThrift(access);
    // Token is renewed
    accessD.type = access.type;
    accessD.accessToken = accessToken;
    accessD.accessSecret = accessSecret;
    accessD.shardId = accessShardId;
    accessD.expired = false;
    // Link an account
    accessD.account = tokenD.account;

    getDS().save(accessD);

    // Sync back
    accessD.syncToThrift(access);

    return access;
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

  @Override
  public Access saveAccess(String token, Access access) throws TException {
    TokenD tokenD = getTokenD(token);

    AccessD accessD = findAccessD(access);

    if(accessD.account != tokenD.account) {
      throw new TException("Access denied");
    }

    accessD.syncFromThrift(access);
    getDS().save(accessD);

    // Sync back
    accessD.syncToThrift(access);

    return access;
  }

  @Override
  public Account createAccessAndAccount(String serverToken, Access access, String accessToken, String accessSecret, String accessShardId) throws TException {
    if(!serverToken.equals("hardCode")) {
      throw new TException("Access denied for token or wrong token given");
    }

    AccessD accessD = findAccessD(access);

    // Token is renewed
    accessD.type = access.type;
    accessD.accessToken = accessToken;
    accessD.accessSecret = accessSecret;
    accessD.shardId = accessShardId;
    accessD.expired = false;

    if(accessD.account == null) {
      accessD.account = new AccountD();
      accessD.account.title = accessD.title;
      getDS().save(accessD.account);
    }

    getDS().save(accessD);

    Account account = new Account();
    accessD.account.syncToThrift(account);

    return account;
  }

  @Override
  public Account saveAccount(String token, Account account) throws TException {
    TokenD tokenD = getTokenD(token);

    tokenD.account.syncFromThrift(account);
    getDS().save(tokenD.account);
    tokenD.account.syncToThrift(account);

    return account;
  }
}
