/**
 * Autogenerated by Thrift
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 */
package everfeeds.remote.auth.thrift.util;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthSystem implements org.apache.thrift.TBase<AuthSystem, AuthSystem._Fields>, java.io.Serializable, Cloneable {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("AuthSystem");

  private static final org.apache.thrift.protocol.TField NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("name", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField METHOD_FIELD_DESC = new org.apache.thrift.protocol.TField("method", org.apache.thrift.protocol.TType.I32, (short)2);
  private static final org.apache.thrift.protocol.TField TYPE_FIELD_DESC = new org.apache.thrift.protocol.TField("type", org.apache.thrift.protocol.TType.I32, (short)3);
  private static final org.apache.thrift.protocol.TField USER_DOMAIN_FIELD_DESC = new org.apache.thrift.protocol.TField("userDomain", org.apache.thrift.protocol.TType.STRING, (short)10);

  public String name;
  /**
   * 
   * @see everfeeds.remote.auth.thrift.util.AuthMethod
   */
  public AuthMethod method;
  /**
   * 
   * @see everfeeds.remote.auth.thrift.util.AccessType
   */
  public AccessType type;
  public String userDomain;

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    NAME((short)1, "name"),
    /**
     * 
     * @see everfeeds.remote.auth.thrift.util.AuthMethod
     */
    METHOD((short)2, "method"),
    /**
     * 
     * @see everfeeds.remote.auth.thrift.util.AccessType
     */
    TYPE((short)3, "type"),
    USER_DOMAIN((short)10, "userDomain");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // NAME
          return NAME;
        case 2: // METHOD
          return METHOD;
        case 3: // TYPE
          return TYPE;
        case 10: // USER_DOMAIN
          return USER_DOMAIN;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments

  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.NAME, new org.apache.thrift.meta_data.FieldMetaData("name", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.METHOD, new org.apache.thrift.meta_data.FieldMetaData("method", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.EnumMetaData(org.apache.thrift.protocol.TType.ENUM, AuthMethod.class)));
    tmpMap.put(_Fields.TYPE, new org.apache.thrift.meta_data.FieldMetaData("type", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.EnumMetaData(org.apache.thrift.protocol.TType.ENUM, AccessType.class)));
    tmpMap.put(_Fields.USER_DOMAIN, new org.apache.thrift.meta_data.FieldMetaData("userDomain", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(AuthSystem.class, metaDataMap);
  }

  public AuthSystem() {
  }

  public AuthSystem(
    String name,
    AuthMethod method,
    AccessType type,
    String userDomain)
  {
    this();
    this.name = name;
    this.method = method;
    this.type = type;
    this.userDomain = userDomain;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public AuthSystem(AuthSystem other) {
    if (other.isSetName()) {
      this.name = other.name;
    }
    if (other.isSetMethod()) {
      this.method = other.method;
    }
    if (other.isSetType()) {
      this.type = other.type;
    }
    if (other.isSetUserDomain()) {
      this.userDomain = other.userDomain;
    }
  }

  public AuthSystem deepCopy() {
    return new AuthSystem(this);
  }

  @Override
  public void clear() {
    this.name = null;
    this.method = null;
    this.type = null;
    this.userDomain = null;
  }

  public String getName() {
    return this.name;
  }

  public AuthSystem setName(String name) {
    this.name = name;
    return this;
  }

  public void unsetName() {
    this.name = null;
  }

  /** Returns true if field name is set (has been assigned a value) and false otherwise */
  public boolean isSetName() {
    return this.name != null;
  }

  public void setNameIsSet(boolean value) {
    if (!value) {
      this.name = null;
    }
  }

  /**
   * 
   * @see everfeeds.remote.auth.thrift.util.AuthMethod
   */
  public AuthMethod getMethod() {
    return this.method;
  }

  /**
   * 
   * @see everfeeds.remote.auth.thrift.util.AuthMethod
   */
  public AuthSystem setMethod(AuthMethod method) {
    this.method = method;
    return this;
  }

  public void unsetMethod() {
    this.method = null;
  }

  /** Returns true if field method is set (has been assigned a value) and false otherwise */
  public boolean isSetMethod() {
    return this.method != null;
  }

  public void setMethodIsSet(boolean value) {
    if (!value) {
      this.method = null;
    }
  }

  /**
   * 
   * @see everfeeds.remote.auth.thrift.util.AccessType
   */
  public AccessType getType() {
    return this.type;
  }

  /**
   * 
   * @see everfeeds.remote.auth.thrift.util.AccessType
   */
  public AuthSystem setType(AccessType type) {
    this.type = type;
    return this;
  }

  public void unsetType() {
    this.type = null;
  }

  /** Returns true if field type is set (has been assigned a value) and false otherwise */
  public boolean isSetType() {
    return this.type != null;
  }

  public void setTypeIsSet(boolean value) {
    if (!value) {
      this.type = null;
    }
  }

  public String getUserDomain() {
    return this.userDomain;
  }

  public AuthSystem setUserDomain(String userDomain) {
    this.userDomain = userDomain;
    return this;
  }

  public void unsetUserDomain() {
    this.userDomain = null;
  }

  /** Returns true if field userDomain is set (has been assigned a value) and false otherwise */
  public boolean isSetUserDomain() {
    return this.userDomain != null;
  }

  public void setUserDomainIsSet(boolean value) {
    if (!value) {
      this.userDomain = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case NAME:
      if (value == null) {
        unsetName();
      } else {
        setName((String)value);
      }
      break;

    case METHOD:
      if (value == null) {
        unsetMethod();
      } else {
        setMethod((AuthMethod)value);
      }
      break;

    case TYPE:
      if (value == null) {
        unsetType();
      } else {
        setType((AccessType)value);
      }
      break;

    case USER_DOMAIN:
      if (value == null) {
        unsetUserDomain();
      } else {
        setUserDomain((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case NAME:
      return getName();

    case METHOD:
      return getMethod();

    case TYPE:
      return getType();

    case USER_DOMAIN:
      return getUserDomain();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case NAME:
      return isSetName();
    case METHOD:
      return isSetMethod();
    case TYPE:
      return isSetType();
    case USER_DOMAIN:
      return isSetUserDomain();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof AuthSystem)
      return this.equals((AuthSystem)that);
    return false;
  }

  public boolean equals(AuthSystem that) {
    if (that == null)
      return false;

    boolean this_present_name = true && this.isSetName();
    boolean that_present_name = true && that.isSetName();
    if (this_present_name || that_present_name) {
      if (!(this_present_name && that_present_name))
        return false;
      if (!this.name.equals(that.name))
        return false;
    }

    boolean this_present_method = true && this.isSetMethod();
    boolean that_present_method = true && that.isSetMethod();
    if (this_present_method || that_present_method) {
      if (!(this_present_method && that_present_method))
        return false;
      if (!this.method.equals(that.method))
        return false;
    }

    boolean this_present_type = true && this.isSetType();
    boolean that_present_type = true && that.isSetType();
    if (this_present_type || that_present_type) {
      if (!(this_present_type && that_present_type))
        return false;
      if (!this.type.equals(that.type))
        return false;
    }

    boolean this_present_userDomain = true && this.isSetUserDomain();
    boolean that_present_userDomain = true && that.isSetUserDomain();
    if (this_present_userDomain || that_present_userDomain) {
      if (!(this_present_userDomain && that_present_userDomain))
        return false;
      if (!this.userDomain.equals(that.userDomain))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  public int compareTo(AuthSystem other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;
    AuthSystem typedOther = (AuthSystem)other;

    lastComparison = Boolean.valueOf(isSetName()).compareTo(typedOther.isSetName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.name, typedOther.name);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetMethod()).compareTo(typedOther.isSetMethod());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetMethod()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.method, typedOther.method);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetType()).compareTo(typedOther.isSetType());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetType()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.type, typedOther.type);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetUserDomain()).compareTo(typedOther.isSetUserDomain());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetUserDomain()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.userDomain, typedOther.userDomain);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    org.apache.thrift.protocol.TField field;
    iprot.readStructBegin();
    while (true)
    {
      field = iprot.readFieldBegin();
      if (field.type == org.apache.thrift.protocol.TType.STOP) { 
        break;
      }
      switch (field.id) {
        case 1: // NAME
          if (field.type == org.apache.thrift.protocol.TType.STRING) {
            this.name = iprot.readString();
          } else { 
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 2: // METHOD
          if (field.type == org.apache.thrift.protocol.TType.I32) {
            this.method = AuthMethod.findByValue(iprot.readI32());
          } else { 
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 3: // TYPE
          if (field.type == org.apache.thrift.protocol.TType.I32) {
            this.type = AccessType.findByValue(iprot.readI32());
          } else { 
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 10: // USER_DOMAIN
          if (field.type == org.apache.thrift.protocol.TType.STRING) {
            this.userDomain = iprot.readString();
          } else { 
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
          }
          break;
        default:
          org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
      }
      iprot.readFieldEnd();
    }
    iprot.readStructEnd();

    // check for required fields of primitive type, which can't be checked in the validate method
    validate();
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    validate();

    oprot.writeStructBegin(STRUCT_DESC);
    if (this.name != null) {
      oprot.writeFieldBegin(NAME_FIELD_DESC);
      oprot.writeString(this.name);
      oprot.writeFieldEnd();
    }
    if (this.method != null) {
      oprot.writeFieldBegin(METHOD_FIELD_DESC);
      oprot.writeI32(this.method.getValue());
      oprot.writeFieldEnd();
    }
    if (this.type != null) {
      oprot.writeFieldBegin(TYPE_FIELD_DESC);
      oprot.writeI32(this.type.getValue());
      oprot.writeFieldEnd();
    }
    if (this.userDomain != null) {
      oprot.writeFieldBegin(USER_DOMAIN_FIELD_DESC);
      oprot.writeString(this.userDomain);
      oprot.writeFieldEnd();
    }
    oprot.writeFieldStop();
    oprot.writeStructEnd();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("AuthSystem(");
    boolean first = true;

    sb.append("name:");
    if (this.name == null) {
      sb.append("null");
    } else {
      sb.append(this.name);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("method:");
    if (this.method == null) {
      sb.append("null");
    } else {
      sb.append(this.method);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("type:");
    if (this.type == null) {
      sb.append("null");
    } else {
      sb.append(this.type);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("userDomain:");
    if (this.userDomain == null) {
      sb.append("null");
    } else {
      sb.append(this.userDomain);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

}

