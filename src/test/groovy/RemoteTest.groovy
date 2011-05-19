import everfeeds.thrift.domain.Filter
import everfeeds.thrift.error.NotFound
import everfeeds.thrift.util.Scope
import everfeeds.thrift.domain.Token
import everfeeds.thrift.domain.Account
import everfeeds.thrift.domain.Access
import everfeeds.thrift.ttype.AccessType
import everfeeds.thrift.error.Forbidden
/**
 * @author Dmitry Kurinskiy
 * @since 18.05.11 23:22
 */
class RemoteTest extends GroovyTestCase{
  void testPull(){
    def filter = new Filter()

    shouldFail(NotFound) {
      ThriftPrivateClient.client.remotePullEntries filter
    }

    String appId = ThriftPrivateClient.client.createApp("test remote", "secret", [Scope.FEED_READ.toString(),Scope.INFO.toString()])
    assertNotNull appId

    Access access = new Access()
    access.type = AccessType.TWITTER
    access.identity = "test twitter"
    Account a = ThriftPrivateClient.client.authenticate(access, "token", "secret", [])
    assertNotNull a
    assertNotNull a.id

    Token tkn = ThriftPrivateClient.client.createToken(appId, a.id, [Scope.FEED_READ.toString()])
    assertNotNull tkn
    assertNotNull tkn.id

    shouldFail(Forbidden) {
      ThriftPublicClient.client.getAccesses(tkn.id)
    }

    tkn = ThriftPrivateClient.client.createToken(appId, a.id, [Scope.FEED_READ.toString(),Scope.INFO.toString()])
    assertNotNull tkn
    assertNotNull tkn.id

    List<Access> accesses = ThriftPublicClient.client.getAccesses(tkn.id)
    assertEquals 1, accesses.size()

    filter.accessId = accesses.first().id
    assertNotNull filter.accessId

    assertNotNull ThriftPrivateClient.client.remotePullEntries(filter)
  }
}
