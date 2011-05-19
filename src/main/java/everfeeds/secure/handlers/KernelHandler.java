package everfeeds.secure.handlers;

import everfeeds.handlers.Handler;
import everfeeds.mongo.*;
import everfeeds.remote.twitter.TwitterRemote;
import everfeeds.secure.thrift.KernelAPI;
import everfeeds.thrift.domain.*;
import everfeeds.thrift.util.Scope;
import everfeeds.thrift.error.Forbidden;
import everfeeds.thrift.error.NotFound;
import everfeeds.thrift.util.Type;
import org.apache.thrift.TException;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dmitry Kurinskiy
 * @since 09.05.11 15:49
 */
public class KernelHandler extends Handler implements KernelAPI.Iface {
  @Override
  public Token createToken(String appId, String accountId, List<String> scopes) throws TException, NotFound, Forbidden {
    ApplicationD appD = getDS().get(ApplicationD.class, new ObjectId(appId));
    if(appD == null) {
      throw new NotFound("App not found for id");
    }

    AccountD accountD = getDS().get(AccountD.class, new ObjectId(accountId));

    TokenD tokenD = getDS().createQuery(TokenD.class)
                        .filter("application", appD)
                        .filter("account", accountD)
                        .get();
    if (tokenD != null) {
      getDS().delete(tokenD);
    }

    tokenD = new TokenD();
    tokenD.account = accountD;
    tokenD.application = appD;
    if(tokenD.scopes != null) {
      tokenD.scopes.clear();
    } else {
      tokenD.scopes = new ArrayList<String>();
    }
    for(String s : scopes) {
      if(appD.scopes.contains(s)) {
        tokenD.scopes.add(s);
      }
    }
    getDS().save(tokenD);

    Token token = new Token();
    tokenD.syncToThrift(token);

    return token;
  }

  @Override
  public String createApp(String key, String secret, List<String> scopes) throws Forbidden, NotFound, TException {
    ApplicationD appD = getDS().createQuery(ApplicationD.class).filter("key", key).get();
    if(appD == null) {
      appD = new ApplicationD();
      appD.key = key;
    }
    appD.secret = secret;
    appD.scopes = scopes;
    getDS().save(appD);
    return appD.id.toString();
  }

  @Override
  public Account authenticate(Access access, String accessToken, String accessSecret, List<String> accessParams) throws TException, Forbidden, NotFound {

    AccessD accessD = findAccessD(access);
    // Token is renewed
    accessD.type = Type.getByThrift(access.type);
    accessD.accessToken = accessToken;
    accessD.accessSecret = accessSecret;
    accessD.params = accessParams;
    accessD.expired = false;

    if (accessD.account == null) {
      accessD.account = new AccountD();
      accessD.account.title = accessD.title;
      getDS().save(accessD.account);
    }

    getDS().save(accessD);
    assert accessD.id != null;

    Account account = new Account();
    accessD.account.syncToThrift(account);
    return account;

  }

  @Override
  public String ping() throws TException {
    return "pong";
  }

  @Override
  public List<Entry> remotePullEntries(Filter filter) throws Forbidden, NotFound, TException {
    FilterD filterD = new FilterD();
    filterD.syncFromThrift(filter);
    filterD = setFilterRelationsFromThrift(filterD, filter);
    return new TwitterRemote().pullToThrift(filterD);
  }

  @Override
  public void remoteSaveEntries(Filter filter) throws Forbidden, NotFound, TException {
    FilterD filterD = new FilterD();
    filterD.syncFromThrift(filter);
    filterD = setFilterRelationsFromThrift(filterD, filter);
    new TwitterRemote().saveEntries(filterD);
  }

}
