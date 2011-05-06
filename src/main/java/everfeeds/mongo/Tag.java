package everfeeds.mongo;

import com.google.code.morphia.annotations.*;
import org.bson.types.ObjectId;

import java.util.Date;

/**
 * @author Dmitry Kurinskiy
 * @since 06.05.11 14:40
 */
@Entity
public class Tag {
  @Id
  ObjectId id;

  @Reference
  Access access;

  @Version
  Long version;

  String identity;
  String title;

  Date dateCreated = new Date();
  Date lastUpdated = new Date();

  @PrePersist
  void prePersist() {
    lastUpdated = new Date();
  }
}
