package manager;

import android.AndroidDeviceConfiguration;
import com.github.lalyos.jfiglet.FigletFont;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParallelExecution {

    private ConfigFileManager configFileManager;
    private DeviceAllocationManager deviceAllocationManager;
    Map<String, String> devices = new HashMap<>();
    private AndroidDeviceConfiguration androidDeviceConfiguration;
    TestExecutor testExecutor;


    public ParallelExecution() throws Exception {
        configFileManager = ConfigFileManager.getInstance();
        deviceAllocationManager = DeviceAllocationManager.getInstance();
        androidDeviceConfiguration = new AndroidDeviceConfiguration();
        testExecutor = new TestExecutor();
    }


    public boolean runner(String pack, List<String> tests) throws Exception {
        figlet("PARALLEL");
        return triggerTest(pack,tests);
    }


    private boolean triggerTest(String pack, List<String> tests) throws Exception {
        return parallelExecution(pack, tests);
    }

    private boolean parallelExecution(String pack, List<String> tests) throws Exception {
        String os = System.getProperty("os.name").toLowerCase();
        int deviceCount = deviceAllocationManager.getDevices().size();

        if(deviceCount == 0){
            figlet("No Devices Connected");
            System.exit(0);
        }

        figlet("Connected Devices: " + deviceCount);

        List<Class> testCases = new ArrayList<>();
        boolean hasFailures = false;

        PackageUtil.getClasses(pack).stream().forEach(t->{
            if(t.toString().contains("Test")){
                testCases.add((Class)t);
            }
        });

        hasFailures = testExecutor.runMethodParallelAppium(tests, pack, deviceCount);

        return hasFailures;
    }

    public static void figlet(String text) {
        String asciiArt1 = null;
        try {
            asciiArt1 = FigletFont.convertOneLine(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(asciiArt1);
    }
}
