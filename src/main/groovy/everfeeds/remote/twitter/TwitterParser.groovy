package everfeeds.remote.twitter

import everfeeds.remote.Parser
import everfeeds.thrift.util.Kind
import java.text.SimpleDateFormat
import com.twitter.Autolink

/**
 * @author Dmitry Kurinskiy
 * @since 14.05.11 12:19
 */
class TwitterParser extends Parser{
  static protected SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss ZZZZZ yyyy", Locale.ENGLISH)
  static protected Autolink autolink = new Autolink()

  public Kind getKind() {
    Kind.STATUS
  }

  String getIdentity() {
    original.id
  }

   boolean getAccessIsAuthor(){
    original.user.id.toString() == accessor.access.identity
  }

  boolean getIsPublic(){
    ! original.user?.protected
  }

  String getSourceUrl() {
    "http://twitter.com/${author}/status/${identity}"
  }

  String getTitle() {
    original.text
  }

  String getContent() {
    autolink.autoLink(title)
  }

  Date getPlacedDate() {
    simpleDateFormat.parse(original.created_at)
  }

  class DM extends TwitterParser {
    public Kind getKind() {
      Kind.DM
    }
  }

  class Status extends TwitterParser {

  }







  String getAuthor() {
    original.user.screen_name
  }

  String getAuthorIdentity(){
    original.user.id
  }

    String getImageUrl() {
    original.user.profile_image_url
  }





  List<String> getTagIdentities() {
    List<String> tags = []
    accessor.TAGS.each {tagId, tagData ->
      if (tagData.check(original)) tags.add tagId
    }
    tags
  }
}
