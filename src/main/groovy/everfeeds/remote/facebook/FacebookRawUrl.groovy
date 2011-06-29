package everfeeds.remote.facebook

/**
 * @author Dmitry Kurinskiy
 * @since 14.05.11 11:22
 */
@Typed
public enum FacebookRawUrl {
  NEWS(GRAPH_ROOT + "me/home"),
  EVENTS(GRAPH_ROOT + "me/events"),
  WALL(GRAPH_ROOT + "me/feed");

  static private String GRAPH_ROOT = "https://graph.facebook.com/"

  private url

  private FacebookRawUrl(String url) {
    this.url = url
  }

  public String toString() {
    url
  }
}