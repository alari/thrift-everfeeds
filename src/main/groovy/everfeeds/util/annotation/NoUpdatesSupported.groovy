@Typed package everfeeds.util.annotation

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.ElementType
import java.lang.annotation.Target
/**
 * @author Dmitry Kurinskiy
 * @since 01.07.11 18:51
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface NoUpdatesSupported {
  String value() default "Updates are not supported"
}