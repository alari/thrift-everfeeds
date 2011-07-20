package everfeeds.internal.thrift;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

/**
 * @author Dmitry Kurinskiy
 * @since 24.06.11 14:05
 */
public class InternalAPIHolder {
  static private InternalAPI.Client internalApi;

  private static void initClient() throws TTransportException{
    TTransport transport = new TSocket("localhost", 9099);
    transport.open();
    TProtocol protocol = new TBinaryProtocol(transport);

    internalApi = new InternalAPI.Client(protocol);
  }

  static public InternalAPI.Client getClient() throws TTransportException{
    if(internalApi == null) {
      initClient();
    }
    return internalApi;
  }
}
