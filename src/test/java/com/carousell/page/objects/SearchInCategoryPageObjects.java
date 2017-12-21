package com.carousell.page.objects;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.support.CacheLookup;

import java.util.List;

public class SearchInCategoryPageObjects {

    @AndroidFindBy(id = "com.thecarousell.Carousell:id/bar_filter_title")
    public MobileElement sortOrFilterTab;

    @CacheLookup
    @AndroidFindBy(id = "com.thecarousell.Carousell:id/feature_button")
    public MobileElement okGotItBtn;

    @AndroidFindBy(id = "com.thecarousell.Carousell:id/radio_group")
    public MobileElement sortByRadioGroup;

    @CacheLookup
    @AndroidFindBy(id = "com.thecarousell.Carousell:id/btn_filter")
    public MobileElement seeFilteredResultsBtn;


    @AndroidFindBy(id = "com.thecarousell.Carousell:id/text_attribute_1")
    public List<MobileElement> sortedResultsList;


}
