package everfeeds.remote.facebook

import everfeeds.remote.Parser
import everfeeds.thrift.util.Kind
import java.text.SimpleDateFormat
import everfeeds.mongo.*

/**
 * @author Dmitry Kurinskiy
 * @since 14.05.11 12:19
 */
@Typed(TypePolicy.MIXED)
class FacebookParser extends Parser {
  static protected SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.ENGLISH)

  FacebookParser(){
  }

  FacebookParser(original, AccessD access, EntryD entry) {
    super(original, access, entry)
  }

  public Kind getKind() {
    Kind.STATUS
  }

  String getIdentity() {
    original.id
  }

  boolean getIsAuthor() {
    original.from?.id?.toString() == access.identity
  }

  boolean getIsPublicAvailable() {
    true
  }

  String getSourceUrl() {
    original.source ?: (original.link ?: "http://facebook.com/${original.id}")
  }

  String getTitle() {
    "Title is TODO"//TODO: I18N
  }

  String getDescription() {
    (original?.message ?: "") + " " + (original?.description ?: "")
  }

  @Override
  boolean getIsFavorite() {
    original?.favorited && (original?.getBoolean("favorited") || original.favorited)
  }

  @Override
  boolean getIsRead() {
    entry.isRead
  }

  @Override
  EntryContentD getContent() {
    EntryContentD ecd = entry.content ?: new EntryContentD()
    ecd.html = "<img src='"+(original?.picture ?: original?.icon ?: "http://facebook.com/${original.id}/picture")+"'/>"
    ecd
  }

  @Override
  AuthorD getAuthor() {
    new AuthorD(
        identity: original.from?.id,
        title: original.from?.name ?: "Unknown",
        screenName: null,
        imageUrl: null
    )
  }

  @Override
  Date getDatePlaced() {
    (original?.created_time) ? simpleDateFormat.parse(original?.created_time) : new Date()
  }

  @Override
  List<TagD> getTags() {
    []
  }
}
