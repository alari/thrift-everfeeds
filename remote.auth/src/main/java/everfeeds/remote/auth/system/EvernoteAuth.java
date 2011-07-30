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
import everfeeds.remote.auth.thrift.util.OAuthStep;
import org.scribe.builder.api.EvernoteApi;
import org.scribe.model.Token;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Dmitry Kurinskiy
 * @since 25.07.11 14:51
 */
@AccessAuth(system = "evernote", method = AuthMethod.OAUTH, type = AccessType.EVERNOTE)
@OAuthProvider(EvernoteApi.class)
@Component
public class EvernoteAuth extends AuthOAuth {
  static private final String SHARD_MARKER = "&edam_shard=";

  @Override
  public Credentials exchangeOAuthToken(OAuthStep oAuthStep, String verifierCode) throws AuthFailed, AuthConnectionError {
    System.out.println("Overrided exchangeOAuthToken...");
    Token accessToken = getOAuthAccessToken(oAuthStep, verifierCode);

    if (accessToken == null) {
      throw new AuthFailed().setMsg("Authentication failed");
    }
    if (accessToken.getToken() == null || accessToken.getToken().isEmpty()) {
      throw new AuthFailed().setMsg("Authentication failed: no token given. Raw response is:\n" + accessToken.getRawResponse());
    }

    int shardIndex = accessToken.getRawResponse().indexOf(SHARD_MARKER);
    Map<String,String> params = new HashMap<String,String>();
    params.put("shard", accessToken.getRawResponse().substring(shardIndex+SHARD_MARKER.length(), accessToken.getRawResponse().indexOf("&", shardIndex)));
    System.out.println("Shard = "+params.get("shard"));

    return new Credentials().setToken(accessToken.getToken()).setSecret(accessToken.getSecret()).setSystem(getSystem()).setParams(params);
  }

  @Override
  public OAuthStep getOAuthStep(String callbackUrl) throws AuthConnectionError {
    if(callbackUrl == null || callbackUrl.equals("") || callbackUrl.equals("oob")) {
      callbackUrl = "http://test.com/oauth_callback";
    }
    return super.getOAuthStep(callbackUrl);
  }

  @Override
  protected boolean checkOAuthCredentials(Credentials credentials) throws AuthSystemUnknown, AuthMethodMismatch, AuthFailed {
    System.out.println("TODO: find the way to check Evernote credentials with no dependency");
    return false;
  }
}
