@Typed package everfeeds.remote

/**
 * @author Dmitry Kurinskiy
 * @since 02.07.11 13:49
 */
abstract class Raw {
  protected def testingContent

  public void setTestingContent(final def content) {
    this.testingContent = content
  }

  protected def getCleanTestingContent(){
    final def content = testingContent
    testingContent = null
    content
  }
}
