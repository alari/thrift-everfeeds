package everfeeds.remote.twitter

/**
 * @author Dmitry Kurinskiy
 * @since 14.05.11 11:32
 */
@Typed
public enum TwitterTag {
  RT("retweet", {it?.retweet_status}),
  REPLY("reply", {it?.in_reply_to_user_id?.size()}),
  WITH_MENTIONS("withMentions", {it?.entities?.user_mentions?.size()}),
  LINK("link", {it?.entities?.urls?.size()}),
  HASH("hash", {it?.entities?.hashtags?.size()});

  private static Map<String, TwitterTag> byIdentity = [:]

  static {
    TwitterTag.values().each {
      byIdentity.put it.identity, it
    }
  }

  public static TwitterTag getByIdentity(String identity) {
    byIdentity.get(identity)
  }

  private String identity
  private Closure checker

  private TwitterTag(String identity, Closure checker) {
    this.identity = identity
    this.checker = checker
  }

  public String getIdentity() {
    identity
  }

  public boolean check(node) {
    checker.call(node)
  }
}
