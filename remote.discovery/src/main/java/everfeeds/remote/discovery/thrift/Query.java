/**
 * Autogenerated by Thrift
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 */
package everfeeds.remote.discovery.thrift;

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

public class Query implements org.apache.thrift.TBase<Query, Query._Fields>, java.io.Serializable, Cloneable {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("Query");

  private static final org.apache.thrift.protocol.TField URI_FIELD_DESC = new org.apache.thrift.protocol.TField("uri", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField NODE_TYPE_FIELD_DESC = new org.apache.thrift.protocol.TField("nodeType", org.apache.thrift.protocol.TType.STRUCT, (short)2);
  private static final org.apache.thrift.protocol.TField LOCATION_FIELD_DESC = new org.apache.thrift.protocol.TField("location", org.apache.thrift.protocol.TType.STRUCT, (short)10);
  private static final org.apache.thrift.protocol.TField SEARCH_FIELD_DESC = new org.apache.thrift.protocol.TField("search", org.apache.thrift.protocol.TType.STRING, (short)11);

  public String uri;
  public everfeeds.remote.discovery.thrift.ttype.NodeType nodeType;
  public everfeeds.remote.discovery.thrift.geo.GeoLocation location;
  public String search;

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    URI((short)1, "uri"),
    NODE_TYPE((short)2, "nodeType"),
    LOCATION((short)10, "location"),
    SEARCH((short)11, "search");

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
        case 1: // URI
          return URI;
        case 2: // NODE_TYPE
          return NODE_TYPE;
        case 10: // LOCATION
          return LOCATION;
        case 11: // SEARCH
          return SEARCH;
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
    tmpMap.put(_Fields.URI, new org.apache.thrift.meta_data.FieldMetaData("uri", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.NODE_TYPE, new org.apache.thrift.meta_data.FieldMetaData("nodeType", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, everfeeds.remote.discovery.thrift.ttype.NodeType.class)));
    tmpMap.put(_Fields.LOCATION, new org.apache.thrift.meta_data.FieldMetaData("location", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, everfeeds.remote.discovery.thrift.geo.GeoLocation.class)));
    tmpMap.put(_Fields.SEARCH, new org.apache.thrift.meta_data.FieldMetaData("search", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(Query.class, metaDataMap);
  }

  public Query() {
  }

  public Query(
    String uri,
    everfeeds.remote.discovery.thrift.ttype.NodeType nodeType,
    everfeeds.remote.discovery.thrift.geo.GeoLocation location,
    String search)
  {
    this();
    this.uri = uri;
    this.nodeType = nodeType;
    this.location = location;
    this.search = search;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public Query(Query other) {
    if (other.isSetUri()) {
      this.uri = other.uri;
    }
    if (other.isSetNodeType()) {
      this.nodeType = new everfeeds.remote.discovery.thrift.ttype.NodeType(other.nodeType);
    }
    if (other.isSetLocation()) {
      this.location = new everfeeds.remote.discovery.thrift.geo.GeoLocation(other.location);
    }
    if (other.isSetSearch()) {
      this.search = other.search;
    }
  }

  public Query deepCopy() {
    return new Query(this);
  }

  @Override
  public void clear() {
    this.uri = null;
    this.nodeType = null;
    this.location = null;
    this.search = null;
  }

  public String getUri() {
    return this.uri;
  }

  public Query setUri(String uri) {
    this.uri = uri;
    return this;
  }

  public void unsetUri() {
    this.uri = null;
  }

  /** Returns true if field uri is set (has been assigned a value) and false otherwise */
  public boolean isSetUri() {
    return this.uri != null;
  }

  public void setUriIsSet(boolean value) {
    if (!value) {
      this.uri = null;
    }
  }

  public everfeeds.remote.discovery.thrift.ttype.NodeType getNodeType() {
    return this.nodeType;
  }

  public Query setNodeType(everfeeds.remote.discovery.thrift.ttype.NodeType nodeType) {
    this.nodeType = nodeType;
    return this;
  }

  public void unsetNodeType() {
    this.nodeType = null;
  }

  /** Returns true if field nodeType is set (has been assigned a value) and false otherwise */
  public boolean isSetNodeType() {
    return this.nodeType != null;
  }

  public void setNodeTypeIsSet(boolean value) {
    if (!value) {
      this.nodeType = null;
    }
  }

  public everfeeds.remote.discovery.thrift.geo.GeoLocation getLocation() {
    return this.location;
  }

  public Query setLocation(everfeeds.remote.discovery.thrift.geo.GeoLocation location) {
    this.location = location;
    return this;
  }

  public void unsetLocation() {
    this.location = null;
  }

  /** Returns true if field location is set (has been assigned a value) and false otherwise */
  public boolean isSetLocation() {
    return this.location != null;
  }

  public void setLocationIsSet(boolean value) {
    if (!value) {
      this.location = null;
    }
  }

  public String getSearch() {
    return this.search;
  }

  public Query setSearch(String search) {
    this.search = search;
    return this;
  }

  public void unsetSearch() {
    this.search = null;
  }

  /** Returns true if field search is set (has been assigned a value) and false otherwise */
  public boolean isSetSearch() {
    return this.search != null;
  }

  public void setSearchIsSet(boolean value) {
    if (!value) {
      this.search = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case URI:
      if (value == null) {
        unsetUri();
      } else {
        setUri((String)value);
      }
      break;

    case NODE_TYPE:
      if (value == null) {
        unsetNodeType();
      } else {
        setNodeType((everfeeds.remote.discovery.thrift.ttype.NodeType)value);
      }
      break;

    case LOCATION:
      if (value == null) {
        unsetLocation();
      } else {
        setLocation((everfeeds.remote.discovery.thrift.geo.GeoLocation)value);
      }
      break;

    case SEARCH:
      if (value == null) {
        unsetSearch();
      } else {
        setSearch((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case URI:
      return getUri();

    case NODE_TYPE:
      return getNodeType();

    case LOCATION:
      return getLocation();

    case SEARCH:
      return getSearch();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case URI:
      return isSetUri();
    case NODE_TYPE:
      return isSetNodeType();
    case LOCATION:
      return isSetLocation();
    case SEARCH:
      return isSetSearch();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof Query)
      return this.equals((Query)that);
    return false;
  }

  public boolean equals(Query that) {
    if (that == null)
      return false;

    boolean this_present_uri = true && this.isSetUri();
    boolean that_present_uri = true && that.isSetUri();
    if (this_present_uri || that_present_uri) {
      if (!(this_present_uri && that_present_uri))
        return false;
      if (!this.uri.equals(that.uri))
        return false;
    }

    boolean this_present_nodeType = true && this.isSetNodeType();
    boolean that_present_nodeType = true && that.isSetNodeType();
    if (this_present_nodeType || that_present_nodeType) {
      if (!(this_present_nodeType && that_present_nodeType))
        return false;
      if (!this.nodeType.equals(that.nodeType))
        return false;
    }

    boolean this_present_location = true && this.isSetLocation();
    boolean that_present_location = true && that.isSetLocation();
    if (this_present_location || that_present_location) {
      if (!(this_present_location && that_present_location))
        return false;
      if (!this.location.equals(that.location))
        return false;
    }

    boolean this_present_search = true && this.isSetSearch();
    boolean that_present_search = true && that.isSetSearch();
    if (this_present_search || that_present_search) {
      if (!(this_present_search && that_present_search))
        return false;
      if (!this.search.equals(that.search))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  public int compareTo(Query other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;
    Query typedOther = (Query)other;

    lastComparison = Boolean.valueOf(isSetUri()).compareTo(typedOther.isSetUri());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetUri()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.uri, typedOther.uri);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetNodeType()).compareTo(typedOther.isSetNodeType());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetNodeType()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.nodeType, typedOther.nodeType);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetLocation()).compareTo(typedOther.isSetLocation());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetLocation()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.location, typedOther.location);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetSearch()).compareTo(typedOther.isSetSearch());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSearch()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.search, typedOther.search);
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
        case 1: // URI
          if (field.type == org.apache.thrift.protocol.TType.STRING) {
            this.uri = iprot.readString();
          } else { 
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 2: // NODE_TYPE
          if (field.type == org.apache.thrift.protocol.TType.STRUCT) {
            this.nodeType = new everfeeds.remote.discovery.thrift.ttype.NodeType();
            this.nodeType.read(iprot);
          } else { 
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 10: // LOCATION
          if (field.type == org.apache.thrift.protocol.TType.STRUCT) {
            this.location = new everfeeds.remote.discovery.thrift.geo.GeoLocation();
            this.location.read(iprot);
          } else { 
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 11: // SEARCH
          if (field.type == org.apache.thrift.protocol.TType.STRING) {
            this.search = iprot.readString();
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
    if (this.uri != null) {
      oprot.writeFieldBegin(URI_FIELD_DESC);
      oprot.writeString(this.uri);
      oprot.writeFieldEnd();
    }
    if (this.nodeType != null) {
      oprot.writeFieldBegin(NODE_TYPE_FIELD_DESC);
      this.nodeType.write(oprot);
      oprot.writeFieldEnd();
    }
    if (this.location != null) {
      oprot.writeFieldBegin(LOCATION_FIELD_DESC);
      this.location.write(oprot);
      oprot.writeFieldEnd();
    }
    if (this.search != null) {
      oprot.writeFieldBegin(SEARCH_FIELD_DESC);
      oprot.writeString(this.search);
      oprot.writeFieldEnd();
    }
    oprot.writeFieldStop();
    oprot.writeStructEnd();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("Query(");
    boolean first = true;

    sb.append("uri:");
    if (this.uri == null) {
      sb.append("null");
    } else {
      sb.append(this.uri);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("nodeType:");
    if (this.nodeType == null) {
      sb.append("null");
    } else {
      sb.append(this.nodeType);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("location:");
    if (this.location == null) {
      sb.append("null");
    } else {
      sb.append(this.location);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("search:");
    if (this.search == null) {
      sb.append("null");
    } else {
      sb.append(this.search);
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

