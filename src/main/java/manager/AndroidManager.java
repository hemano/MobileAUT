package manager;

import org.json.JSONObject;
import utils.CommandPromptUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AndroidManager implements Manager {

    private CommandPromptUtil promptUtil;
    private JSONObject adbDevices;

    public AndroidManager() {
        this.promptUtil = new CommandPromptUtil();
        adbDevices = new JSONObject();
    }


    /**
     * It will start the adb server
     */
    public void startADB() throws IOException {
        String output = promptUtil.runCommandThroughProcess("adb start-server");
        if(output != null){
            String[] lines = output.split("\n");
            if (lines[0].contains("internal or external command")) {
                System.out.println("Please set ANDROID_HOME in your system variables");
            }
        }
    }

    public JSONObject getDeviceInfo(String deviceID) throws IOException {
        String model =
                promptUtil.runCommandThroughProcess("adb -s " + deviceID
                        + " shell getprop ro.product.model");

        String brand =
                promptUtil.runCommandThroughProcess("adb -s " + deviceID
                        + " shell getprop ro.product.brand")
                        .replaceAll("\\s+", "");

        String osVersion =
                promptUtil.runCommandThroughProcess(
                        "adb -s " + deviceID + " shell getprop ro.build.version.release")
                        .replaceAll("\\s+", "");

        String deviceName = brand + " " + model;
        String apiLevel =
                promptUtil.runCommandThroughProcess("adb -s " + deviceID
                        + " shell getprop ro.build.version.sdk")
                        .replaceAll("\n", "");

        String deviceOrEmulator =
                promptUtil.runCommandThroughProcess("adb -s " +
                        deviceID + " shell getprop ro.product.manufacturer");

        String getScreenResolution =
                promptUtil.runCommandThroughProcess("adb -s " + deviceID
                        + " shell wm size").split(":")[1].replace("\n", "");

        boolean isDevice = true;
        if (deviceOrEmulator.contains("Genymotion")
                || deviceOrEmulator.contains("unknown")) {
            isDevice = false;
        }

        String deviceModel =
                promptUtil.runCommandThroughProcess("adb -s " + deviceID
                        + " shell getprop ro.product.model");

        adbDevices.put("name", deviceName);
        adbDevices.put("osVersion", osVersion);
        adbDevices.put("apiLevel", apiLevel);
        adbDevices.put("brand", brand);
        adbDevices.put("udid", deviceID);
        adbDevices.put("isDevice", isDevice);
        adbDevices.put("deviceModel", deviceModel);
        adbDevices.put("screenSize", getScreenResolution);
        return adbDevices;
    }

    public List<Device> getDeviceProperties() throws Exception {
        List<Device> device = new ArrayList<>();
        JSONObject adb = new JSONObject();
        startADB(); // start adb service
        String output = promptUtil.runCommandThroughProcess("adb devices");
        String[] lines = output.split("\n");

        if (lines.length <= 1) {
            //stopADB();
        } else {
            for (int i = 1; i < lines.length; i++) {
                lines[i] = lines[i].replaceAll("\\s+", "");

                if (lines[i].contains("device")) {
                    lines[i] = lines[i].replaceAll("device", "");
                    String deviceID = lines[i];
                    JSONObject deviceInfo = getDeviceInfo(deviceID);
                    device.add(new Device(deviceInfo));
                }
            }
        }
        return device;
    }

    @Override
    public Device getDeviceProperties(String udid) throws Exception {
        Optional<Device> device = getDeviceProperties().stream().filter(d ->
                udid.equals(d.getUdid())).findFirst();
        return device.orElseThrow(() ->
                new RuntimeException("Provided DeviceUDID " + udid
                        + " is not found on the machine")
        );
    }
}

