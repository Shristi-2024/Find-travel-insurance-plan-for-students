package stepdefinitions;

import io.cucumber.java.en.*;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import base.BaseClass;
import main.CarInsurance_Page;
import utilities.ConfigReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class CarInsuranceSteps {

    private static final Logger logger = LogManager.getLogger(CarInsuranceSteps.class);

    WebDriver driver;
    CarInsurance_Page carPage;

    @Given("I am on the Policybazaar Car Insurance page")
   public void i_am_on_the_policybazaar_car_insurance_page() {
        carPage = new CarInsurance_Page(BaseClass.getDriver());
        carPage.navigateToCarInsurance();
        logger.info("Navigated to Policybazaar Car Insurance page.");
    }

    @When("I proceed without entering a car number")
    public void i_proceed_without_entering_a_car_number() {
        carPage.proceedWithoutCarNumber();
        logger.info("Proceeded without entering car number.");
    }

    @When("I select the city")
    public void i_select_the_city() {
        carPage.selectCity();
        logger.info("Selected city.");
    }

    @When("I select the car brand")
    public void i_select_the_car_brand() {
        carPage.selectBrand();
        logger.info("Selected car brand.");
    }

    @When("I select the car model")
    public void i_select_the_car_model() {
        carPage.selectModel();
        logger.info("Selected car model.");
    }


@When("I select the fuel type")
    public void i_select_the_fuel_type() {
        carPage.selectFuelType();
        logger.info("Selected fuel type.");
    }
@When("I select the variant")
    public void i_select_the_variant() {
        carPage.selectVariant();
        logger.info("Selected variant.");
    }

@When("I enter user details with name and invalid mobile number from config")
    public void i_enter_user_details_with_name_and_invalid_mobile_number_from_config() {
        String username = ConfigReader.getProperty("car.insurance.username");
        String invalidMobile = ConfigReader.getProperty("car.insurance.invalid.mobile");
        carPage.enterUserDetails(username, invalidMobile);
        logger.info("Entered user details with name and invalid mobile number.");
    }

@Then("I should see an error message containing {string} for the mobile number field")
    public void i_should_see_an_error_message_containing_for_the_mobile_number_field(String expectedErrorPart) {
        String actualError = carPage.getErrorMessage();
        logger.info("Captured Error Message: " + actualError);
        Assert.assertTrue(actualError.toLowerCase().contains(expectedErrorPart.toLowerCase()),
                "Expected error message to contain '" + expectedErrorPart + "' but found '" + actualError + "'");
        logger.info("Validated error message contains expected text.");
}
@Then("I should capture the error message displayed")
public void i_should_capture_the_error_message_displayed() {
        carPage.takeScreenshot();
        logger.info("Captured screenshot of error message.");
    }
}