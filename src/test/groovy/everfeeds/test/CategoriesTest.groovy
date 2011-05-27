package everfeeds.test

import everfeeds.thrift.domain.Category
import everfeeds.thrift.error.Forbidden
import org.apache.commons.lang.RandomStringUtils

/**
 * @author Dmitry Kurinskiy
 * @since 27.05.11 18:06
 */
class CategoriesTest extends GroovyTestCase {
  public void testGetSaveCategory(){
    Category category = new Category()
    String token = PublicAccess.token
    category.accessId = PublicAccess.api.getAccesses(token).first().id
    category.identity = RandomStringUtils.randomAlphanumeric(10)

    assertFalse PublicAccess.api.getCategories(token, category.accessId).any{it.identity == category.identity}

    Category newCategory = PublicAccess.api.saveCategory(token, category)
    assert newCategory.id
    assertEquals category.identity, newCategory.identity
    assertTrue PublicAccess.api.getCategories(token, category.accessId).any{it.identity == category.identity}
    assert !newCategory.title

    newCategory.title = "test"
    category = PublicAccess.api.saveCategory(token, newCategory)
    assertEquals category.id, newCategory.id
    assertEquals newCategory.title, category.title
  }

  public void testParentCategory(){
    String token = PublicAccess.token
    String accessId = PublicAccess.api.getAccesses(token).first().id
    List<Category> categories = PublicAccess.api.getCategories(token, accessId)

    categories.each {
      it.parentId = null
      assertTrue !PublicAccess.api.saveCategory(token, it).parentId
    }

    categories[0].parentId = categories[1].id
    assertTrue PublicAccess.api.saveCategory(token, categories[0]).parentId == categories[1].id

    shouldFail(Forbidden){
      categories[0].parentId = "asdasd"
      PublicAccess.api.saveCategory(token, categories[0])
    }
  }
}
