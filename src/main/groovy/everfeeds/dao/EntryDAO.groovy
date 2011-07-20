@Typed package everfeeds.dao

import com.google.code.morphia.dao.BasicDAO
import com.google.code.morphia.query.Query
import everfeeds.MongoDB
import everfeeds.mongo.AccountD
import everfeeds.mongo.EntryD
import everfeeds.mongo.FilterD
import org.bson.types.ObjectId
import everfeeds.thrift.domain.Entry
import everfeeds.mongo.AccessD

/**
 * @author Dmitry Kurinskiy
 * @since 25.05.11 22:36
 */
class EntryDAO extends BasicDAO<EntryD, ObjectId> {
  static private EntryDAO instance = new EntryDAO()

  private EntryDAO() {
    super(MongoDB.mongo, MongoDB.morphia, MongoDB.DB_NAME)
  }

  static public final EntryDAO getInstance() {
    instance
  }

  public EntryD getById(String id) {
    if(!ObjectId.isValid(id)) return null
    getById(new ObjectId(id))
  }

  public EntryD getById(ObjectId id) {
    get(id)
  }

  public EntryD getByIdentityAndAccess(String identity, AccessD access) {
    createQuery().filter("identity", identity).filter("access", access).get()
  }

  public EntryD getByThrift(Entry entry, AccessD access){
    entry.id && ObjectId.isValid(entry.id) ? getById(entry.id) : getByIdentityAndAccess(entry.identity, access)
  }

  public List<EntryD> findAllMashNew(AccountD account, long splitDate, short maxCount) {
    findAllMashNew(account, new Date(splitDate), maxCount)
  }

  public List<EntryD> findAllMashNew(AccountD account, Date splitDate, short maxCount) {
    createQuery().filter("account", account).filter("dateCreated >", splitDate).limit(maxCount).order("-datePlaced").asList()
  }

  public List<EntryD> findAllMash(AccountD account, long splitDate, short maxCount, short page) {
    findAllMash(account, new Date(splitDate), maxCount, page)
  }

  public List<EntryD> findAllMash(AccountD account, Date splitDate, short maxCount, short page) {
    createQuery().filter("account", account).filter("dateCreated <", splitDate).limit(maxCount).offset(page * maxCount).order("-datePlaced").asList()
  }

  public List<EntryD> findAllFilteredNew(FilterD filter) {
    return getFilterQuery(filter).order("-datePlaced").filter("dateCreated >", filter.splitDate).asList();
  }

  public List<EntryD> findAllFiltered(FilterD filter, short maxCount, short page) {
    return getFilterQuery(filter).limit(maxCount).offset(page * maxCount).order("-datePlaced").filter("dateCreated <", filter.splitDate).asList();
  }

  private Query<EntryD> getFilterQuery(FilterD filterD) {
    Query<EntryD> query = createQuery();
    query.filter("access", filterD.access);
    if (filterD.categories.size() > 0) {
      if (filterD.categoriesWith) {
        query.filter("category in", filterD.categories);
      } else {
        query.filter("category nin", filterD.categories);
      }
    }
    if (filterD.withTags.size() > 0) {
      query.filter("tags all", filterD.withTags);
    }
    if (filterD.withoutTags.size() > 0) {
      query.filter("tags nin", filterD.withoutTags);
    }
    if (filterD.kinds.size() > 0) {
      if (filterD.kindsWith) {
        query.filter("kind in", filterD.kinds);
      } else {
        query.filter("kind nin", filterD.kinds);
      }
    }
    if (filterD.unreadOnly) {
      query.filter("isRead", false);
    }
    query
  }
}
