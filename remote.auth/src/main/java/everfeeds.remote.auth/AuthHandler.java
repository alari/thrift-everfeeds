package everfeeds.remote.auth;

import everfeeds.remote.auth.thrift.AuthFlow;
import everfeeds.remote.auth.thrift.Credentials;
import everfeeds.remote.auth.thrift.ex.AuthFailed;
import everfeeds.remote.auth.thrift.ex.AuthMethodMismatch;
import everfeeds.remote.auth.thrift.ex.AuthSystemUnknown;
import everfeeds.remote.auth.thrift.util.*;
import everfeeds.remote.auth.system.Auth;
import everfeeds.remote.auth.system.AuthOAuth;
import everfeeds.remote.handshake.HandshakeHandler;
import org.apache.thrift.TException;

import java.util.List;

/**
 * @author Dmitry Kurinskiy
 * @since 16.07.11 3:20
 */
public class AuthHandler extends HandshakeHandler implements AuthFlow.Iface {

  public List<AuthSystem> listAuthSystems() throws TException {
    return Auth.listSystems();
  }

  public boolean checkCredentials(Credentials credentials) throws TException, AuthSystemUnknown {
    Auth auth = Auth.getBySystem(credentials.system);
    if(auth == null) {
      throw new AuthSystemUnknown().setMsg("Cannot find auth by given name");
    }
    return auth.checkCredentials(credentials);
  }

  public OAuthStep getOAuthStep(AuthSystem authSystem, String callbackUrl) throws TException, AuthMethodMismatch, AuthSystemUnknown {
    if (authSystem.method != AuthMethod.OAUTH) {
      throw new AuthMethodMismatch().setMsg("Cannot handle OAuth flow for not-OAuth name");
    }
    AuthOAuth auth = (AuthOAuth)Auth.getBySystem(authSystem);
    if(auth == null) {
      throw new AuthSystemUnknown().setMsg("Cannot find auth by given name");
    }
    return auth.getOAuthStep(callbackUrl);
  }

  public Credentials exchangeOAuthToken(OAuthStep oAuthStep, String verifierCode) throws TException, AuthMethodMismatch, AuthSystemUnknown, AuthFailed {
    if (oAuthStep.system.method != AuthMethod.OAUTH) {
      throw new AuthMethodMismatch().setMsg("Cannot handle OAuth flow for not-OAuth name");
    }
    AuthOAuth auth = (AuthOAuth)Auth.getBySystem(oAuthStep.system);
    if(auth == null) {
      throw new AuthSystemUnknown().setMsg("Cannot find auth by given name");
    }
    return auth.exchangeOAuthToken(oAuthStep, verifierCode);
  }
}
