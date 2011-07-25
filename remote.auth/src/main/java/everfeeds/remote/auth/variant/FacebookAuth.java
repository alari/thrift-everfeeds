package everfeeds.remote.auth.variant;

import everfeeds.remote.auth.annotation.AccessAuth;
import everfeeds.remote.auth.annotation.OAuthProvider;
import everfeeds.remote.auth.thrift.Credentials;
import everfeeds.remote.auth.thrift.util.*;
import org.scribe.builder.api.FacebookApi;

/**
 * @author Dmitry Kurinskiy
 * @since 25.07.11 14:57
 */
@AccessAuth(system = "facebook", method = AuthMethod.OAUTH, type = AccessType.FACEBOOK)
@OAuthProvider(FacebookApi.class)
public class FacebookAuth extends AuthOAuth{
  static private FacebookAuth instance = new FacebookAuth();
  private FacebookAuth() {}

  static {
    Auth.registerInstance(instance);
  }

  @Override
  protected boolean checkOAuthCredentials(Credentials credentials) throws AuthVariantUnknown, AuthMethodMismatch, AuthFailed {
    return false;
  }
}
