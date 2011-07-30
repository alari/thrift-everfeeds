package everfeeds.remote.auth.system;

import everfeeds.remote.auth.annotation.AccessAuth;
import everfeeds.remote.auth.annotation.OAuthProvider;
import everfeeds.remote.auth.thrift.Credentials;
import everfeeds.remote.auth.thrift.ex.AuthConnectionError;
import everfeeds.remote.auth.thrift.ex.AuthFailed;
import everfeeds.remote.auth.thrift.ex.AuthMethodMismatch;
import everfeeds.remote.auth.thrift.ex.AuthSystemUnknown;
import everfeeds.remote.auth.thrift.util.AccessType;
import everfeeds.remote.auth.thrift.util.AuthMethod;
import everfeeds.remote.util.OAuthApi;
import org.scribe.builder.api.FacebookApi;
import org.springframework.stereotype.Component;

/**
 * @author Dmitry Kurinskiy
 * @since 25.07.11 14:57
 */
@AccessAuth(system = "facebook", method = AuthMethod.OAUTH, type = AccessType.FACEBOOK)
@OAuthProvider(FacebookApi.class)
@Component
public class FacebookAuth extends AuthOAuth {
  final static private String CREDENTIALS_URL = "https://graph.facebook.com/me";

  @Override
  protected boolean checkOAuthCredentials(Credentials credentials) throws AuthSystemUnknown, AuthMethodMismatch, AuthFailed, AuthConnectionError {
    System.out.println(new OAuthApi(credentials).callApi(CREDENTIALS_URL));
    return false;
  }
}
