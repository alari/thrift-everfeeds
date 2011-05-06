package everfeeds.mongo;

import com.google.code.morphia.annotations.*;
import org.bson.types.ObjectId;

import java.util.Date;

/**
 * @author Dmitry Kurinskiy
 * @since 06.05.11 14:40
 */
@Entity
public class CategoryD {
  @Id
  public ObjectId id;

  @Version
  public Long version;

  @Reference
  public AccessD access;

  public String identity;
  public String title;

  public Date dateCreated = new Date();
  public Date lastUpdated = new Date();

  @PrePersist
  void prePersist() {
    lastUpdated = new Date();
  }
}
