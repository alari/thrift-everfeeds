package everfeeds.util.annotation

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target
import java.lang.annotation.ElementType
/**
 * @author Dmitry Kurinskiy
 * @since 30.06.11 12:22
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface NotAvailable {
  String value() default "Not Available Via Service Remote API"
}