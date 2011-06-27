@Typed package everfeeds.mongo;


import com.google.code.morphia.annotations.Entity
import com.google.code.morphia.annotations.Id
import com.google.code.morphia.annotations.Indexed
import com.google.code.morphia.annotations.Reference
import everfeeds.thrift.domain.Token
import everfeeds.thrift.util.Scope
import org.bson.types.ObjectId

/**
 * @author Dmitry Kurinskiy
 * @since 06.05.11 17:41
 */
@Entity
public class TokenD {
  @Id
  ObjectId id;

  @Indexed(unique = true)
  String key;

  @Reference
  @Indexed
  AccountD account;

  @Reference(lazy = true)
  @Indexed
  ApplicationD application;

  List<String> scopes = new ArrayList<String>();

  Date dateCreated = new Date();
  Date expires;

  boolean expired = false;

  public void syncToThrift(Token token) {
    token.key = key;
    token.accountId = account.id != null ? account.id.toString() : "";
    if (expires != null) {
      token.expires = expires.getTime();
    }
    token.expired = expired;
    token.scopes = scopes;
  }

  public void syncFromThrift(Token token) {
    expired = token.expired;
    expires = new Date(token.expires);
    scopes.clear();
    if (token.scopes != null) {
      scopes = token.scopes;
    }
  }

  public boolean hasScope(Scope scope) {
    scopes.contains(scope.toString());
  }
}
