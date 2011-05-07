package everfeeds.handlers;

import everfeeds.thrift.Access;
import everfeeds.thrift.Entry;
import everfeeds.thrift.EntryAPI;
import everfeeds.thrift.EntryContent;
import org.apache.thrift.TException;

/**
 * @author Dmitry Kurinskiy
 * @since 06.05.11 19:11
 */
public class EntryHandler extends Handler implements EntryAPI.Iface {
  @Override
  public Entry saveEntry(String token, Access access, Entry entry, EntryContent content) throws TException {
    return null;
  }

  @Override
  public EntryContent getEntryContent(String token, String entryId) throws TException {
    return null;
  }

  @Override
  public Entry getEntry(String token, String entryId) throws TException {
    return null;
  }
}
