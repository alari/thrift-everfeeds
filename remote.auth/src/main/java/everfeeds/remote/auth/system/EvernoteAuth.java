package everfeeds.remote.auth.system;

import everfeeds.remote.auth.annotation.AccessAuth;
import everfeeds.remote.auth.annotation.OAuthProvider;
import everfeeds.remote.auth.thrift.Credentials;
import everfeeds.remote.auth.thrift.ex.AuthFailed;
import everfeeds.remote.auth.thrift.ex.AuthMethodMismatch;
import everfeeds.remote.auth.thrift.ex.AuthSystemUnknown;
import everfeeds.remote.auth.thrift.util.*;
import org.scribe.builder.api.EvernoteApi;

/**
 * @author Dmitry Kurinskiy
 * @since 25.07.11 14:51
 */
@AccessAuth(system = "evernote", method = AuthMethod.OAUTH, type = AccessType.EVERNOTE)
@OAuthProvider(EvernoteApi.class)
public class EvernoteAuth extends AuthOAuth {
  static private EvernoteAuth instance = new EvernoteAuth();
  private EvernoteAuth() {}

  static {
    Auth.registerInstance(instance);
  }

  @Override
  protected boolean checkOAuthCredentials(Credentials credentials) throws AuthSystemUnknown, AuthMethodMismatch, AuthFailed {
    return false;
  }
}
