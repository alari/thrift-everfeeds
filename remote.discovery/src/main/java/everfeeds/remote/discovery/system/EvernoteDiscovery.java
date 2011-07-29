package everfeeds.remote.discovery.system;

import everfeeds.remote.auth.thrift.Credentials;
import everfeeds.remote.auth.thrift.util.AuthSystem;
import everfeeds.remote.discovery.annotation.AccessDiscovery;
import everfeeds.remote.discovery.thrift.Content;
import everfeeds.remote.discovery.thrift.Node;
import everfeeds.remote.discovery.thrift.Query;

import java.util.List;

/**
 * @author Dmitry Kurinskiy
 * @since 29.07.11 20:14
 */
@AccessDiscovery("evernote")
public class EvernoteDiscovery extends DiscoverySystem {
  @Override
  public Node getSystemNode(AuthSystem system) {
    return null;
  }

  @Override
  public Node getAuthorizedNode(Credentials credentials, boolean withContent) {
    return null;
  }

  @Override
  public Node getQueryNode(Credentials credentials, Query query, boolean withContent) {
    return null;
  }

  @Override
  public Node getSearchNode(Node node, String search) {
    return null;
  }

  @Override
  public List<Node> getNodeFeed(Node node, short offset, short maxCount, boolean withContent) {
    return null;
  }

  @Override
  public short countNodeFeed(Node node) {
    return 0;
  }

  @Override
  public Content getNodeContent(Node node) {
    return null;
  }
}
