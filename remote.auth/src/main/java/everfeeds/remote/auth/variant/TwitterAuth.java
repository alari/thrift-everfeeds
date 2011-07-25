package everfeeds.remote.auth.variant;

import everfeeds.remote.auth.annotation.AccessAuth;
import everfeeds.remote.auth.annotation.OAuthProvider;
import everfeeds.remote.auth.thrift.Credentials;
import everfeeds.remote.auth.thrift.util.AccessType;
import everfeeds.remote.auth.thrift.util.AuthMethod;
import everfeeds.remote.auth.thrift.util.AuthMethodMismatch;
import everfeeds.remote.auth.thrift.util.AuthVariantUnknown;
import everfeeds.remote.util.OAuthApi;
import org.scribe.builder.api.TwitterApi;

/**
 * @author Dmitry Kurinskiy
 * @since 20.07.11 16:22
 */
@AccessAuth(system = "twitter", method = AuthMethod.OAUTH, type = AccessType.TWITTER)
@OAuthProvider(TwitterApi.class)
public class TwitterAuth extends AuthOAuth {
  static private TwitterAuth instance = new TwitterAuth();
  final static private String CREDENTIALS_URL = "http://api.twitter.com/1/account/verify_credentials.json";

  private TwitterAuth() {}

  static {
    Auth.registerInstance(instance);
  }

  @Override
  public boolean checkOAuthCredentials(Credentials credentials) throws AuthVariantUnknown, AuthMethodMismatch {
    new OAuthApi(credentials).callApi(CREDENTIALS_URL);
    return false;
  }
}
