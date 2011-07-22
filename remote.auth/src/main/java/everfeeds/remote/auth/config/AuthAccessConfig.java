package everfeeds.remote.auth.config;

import java.util.Map;

/**
 * @author Dmitry Kurinskiy
 * @since 22.07.11 13:03
 */
public class AuthAccessConfig {
  public String key;
  public String secret;
  public String scope;
  public Map<String,String> params;

  public String toString(){
    String r = "";
    r += "    key: "+key+"\n";
    r += "    secret: "+secret+"\n";
    r += "    scope: "+scope+"\n";
    if(params != null) {
      r += "    params:\n";
      for(String k : params.keySet()) {
        r += "        "+k+": "+params.get(k)+"\n";
      }
    }
    return r;
  }
}
