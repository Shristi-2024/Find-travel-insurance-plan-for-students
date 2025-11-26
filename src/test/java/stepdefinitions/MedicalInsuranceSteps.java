package stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utilities.MedicalInsuranceUtil;
import base.BaseClass;
import main.MedicalInsurance_Page;
import utilities.ExcelWriterUtil;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MedicalInsuranceSteps {

    private static final Logger logger = LogManager.getLogger(MedicalInsuranceSteps.class);

    private List<String> fetchedOptions;
    private MedicalInsurance_Page medicalPage;

    @Given("I navigate to the {string} section")
    public void i_navigate_to_the_section(String sectionName) {
        WebDriver driver = BaseClass.getDriver();
        medicalPage = new MedicalInsurance_Page(driver);

        if (sectionName.equalsIgnoreCase("View All Products")) {
            medicalPage.clickViewAllProducts();
            logger.info("Navigated to 'View All Products' section.");
        } else {
            logger.error("Unsupported section specified: " + sectionName);
            throw new IllegalArgumentException("Unsupported section specified in Gherkin: " + sectionName);
        }
    }

    @When("I fetch the list of medical insurance options")
    public void i_fetch_the_list_of_medical_insurance_options() {
        fetchedOptions = medicalPage.fetchMedicalInsuranceOptions();
        logger.info("Fetched medical insurance options.");
    }

    @Then("I should see more than zero medical insurance options displayed")
    public void i_should_see_more_than_zero_medical_insurance_options_displayed() {
        boolean hasOptions = fetchedOptions != null && fetchedOptions.size() > 0;
        logger.info("Number of options fetched: " + (fetchedOptions != null ? fetchedOptions.size() : 0));
        Assert.assertTrue(hasOptions,
                "Expected to find more than zero medical insurance options, but none were found or fetched.");
    }

    @And("I should print all fetched medical insurance options")
    public void i_should_print_all_fetched_medical_insurance_options() {
        logger.info("--- Fetched Medical Insurance Options ---");

        if (fetchedOptions != null && !fetchedOptions.isEmpty()) {
            for (String option : fetchedOptions) {
                logger.info("- " + option);
                ExcelWriterUtil.logResult( option, "PASS");
            }

            // Write all options to Excel file
            MedicalInsuranceUtil.writeMedicalOptionsToExcel(fetchedOptions);
            ExcelWriterUtil.saveExcel(".//src/test/resources/testdata/MedicalInsuranceOptions.xlsx");

        } else {
            logger.warn("No medical insurance options were fetched in the previous step.");
            ExcelWriterUtil.logResult("No medical insurance options were fetched", "FAIL");
        }

        logger.info("-----------------------------------------");
    }
}