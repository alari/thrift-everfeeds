include "entry.thrift"
include "filter.thrift"
include "misc.thrift"
include "t.thrift"
include "enm.thrift"

namespace cpp everfeeds.thrift
namespace java everfeeds.thrift
namespace php everfeeds.thrift
namespace perl everfeeds.thrift
namespace st Thrift.Everfeeds

service ApplicationAPI {
  misc.Token createToken(1: t.String applicationSecret, 2: t.Id accountId);

  misc.Account createAccessAndAccount(1: t.String applicationSecret, 2: misc.Access access, 3: t.String accessToken, 4: t.String accessSecret, 5: t.String accessShardId);
}

service EntryAPI {
  entry.Entry saveEntry(1: t.String token, 3: entry.Entry entry, 4: entry.EntryContent content);

  // to dive into entry contents
  entry.EntryContent getEntryContent(1: t.String token, 5: t.Id entryId);
  entry.Entry getEntry(1: t.String token, 5: t.Id entryId);

  oneway void markRead(1: t.String token, 2: t.Id entryId);
  oneway void markUnread(1: t.String token, 2: t.Id entryId);
}

service AccountAPI extends ApplicationAPI {
  // to discover basic information about current account
  misc.Account getAccount(1: t.String token);
  list<misc.Access> getAccesses(1: t.String token);

  // to create everything
  misc.Access saveAccessToken(1: t.String token, 2: misc.Access access, 3: t.String accessToken, 4: t.String accessSecret, 5: t.String accessShardId);
  misc.Access saveAccess(1: t.String token, 2: misc.Access access);

  misc.Account saveAccount(1: t.String token, 2: misc.Account account);
}

service AccessAPI extends AccountAPI {
  // to get access info
  list<misc.Tag> getTags(1: t.String token, 5: t.Id accessId)
  list<misc.Category> getCategories(1: t.String token, 5: t.Id accessId)
  list<enm.EntryKind> getKinds(1: t.String token, 5: t.Id accessId)
  misc.Tag saveTag(1: t.String token, 3: misc.Tag tag);
  misc.Category saveCategory(1: t.String token, 3: misc.Category category);
}

service FilterAPI {
  filter.Filter saveFilter(1: t.String token, 2: filter.Filter filter);

  // to get simple mash of entries
  list<entry.Entry> getMash(1: t.String token, 2: i16 page, 3: i16 maxCount);
  list<entry.Entry> getMashNew(1: t.String token, 2: t.Timestamp splitDate, 3: i16 maxCount);

  // to filter entries
  list<entry.Entry> getFiltered(1: t.String token, 2: filter.Filter filter, 3: i16 page, 4: i16 maxCount);
  list<entry.Entry> getFilteredNew(1: t.String token, 2: filter.Filter filter);
}