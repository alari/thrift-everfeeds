@Typed package everfeeds.remote.twitter

import everfeeds.util.OAuthAccess
import everfeeds.remote.Remote
import everfeeds.remote.Raw

/**
 * @author Dmitry Kurinskiy
 * @since 14.05.11 11:17
 */
public class TwitterRaw extends Raw {
  private static TwitterRaw ourInstance = new TwitterRaw();

  public static TwitterRaw getInstance() {
    return ourInstance;
  }

  private TwitterRaw() {
  }

  public getJson(OAuthAccess access, TwitterRawUrl url, int maxCount = Remote.PULL_MAX, String query = "") {
    if(testingContent) {
      return cleanTestingContent
    }

    String callUrl = url.toString()
    callUrl += callUrl.contains("?") ? "&" : "?"
    callUrl += "count=${maxCount}&include_entities=1"
    access.callOAuthApiJSON callUrl;
  }

  public postJson(OAuthAccess access, TwitterRawUrl url, Map<String, String> params) {
    access.callOAuthApiJSON url.toString(), params
  }
}