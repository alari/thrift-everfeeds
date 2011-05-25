@Typed package everfeeds.dao

import com.google.code.morphia.dao.BasicDAO
import everfeeds.MongoDB
import everfeeds.mongo.TokenD
import org.bson.types.ObjectId
import everfeeds.mongo.ApplicationD
import everfeeds.mongo.AccountD

/**
 * @author Dmitry Kurinskiy
 * @since 25.05.11 22:36
 */
class TokenDAO extends BasicDAO<TokenD, ObjectId> {
  static private TokenDAO instance = new TokenDAO()

  private TokenDAO() {
    super(MongoDB.mongo, MongoDB.morphia, MongoDB.DB_NAME)
  }

  static public final TokenDAO getInstance() {
    instance
  }

  public TokenD getById(String id) {
    getById(new ObjectId(id))
  }

  public TokenD getById(ObjectId id) {
    get(id)
  }

  public TokenD getByApplicationAndAccount(ApplicationD applicationD, AccountD accountD) {
    createQuery().filter("application", applicationD).filter("account", accountD).get()
  }
}
