namespace java everfeeds.remote.auth.thrift

include "handshake.thrift"
include "_auth.thrift"
include "_auth_ex.thrift"

struct Credentials {
  1: string token;
  2: string secret;
  3: _auth.AuthSystem system;
  10: map<string,string> params;
}

service AuthFlow extends handshake.HandshakeFlow {
  list<_auth.AuthSystem> listAuthSystems();
  bool checkCredentials(1: Credentials credentials) throws(2: _auth_ex.AuthSystemUnknown aUnknown, 4: _auth_ex.AuthConnectionError ace);
  _auth.OAuthStep getOAuthStep(1: _auth.AuthSystem authSystem, 2: string callbackUrl) throws(1: _auth_ex.AuthMethodMismatch mMismatch, 2: _auth_ex.AuthSystemUnknown aUnknown, 4: _auth_ex.AuthConnectionError ace);
  Credentials exchangeOAuthToken(2: _auth.OAuthStep oAuthStep, 3: string verifierCode) throws(1: _auth_ex.AuthMethodMismatch mMismatch, 2: _auth_ex.AuthSystemUnknown aUnknown, 3: _auth_ex.AuthFailed aFailed, 4: _auth_ex.AuthConnectionError ace);
}