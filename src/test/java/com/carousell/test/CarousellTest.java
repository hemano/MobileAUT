package com.carousell.test;

import com.carousell.pages.LandingPage;
import org.testng.annotations.Test;

public class CarousellTest extends UserBaseTest{


    @Test
    public void testMethodOne(){

        LandingPage landingPage = new LandingPage(getDriver());

        landingPage
                .clickLoginBtn()
                .loginWithCredentials("hemantojhaa@gmail.com", "12345678")
                .clickSell();



    }
}
