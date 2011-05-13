package everfeeds

/**
 * @author Dmitry Kurinskiy
 * @since 13.05.11 18:10
 */
@Typed
class ConfigHolder {
  static {
    new File("conf").eachFile {
      if(config) {
        config.merge(new ConfigSlurper().parse(it.toURL()))
      } else {
        config = new ConfigSlurper().parse(it.toURL())
      }
    }
  }

  static private ConfigObject config;

  static public ConfigObject getConfig(){
    config
  }
}
