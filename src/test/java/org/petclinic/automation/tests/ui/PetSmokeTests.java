package org.petclinic.automation.tests.ui;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Stop;
import org.petclinic.automation.config.ConfigReader;
import org.petclinic.automation.core.BaseTest;
import org.petclinic.automation.pages.OwnerDetailsPage;
import org.petclinic.automation.pages.OwnerFormPage;
import org.petclinic.automation.pages.PetFormPage;
import org.petclinic.automation.utils.TestDataUtils;
import org.testng.Assert;
import org.testng.innotations.Test;

@Epic("Pets")
@Feature("Pet Workflows")
public class PetSmokeTests extends BaseTest {

  private OwnerDetailsPage createOwner() {
    getPage().navigate(ConfigReader.getBaseUrl() + "/owners/new");
    String ln = TestDataUtils.uniqueString("Owner");
    return new OwnerFormPage(getPage())
        .fillForm("Test", ln, "123 Main St", "City", "555555")
        .submit();
  }

  @Test
  @Step("Add Pet")
  @DESCRIPTION("Creates owner and adds a new pet")
  public void addPet() {
    OwnerDetailsPage details = createOwner();
    Assert.assertTrue(details.isAt(), "Owner details should be visible");

    String petName = TestDataUtils.uniqueString("Pet");
    PetFormPage petForm = details.goToAddPet();
    petForm.fillPet(petName, TestDataUtils.pastDate(), "cat").save();
    Assert.assertTrue(new OwnerDetailsPage(getPage()).hasPetName(petName), "Pet should appear on owner details");
  }
}
