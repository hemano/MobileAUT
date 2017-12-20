package utils;

import entities.MobilePlatform;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import manager.AppiumDeviceManager;
import manager.DeviceAllocationManager;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Optional;

public class DesiredCapabilityBuilder {

    private AvailablePorts availablePorts;

    public static ThreadLocal<DesiredCapabilities> desiredCapabilitiesThreadLocal
            = new ThreadLocal<>();

    public DesiredCapabilityBuilder() {
        this.availablePorts = new AvailablePorts();
    }

    public static DesiredCapabilities getDesiredCapability() {
        return desiredCapabilitiesThreadLocal.get();
    }

    public void buildDesiredCapability(String platform, String jsonPath) throws Exception {

        Object port = ((HashMap) DeviceAllocationManager.getInstance()
                .deviceMapping.get(AppiumDeviceManager.getDeviceUDID())).get("port");
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        JSONArray jsonParsedObject = new JsonParser(jsonPath).getJsonParsedObject();


        Object platformObject = jsonParsedObject.stream().filter(k -> ((JSONObject) k).get(platform) != null).findFirst();

        Object platformCapabilities = ((JSONObject) ((Optional) platformObject).get()).get(platform);

        ((JSONObject) platformCapabilities)
                .forEach((caps, values) -> {
                            if ("app".equals(caps)) {

                                Path path = FileSystems.getDefault().getPath(values.toString());
                                if (!path.getParent().isAbsolute()) {
                                    desiredCapabilities.setCapability(caps.toString(), path.normalize()
                                            .toAbsolutePath().toString());
                                } else {
                                    desiredCapabilities.setCapability(caps.toString(), path.toString());
                                }
                            } else {
                                desiredCapabilities.setCapability(caps.toString(), values.toString());
                            }
                        }
                );

        if (AppiumDeviceManager.getMobilePlatform().equals(MobilePlatform.ANDROID)) {
            if (desiredCapabilities.getCapability("automationName") == null
                    || desiredCapabilities.getCapability("automationName").toString() != "UIAUtomator2") {
                desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,
                        AutomationName.ANDROID_UIAUTOMATOR2);
                desiredCapabilities.setCapability(AndroidMobileCapabilityType.SYSTEM_PORT,
                        Integer.parseInt(port.toString()));
            }
            appPackage(desiredCapabilities);
        }

        desiredCapabilities.setCapability(MobileCapabilityType.UDID,
                AppiumDeviceManager.getDeviceUDID());
        desiredCapabilitiesThreadLocal.set(desiredCapabilities);
    }

    public void appPackage(DesiredCapabilities desiredCapabilities){
        if(System.getenv("APP_PACKAGE") !=null){
            desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, System.getenv("APP_PACKAGE"));
        }
    }
}
