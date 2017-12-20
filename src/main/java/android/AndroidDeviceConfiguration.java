package android;

import manager.AppiumDeviceManager;
import manager.Device;
import manager.DeviceAllocationManager;
import utils.CommandPromptUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AndroidDeviceConfiguration {

    private CommandPromptUtil cmd = new CommandPromptUtil();
    public static List<String> validDeviceIds = new ArrayList<>();

    /*
    * This method gets the device model name
    */
    public String getDeviceModel() {
        Optional<Device> getModel = getDevice();
        return (getModel.get().getDeviceModel() + getModel.get().getBrand())
                .replaceAll("[^a-zA-Z0-9\\.\\-]", "");
    }

    /*
    * This method gets the device OS API Level
    */
    public String getDeviceOS() {
        Optional<Device> deviceOS = getDevice();
        return deviceOS.get().getOsVersion();
    }


    private Optional<Device> getDevice() {
        Optional<Device> deviceOS = null;
        try {
            deviceOS = DeviceAllocationManager.getInstance().deviceManager.stream().filter(device ->
                    device.getUdid().equals(AppiumDeviceManager.getDeviceUDID())).findFirst();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deviceOS;
    }


    public String getDeviceManufacturer()
            throws IOException, InterruptedException {
        return cmd.runCommandThroughProcess("adb -s " + AppiumDeviceManager.getDeviceUDID()
                + " shell getprop ro.product.manufacturer")
                .trim();
    }
}
