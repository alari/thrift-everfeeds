package everfeeds.test

import everfeeds.thrift.EverfeedsAPI
import org.apache.thrift.transport.TTransport
import org.apache.thrift.transport.TSocket
import org.apache.thrift.protocol.TProtocol
import org.apache.thrift.protocol.TBinaryProtocol
/**
 * @author Dmitry Kurinskiy
 * @since 19.05.11 12:00
 */
@Typed
class ThriftPublicClient {
    static private EverfeedsAPI.Client mainApi

  public static void main(String[] args) {
    initClient()
  }

  private static initClient(){
    TTransport transport = new TSocket("localhost", 9090)
    transport.open()
    TProtocol protocol = new  TBinaryProtocol(transport);

    mainApi = new EverfeedsAPI.Client(protocol);

    System.out.println "Public client running"
  }

  static public EverfeedsAPI.Client getClient(){
    if(mainApi == null) {
      initClient()
    }
    mainApi
  }
}
