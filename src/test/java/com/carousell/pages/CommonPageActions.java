package com.carousell.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class CommonPageActions {
    private static final Logger LOGGER = Logger.getLogger(CommonPageActions.class);
    public AppiumDriver driver;
    private static final String ELEMENT_DISAPPEAR_TIMEOUT = "15";
    private static final String ELEMENT_APPEAR_TIMEOUT = "15";

    public CommonPageActions(AppiumDriver<MobileElement> driver) { this.driver = driver; }

    public MobileElement waitForPageToLoad(WebElement id){
        WebDriverWait wait = new WebDriverWait(driver, Long.valueOf(ELEMENT_APPEAR_TIMEOUT ));
        return (MobileElement) wait
                .ignoring(Exception.class)
                .pollingEvery(2, TimeUnit.SECONDS)
                .until(ExpectedConditions.elementToBeClickable(id) );
    }


    public void waitForElementToDisAppear(String id) {
        WebDriverWait wait = new WebDriverWait(driver, Long.valueOf(ELEMENT_DISAPPEAR_TIMEOUT));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(id)));
    }

    public WebElement waitForElement(WebElement id) {
        waitForPageToLoad(id);
        WebElement el = id;
        return el;
    }

    public boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * method to set the context to required view.
     *
     * @param context view to be set
     */
    public void setContext(String context) {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Set<String> contextNames = driver.getContextHandles();
        for (String contextName : contextNames) {
            System.out.println(contextName); //prints out something like NATIVE_APP \n WEBVIEW_1
        }
        driver.context((String) contextNames.toArray()[1]); // set context to WEBVIEW_1

        LOGGER.info("Current context" + driver.getContext());
    }

    public void clickBackButton() {
        driver.navigate().back(); //Closes keyboard
        //driver.navigate().back(); //Comes out of edit mode
    }
}
