package everfeeds.mongo;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Reference;
import org.bson.types.ObjectId;

import java.util.Date;

/**
 * @author Dmitry Kurinskiy
 * @since 06.05.11 17:41
 */
@Entity
public class TokenD {
  @Id
  public ObjectId id;

  @Reference
  public AccountD account;

  public Date dateCreated = new Date();
  public Date expires;

  public boolean expired = false;
}
