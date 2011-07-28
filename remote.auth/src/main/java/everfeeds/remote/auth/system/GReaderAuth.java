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

/**
 * @author Dmitry Kurinskiy
 * @since 25.07.11 14:57
 */
@AccessAuth(system = "greader", method = AuthMethod.OAUTH, type = AccessType.GREADER)
@OAuthProvider(GoogleApi.class)
public class GReaderAuth extends AuthOAuth {
  static final private String EMAIL_URL = "https://www.googleapis.com/userinfo/email";
  static private GReaderAuth instance = new GReaderAuth();

  private GReaderAuth() {
  }

  static {
    Auth.registerInstance(instance);
  }

  @Override
  protected boolean checkOAuthCredentials(Credentials credentials) throws AuthSystemUnknown, AuthMethodMismatch, AuthFailed, AuthConnectionError {
    String email = new OAuthApi(credentials).callApi(EMAIL_URL);
    email = email != null && !email.isEmpty() ? email.substring(email.indexOf("=") + 1, email.indexOf("&")) : null;
    System.out.println(email);
    return false;
  }
}
