package everfeeds.remote.twitter

/**
 * @author Dmitry Kurinskiy
 * @since 14.05.11 11:31
 */
@Typed
public enum TwitterCategory {
  TIMELINE("timeline", TwitterRawUrl.HOME_TIMELINE),
  MENTIONS("mentions", TwitterRawUrl.MENTIONS),
  MESSAGES("messages", TwitterRawUrl.MESSAGES);

    private static Map<String,TwitterCategory> byIdentity = [:]

  static {
    TwitterCategory.values().each {
      byIdentity.put it.identity, it
    }
  }

  public static TwitterCategory getByIdentity(String identity) {
    byIdentity.get(identity)
  }

  private String identity
  private TwitterRawUrl url

  private TwitterCategory(String identity, TwitterRawUrl url) {
    this.identity = identity
    this.url = url
  }

  public String getIdentity() {
    identity
  }

  public TwitterRawUrl getUrl(){
    url
  }
}