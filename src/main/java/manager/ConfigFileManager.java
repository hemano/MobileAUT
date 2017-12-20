package manager;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * ConfigFileManager  - reads the config.properties file into configFileMap
 */
public class ConfigFileManager {

    public static Map<String, String> configFileMap = new HashMap<>();
    private static Properties properties = new Properties();
    private static ConfigFileManager instance;

    private ConfigFileManager(String configFile) throws IOException {
        FileInputStream inputStream = new FileInputStream(configFile);
        properties.load(inputStream);
    }

    public static ConfigFileManager getInstance(){

        if(instance == null){
            String configFile = "config.properties";
            try{
                instance = new ConfigFileManager(configFile);
                Enumeration keys = properties.propertyNames();
                while (keys.hasMoreElements()){
                    String key = keys.nextElement().toString();
                    configFileMap.put(key, properties.getProperty(key));
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return instance;
    }

    public String getProperty(String key){
        return configFileMap.get(key);
    }

}
