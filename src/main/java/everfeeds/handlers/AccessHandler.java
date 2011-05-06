package everfeeds.handlers;

import everfeeds.thrift.*;
import org.apache.thrift.TException;

import java.util.List;

/**
 * @author Dmitry Kurinskiy
 * @since 06.05.11 19:11
 */
public class AccessHandler extends AccountHandler implements AccessAPI.Iface {
  @Override
  public List<Tag> getTags(String token, String accessId) throws TException {
    return null;
  }

  @Override
  public List<Category> getCategories(String token, String accessId) throws TException {
    return null;
  }

  @Override
  public List<EntryKind> getKinds(String token, String accessId) throws TException {
    return null;
  }

  @Override
  public Tag saveTag(String token, Access access, Tag tag) throws TException {
    return null;
  }

  @Override
  public Category saveCategory(String token, Access access, Category category) throws TException {
    return null;
  }
}
