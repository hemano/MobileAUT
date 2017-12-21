package com.carousell.page.objects;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.support.CacheLookup;

import java.util.List;

public class SearchPageObjects {

    @CacheLookup
    @AndroidFindBy(id = "com.thecarousell.Carousell:id/fab_capture")
    public MobileElement cancelPromoteListingBtn;


    @AndroidFindBy(id = "com.thecarousell.Carousell:id/text_tab")
    public List<MobileElement> tabsList;


    @AndroidFindBy(id = "com.thecarousell.Carousell:id/text_collection")
    public List<MobileElement> categoriesList;

}
