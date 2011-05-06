package everfeeds.mongo;

import com.google.code.morphia.annotations.Embedded;

/**
 * @author Dmitry Kurinskiy
 * @since 06.05.11 14:54
 */
@Embedded
public class AuthorD {
  public String title;
  public String identity;
  public String imageUrl;
  public String screenName;
}
