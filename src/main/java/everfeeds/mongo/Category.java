package everfeeds.mongo;

import com.google.code.morphia.annotations.*;
import org.bson.types.ObjectId;

import java.util.Date;

/**
 * @author Dmitry Kurinskiy
 * @since 06.05.11 14:40
 */
@Entity
public class Category {
  @Id
  ObjectId id;

  @Version
  Long version;

  @Reference
  Access access;

  String identity;
  String title;

  Date dateCreated = new Date();
  Date lastUpdated = new Date();

  @PrePersist
  void prePersist() {
    lastUpdated = new Date();
  }
}
