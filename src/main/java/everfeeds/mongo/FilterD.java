package everfeeds.mongo;

import com.google.code.morphia.annotations.*;
import everfeeds.thrift.EntryKind;
import everfeeds.thrift.Filter;
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

  public void syncToThrift(Filter filter) {
    filter.id = id.toString();
    filter.accessId = access.id.toString();
    filter.title = title;

    filter.categoryWith = categoriesWith;
    filter.categoryIds.clear();
    for (CategoryD c : categories) {
      filter.categoryIds.add(c.id.toString());
    }

    filter.withTagIds.clear();
    for (TagD t : withTags) {
      filter.withTagIds.add(t.id.toString());
    }
    filter.withoutTagIds.clear();
    for (TagD t : withoutTags) {
      filter.withoutTagIds.add(t.id.toString());
    }

    filter.kinds = kinds;
    filter.kindsWith = kindsWith;
  }

  public void syncFromThrift(Filter filter) {
    title = filter.title;
    kinds = filter.kinds;
    kindsWith = filter.kindsWith;

    categoriesWith = filter.categoryWith;
  }
}
