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
    AccessTypeInfo info = new AccessTypeInfo()[accessType:type.toThrift(), isRemote: true]

    info.withEntries = true
    info.withFiles = false

    info.pullEntries = !cls.getMethod("pull").getAnnotation(NotImplemented) && !cls.getMethod("pull").getAnnotation(NotSupported)
    info.pullFiles = false

    info.pushCategory = !cls.getMethod("push", CategoryD).getAnnotation(NotImplemented) && !cls.getMethod("push", CategoryD).getAnnotation(NotSupported)
    info.pushTag = !cls.getMethod("push", TagD).getAnnotation(NotImplemented) && !cls.getMethod("push", TagD).getAnnotation(NotSupported)
    info.pushEntry = !cls.getMethod("push", EntryD).getAnnotation(NotImplemented) && !cls.getMethod("push", EntryD).getAnnotation(NotSupported)

    info.updateEntry = !cls.getMethod("push", EntryD).getAnnotation(NoUpdatesSupported)

    infoMap.put(type.toThrift(), info)
  }

  static public AccessTypeInfo getInfo(AccessType type) {
    infoMap.containsKey(type) ? infoMap.get(type) : (new AccessTypeInfo()[accessType: type, isRemote: false])
  }
}
