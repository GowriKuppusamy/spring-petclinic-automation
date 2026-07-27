package org.petclinic.automation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class VisitFormPage extends BasePage {
  private final Locator date;
  private final Locator description;
  private final Locator saveVisitButton;

  public VisitFormPage(Page page) {
    super(page);
    this.date = page.locator("input#date");
    this.description = page.locator("textarea#description");
    this.saveVisitButton = page.getByRole().button(new Page.GetByRoleOptions().setName("Add Visit"));
  }

  public VisitFormPage fill(String visitDate, String desc) {
    date.fill(visitDate);
    description.fill(desc);
    return this;
  }

  public OwnerDetailsPage save() {
    saveVisitButton.click();
    return new OwnerDetailsPage(page);
  }

  public boolean hasErrors() {
    return page.locator(".text-danger").count() > 0;
  }
}
