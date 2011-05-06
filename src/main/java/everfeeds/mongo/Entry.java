package everfeeds.mongo;

import com.google.code.morphia.annotations.*;
import everfeeds.thrift.EntryKind;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;

/**
 * @author Dmitry Kurinskiy
 * @since 06.05.11 14:40
 */
@Entity
public class Entry {
  @Id
  ObjectId id;

  @Reference
  Access access;


  @Reference
  Account account;

  @Version
  Long version;

  String identity;
  String title;

  String description;
  @Reference
  EntryContent content;

  @Embedded
  Author author;

  EntryKind kind;

  boolean isAuthor;
  boolean isPublicAvailable;
  boolean isFavorite;
  boolean isRead;

  Date dateCreated = new Date();
  Date lastUpdated = new Date();

  @PrePersist
  void prePersist() {
    lastUpdated = new Date();
  }

  Date datePlaced;

  @Reference
  Category category;
  @Reference
  List<Tag> tags;
  @Reference
  List<Filter> filters;
}
