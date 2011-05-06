package everfeeds.mongo;

import com.google.code.morphia.annotations.*;
import everfeeds.thrift.EntryKind;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;

/**
 * @author Dmitry Kurinskiy
 * @since 06.05.11 14:41
 */
@Entity
public class Filter {
  @Id
  ObjectId id;

  @Version
  Long version;

  @Reference
  Access access;

  String title;

  @Reference
  List<Category> categories;
  boolean categoriesWith;

  @Reference
  List<Tag> withTags;
  @Reference
  List<Tag> withoutTags;

  List<EntryKind> kinds;
  boolean kindsWith;

  Date dateCreated = new Date();
  Date lastUpdated = new Date();

  @PrePersist
  void prePersist() {
    lastUpdated = new Date();
  }
}
