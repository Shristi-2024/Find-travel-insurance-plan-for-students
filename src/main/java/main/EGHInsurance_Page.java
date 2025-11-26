package main;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;



import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EGHInsurance_Page extends BasePage {

    //private static final Logger logger = LogManager.getLogger(TravelInsurance_Page.class);

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    public EGHInsurance_Page(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.js = (JavascriptExecutor) driver;
    }

    // WebElements
    @FindBy(xpath = "//i[@class='icon-bg homeIconsBg icon-bg-new ghi']")
    private WebElement EgsLink;

    @FindBy(id = "MobileNo")
    private WebElement MobileNo;

    @FindBy(xpath = "//input[@id='NumberOfEmployees']")
    private WebElement NumberOfEmployees;

    @FindBy(xpath = "//button[normalize-space()='View Plan Instantly']")
    private WebElement Viewplan;

    @FindBy(xpath = "//ul[@class='buying-option']//li[1]//span[text()='Yes']")
    private WebElement Yes;

    @FindBy(id = "CompanyName")
    private WebElement CompanyName;

    @FindBy(xpath = "//button[contains(@class,'submit-btn')]")
    private WebElement continueBtn;

    @FindBy(xpath = "//li[text()='Bengaluru']")
    private WebElement city;

    @FindBy(xpath = "//div[contains(@class, 'supp-holder-logos')]//img")
    private List<WebElement> logos;

    // Utility Methods
    private void waitForElementToBeReady(WebElement element) {
        int retries = 3;
        while (retries > 0) {
            try {
                wait.until(ExpectedConditions.visibilityOf(element));
                wait.until(ExpectedConditions.elementToBeClickable(element));
                break;
            } catch (StaleElementReferenceException e) {
                retries--;
              //  logger.warn("StaleElementReferenceException encountered. Retrying...");
            }
        }
    }

    private void enterText(WebElement element, String text) {
        waitForElementToBeReady(element);
        highlightElement(element);
        element.clear();
        element.sendKeys(text);
    }

    private void highlightElement(WebElement element) {
        js.executeScript("arguments[0].style.border='3px solid red'", element);
    }

    private void scrollToElement(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    private void jsClick(WebElement element) {
        js.executeScript("arguments[0].click();", element);
    }

    private void waitAndClick(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            scrollToElement(element);
            highlightElement(element);
            element.click();
        } catch (ElementClickInterceptedException e) {
           // logger.warn("Click intercepted, trying JS click.");
            jsClick(element);
        }
    }

    private void waitForPageLoad() {
        if (js != null) {
            wait.until(webDriver -> js.executeScript("return document.readyState").equals("complete"));
        } else {
           // logger.error("JavaScriptExecutor is not initialized.");
        }
    }

    private void clickElement(WebElement element) {
        waitForElementToBeReady(element);
        highlightElement(element);
        element.click();
    }

    // Page Actions
    public void navigateToEgs() {
        waitForPageLoad();
        clickElement(EgsLink);
       // logger.info("Navigated to EGS link.");
    }

    public void enterUserDetails(String mobileNumber, String noOfEmployees) {
        enterText(MobileNo, mobileNumber);
        enterText(NumberOfEmployees, noOfEmployees);
      //  logger.info("Entered Mobile number and number of employees.");
    }

    public void selectViewPlan() {
        waitAndClick(Viewplan);
        //logger.info("Selected view plan instantly.");
    }

    public void selectYesOption() {
        try {
            WebElement dialog2 = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[@role='dialog' and contains(@class, 'MuiDialog-paper')]")));
            highlightElement(dialog2);
           // logger.info("Dialog is visible.");

            waitAndClick(Yes);
           // logger.info("Selected yes option.");
        } catch (TimeoutException e) {
            //logger.warn("Dialog did not appear within the expected time.");
        }
    }

    public void enterCompanyName() {
        enterText(CompanyName, "CTS");
       // logger.info("Company name entered.");
    }

    public void clickContinueButton() {
        waitAndClick(continueBtn);
       // logger.info("Clicked Continue button.");
    }

    public void selectCity() {
        waitAndClick(city);
        //logger.info("Clicked city Bengaluru.");
    }

    public void viewPlans() {
        //logger.info("Clicked View Plans button.");
    }

    public List<String> getInsurerNamesFromPlans() {
        List<String> insurerDetails = new ArrayList<>();

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
            List<WebElement> logos = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                    By.xpath("//div[contains(@class, 'supp-holder-logos')]//img")));

            JavascriptExecutor js = (JavascriptExecutor) driver;
            long scrollHeight = (long) js.executeScript("return document.body.scrollHeight");

            for (int j = 0; j < scrollHeight; j += 300) {
                js.executeScript("window.scrollTo(0, arguments[0]);", j);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                  //  logger.warn("Scroll delay interrupted: " + e.getMessage());
                }
            }

            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
           // logger.info("-----------plans---------");

            for (WebElement logo : logos) {
                try {
                    String logoAlt = logo.getAttribute("alt");
                    String logoSrc = logo.getAttribute("src");

                    String detail = "Insurer: " + logoAlt + ", Logo URL: " + logoSrc;
                    insurerDetails.add(detail);

                    //logger.info(detail);
                   // logger.info("-----------------------------");
                } catch (Exception e) {
                   // logger.error("Error extracting logo details: " + e.getMessage());
                }
            }

        } catch (TimeoutException te) {
          //  logger.error("Logos not found within the wait time: " + te.getMessage());
        } catch (Exception e) {
          //  logger.error("Unexpected error: " + e.getMessage());
        }

        return insurerDetails;
    }
}
    
    
    

