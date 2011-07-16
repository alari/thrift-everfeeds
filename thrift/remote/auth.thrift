namespace java everfeeds.remote.auth.thrift

include "handshake.thrift"
include "_auth.thrift"

struct Credentials {
  1: string token;
  2: string secret;
  3: _auth.AccessType type;
  10: map<string,string> params;
}

service AuthFlow extends handshake.HandshakeFlow {
  list<_auth.AuthVariant> listAuthVariants();
  bool checkCredentials(1: Credentials credentials);
  _auth.OAuthStep getOAuthStep(1: _auth.AuthVariant authVariant, 2: string redirectUrl);
  Credentials exchangeOAuthToken(1: _auth.AuthVariant authVariant, 2: _auth.OAuthStep oAuthStep, 3: string verifierCode);
}