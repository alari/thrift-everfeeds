package everfeeds.remote.auth.system;

import everfeeds.remote.auth.annotation.AccessAuth;
import everfeeds.remote.auth.thrift.Credentials;
import everfeeds.remote.auth.thrift.util.AuthSystem;
import everfeeds.remote.auth.thrift.util.AuthSystem;
import everfeeds.remote.util.Package;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Dmitry Kurinskiy
 * @since 20.07.11 16:30
 */
abstract public class Auth {
  private AuthSystem system = null;


  static private Map<String, Auth> instances = new HashMap<String, Auth>();

  static public void registerInstance(Auth instance) {
    instances.put(instance.getSystem().name, instance);
  }

  static public Auth getBySystem(AuthSystem authSystem) {
    return instances.get(authSystem.name);
  }

  static public Auth getBySystemName(String systemName) {
    return instances.get(systemName);
  }

  @SuppressWarnings("unchecked")
  static public List<AuthSystem> listSystems() {
    if (instances.keySet().size() == 0) {
      try {
        for (Class c : Package.getClasses("everfeeds.remote.auth.system")) {
          if (c.getAnnotation(AccessAuth.class) != null) {
            c.getCanonicalName();
          }
        }
      } catch (ClassNotFoundException e) {
        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
      } catch (IOException e) {
        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
      }
    }
    List<AuthSystem> systems = new ArrayList<AuthSystem>();
    for (String s : instances.keySet()) {
      systems.add(instances.get(s).getSystem());
    }
    return systems;
  }

  public AuthSystem getSystem() {
    if (system == null) {
      AccessAuth a = this.getClass().getAnnotation(AccessAuth.class);
      system = new AuthSystem();
      system.setName(a.system()).setMethod(a.method()).setType(a.type());
    }
    return system;
  }

  abstract public boolean checkCredentials(Credentials credentials);
}
