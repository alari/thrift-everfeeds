package everfeeds.handlers;

import everfeeds.mongo.AccessD;
import everfeeds.mongo.AccountD;
import everfeeds.mongo.ApplicationD;
import everfeeds.mongo.TokenD;
import everfeeds.thrift.Access;
import everfeeds.thrift.Account;
import everfeeds.thrift.ApplicationAPI;
import everfeeds.thrift.Token;
import org.apache.thrift.TException;

/**
 * @author Dmitry Kurinskiy
 * @since 09.05.11 15:49
 */
public class ApplicationHandler extends Handler implements ApplicationAPI.Iface {
  @Override
  public Token createToken(String applicationSecret, String accountId) throws TException {
    ApplicationD applicationD = getApplicationD(applicationSecret);
    AccountD accountD = getDS().get(AccountD.class, accountId);

    TokenD tokenD = getDS().createQuery(TokenD.class)
                        .filter("application", applicationD)
                        .filter("account", accountD)
                        .get();
    if (tokenD != null) {
      getDS().delete(tokenD);
    }

    tokenD = new TokenD();
    tokenD.account = accountD;
    tokenD.application = applicationD;
    getDS().save(tokenD);

    Token token = new Token();
    tokenD.syncToThrift(token);

    return token;
  }

  @Override
  public Account createAccessAndAccount(String applicationSecret, Access access, String accessToken, String accessSecret, String accessShardId) throws TException {


    if (!applicationSecret.equals("hardCode")) {
      throw new TException("Access denied for token or wrong token given");
    }

    AccessD accessD = findAccessD(access);

    // Token is renewed
    accessD.type = access.type;
    accessD.accessToken = accessToken;
    accessD.accessSecret = accessSecret;
    accessD.shardId = accessShardId;
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
