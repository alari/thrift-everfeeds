@Typed package everfeeds.mongo

import com.google.code.morphia.annotations.Entity
import com.google.code.morphia.annotations.Id
import org.bson.types.ObjectId

/**
 * @author Dmitry Kurinskiy
 * @since 13.05.11 17:34
 */
@Entity
class OriginalD {
  @Id ObjectId id;

  Map<String,?> data;
}
