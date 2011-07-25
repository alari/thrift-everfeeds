namespace java everfeeds.remote.auth.thrift.util

exception AuthMethodMismatch {
  1: string msg;
}
exception AuthVariantUnknown {
  1: string msg;
}
exception AuthFailed {
  1: string msg;
}

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
  OAUTH,
  BASIC,
}

struct AuthVariant {
  1: string system;
  2: AuthMethod method;
  3: AccessType type;
}

struct OAuthStep {
  1: string authUrl;
  2: string requestToken;
  3: string requestSecret;
  10: AuthVariant variant;
}
