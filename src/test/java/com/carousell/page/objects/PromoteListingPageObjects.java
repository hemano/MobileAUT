package com.carousell.page.objects;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.support.CacheLookup;

public class PromoteListingPageObjects {

    @CacheLookup
    @AndroidFindBy(id = "com.thecarousell.Carousell:id/btn_close")
    public MobileElement closePromotionBtn;

}
