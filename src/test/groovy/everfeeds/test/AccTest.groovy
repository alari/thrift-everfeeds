package everfeeds.test

import everfeeds.thrift.domain.Account
import everfeeds.thrift.domain.Access
import everfeeds.thrift.ttype.AccessType
import org.apache.commons.lang.RandomStringUtils

/**
 * @author Dmitry Kurinskiy
 * @since 21.05.11 17:38
 */
class AccTest extends GroovyTestCase{
  public void testGetAccount(){
    Account account = PublicAccess.api.getAccount( PublicAccess.token )
    assertNotNull account
    assertNotNull account.id
  }

  public void testGetAccesses(){
    List<Access> accesses = PublicAccess.api.getAccesses( PublicAccess.token )
    assertTrue accesses.size() > 0
    assertTrue accesses.any {it.type == AccessType.TWITTER}
  }

  public void testSaveAccount(){
    Account account = PublicAccess.api.getAccount( PublicAccess.token )
    String testTitle = "test "+RandomStringUtils.randomAlphanumeric(10);
    assertNotSame testTitle, account.title
    account.title = testTitle
    account = PublicAccess.api.saveAccount( PublicAccess.token, account )
    assertTrue testTitle == account.title

    account.title = RandomStringUtils.randomAlphanumeric(10);
    Account account2 = PublicAccess.api.saveAccount( PublicAccess.token, account )
    assertTrue account2.id == account.id
    assertTrue account2.title == account.title
  }

  public void testSaveAccess(){
    Access access = PublicAccess.api.getAccesses( PublicAccess.token ).first()

    assertNotNull "Access id should not be null", access.id
    assertNotNull access.identity
    assertNotNull "Access account ID should not be null", access.accountId

    Access accessOld = (Access)access.clone();

    access.title = RandomStringUtils.randomAlphanumeric(10);
    assertTrue access.title.size() == 10
    access.screenName = RandomStringUtils.randomAlphanumeric(10);
    access.expired = !access.expired

    access = PublicAccess.api.saveAccess( PublicAccess.token, access )
    assertEquals accessOld.id, access.id
    assertNotSame access.title, accessOld.title
    assertEquals access.expired, accessOld.expired
  }
}
