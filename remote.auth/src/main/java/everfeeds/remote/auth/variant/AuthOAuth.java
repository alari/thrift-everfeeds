package everfeeds.remote.auth.variant;

import everfeeds.remote.auth.annotation.OAuthProvider;
import everfeeds.remote.auth.config.AuthAccessConfig;
import everfeeds.remote.auth.config.AuthConfig;
import everfeeds.remote.auth.thrift.Credentials;
import everfeeds.remote.auth.thrift.util.OAuthStep;
import org.scribe.builder.ServiceBuilder;
import org.scribe.model.Token;
import org.scribe.oauth.OAuthService;

/**
 * @author Dmitry Kurinskiy
 * @since 25.07.11 1:58
 */
public class AuthOAuth extends Auth{

  private Class<? extends org.scribe.builder.api.Api> provider = this.getClass().getAnnotation(OAuthProvider.class) != null ? this.getClass().getAnnotation(OAuthProvider.class).value() : null;

  public OAuthStep getOAuthStep(String redirectUrl) {
    OAuthStep step = new OAuthStep();
    OAuthService service = getOAuthService(redirectUrl);

    Token requestToken = service.getRequestToken();

    step.requestToken = requestToken.getToken();
    step.requestSecret = requestToken.getSecret();
    step.authUrl = service.getAuthorizationUrl(requestToken);

    return step;
  }

  public Credentials exchangeOAuthToken(OAuthStep oAuthStep, String verifierCode) {
    return null;
  }

  protected OAuthService getOAuthService() {
    return getOAuthService("");
  }

  protected OAuthService getOAuthService(String redirectUrl) {
    AuthAccessConfig conf = AuthConfig.getAccessConfig(getVariant().system);
    ServiceBuilder builder = new ServiceBuilder().provider(provider).apiKey(conf.key).apiSecret(conf.secret);
    if (!redirectUrl.isEmpty()) {
      builder.callback(redirectUrl);
    }
    if (conf.scope != null && !conf.scope.isEmpty()) {
      builder.scope(conf.scope);
    }
    return builder.build();
  }
}
