package everfeeds.remote.auth.variant;

import everfeeds.remote.auth.annotation.AccessAuth;
import everfeeds.remote.auth.annotation.OAuthProvider;
import everfeeds.remote.auth.thrift.util.AccessType;
import everfeeds.remote.auth.thrift.util.AuthMethod;
import org.scribe.builder.api.TwitterApi;

/**
 * @author Dmitry Kurinskiy
 * @since 20.07.11 16:22
 */
@AccessAuth(system = "twitter", method = AuthMethod.OAUTH2, type = AccessType.TWITTER)
@OAuthProvider(TwitterApi.class)
public class TwitterAuth extends AuthOAuth {
  static private TwitterAuth instance = new TwitterAuth();

  private TwitterAuth() {
  }

  static {
    Auth.registerInstance(instance);
  }
}
