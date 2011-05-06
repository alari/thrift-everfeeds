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
public class Access {
  @Id
  ObjectId id;

  String identity;

  boolean expired;

  String accessToken;
  String accessSecret;
  String shardId;

  @Reference
  Account account;

  AccessType type;

  String title;
  String screenName;

  @Version
  Long version;

  Date dateCreated = new Date();
  Date lastUpdated = new Date();

  @PrePersist
  void prePersist() {
    lastUpdated = new Date();
  }
}
