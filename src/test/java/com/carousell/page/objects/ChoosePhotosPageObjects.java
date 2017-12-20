package com.carousell.page.objects;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.support.CacheLookup;

import java.util.List;

public class ChoosePhotosPageObjects {

    @CacheLookup
    @AndroidFindBy(id = "com.thecarousell.Carousell:id/view_open_camera")
    public MobileElement openCameraBtn;

    @CacheLookup
    @AndroidFindBy(id = "com.thecarousell.Carousell:id/list_pictures")
    public MobileElement listPicturesBtn;

    @AndroidFindBy(id = "com.thecarousell.Carousell:id/pic_thumbnail")
    public List<MobileElement> picturesList;

    @CacheLookup
    @AndroidFindBy(id = "com.thecarousell.Carousell:id/action_done")
    public MobileElement nextBtn;

}
