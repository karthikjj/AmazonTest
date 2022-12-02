package amazon.choices;

import amazon.factories.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;

public class BaseClass {

    public static WebDriver driver;

    public BaseClass(){
        driver = DriverFactory.getDriver();
    }

    @AfterSuite
    public static void quitDriver() {

        if (driver != null) {
            driver.quit();
        }
    }
}
