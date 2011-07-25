package everfeeds.test;

import everfeeds.remote.auth.AuthHandler;
import everfeeds.remote.auth.thrift.util.AuthMethod;
import everfeeds.remote.auth.thrift.util.AuthVariant;
import org.apache.thrift.TException;

import java.io.IOException;
import java.util.List;

/**
 * @author Dmitry Kurinskiy
 * @since 25.07.11 15:05
 */
public class OAuthFlow {
    static public void main(String[] args) throws TException, IOException {
    List<AuthVariant> variants = new AuthHandler().listAuthVariants();

    System.out.println("Testing auth variants: OAuth");
    for(AuthVariant authVariant : variants) {
      if(authVariant.method != AuthMethod.OAUTH) continue;

      System.out.println("Auth for: "+authVariant.system);
      System.in.read();
      System.out.println("Read: ");
    }
  }
}
