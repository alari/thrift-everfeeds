package everfeeds.test

import everfeeds.thrift.util.Scope
import everfeeds.thrift.domain.Access
import everfeeds.thrift.ttype.AccessType
import everfeeds.thrift.domain.Account
import everfeeds.thrift.domain.Token
import everfeeds.thrift.EverfeedsAPI
import everfeeds.internal.thrift.Application
import everfeeds.internal.thrift.InternalAPIHolder
import everfeeds.thrift.util.APIHolder
/**
 * @author Dmitry Kurinskiy
 * @since 21.05.11 17:31
 */
class PublicAccess {
  static private String tokenCache

  static EverfeedsAPI.Client getApi(){
    APIHolder.client
  }

  static String getFreshToken() {
    tokenCache = null
    token
  }

  static String getToken() {
    if(tokenCache) {
      return tokenCache
    }

    Application app = new Application()
    app.key = "test remote"
    app.scopes = Scope.values().collect {it.toString()}
    String appId = InternalAPIHolder.client.saveApp(app)?.id
    assert appId

    Access access = new Access()
    access.type = AccessType.TWITTER
    access.identity = "test twitter"
    Account a = InternalAPIHolder.client.authenticate(access, "token", "secret", [:])
    assert a
    assert a.id

    Token tkn = InternalAPIHolder.client.createToken(appId, a.id, app.scopes)
    assert tkn
    assert tkn.id

    tokenCache = tkn.id
    tokenCache
  }
}
