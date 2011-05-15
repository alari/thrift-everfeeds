package everfeeds.remote.twitter

import everfeeds.remote.Remote
import everfeeds.mongo.EntryD
import everfeeds.mongo.CategoryD
import everfeeds.mongo.FilterD
import everfeeds.remote.OAuthAccess
import everfeeds.mongo.TagD
import everfeeds.mongo.AccessD

/**
 * @author Dmitry Kurinskiy
 * @since 14.05.11 11:31
 */
@Typed
class TwitterRemote extends Remote{
  TwitterRemote(AccessD access) {
    super(access)
  }

  private TwitterRaw getRaw(){
    TwitterRaw.getInstance()
  }

  @Override
  List<EntryD> pull(FilterD filterD) {
    List<EntryD> entries = []

    // Prepare the list of categories to pull from
    List<CategoryD> categories = ds.createQuery(CategoryD).filter("access", access).asList()
    if(filterD.categories.size()) {
      if(filterD.categoriesWith) categories = filterD.categories
      else categories = categories - filterD.categories
    }

    // Prepare tags cache
    Map<String,TagD> tags = [:]
    ds.createQuery(TagD).filter("access", access).asList().each {
      tags.put it.identity, it
    }

    // Prepare oauth accessor
    OAuthAccess oAuthAccess = new OAuthAccess(access)

    categories.each{CategoryD category->
      // Enum category
      TwitterCategory c = TwitterCategory.getByIdentity(category.identity)

      // Preparing parser for category
      TwitterParser parser = c.parserClass.newInstance()
      parser.access = access
      parser.category = category
      parser.tagsCache = tags

      // Getting raw json from remote api
      raw.getJson(oAuthAccess, c.url).each{
        // Parse json original
        parser.original = it
        EntryD entry = parser.result

        // Perform after-parse filtering
        if(filterD.withTags.size() && !entry.tags.intersect(filterD.withTags).size()) {
          return;
        }
        if(filterD.withoutTags.size() && entry.tags.intersect(filterD.withoutTags).size()) {
          return;
        }
        if(filterD.kinds.size()) {
          if(filterD.kindsWith && !filterD.kinds.contains(entry.kind)) return;
          else if(filterD.kinds.contains(entry.kind)) return;
        }

        // Everything is okay, adding entry to result list
        entries.add entry
      }
    }

    entries
  }
}
