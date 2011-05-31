package everfeeds.remote.evernote

import everfeeds.remote.Remote
import everfeeds.mongo.EntryD
import everfeeds.mongo.FilterD
import everfeeds.mongo.TagD
import everfeeds.mongo.CategoryD
import everfeeds.mongo.AccessD

/**
 * @author Dmitry Kurinskiy
 * @since 31.05.11 14:49
 */
class EvernoteRemote extends Remote{
  @Override
  List<EntryD> pull(FilterD filterD) {
    return null
  }

  @Override
  TagD push(TagD tagD) {
    if(tagD.identity) {

    }
    return null
  }

  @Override
  List<TagD> getActualizedTags(AccessD access) {
    return null
  }

  @Override
  List<CategoryD> getActualizedCategories(AccessD access) {
    return null
  }

  @Override
  CategoryD push(CategoryD categoryD) {
    return null
  }

  @Override
  EntryD push(EntryD entryD) {
    return null
  }
}
