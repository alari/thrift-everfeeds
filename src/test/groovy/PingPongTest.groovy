
/**
 * @author Dmitry Kurinskiy
 * @since 18.05.11 19:25
 */
class PingPongTest extends GroovyTestCase{
  void testPong(){
    assertEquals "pong", ThriftPrivateClient.appClient.ping()
  }
}
