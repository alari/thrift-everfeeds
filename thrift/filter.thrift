include "t.thrift"
include "enm.thrift"

namespace cpp everfeeds.thrift.domain
namespace java everfeeds.thrift.domain
namespace php everfeeds.thrift.domain
namespace perl everfeeds.thrift.domain
namespace st Thrift.Everfeeds.Domain

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