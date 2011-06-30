package everfeeds.remote.facebook

import everfeeds.remote.Remote
import everfeeds.mongo.TagD
import everfeeds.mongo.AccessD
import everfeeds.mongo.CategoryD
import everfeeds.mongo.EntryD
import everfeeds.mongo.FilterD
import everfeeds.dao.CategoryDAO
import everfeeds.remote.error.NotSupportedException
import everfeeds.remote.OAuthAccess
import everfeeds.remote.error.InvalidTokenException
import everfeeds.thrift.util.Type
import everfeeds.util.annotation.Accessor

/**
 * @author Dmitry Kurinskiy
 * @since 29.06.11 16:36
 */
@Accessor(Type.FACEBOOK)
class FacebookRemote extends Remote{
  private FacebookRaw getRaw(){
    FacebookRaw.getInstance()
  }

  @Override
  List<TagD> getActualizedTags(AccessD access) {
    []
  }

  @Override
  List<CategoryD> getActualizedCategories(AccessD access) {
    List<CategoryD> categories = CategoryDAO.instance.findAllByAccess(access)
    categories.findAll {!FacebookCategory.getByIdentity(it.identity)}.each {
        categories.remove(it)
        CategoryDAO.instance.delete(it)
    }

    FacebookCategory.values().each{FacebookCategory tc->
      if(categories.any{it.identity == tc.identity}) return;

      CategoryD c = tc.domain
      c.access = access
      CategoryDAO.instance.save(c)
      categories.add c
    }
    categories
  }

  @Override
  List<EntryD> pull(FilterD filterD, int max, int offset) {
    List<EntryD> entries = []

    // Prepare the list of categories to pull from
    List<CategoryD> categories = getActualizedCategories(filterD.access)
    if (filterD.categories.size()) {
      if (filterD.categoriesWith) categories = filterD.categories
      else categories = categories - filterD.categories
    }

    // Prepare oauth accessor
    OAuthAccess oAuthAccess = new OAuthAccess(filterD.access)

    categories.each {CategoryD category ->
      // Enum category
      FacebookCategory c = FacebookCategory.getByIdentity(category.identity)
      // TODO: check category existence!

      // Preparing parser for category
      FacebookParser parser = c.parser
      parser.access = filterD.access
      parser.category = category
      parser.tagsCache = [:]

      // Getting raw json from remote api
      def result = raw.getJson(oAuthAccess, c.url, max)
      if(result instanceof Map && (result as Map)?.error) {
        throw new InvalidTokenException()
      }
      result.data.each {
        // Parse json original
        parser.original = it
        EntryD entry = parser.result

        // Everything is okay, adding entry to result list
        if(filterAfterParse(filterD, entry)) entries.add entry
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
    null
  }
}
