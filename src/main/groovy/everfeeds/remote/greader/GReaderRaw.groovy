package everfeeds.remote.greader

import everfeeds.util.OAuthAccess
import everfeeds.remote.Raw

/**
 * @author Dmitry Kurinskiy
 * @since 02.07.11 12:39
 */
class GReaderRaw extends Raw {
  static private GReaderRaw ourInstance = new GReaderRaw()

  static public GReaderRaw getInstance() {
    ourInstance
  }

  private GReaderRaw() {}

  public getJson(OAuthAccess access, GReaderRawUrl url, String urlSuffix = "") {
    if(testingContent) {
      return cleanTestingContent
    }

    String callUrl = url.toString() + urlSuffix
    callUrl += callUrl.contains("?") ? "&" : "?"
    callUrl += "output=json"
    access.callOAuthApiJSON callUrl;
  }
}
