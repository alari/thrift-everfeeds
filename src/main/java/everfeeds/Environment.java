package everfeeds;

/**
 * @author Dmitry Kurinskiy
 * @since 18.05.11 19:42
 */
public enum Environment {
  TESTING,
  DEVELOPMENT,
  PRODUCTION;

  static private Environment current = Environment.DEVELOPMENT;

  static public void setTesting(){
    current = TESTING;
  }

  static public void setProduction(){
    current = PRODUCTION;
  }

  static Environment getCurrent(){
    return current;
  }
}
