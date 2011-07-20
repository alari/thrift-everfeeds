@Typed package everfeeds.remote

import everfeeds.thrift.util.Type

/**
 * @author Dmitry Kurinskiy
 * @since 19.05.11 12:49
 */
class RemoteFactory {
  static private Map<Type,Remote> instances = [:]

  static public Remote getInstance(Type type){
    if(!instances.containsKey(type)) {
      instances.put type, TypeInfo.getRemoteClass(type)?.newInstance()
    }
    instances.get(type)
  }
}
