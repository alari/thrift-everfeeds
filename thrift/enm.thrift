namespace cpp everfeeds.thrift
namespace java everfeeds.thrift
namespace php everfeeds.thrift
namespace perl everfeeds.thrift
namespace st Thrift.Everfeeds

enum EntryKind {
  STATUS,
  NOTE,
  ATOM,
  EMAIL,

  ALBUM,
  APPLICATION,
  CHECKIN,
  LINK,
  MUSIC,
  NEWS,
  PHOTO,
  VIDEO,
  WALL,
}

enum AccessType {
  EVERNOTE,
  FACEBOOK,
  TWITTER,
  GMAIL,
  GREADER,
  LINKEDIN,
  VKONTAKTE,
}