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

service EverfeedsSrv {
  entry.EntryContent getEntryContent(1: t.String accessToken, 5: t.Id entryId);

  list<entry.Entry> getMash(1: t.String accessToken, 2: i16 page, 3: i16 maxCount);
  list<entry.Entry> getMashNew(1: t.String accessToken, 2: t.Timestamp splitDate);

  list<entry.Entry> getFiltered(1: t.String accessToken, 2: filter.Filter filter, 3: i16 page, 4: i16 maxCount);
  list<entry.Entry> getFilteredNew(1: t.String accessToken, 2: filter.Filter filter);
}