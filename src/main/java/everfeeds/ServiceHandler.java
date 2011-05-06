package everfeeds;

import everfeeds.thrift.*;
import org.apache.thrift.TException;

import java.util.List;

/**
 * @author Dmitry Kurinskiy
 * @since 06.05.11 14:17
 */
public class ServiceHandler implements EverfeedsAPI.Iface {
  @Override
  public Access createAccess(String token, Access access) throws TException {
    return null;
  }

  @Override
  public Account createAccessAndAccount(String token, Access access) throws TException {
    return null;
  }

  @Override
  public Tag createTag(String token, Access access, Tag tag) throws TException {
    return null;
  }

  @Override
  public Category createCategory(String token, Access access, Category category) throws TException {
    return null;
  }

  @Override
  public Entry createEntry(String token, Access access, Entry entry, EntryContent content) throws TException {
    return null;
  }

  @Override
  public Filter createFilter(String token, Filter filter) throws TException {
    return null;
  }

  @Override
  public Account getAccount(String token) throws TException {
    return null;
  }

  @Override
  public List<Access> getAccesses(String token) throws TException {
    return null;
  }

  @Override
  public List<Tag> getTags(String token, String accessId) throws TException {
    return null;
  }

  @Override
  public List<Category> getCategories(String token, String accessId) throws TException {
    return null;
  }

  @Override
  public List<EntryKind> getKinds(String token, String accessId) throws TException {
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

  @Override
  public EntryContent getEntryContent(String token, String entryId) throws TException {
    return null;
  }
}
