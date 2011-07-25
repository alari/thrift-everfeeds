package everfeeds.remote.util;

import everfeeds.remote.auth.system.Auth;
import everfeeds.remote.auth.system.AuthOAuth;
import everfeeds.remote.auth.thrift.Credentials;
import everfeeds.remote.auth.thrift.ex.AuthConnectionError;
import everfeeds.remote.auth.thrift.ex.AuthMethodMismatch;
import everfeeds.remote.auth.thrift.ex.AuthSystemUnknown;
import everfeeds.remote.auth.thrift.util.AuthMethod;
import org.scribe.exceptions.OAuthException;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

/**
 * @author Dmitry Kurinskiy
 * @since 25.07.11 14:27
 */
public class OAuthApi {
  private Token accessToken;
  private OAuthService service;

  public OAuthApi(Token accessToken, OAuthService service) {
    this.accessToken = accessToken;
  }

  public OAuthApi(Credentials credentials) throws AuthSystemUnknown, AuthMethodMismatch {
    if (credentials.system.method != AuthMethod.OAUTH) {
      throw new AuthMethodMismatch().setMsg("Cannot call OAuth API on non-OAuth name");
    }
    AuthOAuth auth = (AuthOAuth) Auth.getBySystem(credentials.system);
    if (auth == null) {
      throw new AuthSystemUnknown().setMsg("While trying to call OAuth API noticed that auth name is unknown");
    }

    accessToken = new Token(credentials.token, credentials.secret);
    service = auth.getOAuthService();
  }

  public void callJsonApi(String url) throws AuthConnectionError {
  }

  public void callXmlApi(String url) throws AuthConnectionError {
  }

  public String callApi(String url) throws AuthConnectionError {
    OAuthRequest request = new OAuthRequest(Verb.GET, url);
    service.signRequest(accessToken, request);

    final Response result;
    try {
      result = request.send();
    } catch(OAuthException e) {
      throw new AuthConnectionError().setMsg(e.getMessage());
    }
    // TODO: check response code; auth might be failed
    if (result != null) {
      if (result.getBody() != null && !result.getBody().isEmpty()) {
        return result.getBody();
      } else if (result.getHeaders().get("Content-Type").toLowerCase().contains("text/javascript")) {
        return result.getStream().toString(); // TODO: test if it works or not
      }
    }
    return "";
  }
}
