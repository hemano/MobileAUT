package com.carousell.page.objects;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.support.CacheLookup;

public class LoginPageObjects {

    @CacheLookup
    @AndroidFindBy(id = "com.thecarousell.Carousell:id/layout_username")
    @AndroidFindBy(xpath = ".//TextInputLayout[@resource-id='com.thecarousell.Carousell:id/layout_username']")
    public MobileElement userNameText;

    @CacheLookup
    @AndroidFindBy(id = "com.thecarousell.Carousell:id/layout_password")
    public MobileElement passwordText;

    @CacheLookup
    @AndroidFindBy(id = "com.thecarousell.Carousell:id/login_button")
    public MobileElement loginBtn;

}
