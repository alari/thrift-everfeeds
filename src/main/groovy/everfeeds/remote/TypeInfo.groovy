package everfeeds.remote

import everfeeds.thrift.ttype.AccessTypeInfo
import everfeeds.thrift.ttype.AccessType
import everfeeds.util.Package
import everfeeds.thrift.util.Type
import everfeeds.util.annotation.Accessor

/**
 * @author Dmitry Kurinskiy
 * @since 30.06.11 12:08
 */
class TypeInfo {
  static private Map<AccessType,AccessTypeInfo> infoMap = [:]
  static private Map<Type,Class<Remote>> classMap = [:]

  static {
    Package.getClasses(TypeInfo.class.package.name).each{
      if(!it.isAnnotationPresent(Accessor)) return;
      Type type = ((Accessor)it.getAnnotation(Accessor)).value()
      classMap.put(type, it)
      buildInfo(type, it)
    }
  }

  static private void buildInfo(Type type, Class<Remote> cls) {
    AccessTypeInfo info = new AccessTypeInfo()[type:type.toThrift(), isRemote: true]
    infoMap.put(type.toThrift(), info)
  }

  static public AccessTypeInfo getInfo(AccessType type) {
    infoMap.containsKey(type) ? infoMap.get(type) : new AccessTypeInfo()[type: type, isRemote: false]
  }
}
