package everfeeds.mongo;

import com.google.code.morphia.annotations.*;
import everfeeds.thrift.domain.Access;
import everfeeds.thrift.ttype.AccessType;
import everfeeds.thrift.util.Type;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;

/**
 * @author Dmitry Kurinskiy
 * @since 06.05.11 14:27
 */
@Entity
@Indexes({
             @Index(value = "identity, type", unique = true, dropDups = true)})
public class AccessD {
  @Id
  public ObjectId id;

  public String identity;

  public boolean expired;

  public String accessToken;
  public String accessSecret;
  public List<String> params;

  @Reference(lazy = true)
  public AccountD account;

  public Type type;

  public String title;
  public String screenName;

  @Version
  public Long version;

  public Date dateCreated = new Date();
  public Date lastUpdated = new Date();

  @PrePersist
  void prePersist() {
    lastUpdated = new Date();
  }

  public void syncToThrift(Access access) {
    access.title = title;
    access.identity = identity;
    access.screenName = screenName;
    access.expired = expired;
    access.id = id.toString();
    access.type = type.toThrift();
  }

  public void syncFromThrift(Access access) {
    title = access.title;
    identity = access.identity;
    screenName = access.screenName;
  }
}
