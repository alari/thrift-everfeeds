package everfeeds.remote.twitter

import everfeeds.mongo.CategoryD
import everfeeds.mongo.EntryD
import everfeeds.mongo.FilterD
import everfeeds.mongo.TagD
import everfeeds.remote.OAuthAccess
import everfeeds.remote.Remote
import everfeeds.MongoDB
import everfeeds.remote.InvalidTokenException

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
  List<EntryD> pull(FilterD filterD) throws InvalidTokenException {
    List<EntryD> entries = []

    // Prepare the list of categories to pull from
    List<CategoryD> categories = ds.createQuery(CategoryD).filter("access", filterD.access).asList()
    if(!categories.size()) {
      TwitterCategory.values().collect { it.domain }.each{CategoryD c->
        c.access = filterD.access
        MongoDB.getDS().save(c)
        categories.add c
      }
    }
    if (filterD.categories.size()) {
      if (filterD.categoriesWith) categories = filterD.categories
      else categories = categories - filterD.categories
    }

    // Prepare tags cache
    Map<String, TagD> tags = [:]
    List<TagD> tagsList = ds.createQuery(TagD).filter("access", filterD.access).asList()
    if(!tagsList.size()) {
      TwitterTag.values().collect { it.domain }.each{TagD t->
        t.access = filterD.access
        MongoDB.getDS().save(t)
        tagsList.add t
      }
    }
    tagsList.each {
      tags.put it.identity, it
    }

    // Prepare oauth accessor
    OAuthAccess oAuthAccess = new OAuthAccess(filterD.access)

    categories.each {CategoryD category ->
      // Enum category
      TwitterCategory c = TwitterCategory.getByIdentity(category.identity)

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
        System.out.println("test:"+it);
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
}
