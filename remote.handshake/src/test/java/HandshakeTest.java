import everfeeds.remote.handler.HandshakeHandler;
import junit.framework.TestCase;
import org.apache.thrift.TException;
import org.junit.Test;

/**
 * @author Dmitry Kurinskiy
 * @since 16.07.11 2:55
 */
public class HandshakeTest extends TestCase {
  @Test public void testHello() throws TException{
    assertEquals("Hello!", new HandshakeHandler().sayHello());
  }
}
