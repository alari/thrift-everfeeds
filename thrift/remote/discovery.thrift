include "_auth.thrift"
include "_auth_ex.thrift"
include "auth.thrift"
include "_types.thrift"
include "_content.thrift"
include "_geo.thrift"

namespace java everfeeds.remote.discovery.thrift

struct Query {
  1: string uri;
  2: _types.NodeType nodeType;

  10: _geo.GeoLocation location;
  11: string search;
}

struct Node {
  1: string title;

  10: auth.Credentials credentials;
  11: Query query;

  20: list<Query> labels; // tags, notebook, feed id, etc
  21: _content.Content content;

  30: list<Query> inners; // profile has tags, notebooks, saved searches, etc

  40: list<_types.ContentType> acceptTypes; // what could be pushed to the node

  100: bool flagFeed;
  101: bool flagContent;
}

service DiscoveryFlow extends auth.AuthFlow {
  Node getSystemNode(1: _auth.AuthSystem system) throws(1: _auth_ex.AuthSystemUnknown aUnknown);

  Node getAuthorizedNode(1: auth.Credentials credentials, 4: bool withContent) throws(1: _auth_ex.AuthSystemUnknown aUnknown);

  Node getQueryNode(1: auth.Credentials credentials, 2: Query query, 4: bool withContent) throws(1: _auth_ex.AuthSystemUnknown aUnknown);

  Node getSearchNode(1: Node node, 2: string search) throws(1: _auth_ex.AuthSystemUnknown aUnknown);

  list<Node> getNodeFeed(1: Node node, 2: i16 offset, 3: i16 maxCount, 4: bool withContent) throws(1: _auth_ex.AuthSystemUnknown aUnknown);

  i16 countNodeFeed(1: Node node) throws(1: _auth_ex.AuthSystemUnknown aUnknown);

  _content.Content getNodeContent(1: Node node) throws(1: _auth_ex.AuthSystemUnknown aUnknown);
}