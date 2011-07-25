package everfeeds.remote.auth.variant;

import everfeeds.remote.auth.annotation.AccessAuth;
import everfeeds.remote.auth.annotation.OAuthProvider;
import everfeeds.remote.auth.thrift.Credentials;
import everfeeds.remote.auth.thrift.util.*;
import org.scribe.builder.api.FacebookApi;
import org.scribe.builder.api.VkontakteApi;

/**
 * @author Dmitry Kurinskiy
 * @since 25.07.11 14:57
 */
@AccessAuth(system = "vk", method = AuthMethod.OAUTH, type = AccessType.VK)
@OAuthProvider(VkontakteApi.class)
public class VkAuth extends AuthOAuth{
  static private VkAuth instance = new VkAuth();
  private VkAuth() {}

  static {
    Auth.registerInstance(instance);
  }

  @Override
  protected boolean checkOAuthCredentials(Credentials credentials) throws AuthVariantUnknown, AuthMethodMismatch, AuthFailed {
    return false;
  }
}
