package com.carousell.pages;

import com.carousell.page.objects.HomePageObjects;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends CommonPageActions {

    HomePageObjects homePageObjects= new HomePageObjects();

    public HomePage(AppiumDriver<MobileElement> driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), homePageObjects);
    }


    public ChoosePhotosPage clickSell(){
        waitForPageToLoad(homePageObjects.sellBtn).click();
        return new ChoosePhotosPage(driver);
    }
}
