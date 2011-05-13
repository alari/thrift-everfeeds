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
  misc.Token createToken(1: t.String actApplicationSecret, 2: t.Id applicationId, 3: t.Id accountId, 4: list<string> scopes) throws (1: e.Forbidden eOne, 2: e.NotFound eTwo);

  void createApp(1: t.String actApplicationSecret, 2: string key, 3: string secret, 4: list<string> scopes) throws(1: e.Forbidden eOne, 2: e.NotFound eTwo);

  misc.Account createAccessAndAccount(1: t.String actApplicationSecret, 2: misc.Access access, 3: t.String accessToken, 4: t.String accessSecret, 5: list<string> accessParams) throws(1: e.Forbidden eOne, 2: e.NotFound eTwo);
}