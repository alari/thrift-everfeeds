package everfeeds.remote.twitter

import everfeeds.remote.OAuthAccess

/**
 * @author Dmitry Kurinskiy
 * @since 14.05.11 11:17
 */
@Typed
public class TwitterRaw {
  private static TwitterRaw ourInstance = new TwitterRaw();

  public static TwitterRaw getInstance() {
    return ourInstance;
  }

  private TwitterRaw() {
  }

  public getJson(OAuthAccess access, TwitterRawUrl url, short maxCount = 800, String query = "") {
    String callUrl = url.toString()
    callUrl += callUrl.contains("?") ? "&" : "?"
    callUrl += "count=${maxCount}&include_entities=1"
    access.callOAuthApiJSON callUrl;
  }

  public postJson(OAuthAccess access, TwitterRawUrl url, Map<String, String> params) {
    access.callOAuthApiJSON url.toString(), params
  }
}