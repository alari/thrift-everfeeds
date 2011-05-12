include "entry.thrift"
include "filter.thrift"
include "misc.thrift"
include "t.thrift"
include "enm.thrift"
include "e.thrift"

namespace cpp everfeeds.thrift.service
namespace java everfeeds.thrift.service
namespace php everfeeds.thrift.service
namespace perl everfeeds.thrift.service
namespace st Thrift.Everfeeds.Service

service ApplicationAPI {
  misc.Token createToken(1: t.String applicationSecret, 2: t.Id accountId) throws (1: e.Forbidden e, 2: e.NotFound e);

  misc.Account createAccessAndAccount(1: t.String applicationSecret, 2: misc.Access access, 3: t.String accessToken, 4: t.String accessSecret, 5: t.String accessShardId) throws(1: e.Forbidden e, 2: e.NotFound e);
}

service EntryAPI {
  entry.Entry saveEntry(1: t.String token, 3: entry.Entry entry, 4: entry.EntryContent content) throws(1: e.Forbidden e, 2: e.TokenExpired e, 3: e.TokenNotFound e, 4: e.NotFound e);

  // to dive into entry contents
  entry.EntryContent getEntryContent(1: t.String token, 5: t.Id entryId) throws(1: e.Forbidden e, 2: e.TokenExpired e, 3: e.TokenNotFound e, 4: e.NotFound e);
  entry.Entry getEntry(1: t.String token, 5: t.Id entryId) throws(1: e.Forbidden e, 2: e.TokenExpired e, 3: e.TokenNotFound e, 4: e.NotFound e);

  void markRead(1: t.String token, 2: t.Id entryId) throws(1: e.Forbidden e, 2: e.TokenExpired e, 3: e.TokenNotFound e, 4: e.NotFound e);
  void markUnread(1: t.String token, 2: t.Id entryId) throws(1: e.Forbidden e, 2: e.TokenExpired e, 3: e.TokenNotFound e, 4: e.NotFound e);
}

service AccountAPI extends ApplicationAPI {
  // to discover basic information about current account
  misc.Account getAccount(1: t.String token) throws(1: e.Forbidden e, 2: e.TokenExpired e, 3: e.TokenNotFound e, 4: e.NotFound e);
  list<misc.Access> getAccesses(1: t.String token) throws(1: e.Forbidden e, 2: e.TokenExpired e, 3: e.TokenNotFound e, 4: e.NotFound e);

  // to create everything
  misc.Access saveAccessToken(1: t.String token, 2: misc.Access access, 3: t.String accessToken, 4: t.String accessSecret, 5: t.String accessShardId) throws(1: e.Forbidden e, 2: e.TokenExpired e, 3: e.TokenNotFound e, 4: e.NotFound e);
  misc.Access saveAccess(1: t.String token, 2: misc.Access access) throws(1: e.Forbidden e, 2: e.TokenExpired e, 3: e.TokenNotFound e, 4: e.NotFound e);

  misc.Account saveAccount(1: t.String token, 2: misc.Account account) throws(1: e.Forbidden e, 2: e.TokenExpired e, 3: e.TokenNotFound e, 4: e.NotFound e);
}

service AccessAPI extends AccountAPI {
  // to get access info
  list<misc.Tag> getTags(1: t.String token, 5: t.Id accessId) throws(1: e.Forbidden e, 2: e.TokenExpired e, 3: e.TokenNotFound e, 4: e.NotFound e);
  list<misc.Category> getCategories(1: t.String token, 5: t.Id accessId) throws(1: e.Forbidden e, 2: e.TokenExpired e, 3: e.TokenNotFound e, 4: e.NotFound e);
  list<enm.EntryKind> getKinds(1: t.String token, 5: t.Id accessId) throws(1: e.Forbidden e, 2: e.TokenExpired e, 3: e.TokenNotFound e, 4: e.NotFound e);
  misc.Tag saveTag(1: t.String token, 3: misc.Tag tag) throws(1: e.Forbidden e, 2: e.TokenExpired e, 3: e.TokenNotFound e, 4: e.NotFound e);
  misc.Category saveCategory(1: t.String token, 3: misc.Category category) throws(1: e.Forbidden e, 2: e.TokenExpired e, 3: e.TokenNotFound e, 4: e.NotFound e);
}

service FilterAPI {
  filter.Filter saveFilter(1: t.String token, 2: filter.Filter filter) throws(1: e.Forbidden e, 2: e.TokenExpired e, 3: e.TokenNotFound e, 4: e.NotFound e);

  // to get simple mash of entries
  list<entry.Entry> getMash(1: t.String token, 10: t.Timestamp splitDate, 20: i16 page, 30: i16 maxCount) throws(1: e.Forbidden e, 2: e.TokenExpired e, 3: e.TokenNotFound e, 4: e.NotFound e, 5: e.WrongArgument e);
  list<entry.Entry> getMashNew(1: t.String token, 10: t.Timestamp splitDate, 20: i16 maxCount) throws(1: e.Forbidden e, 2: e.TokenExpired e, 3: e.TokenNotFound e, 4: e.NotFound e, 5: e.WrongArgument e);

  // to filter entries
  list<entry.Entry> getFiltered(1: t.String token, 2: filter.Filter filter, 3: i16 page, 4: i16 maxCount) throws(1: e.Forbidden e, 2: e.TokenExpired e, 3: e.TokenNotFound e, 4: e.NotFound e, 5: e.WrongArgument e);
  list<entry.Entry> getFilteredNew(1: t.String token, 2: filter.Filter filter) throws(1: e.Forbidden e, 2: e.TokenExpired e, 3: e.TokenNotFound e, 4: e.NotFound e, 5: e.WrongArgument e);
}