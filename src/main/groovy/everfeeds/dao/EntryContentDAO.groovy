package everfeeds.dao

import com.google.code.morphia.dao.BasicDAO
import everfeeds.MongoDB
import everfeeds.mongo.EntryContentD
import org.bson.types.ObjectId

/**
 * @author Dmitry Kurinskiy
 * @since 25.05.11 22:36
 */
class EntryContentDAO extends BasicDAO<EntryContentD, ObjectId> {
  static private EntryContentDAO instance = new EntryContentDAO()

  private EntryContentDAO() {
    super(MongoDB.mongo, MongoDB.morphia, MongoDB.DB_NAME)
  }

  static public final EntryContentDAO getInstance() {
    instance
  }

  public EntryContentD getById(String id) {
    if(!ObjectId.isValid(id)) return null
    getById(new ObjectId(id))
  }

  public EntryContentD getById(ObjectId id) {
    get(id)
  }
}
