package com.carousell.pages;

import com.carousell.page.objects.PromoteListingPageObjects;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class PromoteListingPage extends CommonPageActions {

    PromoteListingPageObjects promoteListingPageObjects= new PromoteListingPageObjects();

    public PromoteListingPage(AppiumDriver<MobileElement> driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), promoteListingPageObjects);
    }


    public SearchPage cancelPromotion(){
        waitForPageToLoad(promoteListingPageObjects.closePromotionBtn).click();
        return new SearchPage(driver);
    }
}
