package everfeeds.remote.discovery.system;

import everfeeds.remote.auth.thrift.Credentials;
import everfeeds.remote.auth.thrift.util.AuthSystem;
import everfeeds.remote.discovery.annotation.AccessDiscovery;
import everfeeds.remote.discovery.thrift.Content;
import everfeeds.remote.discovery.thrift.Node;
import everfeeds.remote.discovery.thrift.Query;
import everfeeds.remote.util.Package;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Dmitry Kurinskiy
 * @since 29.07.11 18:25
 */
abstract public class DiscoverySystem {
  static private Map<String, DiscoverySystem> instances = new HashMap<String, DiscoverySystem>();

  static public void registerInstance(DiscoverySystem instance) {
    instances.put(instance.getSystemName(), instance);
  }

  static public DiscoverySystem getBySystemName(String system) {
    return instances.get(system);
  }

  static public DiscoverySystem getBySystem(AuthSystem system) {
    if(system == null || system.name == null) {
      return null;
    }
    return instances.get(system.name);
  }

  static {
    try {
        for (Class c : Package.getClasses("everfeeds.remote.discovery.system")) {
          if (c.getAnnotation(AccessDiscovery.class) != null) {
            c.getCanonicalName();
          }
        }
      } catch (ClassNotFoundException e) {
        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
      } catch (IOException e) {
        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
      }
  }

  protected String getSystemName() {
    return this.getClass().getAnnotation(AccessDiscovery.class).value();
  }

  abstract public Node getSystemNode(AuthSystem system);

  abstract public Node getAuthorizedNode(Credentials credentials, boolean withContent);

  abstract public Node getQueryNode(Credentials credentials, Query query, boolean withContent);

  abstract public Node getSearchNode(Node node, String search);

  abstract public List<Node> getNodeFeed(Node node, short offset, short maxCount, boolean withContent);

  abstract public short countNodeFeed(Node node);

  abstract public Content getNodeContent(Node node);
}
