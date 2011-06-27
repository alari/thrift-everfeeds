package everfeeds.remote.twitter

/**
 * @author Dmitry Kurinskiy
 * @since 14.05.11 11:22
 */
@Typed
public enum TwitterRawUrl {
  HOME_TIMELINE("http://api.twitter.com/1/statuses/home_timeline.json"),
  MENTIONS("http://api.twitter.com/1/statuses/mentions.json"),
  UPDATE_STATUS("http://api.twitter.com/1/statuses/update.json");

  private url

  private TwitterRawUrl(String url) {
    this.url = url
  }

  public String toString() {
    url
  }
}