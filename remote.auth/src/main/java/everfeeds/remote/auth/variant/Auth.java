package everfeeds.remote.auth.variant;

import everfeeds.remote.auth.annotation.AccessAuth;
import everfeeds.remote.auth.thrift.Credentials;
import everfeeds.remote.auth.thrift.util.AuthVariant;
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
  private AuthVariant variant = null;


  static private Map<String, Auth> instances = new HashMap<String, Auth>();

  static public void registerInstance(Auth instance) {
    instances.put(instance.getVariant().system, instance);
  }

  static public Auth getByVariant(AuthVariant authVariant) {
    return instances.get(authVariant.system);
  }

  static public Auth getBySystem(String systemName) {
    return instances.get(systemName);
  }

  @SuppressWarnings("unchecked")
  static public List<AuthVariant> listVariants() {
    if (instances.keySet().size() == 0) {
      try {
        for (Class c : Package.getClasses("everfeeds.remote.auth.variant")) {
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
    List<AuthVariant> variants = new ArrayList<AuthVariant>();
    for (String s : instances.keySet()) {
      variants.add(instances.get(s).getVariant());
    }
    return variants;
  }

  public AuthVariant getVariant() {
    if (variant == null) {
      AccessAuth a = this.getClass().getAnnotation(AccessAuth.class);
      variant = new AuthVariant();
      variant.setSystem(a.system()).setMethod(a.method()).setType(a.type());
    }
    return variant;
  }

  abstract public boolean checkCredentials(Credentials credentials);
}
