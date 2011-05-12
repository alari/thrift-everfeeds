package everfeeds.mongo;

import com.google.code.morphia.annotations.*;
import everfeeds.thrift.Category;
import org.bson.types.ObjectId;

import java.util.Date;

/**
 * @author Dmitry Kurinskiy
 * @since 06.05.11 14:40
 */
@Entity
@Indexes(
            @Index(value = "identity, access", unique = true)
)
public class CategoryD {
  @Id
  public ObjectId id;

  @Version
  public Long version;

  @Reference
  public AccessD access;

  public String identity;
  public String title;

  public Date dateCreated = new Date();
  public Date lastUpdated = new Date();

  @PrePersist
  void prePersist() {
    lastUpdated = new Date();
  }

  public void syncToThrift(Category category) {
    category.id = id.toString();
    category.identity = identity;
    category.title = title;
    category.accessId = access.id.toString();
  }

  public void syncFromThrift(Category category) {
    identity = category.identity;
    title = category.title;
  }
}
