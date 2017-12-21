package com.carousell.pages;

import com.carousell.page.objects.CompleteListingPageObjects;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class CompleteListingPage extends CommonPageActions {

    CompleteListingPageObjects completeListingPageObjects = new CompleteListingPageObjects();

    public CompleteListingPage(AppiumDriver<MobileElement> driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), completeListingPageObjects);
    }


    public PromoteListingPage enterTitleAndPrice(String listingTitle, String price){
        waitForPageToLoad(completeListingPageObjects.listingTitleText).setValue(listingTitle);
        completeListingPageObjects.priceText.setValue(price);
        completeListingPageObjects.listItBtn.click();
        return new PromoteListingPage(driver);
    }
}
