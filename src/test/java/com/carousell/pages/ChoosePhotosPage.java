package com.carousell.pages;

import com.carousell.page.objects.ChoosePhotosPageObjects;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class ChoosePhotosPage extends CommonPageActions {

    ChoosePhotosPageObjects choosePhotosPageObjects = new ChoosePhotosPageObjects();

    public ChoosePhotosPage(AppiumDriver<MobileElement> driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), choosePhotosPageObjects);
    }


    public CameraPage openCamera() {
        waitForPageToLoad(choosePhotosPageObjects.openCameraBtn).click();
        return new CameraPage(driver);
    }

    public ChoosePhotosPage selectPhoto() {
        waitForListToLoad(choosePhotosPageObjects.picturesList);
        waitForPageToLoad(choosePhotosPageObjects.picturesList.get(0)).click();
        return this;
    }


    public ChoosePhotosPage openCameraAndTakePhoto(boolean takeNewPhoto) {
        if(takeNewPhoto){
            openCamera()
                    .capturePhoto();
        }

        return this;
    }

    public boolean anyPhotoAvailable() {
        return (choosePhotosPageObjects.picturesList.size() > 0);
    }


    public ChooseCategoryPage clickNext() {
        choosePhotosPageObjects.nextBtn.click();
        return new ChooseCategoryPage(driver);
    }
}
