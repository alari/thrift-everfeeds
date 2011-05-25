@Typed package everfeeds.dao

import com.google.code.morphia.dao.BasicDAO
import everfeeds.MongoDB
import everfeeds.mongo.AccountD
import org.bson.types.ObjectId

/**
 * @author Dmitry Kurinskiy
 * @since 25.05.11 22:36
 */
class AccountDAO extends BasicDAO<AccountD, ObjectId> {
  static private AccountDAO instance = new AccountDAO()

  private AccountDAO() {
    super(MongoDB.mongo, MongoDB.morphia, MongoDB.DB_NAME)
  }

  static public final AccountDAO getInstance() {
    instance
  }

  public AccountD getById(String id) {
    getById(new ObjectId(id))
  }

  public AccountD getById(ObjectId id) {
    get(id)
  }
}
