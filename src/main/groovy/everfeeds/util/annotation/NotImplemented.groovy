package everfeeds.util.annotation

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.ElementType
import java.lang.annotation.Target
/**
 * @author Dmitry Kurinskiy
 * @since 30.06.11 12:22
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface NotImplemented {
  String value() default "Not Implemented Yet; To Be Done"
}