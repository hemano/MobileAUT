package com.carousell.test;

import com.carousell.pages.ChoosePhotosPage;
import com.carousell.pages.LandingPage;
import com.github.javafaker.Faker;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;

public class CarousellTest extends BaseClass {


    @Test
    public void testMethodOne() {

        LandingPage landingPage = new LandingPage(getDriver());

        landingPage
                .clickLoginBtn()
                .loginWithCredentials("carouselltest2018@gmail.com", "carousell2018")
                .clickSell();


        ChoosePhotosPage choosePhotosPage = new ChoosePhotosPage(getDriver());

        Faker faker = new Faker();
        ThreadLocal<String> listingName = new ThreadLocal<>();
        listingName.set(faker.lorem().fixedString(6));


        boolean isItemListed =
                choosePhotosPage
                .openCameraAndTakePhoto(true)
                .selectPhoto()
                .clickNext()
                .chooseCategory("Everything Else")
                .chooseSubCategory("Everything Else - Others")
                .enterTitleAndPrice(listingName.get(), "15")
                .cancelPromotion()
                .clickBrowseTab()
                .selectCategoryByText("Everything Else")
                .clickSortOrFilterTab()
                .selectSortByRadioByText("Recent")
                .clickSeeFilteredResultsBtn()
                .isItemListed(listingName.get());


        assertThat(isItemListed, Matchers.is(true));


    }
}
