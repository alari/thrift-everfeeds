package everfeeds.test;

import everfeeds.remote.auth.config.AuthConfig;
import junit.framework.TestCase;

/**
 * @author Dmitry Kurinskiy
 * @since 22.07.11 23:57
 */
public class AuthConfigTest extends TestCase {
  public void testParses() {
    System.setProperty(AuthConfig.YAML_PROPERTY, "remote.auth/src/test/resources/auth.yaml");
    System.out.println(AuthConfig.getInstance().toString());
  }

  public void testHasThings() {
    assertNotNull(AuthConfig.getInstance().accesses);
    assertTrue(AuthConfig.getInstance().accesses.containsKey("twitter"));
  }
}
