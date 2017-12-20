package manager;

import org.testng.*;

import java.io.IOException;

public class AppiumTestListener
        implements IClassListener, IInvokedMethodListener, ISuiteListener, ITestListener{

    public AppiumServerManager appiumServerManager;
    private DeviceAllocationManager deviceAllocationManager;
    private AppiumDriverManager appiumDriverManager;


    public AppiumTestListener() throws Exception {
        try {
            this.appiumServerManager = new AppiumServerManager();
            deviceAllocationManager = DeviceAllocationManager.getInstance();
            appiumDriverManager = new AppiumDriverManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void beforeInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {

    }

    public void afterInvocation(IInvokedMethod method, ITestResult testResult){
        if (method.isTestMethod()) {
            appiumDriverManager.stopAppiumDriver();
        }
    }


    public void onStart(ISuite iSuite){
        try {
            appiumServerManager.startAppiumServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onFinish(ISuite iSuite){
        appiumServerManager.stopAppiumServer();
    }


    @Override
    public void onBeforeClass(ITestClass iTestClass) {
        String device = iTestClass.getXmlClass().getAllParameters().get("device").toString();
        deviceAllocationManager.allocateDevice(device,
                deviceAllocationManager.getNextAvailableDeviceId());
    }

    @Override
    public void onAfterClass(ITestClass iTestClass) {
        deviceAllocationManager.freeDevice();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        try {
            appiumDriverManager.startAppiumDriverInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {

    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("Test Skipped...");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }
}
