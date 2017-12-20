package manager;


import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import utils.AvailablePorts;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Logger;

/**
 * AppiumServerManager - this class manages the server during the execution
 * It starts and stops the server
 * Mac settings: PATH should contain following
 * /usr/local/bin:/usr/bin:/bin:/usr/sbin:/sbin
 * For eclipse set above paths in run configuration
 */
public class AppiumServerManager {

    private static final Logger LOGGER = Logger.getLogger(Class.class.getSimpleName());
    private AvailablePorts availablePorts;
    private static AppiumDriverLocalService appiumDriverLocalService;

    private static AppiumDriverLocalService getAppiumDriverLocalService() {
        return appiumDriverLocalService;
    }

    public static void setAppiumDriverLocalService(
            AppiumDriverLocalService appiumDriverLocalService) {
        AppiumServerManager.appiumDriverLocalService = appiumDriverLocalService;
    }


    public AppiumServerManager() {
        availablePorts = new AvailablePorts();
    }

    private void startAppiumServerSingleSession() throws IOException {
        System.out.println(
                "**************************************************************************\n");
        System.out.println("Starting Appium Server......");
        System.out.println(
                "**************************************************************************\n");

        int port = availablePorts.getPort();
        AppiumDriverLocalService appiumDriverLocalService;

        AppiumServiceBuilder builder =
                new AppiumServiceBuilder()
                        .withAppiumJS(new File(ConfigFileManager.getInstance().getProperty("APPIUM_JS_PATH")))
                        .withArgument(GeneralServerFlag.LOG_LEVEL, "info")
                        .withLogFile(new File(System.getProperty("user.dir")
                                + "/target/appiumlogs.appium_logs.txt"))
                        .withIPAddress("127.0.0.1")
                        .usingPort(port);

        appiumDriverLocalService = builder.build();
        appiumDriverLocalService.start();
        System.out.println(
                "**************************************************************************\n");
        System.out.println("Appium Server Started at......"
                + appiumDriverLocalService.getUrl());
        System.out.println(
                "**************************************************************************\n");

        setAppiumDriverLocalService(appiumDriverLocalService);
    }

    public URL getAppiumUrl(){ return getAppiumDriverLocalService().getUrl(); }

    public void startAppiumServer() throws IOException {
        startAppiumServerSingleSession();
    }

    private void destroyAppiumNode(){
        getAppiumDriverLocalService().stop();

        if(getAppiumDriverLocalService().isRunning()){
            LOGGER.info("AppiumServer didn't shut... Trying to quit again....");
            getAppiumDriverLocalService().stop();
        }
        System.out.println(
                "**************************************************************************\n");
        System.out.println("Appium Server has stopped......");
        System.out.println(
                "**************************************************************************\n");


    }

    public void stopAppiumServer(){
        destroyAppiumNode();
        // TODO: 19/12/17 Delete all the devices
    }

}
