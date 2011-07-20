package everfeeds;

import everfeeds.handlers.*;
import everfeeds.handlers.secure.InternalHandler;
import everfeeds.internal.thrift.InternalAPI;
import everfeeds.thrift.EverfeedsAPI;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

/**
 * @author Dmitry Kurinskiy
 * @since 06.05.11 10:56
 */
public class ThriftServer {
  static private EverfeedsAPI.Processor mainAPI;
  static private InternalAPI.Processor kernelAPI;

  public static void main(String[] args) {
    try {
      org.apache.log4j.BasicConfigurator.configure();
      if(args.length>0) {
        System.out.println("Entering non-development mode");
        if(args[0].contains("test")) {
          Environment.setTesting();
        } else if(args[0].contains("prod")) {
          Environment.setProduction();
        }
      }

      kernelAPI = new InternalAPI.Processor(new InternalHandler());
      mainAPI = new EverfeedsAPI.Processor(new EverfeedsHandler());

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
                                                    .processor(mainAPI));

      System.out.println("Starting the Public server...");
      server.serve();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void runPrivateServer() {
    try {
      TServerTransport serverTransport = new TServerSocket(9099);
      TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport)
                                                 .processor(kernelAPI));

      System.out.println("Starting the Private server...");
      server.serve();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
