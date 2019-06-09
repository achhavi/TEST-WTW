package com.webtest.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.webtest.Configurations.fetchUrl;
import static com.webtest.ReadProperties.readConfigurationProperty;
import static java.util.concurrent.TimeUnit.SECONDS;

public class HomePage {

    static By countrySelector = By.xpath(readConfigurationProperty("countrySelector"));
    static By agreeAndProceedButton = By.xpath(readConfigurationProperty("cookieAgreeAndProceed"));
    static By cookieFrame = By.xpath(readConfigurationProperty("cookieIframe"));
    static By siteLanguages = By.xpath(readConfigurationProperty("languages"));
    static By searchText = By.xpath(readConfigurationProperty("searchTextBox"));

    static WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public String captureWebSiteLanguage() {
        return driver.findElement(countrySelector).getText();
    }

    public void launchBrowser() {
        driver.get(fetchUrl());
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3, SECONDS);
    }

    public void clickAgreeAndProceedOnCookies() {
        WebElement iframe = driver.findElement(cookieFrame);
        driver.switchTo().frame(iframe);
        driver.findElement(agreeAndProceedButton).click();
        driver.manage().timeouts().pageLoadTimeout(3, SECONDS);
        driver.switchTo().defaultContent();
    }

    public void selectSiteLanguage(String language) {
        List<WebElement> siteLanguageList = driver.findElements(siteLanguages);
        for (WebElement list : siteLanguageList) {
            if (list.getText().equals(language)) {
                list.click();
                break;
            }
        }
    }

    public void clickChangeWebSite() {
        driver.manage().timeouts().pageLoadTimeout(3000, TimeUnit.SECONDS);
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(countrySelector)).click().perform();
    }

    public String getUrl() {
        return driver.getCurrentUrl();
    }

    public void searchText(String text) {
        driver.findElement(searchText).sendKeys(text);
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
        } catch (AWTException e) {
            e.printStackTrace();
        }


    }
}
