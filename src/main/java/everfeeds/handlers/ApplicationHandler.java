package everfeeds.handlers;

import everfeeds.Scope;
import everfeeds.mongo.AccessD;
import everfeeds.mongo.AccountD;
import everfeeds.mongo.ApplicationD;
import everfeeds.mongo.TokenD;
import everfeeds.thrift.domain.Access;
import everfeeds.thrift.domain.Account;
import everfeeds.thrift.error.Forbidden;
import everfeeds.thrift.error.NotFound;
import everfeeds.thrift.service.ApplicationAPI;
import everfeeds.thrift.domain.Token;
import org.apache.thrift.TException;

import java.util.List;

/**
 * @author Dmitry Kurinskiy
 * @since 09.05.11 15:49
 */
public class ApplicationHandler extends Handler implements ApplicationAPI.Iface {
  @Override
  public Token createToken(String actApplicationSecret, String appId, String accountId, List<String> scopes) throws TException, NotFound, Forbidden {
    ApplicationD actAppD = getApplicationD(actApplicationSecret);

    if(actAppD == null || !actAppD.hasScope(Scope.APP_CREATE_TOKEN)) {
      throw new Forbidden("Access denied for actor token");
    }

    ApplicationD appD = getDS().get(ApplicationD.class, appId);
    if(appD == null) {
      throw new NotFound("App not found for id");
    }

    AccountD accountD = getDS().get(AccountD.class, accountId);

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
    tokenD.scopes.clear();
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
  public void createApp(String actApplicationSecret, String key, String secret, List<String> scopes) throws Forbidden, NotFound, TException {
    ApplicationD actAppD = getApplicationD(actApplicationSecret);

    if(actAppD == null || !actAppD.hasScope(Scope.APP_CREATE_TOKEN)) {
      throw new Forbidden("Access denied for actor token");
    }

    ApplicationD appD = new ApplicationD();
    appD.key = key;
    appD.secret = secret;
    appD.scopes = scopes;
    getDS().save(appD);
  }

  @Override
  public Account createAccessAndAccount(String applicationSecret, Access access, String accessToken, String accessSecret, List<String> accessParams) throws TException, Forbidden, NotFound {
    ApplicationD applicationD = getApplicationD(applicationSecret);

    if (!applicationD.hasScope(Scope.APP_CREATE_ACCOUNT)) {
      throw new Forbidden("Access denied for token or wrong token given");
    }

    AccessD accessD = findAccessD(access);

    // Token is renewed
    accessD.type = access.type;
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

    Account account = new Account();
    accessD.account.syncToThrift(account);

    return account;

  }

}
