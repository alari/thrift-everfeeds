@Typed package everfeeds.mongo;


import everfeeds.thrift.domain.Tag
import org.bson.types.ObjectId
import com.google.code.morphia.annotations.*
import com.google.code.morphia.annotations.Reference;

/**
 * @author Dmitry Kurinskiy
 * @since 06.05.11 14:40
 */
@Entity
@Indexes([
@Index(value = "access,identity", unique = true, dropDups = true)
])
public class TagD {
  @Id
  ObjectId id;

  @Reference
  @Indexed
  AccessD access;

  @Reference(lazy = true)
  TagD parent;

  @Version
  Long version;

  String identity;
  String title;

  Date dateCreated = new Date();
  Date lastUpdated = new Date();

  @PrePersist
  void prePersist() {
    lastUpdated = new Date();
  }

  public void syncToThrift(Tag tag) {
    tag.id = id.toString();
    tag.identity = identity;
    tag.title = title;
    tag.accessId = access.id.toString();
    tag.parentId = parent?.id?.toString();
  }

  public void syncFromThrift(Tag tag) {
    identity = tag.identity;
    title = tag.title;
  }
}
