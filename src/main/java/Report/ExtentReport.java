package Report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class ExtentReport {
    /*
    ExtentSparkReporter sparkReporter;

    public static ExtentReports extent;
    //helps to generate the logs in test report.
    public static ExtentTest extentTest;

    @Parameters({ "OS", "browser" })
    @BeforeTest
    public void startReport(String OS, String browser) {
        // initialize the Spark Reporter
        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") +"/test-output/testReport.html");

        //initialize ExtentReports and attach the Spark Reporter
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        //To add system or environment info by using the setSystemInfo method.
        extent.setSystemInfo("OS", OS);
        extent.setSystemInfo("Browser", browser);

        //configuration items to change the look and feel
        //add content, manage tests etc
        sparkReporter.config().setDocumentTitle("Extent Report - Amazon Test Scenario");
        sparkReporter.config().setReportName("Test Report");
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
    }

    @AfterMethod
    public void getResult(ITestResult result) {
        if(result.getStatus() == ITestResult.FAILURE) {
            extentTest.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" FAILED ", ExtentColor.RED));
            extentTest.fail(result.getThrowable());
        }
        else if(result.getStatus() == ITestResult.SUCCESS) {
            extentTest.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" PASSED ", ExtentColor.GREEN));
        }
        else {
            extentTest.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" SKIPPED ", ExtentColor.ORANGE));
            extentTest.skip(result.getThrowable());
        }
    }

    @AfterTest
    public void tearDown() {
        //to write or update test information to reporter
        extent.flush();
    }

     */
}
