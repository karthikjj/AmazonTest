package Utils;

import amazon.choices.WaitType;
import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.seleniumhq.jetty9.server.HttpChannelState.Action.WAIT;

public class Utilities {

    private static final long timeout = 50;


    public Utilities(){

    }

    public static WebElement findElement(WebDriver driver ,By by){
        WebElement webElement = driver.findElement(by);
        return webElement;
    }

    public static List<WebElement> findElements(WebDriver driver ,By by){
        List<WebElement> webElementList = driver.findElements(by);
        return webElementList;
    }

    public static void waitTillVisible(WebDriver driver){
        new WebDriverWait(driver, Duration.ofSeconds(180))
                .until(d -> ((JavascriptExecutor)d)
                        .executeScript("return document.readyState").equals("complete"));
    }

    private static WebElement waitUntilPresenceOfElementLocated(WebDriver driver ,By by) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private static WebElement waitUntilElementToBeClickable(WebDriver driver , By by) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    private static WebElement waitUntilElementToBeVisible(WebDriver driver , By by) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public static void sendKeys(WebDriver driver ,By by, String value) {
        WebElement element = waitUntilPresenceOfElementLocated(driver ,by);
        element.sendKeys(value);
        //ExtentLogger.pass("Value: " + value + " is successfully passed in textBox: " + element.getText(), true);
    }

    public static void click(WebDriver driver ,By by) {
        WebElement element = waitUntilElementToBeClickable(driver ,by);
        element.click();

       // ExtentLogger.pass("Clicking on: "+BOLD_START + element.getText() + BOLD_END , true);

    }

    public static void click(WebDriver driver,By by, WaitType waitType) {
        WebElement element = getElementAfterWait(driver,by, waitType);
        //  ExtentLogger.pass("Element: " + BOLD_START + element.getText() + BOLD_END + " is clicked successfully");
        //  ExtentLogger.pass(BOLD_START + element.getText() + BOLD_END + " is clicked successfully", true);
        //ExtentLogger.pass("Clicking on: "+BOLD_START + element.getText() + BOLD_END , true);
        element.click();

    }

    private static WebElement getElementAfterWait(WebDriver driver ,By by, WaitType waitType) {
        //waitUntilElementToBeClickable(by).click();
        WebElement element = null;
        if (waitType == WaitType.PRESENCE) {
            element = waitUntilPresenceOfElementLocated(driver,by);
        } else if (waitType == WaitType.CLICKABLE) {
            element = waitUntilElementToBeClickable(driver,by);
        } else if (waitType == WaitType.VISIBLE) {
            element = waitUntilElementToBeVisible(driver,by);
        }
        return element;
    }

    public static void click(WebDriver driver ,By by, WaitType waitType, String elementName) {
        WebElement element = getElementAfterWait(driver,by, waitType);
//        ExtentLogger.pass("Element: " + BOLD_START + elementName + BOLD_END + " is clicked successfully.");
        //ExtentLogger.pass(BOLD_START + elementName + BOLD_END + " is clicked successfully.", true);
       // ExtentLogger.pass("Clicking on: "+BOLD_START + elementName + BOLD_END , true);
        element.click();

    }


    public static String getElementText(WebDriver driver,By by) {
        WebElement element = waitUntilPresenceOfElementLocated(driver,by);
        //ExtentLogger.info("Element Text: " + BOLD_START + element.getText() + BOLD_END);
        return element.getText();
    }

    public static String getPageTitle(WebDriver driver) {
        String title = driver.getTitle();
        //ExtentLogger.info("Page title: " + BOLD_START + title + BOLD_END);
        return title;
    }


    public static WebElement getElement(WebDriver driver ,By by) {
        return waitUntilElementToBeVisible(driver,by);
    }

    public static void captureScreenshot() {
        //ExtentLogger.captureScreenshot();
    }

    public static void waitForSomeTime() {
        Uninterruptibles.sleepUninterruptibly(WAIT.ordinal(), TimeUnit.SECONDS);
    }

    public static void waitForGivenTime(long time) {
        Uninterruptibles.sleepUninterruptibly(time, TimeUnit.SECONDS);
    }

    public static String getWebPageURL(WebDriver driver) {
        String url = driver.getCurrentUrl();
        //ExtentLogger.info("Page URL: " + BOLD_START + url + BOLD_END);
        return url;
    }

    public static WebElement moveToElement(WebDriver driver,By by) {
        WebElement element = waitUntilPresenceOfElementLocated(driver,by);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
        //ExtentLogger.info("Element Text: " + BOLD_START + element.getText() + BOLD_END);
        return element;
    }

    public static WebElement actionsClick(WebDriver driver,By by) {
        WebElement element = waitUntilPresenceOfElementLocated(driver,by);
        Actions actions = new Actions(driver);
        actions.click(element).perform();
        //ExtentLogger.info("Element Text: " + BOLD_START + element.getText() + BOLD_END);
        return element;
    }

    public static WebElement selectByValue(WebDriver driver,By by,String value){
        WebElement element = waitUntilPresenceOfElementLocated(driver,by);
        Select select = new Select(element);
        select.selectByValue(value);
        return element;
    }

    public static WebElement selectByIndex(WebDriver driver,By by,int value){
        WebElement element = waitUntilPresenceOfElementLocated(driver,by);
        Select select = new Select(element);
        select.selectByIndex(value);
        return element;
    }

    public static WebElement selectByVisibleText(WebDriver driver,By by,String value){
        WebElement element = waitUntilPresenceOfElementLocated(driver,by);
        Select select = new Select(element);
        select.selectByVisibleText(value);
        return element;
    }

    public static boolean scrollToTheElement(WebDriver driver ,By by){
        WebElement element = driver.findElement(by);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        waitTillVisible(driver);
        if(element.isDisplayed()){
            return true;
        }
        return false;
    }

    public static  boolean scrollPageTillElementIsVisible(WebDriver driver ,By by){
        boolean result = scrollToTheElement(driver, by);
        while (!result) {
            result = scrollToTheElement(driver, by);
            if(result){
                break;
            }
        }
        return result;
    }

    public static  void scrollPageHeight(WebDriver driver ) throws InterruptedException {
        long temp = 0;
        while (true) {
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
            long start = (Long) ((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight");
            if (start == temp) {
                break;
            }
            temp = start;
        }
    }

    public static String getCurrentWindow(WebDriver driver){
        return driver.getWindowHandle();
    }

    public static void switchToNextWindow(WebDriver driver){
        String currentWindow = getCurrentWindow(driver);
        Set<String> windows = driver.getWindowHandles();
        for(String window : windows){
            if(!window.equals(currentWindow)){
                driver.switchTo().window(window);
            }
        }
    }

}
