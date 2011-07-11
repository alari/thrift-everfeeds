include "auth.thrift"

namespace java everfeeds.remote.thrift

struct Context {
  1: string title;

  10: auth.Credentials credentials;

  20: GeoLocation location;

  30: list<ContextStep> steps;

  40: list<string> singleInners;
  41: list<string> multipleInners;

  50: boolean hasContentFeed;
  51: boolean hasContent;
}

struct ContextStep {
  1: string name;
  2: string value;
  3: string title;
}

struct GeoLocation {
  1: double latitude;
  2: double longitude;
  3: double radius;
}

service DiscoveryFlow extends auth.AuthFlow {
  Context getGlobalContext(1: auth.AccessType type);

  Context getAuthorizedContext(1: auth.Credentials credentials);

  Context searchContext(1: Context context, 2: string search);

  list<ContextStep> listInnerSteps(1: Context context, string stepName);

  Context stepContext(1: Context context, ContextStep step);
}