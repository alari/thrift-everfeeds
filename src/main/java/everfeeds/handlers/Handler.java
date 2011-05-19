package everfeeds.handlers;

import com.google.code.morphia.Datastore;
import everfeeds.MongoDB;
import everfeeds.mongo.*;
import everfeeds.thrift.domain.Access;
import everfeeds.thrift.domain.Filter;
import everfeeds.thrift.error.Forbidden;
import everfeeds.thrift.error.TokenExpired;
import everfeeds.thrift.error.TokenNotFound;
import everfeeds.thrift.util.Scope;
import everfeeds.thrift.util.Type;
import org.apache.thrift.TException;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * @author Dmitry Kurinskiy
 * @since 06.05.11 19:11
 */
abstract public class Handler {
  protected TokenD getTokenD(String id) throws TException, TokenNotFound, TokenExpired, Forbidden {
    TokenD token = getDS().get(TokenD.class, new ObjectId(id));
    if (token == null) {
      throw new TokenNotFound(String.format("Cannot find token for %s", id));
    }
    if (token.expired) {
      throw new TokenExpired(String.format("Token expired at %s", token.expires.toString()));
    }
    return token;
  }

  protected void checkToken(TokenD tokenD, Scope scope) throws Forbidden, TokenNotFound {
    if (tokenD == null) {
      throw new TokenNotFound("Token not found while is checked");
    }
    if (!tokenD.hasScope(scope)) {
      throw new Forbidden("Access denied: token has no scope '" + scope.toString() + "' which is required to perform this action");
    }
  }

  protected AccessD findAccessD(Access access) throws TException {
    AccessD accessD;
    if (access.id != null && !access.id.isEmpty()) {
      accessD = getDS().get(AccessD.class, new ObjectId(access.id));
      if (accessD != null) {
        return accessD;
      }
    }

    accessD = getDS().createQuery(AccessD.class)
                  .filter("identity", access.identity)
                  .filter("type", Type.getByThrift(access.type)).get();
    if (accessD != null) {
      return accessD;
    }
    accessD = new AccessD();
    accessD.type = Type.getByThrift(access.type);
    accessD.identity = access.identity;
    return accessD;
  }


  protected AccessD getAccessD(String token, String id) throws TException, Forbidden, TokenNotFound, TokenExpired {
    TokenD tokenD = getTokenD(token);

    return getDS().createQuery(AccessD.class).filter("id", new ObjectId(id)).filter("account", tokenD.account).get();
  }


  protected FilterD setFilterRelationsFromThrift(FilterD filterD, Filter filter) {
    // Syncing categories and tags
    filterD.categories.clear();
    filterD.withoutTags.clear();
    filterD.withTags.clear();

    if (filter.categoryIds != null && filter.categoryIds.size() > 0) {
      List<CategoryD> categories = getDS().createQuery(CategoryD.class)
                                       .filter("access", filterD.access)
                                       .asList();
      for (CategoryD c : categories) {
        if (filter.categoryIds.contains(c.id.toString())) {
          filterD.categories.add(c);
        }
      }
    }

    if (filter.withTagIds != null || filter.withTagIds != null) {
      List<TagD> tags = getDS().createQuery(TagD.class)
                            .filter("access", filterD.access)
                            .asList();
      for (TagD t : tags) {
        if (filter.withoutTagIds != null && filter.withoutTagIds.contains(t.id.toString())) {
          filterD.withoutTags.add(t);
        } else if (filter.withTagIds != null && filter.withTagIds.contains(t.id.toString())) {
          filterD.withTags.add(t);
        }
      }
    }

    return filterD;
  }

  protected Datastore getDS() {
    return MongoDB.getDS();
  }
}
