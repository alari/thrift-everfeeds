import everfeeds.thrift.domain.Filter
/**
 * @author Dmitry Kurinskiy
 * @since 18.05.11 23:22
 */
class RemoteTest extends GroovyTestCase{
  void testPull(){
    def filter = new Filter()

    ThriftPrivateClient.kernelClient.remotePullEntries filter
  }
}
