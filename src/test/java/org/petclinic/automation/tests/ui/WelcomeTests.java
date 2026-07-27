package org.petclinic.automation.tests.ui;

import org.petclinic.automation.core.BaseTest;
import org.petclinic.automation.pages.WelcomePage;
import org.testng.Assert;
import org.testng.innotations.Test;

public class WelcomeTests extends BaseTest {

  @Test
  public void homePageLoads() {
    WelcomePage welcome = new WelcomePage(getPage()).open();
    Assert.assertTrue(welcome.isAt(), "Welcome page should be visible");
  }
}
