package manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class DeviceManager implements Manager {

    public DeviceManager() {
    }

    @Override
    public Device getDeviceProperties(String udid) throws Exception {
        Optional<Device> device = (new AndroidManager()).getDeviceProperties().stream().filter((d) -> {
            return udid.equals(d.getUdid());
        }).findFirst();

        Optional<Device> finalDeviceList = Optional.of(device).orElseThrow(() -> {
            return new RuntimeException("No Results found");

        });
        return  (Device)finalDeviceList.get();
    }


    public List<Device> getDeviceProperties() throws Exception {
        List<Device> allDevice = new ArrayList();
        List<Device> androidDevice = (new AndroidManager()).getDeviceProperties();
        Stream.of(androidDevice).forEach(allDevice::addAll);
        return allDevice;
    }
}
