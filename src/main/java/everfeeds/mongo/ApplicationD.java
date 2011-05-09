package everfeeds.mongo;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * @author Dmitry Kurinskiy
 * @since 09.05.11 15:45
 */
@Entity
public class ApplicationD {
  @Id
  public ObjectId id;

  public String key;
  public String secret;

  List<String> scopes;
}
