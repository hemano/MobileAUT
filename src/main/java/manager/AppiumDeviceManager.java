package manager;

import android.AndroidDeviceConfiguration;
import entities.MobilePlatform;

import java.io.IOException;

/**
 * AppiumDeviceManager - Handles all devices information
 * For e.g. - Model, ID, UDID etc.
 */
public class AppiumDeviceManager {

    private static ThreadLocal<String> deviceUDID = new ThreadLocal<>();
    private AndroidDeviceConfiguration androidDeviceConfiguration;


    public AppiumDeviceManager() {
        androidDeviceConfiguration = new AndroidDeviceConfiguration();
    }

    public static String getDeviceUDID() {
        return deviceUDID.get();
    }

    protected static void setDeviceUDID(String UDID) {
        deviceUDID.set(UDID);
    }

    public static MobilePlatform getMobilePlatform() {
        return MobilePlatform.ANDROID;
    }

    public String getDeviceModel() throws InterruptedException, IOException {
        if (getMobilePlatform().equals(MobilePlatform.ANDROID)) {
            return androidDeviceConfiguration.getDeviceModel();
        }
        throw new IllegalArgumentException("DeviceModel is Empty");
    }

    public String getDeviceVersion() {
        if (getMobilePlatform().equals(MobilePlatform.ANDROID)) {
            return androidDeviceConfiguration.getDeviceOS();
        }
        throw new IllegalArgumentException("DeviceVersion is Empty");
    }

    public String getDeviceCategory() throws Exception {
        return androidDeviceConfiguration.getDeviceModel();

    }

}
