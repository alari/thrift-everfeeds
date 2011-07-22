package everfeeds.remote.auth.variant;

import everfeeds.remote.auth.annotation.AccessAuth;
import everfeeds.remote.auth.config.AuthAccessConfig;
import everfeeds.remote.auth.config.AuthConfig;
import everfeeds.remote.auth.thrift.util.AuthVariant;
import everfeeds.remote.auth.thrift.util.OAuthStep;
import org.scribe.builder.ServiceBuilder;
import org.scribe.model.Token;
import org.scribe.oauth.OAuthService;

/**
 * @author Dmitry Kurinskiy
 * @since 20.07.11 16:30
 */
abstract public class Auth {
  private AuthVariant variant = null;

  public AuthVariant getVariant(){
    if(variant == null) {
      AccessAuth a = this.getClass().getAnnotation(AccessAuth.class);
      variant = new AuthVariant();
      variant.setSystem(a.system()).setMethod(a.method()).setType(a.type());
    }
    return variant;
  }

  abstract protected Class<? extends org.scribe.builder.api.Api> getProvider();

  public OAuthStep getOAuthStep(String redirectUrl) throws Exception{
    if(!AuthConfig.getInstance().accesses.containsKey(getVariant().system)) {
      throw new Exception("No config found for "+getVariant().system);
    }
    AuthAccessConfig conf = AuthConfig.getAccessConfig(getVariant().system);

    OAuthStep step = new OAuthStep();
        OAuthService service = new ServiceBuilder()
                                  .provider(getProvider())
                                  .apiKey(conf.key)
                                  .apiSecret(conf.secret)
                                  .scope(conf.scope)
                                  .callback(redirectUrl)
                                  .build();
    Token requestToken = service.getRequestToken();
    step.requestToken = requestToken.getToken();
    step.authUrl = service.getAuthorizationUrl(requestToken);

    return step;
  }
}
