package POM;

import Utils.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static Utils.Utilities.*;


public class Electronics {

    private static final By SORT  = By.xpath("//select[contains(@id,'sort')]");

    private static final By ABOUT_THIS_ITEM_SECTION  = By.xpath("//div[@id='feature-bullets']//ul//span");
    private static final By ABOUT_THIS_ITEM  = By.xpath("//div[@id='feature-bullets']/h1");
    private static final String SORT_BY_PRICE_H2L = "price-desc-rank";
    private static final By Go_To_REFINEMENTS(String value){
        return By.xpath("//div/span[text()='"+value+"']");
    }

    private static final By SHOP_BY_REFINEMENTS(String refinement ,String value){
        return By.xpath("//div/span[contains(text(),'"+refinement+"')]//following::span[text()='"+value+"']");
    }

    private static final By GET_SEARCH_RESULT_BASED_ON_INDEX(String value){
        return By.xpath("//div[@data-index='"+value+"']//div[@class='a-section a-spacing-base']");
    }

    public Electronics(){

    }

    public boolean goToRequiredRefinement(WebDriver driver,String value){
        boolean result =  scrollPageTillElementIsVisible(driver,Go_To_REFINEMENTS(value));
        return result;
    }

    public void selectRequiredRefinement(WebDriver driver,String refinement ,String value){
        goToRequiredRefinement(driver,refinement);
        moveToElement(driver,SHOP_BY_REFINEMENTS(refinement,value));
        actionsClick(driver,SHOP_BY_REFINEMENTS(refinement,value));
    }

    public void sortByPriceHighToLow(WebDriver driver){
        boolean result =  Utilities.scrollToTheElement(driver,SORT);
        if(result){
            selectByValue(driver,SORT,SORT_BY_PRICE_H2L);
        }
    }

    public void selectSearchResultBasedOnIndex(WebDriver driver,String index){
        moveToElement(driver,GET_SEARCH_RESULT_BASED_ON_INDEX(index));
        actionsClick(driver,GET_SEARCH_RESULT_BASED_ON_INDEX(index));
    }

    public String getAboutThisItem(WebDriver driver){
        scrollPageTillElementIsVisible(driver,ABOUT_THIS_ITEM);
        return getElementText(driver, ABOUT_THIS_ITEM);

    }

    public List<String> getAboutThisItemSection(WebDriver driver){
        scrollPageTillElementIsVisible(driver,ABOUT_THIS_ITEM_SECTION);
        List<String> sectionText = new ArrayList<String>();
        List<WebElement> elementList = findElements(driver,ABOUT_THIS_ITEM_SECTION);
        System.out.println("About this item :");
        for(WebElement element : elementList){
            String text = element.getText().trim();
            sectionText.add(text);
            System.out.println(">>"+text);
        }
        return sectionText;
    }




}
