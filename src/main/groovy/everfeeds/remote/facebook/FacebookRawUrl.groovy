package everfeeds.remote.facebook

/**
 * @author Dmitry Kurinskiy
 * @since 14.05.11 11:22
 */
@Typed
public enum FacebookRawUrl {
  NEWS("https://graph.facebook.com/me/home"),
  EVENTS("https://graph.facebook.com/me/events"),
  WALL("https://graph.facebook.com/me/feed");

  private url

  private FacebookRawUrl(String url) {
    this.url = url
  }

  public String toString() {
    url
  }
}