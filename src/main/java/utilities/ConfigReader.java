package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;
    private final static String CONFIG_FILE_PATH = "src/test/resources/config/testdata.properties";

    static {
        try {
            FileInputStream fis = new FileInputStream(CONFIG_FILE_PATH);
            properties = new Properties();
            properties.load(fis);
            fis.close();
        } catch (IOException e) {
            // Print a more informative error message if the file is not found or cannot be read
            System.err.println("Error loading properties file: " + CONFIG_FILE_PATH);
            e.printStackTrace();
            throw new RuntimeException("Failed to load properties file: " + CONFIG_FILE_PATH, e);
        }
    }

    public static String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Property '" + key + "' not found in " + CONFIG_FILE_PATH);
        }
        return value;
    }
}