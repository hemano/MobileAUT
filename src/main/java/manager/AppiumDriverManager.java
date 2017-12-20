package manager;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import utils.DesiredCapabilityBuilder;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

public class AppiumDriverManager {

    private static final Logger LOGGER = Logger.getLogger(Class.class.getName());
    private static ThreadLocal<AppiumDriver> appiumDriverThreadLocal
            = new ThreadLocal<>();
    private AppiumServerManager appiumServerManager;
    private DesiredCapabilityBuilder desiredCapabilityBuilder;
    private ConfigFileManager propertiesManager;


    public AppiumDriverManager(){
        appiumServerManager = new AppiumServerManager();
        desiredCapabilityBuilder = new DesiredCapabilityBuilder();
        propertiesManager = ConfigFileManager.getInstance();
    }

    public static AppiumDriver getDriver(){ return appiumDriverThreadLocal.get(); }

    public static void setDriver(AppiumDriver driver){ appiumDriverThreadLocal.set(driver);}

    private AppiumDriver<MobileElement> getAndroidMobileElementAppiumDriver(
            Optional<DesiredCapabilities> androidCaps){

        AppiumDriver<MobileElement> currentDriverSession;
        DesiredCapabilities desiredCapabilities = androidCaps.get();

        currentDriverSession = new AndroidDriver<>(appiumServerManager.getAppiumUrl(),
                desiredCapabilities);

        LOGGER.info("Session Created ----"
                    + currentDriverSession.getSessionId()
                    + "---" + currentDriverSession.getSessionDetail("udid"));

        return currentDriverSession;
    }

    public void startAppiumDriverInstance(Optional<DesiredCapabilities> androidCaps){
                AppiumDriver<MobileElement> currentDriverSession;

                currentDriverSession = getAndroidMobileElementAppiumDriver(androidCaps);
                AppiumDriverManager.setDriver(currentDriverSession);
    }

    public void startAppiumDriverInstance() throws Exception {
        String userSpecifiedCaps;
        DesiredCapabilities android = null;

        userSpecifiedCaps = System.getProperty("user.dir")
                + "/caps/capabilities.json";

        android = getDesiredAndroidCapabilities(userSpecifiedCaps);
        LOGGER.info("Caps generated---" + android);
        startAppiumDriverInstance(Optional.ofNullable(android));
    }

    public DesiredCapabilities getDesiredAndroidCapabilities(String userSpecifiedAndroidCaps) throws Exception {
        if(new File(userSpecifiedAndroidCaps).exists()){
            String androidJsonFilePath = userSpecifiedAndroidCaps;
            Path path = FileSystems.getDefault().getPath(androidJsonFilePath.toString());
            if (!path.getParent().isAbsolute()) {
                androidJsonFilePath = path.normalize()
                        .toAbsolutePath().toString();
            }
            desiredCapabilityBuilder
                    .buildDesiredCapability("android", androidJsonFilePath);
            DesiredCapabilities android = DesiredCapabilityBuilder.getDesiredCapability();
            return android;
        }else {
            System.out.println("Capability file not found");
            return null;
        }
    }

    public void stopAppiumDriver(){
        if (AppiumDriverManager.getDriver() != null
                && AppiumDriverManager.getDriver().getSessionId() != null) {
            LOGGER.info("Session Deleting ---- "
                    + AppiumDriverManager.getDriver().getSessionId() + "---"
                    + AppiumDriverManager.getDriver().getSessionDetail("udid"));
            AppiumDriverManager.getDriver().quit();
        }
    }



}
