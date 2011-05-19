package everfeeds;

import everfeeds.handlers.*;
import everfeeds.secure.handlers.KernelHandler;
import everfeeds.secure.thrift.KernelAPI;
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
  static private KernelAPI.Processor kernelAPI;

  public static void main(String[] args) {
    try {
      org.apache.log4j.BasicConfigurator.configure();
      if(args[0].contains("test")) {
        Environment.setTesting();
      } else if(args[0].contains("prod")) {
        Environment.setProduction();
      }

      kernelAPI = new KernelAPI.Processor(new KernelHandler());
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
