@Typed package everfeeds.handlers;

import everfeeds.thrift.EverfeedsAPI;
import everfeeds.thrift.domain.*;
import everfeeds.thrift.error.*;
import everfeeds.thrift.ttype.EntryKind;
import org.apache.thrift.TException;

import java.util.List
import everfeeds.thrift.domain.Category
import everfeeds.thrift.ttype.AccessTypeInfo
import everfeeds.thrift.ttype.AccessType
import everfeeds.remote.TypeInfo;

/**
 * @author Dmitry Kurinskiy
 * @since 19.05.11 11:39
 */
public class EverfeedsHandler implements EverfeedsAPI.Iface{
  private AccessHandler accessHandler = new AccessHandler();
  private AccountHandler accountHandler = new AccountHandler();
  private EntryHandler entryHandler = new EntryHandler();
  private FilterHandler filterHandler = new FilterHandler();

  @Override
  public Entry saveEntry(String token, Entry entry, EntryContent content) throws Forbidden, TokenExpired, TokenNotFound, NotFound, TException {
    return entryHandler.saveEntry(token, entry, content);
  }

  @Override
  public EntryContent getEntryContent(String token, String entryId) throws Forbidden, TokenExpired, TokenNotFound, NotFound, TException {
    return entryHandler.getEntryContent(token, entryId);
  }

  @Override
  public Entry getEntry(String token, String entryId) throws Forbidden, TokenExpired, TokenNotFound, NotFound, TException {
    return entryHandler.getEntry(token, entryId);
  }

  @Override
  public void markEntryRead(String token, String entryId) throws Forbidden, TokenExpired, TokenNotFound, NotFound, TException {
    entryHandler.markEntryRead(token, entryId);
  }

  @Override
  public void markEntryUnread(String token, String entryId) throws Forbidden, TokenExpired, TokenNotFound, NotFound, TException {
    entryHandler.markEntryUnread(token, entryId);
  }

  @Override
  public Account getAccount(String token) throws Forbidden, TokenExpired, TokenNotFound, NotFound, TException {
    return accountHandler.getAccount(token);
  }

  @Override
  public List<Access> getAccesses(String token) throws Forbidden, TokenExpired, TokenNotFound, NotFound, TException {
    return accountHandler.getAccesses(token);
  }

  @Override
  public Access saveAccess(String token, Access access) throws Forbidden, TokenExpired, TokenNotFound, NotFound, TException {
    return accountHandler.saveAccess(token, access);
  }

  @Override
  public Account saveAccount(String token, Account account) throws Forbidden, TokenExpired, TokenNotFound, NotFound, TException {
    return accountHandler.saveAccount(token, account);
  }

  @Override
  public List<Tag> getTags(String token, String accessId) throws Forbidden, TokenExpired, TokenNotFound, NotFound, TException {
    return accessHandler.getTags(token, accessId);
  }

  @Override
  public List<Category> getCategories(String token, String accessId) throws Forbidden, TokenExpired, TokenNotFound, NotFound, TException {
    return accessHandler.getCategories(token, accessId);
  }

  @Override
  public List<EntryKind> getKinds(String token, String accessId) throws Forbidden, TokenExpired, TokenNotFound, NotFound, TException {
    return accessHandler.getKinds(token, accessId);
  }

  @Override
  public Tag saveTag(String token, Tag tag) throws Forbidden, TokenExpired, TokenNotFound, NotFound, TException {
    return accessHandler.saveTag(token, tag);
  }

  @Override
  public Category saveCategory(String token, Category category) throws Forbidden, TokenExpired, TokenNotFound, NotFound, TException {
    return accessHandler.saveCategory(token, category);
  }

  @Override
  public Filter saveFilter(String token, Filter filter) throws Forbidden, TokenExpired, TokenNotFound, NotFound, TException {
    return filterHandler.saveFilter(token, filter);
  }

  @Override
  public List<Entry> getMash(String token, long splitDate, short page, short maxCount) throws Forbidden, TokenExpired, TokenNotFound, NotFound, WrongArgument, TException {
    return filterHandler.getMash(token, splitDate, page, maxCount);
  }

  @Override
  public List<Entry> getMashNew(String token, long splitDate, short maxCount) throws Forbidden, TokenExpired, TokenNotFound, NotFound, WrongArgument, TException {
    return filterHandler.getMashNew(token, splitDate, maxCount);
  }

  @Override
  public List<Entry> getFiltered(String token, Filter filter, short page, short maxCount) throws Forbidden, TokenExpired, TokenNotFound, NotFound, WrongArgument, TException {
    return filterHandler.getFiltered(token, filter, page, maxCount);
  }

  @Override
  public List<Entry> getFilteredNew(String token, Filter filter) throws Forbidden, TokenExpired, TokenNotFound, NotFound, WrongArgument, TException {
    return filterHandler.getFilteredNew(token, filter);
  }

  AccessTypeInfo getAccessTypeInfo(AccessType type) {
    TypeInfo.getInfo(type)
  }
}
