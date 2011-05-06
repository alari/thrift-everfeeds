include "t.thrift"
include "enm.thrift"

namespace cpp everfeeds.thrift
namespace java everfeeds.thrift
namespace php everfeeds.thrift
namespace perl everfeeds.thrift
namespace st Thrift.Everfeeds

struct Filter {
  1: t.Id id;
  2: t.Id accessId;

  5: t.String title;

  10: list<t.String> categoryIds;
  11: bool categoryWith;

  20: list<t.Id> withTagIds;
  21: list<t.Id> withoutTagIds;

  30: list<enm.EntryKind> kinds;
  31: bool kindsWith;

  40: t.Timestamp splitDate;
}