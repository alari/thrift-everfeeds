package everfeeds.mongo

import com.google.code.morphia.annotations.Entity
import com.google.code.morphia.annotations.Id
import org.bson.types.ObjectId

/**
 * @author Dmitry Kurinskiy
 * @since 13.05.11 17:34
 */
@Entity
@Typed
class OriginalD {
  @Id public ObjectId id;

  public Map data;
}
