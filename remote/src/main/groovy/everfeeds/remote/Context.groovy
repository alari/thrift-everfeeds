package everfeeds.remote

/**
 * @author Dmitry Kurinskiy
 * @since 09.07.11 1:36
 */
class Context {
  boolean isSearchAvailable
  String[] childrenLabels

  String[] mayStoreEntriesOfKinds

  String title
  ContextIdentity identity
}

class ContextIdentity {
  AccessData accessData
  String search
  GeoLocation location

  String label = "notebook:guid/tag:guid/feed:urlEncodedFeedId/tag:guid/GZIPPED FOR FUN"
  List<Map<String,String>> zzz
}

class ContextStep {
  String name
  String value
  String title
}

class AccessData {
  String token
  String secret
  String type
}

class GeoLocation {
  Double latitude
  Double longitude
  Double radius
}

class ContextHandler {

  List<Context> listContextsFor(ContextIdentity identity, String type){
    null
  }

  Context getSearchContext(ContextIdentity identity, String search){
    null
  }

  int countEntries(ContextIdentity identity){0}

  List listEntries(int offset, int max){null}

  def putEntry(ContextIdentity identity, entry){}

  def getEntry(ContextIdentity identity, id){}
}