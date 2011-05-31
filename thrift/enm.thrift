namespace cpp everfeeds.thrift.ttype
namespace java everfeeds.thrift.ttype
namespace php everfeeds.thrift.ttype
namespace perl everfeeds.thrift.ttype
namespace st Thrift.Everfeeds.Ttype

enum EntryKind {
  STATUS = 1,
  NOTE = 2,
  ATOM = 3,
  EMAIL = 4,

  ALBUM = 5,
  APPLICATION = 6,
  CHECKIN = 7,
  LINK = 8,
  MUSIC = 9,
  NEWS = 10,
  PHOTO = 11,
  VIDEO = 12,
  WALL = 13,

  DM = 14,
}

enum AccessType {
  EVERNOTE = 1,
  FACEBOOK = 2,
  TWITTER = 3,
  GMAIL = 4,
  GREADER = 5,
  LINKEDIN = 6,
  VKONTAKTE = 7,
  METAWEBLOG = 8,
}