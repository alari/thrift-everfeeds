package everfeeds.remote.auth.annotation;

import org.scribe.builder.api.Api;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Dmitry Kurinskiy
 * @since 23.07.11 1:03
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface OAuthProvider {
  Class<? extends Api> value();
}
