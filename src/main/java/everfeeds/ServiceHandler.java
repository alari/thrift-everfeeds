package everfeeds;

import everfeeds.thrift.Entry;
import everfeeds.thrift.EntryContent;
import everfeeds.thrift.EverfeedsSrv;
import everfeeds.thrift.Filter;
import org.apache.thrift.TException;

import java.util.List;

/**
 * @author Dmitry Kurinskiy
 * @since 06.05.11 14:17
 */
public class ServiceHandler implements EverfeedsSrv.Iface {
  @Override
  public EntryContent getEntryContent(String accessToken, String entryId) throws TException {
    return null;
  }

  @Override
  public List<Entry> getMash(String accessToken, short page, short maxCount) throws TException {
    return null;
  }

  @Override
  public List<Entry> getMashNew(String accessToken, long splitDate) throws TException {
    return null;
  }

  @Override
  public List<Entry> getFiltered(String accessToken, Filter filter, short page, short maxCount) throws TException {
    return null;
  }

  @Override
  public List<Entry> getFilteredNew(String accessToken, Filter filter) throws TException {
    return null;
  }
}
