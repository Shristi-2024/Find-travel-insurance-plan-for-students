package stepdefinitions;


import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;


import java.util.List;

import Hooks.Hooks;
import base.BaseClass;
import main.EGHInsurance_Page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EGHInsuranceSteps {

    private static final Logger logger = LogManager.getLogger(EGHInsuranceSteps.class);

    WebDriver driver = Hooks.driver;
    EGHInsurance_Page insurancePage;

    @Before
    public void setUp() {
        insurancePage = new EGHInsurance_Page(driver);
        //logger.info("TravelInsurance_Page initialized in @Before hook.");
    }

    @Given("I am on the Policybazaar website")
    public void i_am_on_the_policybazaar_website() {
        logger.info("Navigated to Policybazaar website via @Before hook.");
    }

    @When("the user clicks on the Employee Group Health Insurance link")
    public void user_clicks_on_egs_link() {
        insurancePage = new EGHInsurance_Page(BaseClass.getDriver());
        insurancePage.navigateToEgs();
        logger.info("Clicked on Employee Group Health Insurance link.");
    }

    @When("the user enters mobile number {string} and number of employees {string}")
    public void user_enters_mobile_and_employees(String mobileNumber, String employeeCount) {
        insurancePage.enterUserDetails(mobileNumber, employeeCount);
        logger.info("Entered mobile number: {} and number of employees: {}", mobileNumber, employeeCount);
    }

    @When("the user clicks on View Plan Instantly")
    public void user_clicks_on_view_plan_instantly() {
        insurancePage.selectViewPlan();
        logger.info("Clicked on View Plan Instantly.");
    }

    @When("the dialog appears and the user selects {string}")
    public void dialog_appears_and_user_selects_yes(String option) {
        if (option.equalsIgnoreCase("Yes")) {
            insurancePage.selectYesOption();
            logger.info("Dialog appeared and user selected: {}", option);
        } else {
            logger.warn("Unsupported option received: {}", option);
        }
    }

    @When("the user enters company name {string}")
    public void user_enters_company_name(String companyName) {
        insurancePage.enterCompanyName(); // Static "CTS" used in page method
        logger.info("Entered company name: {}", companyName);
    }

    @When("the user clicks on Continue button")
    public void user_clicks_on_continue_button() {
        insurancePage.clickContinueButton();
        logger.info("Clicked on Continue button.");
    }

    @When("the user selects city {string}")
    public void user_selects_city(String cityName) {
        insurancePage.selectCity(); // Static "Bengaluru" used in page method
        logger.info("Selected city: {}", cityName);
    }

    @When("the user clicks on View Plans button")
    public void user_clicks_on_view_plans_button() {
        insurancePage.viewPlans();
        logger.info("Clicked on View Plans button.");
    }

    @Then("the insurer names should be printed")
    public void the_insurer_names_should_be_printed() {
        List<String> insurerDetails = insurancePage.getInsurerNamesFromPlans();

        logger.info("----- Extracted Insurer Details -----");
        if (insurerDetails != null && !insurerDetails.isEmpty()) {
            for (String detail : insurerDetails) {
                logger.info(detail);
            }
        } else {
            logger.warn("No insurer details were extracted.");
        }
        logger.info("-------------------------------------");
    }
}



    	
    


