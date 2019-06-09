package com.webtest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ReadProperties {

    public static String readConfigurationProperty(String propertyName)
    {
        InputStream fileInputStream = null;
        java.util.Properties properties = new java.util.Properties();
        try {
            fileInputStream = new FileInputStream("src/main/resources/config.properties");
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
     return   properties.getProperty(propertyName);
    }
}
