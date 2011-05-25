@Typed package everfeeds.dao

import com.google.code.morphia.dao.BasicDAO
import everfeeds.MongoDB
import everfeeds.mongo.ApplicationD
import org.bson.types.ObjectId

/**
 * @author Dmitry Kurinskiy
 * @since 25.05.11 22:36
 */
class ApplicationDAO extends BasicDAO<ApplicationD, ObjectId> {
  static private ApplicationDAO instance = new ApplicationDAO()

  private ApplicationDAO() {
    super(MongoDB.mongo, MongoDB.morphia, MongoDB.DB_NAME)
  }

  static public final ApplicationDAO getInstance() {
    instance
  }

  public ApplicationD getById(String id) {
    getById(new ObjectId(id))
  }

  public ApplicationD getById(ObjectId id) {
    get(id)
  }

  public ApplicationD getByKey(String key) {
    createQuery().filter("key", key).get()
  }

  public List<ApplicationD> list(){
    createQuery().asList()
  }
}
