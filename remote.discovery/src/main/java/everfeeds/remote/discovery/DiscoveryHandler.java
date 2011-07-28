package everfeeds.remote.discovery;

import everfeeds.remote.auth.thrift.Credentials;
import everfeeds.remote.auth.thrift.util.AuthSystem;
import everfeeds.remote.discovery.thrift.Content;
import everfeeds.remote.discovery.thrift.DiscoveryFlow;
import everfeeds.remote.auth.AuthHandler;
import everfeeds.remote.discovery.thrift.Node;
import everfeeds.remote.discovery.thrift.Query;
import org.apache.thrift.TException;

import java.util.List;

/**
 * @author Dmitry Kurinskiy
 * @since 26.07.11 16:00
 */
public class DiscoveryHandler extends AuthHandler implements DiscoveryFlow.Iface{
  public Node getSystemNode(AuthSystem system) throws TException {
    return null;
  }

  public Node getAuthorizedNode(Credentials credentials, boolean withContent) throws TException {
    return null;
  }

  public Node getQueryNode(Credentials credentials, Query query, boolean withContent) throws TException {
    return null;
  }

  public Node getSearchNode(Node node, String search) throws TException {
    return null;
  }

  public List<Node> getNodeFeed(Node node, short offset, short maxCount, boolean withContent) throws TException {
    return null;
  }

  public short countNodeFeed(Node node) throws TException {
    return 0;
  }

  public Content getNodeContent(Node node) throws TException {
    return null;
  }
}
