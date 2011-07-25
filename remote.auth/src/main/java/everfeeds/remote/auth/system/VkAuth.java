package everfeeds.remote.auth.system;

import everfeeds.remote.auth.annotation.AccessAuth;
import everfeeds.remote.auth.annotation.OAuthProvider;
import everfeeds.remote.auth.thrift.Credentials;
import everfeeds.remote.auth.thrift.ex.AuthFailed;
import everfeeds.remote.auth.thrift.ex.AuthMethodMismatch;
import everfeeds.remote.auth.thrift.ex.AuthSystemUnknown;
import everfeeds.remote.auth.thrift.util.AccessType;
import everfeeds.remote.auth.thrift.util.AuthMethod;
import org.scribe.builder.api.VkontakteApi;

/**
 * @author Dmitry Kurinskiy
 * @since 25.07.11 14:57
 */
@AccessAuth(system = "vk", method = AuthMethod.OAUTH, type = AccessType.VK)
@OAuthProvider(VkontakteApi.class)
public class VkAuth extends AuthOAuth {
  static private VkAuth instance = new VkAuth();

  private VkAuth() {
  }

  static {
    Auth.registerInstance(instance);
  }

  @Override
  protected boolean checkOAuthCredentials(Credentials credentials) throws AuthSystemUnknown, AuthMethodMismatch, AuthFailed {
    return false;
  }
}
