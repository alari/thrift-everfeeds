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

struct Application {
  1: t.Id id;
  2: t.String key;
  3: t.String secret;

  10: list<string> scopes;

  20: t.String title;
  21: t.String description;
}

service KernelAPI {
  string ping();

  misc.Token createToken(2: t.Id applicationId, 3: t.Id accountId, 4: list<string> scopes) throws (1: e.Forbidden eOne, 2: e.NotFound eTwo);

  Application saveApp(1: Application app) throws(1: e.Forbidden eOne);
  list<Application> listApps();

  misc.Account authenticate(2: misc.Access access, 3: t.String accessToken, 4: t.String accessSecret, 5: map<string,string> accessParams) throws(1: e.Forbidden eOne, 2: e.NotFound eTwo);

  list<entry.Entry> remotePullEntries(1: filter.Filter filter) throws(1: e.Forbidden eOne, 2: e.NotFound eTwo);

  void remoteSaveEntries(1: filter.Filter filter) throws(1: e.Forbidden eOne, 2: e.NotFound eTwo);
}