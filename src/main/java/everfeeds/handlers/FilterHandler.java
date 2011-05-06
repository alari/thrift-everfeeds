package everfeeds.handlers;

import everfeeds.thrift.Entry;
import everfeeds.thrift.Filter;
import everfeeds.thrift.FilterAPI;
import org.apache.thrift.TException;

import java.util.List;

/**
 * @author Dmitry Kurinskiy
 * @since 06.05.11 19:11
 */
public class FilterHandler extends Handler implements FilterAPI.Iface {
  @Override
  public Filter saveFilter(String token, Filter filter) throws TException {
    return null;
  }

  @Override
  public List<Entry> getMash(String token, short page, short maxCount) throws TException {
    return null;
  }

  @Override
  public List<Entry> getMashNew(String token, long splitDate, short maxCount) throws TException {
    return null;
  }

  @Override
  public List<Entry> getFiltered(String token, Filter filter, short page, short maxCount) throws TException {
    return null;
  }

  @Override
  public List<Entry> getFilteredNew(String token, Filter filter) throws TException {
    return null;
  }
}
