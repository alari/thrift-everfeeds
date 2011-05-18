import org.apache.thrift.transport.TTransport
import org.apache.thrift.transport.TSocket
import org.apache.thrift.protocol.TBinaryProtocol
import org.apache.thrift.protocol.TProtocol
import everfeeds.secure.thrift.ApplicationAPI
import org.junit.Test
import everfeeds.secure.thrift.RemoteAPI
/**
 * @author Dmitry Kurinskiy
 * @since 18.05.11 19:04
 */
@Typed
class ThriftPrivateClient {
  static private ApplicationAPI.Client appApi

  public static void main(String[] args) {
    initClient()
  }

  private static initClient(){
    TTransport transport = new TSocket("localhost", 9099)
    transport.open()
    TProtocol protocol = new  TBinaryProtocol(transport);

    appApi = new ApplicationAPI.Client(protocol)
  }

  static public ApplicationAPI.Client getAppClient(){
    if(appApi == null) {
      initClient()
    }
    appApi
  }

  static public RemoteAPI.Client getRemoteClient(){
    null
  }
}