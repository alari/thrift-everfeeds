package everfeeds.handlers;

import everfeeds.Scope;
import everfeeds.mongo.AccessD;
import everfeeds.mongo.CategoryD;
import everfeeds.mongo.TagD;
import everfeeds.thrift.error.Forbidden;
import everfeeds.thrift.error.TokenExpired;
import everfeeds.thrift.error.TokenNotFound;
import everfeeds.thrift.service.AccessAPI;
import everfeeds.thrift.domain.Category;
import everfeeds.thrift.domain.Tag;
import everfeeds.thrift.ttype.EntryKind;
import org.apache.thrift.TException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dmitry Kurinskiy
 * @since 06.05.11 19:11
 */
public class AccessHandler extends AccountHandler implements AccessAPI.Iface {
  @Override
  public List<Tag> getTags(String token, String accessId) throws TException, Forbidden, TokenNotFound, TokenExpired {
    AccessD accessD = getAccessD(token, accessId);
    checkToken(getTokenD(token), Scope.INFO);

    List<Tag> tags = new ArrayList<Tag>();
    Tag tag;

    for (TagD t : getDS().createQuery(TagD.class).filter("access", accessD).fetch()) {
      tag = new Tag();
      t.syncToThrift(tag);
      tags.add(tag);
    }

    return tags;
  }

  @Override
  public List<Category> getCategories(String token, String accessId) throws TException, Forbidden, TokenNotFound, TokenExpired {
    AccessD accessD = getAccessD(token, accessId);
    checkToken(getTokenD(token), Scope.INFO);

    List<Category> categories = new ArrayList<Category>();
    Category category;

    for (CategoryD c : getDS().createQuery(CategoryD.class).filter("access", accessD).fetch()) {
      category = new Category();
      c.syncToThrift(category);
      categories.add(category);
    }

    return categories;
  }

  @Override
  public List<EntryKind> getKinds(String token, String accessId) throws TException, TokenNotFound, Forbidden, TokenExpired {
    // can't imagine how to do it
    checkToken(getTokenD(token), Scope.INFO);
    return null;
  }

  @Override
  public Tag saveTag(String token, Tag tag) throws TException, Forbidden, TokenNotFound, TokenExpired {
    AccessD accessD = getAccessD(token, tag.accessId);
    checkToken(getTokenD(token), Scope.INFO_ORDER);

    if (accessD == null) {
      throw new TException("You must specify a valid Tag Access ID!");
    }
    TagD tagD;
    if (!tag.id.isEmpty()) {
      tagD = getDS().createQuery(TagD.class).filter("id", tag.id).filter("access", accessD).get();
    } else {
      tagD = getDS().createQuery(TagD.class).filter("identity", tag.identity).filter("access", accessD).get();
      if (tagD == null) {
        tagD = new TagD();
        tagD.access = accessD;
      }
    }

    tagD.syncFromThrift(tag);
    getDS().save(tagD);
    tagD.syncToThrift(tag);

    return tag;
  }

  @Override
  public Category saveCategory(String token, Category category) throws TException, Forbidden, TokenNotFound, TokenExpired {
    AccessD accessD = getAccessD(token, category.accessId);
    checkToken(getTokenD(token), Scope.INFO_ORDER);

    if (accessD == null) {
      throw new TException("You must specify a valid Tag Access ID!");
    }
    CategoryD categoryD;
    if (!category.id.isEmpty()) {
      categoryD = getDS().createQuery(CategoryD.class).filter("id", category.id).filter("access", accessD).get();
    } else {
      categoryD = getDS().createQuery(CategoryD.class).filter("identity", category.identity).filter("access", accessD).get();
      if (categoryD == null) {
        categoryD = new CategoryD();
        categoryD.access = accessD;
      }
    }

    categoryD.syncFromThrift(category);
    getDS().save(categoryD);
    categoryD.syncToThrift(category);

    return category;
  }

}
