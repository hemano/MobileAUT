package manager;

import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.testng.TestNG;
import org.testng.collections.Lists;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class TestExecutor {

    private final ConfigFileManager propertiesManager;
    private final DeviceAllocationManager deviceAllocationManager;
    public List<Class> testCases = new ArrayList<>();
    private List<Class> listeners = new ArrayList<>();
    public ArrayList<String> items = new ArrayList<String>();

    public TestExecutor() throws Exception {
        deviceAllocationManager = DeviceAllocationManager.getInstance();
        propertiesManager = ConfigFileManager.getInstance();
    }


    public boolean runMethodParallelAppium(List<String> test, String pack, int deviceCount) throws MalformedURLException {

        boolean hasFailure;
        URL newURL = null;
        List<URL> newURLs = new ArrayList<>();
        Collections.addAll(items, pack.split("\\s*,\\s"));
        int a = 0;
        Collection<URL> urls = ClasspathHelper.forPackage(items.get(a));
        Iterator<URL> iter = urls.iterator();
        URL url = iter.next();
        urls.clear();

        for (int i = 0; i < items.size(); i++) {
            newURL = new URL(url.toString() + items.get(i).replaceAll("\\.", "/"));
            newURLs.add(newURL);
            a++;
        }

        Reflections reflections = new Reflections(new ConfigurationBuilder().setUrls(newURL)
                .setScanners(new MethodAnnotationsScanner()));
        Set<Method> resources = reflections.getMethodsAnnotatedWith(org.testng.annotations.Test.class);

        constructXmlSuiteForParallelMethods(pack, test, createTestsMap(resources),
                deviceCount, deviceAllocationManager.getDevices());

        hasFailure = runMethodParallel();

        System.out.println("Finally complete");
        ParallelExecution.figlet("Test Completed");
        //ImageUtils.creatResultsSet();
        //ImageUtils.createJSonForHtml();
        return hasFailure;
    }

    public boolean runMethodParallel() {
        TestNG testNG = new TestNG();
        List<String> suites = Lists.newArrayList();
        suites.add(System.getProperty("user.dir") + "/target/parallel.xml");
        testNG.setTestSuites(suites);
        testNG.run();
        return testNG.hasFailure();
    }

    public Map<String, List<Method>> createTestsMap(Set<Method> methods) {
        Map<String, List<Method>> testsMap = new HashMap<>();
        methods.stream().forEach(m -> {
            List<Method> methodsList = testsMap.get(
                    m.getDeclaringClass().getPackage().getName()
                            + "." + m.getDeclaringClass().getSimpleName());
            if (methodsList == null) {
                methodsList = new ArrayList<>();
                testsMap.put(m.getDeclaringClass().getPackage().getName()
                        + "." + m.getDeclaringClass().getSimpleName(), methodsList);
            }
            methodsList.add(m);
        });
        return testsMap;
    }


    public XmlSuite constructXmlSuiteForParallelMethods(String pack, List<String> testcases,
                                                        Map<String, List<Method>> methods,
                                                        int deviceCount, ArrayList<String> deviceArray) {

        List<String> listeners = new ArrayList<>();
        listeners.add("manager.AppiumTestListener");

        XmlSuite suite = new XmlSuite();
        suite.setName("TestNG Suite");
        suite.setThreadCount(deviceCount);
        suite.setDataProviderThreadCount(deviceCount);
        suite.setParallel(XmlSuite.ParallelMode.TESTS);
        suite.setVerbose(2);
        suite.setListeners(listeners);

        for (int i = 0; i < deviceCount; i++) {
            XmlTest test = new XmlTest(suite);
            test.setName("TestNG Test" + i);
            test.addParameter("device", deviceArray.get(i));
            List<XmlClass> xmlClasses = new ArrayList<>();
            writeXmlClass(testcases, methods, xmlClasses);
            test.setXmlClasses(xmlClasses);
        }
        System.out.println(suite.toXml());
        writeTestNGFile(suite);
        return suite;
    }

    private void writeTestNGFile(XmlSuite suite) {
        try {
            FileWriter writer = new FileWriter(new File(
                    System.getProperty("user.dir") + "/target/parallel.xml"));
            writer.write(suite.toXml());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<XmlClass> writeXmlClass(List<String> testcases, Map<String,
            List<Method>> methods, List<XmlClass> xmlClasses) {

        for (String className : methods.keySet()) {
            if (className.contains("Test")) {
                if (testcases.size() == 0) {
                    xmlClasses.add(createClass(className));
                } else {
                    for (String s : testcases) {
                        for (int itemIndex = 0; itemIndex < items.size(); itemIndex++) {
                            String testName = items.get(itemIndex).concat("." + s).toString();
                            if (testName.equals(className)) {
                                xmlClasses.add(createClass(className));
                            }
                        }
                    }
                }
            }
        }
        return xmlClasses;
    }

    private XmlClass createClass(String className) {
        XmlClass clazz = new XmlClass();
        clazz.setName(className);
        //clazz.setIncludedMethods(constructIncludes(methods));
        return clazz;
    }
}
