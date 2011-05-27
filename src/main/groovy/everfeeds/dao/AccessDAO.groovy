@Typed package everfeeds.dao

import com.google.code.morphia.dao.BasicDAO
import everfeeds.MongoDB
import everfeeds.mongo.AccessD
import org.bson.types.ObjectId
import everfeeds.mongo.AccountD
import everfeeds.thrift.util.Type
import everfeeds.thrift.ttype.AccessType
import everfeeds.thrift.domain.Access

/**
 * @author Dmitry Kurinskiy
 * @since 25.05.11 22:36
 */
class AccessDAO extends BasicDAO<AccessD, ObjectId> {
  static private AccessDAO instance = new AccessDAO()

  private AccessDAO() {
    super(MongoDB.mongo, MongoDB.morphia, MongoDB.DB_NAME)
  }

  static public final AccessDAO getInstance() {
    instance
  }

  public AccessD getById(String id) {
    if(!ObjectId.isValid(id)) return null
    getById(new ObjectId(id))
  }

  public AccessD getById(ObjectId id) {
    get(id)
  }

  public AccessD getByThrift(Access access){
    AccessD accessD
    if (access.id != null && !access.id.isEmpty()) {
      accessD = getById(access.id);
      if (accessD != null) {
        return accessD;
      }
    }
    getByIdentityAndType(access.identity, access.type)
  }

  public AccessD getByIdentityAndType(String identity, AccessType type){
    getByIdentityAndType(identity, Type.getByThrift(type))
  }

  public AccessD getByIdentityAndType(String identity, Type type){
    createQuery().filter("identity", identity)
                  .filter("type", type).get();
  }

  public AccessD getByIdAndAccount(String id, AccountD account){
    if(!ObjectId.isValid(id)) return null
    getByIdAndAccount(new ObjectId(id), account)
  }

  public AccessD getByIdAndAccount(ObjectId id, AccountD account){
    createQuery().filter("id", id).filter("account", account).get();
  }

  public List<AccessD> findAllByAccount(AccountD account){
    createQuery().filter("account", account).asList()
  }
}
