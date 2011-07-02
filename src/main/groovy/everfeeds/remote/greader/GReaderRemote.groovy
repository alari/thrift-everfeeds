@Typed package everfeeds.remote.greader

import everfeeds.util.OAuthAccess
import everfeeds.remote.Remote
import everfeeds.util.error.InvalidTokenException
import everfeeds.util.error.NotSupportedException
import everfeeds.thrift.util.Type
import everfeeds.util.annotation.Accessor
import everfeeds.util.annotation.NotImplemented
import everfeeds.util.annotation.NotSupported
import everfeeds.mongo.*

import everfeeds.util.RemoteUtils

/**
 * @author Dmitry Kurinskiy
 * @since 02.07.11 12:37
 */
@Accessor(Type.GREADER)
class GReaderRemote extends Remote {
  private GReaderRaw getRaw() {
    GReaderRaw.instance
  }

  @Override
  List<TagD> getActualizedTags(AccessD access) {
    OAuthAccess oAuthAccess = new OAuthAccess(access)
    List jsonTags = ((Map)raw.getJson(oAuthAccess, GReaderRawUrl.TAG_LIST))?.tags
    RemoteUtils.actualizeTags(access, jsonTags, {Map o->o.id}, {Map o->o.id.toString().substring(o.id.toString().lastIndexOf("/") + 1)})
  }

  @Override
  List<CategoryD> getActualizedCategories(AccessD access) {
    OAuthAccess oAuthAccess = new OAuthAccess(access)
    List jsonCategories = ((Map)raw.getJson(oAuthAccess, GReaderRawUrl.SUBSCRIPTION_LIST))?.subscriptions
    RemoteUtils.actualizeCategories(access, jsonCategories, {Map o->o.id}, {Map o->o.title})
  }

  @Override
  List<EntryD> pull(FilterD filterD, int max, int offset) {
    GReaderRawUrl url
    String urlSuffix = ""

    if (filterD.categoriesWith && filterD.categories.size() == 1) {
      url = GReaderRawUrl.CONTENT_BASE
      urlSuffix = filterD.categories.first().identity
    } else {
      url = GReaderRawUrl.CONTENT_READER_LIST
    }

    urlSuffix += "?ck=" + System.currentTimeMillis() / 1000
    urlSuffix += "&n=" + max

    OAuthAccess oAuthAccess = new OAuthAccess(filterD.access)

    // Preparing parser for category
    GReaderParser parser = new GReaderParser()
    parser.access = filterD.access
    parser.tagsCache = RemoteUtils.getTagsCache(filterD.access, this)

    // Getting raw json from remote api
    def result = (Map)raw.getJson(oAuthAccess, url, urlSuffix)
    if (result?.error) {
      throw new InvalidTokenException()
    }

    List<EntryD> entries = []
    Map<String,CategoryD> categoryDMap = RemoteUtils.getCategoriesCache(filterD.access, this)

    for (Map obj in (result?.items as List)) {
      // Parse json original
      parser.category = categoryDMap.get((obj.origin as Map).streamId)
      parser.original = obj
      EntryD entry = parser.result

      // Everything is okay, adding entry to result list
      if (RemoteUtils.filterAfterParse(filterD, entry)) entries.add entry
    }

    entries
  }

  @Override
  @NotImplemented
  TagD push(TagD tagD) {
    return null
  }

  @Override
  @NotImplemented
  CategoryD push(CategoryD categoryD) {
    return null
  }

  @Override
  @NotSupported
  EntryD push(EntryD entryD) {
    throw new NotSupportedException()
  }
}
