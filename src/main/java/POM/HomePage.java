package POM;

import Utils.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    private static final By HAMBURGER_MENU  = By.id("nav-hamburger-menu");
    private static final By SHOP_BY_CATEGORY  = By.xpath("//a[@class='hmenu-item'][contains(.,'TV, Appliances, Electronics')]");
    private static final By TELEVISIONS  = By.xpath("//a[@class='hmenu-item'][contains(.,'Televisions')]");


    public HomePage(){

    }

    public void clickHamburgerMenu(WebDriver driver){
        Utilities.moveToElement(driver,HAMBURGER_MENU);
        Utilities.actionsClick(driver,HAMBURGER_MENU);
    }

    public void goToShopByCategory(WebDriver driver){
        Utilities.scrollToTheElement(driver,SHOP_BY_CATEGORY);
        Utilities.moveToElement(driver,SHOP_BY_CATEGORY);
        Utilities.actionsClick(driver,SHOP_BY_CATEGORY);
    }

    public void selectTelevisionsCategory(WebDriver driver){
        Utilities.moveToElement(driver,TELEVISIONS);
        Utilities.actionsClick(driver,TELEVISIONS);
    }

}
