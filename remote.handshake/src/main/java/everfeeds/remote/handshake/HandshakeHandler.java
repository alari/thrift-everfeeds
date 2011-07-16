package everfeeds.remote.handshake;

import everfeeds.remote.handshake.thrift.HandshakeFlow;
import org.apache.thrift.TException;

/**
 * @author Dmitry Kurinskiy
 * @since 16.07.11 2:54
 */
public class HandshakeHandler implements HandshakeFlow.Iface{
  public String sayHello() throws TException {
    return "Hello!";
  }
}
