package everfeeds.remote

import everfeeds.mongo.EntryD
import everfeeds.thrift.util.Kind
import everfeeds.mongo.AccessD
import everfeeds.MongoDB
import everfeeds.mongo.OriginalD
import everfeeds.mongo.EntryContentD
import everfeeds.mongo.AuthorD
import everfeeds.mongo.CategoryD
import everfeeds.mongo.TagD
import everfeeds.mongo.FilterD
import com.google.code.morphia.Datastore

/**
 * @author Dmitry Kurinskiy
 * @since 14.05.11 12:20
 */
@Typed
abstract class Parser {
  protected original
  protected EntryD entry
  protected AccessD access
  protected CategoryD categoryD
  protected Map<String,TagD> tagsCache = [:]
  boolean isParsed = false

  Parser(){}

  Parser(final original, AccessD access, EntryD entry = null) {
    this.original = original
    this.access = access
    this.entry = entry ?: new EntryD()
  }

  public void setTagsCache(Map<String,TagD> cache) {
    tagsCache = cache
    entry = null
    isParsed = false
  }

  public void setEntry(EntryD entryD) {
    entry = entryD
    isParsed = false
  }

  public void setOriginal(original) {
    this.original = original
    entry = null
    isParsed = false
  }

  public void setAccess(AccessD accessD) {
    access = accessD
    entry = null
    isParsed = false
  }

  public void setCategory(CategoryD categorySet) {
    categoryD = categorySet
    entry = null
    isParsed = false
  }

  @Typed(TypePolicy.MIXED)
  public EntryD getResult() {
    if(!isParsed) {
      if(!access) {
        throw new Exception("Cannot parse original without an accessD being set")
      }
      if(!original) {
        throw new Exception("Nothing to parse: no original provided")
      }
      if(!entry) {
        entry = ds.createQuery(EntryD)
            .filter("access", access)
            .filter("kind", kind)
            .filter("identity", identity)
            .get() ?: new EntryD()
      }
      isParsed = true

      entry.access = access
      entry.account = access.account

      entry.original = originalD
      entry.content = content

      ["isAuthor","isPublicAvailable","isFavorite","isRead",
          "identity","title","description","sourceUrl",
          "author","kind","datePlaced","tags","filters","category"].each{
        entry."${it}" = this."${it}"
      }
    }
    entry
  }

  public EntryD save(){
    ds.save(entry.original)
    if(entry.content){
      ds.save(entry.content)
    }
    ds.save(result)
    entry
  }

  protected Datastore getDs(){
    MongoDB.getDS();
  }

  private OriginalD getOriginalD(){
    if(!entry.original) {
      entry.original = new OriginalD()
    }
    // set original as map
    entry.original.data = original as Map
    entry.original
  }

  abstract public boolean getIsAuthor()

  abstract public boolean getIsPublicAvailable()

  abstract public boolean getIsFavorite()

  abstract public boolean getIsRead()

  abstract public String getIdentity()

  abstract public String getTitle()

  abstract public String getDescription()

  public String getSourceUrl(){""}

  abstract public EntryContentD getContent()

  abstract public AuthorD getAuthor()

  abstract public Kind getKind()

  abstract public Date getDatePlaced()

  abstract public List<TagD> getTags()

  public List<FilterD> getFilters(){null}

    public CategoryD getCategory(){
    if(!categoryD) {
      throw new Exception("Category is not set for entry")
    }
    categoryD
  }
}
