import POM.Electronics;
import POM.HomePage;
import Utils.Utilities;
import amazon.choices.BaseClass;
import amazon.config.EnvFactory;
import com.aventstack.extentreports.Status;
import com.typesafe.config.Config;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static Utils.Utilities.switchToNextWindow;
import static Utils.Utilities.waitForSomeTime;
import static Utils.Utilities.waitTillVisible;

public class TestAmazonSite extends BaseClass {
    private static Config config = EnvFactory.getInstance().getConfig();
    private static final String HOME_PAGE_URL = config.getString("HOME_PAGE_URL");


    HomePage homePage = new HomePage();
    Electronics electronics = new Electronics();

    public static String brand = "Brands";
    public static String tvBrand = "Samsung";

    @Tag("smokeTest")
    @DisplayName("Assignment - Amazon Test Scenario")
    @Test(priority = 0)
    public void assertThatHomePageTitleIsCorrect() {
        test = extent.createTest("Verify Home Page Tile");
        driver.get(HOME_PAGE_URL.replace(".com",".in"));
        waitTillVisible(driver);
        String expectedTitle = Utilities.getPageTitle(driver);
        String actualTile = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
        Assert.assertEquals(actualTile, expectedTitle);
        waitTillVisible(driver);
        test.log(Status.PASS,"Page Tile is "+expectedTitle);
    }

    @DisplayName("Select Televisions Category")
    @Test(priority = 1)
    public void selectTelevisions() {
        test = extent.createTest("Select Televisions Category");
        waitForSomeTime();
        homePage.clickHamburgerMenu(driver);
        homePage.goToShopByCategory(driver);
        homePage.selectTelevisionsCategory(driver);
        waitTillVisible(driver);
        test.log(Status.PASS,"Television Is Selected");
    }

    @DisplayName("Select By  Brand - Samsung")
    @Test(priority = 2)
    public void selectAndSortByBrand() {
        test = extent.createTest("Select By  Brand - Samsung");
        electronics.selectRequiredRefinement(driver,brand,tvBrand);
        electronics.sortByPriceHighToLow(driver);
        waitTillVisible(driver);
        test.log(Status.PASS,"Samsung Brand TV Is Selected Successfully.");
    }

    @DisplayName("Select Second Highest Price Television")
    @Test(priority = 2)
    public void selectSecondHighestResultAndAssertAboutThisItem() {
        test = extent.createTest("Select Second Highest Price Television");
        electronics.selectSearchResultBasedOnIndex(driver,"2");
        switchToNextWindow(driver);
        String expectedValue = electronics.getAboutThisItem(driver);
        Assert.assertEquals("About this item", expectedValue.trim());
        waitTillVisible(driver);
        test.log(Status.PASS,"Second Highest Price - Samsung Brand TV Is Selected Successfully.");
    }

    @DisplayName("Print About this item section")
    @Test(priority = 3)
    public void assertAboutThisItemSection() {
        test = extent.createTest("Print About this item section");
        List<String> sectionText = electronics.getAboutThisItemSection(driver);
        test.log(Status.PASS,"About this item :");
        for(String item : sectionText){
            String text = item.trim();
            if(text!=null){
                test.log(Status.PASS,">>"+text);
            }
        }
        waitTillVisible(driver);
    }


}
