package com.carousell.pages;

import com.carousell.page.objects.SearchInCategoryPageObjects;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

public class SearchInCategoryPage extends CommonPageActions {

    SearchInCategoryPageObjects searchInCategoryPageObjects = new SearchInCategoryPageObjects();

    public SearchInCategoryPage(AppiumDriver<MobileElement> driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), searchInCategoryPageObjects);
    }


    public SearchInCategoryPage clickSortOrFilterTab(){

        waitForPageToLoad(searchInCategoryPageObjects.okGotItBtn).click();
        waitForPageToLoad(searchInCategoryPageObjects.sortOrFilterTab).click();
        return this;
    }

    public SearchInCategoryPage selectSortByRadioByText(String sortByOption){


        waitForPageToLoad(searchInCategoryPageObjects.sortByRadioGroup)
                .findElements(By.className("android.widget.RadioButton"))
                .stream().filter(r -> r.getAttribute("text").equalsIgnoreCase(sortByOption)).findFirst().get().click();

        return this;
    }

    public SearchInCategoryPage clickSeeFilteredResultsBtn(){
        searchInCategoryPageObjects.seeFilteredResultsBtn.click();
        return this;
    }


    public boolean isItemListed(String itemName){

        waitForListToLoad(searchInCategoryPageObjects.sortedResultsList);
        boolean flag = searchInCategoryPageObjects.sortedResultsList.stream().filter(e ->
                e.getAttribute("text").equalsIgnoreCase(itemName.trim())).findFirst().isPresent();
        while (!flag) {
            scrollDown(1);
            flag = searchInCategoryPageObjects.sortedResultsList.stream().filter(e ->
                    e.getAttribute("text").equalsIgnoreCase(itemName.trim())).findFirst().isPresent();
        }

        return flag;
    }


}
