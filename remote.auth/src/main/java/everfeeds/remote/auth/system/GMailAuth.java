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
import org.scribe.builder.api.GoogleApi;
import org.springframework.stereotype.Component;

/**
 * @author Dmitry Kurinskiy
 * @since 25.07.11 14:57
 */
@AccessAuth(system = "gmail", method = AuthMethod.OAUTH, type = AccessType.GMAIL)
@OAuthProvider(GoogleApi.class)
@Component
public class GMailAuth extends AuthOAuth {
  static final private String EMAIL_URL = "https://www.googleapis.com/userinfo/email";

  @Override
  protected boolean checkOAuthCredentials(Credentials credentials) throws AuthSystemUnknown, AuthMethodMismatch, AuthFailed, AuthConnectionError {
    String email = new OAuthApi(credentials).callApi(EMAIL_URL);
    email = email != null && !email.isEmpty() ? email.substring(email.indexOf("=") + 1, email.indexOf("&")) : null;
    return !(email == null || email.isEmpty());
  }
}
