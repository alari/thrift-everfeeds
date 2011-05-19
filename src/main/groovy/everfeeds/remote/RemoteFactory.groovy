package everfeeds.remote

import everfeeds.thrift.util.Type
import everfeeds.remote.twitter.TwitterRemote
import everfeeds.Environment
import everfeeds.remote.twitter.TwitterRaw

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
          if(Environment.isTesting()) {
            instance = [getRaw: {System.out.println("I've overrided getRaw!");TwitterRaw.getInstance()}] as TwitterRemote
          }
          break;
      }
      instances.put(type, instance)
    }
    instances.get(type)
  }
}
