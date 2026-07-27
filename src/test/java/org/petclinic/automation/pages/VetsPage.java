package org.petclinic.automation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class VetsPage extends BasePage {
  private final Locator tableRows;

  public VetsPage(Page page) {
    super(page);
    this.tableRows = page.locator("table tbody tr");
  }

  public VetsPage open() {
    page.navigate(baseUrl() + "/vets.html");
    return this;
  }

  public boolean hasVets() {
    return tableRows.count() > 0;
  }
}
