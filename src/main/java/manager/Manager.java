package manager;

public interface Manager {

    Device getDeviceProperties(String udid) throws Exception;
}
