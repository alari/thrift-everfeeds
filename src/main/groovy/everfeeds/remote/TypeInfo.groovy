@Typed package everfeeds.remote

import everfeeds.thrift.ttype.AccessTypeInfo
import everfeeds.thrift.ttype.AccessType
import everfeeds.util.Package
import everfeeds.thrift.util.Type
import everfeeds.util.annotation.Accessor
import everfeeds.util.annotation.NotImplemented
import everfeeds.util.annotation.NotSupported
import everfeeds.mongo.CategoryD
import everfeeds.mongo.TagD
import everfeeds.mongo.EntryD
import everfeeds.util.annotation.NoUpdatesSupported
import java.lang.reflect.Method

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

  static public Class<Remote> getRemoteClass(Type type){
    classMap.get(type)
  }

  static private void buildInfo(Type type, Class<Remote> cls) {
    AccessTypeInfo info = new AccessTypeInfo()[accessType:type.toThrift(), isRemote: true]

    info.withEntries = true
    info.withFiles = false

    final Closure isAvailable = {Method m -> !m.getAnnotation(NotImplemented) && !m.getAnnotation(NotSupported)}

    info.pullEntries = isAvailable(cls.getMethod("pull"))
    info.pullFiles = false

    info.pushCategory = isAvailable(cls.getMethod("push", CategoryD))
    info.pushTag = isAvailable(cls.getMethod("push", TagD))
    info.pushEntry = isAvailable(cls.getMethod("push", EntryD))

    info.updateEntry = info.pushEntry && !cls.getMethod("push", EntryD).getAnnotation(NoUpdatesSupported)

    infoMap.put(type.toThrift(), info)
  }

  static public AccessTypeInfo getInfo(AccessType type) {
    infoMap.containsKey(type) ? infoMap.get(type) : (new AccessTypeInfo()[accessType: type, isRemote: false])
  }
}
