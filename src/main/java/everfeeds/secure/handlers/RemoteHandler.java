package everfeeds.secure.handlers;

import everfeeds.handlers.Handler;
import everfeeds.mongo.FilterD;
import everfeeds.remote.twitter.TwitterRemote;
import everfeeds.secure.thrift.RemoteAPI;
import everfeeds.thrift.domain.Entry;
import everfeeds.thrift.domain.Filter;
import everfeeds.thrift.error.Forbidden;
import everfeeds.thrift.error.NotFound;
import org.apache.thrift.TException;

import java.util.List;

/**
 * @author Dmitry Kurinskiy
 * @since 18.05.11 19:30
 */
public class RemoteHandler extends Handler implements RemoteAPI.Iface{
  @Override
  public List<Entry> pull(Filter filter) throws Forbidden, NotFound, TException {
    FilterD filterD = new FilterD();
    filterD.syncFromThrift(filter);
    filterD = setFilterRelationsFromThrift(filterD, filter);
    return new TwitterRemote().pullToThrift(filterD);
  }

  @Override
  public void saveEntries(Filter filter) throws Forbidden, NotFound, TException {
    FilterD filterD = new FilterD();
    filterD.syncFromThrift(filter);
    filterD = setFilterRelationsFromThrift(filterD, filter);
    new TwitterRemote().saveEntries(filterD);
  }
}
