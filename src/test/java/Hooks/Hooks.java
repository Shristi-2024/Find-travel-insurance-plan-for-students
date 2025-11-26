package Hooks;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import base.BaseClass;

import utilities.MedicalInsuranceUtil;
import utilities.ExcelWriterUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;


public class Hooks {
    public static WebDriver driver;
     // Declare as static to use in @AfterAll
    Properties p;
    public static List<String> options;
    @Before
    public void setup() throws IOException {
        driver = BaseClass.initializeBrowser();
        BaseClass.setDriver(driver);
        p = BaseClass.getProperties();
        driver.get(p.getProperty("appURL"));
        driver.manage().window().maximize();

        
    }

   
    
    @After
    public void tearDown() {
        // Write to Excel before quitting the driver
        

        driver.quit(); // ✅ Quit only after writing
    }
}
