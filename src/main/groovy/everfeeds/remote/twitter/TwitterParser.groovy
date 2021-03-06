package everfeeds.remote.twitter

import com.twitter.Autolink
import everfeeds.remote.Parser
import everfeeds.thrift.util.Kind
import java.text.SimpleDateFormat
import everfeeds.mongo.*

/**
 * @author Dmitry Kurinskiy
 * @since 14.05.11 12:19
 */
@Typed(TypePolicy.MIXED)
class TwitterParser extends Parser {
  static protected SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss ZZZZZ yyyy", Locale.ENGLISH)
  static protected Autolink autolink = new Autolink()

  TwitterParser(){
  }

  TwitterParser(original, AccessD access, EntryD entry) {
    super(original, access, entry)
  }

  public Kind getKind() {
    Kind.STATUS
  }

  String getIdentity() {
    original.id
  }

  boolean getIsAuthor() {
    original.user.id.toString() == access.identity
  }

  boolean getIsPublicAvailable() {
    !original.user?.protected
  }

  String getSourceUrl() {
    "http://twitter.com/${original.user.screen_name}/status/${original.id}"
  }

  String getTitle() {
    original.text
  }

  String getDescription() {
    autolink.autoLink(title)
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
    return null
  }

  @Override
  AuthorD getAuthor() {
    new AuthorD(
        identity: original.user.id,
        title: original.user.name,
        screenName: original.user.screen_name,
        imageUrl: original.user.profile_image_url
    )
  }

  @Override
  Date getDatePlaced() {
    simpleDateFormat.parse(original.created_at)
  }

  @Override
  List<TagD> getTags() {
    TwitterTag.values().findAll {it.check(original)}*.identity.collect {tagsCache.get(it)}
  }
}
