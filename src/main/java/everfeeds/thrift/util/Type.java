package everfeeds.thrift.util;

import everfeeds.thrift.ttype.AccessType;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Dmitry Kurinskiy
 * @since 13.05.11 17:40
 */
public enum Type {
  FACEBOOK(AccessType.FACEBOOK, "facebook"),
  EVERNOTE(AccessType.EVERNOTE, "evernote"),
  TWITTER(AccessType.TWITTER, "twitter"),
  GMAIL(AccessType.GMAIL, "gmail"),
  GREADER(AccessType.GREADER, "greader"),
  LINKEDIN(AccessType.LINKEDIN, "linkedin"),
  VKONTAKTE(AccessType.VKONTAKTE, "vkontakte"),
  METAWEBLOG(AccessType.METAWEBLOG, "metaweblog");

  static private Map<String,Type> byName = new HashMap<String,Type>();
  static private Map<AccessType,Type> byThrift = new HashMap<AccessType,Type>();

  static public Type getByName(String name){
    return byName.get(name);
  }

  static public Type getByThrift(AccessType thrift){
    return byThrift.get(thrift);
  }

  static {
    for(Type t : Type.values()) {
      byName.put(t.name, t);
      byThrift.put(t.thriftType, t);
    }
  }

  private AccessType thriftType;
  private String name;

  Type(AccessType thriftType, String name){
    this.thriftType = thriftType;
    this.name = name;
  }

  public String toString(){
    return this.name;
  }

  public AccessType toThrift(){
    return thriftType;
  }
}
