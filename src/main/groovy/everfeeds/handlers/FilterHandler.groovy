@Typed package everfeeds.handlers;

import com.google.code.morphia.query.Query;
import everfeeds.thrift.util.Scope;
import everfeeds.mongo.*;
import everfeeds.thrift.error.*;
import everfeeds.thrift.domain.Entry;
import everfeeds.thrift.domain.Filter;
import org.apache.thrift.TException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List
import everfeeds.adapters.FilterAdapter;

/**
 * @author Dmitry Kurinskiy
 * @since 06.05.11 19:11
 */
public class FilterHandler extends Handler {

  public Filter saveFilter(String token, Filter filter) throws TException, Forbidden, TokenNotFound, TokenExpired, NotFound {
    checkToken(getTokenD(token), Scope.FEED_WRITE);

    FilterD filterD = getFilterD(token, filter);
    FilterAdapter.setFilterRelationsFromThrift(filterD, filter);

    // Saving filter domain
    filterD.syncFromThrift(filter);
    filterDAO.save(filterD);
    filterD.syncToThrift(filter);

    return filter;
  }


  public List<Entry> getMash(String token, long splitDate, short page, short maxCount) throws TException, TokenNotFound, Forbidden, TokenExpired, WrongArgument {
    TokenD tokenD = getTokenD(token);
    checkToken(tokenD, Scope.FEED_READ);

    if (splitDate < 1) {
      throw new WrongArgument("You must set split date to get entries");
    }
    return domainsToThrift(entryDAO.findAllMash(tokenD.account, splitDate, maxCount, page))
  }

  public List<Entry> getMashNew(String token, long splitDate, short maxCount) throws TException, TokenNotFound, Forbidden, TokenExpired, WrongArgument {
    TokenD tokenD = getTokenD(token);
    checkToken(tokenD, Scope.FEED_READ);

    if (splitDate < 1) {
      throw new WrongArgument("You must set split date to get entries");
    }
    return domainsToThrift(entryDAO.findAllMashNew(tokenD.account, splitDate, maxCount))
  }


  public List<Entry> getFiltered(String token, Filter filter, short page, short maxCount) throws TException, Forbidden, TokenNotFound, TokenExpired, NotFound, WrongArgument {
    checkToken(getTokenD(token), Scope.FEED_READ);

    if (filter.splitDate < 1) {
      throw new WrongArgument("You must set filter split date to get entries");
    }

    FilterD filterD = getFilterD(token, filter);
    FilterAdapter.setFilterRelationsFromThrift(filterD, filter);
    filterD.syncFromThrift(filter);

    return domainsToThrift(entryDAO.findAllFiltered(filterD, page, maxCount))
  }


  public List<Entry> getFilteredNew(String token, Filter filter) throws TException, Forbidden, TokenNotFound, TokenExpired, NotFound, WrongArgument {
    checkToken(getTokenD(token), Scope.FEED_READ);

    if (filter.splitDate < 1) {
      throw new WrongArgument("You must set filter split date to get entries");
    }

    FilterD filterD = getFilterD(token, filter);
    FilterAdapter.setFilterRelationsFromThrift(filterD, filter);
    filterD.syncFromThrift(filter);

    return domainsToThrift(entryDAO.findAllFilteredNew(filterD))
  }

  private List<Entry> domainsToThrift(List<EntryD> entryDs){
    List<Entry> entries = new ArrayList<Entry>();
    for (EntryD eD : entryDs) {
      Entry e = new Entry();
      eD.syncToThrift(e);
      entries.add(e);
    }
    return entries;
  }

  protected FilterD getFilterD(String token, Filter filter) throws TException, Forbidden, TokenNotFound, TokenExpired, NotFound {
    FilterD filterD;
    if (!filter.id.isEmpty()) {
      filterD = filterDAO.getById(filter.id);
      if (filterD == null) {
        throw new NotFound("Filter not found by id");
      }
      if (filterD.access.account.id != getTokenD(token).account.id) {
        throw new Forbidden("Forbidden");
      }
    } else {
      filterD = new FilterD();
      filterD.access = getAccessD(token, filter.accessId);
      if (filterD.access == null) {
        throw new NotFound("Filter access not found by ID");
      }
    }
    return filterD;
  }
}
