namespace java everfeeds.remote.auth.thrift

include "handshake.thrift"
include "_auth.thrift"

struct Credentials {
  1: string token;
  2: string secret;
  3: _auth.AuthVariant variant;
  10: map<string,string> params;
}

service AuthFlow extends handshake.HandshakeFlow {
  list<_auth.AuthVariant> listAuthVariants();
  bool checkCredentials(1: Credentials credentials) throws(2: _auth.AuthVariantUnknown aUnknown);
  _auth.OAuthStep getOAuthStep(1: _auth.AuthVariant authVariant, 2: string callbackUrl) throws(1: _auth.AuthMethodMismatch mMismatch, 2: _auth.AuthVariantUnknown aUnknown);
  Credentials exchangeOAuthToken(2: _auth.OAuthStep oAuthStep, 3: string verifierCode) throws(1: _auth.AuthMethodMismatch mMismatch, 2: _auth.AuthVariantUnknown aUnknown, 3: _auth.AuthFailed aFailed);
}