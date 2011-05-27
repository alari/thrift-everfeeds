@Typed package everfeeds.dao

import com.google.code.morphia.dao.BasicDAO
import everfeeds.MongoDB
import everfeeds.mongo.CategoryD
import org.bson.types.ObjectId
import everfeeds.mongo.AccessD
import everfeeds.thrift.domain.Category

/**
 * @author Dmitry Kurinskiy
 * @since 25.05.11 22:36
 */
class CategoryDAO extends BasicDAO<CategoryD, ObjectId> {
  static private CategoryDAO instance = new CategoryDAO()

  private CategoryDAO() {
    super(MongoDB.mongo, MongoDB.morphia, MongoDB.DB_NAME)
  }

  static public final CategoryDAO getInstance() {
    instance
  }

  public CategoryD getById(String id) {
    if(!ObjectId.isValid(id)) return null
    getById(new ObjectId(id))
  }

  public CategoryD getById(ObjectId id) {
    get(id)
  }

  public List<CategoryD> findAllByAccess(AccessD access){
    createQuery().filter("access", access).asList()
  }

  public CategoryD getByIdAndAccess(String id, AccessD access) {
    if(!ObjectId.isValid(id)) return null
    getByIdAndAccess(new ObjectId(id), access)
  }

  public CategoryD getByIdAndAccess(ObjectId id, AccessD access) {
    createQuery().filter("id", id).filter("access", access).get()
  }

  public CategoryD getByIdentityAndAccess(String identity, AccessD access) {
    createQuery().filter("identity", identity).filter("access", access).get()
  }

  public CategoryD getByThrift(Category category, AccessD accessD){
    ObjectId.isValid(category.id) ? getByIdAndAccess(category.id, accessD) : getByIdentityAndAccess(category.identity, accessD)
  }
}
