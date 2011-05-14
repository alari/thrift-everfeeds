package everfeeds.remote

import everfeeds.mongo.EntryD
import everfeeds.thrift.util.Kind
import everfeeds.mongo.AccessD

/**
 * @author Dmitry Kurinskiy
 * @since 14.05.11 12:20
 */
@Typed
abstract class Parser {
  protected original

  protected EntryD entry = new EntryD()

  protected AccessD access
  boolean isParsed = false

  protected void setOriginal(original) {
    this.original = original
    isParsed = false
  }

  public void setEntryD(EntryD entryD) {
    entry = entryD
  }

  public void setAccessD(AccessD accessD) {
    access = accessD
  }

  public EntryD getEntryD() {
    if(!isParsed) {
      if(!access) {
        throw new Exception("Cannot parse original without an accessD being set")
      }
      if(!original) {
        throw new Exception("Nothing to parse: no original provided")
      }
      isParsed = true
    }
    entry
  }
  /*
  abstract String getIdentity()

  abstract String getTitle()

  abstract Kind getKind()

  String getSourceUrl() {""}

  abstract Date getPlacedDate()

  abstract boolean getIsPublic()

  String getCategoryIdentity() {categoryIdentityPreset}

  String getImageUrl() {""}

  String getDescription() {""}

  String getContent() {""}

  String getAuthor() {""}

  String getAuthorIdentity() {""}

  boolean getAccessIsAuthor() {null}

  int getAccessId() {accessor.access.id}

  List<String> getTagIdentities() {[]}  */
}
