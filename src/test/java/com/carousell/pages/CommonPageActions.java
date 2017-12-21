package com.carousell.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.functions.ExpectedCondition;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.internal.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class CommonPageActions {
    private static final Logger LOGGER = Logger.getLogger(CommonPageActions.class);
    public AppiumDriver driver;
    private static final String ELEMENT_DISAPPEAR_TIMEOUT = "15";
    private static final String ELEMENT_APPEAR_TIMEOUT = "30";

    public CommonPageActions(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
    }

    public MobileElement waitForPageToLoad(WebElement id) {
        WebDriverWait wait = new WebDriverWait(driver, Long.valueOf(ELEMENT_APPEAR_TIMEOUT));
        return (MobileElement) wait
                .ignoring(Exception.class)
                .pollingEvery(100, TimeUnit.MILLISECONDS)
                .until(ExpectedConditions.visibilityOf(id));
    }

    public boolean waitForListToLoad(List<MobileElement> list){
         return new WebDriverWait(driver, 10)
                .until(new ExpectedCondition<Boolean>() {
                    @Override
                    public Boolean apply(@Nullable WebDriver webDriver) {
                        return list.size() > 1;
                    }
                });
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

    public boolean moveToElement(MobileElement element) {
        int limit = 0;
        while (!isExist(element).isPresent() && limit < 5) {
            limit++;
            new TouchAction((MobileDriver) driver).longPress(0, 0).moveTo(0, 100).release().perform();
        }
        if (limit == 5) {
            return false;
        } else {
            return true;
        }
    }


    public void scrollDown(int swipes) {
        int limit = 0;
        while (limit < swipes) {
            limit++;
            new TouchAction((MobileDriver) driver).longPress(0, 0).moveTo(0, 100).release().perform();
        }
    }

    public Optional<MobileElement> isExist(MobileElement element) {
        try {
            element.isDisplayed();
            return Optional.of(element);
        } catch (NoSuchElementException e) {
            return Optional.empty();
        }
    }



}
