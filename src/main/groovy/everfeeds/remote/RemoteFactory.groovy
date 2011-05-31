package everfeeds.remote

import everfeeds.thrift.util.Type
import everfeeds.remote.twitter.TwitterRemote
import everfeeds.Environment
import everfeeds.remote.twitter.TwitterRaw
import everfeeds.remote.evernote.EvernoteRemote
import everfeeds.remote.metaweblog.MetaweblogRemote

/**
 * @author Dmitry Kurinskiy
 * @since 19.05.11 12:49
 */
@Typed
class RemoteFactory {
  static private Map<Type,Remote> instances = [:]

  static public Remote getInstance(Type type){
    if(!instances.containsKey(type)) {
      Remote instance
      switch(type){
        case Type.TWITTER:
          instance = new TwitterRemote()
          break;
        case Type.EVERNOTE:
          instance = new EvernoteRemote()
              break;
        case Type.METAWEBLOG:
          instance = new MetaweblogRemote()
              break;
      }
      instances.put(type, instance)
    }
    instances.get(type)
  }
}
