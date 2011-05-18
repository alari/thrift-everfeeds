package everfeeds.secure.handlers;

import everfeeds.handlers.Handler;
import everfeeds.thrift.util.Scope;
import everfeeds.mongo.AccessD;
import everfeeds.mongo.AccountD;
import everfeeds.mongo.ApplicationD;
import everfeeds.mongo.TokenD;
import everfeeds.thrift.domain.Access;
import everfeeds.thrift.domain.Account;
import everfeeds.thrift.error.Forbidden;
import everfeeds.thrift.error.NotFound;
import everfeeds.secure.thrift.ApplicationAPI;
import everfeeds.thrift.domain.Token;
import everfeeds.thrift.util.Type;
import org.apache.thrift.TException;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * @author Dmitry Kurinskiy
 * @since 09.05.11 15:49
 */
public class ApplicationHandler extends Handler implements ApplicationAPI.Iface {
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
    if(tokenD.scopes != null) tokenD.scopes.clear();
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
  public Account createAccessAndAccount(Access access, String accessToken, String accessSecret, List<String> accessParams) throws TException, Forbidden, NotFound {

    AccessD accessD = findAccessD(access);
    System.out.println("Found access: ("+accessD.id+") for "+access.identity+" : "+access.type);
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
    System.out.println(accessD.id+" "+accessD.identity+" "+accessD.type);

    Account account = new Account();
    accessD.account.syncToThrift(account);
    return account;

  }

  @Override
  public String ping() throws TException {
    return "pong";
  }

}
