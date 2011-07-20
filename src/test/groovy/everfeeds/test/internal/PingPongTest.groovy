package everfeeds.test.internal

import everfeeds.internal.thrift.InternalAPIHolder

/**
 * @author Dmitry Kurinskiy
 * @since 18.05.11 19:25
 */
class PingPongTest extends GroovyTestCase{
  void testPong(){
    assertEquals "pong", InternalAPIHolder.client.ping()
  }
}
