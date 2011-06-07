@Typed package everfeeds.mongo;


import everfeeds.thrift.domain.Access
import everfeeds.thrift.util.Type
import org.bson.types.ObjectId
import com.google.code.morphia.annotations.*
import com.google.code.morphia.annotations.Reference;

/**
 * @author Dmitry Kurinskiy
 * @since 06.05.11 14:27
 */
@Entity
@Indexes([
@Index(value = "identity, type", unique = true, dropDups = true)])
public class AccessD {
  @Id
  ObjectId id;

  String identity;

  boolean expired;

  String accessToken;
  String accessSecret;
  Map<String,String> params;

  @Reference(lazy = true)
  AccountD account;

  Type type;

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

  public void syncToThrift(Access access) {
    access.title = title;
    access.identity = identity;
    access.screenName = screenName;
    access.expired = expired;
    access.id = id.toString();
    access.type = type.toThrift();
    access.accountId = account.id.toString();
  }

  public void syncFromThrift(Access access) {
    title = access.title;
    identity = access.identity;
    screenName = access.screenName;
  }

  public void syncFullFromThrift(Access access) {
    syncFromThrift(access);
    expired = access.expired;
    type = Type.getByThrift(access.type);
  }
}