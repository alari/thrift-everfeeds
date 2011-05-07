package everfeeds.mongo;

import com.google.code.morphia.annotations.*;
import everfeeds.thrift.Entry;
import everfeeds.thrift.EntryKind;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Dmitry Kurinskiy
 * @since 06.05.11 14:40
 */
@Entity
public class EntryD {
  @Id
  public ObjectId id;

  @Reference
  public AccessD access;


  @Reference
  public AccountD account;

  @Version
  public Long version;

  public String identity;
  public String title;

  public String description;
  @Reference(lazy=true)
  public EntryContentD content;

  @Embedded
  public AuthorD author;

  public EntryKind kind;

  public boolean isAuthor;
  public boolean isPublicAvailable;
  public boolean isFavorite;
  public boolean isRead;

  public Date dateCreated = new Date();
  public Date lastUpdated = new Date();

  @PrePersist
  void prePersist() {
    lastUpdated = new Date();
  }

  public Date datePlaced;

  @Reference
  public CategoryD category;
  @Reference
  public List<TagD> tags;
  @Reference
  public List<FilterD> filters;

  public void syncToThrift(Entry entry){
    entry.id = id.toString();
    entry.accessId = access.id.toString();

    entry.identity = identity;
    entry.title = title;
    entry.description = description;

    entry.author = author.title;
    entry.authorIdentity = author.identity;
    entry.authorPicUrl = author.imageUrl;
    entry.authorScreenName = author.screenName;

    entry.kind = kind;

    entry.isAuthor = isAuthor;
    entry.isPublicAvailable = isPublicAvailable;
    entry.isFavorite = isFavorite;
    entry.isRead = isRead;

    entry.dateCreated = dateCreated.getTime();
    entry.datePlaced = datePlaced.getTime();

    entry.categoryId = category.id.toString();

    entry.tagIds.clear();
    for(TagD tag: tags){
      entry.tagIds.add(tag.id.toString());
    }

    entry.filterIds.clear();
    for(FilterD filter: filters){
      entry.filterIds.add(filter.id.toString());
    }
  }

  public void syncFromThrift(Entry entry){
    identity = entry.getIdentity();
    title = entry.getTitle();
    description = entry.getDescription();

    author = new AuthorD();
    author.title = entry.getAuthor();
    author.identity = entry.getAuthorIdentity();
    author.imageUrl = entry.getAuthorPicUrl();
    author.screenName = entry.getAuthorScreenName();

    kind = entry.getKind();

    isAuthor = entry.isIsAuthor();
    isPublicAvailable = entry.isIsPublicAvailable();
    isFavorite = entry.isIsFavorite();
    isRead = entry.isIsRead();

    datePlaced = new Date(entry.getDatePlaced());
  }
}
