package com.carousell.page.objects;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.support.CacheLookup;

public class CompleteListingPageObjects {

    @CacheLookup
    @AndroidFindBy(id = "com.thecarousell.Carousell:id/input_layout")
    public MobileElement listingTitleText;

    @CacheLookup
    @AndroidFindBy(id = "com.thecarousell.Carousell:id/text_input")
    public MobileElement priceText;

    @CacheLookup
    @AndroidFindBy(id = "com.thecarousell.Carousell:id/label_button_submit")
    public MobileElement listItBtn;

}
