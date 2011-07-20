package everfeeds.test.greader

import everfeeds.remote.greader.GReaderRawUrl

/**
 * @author Dmitry Kurinskiy
 * @since 02.07.11 12:29
 */
class GReaderUrlTest extends GroovyTestCase {
  public void testUrlConcat(){
    assertEquals GReaderRawUrl._API.toString(), GReaderRawUrl._BASE.toString()+"api/0/"
  }
}
