package everfeeds.test

import everfeeds.secure.thrift.Application
import everfeeds.thrift.util.Scope
import everfeeds.thrift.domain.Access
import everfeeds.thrift.ttype.AccessType
import everfeeds.thrift.domain.Account
import everfeeds.thrift.domain.Token
import everfeeds.test.secure.ThriftPrivateClient
import everfeeds.thrift.EverfeedsAPI
/**
 * @author Dmitry Kurinskiy
 * @since 21.05.11 17:31
 */
class PublicAccess {
  static private String tokenCache

  static EverfeedsAPI.Client getApi(){
    ThriftPublicClient.client
  }

  static String getToken() {
    if(tokenCache) {
      return tokenCache
    }

    Application app = new Application()
    app.key = "test remote"
    app.scopes = Scope.values().collect {it.toString()}
    String appId = ThriftPrivateClient.client.saveApp(app)?.id
    assert appId

    Access access = new Access()
    access.type = AccessType.TWITTER
    access.identity = "test twitter"
    Account a = ThriftPrivateClient.client.authenticate(access, "token", "secret", [:])
    assert a
    assert a.id

    Token tkn = ThriftPrivateClient.client.createToken(appId, a.id, app.scopes)
    assert tkn
    assert tkn.id

    tokenCache = tkn.id
    tokenCache
  }
}
