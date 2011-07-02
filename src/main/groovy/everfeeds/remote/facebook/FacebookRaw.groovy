@Typed package everfeeds.remote.facebook

import everfeeds.util.OAuthAccess
import everfeeds.remote.Remote
import everfeeds.remote.Raw

/**
 * @author Dmitry Kurinskiy
 * @since 14.05.11 11:17
 */
public class FacebookRaw extends Raw{
  private static FacebookRaw ourInstance = new FacebookRaw();

  public static FacebookRaw getInstance() {
    return ourInstance;
  }

  private FacebookRaw() {
  }

  public getJson(OAuthAccess access, FacebookRawUrl url, int maxCount = Remote.PULL_MAX, String query = "") {
    if(testingContent) {
      return cleanTestingContent
    }

    access.callOAuthApiJSON url.toString();
  }

  public postJson(OAuthAccess access, FacebookRawUrl url, Map<String, String> params) {
    access.callOAuthApiJSON url.toString(), params
  }
}