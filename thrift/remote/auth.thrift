namespace java everfeeds.remote.thrift

include "handshake.thrift"

enum AccessType {
  EVERNOTE = 1,
  FACEBOOK = 2,
  TWITTER = 3,
  GMAIL = 4,
  GREADER = 5,
  LINKEDIN = 6,
  VK = 7,
  METAWEBLOG = 8,
}

enum AuthMethod {
  OAUTH2,
  BASIC,
}

struct Credentials {
  1: string token;
  2: string secret;
  3: AccessType type;
  10: map<string,string> params;
}
struct AuthVariant {
  1: string system;
  2: AuthMethod method;
  3: AccessType type;
}
struct OAuthStep {
  1: string authUrl;
  2: string requestToken;
}

service AuthFlow extends handshake.Handshake {
  list<AuthVariant> listAuthVariants();
  boolean checkCredentials(1: Credentials credentials);
  OAuthStep getOAuthStep(1: AuthVariant authVariant);
  Credentials exchangeOAuthToken(1: AuthVariant authVariant, 2: OAuthStep oAuthStep, 3: string verifierCode);
}