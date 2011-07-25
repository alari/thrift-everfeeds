package everfeeds.test;

import everfeeds.remote.auth.AuthHandler;
import everfeeds.remote.auth.thrift.util.AuthMethod;
import everfeeds.remote.auth.thrift.util.AuthSystem;
import org.apache.thrift.TException;

import java.io.IOException;
import java.util.List;

/**
 * @author Dmitry Kurinskiy
 * @since 25.07.11 15:05
 */
public class OAuthFlow {
    static public void main(String[] args) throws TException, IOException {
    List<AuthSystem> systems = new AuthHandler().listAuthSystems();

    System.out.println("Testing auth systems: OAuth");
    for(AuthSystem authSystem : systems) {
      if(authSystem.method != AuthMethod.OAUTH) continue;

      System.out.println("Auth for: " + authSystem.name);
      System.in.read();
      System.out.println("Read: ");
    }
  }
}
