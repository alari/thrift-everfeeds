package everfeeds.mongo;

import com.google.code.morphia.annotations.*;
import everfeeds.thrift.AccessType;
import org.bson.types.ObjectId;

import java.util.Date;

/**
 * @author Dmitry Kurinskiy
 * @since 06.05.11 14:27
 */
@Entity
public class AccessD {
  @Id
  public ObjectId id;

  public String identity;

  public boolean expired;

  public String accessToken;
  public String accessSecret;
  public String shardId;

  @Reference
  public AccountD account;

  public AccessType type;

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
}
