include "entry.thrift"
include "filter.thrift"
include "misc.thrift"
include "t.thrift"
include "enm.thrift"
include "e.thrift"

namespace cpp everfeeds.thrift
namespace java everfeeds.thrift
namespace php everfeeds.thrift
namespace perl everfeeds.thrift
namespace st Thrift.Everfeeds

service EverfeedsAPI {
// ENTRY DOMAIN
  entry.Entry saveEntry(1: t.String token, 3: entry.Entry entry, 4: entry.EntryContent content) throws(1: e.Forbidden eF, 2: e.TokenExpired eE, 3: e.TokenNotFound eTNF, 4: e.NotFound eNF);

  // to dive into entry contents
  entry.EntryContent getEntryContent(1: t.String token, 5: t.Id entryId) throws(1: e.Forbidden eF, 2: e.TokenExpired eE, 3: e.TokenNotFound eTNF, 4: e.NotFound eNF);
  entry.Entry getEntry(1: t.String token, 5: t.Id entryId) throws(1: e.Forbidden eF, 2: e.TokenExpired eE, 3: e.TokenNotFound eTNF, 4: e.NotFound eNF);

  void markEntryRead(1: t.String token, 2: t.Id entryId) throws(1: e.Forbidden eF, 2: e.TokenExpired eE, 3: e.TokenNotFound eTNF, 4: e.NotFound eNF);
  void markEntryUnread(1: t.String token, 2: t.Id entryId) throws(1: e.Forbidden eF, 2: e.TokenExpired eE, 3: e.TokenNotFound eTNF, 4: e.NotFound eNF);
// END ENTRY DOMAIN

  // to discover basic information about current account
  misc.Account getAccount(1: t.String token) throws(1: e.Forbidden eF, 2: e.TokenExpired eE, 3: e.TokenNotFound eTNF, 4: e.NotFound eNF);
  list<misc.Access> getAccesses(1: t.String token) throws(1: e.Forbidden eF, 2: e.TokenExpired eE, 3: e.TokenNotFound eTNF, 4: e.NotFound eNF);

  // to create everything
  misc.Access saveAccess(1: t.String token, 2: misc.Access access) throws(1: e.Forbidden eF, 2: e.TokenExpired eE, 3: e.TokenNotFound eTNF, 4: e.NotFound eNF);

  misc.Account saveAccount(1: t.String token, 2: misc.Account account) throws(1: e.Forbidden eF, 2: e.TokenExpired eE, 3: e.TokenNotFound eTNF, 4: e.NotFound eNF);

// entries systematization
  list<misc.Tag> getTags(1: t.String token, 5: t.Id accessId) throws(1: e.Forbidden eF, 2: e.TokenExpired eE, 3: e.TokenNotFound eTNF, 4: e.NotFound eNF);
  list<misc.Category> getCategories(1: t.String token, 5: t.Id accessId) throws(1: e.Forbidden eF, 2: e.TokenExpired eE, 3: e.TokenNotFound eTNF, 4: e.NotFound eNF);
  list<enm.EntryKind> getKinds(1: t.String token, 5: t.Id accessId) throws(1: e.Forbidden eF, 2: e.TokenExpired eE, 3: e.TokenNotFound eTNF, 4: e.NotFound eNF);

  misc.Tag saveTag(1: t.String token, 3: misc.Tag tag) throws(1: e.Forbidden eF, 2: e.TokenExpired eE, 3: e.TokenNotFound eTNF, 4: e.NotFound eNF);
  misc.Category saveCategory(1: t.String token, 3: misc.Category category) throws(1: e.Forbidden eF, 2: e.TokenExpired eE, 3: e.TokenNotFound eTNF, 4: e.NotFound eNF);

// filtering
  filter.Filter saveFilter(1: t.String token, 2: filter.Filter filter) throws(1: e.Forbidden eF, 2: e.TokenExpired eE, 3: e.TokenNotFound eTNF, 4: e.NotFound eNF);

  // to get simple mash of entries
  list<entry.Entry> getMash(1: t.String token, 10: t.Timestamp splitDate, 20: i16 page, 30: i16 maxCount) throws(1: e.Forbidden eF, 2: e.TokenExpired eE, 3: e.TokenNotFound eTNF, 4: e.NotFound eNF, 5: e.WrongArgument e);
  list<entry.Entry> getMashNew(1: t.String token, 10: t.Timestamp splitDate, 20: i16 maxCount) throws(1: e.Forbidden eF, 2: e.TokenExpired eE, 3: e.TokenNotFound eTNF, 4: e.NotFound eNF, 5: e.WrongArgument e);

  // to filter entries
  list<entry.Entry> getFiltered(1: t.String token, 2: filter.Filter filter, 3: i16 page, 4: i16 maxCount) throws(1: e.Forbidden eF, 2: e.TokenExpired eE, 3: e.TokenNotFound eTNF, 4: e.NotFound eNF, 5: e.WrongArgument e);
  list<entry.Entry> getFilteredNew(1: t.String token, 2: filter.Filter filter) throws(1: e.Forbidden eF, 2: e.TokenExpired eE, 3: e.TokenNotFound eTNF, 4: e.NotFound eNF, 5: e.WrongArgument e);
}