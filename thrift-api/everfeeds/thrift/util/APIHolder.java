package everfeeds.thrift.util;

import everfeeds.thrift.EverfeedsAPI;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

/**
 * @author Dmitry Kurinskiy
 * @since 24.06.11 14:05
 */
public class APIHolder {
  static private EverfeedsAPI.Client mainApi;

  private static void initClient() throws TTransportException{
    TTransport transport = new TSocket("localhost", 9090);
    transport.open();
    TProtocol protocol = new TBinaryProtocol(transport);

    mainApi = new EverfeedsAPI.Client(protocol);
  }

  static public EverfeedsAPI.Client getClient() throws TTransportException{
    if(mainApi == null) {
      initClient();
    }
    return mainApi;
  }
}
