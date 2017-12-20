package com.carousell.pages;

import com.carousell.page.objects.CameraPageObjects;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class CameraPage extends CommonPageActions {

    CameraPageObjects cameraPageObjects = new CameraPageObjects();

    public CameraPage(AppiumDriver<MobileElement> driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), cameraPageObjects);
    }


    public ChoosePhotosPage  capturePhoto(){
        waitForPageToLoad(cameraPageObjects.capturePhotoBtn).click();
        return new ChoosePhotosPage(driver);
    }
}
