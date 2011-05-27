@Typed package everfeeds.dao

import com.google.code.morphia.dao.BasicDAO
import everfeeds.MongoDB
import everfeeds.mongo.TagD
import org.bson.types.ObjectId
import everfeeds.mongo.AccountD
import everfeeds.mongo.AccessD
import everfeeds.thrift.domain.Tag

/**
 * @author Dmitry Kurinskiy
 * @since 25.05.11 22:36
 */
class TagDAO extends BasicDAO<TagD, ObjectId> {
  static private TagDAO instance = new TagDAO()

  private TagDAO() {
    super(MongoDB.mongo, MongoDB.morphia, MongoDB.DB_NAME)
  }

  static public final TagDAO getInstance() {
    instance
  }

  public TagD getById(String id) {
    if(!ObjectId.isValid(id)) return null
    getById(new ObjectId(id))
  }

  public TagD getById(ObjectId id) {
    get(id)
  }

  public TagD getByIdAndAccess(String id, AccessD access) {
    if(!ObjectId.isValid(id)) return null
    getByIdAndAccess(new ObjectId(id), access)
  }

  public TagD getByIdAndAccess(ObjectId id, AccessD access) {
    createQuery().filter("id", id).filter("access", access).get()
  }

  public TagD getByIdentityAndAccess(String identity, AccessD access) {
    createQuery().filter("identity", identity).filter("access", access).get()
  }

  public List<TagD> findAllByAccess(AccessD access){
    createQuery().filter("access", access).asList()
  }

  public TagD getByThrift(Tag tag, AccessD accessD){
    tag.id ? getByIdAndAccess(tag.id, accessD) : getByIdentityAndAccess(tag.identity, accessD)
  }
}
