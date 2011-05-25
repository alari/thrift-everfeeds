@Typed package everfeeds.dao

import com.google.code.morphia.dao.BasicDAO
import everfeeds.MongoDB
import everfeeds.mongo.OriginalD
import org.bson.types.ObjectId

/**
 * @author Dmitry Kurinskiy
 * @since 25.05.11 22:36
 */
class OriginalDAO extends BasicDAO<OriginalD, ObjectId> {
  static private OriginalDAO instance = new OriginalDAO()

  private OriginalDAO() {
    super(MongoDB.mongo, MongoDB.morphia, MongoDB.DB_NAME)
  }

  static public final OriginalDAO getInstance() {
    instance
  }

  public OriginalD getById(String id) {
    getById(new ObjectId(id))
  }

  public OriginalD getById(ObjectId id) {
    get(id)
  }
}
