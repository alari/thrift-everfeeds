package everfeeds.mongo;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Indexed;
import com.google.code.morphia.annotations.Reference;
import everfeeds.thrift.util.Scope;
import everfeeds.thrift.domain.Token;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Dmitry Kurinskiy
 * @since 06.05.11 17:41
 */
@Entity
public class TokenD {
  @Id
  public ObjectId id;

  @Reference
  @Indexed
  public AccountD account;

  @Reference(lazy = true)
  @Indexed
  public ApplicationD application;

  public List<String> scopes = new ArrayList<String>();

  public Date dateCreated = new Date();
  public Date expires;

  public boolean expired = false;

  public void syncToThrift(Token token) {
    token.id = id.toString();
    token.accountId = account.id != null ? account.id.toString(): "";
    if(expires != null) {
      token.expires = expires.getTime();
    }
    token.expired = expired;
    token.scopes = scopes;
  }

  public void syncFromThrift(Token token) {
    expired = token.expired;
    expires = new Date(token.expires);
    scopes.clear();
    if(token.scopes != null) {
      scopes = token.scopes;
    }
  }

  public boolean hasScope(Scope scope) {
    return this.scopes.contains(scope.toString());
  }
}
