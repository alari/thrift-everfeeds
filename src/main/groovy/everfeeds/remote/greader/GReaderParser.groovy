@Typed package everfeeds.remote.greader

import everfeeds.remote.Parser
import everfeeds.thrift.util.Kind
import everfeeds.mongo.*

/**
 * @author Dmitry Kurinskiy
 * @since 02.07.11 13:00
 */
class GReaderParser extends Parser {
  private String contentCache

  GReaderParser(){
  }

  GReaderParser(original, AccessD access, EntryD entry) {
    super(original, access, entry)
    setContentCache()
  }

  @Override
  void setOriginal(Object original) {
    super.setOriginal(original)
    setContentCache()
  }

  private void setContentCache(){
    contentCache ="";// original.content?.content ?: original?.summary?.content?.replace("\n", "<br/>")
  }

  @Override
  String getSourceUrl() {
    "";//(original.alternate.find {it.type == "text/html"} as Map)?.href
  }



  @Override
  boolean getIsAuthor() {
    false
  }

  @Override
  boolean getIsPublicAvailable() {
     true
  }

  @Override
  boolean getIsFavorite() {
    false//original.categories.any{it.toString() == "user/-/state/com.google/starred"}
  }

  @Override
  boolean getIsRead() {
    false//original.categories.any{it.toString() == "user/-/state/com.google/read"}
  }

  @Override
  String getIdentity() {
    ""//original.id
  }

  @Override
  String getTitle() {
    ""//original.title
  }

  @Override
  String getDescription() {
    contentCache.size() <= 1024 ? contentCache : ""
  }

  @Override
  EntryContentD getContent() {
    if (contentCache.size() <= 1024) return null

    EntryContentD contentD = entry?.content ?: new EntryContentD()
    contentD.html = contentCache
    contentD
  }

  @Override
  AuthorD getAuthor() {
    new AuthorD(title: "")//original.author)
  }

  @Override
  Kind getKind() {
    Kind.ATOM
  }

  @Override
  Date getDatePlaced() {
   new Date(); //new Date(((long) original.updated) * 1000)
  }

  @Override
  List<TagD> getTags() {
    null//original.categories.collect{ tagsCache.get it.toString()}
  }
}
