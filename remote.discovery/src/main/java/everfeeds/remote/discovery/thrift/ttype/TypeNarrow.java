/**
 * Autogenerated by Thrift
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 */
package everfeeds.remote.discovery.thrift.ttype;


import java.util.Map;
import java.util.HashMap;
import org.apache.thrift.TEnum;

public enum TypeNarrow implements TEnum {
  USER(1),
  FOLDER(2),
  FEED(3),
  ALBUM(4),
  COMMUNITY(5),
  PUBLIC_PAGE(6),
  ENTRY(20),
  SEARCH(21),
  SEARCH_SAVED(22),
  COMMENT(23),
  RELATION_FRIEND(30),
  RELATION_MEMBER(31),
  RELATION_ADMIN(32),
  RELATION_PERMISSION(33),
  FILE_VIDEO(50),
  FILE_AUDIO(51),
  FILE_IMAGE(52),
  FILE_DOC(53),
  UNKNOWN(255);

  private final int value;

  private TypeNarrow(int value) {
    this.value = value;
  }

  /**
   * Get the integer value of this enum value, as defined in the Thrift IDL.
   */
  public int getValue() {
    return value;
  }

  /**
   * Find a the enum type by its integer value, as defined in the Thrift IDL.
   * @return null if the value is not found.
   */
  public static TypeNarrow findByValue(int value) { 
    switch (value) {
      case 1:
        return USER;
      case 2:
        return FOLDER;
      case 3:
        return FEED;
      case 4:
        return ALBUM;
      case 5:
        return COMMUNITY;
      case 6:
        return PUBLIC_PAGE;
      case 20:
        return ENTRY;
      case 21:
        return SEARCH;
      case 22:
        return SEARCH_SAVED;
      case 23:
        return COMMENT;
      case 30:
        return RELATION_FRIEND;
      case 31:
        return RELATION_MEMBER;
      case 32:
        return RELATION_ADMIN;
      case 33:
        return RELATION_PERMISSION;
      case 50:
        return FILE_VIDEO;
      case 51:
        return FILE_AUDIO;
      case 52:
        return FILE_IMAGE;
      case 53:
        return FILE_DOC;
      case 255:
        return UNKNOWN;
      default:
        return null;
    }
  }
}
