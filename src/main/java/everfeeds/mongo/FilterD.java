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
public class FilterD {
  @Id
  public ObjectId id;

  @Version
  public Long version;

  @Reference
  public AccessD access;

  public String title;

  @Reference
  public List<CategoryD> categories;
  public boolean categoriesWith;

  @Reference
  public List<TagD> withTags;
  @Reference
  public List<TagD> withoutTags;

  public List<EntryKind> kinds;
  public boolean kindsWith;

  public Date dateCreated = new Date();
  public Date lastUpdated = new Date();

  @PrePersist
  void prePersist() {
    lastUpdated = new Date();
  }
}
