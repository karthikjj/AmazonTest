package POM;

import Utils.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static Utils.Utilities.*;

/**
 * Electronics Page Related Elements and methods
 */
public class Electronics {

    private static final By SORT  = By.xpath("//select[contains(@id,'sort')]");
    private static final By SHOW_MORE  = By.xpath("//a//span[text()='Show More']");
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

    /**
     * Go to Required Refinements
     * @param driver
     * @param value - Refinements Name
     * @return
     */
    public boolean goToRequiredRefinement(WebDriver driver,String value){
        boolean result =  scrollPageTillElementIsVisible(driver,Go_To_REFINEMENTS(value));
        return result;
    }

    /**
     * Select Required Refinement
     * @param driver
     * @param refinement - Refinement name
     * @param value - Refinement value
     */
    public void selectRequiredRefinement(WebDriver driver,String refinement ,String value){
        goToRequiredRefinement(driver,refinement);
        moveToElement(driver,SHOP_BY_REFINEMENTS(refinement,value));
        actionsClick(driver,SHOP_BY_REFINEMENTS(refinement,value));
    }

    /**
     * Sort By Price High To Low
     * @param driver
     */
    public void sortByPriceHighToLow(WebDriver driver){
        boolean result =  Utilities.scrollToTheElement(driver,SORT);
        if(result){
            selectByValue(driver,SORT,SORT_BY_PRICE_H2L);
        }
    }

    /**
     * Select Search Result Based On Index
     * @param driver
     * @param index - selects the required result in current page
     */
    public void selectSearchResultBasedOnIndex(WebDriver driver,String index){
        moveToElement(driver,GET_SEARCH_RESULT_BASED_ON_INDEX(index));
        actionsClick(driver,GET_SEARCH_RESULT_BASED_ON_INDEX(index));
    }

    /**
     * Go to About This Item Section And Get Text
     * @param driver
     * @return
     */
    public String getAboutThisItem(WebDriver driver){
        scrollPageTillElementIsVisible(driver,ABOUT_THIS_ITEM);
        return getElementText(driver, ABOUT_THIS_ITEM);

    }

    /**
     * Get All Text in About This Item Section
     * @param driver
     * @return
     */
    public List<String> getAboutThisItemSection(WebDriver driver){
        scrollPageTillElementIsVisible(driver,ABOUT_THIS_ITEM_SECTION);
        try{
            WebElement element = findElement(driver,SHOW_MORE);
            if(element.isDisplayed()){
                moveToElement(driver,SHOW_MORE);
                actionsClick(driver,SHOW_MORE);
            }
        }
        catch(Exception e){

        }
        List<String> sectionText = new ArrayList<String>();
        List<WebElement> elementList = findElements(driver,ABOUT_THIS_ITEM_SECTION);
        System.out.println("About this item :");
        for(WebElement element : elementList){
            String text = element.getText().trim();
            if(text!=null || !text.isBlank() || !text.isEmpty()) {
                sectionText.add(text);
                System.out.println(">>" + text);
            }
        }
        return sectionText;
    }




}
