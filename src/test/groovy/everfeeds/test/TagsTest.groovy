package everfeeds.test

import everfeeds.thrift.domain.Tag
import org.apache.commons.lang.RandomStringUtils
import everfeeds.thrift.error.Forbidden

/**
 * @author Dmitry Kurinskiy
 * @since 27.05.11 18:06
 */
class TagsTest extends GroovyTestCase {
  public void testGetSaveTag(){
    Tag tag = new Tag()
    String token = PublicAccess.token
    tag.accessId = PublicAccess.api.getAccesses(token).first().id
    tag.identity = RandomStringUtils.randomAlphanumeric(10)

    assertFalse PublicAccess.api.getTags(token, tag.accessId).any{it.identity == tag.identity}

   Tag newTag = PublicAccess.api.saveTag(token, tag)
    assert newTag.id
    assertEquals tag.identity, newTag.identity
    assertTrue PublicAccess.api.getTags(token, tag.accessId).any{it.identity == tag.identity}
    assert !newTag.title

    newTag.title = "test"
    tag = PublicAccess.api.saveTag(token, newTag)
    assertEquals tag.id, newTag.id
    assertEquals newTag.title, tag.title
  }

  public void testParentTag(){
    String token = PublicAccess.token
    String accessId = PublicAccess.api.getAccesses(token).first().id
    List<Tag> tags = PublicAccess.api.getTags(token, accessId)

    tags.each {
      it.parentId = null
      assertTrue !PublicAccess.api.saveTag(token, it).parentId
    }

    tags[0].parentId = tags[1].id
    assertTrue PublicAccess.api.saveTag(token, tags[0]).parentId == tags[1].id

    shouldFail(Forbidden){
      tags[0].parentId = "asdasd"
      PublicAccess.api.saveTag(token, tags[0])
    }
  }
}
