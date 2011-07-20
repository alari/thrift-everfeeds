package everfeeds.remote.twitter

import everfeeds.mongo.CategoryD

/**
 * @author Dmitry Kurinskiy
 * @since 14.05.11 11:31
 */
@Typed
public enum TwitterCategory {
  TIMELINE("timeline", TwitterRawUrl.HOME_TIMELINE, TwitterParser),
  MENTIONS("mentions", TwitterRawUrl.MENTIONS, TwitterParser);

  private static Map<String, TwitterCategory> byIdentity = [:]

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
  private Class<TwitterParser> parser

  private TwitterCategory(String identity, TwitterRawUrl url, Class parser) {
    this.identity = identity
    this.url = url
    this.parser = parser
  }

  public String getIdentity() {
    identity
  }

  public TwitterRawUrl getUrl() {
    url
  }

  public Class<TwitterParser> getParserClass() {
    parser
  }

  public TwitterParser getParser(){
    parser.newInstance()
  }

  public CategoryD getDomain(){
    CategoryD categoryD = new CategoryD()
    categoryD.identity = identity
    categoryD
  }
}