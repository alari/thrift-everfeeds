@Typed package everfeeds.mongo;


import everfeeds.thrift.domain.Entry
import everfeeds.thrift.util.Kind
import org.bson.types.ObjectId
import com.google.code.morphia.annotations.*
import com.google.code.morphia.annotations.Reference;

/**
 * @author Dmitry Kurinskiy
 * @since 06.05.11 14:40
 */
@Entity
@Indexes([
@Index(value = "kind,tags,category,dateCreated,-datePlaced"),
@Index(value = "kind,identity,access", unique = true, dropDups = true),
@Index(value = "access,isRead,-datePlaced")
])
public class EntryD {
  @Id
  ObjectId id;

  @Reference
  @Indexed
  AccessD access;


  @Reference
  @Indexed
  AccountD account;

  @Version
  Long version;

  String identity;
  String title;

  String description;
  @Reference(lazy = true)
  EntryContentD content;

  @Embedded
  AuthorD author;

  String sourceUrl;

  @Indexed
  Kind kind;

  @Indexed
  boolean isAuthor;
  @Indexed
  boolean isPublicAvailable;
  @Indexed
  boolean isFavorite;
  @Indexed
  boolean isRead;

  Date dateCreated = new Date();
  Date lastUpdated = new Date();

  @Indexed
  Date datePlaced;

  @Reference
  @Indexed
  CategoryD category;
  @Reference
  @Indexed
  List<TagD> tags;
  @Reference
  @Indexed
  List<FilterD> filters;

  @Reference(lazy = true)
  OriginalD original;

  @PrePersist
  void prePersist() {
    lastUpdated = new Date();
  }

  public void syncToThrift(Entry entry) {
    entry.id = id.toString();
    entry.accessId = access.id.toString();

    entry.identity = identity;
    entry.title = title;
    entry.description = description;
    entry.sourceUrl = sourceUrl;

    entry.author = author.toThrift();

    entry.kind = kind.toThrift();

    entry.isAuthor = isAuthor;
    entry.isPublicAvailable = isPublicAvailable;
    entry.isFavorite = isFavorite;
    entry.isRead = isRead;

    entry.dateCreated = dateCreated.getTime();
    entry.datePlaced = datePlaced.getTime();

    entry.categoryId = category.id.toString();

    entry.tagIds.clear();
    for (TagD tag: tags) {
      entry.tagIds.add(tag.id.toString());
    }

    entry.filterIds.clear();
    for (FilterD filter: filters) {
      entry.filterIds.add(filter.id.toString());
    }
  }

  public void syncFromThrift(Entry entry) {
    identity = entry.getIdentity();
    title = entry.getTitle();
    description = entry.getDescription();
    sourceUrl = entry.sourceUrl;

    author = new AuthorD();
    author.syncFromThrift(entry.author);

    kind = Kind.getByThrift(entry.kind);

    isAuthor = entry.isIsAuthor();
    isPublicAvailable = entry.isIsPublicAvailable();
    isFavorite = entry.isIsFavorite();
    isRead = entry.isIsRead();

    datePlaced = new Date(entry.getDatePlaced());
  }
}
