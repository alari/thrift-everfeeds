package everfeeds.mongo;

import com.google.code.morphia.annotations.*;
import everfeeds.thrift.domain.Entry;
import everfeeds.thrift.util.Kind;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;

/**
 * @author Dmitry Kurinskiy
 * @since 06.05.11 14:40
 */
@Entity
@Indexes({
             @Index(value = "kind,tags,category,dateCreated,-datePlaced"),
             @Index(value = "kind,identity,access", unique = true, dropDups = true),
             @Index(value = "access,isRead,-datePlaced")
})
public class EntryD {
  @Id
  public ObjectId id;

  @Reference
  @Indexed
  public AccessD access;


  @Reference
  @Indexed
  public AccountD account;

  @Version
  public Long version;

  public String identity;
  public String title;

  public String description;
  @Reference(lazy = true)
  public EntryContentD content;

  @Embedded
  public AuthorD author;

  public String sourceUrl;

  @Indexed
  public Kind kind;

  @Indexed
  public boolean isAuthor;
  @Indexed
  public boolean isPublicAvailable;
  @Indexed
  public boolean isFavorite;
  @Indexed
  public boolean isRead;

  public Date dateCreated = new Date();
  public Date lastUpdated = new Date();

  @PrePersist
  void prePersist() {
    lastUpdated = new Date();
  }

  @Indexed
  public Date datePlaced;

  @Reference
  @Indexed
  public CategoryD category;
  @Reference
  @Indexed
  public List<TagD> tags;
  @Reference
  @Indexed
  public List<FilterD> filters;

  @Reference
  public OriginalD original;

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
    for (TagD tag : tags) {
      entry.tagIds.add(tag.id.toString());
    }

    entry.filterIds.clear();
    for (FilterD filter : filters) {
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
