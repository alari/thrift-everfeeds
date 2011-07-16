package everfeeds.remote.auth;

import everfeeds.remote.auth.thrift.AuthFlow;
import everfeeds.remote.auth.thrift.Credentials;
import everfeeds.remote.auth.thrift.util.AuthVariant;
import everfeeds.remote.auth.thrift.util.OAuthStep;
import everfeeds.remote.handshake.HandshakeHandler;
import org.apache.thrift.TException;

import java.util.List;

/**
 * @author Dmitry Kurinskiy
 * @since 16.07.11 3:20
 */
public class AuthHandler extends HandshakeHandler implements AuthFlow.Iface{
  public List<AuthVariant> listAuthVariants() throws TException {
    AuthVariant v = new AuthVariant();
    return null;
  }

  public boolean checkCredentials(Credentials credentials) throws TException {
    return false;
  }

  public OAuthStep getOAuthStep(AuthVariant authVariant, String redirectUrl) throws TException {
    return null;
  }

  public Credentials exchangeOAuthToken(AuthVariant authVariant, OAuthStep oAuthStep, String verifierCode) throws TException {
    return null;
  }
}
