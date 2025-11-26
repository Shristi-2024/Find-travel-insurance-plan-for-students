package main;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;


public class MedicalInsurance_Page extends BasePage {

    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;
    Actions actions;

   // private static final Logger logger = LogManager.getLogger(MedicalInsurancePage.class);

    public MedicalInsurance_Page(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        
        this.js = (JavascriptExecutor) driver;
        this.actions = new Actions(driver);
       // logger.info("MedicalInsurancePage initialized.");
    }

    @FindBy(linkText = "View all products")
    WebElement viewAllProductsLink;

    @FindBy(xpath = "//*[@id='tab-content1']/div[1]/div[2]/ul/li")
    List<WebElement> medicalOptions;

    private void highlightElement(WebElement element) {
        js.executeScript("arguments[0].style.border='3px solid red'", element);
       // logger.debug("Highlighted element: " + element.toString());
    }

    public void clickViewAllProducts() {
       // logger.info("Attempting to click 'View all products' link.");
        wait.until(ExpectedConditions.elementToBeClickable(viewAllProductsLink));
        actions.moveToElement(viewAllProductsLink).perform();
        highlightElement(viewAllProductsLink);
        viewAllProductsLink.click();
       // logger.info("'View all products' link clicked.");
    }

    public List<String> fetchMedicalInsuranceOptions() {
       // logger.info("Fetching medical insurance options...");

        List<String> options = new ArrayList<>();

        try {
            if (medicalOptions == null) {
               // logger.error("medicalOptions is null. Check @FindBy locator or PageFactory initialization.");
                return options;
            }

            wait.until(ExpectedConditions.visibilityOfAllElements(medicalOptions));

            for (WebElement option : medicalOptions) {
                highlightElement(option); // Optional: for visual debugging
                String text = option.getText().trim();
                if (!text.isEmpty()) {
                    options.add(text);
                   // logger.debug("Fetched option: " + text);
                }
            }

           // logger.info("Total options fetched: " + options.size());

        } catch (Exception e) {
           // logger.error("Error while fetching medical insurance options", e);
        }

        return options;
    }
}