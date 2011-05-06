package everfeeds.handlers;

import everfeeds.mongo.AccessD;
import everfeeds.mongo.TokenD;
import everfeeds.thrift.Access;
import everfeeds.thrift.AccessAPI;
import everfeeds.thrift.Account;
import everfeeds.thrift.AccountAPI;
import org.apache.thrift.TException;

import java.util.List;

/**
 * @author Dmitry Kurinskiy
 * @since 06.05.11 19:11
 */
public class AccountHandler extends Handler implements AccountAPI.Iface{
  @Override
  public Account getAccount(String token) throws TException {
    return null;
  }

  @Override
  public List<Access> getAccesses(String token) throws TException {
    return null;
  }

  @Override
  public Access saveAccessToken(String token, Access access, String accessToken, String accessSecret, String accessShardId) throws TException {
       TokenD tokenD = getTokenD(token);
    if(tokenD.expired) {
      throw new TException("Token expired");
    }

    AccessD accessD;

    if(!access.getId().equals("")) {
      accessD = getDS().get(AccessD.class, access.getId());
    } else {
      accessD = new AccessD();
    }
    accessD.accessToken = accessToken;
    accessD.accessSecret = accessSecret;
    accessD.shardId = accessShardId;
    accessD.title = access.getTitle();
    accessD.identity = access.getIdentity();
    accessD.expired = false;
    accessD.account = tokenD.account;
    accessD.screenName = access.getScreenName();

    getDS().save(accessD);

    access.setAccountId( accessD.account.id.toString() );
    access.setId( accessD.id.toString() );

    return access;
  }

  @Override
  public Access saveAccess(String token, Access access) throws TException {
    return null;
  }

  @Override
  public Account createAccessAndAccount(String token, Access access, String accessToken, String accessSecret, String accessShardId) throws TException {
    return null;
  }

  @Override
  public Account saveAccount(String token, Account account) throws TException {
    return null;
  }
}
