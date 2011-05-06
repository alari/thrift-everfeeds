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
  @Reference
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
}
