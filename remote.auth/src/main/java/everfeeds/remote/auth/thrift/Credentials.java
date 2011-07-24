/**
 * Autogenerated by Thrift
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 */
package everfeeds.remote.auth.thrift;

import java.util.*;

public class Credentials implements org.apache.thrift.TBase<Credentials, Credentials._Fields>, java.io.Serializable, Cloneable {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("Credentials");

  private static final org.apache.thrift.protocol.TField TOKEN_FIELD_DESC = new org.apache.thrift.protocol.TField("token", org.apache.thrift.protocol.TType.STRING, (short) 1);
  private static final org.apache.thrift.protocol.TField SECRET_FIELD_DESC = new org.apache.thrift.protocol.TField("secret", org.apache.thrift.protocol.TType.STRING, (short) 2);
  private static final org.apache.thrift.protocol.TField TYPE_FIELD_DESC = new org.apache.thrift.protocol.TField("type", org.apache.thrift.protocol.TType.I32, (short) 3);
  private static final org.apache.thrift.protocol.TField PARAMS_FIELD_DESC = new org.apache.thrift.protocol.TField("params", org.apache.thrift.protocol.TType.MAP, (short) 10);

  public String token;
  public String secret;
  /**
   * @see everfeeds.remote.auth.thrift.util.AccessType
   */
  public everfeeds.remote.auth.thrift.util.AccessType type;
  public Map<String, String> params;

  /**
   * The set of fields this struct contains, along with convenience methods for finding and manipulating them.
   */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    TOKEN((short) 1, "token"),
    SECRET((short) 2, "secret"),
    /**
     * @see everfeeds.remote.auth.thrift.util.AccessType
     */
    TYPE((short) 3, "type"),
    PARAMS((short) 10, "params");

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
      switch (fieldId) {
        case 1: // TOKEN
          return TOKEN;
        case 2: // SECRET
          return SECRET;
        case 3: // TYPE
          return TYPE;
        case 10: // PARAMS
          return PARAMS;
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
    tmpMap.put(_Fields.TOKEN, new org.apache.thrift.meta_data.FieldMetaData("token", org.apache.thrift.TFieldRequirementType.DEFAULT,
                                                                               new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.SECRET, new org.apache.thrift.meta_data.FieldMetaData("secret", org.apache.thrift.TFieldRequirementType.DEFAULT,
                                                                                new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.TYPE, new org.apache.thrift.meta_data.FieldMetaData("type", org.apache.thrift.TFieldRequirementType.DEFAULT,
                                                                              new org.apache.thrift.meta_data.EnumMetaData(org.apache.thrift.protocol.TType.ENUM, everfeeds.remote.auth.thrift.util.AccessType.class)));
    tmpMap.put(_Fields.PARAMS, new org.apache.thrift.meta_data.FieldMetaData("params", org.apache.thrift.TFieldRequirementType.DEFAULT,
                                                                                new org.apache.thrift.meta_data.MapMetaData(org.apache.thrift.protocol.TType.MAP,
                                                                                                                               new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING),
                                                                                                                               new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING))));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(Credentials.class, metaDataMap);
  }

  public Credentials() {
  }

