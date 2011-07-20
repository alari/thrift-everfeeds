package everfeeds.util.annotation

import everfeeds.thrift.util.Type
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
/**
 * @author Dmitry Kurinskiy
 * @since 30.06.11 12:15
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Accessor {
  Type value()
}