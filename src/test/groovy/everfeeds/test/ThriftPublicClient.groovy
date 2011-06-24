package everfeeds.test

import everfeeds.thrift.EverfeedsAPI
import everfeeds.thrift.util.APIHolder
/**
 * @author Dmitry Kurinskiy
 * @since 19.05.11 12:00
 */
@Typed
class ThriftPublicClient {

  static public EverfeedsAPI.Client getClient(){
    APIHolder.client
  }
}
