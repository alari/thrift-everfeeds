package everfeeds.remote.twitter

import everfeeds.remote.Remote
import everfeeds.mongo.EntryD
import everfeeds.mongo.CategoryD

/**
 * @author Dmitry Kurinskiy
 * @since 14.05.11 11:31
 */
@Typed
class TwitterRemote extends Remote{
  @Override
  List<EntryD> get(CategoryD categoryD) {
    return null
  }
}
