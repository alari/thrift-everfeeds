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

service EverfeedsAPI {
  // to create everything
  misc.Access createAccess(1: t.String token, 2: misc.Access access);
  misc.Account createAccessAndAccount(1: t.String token, 2: misc.Access access);
  misc.Tag createTag(1: t.String token, 2: misc.Access access, 3: misc.Tag tag);
  misc.Category createCategory(1: t.String token, 2: misc.Access access, 3: misc.Category category);
  entry.Entry createEntry(1: t.String token, 2: misc.Access access, 3: entry.Entry entry, 4: entry.EntryContent content);
  filter.Filter createFilter(1: t.String token, 2: filter.Filter filter);

  // to discover basic information about current account
  misc.Account getAccount(1: t.String token);
  list<misc.Access> getAccesses(1: t.String token);

  // to get access info
  list<misc.Tag> getTags(1: t.String token, 5: t.Id accessId)
  list<misc.Category> getCategories(1: t.String token, 5: t.Id accessId)
  list<enm.EntryKind> getKinds(1: t.String token, 5: t.Id accessId)

  // to get simple mash of entries
  list<entry.Entry> getMash(1: t.String token, 2: i16 page, 3: i16 maxCount);
  list<entry.Entry> getMashNew(1: t.String token, 2: t.Timestamp splitDate, 3: i16 maxCount);

  // to filter entries
  list<entry.Entry> getFiltered(1: t.String token, 2: filter.Filter filter, 3: i16 page, 4: i16 maxCount);
  list<entry.Entry> getFilteredNew(1: t.String token, 2: filter.Filter filter);

  // to dive into entry contents
  entry.EntryContent getEntryContent(1: t.String token, 5: t.Id entryId);
}