  public Credentials(
                        String token,
                        String secret,
                        everfeeds.remote.auth.thrift.util.AccessType type,
                        Map<String, String> params) {
    this();
    this.token = token;
    this.secret = secret;
    this.type = type;
    this.params = params;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public Credentials(Credentials other) {
    if (other.isSetToken()) {
      this.token = other.token;
    }
    if (other.isSetSecret()) {
      this.secret = other.secret;
    }
    if (other.isSetType()) {
      this.type = other.type;
    }
    if (other.isSetParams()) {
      Map<String, String> __this__params = new HashMap<String, String>();
      for (Map.Entry<String, String> other_element : other.params.entrySet()) {

        String other_element_key = other_element.getKey();
        String other_element_value = other_element.getValue();

        String __this__params_copy_key = other_element_key;

        String __this__params_copy_value = other_element_value;

        __this__params.put(__this__params_copy_key, __this__params_copy_value);
      }
      this.params = __this__params;
    }
  }

  public Credentials deepCopy() {
    return new Credentials(this);
  }

  @Override
  public void clear() {
    this.token = null;
    this.secret = null;
    this.type = null;
    this.params = null;
  }

  public String getToken() {
    return this.token;
  }

  public Credentials setToken(String token) {
    this.token = token;
    return this;
  }

  public void unsetToken() {
    this.token = null;
  }

  /**
   * Returns true if field token is set (has been assigned a value) and false otherwise
   */
  public boolean isSetToken() {
    return this.token != null;
  }

  public void setTokenIsSet(boolean value) {
    if (!value) {
      this.token = null;
    }
  }

  public String getSecret() {
    return this.secret;
  }

  public Credentials setSecret(String secret) {
    this.secret = secret;
    return this;
  }

  public void unsetSecret() {
    this.secret = null;
  }

  /**
   * Returns true if field secret is set (has been assigned a value) and false otherwise
   */
  public boolean isSetSecret() {
    return this.secret != null;
  }

  public void setSecretIsSet(boolean value) {
    if (!value) {
      this.secret = null;
    }
  }

  /**
   * @see everfeeds.remote.auth.thrift.util.AccessType
   */
  public everfeeds.remote.auth.thrift.util.AccessType getType() {
    return this.type;
  }

  /**
   * @see everfeeds.remote.auth.thrift.util.AccessType
   */
  public Credentials setType(everfeeds.remote.auth.thrift.util.AccessType type) {
    this.type = type;
    return this;
  }

  public void unsetType() {
    this.type = null;
  }

  /**
   * Returns true if field type is set (has been assigned a value) and false otherwise
   */
  public boolean isSetType() {
    return this.type != null;
  }

  public void setTypeIsSet(boolean value) {
    if (!value) {
      this.type = null;
    }
  }

  public int getParamsSize() {
    return (this.params == null) ? 0 : this.params.size();
  }

  public void putToParams(String key, String val) {
    if (this.params == null) {
      this.params = new HashMap<String, String>();
    }
    this.params.put(key, val);
  }

  public Map<String, String> getParams() {
    return this.params;
  }

  public Credentials setParams(Map<String, String> params) {
    this.params = params;
    return this;
  }

  public void unsetParams() {
    this.params = null;
  }

  /**
   * Returns true if field params is set (has been assigned a value) and false otherwise
   */
  public boolean isSetParams() {
    return this.params != null;
  }

  public void setParamsIsSet(boolean value) {
    if (!value) {
      this.params = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
      case TOKEN:
        if (value == null) {
          unsetToken();
        } else {
          setToken((String) value);
        }
        break;

      case SECRET:
        if (value == null) {
          unsetSecret();
        } else {
          setSecret((String) value);
        }
        break;

      case TYPE:
        if (value == null) {
          unsetType();
        } else {
          setType((everfeeds.remote.auth.thrift.util.AccessType) value);
        }
        break;

      case PARAMS:
        if (value == null) {
          unsetParams();
        } else {
          setParams((Map<String, String>) value);
        }
        break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
      case TOKEN:
        return getToken();

      case SECRET:
        return getSecret();

      case TYPE:
        return getType();

      case PARAMS:
        return getParams();

    }
    throw new IllegalStateException();
  }

  /**
   * Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise
   */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
      case TOKEN:
        return isSetToken();
      case SECRET:
        return isSetSecret();
      case TYPE:
        return isSetType();
      case PARAMS:
        return isSetParams();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof Credentials)
      return this.equals((Credentials) that);
    return false;
  }

  public boolean equals(Credentials that) {
    if (that == null)
      return false;

    boolean this_present_token = true && this.isSetToken();
    boolean that_present_token = true && that.isSetToken();
    if (this_present_token || that_present_token) {
      if (!(this_present_token && that_present_token))
        return false;
      if (!this.token.equals(that.token))
        return false;
    }

    boolean this_present_secret = true && this.isSetSecret();
    boolean that_present_secret = true && that.isSetSecret();
    if (this_present_secret || that_present_secret) {
      if (!(this_present_secret && that_present_secret))
        return false;
      if (!this.secret.equals(that.secret))
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

    boolean this_present_params = true && this.isSetParams();
    boolean that_present_params = true && that.isSetParams();
    if (this_present_params || that_present_params) {
      if (!(this_present_params && that_present_params))
        return false;
      if (!this.params.equals(that.params))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  public int compareTo(Credentials other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;
    Credentials typedOther = (Credentials) other;

    lastComparison = Boolean.valueOf(isSetToken()).compareTo(typedOther.isSetToken());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetToken()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.token, typedOther.token);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetSecret()).compareTo(typedOther.isSetSecret());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSecret()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.secret, typedOther.secret);
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
    lastComparison = Boolean.valueOf(isSetParams()).compareTo(typedOther.isSetParams());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetParams()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.params, typedOther.params);
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
    while (true) {
      field = iprot.readFieldBegin();
      if (field.type == org.apache.thrift.protocol.TType.STOP) {
        break;
      }
      switch (field.id) {
        case 1: // TOKEN
          if (field.type == org.apache.thrift.protocol.TType.STRING) {
            this.token = iprot.readString();
          } else {
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 2: // SECRET
          if (field.type == org.apache.thrift.protocol.TType.STRING) {
            this.secret = iprot.readString();
          } else {
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 3: // TYPE
          if (field.type == org.apache.thrift.protocol.TType.I32) {
            this.type = everfeeds.remote.auth.thrift.util.AccessType.findByValue(iprot.readI32());
          } else {
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 10: // PARAMS
          if (field.type == org.apache.thrift.protocol.TType.MAP) {
            {
              org.apache.thrift.protocol.TMap _map0 = iprot.readMapBegin();
              this.params = new HashMap<String, String>(2 * _map0.size);
              for (int _i1 = 0; _i1 < _map0.size; ++_i1) {
                String _key2;
                String _val3;
                _key2 = iprot.readString();
                _val3 = iprot.readString();
                this.params.put(_key2, _val3);
              }
              iprot.readMapEnd();
            }
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
    if (this.token != null) {
      oprot.writeFieldBegin(TOKEN_FIELD_DESC);
      oprot.writeString(this.token);
      oprot.writeFieldEnd();
    }
    if (this.secret != null) {
      oprot.writeFieldBegin(SECRET_FIELD_DESC);
      oprot.writeString(this.secret);
      oprot.writeFieldEnd();
    }
    if (this.type != null) {
      oprot.writeFieldBegin(TYPE_FIELD_DESC);
      oprot.writeI32(this.type.getValue());
      oprot.writeFieldEnd();
    }
    if (this.params != null) {
      oprot.writeFieldBegin(PARAMS_FIELD_DESC);
      {
        oprot.writeMapBegin(new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.STRING, org.apache.thrift.protocol.TType.STRING, this.params.size()));
        for (Map.Entry<String, String> _iter4 : this.params.entrySet()) {
          oprot.writeString(_iter4.getKey());
          oprot.writeString(_iter4.getValue());
        }
        oprot.writeMapEnd();
      }
      oprot.writeFieldEnd();
    }
    oprot.writeFieldStop();
    oprot.writeStructEnd();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("Credentials(");
    boolean first = true;

    sb.append("token:");
    if (this.token == null) {
      sb.append("null");
    } else {
      sb.append(this.token);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("secret:");
    if (this.secret == null) {
      sb.append("null");
    } else {
      sb.append(this.secret);
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
    sb.append("params:");
    if (this.params == null) {
      sb.append("null");
    } else {
      sb.append(this.params);
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

