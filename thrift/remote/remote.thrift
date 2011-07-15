include "auth.thrift"
include "_types.thrift"
include "_content.thrift"
include "_geo.thrift"

namespace java everfeeds.remote.thrift

struct Node {
  1: string title;

  10: auth.Credentials credentials;
  11: Query query;

  20: list<Query> labels; // tags, notebook, feed id, etc
  21: _content.Content content;

  30: list<Query> inners; // profile has tags, notebooks, saved searches, etc

  40: list<_types.ContentType> acceptTypes; // what could be pushed to the node

  100: boolean flagFeed;
  101: boolean flagContent;
}

struct Query {
  1: string uri;
  2: types.NodeType nodeType;

  10: _geo.GeoLocation location;
  11: string search;
}

service DiscoveryFlow {
  Node getGlobalNode(1: auth.AccessType type);

  Node getAuthorizedNode(1: auth.Credentials credentials, 4: boolean withContent);

  Node getQueryNode(1: auth.Credentials credentials, 2: Query query, 4: boolean withContent);

  Node getSearchNode(1: Node node, 2: string search);

  list<Node> getNodeFeed(1: Node node, 2: i16 offset, 3: i16 maxCount, 4: boolean withContent);

  i16 countNodeFeed(1: Node node);

  _content.Content getNodeContent(1: Node node);
}