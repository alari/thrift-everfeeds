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

public class Node implements org.apache.thrift.TBase<Node, Node._Fields>, java.io.Serializable, Cloneable {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("Node");

  private static final org.apache.thrift.protocol.TField TITLE_FIELD_DESC = new org.apache.thrift.protocol.TField("title", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField CREDENTIALS_FIELD_DESC = new org.apache.thrift.protocol.TField("credentials", org.apache.thrift.protocol.TType.STRUCT, (short)10);
  private static final org.apache.thrift.protocol.TField QUERY_FIELD_DESC = new org.apache.thrift.protocol.TField("query", org.apache.thrift.protocol.TType.STRUCT, (short)11);
  private static final org.apache.thrift.protocol.TField LABELS_FIELD_DESC = new org.apache.thrift.protocol.TField("labels", org.apache.thrift.protocol.TType.LIST, (short)20);
  private static final org.apache.thrift.protocol.TField CONTENT_FIELD_DESC = new org.apache.thrift.protocol.TField("content", org.apache.thrift.protocol.TType.STRUCT, (short)21);
  private static final org.apache.thrift.protocol.TField INNERS_FIELD_DESC = new org.apache.thrift.protocol.TField("inners", org.apache.thrift.protocol.TType.LIST, (short)30);
  private static final org.apache.thrift.protocol.TField ACCEPT_TYPES_FIELD_DESC = new org.apache.thrift.protocol.TField("acceptTypes", org.apache.thrift.protocol.TType.LIST, (short)40);
  private static final org.apache.thrift.protocol.TField FLAG_FEED_FIELD_DESC = new org.apache.thrift.protocol.TField("flagFeed", org.apache.thrift.protocol.TType.BOOL, (short)100);
  private static final org.apache.thrift.protocol.TField FLAG_CONTENT_FIELD_DESC = new org.apache.thrift.protocol.TField("flagContent", org.apache.thrift.protocol.TType.BOOL, (short)101);

  public String title;
  public everfeeds.remote.auth.thrift.Credentials credentials;
  public Query query;
  public List<Query> labels;
  public Content content;
  public List<Query> inners;
  public List<everfeeds.remote.discovery.thrift.ttype.ContentType> acceptTypes;
  public boolean flagFeed;
  public boolean flagContent;

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    TITLE((short)1, "title"),
    CREDENTIALS((short)10, "credentials"),
    QUERY((short)11, "query"),
    LABELS((short)20, "labels"),
    CONTENT((short)21, "content"),
    INNERS((short)30, "inners"),
    ACCEPT_TYPES((short)40, "acceptTypes"),
    FLAG_FEED((short)100, "flagFeed"),
    FLAG_CONTENT((short)101, "flagContent");

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
        case 1: // TITLE
          return TITLE;
        case 10: // CREDENTIALS
          return CREDENTIALS;
        case 11: // QUERY
          return QUERY;
        case 20: // LABELS
          return LABELS;
        case 21: // CONTENT
          return CONTENT;
        case 30: // INNERS
          return INNERS;
        case 40: // ACCEPT_TYPES
          return ACCEPT_TYPES;
        case 100: // FLAG_FEED
          return FLAG_FEED;
        case 101: // FLAG_CONTENT
          return FLAG_CONTENT;
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
  private static final int __FLAGFEED_ISSET_ID = 0;
  private static final int __FLAGCONTENT_ISSET_ID = 1;
  private BitSet __isset_bit_vector = new BitSet(2);

  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.TITLE, new org.apache.thrift.meta_data.FieldMetaData("title", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.CREDENTIALS, new org.apache.thrift.meta_data.FieldMetaData("credentials", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, everfeeds.remote.auth.thrift.Credentials.class)));
    tmpMap.put(_Fields.QUERY, new org.apache.thrift.meta_data.FieldMetaData("query", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, Query.class)));
    tmpMap.put(_Fields.LABELS, new org.apache.thrift.meta_data.FieldMetaData("labels", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, Query.class))));
    tmpMap.put(_Fields.CONTENT, new org.apache.thrift.meta_data.FieldMetaData("content", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, Content.class)));
    tmpMap.put(_Fields.INNERS, new org.apache.thrift.meta_data.FieldMetaData("inners", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, Query.class))));
    tmpMap.put(_Fields.ACCEPT_TYPES, new org.apache.thrift.meta_data.FieldMetaData("acceptTypes", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, everfeeds.remote.discovery.thrift.ttype.ContentType.class))));
    tmpMap.put(_Fields.FLAG_FEED, new org.apache.thrift.meta_data.FieldMetaData("flagFeed", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    tmpMap.put(_Fields.FLAG_CONTENT, new org.apache.thrift.meta_data.FieldMetaData("flagContent", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(Node.class, metaDataMap);
  }

  public Node() {
  }

  public Node(
    String title,
    everfeeds.remote.auth.thrift.Credentials credentials,
    Query query,
    List<Query> labels,
    Content content,
    List<Query> inners,
    List<everfeeds.remote.discovery.thrift.ttype.ContentType> acceptTypes,
    boolean flagFeed,
    boolean flagContent)
  {
    this();
    this.title = title;
    this.credentials = credentials;
    this.query = query;
    this.labels = labels;
    this.content = content;
    this.inners = inners;
    this.acceptTypes = acceptTypes;
    this.flagFeed = flagFeed;
    setFlagFeedIsSet(true);
    this.flagContent = flagContent;
    setFlagContentIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public Node(Node other) {
    __isset_bit_vector.clear();
    __isset_bit_vector.or(other.__isset_bit_vector);
    if (other.isSetTitle()) {
      this.title = other.title;
    }
    if (other.isSetCredentials()) {
      this.credentials = new everfeeds.remote.auth.thrift.Credentials(other.credentials);
    }
    if (other.isSetQuery()) {
      this.query = new Query(other.query);
    }
    if (other.isSetLabels()) {
      List<Query> __this__labels = new ArrayList<Query>();
      for (Query other_element : other.labels) {
        __this__labels.add(new Query(other_element));
      }
      this.labels = __this__labels;
    }
    if (other.isSetContent()) {
      this.content = new Content(other.content);
    }
    if (other.isSetInners()) {
      List<Query> __this__inners = new ArrayList<Query>();
      for (Query other_element : other.inners) {
        __this__inners.add(new Query(other_element));
      }
      this.inners = __this__inners;
    }
    if (other.isSetAcceptTypes()) {
      List<everfeeds.remote.discovery.thrift.ttype.ContentType> __this__acceptTypes = new ArrayList<everfeeds.remote.discovery.thrift.ttype.ContentType>();
      for (everfeeds.remote.discovery.thrift.ttype.ContentType other_element : other.acceptTypes) {
        __this__acceptTypes.add(new everfeeds.remote.discovery.thrift.ttype.ContentType(other_element));
      }
      this.acceptTypes = __this__acceptTypes;
    }
    this.flagFeed = other.flagFeed;
    this.flagContent = other.flagContent;
  }

  public Node deepCopy() {
    return new Node(this);
  }

  @Override
  public void clear() {
    this.title = null;
    this.credentials = null;
    this.query = null;
    this.labels = null;
    this.content = null;
    this.inners = null;
    this.acceptTypes = null;
    setFlagFeedIsSet(false);
    this.flagFeed = false;
    setFlagContentIsSet(false);
    this.flagContent = false;
  }

  public String getTitle() {
    return this.title;
  }

  public Node setTitle(String title) {
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

  public everfeeds.remote.auth.thrift.Credentials getCredentials() {
    return this.credentials;
  }

  public Node setCredentials(everfeeds.remote.auth.thrift.Credentials credentials) {
    this.credentials = credentials;
    return this;
  }

  public void unsetCredentials() {
    this.credentials = null;
  }

  /** Returns true if field credentials is set (has been assigned a value) and false otherwise */
  public boolean isSetCredentials() {
    return this.credentials != null;
  }

  public void setCredentialsIsSet(boolean value) {
    if (!value) {
      this.credentials = null;
    }
  }

  public Query getQuery() {
    return this.query;
  }

  public Node setQuery(Query query) {
    this.query = query;
    return this;
  }

  public void unsetQuery() {
    this.query = null;
  }

  /** Returns true if field query is set (has been assigned a value) and false otherwise */
  public boolean isSetQuery() {
    return this.query != null;
  }

  public void setQueryIsSet(boolean value) {
    if (!value) {
      this.query = null;
    }
  }

  public int getLabelsSize() {
    return (this.labels == null) ? 0 : this.labels.size();
  }

  public java.util.Iterator<Query> getLabelsIterator() {
    return (this.labels == null) ? null : this.labels.iterator();
  }

  public void addToLabels(Query elem) {
    if (this.labels == null) {
      this.labels = new ArrayList<Query>();
    }
    this.labels.add(elem);
  }

  public List<Query> getLabels() {
    return this.labels;
  }

  public Node setLabels(List<Query> labels) {
    this.labels = labels;
    return this;
  }

  public void unsetLabels() {
    this.labels = null;
  }

  /** Returns true if field labels is set (has been assigned a value) and false otherwise */
  public boolean isSetLabels() {
    return this.labels != null;
  }

  public void setLabelsIsSet(boolean value) {
    if (!value) {
      this.labels = null;
    }
  }

  public Content getContent() {
    return this.content;
  }

  public Node setContent(Content content) {
    this.content = content;
    return this;
  }

  public void unsetContent() {
    this.content = null;
  }

  /** Returns true if field content is set (has been assigned a value) and false otherwise */
  public boolean isSetContent() {
    return this.content != null;
  }

  public void setContentIsSet(boolean value) {
    if (!value) {
      this.content = null;
    }
  }

  public int getInnersSize() {
    return (this.inners == null) ? 0 : this.inners.size();
  }

  public java.util.Iterator<Query> getInnersIterator() {
    return (this.inners == null) ? null : this.inners.iterator();
  }

  public void addToInners(Query elem) {
    if (this.inners == null) {
      this.inners = new ArrayList<Query>();
    }
    this.inners.add(elem);
  }

  public List<Query> getInners() {
    return this.inners;
  }

  public Node setInners(List<Query> inners) {
    this.inners = inners;
    return this;
  }

  public void unsetInners() {
    this.inners = null;
  }

  /** Returns true if field inners is set (has been assigned a value) and false otherwise */
  public boolean isSetInners() {
    return this.inners != null;
  }

  public void setInnersIsSet(boolean value) {
    if (!value) {
      this.inners = null;
    }
  }

  public int getAcceptTypesSize() {
    return (this.acceptTypes == null) ? 0 : this.acceptTypes.size();
  }

  public java.util.Iterator<everfeeds.remote.discovery.thrift.ttype.ContentType> getAcceptTypesIterator() {
    return (this.acceptTypes == null) ? null : this.acceptTypes.iterator();
  }

  public void addToAcceptTypes(everfeeds.remote.discovery.thrift.ttype.ContentType elem) {
    if (this.acceptTypes == null) {
      this.acceptTypes = new ArrayList<everfeeds.remote.discovery.thrift.ttype.ContentType>();
    }
    this.acceptTypes.add(elem);
  }

  public List<everfeeds.remote.discovery.thrift.ttype.ContentType> getAcceptTypes() {
    return this.acceptTypes;
  }

  public Node setAcceptTypes(List<everfeeds.remote.discovery.thrift.ttype.ContentType> acceptTypes) {
    this.acceptTypes = acceptTypes;
    return this;
  }

  public void unsetAcceptTypes() {
    this.acceptTypes = null;
  }

  /** Returns true if field acceptTypes is set (has been assigned a value) and false otherwise */
  public boolean isSetAcceptTypes() {
    return this.acceptTypes != null;
  }

  public void setAcceptTypesIsSet(boolean value) {
    if (!value) {
      this.acceptTypes = null;
    }
  }

  public boolean isFlagFeed() {
    return this.flagFeed;
  }

  public Node setFlagFeed(boolean flagFeed) {
    this.flagFeed = flagFeed;
    setFlagFeedIsSet(true);
    return this;
  }

  public void unsetFlagFeed() {
    __isset_bit_vector.clear(__FLAGFEED_ISSET_ID);
  }

  /** Returns true if field flagFeed is set (has been assigned a value) and false otherwise */
  public boolean isSetFlagFeed() {
    return __isset_bit_vector.get(__FLAGFEED_ISSET_ID);
  }

  public void setFlagFeedIsSet(boolean value) {
    __isset_bit_vector.set(__FLAGFEED_ISSET_ID, value);
  }

  public boolean isFlagContent() {
    return this.flagContent;
  }

  public Node setFlagContent(boolean flagContent) {
    this.flagContent = flagContent;
    setFlagContentIsSet(true);
    return this;
  }

  public void unsetFlagContent() {
    __isset_bit_vector.clear(__FLAGCONTENT_ISSET_ID);
  }

  /** Returns true if field flagContent is set (has been assigned a value) and false otherwise */
  public boolean isSetFlagContent() {
    return __isset_bit_vector.get(__FLAGCONTENT_ISSET_ID);
  }

  public void setFlagContentIsSet(boolean value) {
    __isset_bit_vector.set(__FLAGCONTENT_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case TITLE:
      if (value == null) {
        unsetTitle();
      } else {
        setTitle((String)value);
      }
      break;

    case CREDENTIALS:
      if (value == null) {
        unsetCredentials();
      } else {
        setCredentials((everfeeds.remote.auth.thrift.Credentials)value);
      }
      break;

    case QUERY:
      if (value == null) {
        unsetQuery();
      } else {
        setQuery((Query)value);
      }
      break;

    case LABELS:
      if (value == null) {
        unsetLabels();
      } else {
        setLabels((List<Query>)value);
      }
      break;

    case CONTENT:
      if (value == null) {
        unsetContent();
      } else {
        setContent((Content)value);
      }
      break;

    case INNERS:
      if (value == null) {
        unsetInners();
      } else {
        setInners((List<Query>)value);
      }
      break;

    case ACCEPT_TYPES:
      if (value == null) {
        unsetAcceptTypes();
      } else {
        setAcceptTypes((List<everfeeds.remote.discovery.thrift.ttype.ContentType>)value);
      }
      break;

    case FLAG_FEED:
      if (value == null) {
        unsetFlagFeed();
      } else {
        setFlagFeed((Boolean)value);
      }
      break;

    case FLAG_CONTENT:
      if (value == null) {
        unsetFlagContent();
      } else {
        setFlagContent((Boolean)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case TITLE:
      return getTitle();

    case CREDENTIALS:
      return getCredentials();

    case QUERY:
      return getQuery();

    case LABELS:
      return getLabels();

    case CONTENT:
      return getContent();

    case INNERS:
      return getInners();

    case ACCEPT_TYPES:
      return getAcceptTypes();

    case FLAG_FEED:
      return new Boolean(isFlagFeed());

    case FLAG_CONTENT:
      return new Boolean(isFlagContent());

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case TITLE:
      return isSetTitle();
    case CREDENTIALS:
      return isSetCredentials();
    case QUERY:
      return isSetQuery();
    case LABELS:
      return isSetLabels();
    case CONTENT:
      return isSetContent();
    case INNERS:
      return isSetInners();
    case ACCEPT_TYPES:
      return isSetAcceptTypes();
    case FLAG_FEED:
      return isSetFlagFeed();
    case FLAG_CONTENT:
      return isSetFlagContent();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof Node)
      return this.equals((Node)that);
    return false;
  }

  public boolean equals(Node that) {
    if (that == null)
      return false;

    boolean this_present_title = true && this.isSetTitle();
    boolean that_present_title = true && that.isSetTitle();
    if (this_present_title || that_present_title) {
      if (!(this_present_title && that_present_title))
        return false;
      if (!this.title.equals(that.title))
        return false;
    }

    boolean this_present_credentials = true && this.isSetCredentials();
    boolean that_present_credentials = true && that.isSetCredentials();
    if (this_present_credentials || that_present_credentials) {
      if (!(this_present_credentials && that_present_credentials))
        return false;
      if (!this.credentials.equals(that.credentials))
        return false;
    }

    boolean this_present_query = true && this.isSetQuery();
    boolean that_present_query = true && that.isSetQuery();
    if (this_present_query || that_present_query) {
      if (!(this_present_query && that_present_query))
        return false;
      if (!this.query.equals(that.query))
        return false;
    }

    boolean this_present_labels = true && this.isSetLabels();
    boolean that_present_labels = true && that.isSetLabels();
    if (this_present_labels || that_present_labels) {
      if (!(this_present_labels && that_present_labels))
        return false;
      if (!this.labels.equals(that.labels))
        return false;
    }

    boolean this_present_content = true && this.isSetContent();
    boolean that_present_content = true && that.isSetContent();
    if (this_present_content || that_present_content) {
      if (!(this_present_content && that_present_content))
        return false;
      if (!this.content.equals(that.content))
        return false;
    }

    boolean this_present_inners = true && this.isSetInners();
    boolean that_present_inners = true && that.isSetInners();
    if (this_present_inners || that_present_inners) {
      if (!(this_present_inners && that_present_inners))
        return false;
      if (!this.inners.equals(that.inners))
        return false;
    }

    boolean this_present_acceptTypes = true && this.isSetAcceptTypes();
    boolean that_present_acceptTypes = true && that.isSetAcceptTypes();
    if (this_present_acceptTypes || that_present_acceptTypes) {
      if (!(this_present_acceptTypes && that_present_acceptTypes))
        return false;
      if (!this.acceptTypes.equals(that.acceptTypes))
        return false;
    }

    boolean this_present_flagFeed = true;
    boolean that_present_flagFeed = true;
    if (this_present_flagFeed || that_present_flagFeed) {
      if (!(this_present_flagFeed && that_present_flagFeed))
        return false;
      if (this.flagFeed != that.flagFeed)
        return false;
    }

    boolean this_present_flagContent = true;
    boolean that_present_flagContent = true;
    if (this_present_flagContent || that_present_flagContent) {
      if (!(this_present_flagContent && that_present_flagContent))
        return false;
      if (this.flagContent != that.flagContent)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  public int compareTo(Node other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;
    Node typedOther = (Node)other;

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
    lastComparison = Boolean.valueOf(isSetCredentials()).compareTo(typedOther.isSetCredentials());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCredentials()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.credentials, typedOther.credentials);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetQuery()).compareTo(typedOther.isSetQuery());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetQuery()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.query, typedOther.query);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetLabels()).compareTo(typedOther.isSetLabels());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetLabels()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.labels, typedOther.labels);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetContent()).compareTo(typedOther.isSetContent());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetContent()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.content, typedOther.content);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetInners()).compareTo(typedOther.isSetInners());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetInners()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.inners, typedOther.inners);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetAcceptTypes()).compareTo(typedOther.isSetAcceptTypes());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetAcceptTypes()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.acceptTypes, typedOther.acceptTypes);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetFlagFeed()).compareTo(typedOther.isSetFlagFeed());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetFlagFeed()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.flagFeed, typedOther.flagFeed);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetFlagContent()).compareTo(typedOther.isSetFlagContent());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetFlagContent()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.flagContent, typedOther.flagContent);
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
        case 1: // TITLE
          if (field.type == org.apache.thrift.protocol.TType.STRING) {
            this.title = iprot.readString();
          } else { 
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 10: // CREDENTIALS
          if (field.type == org.apache.thrift.protocol.TType.STRUCT) {
            this.credentials = new everfeeds.remote.auth.thrift.Credentials();
            this.credentials.read(iprot);
          } else { 
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 11: // QUERY
          if (field.type == org.apache.thrift.protocol.TType.STRUCT) {
            this.query = new Query();
            this.query.read(iprot);
          } else { 
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 20: // LABELS
          if (field.type == org.apache.thrift.protocol.TType.LIST) {
            {
              org.apache.thrift.protocol.TList _list0 = iprot.readListBegin();
              this.labels = new ArrayList<Query>(_list0.size);
              for (int _i1 = 0; _i1 < _list0.size; ++_i1)
              {
                Query _elem2;
                _elem2 = new Query();
                _elem2.read(iprot);
                this.labels.add(_elem2);
              }
              iprot.readListEnd();
            }
          } else { 
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 21: // CONTENT
          if (field.type == org.apache.thrift.protocol.TType.STRUCT) {
            this.content = new Content();
            this.content.read(iprot);
          } else { 
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 30: // INNERS
          if (field.type == org.apache.thrift.protocol.TType.LIST) {
            {
              org.apache.thrift.protocol.TList _list3 = iprot.readListBegin();
              this.inners = new ArrayList<Query>(_list3.size);
              for (int _i4 = 0; _i4 < _list3.size; ++_i4)
              {
                Query _elem5;
                _elem5 = new Query();
                _elem5.read(iprot);
                this.inners.add(_elem5);
              }
              iprot.readListEnd();
            }
          } else { 
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 40: // ACCEPT_TYPES
          if (field.type == org.apache.thrift.protocol.TType.LIST) {
            {
              org.apache.thrift.protocol.TList _list6 = iprot.readListBegin();
              this.acceptTypes = new ArrayList<everfeeds.remote.discovery.thrift.ttype.ContentType>(_list6.size);
              for (int _i7 = 0; _i7 < _list6.size; ++_i7)
              {
                everfeeds.remote.discovery.thrift.ttype.ContentType _elem8;
                _elem8 = new everfeeds.remote.discovery.thrift.ttype.ContentType();
                _elem8.read(iprot);
                this.acceptTypes.add(_elem8);
              }
              iprot.readListEnd();
            }
          } else { 
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 100: // FLAG_FEED
          if (field.type == org.apache.thrift.protocol.TType.BOOL) {
            this.flagFeed = iprot.readBool();
            setFlagFeedIsSet(true);
          } else { 
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 101: // FLAG_CONTENT
          if (field.type == org.apache.thrift.protocol.TType.BOOL) {
            this.flagContent = iprot.readBool();
            setFlagContentIsSet(true);
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
    if (this.title != null) {
      oprot.writeFieldBegin(TITLE_FIELD_DESC);
      oprot.writeString(this.title);
      oprot.writeFieldEnd();
    }
    if (this.credentials != null) {
      oprot.writeFieldBegin(CREDENTIALS_FIELD_DESC);
      this.credentials.write(oprot);
      oprot.writeFieldEnd();
    }
    if (this.query != null) {
      oprot.writeFieldBegin(QUERY_FIELD_DESC);
      this.query.write(oprot);
      oprot.writeFieldEnd();
    }
    if (this.labels != null) {
      oprot.writeFieldBegin(LABELS_FIELD_DESC);
      {
        oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, this.labels.size()));
        for (Query _iter9 : this.labels)
        {
          _iter9.write(oprot);
        }
        oprot.writeListEnd();
      }
      oprot.writeFieldEnd();
    }
    if (this.content != null) {
      oprot.writeFieldBegin(CONTENT_FIELD_DESC);
      this.content.write(oprot);
      oprot.writeFieldEnd();
    }
    if (this.inners != null) {
      oprot.writeFieldBegin(INNERS_FIELD_DESC);
      {
        oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, this.inners.size()));
        for (Query _iter10 : this.inners)
        {
          _iter10.write(oprot);
        }
        oprot.writeListEnd();
      }
      oprot.writeFieldEnd();
    }
    if (this.acceptTypes != null) {
      oprot.writeFieldBegin(ACCEPT_TYPES_FIELD_DESC);
      {
        oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, this.acceptTypes.size()));
        for (everfeeds.remote.discovery.thrift.ttype.ContentType _iter11 : this.acceptTypes)
        {
          _iter11.write(oprot);
        }
        oprot.writeListEnd();
      }
      oprot.writeFieldEnd();
    }
    oprot.writeFieldBegin(FLAG_FEED_FIELD_DESC);
    oprot.writeBool(this.flagFeed);
    oprot.writeFieldEnd();
    oprot.writeFieldBegin(FLAG_CONTENT_FIELD_DESC);
    oprot.writeBool(this.flagContent);
    oprot.writeFieldEnd();
    oprot.writeFieldStop();
    oprot.writeStructEnd();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("Node(");
    boolean first = true;

    sb.append("title:");
    if (this.title == null) {
      sb.append("null");
    } else {
      sb.append(this.title);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("credentials:");
    if (this.credentials == null) {
      sb.append("null");
    } else {
      sb.append(this.credentials);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("query:");
    if (this.query == null) {
      sb.append("null");
    } else {
      sb.append(this.query);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("labels:");
    if (this.labels == null) {
      sb.append("null");
    } else {
      sb.append(this.labels);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("content:");
    if (this.content == null) {
      sb.append("null");
    } else {
      sb.append(this.content);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("inners:");
    if (this.inners == null) {
      sb.append("null");
    } else {
      sb.append(this.inners);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("acceptTypes:");
    if (this.acceptTypes == null) {
      sb.append("null");
    } else {
      sb.append(this.acceptTypes);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("flagFeed:");
    sb.append(this.flagFeed);
    first = false;
    if (!first) sb.append(", ");
    sb.append("flagContent:");
    sb.append(this.flagContent);
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

