package org.petclinic.automation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class OwnersListPage extends BasePage {
  private final Locator tableRows;

  public OwnersListPage(Page page) {
    super(page);
    this.tableRows = page.locator("table tbody tr");
  }

  public boolean hasResults() {
    return tableRows.count() > 0;
  }

  public OwnerDetailsPage openFirstOwner() {
    Locator link = page.locator("table tbody tr a").first();
    link.click();
    return new OwnerDetailsPage(page);
  }
}
