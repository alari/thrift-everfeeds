package everfeeds.remote.metaweblog

import everfeeds.remote.Remote
import everfeeds.mongo.EntryD
import everfeeds.mongo.FilterD
import everfeeds.mongo.TagD
import everfeeds.mongo.CategoryD

/**
 * @author Dmitry Kurinskiy
 * @since 31.05.11 14:49
 */
class MetaweblogRemote extends Remote{
  @Override
  List<EntryD> pull(FilterD filterD) {
    return null
  }

  @Override
  TagD push(TagD tagD) {
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
