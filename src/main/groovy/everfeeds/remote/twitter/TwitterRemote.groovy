package everfeeds.remote.twitter

import everfeeds.mongo.CategoryD
import everfeeds.mongo.EntryD
import everfeeds.mongo.FilterD
import everfeeds.mongo.TagD
import everfeeds.remote.OAuthAccess
import everfeeds.remote.Remote
import everfeeds.MongoDB
import everfeeds.remote.error.InvalidTokenException
import everfeeds.remote.error.NotSupportedException
import everfeeds.dao.TagDAO
import everfeeds.mongo.AccessD
import everfeeds.dao.CategoryDAO

/**
 * @author Dmitry Kurinskiy
 * @since 14.05.11 11:31
 */
@Typed
class TwitterRemote extends Remote {
  private TwitterRaw getRaw() {
    TwitterRaw.getInstance()
  }

  @Override
  List<TagD> getActualizedTags(AccessD access) {
    List<TagD> tags = TagDAO.instance.findAllByAccess(access)
    tags?.each{TagD t->
      if(!TwitterTag.getByIdentity(t.identity)) {
        tags.remove(t)
        TagDAO.instance.delete(t)
      }
    }
    TwitterTag.values().each{TwitterTag tt->
      if(tags.any{it.identity == tt.identity}) return;

      TagD t = tt.domain
      t.access = access
      TagDAO.instance.save(t)
      tags.add t
    }
    tags
  }

  @Override
  List<CategoryD> getActualizedCategories(AccessD access) {
    List<CategoryD> categories = CategoryDAO.instance.findAllByAccess(access)
    categories?.each{CategoryD c->
      if(!TwitterCategory.getByIdentity(c.identity)) {
        categories.remove(c)
        CategoryDAO.instance.delete(c)
      }
    }
    TwitterCategory.values().each{TwitterCategory tc->
      if(categories.any{it.identity == tc.identity}) return;

      CategoryD c = tc.domain
      c.access = access
      CategoryDAO.instance.save(c)
      categories.add c
    }
    categories
  }

  private Map<String,TagD> getTagsCache(AccessD access){
    // Prepare tags cache
    Map<String, TagD> tags = [:]
    getActualizedTags(access).each{
      tags.put it.identity, it
    }
    tags
  }

  @Override
  List<EntryD> pull(FilterD filterD) throws InvalidTokenException {
    List<EntryD> entries = []

    // Prepare the list of categories to pull from
    List<CategoryD> categories = getActualizedCategories(filterD.access)
    if (filterD.categories.size()) {
      if (filterD.categoriesWith) categories = filterD.categories
      else categories = categories - filterD.categories
    }

    // Prepare tags cache
    Map<String, TagD> tags = getTagsCache(filterD.access)

    // Prepare oauth accessor
    OAuthAccess oAuthAccess = new OAuthAccess(filterD.access)

    categories.each {CategoryD category ->
      // Enum category
      TwitterCategory c = TwitterCategory.getByIdentity(category.identity)
      // TODO: check category existence!

      // Preparing parser for category
      TwitterParser parser = c.parserClass.newInstance()
      parser.access = filterD.access
      parser.category = category
      parser.tagsCache = tags

      // Getting raw json from remote api
      def result = raw.getJson(oAuthAccess, c.url)
      if(result instanceof Map && (result as Map)?.error) {
        throw new InvalidTokenException()
      }
      result.each {
        // Parse json original
        parser.original = it
        EntryD entry = parser.result

        // Perform after-parse filtering
        if (filterD.withTags.size() && !entry.tags.intersect(filterD.withTags).size()) {
          return;
        }
        if (filterD.withoutTags.size() && entry.tags.intersect(filterD.withoutTags).size()) {
          return;
        }
        if (filterD.kinds.size()) {
          if (filterD.kindsWith && !filterD.kinds.contains(entry.kind)) return;
          else if (filterD.kinds.contains(entry.kind)) return;
        }

        // Everything is okay, adding entry to result list
        entries.add entry
      }
    }

    entries
  }

  @Override
  TagD push(TagD tagD) {
    throw new NotSupportedException()
  }

  @Override
  CategoryD push(CategoryD categoryD) {
    throw new NotSupportedException()
  }

  @Override
  EntryD push(EntryD entryD) {
    if(entryD.id) {
      throw new NotSupportedException()
    }
    // Prepare oauth accessor
    OAuthAccess oAuthAccess = new OAuthAccess(entryD.access)

    Map original = (Map)raw.postJson(oAuthAccess, TwitterRawUrl.UPDATE_STATUS, [status:entryD.title])
    if(original.containsKey("error")) {
      // TODO: check error type; throw proper exception
      return null
    }

    // Enum category
    CategoryD category = CategoryDAO.getInstance().getByIdentityAndAccess(TwitterCategory.TIMELINE.identity, entryD.access)

    // Preparing parser for category
    TwitterParser parser = TwitterCategory.TIMELINE.parserClass.newInstance()
    parser.access = entryD.access
    parser.category = category
    parser.tagsCache = getTagsCache(entryD.access)
    parser.entry = entryD
    parser.original = original

    parser.result
  }
}
