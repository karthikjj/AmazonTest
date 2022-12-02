import POM.Electronics;
import POM.HomePage;
import Utils.Utilities;
import amazon.choices.BaseClass;
import amazon.config.EnvFactory;
import com.typesafe.config.Config;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static Utils.Utilities.switchToNextWindow;
import static Utils.Utilities.waitTillVisible;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        driver.get(HOME_PAGE_URL.replace(".com",".in"));
        driver.manage().window().maximize();
        String expectedTitle = Utilities.getPageTitle(driver);
        String actualTile = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
        Assert.assertEquals(actualTile, expectedTitle);
        waitTillVisible(driver);
    }

    @DisplayName("Select Televisions Category")
    @Test(priority = 1)
    public void selectTelevisions() {
        homePage.clickHamburgerMenu(driver);
        homePage.goToShopByCategory(driver);
        homePage.selectTelevisionsCategory(driver);
        waitTillVisible(driver);

    }

    @DisplayName("Select By  Brand - Samsung")
    @Test(priority = 2)
    public void selectAndSortByBrand() {
        electronics.selectRequiredRefinement(driver,brand,tvBrand);
        electronics.sortByPriceHighToLow(driver);
        waitTillVisible(driver);

    }

    @DisplayName("Select Second Highest Price Television")
    @Test(priority = 2)
    public void selectSecondHighestResultAndAssertAboutThisItem() {
        electronics.selectSearchResultBasedOnIndex(driver,"2");
        switchToNextWindow(driver);
        String expectedValue = electronics.getAboutThisItem(driver);
        Assert.assertEquals("About this item", expectedValue.trim());
        waitTillVisible(driver);

    }

    @DisplayName("Print About this item section")
    @Test(priority = 3)
    public void assertAboutThisItemSection() {
        List<String> sectionText = electronics.getAboutThisItemSection(driver);
        waitTillVisible(driver);
    }


}
