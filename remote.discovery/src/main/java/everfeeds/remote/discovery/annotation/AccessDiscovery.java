package everfeeds.remote.discovery.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Dmitry Kurinskiy
 * @since 29.07.11 19:38
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface AccessDiscovery {
  String value();
}
