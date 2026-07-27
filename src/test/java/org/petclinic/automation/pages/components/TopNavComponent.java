package org.petclinic.automation.pages.components;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.petclinic.automation.pages.BasePage;

public class TopNavComponent extends BasePage {

  private final Locator ownersLink;
  private final Locator vetsLink;

  public TopNavComponent(Page page) {
    super(page);
    this.ownersLink = page.getByRole().link(new Page.GetByRoleOptions().setName("FIND OWNERS"));
    this.vetsLink = page.getByRole().link(new Page.GetByRoleOptions().setName("VETERINARIANS"));
  }

  public void goToFindOwners() {
    ownersLink.click();
  }

  public void goToVets() {
    vetsLink.click();
  }
}
