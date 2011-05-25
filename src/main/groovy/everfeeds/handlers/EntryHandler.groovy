@Typed package everfeeds.handlers;

import everfeeds.thrift.util.Scope;
import everfeeds.mongo.*;
import everfeeds.thrift.domain.Entry;
import everfeeds.thrift.domain.EntryContent;
import everfeeds.thrift.error.Forbidden;
import everfeeds.thrift.error.NotFound;
import everfeeds.thrift.error.TokenExpired;
import everfeeds.thrift.error.TokenNotFound;
import org.apache.thrift.TException
import everfeeds.dao.TagDAO;

/**
 * @author Dmitry Kurinskiy
 * @since 06.05.11 19:11
 */
public class EntryHandler extends Handler {
  public Entry saveEntry(String token, Entry entry, EntryContent content) throws TException, TokenNotFound, Forbidden, TokenExpired, NotFound {
    TokenD tokenD = getTokenD(token);
    checkToken(tokenD, Scope.FEED_WRITE);

    AccessD accessD = accessDAO.getById(entry.accessId);
    if (accessD == null || accessD.account.id != tokenD.account.id) {
      throw new Forbidden("Wrong Access ID in entry");
    }

    EntryD entryD = entryDAO.getByThrift(entry, accessD);

      if (entry.id && entryD == null) {
        throw new NotFound("Entry not found by id");
      }

    if (entryD == null) {
      entryD = new EntryD();
      entryD.content = new EntryContentD();
    }

    CategoryD categoryD = categoryDAO.getByIdAndAccess(entry.categoryId, accessD);
    if (categoryD == null) {
      throw new NotFound("Category not found or is not set.");
    }
    entryD.category = categoryD;

    entryD.tags.clear();
    tagDAO.findAllByAccess(accessD).each{TagD t->
      if (entry.tagIds.contains(t.id.toString())) {
        entryD.tags.add(t);
      }
    }

    entryD.content.syncFromThrift(content);
    entryContentDAO.save(entryD.content);

    entryD.access = accessD;
    entryD.account = tokenD.account;
    entryD.syncFromThrift(entry);

    entryDAO.save(entryD);

    entryD.syncToThrift(entry);

    return entry;
  }


  public EntryContent getEntryContent(String token, String entryId) throws TException, Forbidden, TokenNotFound, TokenExpired, NotFound {
    checkToken(getTokenD(token), Scope.FEED_READ);
    EntryD entryD = getEntryD(token, entryId);

    EntryContent content = new EntryContent();
    entryD.content.syncToThrift(content);
    content.entryId = entryId;
    return content;
  }


  public Entry getEntry(String token, String entryId) throws TException, Forbidden, TokenNotFound, TokenExpired, NotFound {
    checkToken(getTokenD(token), Scope.FEED_READ);
    EntryD entryD = getEntryD(token, entryId);
    Entry entry = new Entry();
    entryD.syncToThrift(entry);
    return entry;
  }


  public void markEntryRead(String token, String entryId) throws TException, Forbidden, TokenNotFound, TokenExpired, NotFound {
    checkToken(getTokenD(token), Scope.FEED_READ);
    EntryD entryD = getEntryD(token, entryId);
    entryD.isRead = true;
    getDS().save(entryD);
  }

  public void markEntryUnread(String token, String entryId) throws TException, Forbidden, TokenNotFound, TokenExpired, NotFound {
    checkToken(getTokenD(token), Scope.FEED_READ);
    EntryD entryD = getEntryD(token, entryId);
    entryD.isRead = true;
    getDS().save(entryD);
  }

  EntryD getEntryD(String token, String id) throws TException, Forbidden, TokenNotFound, TokenExpired, NotFound {
    checkToken(getTokenD(token), Scope.FEED_READ);
    TokenD tokenD = getTokenD(token);
    EntryD entryD = entryDAO.getById(id);

    if (entryD == null) {
      throw new NotFound("Entry not found for id");
    }
    if(entryD.account.id != tokenD.account.id) {
      throw new Forbidden("Forbidden")
    }

    return entryD;
  }
}
