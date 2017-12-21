package com.carousell.pages;

import com.carousell.page.objects.SearchPageObjects;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class SearchPage extends CommonPageActions {

    SearchPageObjects searchPageObjects = new SearchPageObjects();

    public SearchPage(AppiumDriver<MobileElement> driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), searchPageObjects);
    }


    public SearchPage clickBrowseTab() {
        waitForListToLoad(searchPageObjects.tabsList);
        waitForPageToLoad(searchPageObjects.tabsList.get(0)).click();
        return this;
    }

    public SearchInCategoryPage selectCategoryByText(String categoryName) {

        boolean flag = searchPageObjects.categoriesList.stream().filter(e ->
                e.getAttribute("text").equalsIgnoreCase(categoryName)).findFirst().isPresent();
        while (!flag) {
            scrollDown(3);
            flag = searchPageObjects.categoriesList.stream().filter(e ->
                    e.getAttribute("text").equalsIgnoreCase(categoryName)).findFirst().isPresent();
        }

        searchPageObjects.categoriesList.stream().filter(e ->
                e.getAttribute("text").equalsIgnoreCase(categoryName)).findFirst().get().click();
        return new SearchInCategoryPage(driver);
    }


}
