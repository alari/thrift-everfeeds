package everfeeds.remote

import everfeeds.ConfigHolder
import everfeeds.mongo.AccessD
import everfeeds.thrift.util.Type
import groovy.json.JsonSlurper
import org.apache.log4j.Logger
import org.scribe.builder.ServiceBuilder
import org.scribe.model.OAuthRequest
import org.scribe.model.Response
import org.scribe.model.Token
import org.scribe.model.Verb
import org.scribe.oauth.OAuthService

/**
 * @author Dmitry Kurinskiy
 * @since 13.05.11 18:02
 */
@Typed
class OAuthAccess {
  private AccessD accessD;

  OAuthAccess(AccessD accessD) {
    this.accessD = accessD
  }

  final public String callOAuthApi(String url, Verb verb) {
    callOAuthApi(url, [:], verb)
  }

  final public String callOAuthApi(String url) {
    callOAuthApi(url, [:], Verb.GET)
  }

  final public String callOAuthApi(String url, Map<String, String> params) {
    callOAuthApi(url, params, Verb.POST)
  }

  static private Map<Type, OAuthService> cachedServices = [:];

  static private Logger log = Logger.getLogger(OAuthAccess)

  @Typed(TypePolicy.MIXED)
  static public OAuthService getOAuthService(Type type) {
    if (!cachedServices.containsKey(type)) {
      ConfigObject conf = ConfigHolder.config.oauth."${type.toString()}"
      cachedServices.put(type, buildOAuthService(
          conf.key.toString(),
          conf.secret.toString(),
          conf.provider as Class
      ))
    }
    cachedServices.get(type)
  }

  static private OAuthService buildOAuthService(String key, String secret, Class provider) {
    new ServiceBuilder().provider(provider).apiKey(key).apiSecret(secret).build()
  }

  final public String callOAuthApi(String url, Map<String, String> params, Verb verb) {
    try {
      OAuthService service = getOAuthService(accessD.type)
      OAuthRequest request = new OAuthRequest(verb, url);

      if (params.size()) {
        params.each {k, v ->
          if (verb == Verb.GET) {
            request.addQuerystringParameter k, v
          } else {
            request.addBodyParameter k, v
          }
        }
      }

      Token tkn = new Token(accessD.accessToken, accessD.accessSecret)
      service.signRequest(tkn, request);
      Response result = sendRequest(request)
      if (result != null) {
        if (result.body != null) {
          return result?.body;
        } else if (((Response) result).headers.get("Content-Type").toLowerCase().contains("text/javascript")) {
          Response response = ((Response) result)
          return response.getStream().getText("UTF-8") // TODO(low): other encodings
        }
      }
      return "{}"


    } catch (e) {
      log.error "OAuth Api call failed", e
    }
    "{}"
  }

  @Typed(TypePolicy.DYNAMIC)
  final private Response sendRequest(OAuthRequest request){
     request.send()
  }

  @Typed(TypePolicy.MIXED)
  final public Object callOAuthApiJSON(String url, Verb verb) {
    new JsonSlurper().parseText callOAuthApi(url, verb)
  }

  @Typed(TypePolicy.MIXED)
  final public Object callOAuthApiJSON(String url) {
    new JsonSlurper().parseText callOAuthApi(url)
  }

  @Typed(TypePolicy.MIXED)
  final public Object callOAuthApiJSON(String url, Map<String, String> params) {
    new JsonSlurper().parseText callOAuthApi(url, params)
  }

  @Typed(TypePolicy.MIXED)
  final public Object callOAuthApiJSON(String url, Map<String, String> params, Verb verb) {
    new JsonSlurper().parseText callOAuthApi(url, params, verb)
  }
}
