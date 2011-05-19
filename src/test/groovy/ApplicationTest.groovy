import everfeeds.thrift.util.Scope
import everfeeds.thrift.domain.Access
import everfeeds.thrift.ttype.AccessType
import everfeeds.thrift.domain.Account
import everfeeds.thrift.domain.Token
/**
 * @author Dmitry Kurinskiy
 * @since 18.05.11 19:57
 */
class ApplicationTest extends GroovyTestCase{

  String getAppId() {
    ThriftPrivateClient.client.createApp("test key", "test secret", [Scope.FEED_READ.toString()])
  }

  String getAccountId(){
    Access a = new Access()
    a.identity = "testing"
    a.type = AccessType.TWITTER
    a.title = "my testing access"
    ThriftPrivateClient.client.authenticate(a, "token", "secret", [])?.id
  }

  void testCreateApp(){
    String id = ThriftPrivateClient.client.createApp("test key", "test secret", [Scope.FEED_READ.toString()])
    assert id
    assertEquals id, ThriftPrivateClient.client.createApp("test key", "222", [])
  }

  void testCreateAccessAndAccount(){
    Access a = new Access()
    a.identity = "testing"
    a.type = AccessType.TWITTER
    a.title = "my testing access"

    Account account = ThriftPrivateClient.client.authenticate(a, "token", "secret", [])
    assert account.id

    assertEquals account.id, ThriftPrivateClient.client.authenticate(a, "token", "secret", [])?.id
  }

  void testCreateToken(){

    Token tkn = ThriftPrivateClient.client.createToken(appId, accountId, [])
    assert tkn.id

    tkn = ThriftPrivateClient.client.createToken(appId, accountId, [Scope.FEED_READ.toString(), Scope.FEED_WRITE.toString()])
    assertNotNull tkn.id
    assert Scope.FEED_READ.toString() in tkn.scopes
    assert !(Scope.FEED_WRITE.toString() in tkn.scopes)
  }
}
