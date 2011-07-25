namespace java everfeeds.remote.auth.thrift.util

enum AccessType {
  EVERNOTE = 1,
  FACEBOOK = 2,
  TWITTER = 3,
  GMAIL = 4,
  GREADER = 5,
  LINKEDIN = 6,
  VK = 7,
  ATOMPUB = 8,
}

enum AuthMethod {
  OAUTH,
  BASIC,
}

struct AuthSystem {
  1: string name;
  2: AuthMethod method;
  3: AccessType type;
  10: string userDomain;
}

struct OAuthStep {
  1: string authUrl;
  2: string requestToken;
  3: string requestSecret;
  10: AuthSystem system;
}
