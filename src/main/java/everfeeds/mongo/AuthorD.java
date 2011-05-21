package everfeeds.mongo;

import com.google.code.morphia.annotations.Embedded;
import everfeeds.thrift.domain.Author;

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

  public Author toThrift() {
    Author a = new Author();
    a.title = title;
    a.identity = identity;
    a.imageUrl = imageUrl;
    a.screenName = screenName;
    return a;
  }

  public void syncFromThrift(Author author) {
    title = author == null ? "" : author.title;
    identity = author == null ? "" :author.identity;
    imageUrl = author == null ? "" :author.imageUrl;
    screenName = author == null ? "" :author.screenName;
  }
}
