package everfeeds;

import everfeeds.handlers.*;
import everfeeds.secure.handlers.ApplicationHandler;
import everfeeds.secure.thrift.ApplicationAPI;
import everfeeds.thrift.service.*;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
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

      Runnable publicServer = new Runnable() {
        public void run() {
          runPublicServer();
        }
      };

      Runnable privateServer = new Runnable() {
        public void run() {
          runPrivateServer();
        }
      };

      new Thread(publicServer).start();
      new Thread(privateServer).start();
    } catch (Exception x) {
      x.printStackTrace();
    }
  }

  public static void runPublicServer() {
    try {
      TServerTransport serverTransport = new TServerSocket(9090);

      TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport)
                                                    .processor(entryAPI)
                                                    .processor(accessAPI)
                                                    .processor(accountAPI)
                                                    .processor(filterAPI));

      System.out.println("Starting the runPublicServer server...");
      server.serve();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void runPrivateServer() {
    try {
      TServerTransport serverTransport = new TServerSocket(9099);

      TServer server = new TSimpleServer(new TServer.Args(serverTransport)
                                                    .processor(applicationAPI));

      System.out.println("Starting the runPrivateServer server...");
      server.serve();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
