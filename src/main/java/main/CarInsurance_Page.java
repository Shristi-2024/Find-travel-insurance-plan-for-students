package main;

import java.io.File;
import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;

public class CarInsurance_Page extends BasePage {

   // private static final Logger logger = LogManager.getLogger(CarInsurance_Page.class);

    WebDriver driver;
    WebDriverWait wait;
    private JavascriptExecutor js;

    public CarInsurance_Page(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.js = (JavascriptExecutor) driver;
       // logger.info("CarInsurance_Page initialized.");
    }

    @FindBy(xpath = "//div[@class='prd-block car']/a")
    private WebElement carInsuranceLink;

    @FindBy(xpath = "//span[@class='CarRegDetails_blueTextButton__P1blP blueTextButton fontMedium']")
    private WebElement proceedWithoutCarNumber;

    @FindBy(xpath = "//span[@class='truncate' and text()='Bangalore']")
    private WebElement citySelect;

    @FindBy(xpath = "//span[@class='brandName' and text()='MAHINDRA']")
    private WebElement brandSelect;

    @FindBy(xpath = "//li[normalize-space()='THAR']")
    private WebElement modelSelect;

    @FindBy(xpath = "//li[normalize-space()='Petrol']")
    private WebElement fuelTypeSelect;

    @FindBy(xpath = "//li[normalize-space()='LX 4-STR Hard Top Petrol AT RWD (1997 cc)']")
    private WebElement variantSelect;

    @FindBy(id = "name-form-control")
    private WebElement nameField;

    @FindBy(id = "mobile-form-control")
    private WebElement mobileField;

    @FindBy(xpath = "//div[@class='errorMsg']")
    private WebElement errorMsg;

    private void waitForPageLoad() {
        if (js != null) {
            wait.until(webDriver -> js.executeScript("return document.readyState").equals("complete"));
           // logger.info("Page load complete.");
        } else {
           // logger.warn("JavaScriptExecutor is not initialized.");
        }
    }

    private void highlightElement(WebElement element) {
        try {
            js.executeScript("arguments[0].style.border='3px solid red'", element);
           // logger.debug("Highlighted element: " + element.toString());
        } catch (StaleElementReferenceException e) {
        	 // logger.warn("Skipping highlight due to stale element: " + e.getMessage());
        }
    }

    private void waitForElementToBeReady(WebElement element) {
        int retries = 3;
        while (retries > 0) {
            try {
                wait.until(ExpectedConditions.visibilityOf(element));
                wait.until(ExpectedConditions.elementToBeClickable(element));
                // logger.debug("Element is ready: " + element.toString());
                break;
            } catch (StaleElementReferenceException e) {
                retries--;
                // logger.warn("Retrying due to stale element: " + e.getMessage());
            }
        }
    }

    private void clickElement(WebElement element) {
        waitForElementToBeReady(element);
        highlightElement(element);
        element.click();
        //logger.info("Clicked element: " + element.toString());
    }

    private void enterText(WebElement element, String text) {
        waitForElementToBeReady(element);
        highlightElement(element);
        element.clear();
        element.sendKeys(text);
        //logger.info("Entered text '" + text + "' into element: " + element.toString());
    }

    public void navigateToCarInsurance() {
        waitForPageLoad();
        clickElement(carInsuranceLink);
        //logger.info("Navigated to Car Insurance section.");
    }

    public void proceedWithoutCarNumber() {
        clickElement(proceedWithoutCarNumber);
        //logger.info("Proceeded without car number.");
    }

    public void selectCity() {
        clickElement(citySelect);
        // logger.info("Selected city: Bangalore.");
    }

    public void selectBrand() {
        clickElement(brandSelect);
        //logger.info("Selected brand: MAHINDRA.");
    }

    public void selectModel() {
        clickElement(modelSelect);
        //logger.info("Selected model: THAR.");
    }

    public void selectFuelType() {
        clickElement(fuelTypeSelect);
        //logger.info("Selected fuel type: Petrol.");
    }

    public void selectVariant() {
        clickElement(variantSelect);
       // logger.info("Selected variant: LX 4-STR Hard Top Petrol AT RWD.");
    }

    public void enterUserDetails(String name, String mobile) {
        enterText(nameField, name);
        enterText(mobileField, mobile);
        //logger.info("Entered user details: Name = " + name + ", Mobile = " + mobile);
    }

    public String getErrorMessage() {
        waitForElementToBeReady(errorMsg);
        highlightElement(errorMsg);
        String error = errorMsg.getText();
        //logger.warn("Error message displayed: " + error);
        return error;
    }

    public void takeScreenshot() {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source=ts.getScreenshotAs(OutputType.FILE);
            File target=new File(System.getProperty("user.dir")+"\\screenshots\\error.png");
    		source.renameTo(target);
           // logger.info("Screenshot saved at: " + target.getAbsolutePath());
        } catch (Exception e) {
           // logger.error("Failed to take screenshot: " + e.getMessage());
        }
    }
}