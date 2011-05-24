@Typed package everfeeds.mongo;


import everfeeds.thrift.domain.Filter
import everfeeds.thrift.ttype.EntryKind
import everfeeds.thrift.util.Kind
import org.bson.types.ObjectId
import com.google.code.morphia.annotations.*
import com.google.code.morphia.annotations.Reference;

/**
 * @author Dmitry Kurinskiy
 * @since 06.05.11 14:41
 */
@Entity
public class FilterD {
  @Id
  ObjectId id;

  @Version
  Long version;

  @Reference
  AccessD access;

  String title;

  @Reference
  List<CategoryD> categories = new ArrayList<CategoryD>();
  boolean categoriesWith;

  @Reference
  List<TagD> withTags = new ArrayList<TagD>();
  @Reference
  List<TagD> withoutTags = new ArrayList<TagD>();

  List<Kind> kinds = new ArrayList<Kind>();
  boolean kindsWith;

  boolean unreadOnly = false;

  Date dateCreated = new Date();
  Date lastUpdated = new Date();

  @Transient
  Date splitDate = new Date();

  @PrePersist
  void prePersist() {
    lastUpdated = new Date();
  }

  public void syncToThrift(Filter filter) {
    filter.id = id.toString();
    filter.accessId = access.id.toString();
    filter.title = title;
    filter.splitDate = splitDate.getTime();

    filter.unreadOnly = unreadOnly;

    filter.categoryWith = categoriesWith;
    filter.categoryIds.clear();
    for (CategoryD c: categories) {
      filter.categoryIds.add(c.id.toString());
    }

    filter.withTagIds.clear();
    for (TagD t: withTags) {
      filter.withTagIds.add(t.id.toString());
    }
    filter.withoutTagIds.clear();
    for (TagD t: withoutTags) {
      filter.withoutTagIds.add(t.id.toString());
    }

    filter.kinds.clear();
    for (Kind k: kinds) {
      filter.kinds.add(k.toThrift());
    }
    filter.kindsWith = kindsWith;
  }

  public void syncFromThrift(Filter filter) {
    title = filter.title;
    kinds.clear();
    if (filter.kinds != null) for (EntryKind k: filter.kinds) {
      kinds.add(Kind.getByThrift(k));
    }
    kindsWith = filter.kindsWith;
    splitDate = new Date(filter.splitDate);

    categoriesWith = filter.categoryWith;

    unreadOnly = filter.unreadOnly;
  }
}
