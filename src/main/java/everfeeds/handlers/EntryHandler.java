package everfeeds.handlers;

import everfeeds.thrift.util.Scope;
import everfeeds.mongo.*;
import everfeeds.thrift.domain.Entry;
import everfeeds.thrift.domain.EntryContent;
import everfeeds.thrift.error.Forbidden;
import everfeeds.thrift.error.NotFound;
import everfeeds.thrift.error.TokenExpired;
import everfeeds.thrift.error.TokenNotFound;
import everfeeds.thrift.service.EntryAPI;
import org.apache.thrift.TException;

/**
 * @author Dmitry Kurinskiy
 * @since 06.05.11 19:11
 */
public class EntryHandler extends Handler implements EntryAPI.Iface {
  @Override
  public Entry saveEntry(String token, Entry entry, EntryContent content) throws TException, TokenNotFound, Forbidden, TokenExpired, NotFound {
    TokenD tokenD = getTokenD(token);
    checkToken(tokenD, Scope.FEED_WRITE);

    AccessD accessD = getDS().createQuery(AccessD.class).filter("id", entry.accessId).get();
    if (accessD == null || accessD.account != tokenD.account) {
      throw new Forbidden("Wrong Access ID in entry");
    }

    EntryD entryD;

    if (!entry.id.isEmpty()) {
      entryD = getDS().get(EntryD.class, entry.id);
      if (entryD == null) {
        throw new NotFound("Entry not found by id");
      }
    } else {
      entryD = getDS().createQuery(EntryD.class).filter("identity", entry.identity).filter("access", accessD).get();
    }

    if (entryD == null) {
      entryD = new EntryD();
      entryD.content = new EntryContentD();
    }

    CategoryD categoryD = getDS().createQuery(CategoryD.class).filter("id", entry.categoryId).filter("access", accessD).get();
    if (categoryD == null) {
      throw new NotFound("Category not found or is not set.");
    }
    entryD.category = categoryD;

    entryD.tags.clear();
    for (TagD t : getDS().createQuery(TagD.class).filter("access", accessD).fetch()) {
      if (entry.tagIds.contains(t.id.toString())) {
        entryD.tags.add(t);
      }
    }

    entryD.content.syncFromThrift(content);
    getDS().save(entryD.content);

    entryD.access = accessD;
    entryD.account = tokenD.account;
    entryD.syncFromThrift(entry);

    getDS().save(entryD);

    entryD.syncToThrift(entry);

    return entry;
  }

  @Override
  public EntryContent getEntryContent(String token, String entryId) throws TException, Forbidden, TokenNotFound, TokenExpired, NotFound {
    checkToken(getTokenD(token), Scope.FEED_READ);
    EntryD entryD = getEntryD(token, entryId);

    EntryContent content = new EntryContent();
    entryD.content.syncToThrift(content);
    content.entryId = entryId;
    return content;
  }

  @Override
  public Entry getEntry(String token, String entryId) throws TException, Forbidden, TokenNotFound, TokenExpired, NotFound {
    checkToken(getTokenD(token), Scope.FEED_READ);
    EntryD entryD = getEntryD(token, entryId);
    Entry entry = new Entry();
    entryD.syncToThrift(entry);
    return entry;
  }

  @Override
  public void markRead(String token, String entryId) throws TException, Forbidden, TokenNotFound, TokenExpired, NotFound {
    checkToken(getTokenD(token), Scope.FEED_READ);
    EntryD entryD = getEntryD(token, entryId);
    entryD.isRead = true;
    getDS().save(entryD);
  }

  @Override
  public void markUnread(String token, String entryId) throws TException, Forbidden, TokenNotFound, TokenExpired, NotFound {
    checkToken(getTokenD(token), Scope.FEED_READ);
    EntryD entryD = getEntryD(token, entryId);
    entryD.isRead = true;
    getDS().save(entryD);
  }

  EntryD getEntryD(String token, String id) throws TException, Forbidden, TokenNotFound, TokenExpired, NotFound {
    checkToken(getTokenD(token), Scope.FEED_READ);
    TokenD tokenD = getTokenD(token);
    EntryD entryD = getDS().createQuery(EntryD.class).filter("account", tokenD.account).filter("id", id).get();

    if (entryD == null) {
      throw new NotFound("Entry not found for id");
    }

    return entryD;
  }
}
