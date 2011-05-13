package everfeeds.mongo;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Indexed;
import everfeeds.thrift.util.Scope;
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

  @Indexed(unique = true)
  public String key;
  @Indexed(unique = true)
  public String secret;

  public List<String> scopes;

  public boolean hasScope(Scope scope) {
    return this.scopes.contains(scope.toString());
  }
}
