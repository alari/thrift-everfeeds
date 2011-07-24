package everfeeds.remote.auth.config;

import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;

/**
 * @author Dmitry Kurinskiy
 * @since 22.07.11 13:00
 */
public class AuthConfig {
  static public final String YAML_PROPERTY = "everfeeds.yaml.auth";

  static private AuthConfig instance;

  static public AuthConfig getInstance() {
    return instance;
  }

  static {
    Constructor constructor = new Constructor(AuthConfig.class);//Car.class is root
    TypeDescription configDescription = new TypeDescription(AuthConfig.class);
    configDescription.putMapPropertyType("accesses", String.class, AuthAccessConfig.class);
    constructor.addTypeDescription(configDescription);
    Yaml yaml = new Yaml(constructor);
    try {
      instance = (AuthConfig) yaml.load(new FileInputStream(new File(System.getProperty(YAML_PROPERTY))));
    } catch (FileNotFoundException e) {
      System.err.println("Auth config file not found");
    }
  }

  static public AuthAccessConfig getAccessConfig(String system) {
    return getInstance().accesses.get(system);
  }

  public Map<String, AuthAccessConfig> accesses;


  public String toString() {
    String r = "";
    for (String k : accesses.keySet()) {
      r += k + ":\n";
      r += accesses.get(k).toString() + "\n";
    }
    return r;
  }
}
