package everfeeds.remote.handler;

import everfeeds.remote.thrift.Handshake;
import org.apache.thrift.TException;

/**
 * @author Dmitry Kurinskiy
 * @since 16.07.11 2:54
 */
public class HandshakeHandler implements Handshake.Iface{
  public String sayHello() throws TException {
    return "Hello!";
  }
}
