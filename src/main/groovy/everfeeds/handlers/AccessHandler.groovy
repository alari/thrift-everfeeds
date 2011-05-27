@Typed package everfeeds.handlers;

import everfeeds.thrift.util.Scope;
import everfeeds.mongo.AccessD;
import everfeeds.mongo.CategoryD;
import everfeeds.mongo.TagD;
import everfeeds.thrift.error.Forbidden;
import everfeeds.thrift.error.TokenExpired;
import everfeeds.thrift.error.TokenNotFound;
import everfeeds.thrift.domain.Category;
import everfeeds.thrift.domain.Tag;
import everfeeds.thrift.ttype.EntryKind;
import org.apache.thrift.TException;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dmitry Kurinskiy
 * @since 06.05.11 19:11
 */
public class AccessHandler extends AccountHandler {

  public List<Tag> getTags(String token, String accessId) throws TException, Forbidden, TokenNotFound, TokenExpired {
    AccessD accessD = getAccessD(token, accessId);
    checkToken(getTokenD(token), Scope.INFO);

    List<Tag> tags = new ArrayList<Tag>();
    //Tag tag;

    tagDAO.findAllByAccess(accessD).each {TagD t->
      Tag tag = new Tag();
      t.syncToThrift(tag);
      tags.add(tag);
    }

    return tags;
  }


  public List<Category> getCategories(String token, String accessId) throws TException, Forbidden, TokenNotFound, TokenExpired {
    AccessD accessD = getAccessD(token, accessId);
    checkToken(getTokenD(token), Scope.INFO);

    List<Category> categories = new ArrayList<Category>();
    //Category category;

    categoryDAO.findAllByAccess(accessD).each {CategoryD c->
      Category category = new Category();
      c.syncToThrift(category);
      categories.add(category);
    }

    return categories;
  }


  public List<EntryKind> getKinds(String token, String accessId) throws TException, TokenNotFound, Forbidden, TokenExpired {
    // can't imagine how to do it
    checkToken(getTokenD(token), Scope.INFO);
    return new ArrayList<EntryKind>();
  }


  public Tag saveTag(String token, Tag tag) throws TException, Forbidden, TokenNotFound, TokenExpired {
    AccessD accessD = getAccessD(token, tag.accessId);
    checkToken(getTokenD(token), Scope.INFO_ORDER);

    if (accessD == null) {
      throw new TException("You must specify a valid Tag Access ID!");
    }
    TagD tagD = tagDAO.getByThrift(tag, accessD);
      if (tagD == null) {
        tagD = new TagD();
        tagD.access = accessD;
      }

    if(tag.parentId != null && !tag.parentId.isEmpty()) {
      TagD parentTag = tagDAO.getByIdAndAccess(tag.parentId, accessD)
      if(parentTag == null) {
        throw new Forbidden("Access to parent tag is forbidden");
      }
      tagD.parent = parentTag;
    } else {
      tagD.parent = null;
    }

    tagD.syncFromThrift(tag);
    tagDAO.save(tagD);
    tagD.syncToThrift(tag);
    return tag;
  }


  public Category saveCategory(String token, Category category) throws TException, Forbidden, TokenNotFound, TokenExpired {
    AccessD accessD = getAccessD(token, category.accessId);
    checkToken(getTokenD(token), Scope.INFO_ORDER);

    if (accessD == null) {
      throw new TException("You must specify a valid Category Access ID!");
    }
    CategoryD categoryD = categoryDAO.getByThrift(category, accessD);
      if (categoryD == null) {
        categoryD = new CategoryD();
        categoryD.access = accessD;
      }

       if(category.parentId != null && !category.parentId.isEmpty()) {
      CategoryD parentCategory = categoryDAO.getByIdAndAccess(category.parentId, accessD)
      if(parentCategory == null) {
        throw new Forbidden("Access to parent category is forbidden");
      }
      categoryD.parent = parentCategory;
    } else {
      categoryD.parent = null;
    }

    categoryD.syncFromThrift(category);
    categoryDAO.save(categoryD);
    categoryD.syncToThrift(category);

    return category;
  }

}
