package com.carousell.test;

import com.carousell.pages.ChoosePhotosPage;
import com.carousell.pages.LandingPage;
import org.testng.annotations.Test;

public class CarousellTest extends BaseClass {


    @Test
    public void testMethodOne() {

        LandingPage landingPage = new LandingPage(getDriver());

        landingPage
                .clickLoginBtn()
                .loginWithCredentials("carouselltest2018@gmail.com", "carousell2018")
                .clickSell();


        ChoosePhotosPage choosePhotosPage = new ChoosePhotosPage(getDriver());

        choosePhotosPage
                .openCameraAndTakePhoto(
                        choosePhotosPage.anyPhotoAvailable())
                .selectPhoto()
                .clickNext()
                .chooseCategory("Everything Else")
                .chooseSubCategory("Everything Else - Others");

    }
}
