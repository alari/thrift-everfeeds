/**
 * Autogenerated by Thrift
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 */
package everfeeds.remote.auth.thrift.util;


import java.util.Map;
import java.util.HashMap;
import org.apache.thrift.TEnum;

public enum AuthMethod implements TEnum {
  OAUTH2(0),
  BASIC(1);

  private final int value;

  private AuthMethod(int value) {
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
  public static AuthMethod findByValue(int value) { 
    switch (value) {
      case 0:
        return OAUTH2;
      case 1:
        return BASIC;
      default:
        return null;
    }
  }
}
