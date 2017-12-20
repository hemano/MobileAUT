package manager;


import utils.AvailablePorts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Logger;

/**
 * DeviceAllocationManager - Manages the allocation and de-allocation of connected devices
 * and Android Emulators.
 */
public class DeviceAllocationManager {

    private static final Logger LOGGER = Logger.getLogger(Class.class.getName());
    public ConcurrentHashMap<String, Object> deviceMapping;
    private ArrayList<String> devices = new ArrayList<>();
    private static DeviceAllocationManager instance;
    private static AndroidManager androidManager;
    public List<Device> deviceManager;



    public DeviceAllocationManager() throws Exception {
        this.deviceMapping = new ConcurrentHashMap<>();
        deviceManager = new CopyOnWriteArrayList<>(new DeviceManager().getDeviceProperties());
        androidManager = new AndroidManager();

        initializeDevices();
    }

    public static DeviceAllocationManager getInstance() throws Exception {
        if (instance == null) {
            instance = new DeviceAllocationManager();
        }
        return instance;
    }

    private void initializeDevices() throws Exception {
        androidManager.getDeviceProperties()
                .forEach(device -> this.devices.add(device.getUdid()));

        for(String device: devices){
            HashMap<Object, Object> deviceState = new HashMap<>();
            deviceState.put("deviceState", true);
            deviceState.put("port", new AvailablePorts().getPort());
            deviceMapping.put(device, deviceState);
        }
    }


    public ArrayList<String> getDevices() {
        LOGGER.info("All devices connected" + devices);
        return devices;
    }


    public synchronized String getNextAvailableDeviceId() {
        ConcurrentHashMap.KeySetView<String, Object> devices = deviceMapping.keySet();
        int i = 0;
        for (String device : devices) {
            Thread t = Thread.currentThread();
            t.setName("Thread_" + i);
            i++;
            if (((HashMap) deviceMapping.get(device))
                    .get("deviceState").toString().equals("true")) {
                ((HashMap) deviceMapping.get(device)).put("deviceState", false);
                return device;
            }
        }
        return null;
    }

    public void freeDevice() {
        ((HashMap) deviceMapping.get(AppiumDeviceManager.getDeviceUDID())).put("deviceState", true);
        LOGGER.info("DeAllocated Device " + AppiumDeviceManager.getDeviceUDID()
                + " from execution list");
    }


    public void allocateDevice(String device, String deviceUDID) {
        if (device.isEmpty()) {
            LOGGER.info("Allocated Device " + deviceUDID + " for Execution");
            AppiumDeviceManager.setDeviceUDID(deviceUDID);
        } else {
            LOGGER.info("Allocated Device " + deviceUDID + " for Execution");
            AppiumDeviceManager.setDeviceUDID(device);
        }
    }

}

