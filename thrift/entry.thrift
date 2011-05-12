include "t.thrift"
include "enm.thrift"

namespace cpp everfeeds.thrift.domain
namespace java everfeeds.thrift.domain
namespace php everfeeds.thrift.domain
namespace perl everfeeds.thrift.domain
namespace st Thrift.Everfeeds.Domain

struct Entry {
  # Identifiers
  1: t.Id id;
  2: t.String identity;

  # Owner info
  10: t.Id accessId;
  // t.Id accountId;
  // t.Id type;

  # Entry kind
  20: enm.EntryKind kind;

  # Flags
  30: bool isAuthor;
  31: bool isPublicAvailable;
  32: bool isFavorite;
  33: bool isRead;

  # Common content fields
  40: t.String title;
  41: optional t.String description;
  // String content; to be required separately

  # Author info
  50: optional t.String author;
  51: optional t.String authorIdentity;
  52: optional t.String authorPicUrl;
  53: optional t.String authorScreenName;

  # Dates
  60: optional t.Timestamp dateCreated;
  61: optional t.Timestamp lastUpdated;
  62: optional t.Timestamp datePlaced;

  # Sortings
  70: t.Id categoryId
  71: list<t.Id> tagIds
  72: list<t.Id> filterIds
}

struct EntryContent {
  1: t.Id id;
  2: t.Id entryId;

  10: t.String content;
}

