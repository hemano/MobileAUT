package com.carousell.page.objects;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.support.CacheLookup;

import java.util.List;

public class ChooseCategoryPageObjects {

    @CacheLookup
    @AndroidFindBy(id = "com.thecarousell.Carousell:id/search_field")
    public MobileElement searchFieldTxt;


    @AndroidFindBy(id = "com.thecarousell.Carousell:id/tv_title")
    public List<MobileElement> categoriesList;

}
