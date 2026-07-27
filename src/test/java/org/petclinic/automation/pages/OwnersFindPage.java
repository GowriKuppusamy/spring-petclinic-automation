package org.petclinic.automation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class OwnersFindPage extends BasePage {
  private final Locator lastNameInput;
  private final Locator findOwnerButton;
  private final Locator errorMsg;

  public OwnersFindPage(Page page) {
    super(page);
    this.lastNameInput = page.locator("input#name");
    this.findOwnerButton = page.getByRole().button(new Page.GetByRoleOptions().setName("Find Owner"));
    this.errorMsg = page.locator(".required-field");
  }

  public OwnersFindPage open() {
    page.navigate(baseUrl() + "/owners/find");
    return this;
  }

  public OwnersFindPage searchByLastName(String lastName) {
    lastNameInput.fill(lastName);
    findOwnerButton.click();
    return this;
  }

  public boolean hasErrorMessage() {
    return errorMsg.isVisible();
  }
}
