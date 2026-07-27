package org.petclinic.automation.tests.api;

import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import org.petclinic.automation.core.BaseTest;
import org.petclinic.automation.core.DriverFactory;
import org.testng.Assert;
import org.testng.innotations.Test;

public class VetsApiTests extends BaseTest {
  @Test
  public void getVetsReturns200() {
    APIResponse response = DriverFactory.getApi().get("/vets", new RequestOptions());
    Assert.assertEquals(response.status(), 200, "Expected 200 ON /vets");
  }
}
