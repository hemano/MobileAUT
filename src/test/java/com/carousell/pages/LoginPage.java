package com.carousell.pages;

import com.carousell.page.objects.LoginPageObjects;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends CommonPageActions {

    LoginPageObjects loginPageObjects= new LoginPageObjects();

    public LoginPage(AppiumDriver<MobileElement> driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), loginPageObjects);
    }


    public HomePage loginWithCredentials(String userName, String password){
        waitForPageToLoad(loginPageObjects.userNameText).setValue(userName);
        loginPageObjects.passwordText.setValue(password);
        loginPageObjects.loginBtn.click();
        return new HomePage(driver);
    }
}
