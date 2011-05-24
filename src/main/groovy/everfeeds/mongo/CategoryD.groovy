@Typed package everfeeds.mongo;


import everfeeds.thrift.domain.Category
import org.bson.types.ObjectId
import com.google.code.morphia.annotations.*
import com.google.code.morphia.annotations.Reference;

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
  ObjectId id;

  @Version
  Long version;

  @Reference
  AccessD access;

  @Reference(lazy = true)
  CategoryD parent;

  String identity;
  String title;

  Date dateCreated = new Date();
  Date lastUpdated = new Date();

  @PrePersist
  void prePersist() {
    lastUpdated = new Date();
  }

  public void syncToThrift(Category category) {
    category.id = id.toString();
    category.identity = identity;
    category.title = title;
    category.accessId = access.id.toString();
    category.parentId = parent == null ? "" : parent.id.toString();
  }

  public void syncFromThrift(Category category) {
    identity = category.identity;
    title = category.title;
  }
}
