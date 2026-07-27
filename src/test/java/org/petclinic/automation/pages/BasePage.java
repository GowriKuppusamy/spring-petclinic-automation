package org.petclinic.automation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.petclinic.automation.config.ConfigReader;

public abstract class BasePage {
  protected final Page page;

  protected BasePage(Page page) {
    this.page = page;
  }

  protected String baseUrl() {
    return ConfigReader.getBaseUrl();
  }

  protected void click(Locator locator) {
    locator.click();
  }

  protected void type(Locator locator, String text) {
    locator.fill(text);
  }
}
