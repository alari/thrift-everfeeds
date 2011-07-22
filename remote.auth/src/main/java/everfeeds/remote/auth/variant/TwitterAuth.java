package everfeeds.remote.auth.variant;

import everfeeds.remote.auth.annotation.AccessAuth;
import everfeeds.remote.auth.thrift.util.AccessType;
import everfeeds.remote.auth.thrift.util.AuthMethod;
import org.scribe.builder.api.Api;
import org.scribe.builder.api.TwitterApi;

/**
 * @author Dmitry Kurinskiy
 * @since 20.07.11 16:22
 */
@AccessAuth(system = "twitter", method = AuthMethod.OAUTH2, type = AccessType.TWITTER)
public class TwitterAuth extends Auth{
  static private TwitterAuth instance = new TwitterAuth();
  private TwitterAuth(){}

  @Override
  protected Class<? extends Api> getProvider() {
    return TwitterApi.class;
  }
}
