package everfeeds;

import everfeeds.handlers.*;
import everfeeds.thrift.*;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

/**
 * @author Dmitry Kurinskiy
 * @since 06.05.11 10:56
 */
public class ThriftServer {
  static private EntryAPI.Processor entryAPI;
  static private AccountAPI.Processor accountAPI;
  static private AccessAPI.Processor accessAPI;
  static private FilterAPI.Processor filterAPI;
  static private ApplicationAPI.Processor applicationAPI;

  public static void main(String[] args) {
    try {
      entryAPI = new EntryAPI.Processor(new EntryHandler());
      accountAPI = new AccountAPI.Processor(new AccountHandler());
      accessAPI = new AccessAPI.Processor(new AccessHandler());
      filterAPI = new FilterAPI.Processor(new FilterHandler());
      applicationAPI = new ApplicationAPI.Processor(new ApplicationHandler());

      Runnable simple = new Runnable() {
        public void run() {
          runServer();
        }
      };

      new Thread(simple).start();
    } catch (Exception x) {
      x.printStackTrace();
    }
  }


  public static void runServer() {
    try {
      TServerTransport serverTransport = new TServerSocket(9090);
      //TServer server = new TSimpleServer(new Args(serverTransport).processor(processor));

      // Use this for a multithreaded server

      TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport)
                                                    .processor(entryAPI)
                                                    .processor(accessAPI)
                                                    .processor(accountAPI)
                                                    .processor(filterAPI)
                                                    .processor(applicationAPI));

      System.out.println("Starting the runServer server...");
      server.serve();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
