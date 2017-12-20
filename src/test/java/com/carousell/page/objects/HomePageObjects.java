package com.carousell.page.objects;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.support.CacheLookup;

public class HomePageObjects {

    @CacheLookup
    @AndroidFindBy(id = "com.thecarousell.Carousell:id/button_sell")
    public MobileElement sellBtn;

}
