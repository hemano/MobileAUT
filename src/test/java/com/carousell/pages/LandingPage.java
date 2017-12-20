package com.carousell.pages;

import com.carousell.page.objects.LandingPageObjects;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends CommonPageActions {

    LandingPageObjects landingPageObjects = new LandingPageObjects();

    public LandingPage(AppiumDriver<MobileElement> driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), landingPageObjects);
    }


    public LoginPage clickLoginBtn(){
        waitForPageToLoad(landingPageObjects.LogInBtn).click();
        return new LoginPage(driver);
    }
}
