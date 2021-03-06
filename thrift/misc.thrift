include "t.thrift"
include "enm.thrift"

namespace cpp everfeeds.thrift.domain
namespace java everfeeds.thrift.domain
namespace php everfeeds.thrift.domain
namespace perl everfeeds.thrift.domain
namespace st Thrift.Everfeeds.Domain

struct Token {
  2: t.Id accountId;
  3: string key;

  10: t.Timestamp expires;
  11: bool expired;

  20: list<string> scopes;
}

struct Tag {
  1: t.Id id;
  2: t.String identity;
  3: t.String accessId;

  10: t.String title;

  20: t.Id parentId;
}

struct Category {
  1: t.Id id;
  2: t.String identity;
  3: t.String accessId;

  10: t.String title;

  20: t.Id parentId;
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

struct Author {
  10: t.String title;
  11: t.String identity;
  12: t.String imageUrl;
  13: t.String screenName;
}