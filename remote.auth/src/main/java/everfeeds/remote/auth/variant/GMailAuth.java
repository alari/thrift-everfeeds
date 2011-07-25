package everfeeds.remote.auth.variant;

import everfeeds.remote.auth.annotation.AccessAuth;
import everfeeds.remote.auth.annotation.OAuthProvider;
import everfeeds.remote.auth.thrift.Credentials;
import everfeeds.remote.auth.thrift.util.*;
import org.scribe.builder.api.GoogleApi;

/**
 * @author Dmitry Kurinskiy
 * @since 25.07.11 14:57
 */
@AccessAuth(system = "gmail", method = AuthMethod.OAUTH, type = AccessType.GMAIL)
@OAuthProvider(GoogleApi.class)
public class GMailAuth extends AuthOAuth{
  static private GMailAuth instance = new GMailAuth();
  private GMailAuth() {}

  static {
    Auth.registerInstance(instance);
  }

  @Override
  protected boolean checkOAuthCredentials(Credentials credentials) throws AuthVariantUnknown, AuthMethodMismatch, AuthFailed {
    return false;
  }
}
