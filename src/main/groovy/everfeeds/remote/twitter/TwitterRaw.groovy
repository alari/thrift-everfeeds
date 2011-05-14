package everfeeds.remote.twitter

import everfeeds.remote.OAuthAccess;

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

  public getJson(OAuthAccess access, TwitterRawUrl url, short maxCount){
    access.callOAuthApiJSON "${url}?count=${maxCount}&include_entities=1";
  }

  public postJson(OAuthAccess access, TwitterRawUrl url, Map<String,String> params) {
    access.callOAuthApiJSON url.toString(), params
  }
}