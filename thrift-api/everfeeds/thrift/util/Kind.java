package everfeeds.thrift.util;

import everfeeds.thrift.ttype.EntryKind;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Dmitry Kurinskiy
 * @since 13.05.11 17:48
 */
public enum Kind {
  STATUS(EntryKind.STATUS, "status"),
  DM(EntryKind.DM, "directm"),
  NOTE(EntryKind.NOTE, "note"),
  ATOM(EntryKind.ATOM, "atom"),
  EMAIL(EntryKind.EMAIL, "email"),
  ALBUM(EntryKind.ALBUM, "album"),
  APPLICATION(EntryKind.APPLICATION, "application"),
  CHECKIN(EntryKind.CHECKIN, "checkin"),
  LINK(EntryKind.LINK, "link"),
  MUSIC(EntryKind.MUSIC, "music"),
  NEWS(EntryKind.NEWS, "news"),
  PHOTO(EntryKind.PHOTO, "photo"),
  VIDEO(EntryKind.VIDEO, "video"),
  WALL(EntryKind.WALL, "wall");

  static private Map<String,Kind> byName = new HashMap<String,Kind>();
  static private Map<EntryKind,Kind> byThrift = new HashMap<EntryKind,Kind>();

  static public Kind getByName(String name){
    return byName.get(name);
  }

  static public Kind getByThrift(EntryKind thrift){
    return byThrift.get(thrift);
  }

  static {
    for(Kind k : Kind.values()) {
      byName.put(k.name, k);
      byThrift.put(k.thriftKind, k);
    }
  }

  private EntryKind thriftKind;
  private String name;

  Kind(EntryKind thriftKind, String name){
    this.thriftKind = thriftKind;
    this.name = name;
  }

  public String toString(){
    return this.name;
  }

  public EntryKind toThrift(){
    return thriftKind;
  }
}
