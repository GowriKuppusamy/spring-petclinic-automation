package org.petclinic.automation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class OwnerFormPage extends BasePage {
  private final Locator firstName;
  private final Locator lastName;
  private final Locator address;
  private final Locator city;
  private final Locator telephone;
  private final Locator submit;

  public OwnerFormPage(Page page) {
    super(page);
    this.firstName = page.locator("input#firstName");
    this.lastName = page.locator("input#lastName");
    this.address = page.locator("input#address");
    this.city = page.locator("input#city");
    this.telephone = page.locator("input#telephone");
    this.submit = page.getByRole().button(new Page.GetByRoleOptions().setName("Add Owner"));
    // edit form uses "Update Owner": we handle via fallback in submit()
  }

  public OwnerFormPage fillForm(String fn, String ln, String addr, String city, String phone) {
    firstName.fill(fn);
    lastName.fill(ln);
    address.fill(addr);
    city.fill(city);
    telephone.fill(phone);
    return this;
  }

  public OwnerDetailsPage submit() {
    Locator btn = page.getByRole().button(new Page.GetByRoleOptions().setName("Add Owner"));
    if (btn.count() == 0) {
      btn = page.getByRole().button(new Page.GetByRoleOptions().setName("Update Owner"));
    }
    btn.click();
    return new OwnerDetailsPage(page);
  }

  public boolean hasErrorsForRequiredFields() {
    return page.locator(".text-danger").count() > 0;
  }
}
