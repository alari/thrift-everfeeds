import org.apache.thrift.transport.TTransport
import org.apache.thrift.transport.TSocket
import org.apache.thrift.protocol.TBinaryProtocol
import org.apache.thrift.protocol.TProtocol
import everfeeds.secure.thrift.KernelAPI
/**
 * @author Dmitry Kurinskiy
 * @since 18.05.11 19:04
 */
@Typed
class ThriftPrivateClient {
  static private KernelAPI.Client kernelApi

  public static void main(String[] args) {
    initClient()
  }

  private static initClient(){
    TTransport transport = new TSocket("localhost", 9099)
    transport.open()
    TProtocol protocol = new  TBinaryProtocol(transport);

    kernelApi = new KernelAPI.Client(protocol);

    System.out.println "Private client running"
  }

  static public KernelAPI.Client getKernelClient(){
    if(kernelApi == null) {
      initClient()
    }
    kernelApi
  }
}