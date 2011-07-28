namespace java everfeeds.remote.discovery.thrift.ttype

enum NodeTypeGroup {
  USER_ACCOUNT = 1,
  FOLDER = 2,
  COMMUNITY = 3,
  TAG = 4,
  USER_RELATION = 5,
  ENTRY = 6,
  CHECK_IN = 7,
  FILE = 8,
  LINK = 9,
}
enum TypeNarrow {
  USER = 1,
  FOLDER = 2,
  FEED = 3,
  ALBUM = 4,
  COMMUNITY = 5,
  PUBLIC_PAGE = 6,

  ENTRY = 20,
  SEARCH = 21,
  SEARCH_SAVED = 22,
  COMMENT = 23,

  RELATION_FRIEND = 30,
  RELATION_MEMBER = 31,
  RELATION_ADMIN = 32,
  RELATION_PERMISSION = 33,

  FILE_VIDEO = 50,
  FILE_AUDIO = 51,
  FILE_IMAGE = 52,
  FILE_DOC = 53,

  UNKNOWN = 255,
}


struct ContentType {
  1: TypeNarrow narrow;
  2: string type;
}

struct NodeType {
  1: NodeTypeGroup group;
  2: TypeNarrow narrow;

  10: string type;
}