@Typed package everfeeds.mongo;


import com.google.code.morphia.annotations.Entity
import com.google.code.morphia.annotations.Id
import com.google.code.morphia.annotations.PrePersist
import com.google.code.morphia.annotations.Version
import everfeeds.thrift.domain.Account
import org.bson.types.ObjectId

/**
 * @author Dmitry Kurinskiy
 * @since 06.05.11 14:34
 */
@Entity
public class AccountD {
  @Id
  ObjectId id;

  String title;

  @Version
  Long version;

  Date dateCreated = new Date();
  Date lastUpdated = new Date();

  @PrePersist
  void prePersist() {
    lastUpdated = new Date();
  }

  public void syncToThrift(Account account) {
    account.title = title;
    account.id = id.toString();
  }

  public void syncFromThrift(Account account) {
    title = account.title;
  }
}