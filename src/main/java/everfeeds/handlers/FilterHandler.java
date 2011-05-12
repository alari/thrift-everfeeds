package everfeeds.handlers;

import com.google.code.morphia.query.Query;
import everfeeds.mongo.CategoryD;
import everfeeds.mongo.EntryD;
import everfeeds.mongo.FilterD;
import everfeeds.mongo.TagD;
import everfeeds.thrift.Entry;
import everfeeds.thrift.Filter;
import everfeeds.thrift.FilterAPI;
import org.apache.thrift.TException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Dmitry Kurinskiy
 * @since 06.05.11 19:11
 */
public class FilterHandler extends Handler implements FilterAPI.Iface {
  @Override
  public Filter saveFilter(String token, Filter filter) throws TException {

    FilterD filterD = getFilterD(token, filter);
    setRelsFromThrift(filterD, filter);

    // Saving filter domain
    filterD.syncFromThrift(filter);
    getDS().save(filterD);
    filterD.syncToThrift(filter);

    return filter;
  }

  @Override
  public List<Entry> getMash(String token, long splitDate, short page, short maxCount) throws TException {
    return getEntriesForQuery(getDS().createQuery(EntryD.class)
                                  .filter("account", getTokenD(token).account)
                                  .filter("dateCreated <", new Date(splitDate))
                                  .limit(maxCount).offset(page * maxCount));
  }

  @Override
  public List<Entry> getMashNew(String token, long splitDate, short maxCount) throws TException {
    return getEntriesForQuery(getDS().createQuery(EntryD.class)
                                  .filter("account", getTokenD(token).account)
                                  .filter("dateCreated >", new Date(splitDate))
                                  .limit(maxCount));
  }

  @Override
  public List<Entry> getFiltered(String token, Filter filter, short page, short maxCount) throws TException {
    if (filter.splitDate < 1) {
      throw new TException("You must set filter split date to get entries");
    }

    FilterD filterD = getFilterD(token, filter);
    setRelsFromThrift(filterD, filter);
    filterD.syncFromThrift(filter);

    Query<EntryD> query = getDS().createQuery(EntryD.class);

    query = applyFilter(query, filterD);

    return getEntriesForQuery(query.filter("dateCreated <", new Date(filter.splitDate)));
  }

  @Override
  public List<Entry> getFilteredNew(String token, Filter filter) throws TException {
    if (filter.splitDate < 1) {
      throw new TException("You must set filter split date to get entries");
    }

    FilterD filterD = getFilterD(token, filter);
    setRelsFromThrift(filterD, filter);
    filterD.syncFromThrift(filter);

    Query<EntryD> query = getDS().createQuery(EntryD.class);

    query = applyFilter(query, filterD);

    return getEntriesForQuery(query.filter("dateCreated >", new Date(filter.splitDate)));
  }

  Query<EntryD> applyFilter(Query<EntryD> query, FilterD filterD) {
    query.filter("access", filterD.access);
    if (filterD.categories.size() > 0) {
      if (filterD.categoriesWith) {
        query.filter("category in", filterD.categories);
      } else {
        query.filter("category nin", filterD.categories);
      }
    }
    if (filterD.withTags.size() > 0) {
      query.filter("tags all", filterD.withTags);
    }
    if (filterD.withoutTags.size() > 0) {
      query.filter("tags nin", filterD.withoutTags);
    }
    if (filterD.kinds.size() > 0) {
      if (filterD.kindsWith) {
        query.filter("kind in", filterD.kinds);
      } else {
        query.filter("kind nin", filterD.kinds);
      }
    }

    return query;
  }

  protected List<Entry> getEntriesForQuery(Query<EntryD> query) {
    return getEntriesForDomains(query.order("-datePlaced").asList());
  }

  protected List<Entry> getEntriesForDomains(List<EntryD> entryDs) {
    List<Entry> entries = new ArrayList<Entry>();
    for (EntryD eD : entryDs) {
      Entry e = new Entry();
      eD.syncToThrift(e);
      entries.add(e);
    }
    return entries;
  }

  protected FilterD getFilterD(String token, Filter filter) throws TException {
    FilterD filterD;
    if (!filter.id.isEmpty()) {
      filterD = getDS().get(FilterD.class, filter.id);
      if (filterD == null) {
        throw new TException("Filter not found by id");
      }
      if (filterD.access.account != getTokenD(token).account) {
        throw new TException("Forbidden");
      }
    } else {
      filterD = new FilterD();
      filterD.access = getAccessD(token, filter.accessId);
      if (filterD.access == null) {
        throw new TException("Filter access not found by ID");
      }
    }
    return filterD;
  }

  protected FilterD setRelsFromThrift(FilterD filterD, Filter filter) {
    // Syncing categories and tags
    filterD.categories.clear();
    filterD.withoutTags.clear();
    filterD.withTags.clear();

    List<CategoryD> categories = getDS().createQuery(CategoryD.class)
                                     .filter("access", filterD.access)
                                     .asList();
    List<TagD> tags = getDS().createQuery(TagD.class)
                          .filter("access", filterD.access)
                          .asList();

    for (CategoryD c : categories) {
      if (filter.categoryIds.contains(c.id.toString())) {
        filterD.categories.add(c);
      }
    }

    for (TagD t : tags) {
      if (filter.withoutTagIds.contains(t.id.toString())) {
        filterD.withoutTags.add(t);
      } else if (filter.withTagIds.contains(t.id.toString())) {
        filterD.withTags.add(t);
      }
    }

    return filterD;
  }
}
