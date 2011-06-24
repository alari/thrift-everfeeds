@Typed package everfeeds.mongo;


import everfeeds.internal.thrift.Application
import everfeeds.thrift.util.Scope
import org.apache.commons.lang.RandomStringUtils
import org.bson.types.ObjectId
import com.google.code.morphia.annotations.*

/**
 * @author Dmitry Kurinskiy
 * @since 09.05.11 15:45
 */
@Entity
public class ApplicationD {
  @Id
  ObjectId id;

  @Indexed(unique = true)
  String key;
  @Indexed(unique = true)
  String secret;

  String title;
  String description;

  @Version Long version;

  List<String> scopes = new ArrayList<String>();

  Date dateCreated = new Date();
  Date lastUpdated = new Date();

  @PrePersist
  void prePersist() {
    lastUpdated = new Date();
  }

  public boolean hasScope(Scope scope) {
    return this.scopes.contains(scope.toString());
  }

  public void syncToThrift(Application app) {
    app.id = id.toString();
    app.key = key;
    app.secret = secret;
    app.scopes = scopes;
    app.title = title;
    app.description = description;
  }

  public void syncFromThrift(Application app) {
    key = app.key;
    secret = app.secret;
    if (secret == null || secret.isEmpty()) {
      secret = RandomStringUtils.randomAlphanumeric(128);
    }
    scopes.clear();
    if (app.scopes != null) {
      scopes = app.scopes;
    }
    title = app.title != null && !app.title.isEmpty() ? app.title : key;
    description = app.description;
  }
}
