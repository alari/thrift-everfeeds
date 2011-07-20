package everfeeds.remote.evernote

import org.jsoup.Jsoup
import org.jsoup.safety.Whitelist

/**
 * @author Dmitry Kurinskiy
 * @since 13.06.11 18:34
 */
class EvernoteMarkup {
  static private EvernoteMarkup instance = new EvernoteMarkup()

  static public EvernoteMarkup getInstance() {
    instance
  }

  private EvernoteMarkup(){}

  public String getEdamForText(String text) {
    getEdamForHtml text.replaceAll(~/\n/, "<br/>")
  }

  public String getEdamForHtml(String html) {
        """<?xml version="1.0" encoding="UTF-8"?>

<!DOCTYPE en-note SYSTEM "http://xml.evernote.com/pub/enml2.dtd">

<en-note>""" + Jsoup.clean(html, Whitelist.relaxed()) + "</en-note>"
  }

  public String getHtmlForEdam(String edam) {
    Jsoup.clean(edam, Whitelist.relaxed())
  }
}
