@Typed package everfeeds.handlers.secure;

import everfeeds.adapters.FilterAdapter;
import everfeeds.handlers.Handler
import everfeeds.internal.thrift.Application
import everfeeds.internal.thrift.InternalAPI
import everfeeds.remote.RemoteFactory
import everfeeds.thrift.error.Forbidden
import everfeeds.thrift.error.NotFound
import everfeeds.thrift.util.Type
import org.apache.commons.lang.RandomStringUtils
import org.apache.thrift.TException
import everfeeds.mongo.*
import everfeeds.thrift.domain.*

/**
 * @author Dmitry Kurinskiy
 * @since 09.05.11 15:49
 */
public class InternalHandler extends Handler implements InternalAPI.Iface {
  @Override
  public Token createToken(String appId, String accountId, List<String> scopes) throws TException, NotFound, Forbidden {
    ApplicationD appD = applicationDAO.getById(appId)
    if(appD == null) {
      throw new NotFound("App not found for id");
    }

    AccountD accountD = accountDAO.getById(accountId)

    TokenD tokenD = tokenDAO.getByApplicationAndAccount(appD, accountD)
    if (tokenD != null) {
      tokenDAO.delete(tokenD);
    }

    tokenD = new TokenD();
    tokenD.key = RandomStringUtils.randomAlphanumeric(127)
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
    tokenDAO.save(tokenD);

    Token token = new Token();
    tokenD.syncToThrift(token);

    return token;
  }

  @Override
  public Application saveApp(Application app) throws Forbidden, TException {
    ApplicationD appD = applicationDAO.getByKey(app.key)
    if(appD == null) {
      appD = new ApplicationD();
    }
    appD.syncFromThrift(app);
    applicationDAO.save(appD);
    appD.syncToThrift(app);
    return app;
  }

  @Override
  public List<Application> listApps() throws TException {
    List<Application> apps = new ArrayList<Application>();
    applicationDAO.list().each {ApplicationD appD ->
      Application app = new Application();
      appD.syncToThrift(app);
      apps.add(app);
    }
    return apps;
  }

  @Override
  public Account authenticate(Access access, String accessToken, String accessSecret, Map<String,String> accessParams) throws TException, Forbidden, NotFound {

    AccessD accessD = findAccessD(access);
    accessD.syncFromThrift(access)

    // Token is renewed
    accessD.type = Type.getByThrift(access.type);
    accessD.accessToken = accessToken;
    accessD.accessSecret = accessSecret;
    accessD.params = accessParams;
    accessD.expired = false;

    // It's a linkage to an existing account
    if(access.accountId) {
      // TODO: We need to rewire an access to a new account; not implemented yet
      if(accessD.account && accessD.account.id.toString() != access.accountId) {
        throw new Forbidden("Access already have been saved; accounts doesn't match; cannot rewire access to a new account (not implemented yet)")
      }
      // Get account by id, wire access to it
      if(!accessD.account) {
        accessD.account = accountDAO.getById(access.accountId)
        if(!accessD.account) throw new NotFound("Account not found for id ${access.accountId}")
      }
    // It's a new, fresh account to be created
    } else if (accessD.account == null) {
      accessD.account = new AccountD();
      accessD.account.title = accessD.title ?: "Everfeeds.com Account";
      accountDAO.save(accessD.account);
    }

    accessDAO.save(accessD);
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
    if(filter.accessId == null || filter.accessId.isEmpty()) {
      throw new NotFound("Access not found by id: no id provided");
    }
    filterD.access = accessDAO.getById(filter.accessId)
    if(filterD.access == null) {
      throw new NotFound("Access not found by id: "+filter.accessId);
    }
    filterD = FilterAdapter.setFilterRelationsFromThrift(filterD, filter);
    return RemoteFactory.getInstance(filterD.access.type).pullToThrift(filterD);
  }

  @Override
  public void remoteSaveEntries(Filter filter) throws Forbidden, NotFound, TException {
    FilterD filterD = new FilterD();
    filterD.syncFromThrift(filter);
    filterD.access = accessDAO.getById(filter.accessId);
    if(filterD.access == null) {
      throw new NotFound("Access not found by id");
    }
    filterD = FilterAdapter.setFilterRelationsFromThrift(filterD, filter);
    RemoteFactory.getInstance(filterD.access.type).saveEntries(filterD);
  }

}
