@Typed package everfeeds.remote.greader

import everfeeds.mongo.AuthorD
import everfeeds.mongo.EntryContentD
import everfeeds.mongo.TagD
import everfeeds.remote.Parser
import everfeeds.thrift.util.Kind

/**
 * @author Dmitry Kurinskiy
 * @since 02.07.11 13:00
 */
class GReaderParser extends Parser {
  /*

  private contentCache

  protected beforeBuildEnvelop(){
    contentCache = original.content?.content ?: original.summary?.content?.replace("\n", "<br/>")
  }

  String getIdentity() {
    original.id
  }

  String getAuthor() {
    original.author
  }

  boolean getIsPublic() {
    true
  }

  String getSourceUrl() {
    original.alternate.find {it.type == "text/html"}?.href
  }

  String getTitle() {
    original.title
  }

  String getDescription() {
    contentCache.size() <= 1024 ? contentCache : ""
  }

  String getContent() {
    contentCache.size() > 1024 ? contentCache : ""
  }

  Date getPlacedDate() {
    new Date(((long) original.updated) * 1000)
  }

  List<String> getTagIdentities() {
    original.categories.collect {it.toString()}
  }
  * */

  @Override
  boolean getIsAuthor() {
    return false
  }

  @Override
  boolean getIsPublicAvailable() {
    return false
  }

  @Override
  boolean getIsFavorite() {
    return false
  }

  @Override
  boolean getIsRead() {
    return false
  }

  @Override
  String getIdentity() {
    return null
  }

  @Override
  String getTitle() {
    return null
  }

  @Override
  String getDescription() {
    return null
  }

  @Override
  EntryContentD getContent() {
    return null
  }

  @Override
  AuthorD getAuthor() {
    return null
  }

  @Override
  Kind getKind() {
    return null
  }

  @Override
  Date getDatePlaced() {
    return null
  }

  @Override
  List<TagD> getTags() {
    return null
  }
}
