package everfeeds.remote.util;

import everfeeds.remote.auth.thrift.Credentials;
import everfeeds.remote.auth.thrift.util.AuthMethod;
import everfeeds.remote.auth.thrift.util.AuthMethodMismatch;
import everfeeds.remote.auth.thrift.util.AuthVariantUnknown;
import everfeeds.remote.auth.variant.Auth;
import everfeeds.remote.auth.variant.AuthOAuth;
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

  public OAuthApi(Token accessToken, OAuthService service){
    this.accessToken = accessToken;
  }

  public OAuthApi(Credentials credentials) throws AuthVariantUnknown, AuthMethodMismatch {
    if(credentials.variant.method != AuthMethod.OAUTH) {
      throw new AuthMethodMismatch().setMsg("Cannot call OAuth API on non-OAuth variant");
    }
    AuthOAuth auth = (AuthOAuth)Auth.getByVariant(credentials.variant);
    if(auth == null) {
      throw new AuthVariantUnknown().setMsg("While trying to call OAuth API noticed that auth variant is unknown");
    }

    accessToken = new Token(credentials.token, credentials.secret);
    service = auth.getOAuthService();
  }

  public void callJsonApi(String url){}

  public void callXmlApi(String url){}

  public String callApi(String url) {
    OAuthRequest request = new OAuthRequest(Verb.GET, url);
    service.signRequest(accessToken, request);

    final Response result = request.send();
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
