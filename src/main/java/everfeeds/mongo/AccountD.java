package everfeeds.mongo;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.PrePersist;
import com.google.code.morphia.annotations.Version;
import everfeeds.thrift.Account;
import org.bson.types.ObjectId;

import java.util.Date;

/**
 * @author Dmitry Kurinskiy
 * @since 06.05.11 14:34
 */
@Entity
public class AccountD {
  @Id
  public ObjectId id;

  public String title;

  @Version
  public Long version;

  public Date dateCreated = new Date();
  public Date lastUpdated = new Date();

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
