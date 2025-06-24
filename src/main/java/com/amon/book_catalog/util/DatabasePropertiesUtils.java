package com.amon.book_catalog.util;

// Global libraries
import java.io.*;
import java.util.Properties;

public class DatabasePropertiesUtils {

    private static final String PROPERTIES_FILE = "db-config.properties";

    // Load database properties
    public static Properties loadDatabaseProperties() {
        Properties properties = new Properties();
        try (InputStream inputStream = new FileInputStream(PROPERTIES_FILE)){
            properties.load(inputStream);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return properties;
    }

    // Get a property and decrypt if the key is password
    public static String getProperty(String key) {
        Properties properties = loadDatabaseProperties();
        String value = properties.getProperty(key);
        if ("spring.datasource.password".equals(key) && value != null) {
            return CryptoUtil.decrypt(value);
        }
        return value;
    }

    // Set a property and encrypt if key is password
    public static void setProperty(String key, String value) {
        Properties properties = loadDatabaseProperties();
        if ("spring.datasource.password".equals(key)) {
            value = CryptoUtil.encrypt(value);
        }
        properties.setProperty(key, value);
        try (OutputStream outputStream = new FileOutputStream(PROPERTIES_FILE)){
            properties.store(outputStream, "Updated " + key);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    // Print current database configuration
    public static void printDababaseConfig() {
        Properties properties = loadDatabaseProperties();
        System.out.println("DB URL: " + properties.getProperty("spring.datasource.url"));
        System.out.println("DB Username: " + properties.getProperty("spring.datasource.username"));
    }
}