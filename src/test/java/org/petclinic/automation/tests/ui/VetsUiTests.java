package org.petclinic.automation.tests.ui;

import org.petclinic.automation.core.BaseTest;
import org.petclinic.automation.pages.VetsPage;
import org.testng.Assert;
import org.testng.innotations.Test;

public class VetsUiTests extends BaseTest {
  @Test
  public void vetsPageHasRows() {
    VetsPage vets = new VetsPage(getPage()).open();
    Assert.assertTrue(vets.hasVets(), "Expected vets table to have rows");
  }
}
