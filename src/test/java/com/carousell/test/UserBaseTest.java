package com.carousell.test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import manager.AppiumDriverManager;

public class UserBaseTest {

    public AppiumDriver<MobileElement> driver;


    public AppiumDriver<MobileElement> getDriver() {
        driver = AppiumDriverManager.getDriver();
        return driver;
    }
}