/**
 * Autogenerated by Thrift
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 */
package everfeeds.thrift.error;

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

public class TokenExpired extends Exception implements org.apache.thrift.TBase<TokenExpired, TokenExpired._Fields>, java.io.Serializable, Cloneable {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TokenExpired");

  private static final org.apache.thrift.protocol.TField ERR_MSG_FIELD_DESC = new org.apache.thrift.protocol.TField("errMsg", org.apache.thrift.protocol.TType.STRING, (short)1);

  public String errMsg;

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ERR_MSG((short)1, "errMsg");

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
        case 1: // ERR_MSG
          return ERR_MSG;
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
    tmpMap.put(_Fields.ERR_MSG, new org.apache.thrift.meta_data.FieldMetaData("errMsg", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TokenExpired.class, metaDataMap);
  }

  public TokenExpired() {
  }

  public TokenExpired(
    String errMsg)
  {
    this();
    this.errMsg = errMsg;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TokenExpired(TokenExpired other) {
    if (other.isSetErrMsg()) {
      this.errMsg = other.errMsg;
    }
  }

  public TokenExpired deepCopy() {
    return new TokenExpired(this);
  }

  @Override
  public void clear() {
    this.errMsg = null;
  }

  public String getErrMsg() {
    return this.errMsg;
  }

  public TokenExpired setErrMsg(String errMsg) {
    this.errMsg = errMsg;
    return this;
  }

  public void unsetErrMsg() {
    this.errMsg = null;
  }

  /** Returns true if field errMsg is set (has been assigned a value) and false otherwise */
  public boolean isSetErrMsg() {
    return this.errMsg != null;
  }

  public void setErrMsgIsSet(boolean value) {
    if (!value) {
      this.errMsg = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case ERR_MSG:
      if (value == null) {
        unsetErrMsg();
      } else {
        setErrMsg((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case ERR_MSG:
      return getErrMsg();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case ERR_MSG:
      return isSetErrMsg();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof TokenExpired)
      return this.equals((TokenExpired)that);
    return false;
  }

  public boolean equals(TokenExpired that) {
    if (that == null)
      return false;

    boolean this_present_errMsg = true && this.isSetErrMsg();
    boolean that_present_errMsg = true && that.isSetErrMsg();
    if (this_present_errMsg || that_present_errMsg) {
      if (!(this_present_errMsg && that_present_errMsg))
        return false;
      if (!this.errMsg.equals(that.errMsg))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  public int compareTo(TokenExpired other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;
    TokenExpired typedOther = (TokenExpired)other;

    lastComparison = Boolean.valueOf(isSetErrMsg()).compareTo(typedOther.isSetErrMsg());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetErrMsg()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.errMsg, typedOther.errMsg);
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
        case 1: // ERR_MSG
          if (field.type == org.apache.thrift.protocol.TType.STRING) {
            this.errMsg = iprot.readString();
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
    if (this.errMsg != null) {
      oprot.writeFieldBegin(ERR_MSG_FIELD_DESC);
      oprot.writeString(this.errMsg);
      oprot.writeFieldEnd();
    }
    oprot.writeFieldStop();
    oprot.writeStructEnd();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("TokenExpired(");
    boolean first = true;

    sb.append("errMsg:");
    if (this.errMsg == null) {
      sb.append("null");
    } else {
      sb.append(this.errMsg);
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
