package everfeeds.remote.auth.system;

import everfeeds.remote.auth.annotation.OAuthProvider;
import everfeeds.remote.auth.config.AuthAccessConfig;
import everfeeds.remote.auth.config.AuthConfig;
import everfeeds.remote.auth.thrift.Credentials;
import everfeeds.remote.auth.thrift.ex.AuthConnectionError;
import everfeeds.remote.auth.thrift.ex.AuthFailed;
import everfeeds.remote.auth.thrift.ex.AuthMethodMismatch;
import everfeeds.remote.auth.thrift.ex.AuthSystemUnknown;
import everfeeds.remote.auth.thrift.util.OAuthStep;
import org.scribe.builder.ServiceBuilder;
import org.scribe.exceptions.OAuthException;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

/**
 * @author Dmitry Kurinskiy
 * @since 25.07.11 1:58
 */
abstract public class AuthOAuth extends Auth {

  private static final Token EMPTY_TOKEN = null;

  private Class<? extends org.scribe.builder.api.Api> provider = this.getClass().getAnnotation(OAuthProvider.class) != null ? this.getClass().getAnnotation(OAuthProvider.class).value() : null;
  private OAuthService service = null;

  /**
   * Returns an OAuth step data: auth url, request token
   * <p/>
   * Auth flow should look like:
   * getOAuthStep -> proceed to step.authUrl
   * get back to callbackUrl -> get verifier code from query string
   * proceed to exchangeOAuthToken(step, verifier) -> got Credentials
   *
   * @param callbackUrl URL to a page where OAuth response will be handled and token will be exchanged
   * @return OAuthStep
   * @throws AuthConnectionError if cannot connect
   */
  public OAuthStep getOAuthStep(String callbackUrl) throws AuthConnectionError {
    OAuthStep step = new OAuthStep();
    OAuthService service = getOAuthService(callbackUrl);

    Token requestToken = EMPTY_TOKEN;
    if (service instanceof org.scribe.oauth.OAuth10aServiceImpl) {
      try {
        requestToken = service.getRequestToken();
      } catch (OAuthException e) {
        throw new AuthConnectionError().setMsg(e.getMessage());
      }
      step.requestToken = requestToken.getToken();
      step.requestSecret = requestToken.getSecret();
    }

    try {
      step.authUrl = service.getAuthorizationUrl(requestToken);
    } catch(Throwable e) {
      throw new AuthConnectionError().setMsg(e.getClass().getCanonicalName()+": "+e.getMessage());
    }
    step.system = getSystem();

    return step;
  }

  /**
   * Exchanges requested token with a verifier string to an access token, enveloped into Credentials
   *
   * @param oAuthStep    given by {getOAuthStep}
   * @param verifierCode string from remote server
   * @return Credentials to be used in all future API calls
   * @throws AuthFailed if access token is not given
   * @throws AuthConnectionError if cannot connect to remote end
   */
  public Credentials exchangeOAuthToken(OAuthStep oAuthStep, String verifierCode) throws AuthFailed, AuthConnectionError {
    Token accessToken = getOAuthAccessToken(oAuthStep, verifierCode);

    if (accessToken == null) {
      throw new AuthFailed().setMsg("Authentication failed");
    }
    if (accessToken.getToken() == null || accessToken.getToken().isEmpty()) {
      throw new AuthFailed().setMsg("Authentication failed: no token given. Raw response is:\n" + accessToken.getRawResponse());
    }
    return new Credentials().setToken(accessToken.getToken()).setSecret(accessToken.getSecret()).setSystem(getSystem());
  }

  /**
   * Returns Access Token object to build Credentials from
   *
   * @param oAuthStep    step
   * @param verifierCode given by remote server
   * @return Token
   * @throws AuthConnectionError if cannot connect
   */
  protected Token getOAuthAccessToken(OAuthStep oAuthStep, String verifierCode) throws AuthConnectionError {
    Verifier verifier = new Verifier(verifierCode);

    Token requestToken = EMPTY_TOKEN;
    OAuthService service = getOAuthService();
    if (service instanceof org.scribe.oauth.OAuth10aServiceImpl) {
      requestToken = new Token(oAuthStep.requestToken, oAuthStep.requestSecret);
    }

    try {
      return service.getAccessToken(requestToken, verifier);
    } catch(OAuthException e) {
      throw new AuthConnectionError().setMsg(e.getMessage());
    } catch (Throwable e) {
      System.err.println(e.getMessage());
      return null;
    }
  }

  public boolean checkCredentials(Credentials credentials) throws AuthConnectionError {
    try {
      return checkOAuthCredentials(credentials);
    } catch (AuthSystemUnknown authSystemUnknown) {
      // ignore
    } catch (AuthMethodMismatch authMethodMismatch) {
      // ignore
    } catch (AuthFailed authFailed) {
      // not authenticated
      return false;
    }
    return false;
  }

  abstract protected boolean checkOAuthCredentials(Credentials credentials) throws AuthSystemUnknown, AuthMethodMismatch, AuthFailed, AuthConnectionError;

  /**
   * Returns configured OAuth Service to call APIs
   *
   * @return OAuthService
   */
  public OAuthService getOAuthService() {
    if (service == null) {
      service = getOAuthService("");
    }
    return service;
  }

  protected OAuthService getOAuthService(String callback) {
    AuthAccessConfig conf = AuthConfig.getAccessConfig(getSystem().name);
    ServiceBuilder builder = new ServiceBuilder().provider(provider).apiKey(conf.key).apiSecret(conf.secret);
    if (!callback.isEmpty()) {
      builder.callback(callback);
    }
    if (conf.scope != null && !conf.scope.isEmpty()) {
      builder.scope(conf.scope);
    }
    return builder.build();
  }
}
