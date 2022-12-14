package POM;

import Utils.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
/*
Amazon Homepage related elements and methods
*/
public class HomePage {

    private static final By HAMBURGER_MENU  = By.id("nav-hamburger-menu");
    private static final By HAMBURGER_MENU_CANVAS  = By.id("hmenu-canvas");
    private static final By SHOP_BY_CATEGORY  = By.xpath("//a[@class='hmenu-item'][contains(.,'TV, Appliances, Electronics')]");
    private static final By TELEVISIONS  = By.xpath("//a[@class='hmenu-item'][contains(.,'Televisions')]");


    public HomePage(){

    }

    /**
     * click Hamburger Menu
     * @param driver
     */
    public void clickHamburgerMenu(WebDriver driver){
        Utilities.moveAndClick(driver,HAMBURGER_MENU);
        boolean result = Utilities.findElement(driver,HAMBURGER_MENU_CANVAS).isDisplayed();
        if(!result){
            Utilities.moveAndClick(driver,HAMBURGER_MENU);
        }
    }

    /**
     * Go To Shop By Category
     * @param driver
     */
    public void goToShopByCategory(WebDriver driver){
        boolean result = Utilities.scrollToTheElement(driver,SHOP_BY_CATEGORY);
        if(!result){
            Utilities.moveToElement(driver,SHOP_BY_CATEGORY);
        }
        Utilities.actionsClick(driver,SHOP_BY_CATEGORY);
    }

    /**
     * Select Televisions Category
     * @param driver
     */
    public void selectTelevisionsCategory(WebDriver driver){
        Utilities.moveToElement(driver,TELEVISIONS);
        Utilities.actionsClick(driver,TELEVISIONS);
    }

}
