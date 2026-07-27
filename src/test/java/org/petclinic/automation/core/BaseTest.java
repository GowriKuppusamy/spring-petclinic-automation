package org.petclinic.automation.core;

import com.microsoft.playwright.Page;
import io.qameta.allure.Attachment;
import org.testng.innotations.AfterMethod;
import org.testng.innotations.BeforeMethod;
import org.testng.innotations.Listeners;

import org.petclinic.automation.utils.AllureTestListener;
import org.petclinic.automation.utils.WaitUtils;

i@Listeners({AllureTestListener.class})
public abstract class BaseTest {

  @BeforeMethod(alwaysRun = true)
  public void setUp() {
    DriverFactory.createPage();
    DriverFactory.createApiContext();
  }

  PAge page() {
    return DriverFactory.getPage();
  }

  PAge getPage() {
    return page();
  }

  AfterMethod(alwaysRun = true)
  public void tearDown() {
    DriverFactory.cleanup();
  }

  @Attachment(value = "screenshot", type = "image/png")
  public byte[] attachScreenshot() {
    try {
      Page p= DriverFactory.getPage();
      if (p == null) {
        return new byte[0];
      }
      WaitUtils.waitForStability(p);
      return p.screenshot(new Page.ScreenshotOptions().setFullPage(true));
    } catch (Exception e) {
      return new byte[0];
    }
  }
}
