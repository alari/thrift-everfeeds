package everfeeds.mongo;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Indexed;
import com.google.code.morphia.annotations.Reference;
import everfeeds.thrift.domain.Token;
import org.bson.types.ObjectId;

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

  List<String> scopes;

  public Date dateCreated = new Date();
  public Date expires;

  public boolean expired = false;

  public void syncToThrift(Token token) {
    token.id = id.toString();
    token.accountId = account.id.toString();
    token.expires = expires.getTime();
    token.expired = expired;
    token.scopes = scopes;
  }

  public void syncFromThrift(Token token) {
    expired = token.expired;
    expires = new Date(token.expires);
    scopes = token.scopes;
  }
}
