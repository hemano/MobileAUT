package com.carousell.pages;

import com.carousell.page.objects.ChooseCategoryPageObjects;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class ChooseCategoryPage extends CommonPageActions {

    ChooseCategoryPageObjects chooseCategoryPageObjects = new ChooseCategoryPageObjects();

    public ChooseCategoryPage(AppiumDriver<MobileElement> driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), chooseCategoryPageObjects);
    }


    public ChooseCategoryPage chooseCategory(String categoryName) {
        waitForPageToLoad(chooseCategoryPageObjects.searchFieldTxt).setValue(categoryName);

        selectCategoryFromList(categoryName);
        return this;
    }

    public void chooseSubCategory(String categoryName) {
        selectCategoryFromList(categoryName);
    }

    private void selectCategoryFromList(String categoryName) {
        chooseCategoryPageObjects.categoriesList.stream().filter(e ->
                e.getAttribute("text")
                .equalsIgnoreCase(categoryName))
                .findFirst()
                .get().click();
    }
}
