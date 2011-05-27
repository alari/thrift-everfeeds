@Typed package everfeeds.dao

import com.google.code.morphia.dao.BasicDAO
import everfeeds.MongoDB
import everfeeds.mongo.FilterD
import org.bson.types.ObjectId

/**
 * @author Dmitry Kurinskiy
 * @since 25.05.11 22:36
 */
class FilterDAO extends BasicDAO<FilterD, ObjectId> {
  static private FilterDAO instance = new FilterDAO()

  private FilterDAO() {
    super(MongoDB.mongo, MongoDB.morphia, MongoDB.DB_NAME)
  }

  static public final FilterDAO getInstance() {
    instance
  }

  public FilterD getById(String id) {
    if(!ObjectId.isValid(id)) return null
    getById(new ObjectId(id))
  }

  public FilterD getById(ObjectId id) {
    get(id)
  }
}
