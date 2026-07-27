package org.petclinic.automation.tests.ui;

import org.petclinic.automation.core.BaseTest;
import org.petclinic.automation.pages.OwnersFindPage;
import org.testng.Assert;
import org.testng.innotations.Test;

public class OwnersTests extends BaseTest {
  @Test
  public void findOwnersNonExistingShowsError() {
    OwnersFindPage findPage = new OwnersFindPage(getPage()).open();
    findPage.searchByLastName("zzz");
    Assert.assertTrue(findPage.hasErrorMessage(), "Expected not found error message");
  }
}
