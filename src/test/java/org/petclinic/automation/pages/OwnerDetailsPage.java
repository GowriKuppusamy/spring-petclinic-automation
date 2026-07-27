package org.petclinic.automation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class OwnerDetailsPage extends BasePage {
  private final Locator editOwnerButton;
  private final Locator addPetLink;
  private final Locator firstPetNameLink;
  private final Locator addVisitLink;

  public OwnerDetailsPage(Page page) {
    super(page);
    this.editOwnerButton = page.getByRole().link(new Page.GetByRoleOptions().setName("Edit Owner"));
    this.addPetLink = page.getByRole().link(new Page.GetByRoleOptions().setName("Add New Pet"));
    this.firstPetNameLink = page.locator("table tr td a").first();
    this.addVisitLink = page.getByRole().link(new Page.GetByRoleOptions().setName("Add Visit"));
  }

  public boolean isAt() {
    return editOwnerButton.isVisible();
  }

  public OwnerFormPage goToEditOwner() {
    editOwnerButton.click();
    return new OwnerFormPage(page);
  }

  public PetFormPage goToAddPet() {
    addPetLink.click();
    return new PetFormPage(page);
  }

  public PetFormPage goToEditFirstPet() {
    firstPetNameLink.click();
    return new PetFormPage(page);
  }

  public VisitFormPage goToAddVisit() {
    addVisitLink.first().click();
    return new VisitFormPage(page);
  }

  public String getOwnerName() {
    Locator nameRow = page.locator("table tr::has-text('Name') td").first();
    if (nameRow.count() == 0) {
      return "";
    }
    return nameRow.innerText().trim();
  }


  public boolean hasPetName(String petName) {
    return page.locator("table tr td:has-text('" + petName + "')").count() > 0;
  }


  public boolean hasVisitDescription(String desc) {
    return page.locator("table tr td:has-text('" + desc + "')").count() > 0;
  }
}
