package everfeeds.remote.auth.system;

import everfeeds.remote.auth.annotation.AccessAuth;
import everfeeds.remote.auth.annotation.OAuthProvider;
import everfeeds.remote.auth.thrift.Credentials;
import everfeeds.remote.auth.thrift.ex.AuthConnectionError;
import everfeeds.remote.auth.thrift.ex.AuthMethodMismatch;
import everfeeds.remote.auth.thrift.ex.AuthSystemUnknown;
import everfeeds.remote.auth.thrift.util.AccessType;
import everfeeds.remote.auth.thrift.util.AuthMethod;
import everfeeds.remote.util.OAuthApi;
import org.scribe.builder.api.TwitterApi;
import org.springframework.stereotype.Component;

/**
 * @author Dmitry Kurinskiy
 * @since 20.07.11 16:22
 */
@AccessAuth(system = "twitter", method = AuthMethod.OAUTH, type = AccessType.TWITTER)
@OAuthProvider(TwitterApi.class)
@Component
public class TwitterAuth extends AuthOAuth {
  final static private String CREDENTIALS_URL = "http://api.twitter.com/1/account/verify_credentials.json";

  @Override
  public boolean checkOAuthCredentials(Credentials credentials) throws AuthSystemUnknown, AuthMethodMismatch, AuthConnectionError {
    return new OAuthApi(credentials).callApi(CREDENTIALS_URL).contains("\"id_str\":\"");
  }
}
