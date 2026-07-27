package org.petclinic.automation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class PetFormPage extends BasePage {
  private final Locator name;
  private final Locator birthDate;
  private final Locator typeSelect;
  private final Locator savePetButton;

  public PetFormPage(Page page) {
    super(page);
    this.name = page.locator("input#name");
    this.birthDate = page.locator("input#birthDate");
    this.typeSelect = page.locator("select#type");
    this.savePetButton = page.getByRole().button(new Page.GetByRoleOptions().setName("Save Pet"));
  }

  public PetFormPage fillPet(String petName, String bornDate, String type) {
    name.fill(petName);
    birthDate.fill(bornDate);
    typeSelect.selectOption(type);
    return this;
  }

  public OwnerDetailsPage save() {
    savePetButton.click();
    return new OwnerDetailsPage(page);
  }

  public boolean hasFieldErrors() {
    return page.locator(".text-danger").count() > 0;
  }

  public boolean hasDuplicateNameError() {
    return page.locator("span:has-text('duplicate')").count() > 0;
  }
}
