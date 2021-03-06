package everfeeds.remote.auth.system;

import everfeeds.remote.auth.annotation.AccessAuth;
import everfeeds.remote.auth.thrift.Credentials;
import everfeeds.remote.auth.thrift.ex.AuthConnectionError;
import everfeeds.remote.auth.thrift.util.AuthSystem;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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

  static public Auth getBySystem(AuthSystem authSystem) {
    return instances.get(authSystem.name);
  }

  static public Auth getBySystemName(String systemName) {
    return instances.get(systemName);
  }

  @SuppressWarnings("unchecked")
  static public List<AuthSystem> listSystems() {
    List<AuthSystem> systems = new ArrayList<AuthSystem>();
    for (String s : instances.keySet()) {
      systems.add(instances.get(s).getSystem());
    }
    return systems;
  }

  static {
     AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
    ctx.scan(Auth.class.getPackage().getName());
    ctx.refresh();

    for(Auth a: ctx.getBeansOfType(Auth.class).values()) {
      Logger.getLogger(Auth.class).debug(a.getSystem().getName());
      instances.put(a.getSystem().getName(), a);
    }
  }

  public AuthSystem getSystem() {
    if (system == null) {
      AccessAuth a = this.getClass().getAnnotation(AccessAuth.class);
      system = new AuthSystem();
      system.setName(a.system()).setMethod(a.method()).setType(a.type());
    }
    return system;
  }

  abstract public boolean checkCredentials(Credentials credentials) throws AuthConnectionError;
}
