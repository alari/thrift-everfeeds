package everfeeds.remote.discovery;

import everfeeds.remote.auth.AuthHandler;
import everfeeds.remote.auth.thrift.Credentials;
import everfeeds.remote.auth.thrift.ex.AuthSystemUnknown;
import everfeeds.remote.auth.thrift.util.AuthSystem;
import everfeeds.remote.discovery.system.DiscoverySystem;
import everfeeds.remote.discovery.thrift.Content;
import everfeeds.remote.discovery.thrift.DiscoveryFlow;
import everfeeds.remote.discovery.thrift.Node;
import everfeeds.remote.discovery.thrift.Query;
import org.apache.thrift.TException;

import java.util.List;

/**
 * @author Dmitry Kurinskiy
 * @since 26.07.11 16:00
 */
public class DiscoveryHandler extends AuthHandler implements DiscoveryFlow.Iface {
  public Node getSystemNode(AuthSystem system) throws TException, AuthSystemUnknown {
    DiscoverySystem ds = getDiscoveryBySystem(system);
    return ds.getSystemNode(system);
  }

  public Node getAuthorizedNode(Credentials credentials, boolean withContent) throws TException, AuthSystemUnknown {
    if (credentials == null || credentials.system == null) {
      throw new AuthSystemUnknown().setMsg("Cannot find system: you have not set its name in request properties");
    }
    DiscoverySystem ds = getDiscoveryBySystem(credentials.system);
    return ds.getAuthorizedNode(credentials, withContent);
  }

  public Node getQueryNode(Credentials credentials, Query query, boolean withContent) throws TException, AuthSystemUnknown {
    if (credentials == null || credentials.system == null) {
      throw new AuthSystemUnknown().setMsg("Cannot find system: you have not set its name in request properties");
    }
    DiscoverySystem ds = getDiscoveryBySystem(credentials.system);
    return ds.getQueryNode(credentials,query,withContent);
  }

  public Node getSearchNode(Node node, String search) throws TException, AuthSystemUnknown {
    checkNodeSystem(node);
    DiscoverySystem ds = getDiscoveryBySystem(node.credentials.system);
    return ds.getSearchNode(node, search);
  }

  public List<Node> getNodeFeed(Node node, short offset, short maxCount, boolean withContent) throws TException, AuthSystemUnknown {
    checkNodeSystem(node);
    DiscoverySystem ds = getDiscoveryBySystem(node.credentials.system);
    return ds.getNodeFeed(node, offset, maxCount, withContent);
  }

  public short countNodeFeed(Node node) throws TException, AuthSystemUnknown {
    checkNodeSystem(node);
    DiscoverySystem ds = getDiscoveryBySystem(node.credentials.system);
    return ds.countNodeFeed(node);
  }

  public Content getNodeContent(Node node) throws TException, AuthSystemUnknown {
    checkNodeSystem(node);
    DiscoverySystem ds = getDiscoveryBySystem(node.credentials.system);
    return ds.getNodeContent(node);
  }

  private void checkNodeSystem(Node node) throws AuthSystemUnknown {
    if (node == null || node.credentials == null || node.credentials.system == null) {
      throw new AuthSystemUnknown().setMsg("Cannot find system: you have not set its name in request properties");
    }
  }

  private DiscoverySystem getDiscoveryBySystem(AuthSystem system) throws AuthSystemUnknown {
    DiscoverySystem ds = DiscoverySystem.getBySystem(system);
    if (ds == null) {
      throw new AuthSystemUnknown().setMsg("Cannot find system by the name provided");
    }
    return ds;
  }
}
