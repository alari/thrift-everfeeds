include "t.thrift"
include "enm.thrift"

namespace cpp everfeeds.thrift
namespace java everfeeds.thrift
namespace php everfeeds.thrift
namespace perl everfeeds.thrift
namespace st Thrift.Everfeeds

struct Token {
  1: t.Id id;
  2: t.Id accountId;

  10: t.Timestamp expires;
  11: bool expired;

  20: list<string> scopes;
}

struct Tag {
  1: t.Id id;
  2: t.String identity;
  3: t.String accessId;

  10: t.String title;
}

struct Category {
  1: t.Id id;
  2: t.String identity;
  3: t.String accessId;

  10: t.String title;
}

struct Access {
  1: t.Id id;
  2: t.String identity;
  3: t.Id accountId;
  4: enm.AccessType type
  5: bool expired;

  10: t.String title;
  11: t.String screenName;
}

struct Account {
  1: t.Id id;

  10: t.String title;
}