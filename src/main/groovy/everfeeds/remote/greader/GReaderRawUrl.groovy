@Typed package everfeeds.remote.greader

/**
 * @author Dmitry Kurinskiy
 * @since 02.07.11 12:26
 */
public enum GReaderRawUrl {
  _BASE("http://www.google.com/reader/"),
  _API(_BASE, "api/0/"),
  TOKEN(_API, "token"),
  USER_INFO(_API, "user-info"),
  USER_LABEL(_API, "user/-/label/"),
  TAG_LIST(_API, "tag/list"),
  EDIT_TAG(_API, "tag/edit"),
  RENAME_TAG(_API, "rename-tag"),
  DISABLE_TAG(_API, "disable-tag"),
  SUBSCRIPTION(_API, "subscription/edit"),
  SUBSCRIPTION_LIST(_API, "subscription/list"),
  CONTENT_BASE(_API, "stream/contents/"),
  CONTENT_READER_LIST(CONTENT_BASE, "user/-/state/com.google/reading-list");

  private String url;

  GReaderRawUrl(String url) {
    this.url = url
  }

  GReaderRawUrl(GReaderRawUrl url, String suffix) {
    this.url = url.url + suffix
  }

  String toString() {
    url
  }
}