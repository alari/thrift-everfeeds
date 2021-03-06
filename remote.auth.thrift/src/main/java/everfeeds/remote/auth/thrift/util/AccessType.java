/**
 * Autogenerated by Thrift
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 */
package everfeeds.remote.auth.thrift.util;


import java.util.Map;
import java.util.HashMap;
import org.apache.thrift.TEnum;

public enum AccessType implements TEnum {
  EVERNOTE(1),
  FACEBOOK(2),
  TWITTER(3),
  GMAIL(4),
  GREADER(5),
  LINKEDIN(6),
  VK(7),
  ATOMPUB(8);

  private final int value;

  private AccessType(int value) {
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
  public static AccessType findByValue(int value) { 
    switch (value) {
      case 1:
        return EVERNOTE;
      case 2:
        return FACEBOOK;
      case 3:
        return TWITTER;
      case 4:
        return GMAIL;
      case 5:
        return GREADER;
      case 6:
        return LINKEDIN;
      case 7:
        return VK;
      case 8:
        return ATOMPUB;
      default:
        return null;
    }
  }
}
