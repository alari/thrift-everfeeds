include "entry.thrift"
include "filter.thrift"
include "misc.thrift"
include "t.thrift"
include "enm.thrift"
include "e.thrift"

namespace cpp everfeeds.secure.thrift
namespace java everfeeds.secure.thrift
namespace php everfeeds.secure.thrift
namespace perl everfeeds.secure.thrift
namespace st Thrift.Everfeeds.Secure


service ApplicationAPI {
  misc.Token createToken(2: t.Id applicationId, 3: t.Id accountId, 4: list<string> scopes) throws (1: e.Forbidden eOne, 2: e.NotFound eTwo);

  t.Id createApp(2: string key, 3: string secret, 4: list<string> scopes) throws(1: e.Forbidden eOne, 2: e.NotFound eTwo);

  misc.Account createAccessAndAccount(2: misc.Access access, 3: t.String accessToken, 4: t.String accessSecret, 5: list<string> accessParams) throws(1: e.Forbidden eOne, 2: e.NotFound eTwo);

  string ping();
}

service RemoteAPI {
  list<entry.Entry> pull(1: filter.Filter filter) throws(1: e.Forbidden eOne, 2: e.NotFound eTwo);

  void saveEntries(1: filter.Filter filter) throws(1: e.Forbidden eOne, 2: e.NotFound eTwo);
}