package org.petclinic.automation.tests.ui;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Stop;
import org.petclinic.automation.config.ConfigReader;
import org.petclinic.automation.core.BaseTest;
import org.petclinic.automation.pages.OwnerDetailsPage;
import org.petclinic.automation.pages.OwnerFormPage;
import org.petclinic.automation.pages.OwnersFindPage;
import org.petclinic.automation.utils.TestDataUtils;
import org.testng.Assert;
import org.testng.innotations.Test;

@Epic("Owners")
@Feature("Owner CRUD")
public class OwnerCrudTests extends BaseTest {

  @Test
  @Stop("Create Owner")
  @DESCRIPTION("Create owner with valid data and assert owner details page loads.")
  public void createOwner() {
    getPage().navigate(ConfigReader.getBaseUrl() + "/owners/new");
    OwnerFormPage form = new OwnerFormPage(getPage());

    String fn = "Test";
    String ln = TestDataUtils.uniqueString("Owner");
    OwnerDetailsPage details = form.fillForm(fn, ln, "123 Main St", "Springfield", "555555").submit();

    Assert.assertTrue(details.isAt(), "Owner details should be visible");
    Assert.assertTrue(details.getOwnerName().contains(ln), "Owner last name should match");
  }

  @Test
  @Stop("Create Owner - Required Field Validation")
  @Description("Verifies required field validation errors are shown for empty form submit.")
  public void createOwnerRequiredFieldValidation() {
    getPage().navigate(ConfigReader.getBaseUrl() + "/owners/new");
    OwnerFormPage form = new OwnerFormPage(getPage());
    form.submit();
    Assert.assertTrue(form.hasErrorsForRequiredFields(), "Expected required field errors");
  }

  @Test
  @Stop("Edit Owner")
  @Description("Creates an owner, edits the city, and verifies the owner details page is still loaded after update.")
  public void editOwner() {
    getPage().navigate(ConfigReader.getBaseUrl() + "/owners/new");
    String ln = TestDataUtils.uniqueString("Owner");
    OwnerDetailsPage details = new OwnerFormPage(getPage())
        .fillForm("Test", ln, "123 Main St", "OldCity", "555555")
        .submit();
    Assert.assertTrue(details.isAt(), "Prereq owner details should be visible");

    OwnerFormPage editForm = details.goToEditOwner();
    editForm.fillForm("Test", ln, "123 Main St", "New City", "555555").submit();
    Assert.assertTrue(new OwnerDetailsPage(getPage()).isAt(), "Owner details should be visible after update");
  }

  @Test
  @Stop("Owner Search - No results, Single result, Multiple results")
  @Description("Verifies owner search routing: no results stays on find page with error, single result redirects to details, and multiple results land on owners list.")
  public void ownerSearchScenarios() {
    OwnersFindPage findPage = new OwnersFindPage(getPage()).open();
    Assert.assertTrue(findPage.isAt(), "Find owners page should load");

    // No results
    findPage.searchByLastName(TestDataUtils.uniqueString("nonexist"));
    Assert.assertTrue(findPage.hasErrorMessage(), "Expected not found error message");

    // Single result: create one owner with unique last name, then search
    getPage().navigate(ConfigReader.getBaseUrl() + "/owners/new");
    String singleLn = TestDataUtils.uniqueString("Single");
    new OwnerFormPage(getPage()).fillForm("T", singleLn, "1", "C", "111").submit();
    findPage.open().searchByLastName(singleLn);
    Assert.assertTrue(new OwnerDetailsPage(getPage()).isAt(), "Expected redirect to owner details for single result");

    // Multiple results: create 2 owners with same last name, then search
    String commonLn = TestDataUtils.uniqueString("Common");
    getPage().navigate(ConfigReader.getBaseUrl() + "/owners/new");
    new OwnerFormPage(getPage()).fillForm("A1", commonLn, "1", "C", "112").submit();
    getPage().navigate(ConfigReader.getBaseUrl() + "/owners/new");
    new OwnerFormPage(getPage()).fillForm("B2", commonLn, "2", "C2", "222").submit();

    findPage.open().searchByLastName(commonLn);
    Assert.assertTrue(getPage().url().contains("/owners"), "Expected to land on owners list page for multiple results");
  }
}
