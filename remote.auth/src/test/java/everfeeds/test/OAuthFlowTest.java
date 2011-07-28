package everfeeds.test;

import everfeeds.remote.auth.AuthHandler;
import everfeeds.remote.auth.config.AuthConfig;
import everfeeds.remote.auth.thrift.Credentials;
import everfeeds.remote.auth.thrift.ex.AuthConnectionError;
import everfeeds.remote.auth.thrift.ex.AuthFailed;
import everfeeds.remote.auth.thrift.ex.AuthMethodMismatch;
import everfeeds.remote.auth.thrift.ex.AuthSystemUnknown;
import everfeeds.remote.auth.thrift.util.AuthMethod;
import everfeeds.remote.auth.thrift.util.AuthSystem;
import everfeeds.remote.auth.thrift.util.OAuthStep;
import org.apache.thrift.TException;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * @author Dmitry Kurinskiy
 * @since 25.07.11 15:05
 */
public class OAuthFlowTest {
  static public void main(String[] args) throws TException, IOException, AuthMethodMismatch, AuthFailed, AuthSystemUnknown {
    System.setProperty(AuthConfig.YAML_PROPERTY, "remote.auth/src/test/resources/auth.yaml");
    List<AuthSystem> systems = new AuthHandler().listAuthSystems();

    Scanner in = new Scanner(System.in);

    System.out.println("Testing auth systems: OAuth");
    for (AuthSystem authSystem : systems) {
      if (authSystem.method != AuthMethod.OAUTH) continue;

      System.out.println("Auth for: " + authSystem.name);
      OAuthStep step;
      try {
        step = new AuthHandler().getOAuthStep(authSystem, "oob");
      } catch (AuthConnectionError authConnectionError) {
        System.out.println("Auth connection error: "+authConnectionError.getMsg());
        System.out.println("===============================\n");
        continue;
      }

      System.out.println("Please proceed to "+step.authUrl);
      System.out.println("Enter the code:");
      try {
        String verificationCode = in.nextLine();
        System.out.println("Got: " + verificationCode + "!");
        Credentials credentials = new AuthHandler().exchangeOAuthToken(step, verificationCode);
        System.out.println("Token: "+credentials.token);
        System.out.println("Secret: "+credentials.secret);

        System.out.println("Checking credentials...");
        System.out.println(new AuthHandler().checkCredentials(credentials) ? "OK!" : "FAILED!!!");
      } catch(AuthFailed af) {
        System.out.println("Authentication failed!");
      } catch(AuthConnectionError authConnectionError) {
        System.out.println("Auth connection error: "+authConnectionError.getMsg());
        System.out.println("===============================\n");
      }
      System.out.println("===============================\n");
    }
  }
}
