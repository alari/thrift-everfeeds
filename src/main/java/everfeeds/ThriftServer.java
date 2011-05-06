package everfeeds;

import everfeeds.thrift.EverfeedsSrv;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

/**
 * @author Dmitry Kurinskiy
 * @since 06.05.11 10:56
 */
public class ThriftServer {
  static private EverfeedsSrv.Processor processor;

  public static void main(String[] args) {
    try {
      ServiceHandler handler = new ServiceHandler();
      processor = new EverfeedsSrv.Processor(handler);

      Runnable simple = new Runnable() {
        public void run() {
          simple(processor);
        }
      };

      new Thread(simple).start();
    } catch (Exception x) {
      x.printStackTrace();
    }
  }


  public static void simple(EverfeedsSrv.Processor processor) {
    try {
      TServerTransport serverTransport = new TServerSocket(9090);
      //TServer server = new TSimpleServer(new Args(serverTransport).processor(processor));

      // Use this for a multithreaded server
      TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(processor));

      System.out.println("Starting the simple server...");
      server.serve();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
