package everfeeds.remote.facebook

import everfeeds.mongo.CategoryD
import everfeeds.remote.twitter.TwitterParser

/**
 * @author Dmitry Kurinskiy
 * @since 14.05.11 11:31
 */
@Typed
public enum FacebookCategory {
  NEWS("news", FacebookRawUrl.NEWS, FacebookParser),
  EVENTS("events", FacebookRawUrl.EVENTS, FacebookParser),
  WALL("wall", FacebookRawUrl.WALL, FacebookParser);

  private static Map<String, FacebookCategory> byIdentity = [:]

  static {
    FacebookCategory.values().each {
      byIdentity.put it.identity, it
    }
  }

  public static FacebookCategory getByIdentity(String identity) {
    byIdentity.get(identity)
  }

  private String identity
  private everfeeds.remote.facebook.FacebookRawUrl url
  private Class<FacebookParser> parser

  private FacebookCategory(String identity, FacebookRawUrl url, Class parser) {
    this.identity = identity
    this.url = url
    this.parser = parser
  }

  public String getIdentity() {
    identity
  }

  public everfeeds.remote.facebook.FacebookRawUrl getUrl() {
    url
  }

  public Class<FacebookParser> getParserClass() {
    parser
  }

  public FacebookParser getParser(){
    parser.newInstance()
  }

  public CategoryD getDomain(){
    CategoryD categoryD = new CategoryD()
    categoryD.identity = identity
    categoryD
  }
}