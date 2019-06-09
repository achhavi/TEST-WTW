package com.webtest;

public class Configurations {

    public static String fetchUrl() {
        return ReadProperties.readConfigurationProperty("url");
    }

}
