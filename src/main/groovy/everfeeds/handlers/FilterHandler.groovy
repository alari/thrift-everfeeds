@Typed package everfeeds.handlers;


import everfeeds.adapters.DomainAdapter
import everfeeds.adapters.FilterAdapter
import everfeeds.mongo.FilterD
import everfeeds.mongo.TokenD
import everfeeds.thrift.domain.Entry
import everfeeds.thrift.domain.Filter
import everfeeds.thrift.util.Scope
import org.apache.thrift.TException
import everfeeds.thrift.error.*

/**
 * @author Dmitry Kurinskiy
 * @since 06.05.11 19:11
 */
public class FilterHandler extends Handler {

  public Filter saveFilter(String token, Filter filter)
      throws TException, Forbidden, TokenNotFound, TokenExpired, NotFound {
    checkToken(getTokenD(token), Scope.FEED_WRITE);

    FilterD filterD = FilterAdapter.setFilterRelationsFromThrift(getFilterD(token, filter), filter);
    // Saving filter domain
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
    return DomainAdapter.domainsToThrift(entryDAO.findAllMash(tokenD.account, splitDate, maxCount, page))
  }

  public List<Entry> getMashNew(String token, long splitDate, short maxCount) throws TException, TokenNotFound, Forbidden, TokenExpired, WrongArgument {
    TokenD tokenD = getTokenD(token);
    checkToken(tokenD, Scope.FEED_READ);

    if (splitDate < 1) {
      throw new WrongArgument("You must set split date to get entries");
    }
    return DomainAdapter.domainsToThrift(entryDAO.findAllMashNew(tokenD.account, splitDate, maxCount))
  }


  public List<Entry> getFiltered(String token, Filter filter, short page, short maxCount) throws TException, Forbidden, TokenNotFound, TokenExpired, NotFound, WrongArgument {
    checkToken(getTokenD(token), Scope.FEED_READ);

    if (filter.splitDate < 1) {
      throw new WrongArgument("You must set filter split date to get entries");
    }

    FilterD filterD = FilterAdapter.setFilterRelationsFromThrift(getFilterD(token, filter), filter);

    return DomainAdapter.domainsToThrift(entryDAO.findAllFiltered(filterD, page, maxCount))
  }


  public getFilteredNew = {String token, Filter filter ->
    checkToken(getTokenD(token), Scope.FEED_READ);

    if (filter.splitDate < 1) {
      throw new WrongArgument("You must set filter split date to get entries");
    }

    FilterD filterD = FilterAdapter.setFilterRelationsFromThrift(getFilterD(token, filter), filter);

    return DomainAdapter.domainsToThrift(entryDAO.findAllFilteredNew(filterD))
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
