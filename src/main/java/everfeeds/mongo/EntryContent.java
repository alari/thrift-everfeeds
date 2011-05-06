package everfeeds.mongo;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Version;
import org.bson.types.ObjectId;

/**
 * @author Dmitry Kurinskiy
 * @since 06.05.11 14:40
 */
@Entity
public class EntryContent {
  @Id
  ObjectId id;

  @Version
  Long version;

  String content;
}
