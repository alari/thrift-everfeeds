/**
 * Autogenerated by Thrift
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 */
package everfeeds.thrift.domain;

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

public class Access implements org.apache.thrift.TBase<Access, Access._Fields>, java.io.Serializable, Cloneable {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("Access");

  private static final org.apache.thrift.protocol.TField ID_FIELD_DESC = new org.apache.thrift.protocol.TField("id", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField IDENTITY_FIELD_DESC = new org.apache.thrift.protocol.TField("identity", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField ACCOUNT_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("accountId", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField TYPE_FIELD_DESC = new org.apache.thrift.protocol.TField("type", org.apache.thrift.protocol.TType.I32, (short)4);
  private static final org.apache.thrift.protocol.TField EXPIRED_FIELD_DESC = new org.apache.thrift.protocol.TField("expired", org.apache.thrift.protocol.TType.BOOL, (short)5);
  private static final org.apache.thrift.protocol.TField TITLE_FIELD_DESC = new org.apache.thrift.protocol.TField("title", org.apache.thrift.protocol.TType.STRING, (short)10);
  private static final org.apache.thrift.protocol.TField SCREEN_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("screenName", org.apache.thrift.protocol.TType.STRING, (short)11);
  private static final org.apache.thrift.protocol.TField PARAMS_FIELD_DESC = new org.apache.thrift.protocol.TField("params", org.apache.thrift.protocol.TType.LIST, (short)20);

  public String id;
  public String identity;
  public String accountId;
  /**
   * 
   * @see everfeeds.thrift.ttype.AccessType
   */
  public everfeeds.thrift.ttype.AccessType type;
  public boolean expired;
  public String title;
  public String screenName;
  public List<String> params;

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ID((short)1, "id"),
    IDENTITY((short)2, "identity"),
    ACCOUNT_ID((short)3, "accountId"),
    /**
     * 
     * @see everfeeds.thrift.ttype.AccessType
     */
    TYPE((short)4, "type"),
    EXPIRED((short)5, "expired"),
    TITLE((short)10, "title"),
    SCREEN_NAME((short)11, "screenName"),
    PARAMS((short)20, "params");

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
        case 1: // ID
          return ID;
        case 2: // IDENTITY
          return IDENTITY;
        case 3: // ACCOUNT_ID
          return ACCOUNT_ID;
        case 4: // TYPE
          return TYPE;
        case 5: // EXPIRED
          return EXPIRED;
        case 10: // TITLE
          return TITLE;
        case 11: // SCREEN_NAME
          return SCREEN_NAME;
        case 20: // PARAMS
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
  private static final int __EXPIRED_ISSET_ID = 0;
  private BitSet __isset_bit_vector = new BitSet(1);

  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.ID, new org.apache.thrift.meta_data.FieldMetaData("id", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING        , "Id")));
    tmpMap.put(_Fields.IDENTITY, new org.apache.thrift.meta_data.FieldMetaData("identity", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING        , "String")));
    tmpMap.put(_Fields.ACCOUNT_ID, new org.apache.thrift.meta_data.FieldMetaData("accountId", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING        , "Id")));
    tmpMap.put(_Fields.TYPE, new org.apache.thrift.meta_data.FieldMetaData("type", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.EnumMetaData(org.apache.thrift.protocol.TType.ENUM, everfeeds.thrift.ttype.AccessType.class)));
    tmpMap.put(_Fields.EXPIRED, new org.apache.thrift.meta_data.FieldMetaData("expired", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    tmpMap.put(_Fields.TITLE, new org.apache.thrift.meta_data.FieldMetaData("title", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING        , "String")));
    tmpMap.put(_Fields.SCREEN_NAME, new org.apache.thrift.meta_data.FieldMetaData("screenName", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING        , "String")));
    tmpMap.put(_Fields.PARAMS, new org.apache.thrift.meta_data.FieldMetaData("params", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING))));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(Access.class, metaDataMap);
  }

  public Access() {
  }

  public Access(
    String id,
    String identity,
    String accountId,
    everfeeds.thrift.ttype.AccessType type,
    boolean expired,
    String title,
    String screenName,
    List<String> params)
  {
    this();
    this.id = id;
    this.identity = identity;
    this.accountId = accountId;
    this.type = type;
    this.expired = expired;
    setExpiredIsSet(true);
    this.title = title;
    this.screenName = screenName;
    this.params = params;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public Access(Access other) {
    __isset_bit_vector.clear();
    __isset_bit_vector.or(other.__isset_bit_vector);
    if (other.isSetId()) {
      this.id = other.id;
    }
    if (other.isSetIdentity()) {
      this.identity = other.identity;
    }
    if (other.isSetAccountId()) {
      this.accountId = other.accountId;
    }
    if (other.isSetType()) {
      this.type = other.type;
    }
    this.expired = other.expired;
    if (other.isSetTitle()) {
      this.title = other.title;
    }
    if (other.isSetScreenName()) {
      this.screenName = other.screenName;
    }
    if (other.isSetParams()) {
      List<String> __this__params = new ArrayList<String>();
      for (String other_element : other.params) {
        __this__params.add(other_element);
      }
      this.params = __this__params;
    }
  }

  public Access deepCopy() {
    return new Access(this);
  }

  @Override
  public void clear() {
    this.id = null;
    this.identity = null;
    this.accountId = null;
    this.type = null;
    setExpiredIsSet(false);
    this.expired = false;
    this.title = null;
    this.screenName = null;
    this.params = null;
  }

  public String getId() {
    return this.id;
  }

  public Access setId(String id) {
    this.id = id;
    return this;
  }

  public void unsetId() {
    this.id = null;
  }

  /** Returns true if field id is set (has been assigned a value) and false otherwise */
  public boolean isSetId() {
    return this.id != null;
  }

  public void setIdIsSet(boolean value) {
    if (!value) {
      this.id = null;
    }
  }

  public String getIdentity() {
    return this.identity;
  }

  public Access setIdentity(String identity) {
    this.identity = identity;
    return this;
  }

  public void unsetIdentity() {
    this.identity = null;
  }

  /** Returns true if field identity is set (has been assigned a value) and false otherwise */
  public boolean isSetIdentity() {
    return this.identity != null;
  }

  public void setIdentityIsSet(boolean value) {
    if (!value) {
      this.identity = null;
    }
  }

  public String getAccountId() {
    return this.accountId;
  }

  public Access setAccountId(String accountId) {
    this.accountId = accountId;
    return this;
  }

  public void unsetAccountId() {
    this.accountId = null;
  }

  /** Returns true if field accountId is set (has been assigned a value) and false otherwise */
  public boolean isSetAccountId() {
    return this.accountId != null;
  }

  public void setAccountIdIsSet(boolean value) {
    if (!value) {
      this.accountId = null;
    }
  }

  /**
   * 
   * @see everfeeds.thrift.ttype.AccessType
   */
  public everfeeds.thrift.ttype.AccessType getType() {
    return this.type;
  }

  /**
   * 
   * @see everfeeds.thrift.ttype.AccessType
   */
  public Access setType(everfeeds.thrift.ttype.AccessType type) {
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

  public boolean isExpired() {
    return this.expired;
  }

  public Access setExpired(boolean expired) {
    this.expired = expired;
    setExpiredIsSet(true);
    return this;
  }

  public void unsetExpired() {
    __isset_bit_vector.clear(__EXPIRED_ISSET_ID);
  }

  /** Returns true if field expired is set (has been assigned a value) and false otherwise */
  public boolean isSetExpired() {
    return __isset_bit_vector.get(__EXPIRED_ISSET_ID);
  }

  public void setExpiredIsSet(boolean value) {
    __isset_bit_vector.set(__EXPIRED_ISSET_ID, value);
  }

  public String getTitle() {
    return this.title;
  }

  public Access setTitle(String title) {
    this.title = title;
    return this;
  }

  public void unsetTitle() {
    this.title = null;
  }

  /** Returns true if field title is set (has been assigned a value) and false otherwise */
  public boolean isSetTitle() {
    return this.title != null;
  }

  public void setTitleIsSet(boolean value) {
    if (!value) {
      this.title = null;
    }
  }

  public String getScreenName() {
    return this.screenName;
  }

  public Access setScreenName(String screenName) {
    this.screenName = screenName;
    return this;
  }

  public void unsetScreenName() {
    this.screenName = null;
  }

  /** Returns true if field screenName is set (has been assigned a value) and false otherwise */
  public boolean isSetScreenName() {
    return this.screenName != null;
  }

  public void setScreenNameIsSet(boolean value) {
    if (!value) {
      this.screenName = null;
    }
  }

  public int getParamsSize() {
    return (this.params == null) ? 0 : this.params.size();
  }

  public java.util.Iterator<String> getParamsIterator() {
    return (this.params == null) ? null : this.params.iterator();
  }

  public void addToParams(String elem) {
    if (this.params == null) {
      this.params = new ArrayList<String>();
    }
    this.params.add(elem);
  }

  public List<String> getParams() {
    return this.params;
  }

  public Access setParams(List<String> params) {
    this.params = params;
    return this;
  }

  public void unsetParams() {
    this.params = null;
  }

  /** Returns true if field params is set (has been assigned a value) and false otherwise */
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
    case ID:
      if (value == null) {
        unsetId();
      } else {
        setId((String)value);
      }
      break;

    case IDENTITY:
      if (value == null) {
        unsetIdentity();
      } else {
        setIdentity((String)value);
      }
      break;

    case ACCOUNT_ID:
      if (value == null) {
        unsetAccountId();
      } else {
        setAccountId((String)value);
      }
      break;

    case TYPE:
      if (value == null) {
        unsetType();
      } else {
        setType((everfeeds.thrift.ttype.AccessType)value);
      }
      break;

    case EXPIRED:
      if (value == null) {
        unsetExpired();
      } else {
        setExpired((Boolean)value);
      }
      break;

    case TITLE:
      if (value == null) {
        unsetTitle();
      } else {
        setTitle((String)value);
      }
      break;

    case SCREEN_NAME:
      if (value == null) {
        unsetScreenName();
      } else {
        setScreenName((String)value);
      }
      break;

    case PARAMS:
      if (value == null) {
        unsetParams();
      } else {
        setParams((List<String>)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case ID:
      return getId();

    case IDENTITY:
      return getIdentity();

    case ACCOUNT_ID:
      return getAccountId();

    case TYPE:
      return getType();

    case EXPIRED:
      return new Boolean(isExpired());

    case TITLE:
      return getTitle();

    case SCREEN_NAME:
      return getScreenName();

    case PARAMS:
      return getParams();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case ID:
      return isSetId();
    case IDENTITY:
      return isSetIdentity();
    case ACCOUNT_ID:
      return isSetAccountId();
    case TYPE:
      return isSetType();
    case EXPIRED:
      return isSetExpired();
    case TITLE:
      return isSetTitle();
    case SCREEN_NAME:
      return isSetScreenName();
    case PARAMS:
      return isSetParams();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof Access)
      return this.equals((Access)that);
    return false;
  }

  public boolean equals(Access that) {
    if (that == null)
      return false;

    boolean this_present_id = true && this.isSetId();
    boolean that_present_id = true && that.isSetId();
    if (this_present_id || that_present_id) {
      if (!(this_present_id && that_present_id))
        return false;
      if (!this.id.equals(that.id))
        return false;
    }

    boolean this_present_identity = true && this.isSetIdentity();
    boolean that_present_identity = true && that.isSetIdentity();
    if (this_present_identity || that_present_identity) {
      if (!(this_present_identity && that_present_identity))
        return false;
      if (!this.identity.equals(that.identity))
        return false;
    }

    boolean this_present_accountId = true && this.isSetAccountId();
    boolean that_present_accountId = true && that.isSetAccountId();
    if (this_present_accountId || that_present_accountId) {
      if (!(this_present_accountId && that_present_accountId))
        return false;
      if (!this.accountId.equals(that.accountId))
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

    boolean this_present_expired = true;
    boolean that_present_expired = true;
    if (this_present_expired || that_present_expired) {
      if (!(this_present_expired && that_present_expired))
        return false;
      if (this.expired != that.expired)
        return false;
    }

    boolean this_present_title = true && this.isSetTitle();
    boolean that_present_title = true && that.isSetTitle();
    if (this_present_title || that_present_title) {
      if (!(this_present_title && that_present_title))
        return false;
      if (!this.title.equals(that.title))
        return false;
    }

    boolean this_present_screenName = true && this.isSetScreenName();
    boolean that_present_screenName = true && that.isSetScreenName();
    if (this_present_screenName || that_present_screenName) {
      if (!(this_present_screenName && that_present_screenName))
        return false;
      if (!this.screenName.equals(that.screenName))
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

  public int compareTo(Access other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;
    Access typedOther = (Access)other;

    lastComparison = Boolean.valueOf(isSetId()).compareTo(typedOther.isSetId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.id, typedOther.id);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetIdentity()).compareTo(typedOther.isSetIdentity());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetIdentity()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.identity, typedOther.identity);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetAccountId()).compareTo(typedOther.isSetAccountId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetAccountId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.accountId, typedOther.accountId);
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
    lastComparison = Boolean.valueOf(isSetExpired()).compareTo(typedOther.isSetExpired());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetExpired()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.expired, typedOther.expired);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetTitle()).compareTo(typedOther.isSetTitle());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTitle()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.title, typedOther.title);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetScreenName()).compareTo(typedOther.isSetScreenName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetScreenName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.screenName, typedOther.screenName);
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
    while (true)
    {
      field = iprot.readFieldBegin();
      if (field.type == org.apache.thrift.protocol.TType.STOP) { 
        break;
      }
      switch (field.id) {
        case 1: // ID
          if (field.type == org.apache.thrift.protocol.TType.STRING) {
            this.id = iprot.readString();
          } else { 
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 2: // IDENTITY
          if (field.type == org.apache.thrift.protocol.TType.STRING) {
            this.identity = iprot.readString();
          } else { 
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 3: // ACCOUNT_ID
          if (field.type == org.apache.thrift.protocol.TType.STRING) {
            this.accountId = iprot.readString();
          } else { 
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 4: // TYPE
          if (field.type == org.apache.thrift.protocol.TType.I32) {
            this.type = everfeeds.thrift.ttype.AccessType.findByValue(iprot.readI32());
          } else { 
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 5: // EXPIRED
          if (field.type == org.apache.thrift.protocol.TType.BOOL) {
            this.expired = iprot.readBool();
            setExpiredIsSet(true);
          } else { 
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 10: // TITLE
          if (field.type == org.apache.thrift.protocol.TType.STRING) {
            this.title = iprot.readString();
          } else { 
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 11: // SCREEN_NAME
          if (field.type == org.apache.thrift.protocol.TType.STRING) {
            this.screenName = iprot.readString();
          } else { 
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 20: // PARAMS
          if (field.type == org.apache.thrift.protocol.TType.LIST) {
            {
              org.apache.thrift.protocol.TList _list4 = iprot.readListBegin();
              this.params = new ArrayList<String>(_list4.size);
              for (int _i5 = 0; _i5 < _list4.size; ++_i5)
              {
                String _elem6;
                _elem6 = iprot.readString();
                this.params.add(_elem6);
              }
              iprot.readListEnd();
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
    if (this.id != null) {
      oprot.writeFieldBegin(ID_FIELD_DESC);
      oprot.writeString(this.id);
      oprot.writeFieldEnd();
    }
    if (this.identity != null) {
      oprot.writeFieldBegin(IDENTITY_FIELD_DESC);
      oprot.writeString(this.identity);
      oprot.writeFieldEnd();
    }
    if (this.accountId != null) {
      oprot.writeFieldBegin(ACCOUNT_ID_FIELD_DESC);
      oprot.writeString(this.accountId);
      oprot.writeFieldEnd();
    }
    if (this.type != null) {
      oprot.writeFieldBegin(TYPE_FIELD_DESC);
      oprot.writeI32(this.type.getValue());
      oprot.writeFieldEnd();
    }
    oprot.writeFieldBegin(EXPIRED_FIELD_DESC);
    oprot.writeBool(this.expired);
    oprot.writeFieldEnd();
    if (this.title != null) {
      oprot.writeFieldBegin(TITLE_FIELD_DESC);
      oprot.writeString(this.title);
      oprot.writeFieldEnd();
    }
    if (this.screenName != null) {
      oprot.writeFieldBegin(SCREEN_NAME_FIELD_DESC);
      oprot.writeString(this.screenName);
      oprot.writeFieldEnd();
    }
    if (this.params != null) {
      oprot.writeFieldBegin(PARAMS_FIELD_DESC);
      {
        oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, this.params.size()));
        for (String _iter7 : this.params)
        {
          oprot.writeString(_iter7);
        }
        oprot.writeListEnd();
      }
      oprot.writeFieldEnd();
    }
    oprot.writeFieldStop();
    oprot.writeStructEnd();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("Access(");
    boolean first = true;

    sb.append("id:");
    if (this.id == null) {
      sb.append("null");
    } else {
      sb.append(this.id);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("identity:");
    if (this.identity == null) {
      sb.append("null");
    } else {
      sb.append(this.identity);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("accountId:");
    if (this.accountId == null) {
      sb.append("null");
    } else {
      sb.append(this.accountId);
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
    sb.append("expired:");
    sb.append(this.expired);
    first = false;
    if (!first) sb.append(", ");
    sb.append("title:");
    if (this.title == null) {
      sb.append("null");
    } else {
      sb.append(this.title);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("screenName:");
    if (this.screenName == null) {
      sb.append("null");
    } else {
      sb.append(this.screenName);
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
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bit_vector = new BitSet(1);
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

}

