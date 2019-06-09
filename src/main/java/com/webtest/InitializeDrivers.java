package com.webtest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.webtest.ReadProperties.readConfigurationProperty;

public class InitializeDrivers {

    private static WebDriver driver;

    public static WebDriver initializeBrowser(String browser)
    {
        String chromeBrowser= readConfigurationProperty("chromebrowser");

        if(browser.equals(chromeBrowser))
        {
            System.setProperty("webdriver.chrome.driver", "src//main//resources//drivers//chromedriver.exe");
            driver = new ChromeDriver();
        }
        return driver;
    }


}
