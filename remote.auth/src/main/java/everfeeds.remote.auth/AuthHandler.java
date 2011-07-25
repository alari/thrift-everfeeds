package everfeeds.remote.auth;

import everfeeds.remote.auth.thrift.AuthFlow;
import everfeeds.remote.auth.thrift.Credentials;
import everfeeds.remote.auth.thrift.util.*;
import everfeeds.remote.auth.variant.Auth;
import everfeeds.remote.auth.variant.AuthOAuth;
import everfeeds.remote.handshake.HandshakeHandler;
import org.apache.thrift.TException;

import java.util.List;

/**
 * @author Dmitry Kurinskiy
 * @since 16.07.11 3:20
 */
public class AuthHandler extends HandshakeHandler implements AuthFlow.Iface {

  public List<AuthVariant> listAuthVariants() throws TException {
    return Auth.listVariants();
  }

  public boolean checkCredentials(Credentials credentials) throws TException, AuthVariantUnknown {
    Auth auth = Auth.getByVariant(credentials.variant);
    if(auth == null) {
      throw new AuthVariantUnknown().setMsg("Cannot find auth by given variant");
    }
    return auth.checkCredentials(credentials);
  }

  public OAuthStep getOAuthStep(AuthVariant authVariant, String callbackUrl) throws TException, AuthMethodMismatch, AuthVariantUnknown {
    if (authVariant.method != AuthMethod.OAUTH) {
      throw new AuthMethodMismatch().setMsg("Cannot handle OAuth flow for not-OAuth variant");
    }
    AuthOAuth auth = (AuthOAuth)Auth.getByVariant(authVariant);
    if(auth == null) {
      throw new AuthVariantUnknown().setMsg("Cannot find auth by given variant");
    }
    return auth.getOAuthStep(callbackUrl);
  }

  public Credentials exchangeOAuthToken(OAuthStep oAuthStep, String verifierCode) throws TException, AuthMethodMismatch, AuthVariantUnknown, AuthFailed {
    if (oAuthStep.variant.method != AuthMethod.OAUTH) {
      throw new AuthMethodMismatch().setMsg("Cannot handle OAuth flow for not-OAuth variant");
    }
    AuthOAuth auth = (AuthOAuth)Auth.getByVariant(oAuthStep.variant);
    if(auth == null) {
      throw new AuthVariantUnknown().setMsg("Cannot find auth by given variant");
    }
    return auth.exchangeOAuthToken(oAuthStep, verifierCode);
  }
}
