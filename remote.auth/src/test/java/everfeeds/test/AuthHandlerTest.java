package everfeeds.test;

import everfeeds.remote.auth.AuthHandler;
import everfeeds.remote.auth.thrift.util.AuthSystem;
import junit.framework.TestCase;
import org.apache.thrift.TException;

/**
 * @author Dmitry Kurinskiy
 * @since 25.07.11 1:28
 */
public class AuthHandlerTest extends TestCase {
  public void testHasVariants() throws TException {
    assertTrue(new AuthHandler().listAuthSystems().size() > 0);

    for (AuthSystem av : new AuthHandler().listAuthSystems()) {
      assertNotNull(av.name);
    }
  }
}
