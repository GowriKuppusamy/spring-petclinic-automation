package org.petclinic.automation.utils;

import io.qameta.allure.Attachment;
import org.petclinic.automation.core.DriverFactory;
import org.testng.iTestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class AllureTestListener implements ITestListener {

  @Override
  public void onTestFailure(ITestResult result) {
    attachScreenshot();
  }

  @Override
  public void onTestSkipped(ITestResult result) {
    // optional
  }

  @Override
  public void onTestStart(ITestResult result) {}

  @Override
  public void onFinish(ITestContext context) {}

  @Attachment(value = "screenshot on failure", type = "image/png")
  public byte[] attachScreenshot() {
    try {
      if (DriverFactory.getPage() == null) {
        return new byte[0];
      }
      return DriverFactory.getPage().screenshot(new com.microsoft.playwright.Page.ScreenshotOptions().setFullPage(true));
    } catch (Exception e) {
      return new byte[0];
    }
  }
}
