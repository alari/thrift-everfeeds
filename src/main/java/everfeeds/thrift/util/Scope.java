package everfeeds.thrift.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Dmitry Kurinskiy
 * @since 12.05.11 18:26
 */
public enum Scope {


  INFO("accountInfo"),
  INFO_WRITE("writeAccountInfo"),
  INFO_ORDER("writeAccountOrder"),
  FEED_READ("readFeeds"),
  FEED_WRITE("writeFeeds");

  static private Map<String, Scope> byString = new HashMap<String, Scope>();

  static {
    for(Scope s : Scope.values()) {
      byString.put(s.value, s);
    }
  }

  static public Scope get(String value) {
    return byString.get(value);
  }

  private String value;

  Scope(String value){
    this.value = value;
  }

  public String toString() {
    return value;
  }

}
