package org.petclinic.automation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.petclinic.automation.pages.components.TopNavComponent;

public class WelcomePage extends BasePage {

  private final Locator heading;
  private final TopNavComponent nav;

  public WelcomePage(Page page) {
    super(page);
    this.heading = page.locator("h1");
    this.nav = new TopNavComponent(page);
  }

  public WelcomePage open() {
    page.navigate(baseUrl() + "/");
    return this;
  }

  public boolean isAt() {
    return heading.isVisible();
  }

  public String getHeadingText() {
    return heading.innerText();
  }

  public TopNavComponent nav() {
    return nav;
  }
}
