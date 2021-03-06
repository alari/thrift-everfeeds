/**
 * Autogenerated by Thrift
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 */
package everfeeds.remote.discovery.thrift.ttype;

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

public class NodeType implements org.apache.thrift.TBase<NodeType, NodeType._Fields>, java.io.Serializable, Cloneable {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("NodeType");

  private static final org.apache.thrift.protocol.TField GROUP_FIELD_DESC = new org.apache.thrift.protocol.TField("group", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField NARROW_FIELD_DESC = new org.apache.thrift.protocol.TField("narrow", org.apache.thrift.protocol.TType.I32, (short)2);
  private static final org.apache.thrift.protocol.TField TYPE_FIELD_DESC = new org.apache.thrift.protocol.TField("type", org.apache.thrift.protocol.TType.STRING, (short)10);

  /**
   * 
   * @see NodeTypeGroup
   */
  public NodeTypeGroup group;
  /**
   * 
   * @see TypeNarrow
   */
  public TypeNarrow narrow;
  public String type;

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    /**
     * 
     * @see NodeTypeGroup
     */
    GROUP((short)1, "group"),
    /**
     * 
     * @see TypeNarrow
     */
    NARROW((short)2, "narrow"),
    TYPE((short)10, "type");

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
        case 1: // GROUP
          return GROUP;
        case 2: // NARROW
          return NARROW;
        case 10: // TYPE
          return TYPE;
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
    tmpMap.put(_Fields.GROUP, new org.apache.thrift.meta_data.FieldMetaData("group", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.EnumMetaData(org.apache.thrift.protocol.TType.ENUM, NodeTypeGroup.class)));
    tmpMap.put(_Fields.NARROW, new org.apache.thrift.meta_data.FieldMetaData("narrow", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.EnumMetaData(org.apache.thrift.protocol.TType.ENUM, TypeNarrow.class)));
    tmpMap.put(_Fields.TYPE, new org.apache.thrift.meta_data.FieldMetaData("type", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(NodeType.class, metaDataMap);
  }

  public NodeType() {
  }

  public NodeType(
    NodeTypeGroup group,
    TypeNarrow narrow,
    String type)
  {
    this();
    this.group = group;
    this.narrow = narrow;
    this.type = type;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public NodeType(NodeType other) {
    if (other.isSetGroup()) {
      this.group = other.group;
    }
    if (other.isSetNarrow()) {
      this.narrow = other.narrow;
    }
    if (other.isSetType()) {
      this.type = other.type;
    }
  }

  public NodeType deepCopy() {
    return new NodeType(this);
  }

  @Override
  public void clear() {
    this.group = null;
    this.narrow = null;
    this.type = null;
  }

  /**
   * 
   * @see NodeTypeGroup
   */
  public NodeTypeGroup getGroup() {
    return this.group;
  }

  /**
   * 
   * @see NodeTypeGroup
   */
  public NodeType setGroup(NodeTypeGroup group) {
    this.group = group;
    return this;
  }

  public void unsetGroup() {
    this.group = null;
  }

  /** Returns true if field group is set (has been assigned a value) and false otherwise */
  public boolean isSetGroup() {
    return this.group != null;
  }

  public void setGroupIsSet(boolean value) {
    if (!value) {
      this.group = null;
    }
  }

  /**
   * 
   * @see TypeNarrow
   */
  public TypeNarrow getNarrow() {
    return this.narrow;
  }

  /**
   * 
   * @see TypeNarrow
   */
  public NodeType setNarrow(TypeNarrow narrow) {
    this.narrow = narrow;
    return this;
  }

  public void unsetNarrow() {
    this.narrow = null;
  }

  /** Returns true if field narrow is set (has been assigned a value) and false otherwise */
  public boolean isSetNarrow() {
    return this.narrow != null;
  }

  public void setNarrowIsSet(boolean value) {
    if (!value) {
      this.narrow = null;
    }
  }

  public String getType() {
    return this.type;
  }

  public NodeType setType(String type) {
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

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case GROUP:
      if (value == null) {
        unsetGroup();
      } else {
        setGroup((NodeTypeGroup)value);
      }
      break;

    case NARROW:
      if (value == null) {
        unsetNarrow();
      } else {
        setNarrow((TypeNarrow)value);
      }
      break;

    case TYPE:
      if (value == null) {
        unsetType();
      } else {
        setType((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case GROUP:
      return getGroup();

    case NARROW:
      return getNarrow();

    case TYPE:
      return getType();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case GROUP:
      return isSetGroup();
    case NARROW:
      return isSetNarrow();
    case TYPE:
      return isSetType();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof NodeType)
      return this.equals((NodeType)that);
    return false;
  }

  public boolean equals(NodeType that) {
    if (that == null)
      return false;

    boolean this_present_group = true && this.isSetGroup();
    boolean that_present_group = true && that.isSetGroup();
    if (this_present_group || that_present_group) {
      if (!(this_present_group && that_present_group))
        return false;
      if (!this.group.equals(that.group))
        return false;
    }

    boolean this_present_narrow = true && this.isSetNarrow();
    boolean that_present_narrow = true && that.isSetNarrow();
    if (this_present_narrow || that_present_narrow) {
      if (!(this_present_narrow && that_present_narrow))
        return false;
      if (!this.narrow.equals(that.narrow))
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

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  public int compareTo(NodeType other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;
    NodeType typedOther = (NodeType)other;

    lastComparison = Boolean.valueOf(isSetGroup()).compareTo(typedOther.isSetGroup());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetGroup()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.group, typedOther.group);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetNarrow()).compareTo(typedOther.isSetNarrow());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetNarrow()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.narrow, typedOther.narrow);
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
        case 1: // GROUP
          if (field.type == org.apache.thrift.protocol.TType.I32) {
            this.group = NodeTypeGroup.findByValue(iprot.readI32());
          } else { 
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 2: // NARROW
          if (field.type == org.apache.thrift.protocol.TType.I32) {
            this.narrow = TypeNarrow.findByValue(iprot.readI32());
          } else { 
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 10: // TYPE
          if (field.type == org.apache.thrift.protocol.TType.STRING) {
            this.type = iprot.readString();
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
    if (this.group != null) {
      oprot.writeFieldBegin(GROUP_FIELD_DESC);
      oprot.writeI32(this.group.getValue());
      oprot.writeFieldEnd();
    }
    if (this.narrow != null) {
      oprot.writeFieldBegin(NARROW_FIELD_DESC);
      oprot.writeI32(this.narrow.getValue());
      oprot.writeFieldEnd();
    }
    if (this.type != null) {
      oprot.writeFieldBegin(TYPE_FIELD_DESC);
      oprot.writeString(this.type);
      oprot.writeFieldEnd();
    }
    oprot.writeFieldStop();
    oprot.writeStructEnd();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("NodeType(");
    boolean first = true;

    sb.append("group:");
    if (this.group == null) {
      sb.append("null");
    } else {
      sb.append(this.group);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("narrow:");
    if (this.narrow == null) {
      sb.append("null");
    } else {
      sb.append(this.narrow);
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

