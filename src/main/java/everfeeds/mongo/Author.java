package everfeeds.mongo;

import com.google.code.morphia.annotations.Embedded;

/**
 * @author Dmitry Kurinskiy
 * @since 06.05.11 14:54
 */
@Embedded
public class Author {
  String title;
  String identity;
  String imageUrl;
  String screenName;
}
