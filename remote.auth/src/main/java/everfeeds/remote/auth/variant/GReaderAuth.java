package everfeeds.remote.auth.variant;

import everfeeds.remote.auth.annotation.AccessAuth;
import everfeeds.remote.auth.annotation.OAuthProvider;
import everfeeds.remote.auth.thrift.Credentials;
import everfeeds.remote.auth.thrift.util.*;
import org.scribe.builder.api.GoogleApi;
import org.scribe.builder.api.VkontakteApi;

/**
 * @author Dmitry Kurinskiy
 * @since 25.07.11 14:57
 */
@AccessAuth(system = "greader", method = AuthMethod.OAUTH, type = AccessType.GREADER)
@OAuthProvider(GoogleApi.class)
public class GReaderAuth extends AuthOAuth{
  static private GReaderAuth instance = new GReaderAuth();
  private GReaderAuth() {}

  static {
    Auth.registerInstance(instance);
  }

  @Override
  protected boolean checkOAuthCredentials(Credentials credentials) throws AuthVariantUnknown, AuthMethodMismatch, AuthFailed {
    return false;
  }
}
