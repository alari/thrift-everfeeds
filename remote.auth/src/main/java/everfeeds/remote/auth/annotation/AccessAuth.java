package everfeeds.remote.auth.annotation;

import everfeeds.remote.auth.thrift.util.AccessType;
import everfeeds.remote.auth.thrift.util.AuthMethod;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Dmitry Kurinskiy
 * @since 20.07.11 16:24
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface AccessAuth {
  String system() default "";
  AuthMethod method();
  AccessType type();
}
