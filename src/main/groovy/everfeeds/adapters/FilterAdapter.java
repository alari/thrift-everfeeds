package everfeeds.adapters;

import com.google.code.morphia.Datastore;
import everfeeds.MongoDB;
import everfeeds.dao.CategoryDAO;
import everfeeds.dao.TagDAO;
import everfeeds.mongo.CategoryD;
import everfeeds.mongo.FilterD;
import everfeeds.mongo.TagD;
import everfeeds.thrift.domain.Filter;

import java.util.List;

/**
 * @author Boris G. Tsirkin<mail@dotbg.name>
 * @since 29 May 2011
 */
public class FilterAdapter {
  protected static Datastore getDS() {
    return MongoDB.getDS();
  }

  public static FilterD setFilterRelationsFromThrift(FilterD filterD, Filter filter) {
    // Syncing categories and tags
    filterD.getCategories().clear();
    filterD.getWithoutTags().clear();
    filterD.getWithTags().clear();

    if (filter.categoryIds != null && filter.categoryIds.size() > 0) {
      for (final CategoryD c : CategoryDAO.getInstance().findAllByAccess(filterD.getAccess())) {
        if (filter.categoryIds.contains(c.getId().toString())) {
          filterD.getCategories().add(c);
        }
      }
    }

    if (filter.withTagIds != null || filter.withTagIds != null) {
      for (TagD t : TagDAO.getInstance().findAllByAccess(filterD.getAccess())) {
        if (filter.withoutTagIds != null && filter.withoutTagIds.contains(t.getId().toString())) {
          filterD.getWithoutTags().add(t);
        } else if (filter.withTagIds != null && filter.withTagIds.contains(t.getId().toString())) {
          filterD.getWithTags().add(t);
        }
      }
    }

    return filterD;
  }
}
