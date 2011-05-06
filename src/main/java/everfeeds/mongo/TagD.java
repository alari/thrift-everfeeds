package everfeeds.mongo;

import com.google.code.morphia.annotations.*;
import org.bson.types.ObjectId;

import java.util.Date;

/**
 * @author Dmitry Kurinskiy
 * @since 06.05.11 14:40
 */
@Entity
public class TagD {
  @Id
  public ObjectId id;

  @Reference
  public AccessD access;

  @Version
  public Long version;

  public String identity;
  public String title;

  public Date dateCreated = new Date();
  public Date lastUpdated = new Date();

  @PrePersist
  void prePersist() {
    lastUpdated = new Date();
  }
}
