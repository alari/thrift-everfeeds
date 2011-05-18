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

  static public boolean isTesting(){
    return current == TESTING;
  }

  static public boolean isDevelopment(){
    return current == DEVELOPMENT;
  }

  static public boolean isProduction(){
    return current == PRODUCTION;
  }

  static public void setTesting(){
    current = TESTING;
  }

  static public void setProduction(){
    current = PRODUCTION;
  }

  static public Environment getCurrent(){
    return current;
  }
}
