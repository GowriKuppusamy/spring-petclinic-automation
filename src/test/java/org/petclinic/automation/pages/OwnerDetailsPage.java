package org.petclinic.automation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class OwnerDetailsPage extends BasePage {
  private final Locator ownerInfoTable;
  private final Locator editOwnerButton;
  private final Locator addPetLink;
  private final Locator firstPetNameLink;
  private final Locator addVisitLink;

  public OwnerDetailsPage(Page page) {
    super(page);
    this.ownerInfoTable = page.locator("table tr: text('Name')");
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
}
