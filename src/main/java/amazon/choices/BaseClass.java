package amazon.choices;

import Report.ExtentManager;
import amazon.factories.DriverFactory;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;
import java.time.Duration;

import static Utils.Utilities.timeout;

public class BaseClass  {

    public static WebDriver driver;

    public static ExtentReports extent = getInstance();

    public static ExtentTest test;

    static ExtentSparkReporter sparkReporter ;


    public BaseClass(){
        driver = DriverFactory.getDriver();
    }

    @AfterSuite
    public static void quitDriver() {

        if (driver != null) {
            driver.quit();
        }
    }

    public static ExtentReports getInstance() {
        if (extent == null)
            createInstance();

        return extent;
    }

    public static ExtentReports createInstance() {
        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") +"/test-output/testReport.html");
        sparkReporter.config().setDocumentTitle("Extent Report - Amazon Test Scenario");
        sparkReporter.config().setReportName("Test Report");
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setEncoding("utf-8");
        sparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        return extent;
    }

    @BeforeSuite
    public void beforeSuite() {
        extent = ExtentManager.createInstance();
        extent.attachReporter(sparkReporter);
        driver.manage().window().maximize();
    }


    @AfterMethod
    public void getResult(ITestResult result) {
        if(result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" FAILED ", ExtentColor.RED));
            test.fail(result.getThrowable());
        }
        else if(result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" PASSED ", ExtentColor.GREEN));
        }
        else {
            test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" SKIPPED ", ExtentColor.ORANGE));
            test.skip(result.getThrowable());
        }
    }


    //to write or update test information to reporter
    @AfterTest
    public void tearDown() {
        extent.flush();
    }



}
