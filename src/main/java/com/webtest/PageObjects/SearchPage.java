package com.webtest.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.webtest.ReadProperties.readConfigurationProperty;

public class SearchPage {

    private static By searchTextDisplay = By.xpath(readConfigurationProperty("searchText"));
    private static By sortByRelevance = By.xpath(readConfigurationProperty("sortByRelevance"));
    private static By sortByDate = By.xpath(readConfigurationProperty("sortByDate"));
    private static By sortDatesList = By.xpath(readConfigurationProperty("sortDatesList"));
    private static By refineFilters = By.xpath(readConfigurationProperty("refineFilters"));
    private static By articleTitles = By.xpath(readConfigurationProperty("articleTitle"));

    private WebDriver driver;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public String displayResultsPage() {
        driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return driver.findElement(searchTextDisplay).getText();

    }

    public void sortType() {
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        String attributeValue = driver.findElement(sortByRelevance).getAttribute("class");
        if (attributeValue.equals(readConfigurationProperty("sortSelected"))) {
            driver.findElement(sortByDate).click();
        }
    }

    public List captureListOfResultsDate() {
        return driver.findElements(sortDatesList);
    }

    public void selectRefineSearch(String searchFilterName) {
        driver.manage().timeouts().pageLoadTimeout(3000, TimeUnit.SECONDS);
        List<WebElement> refineBy = driver.findElements(refineFilters);
        for (WebElement refine : refineBy) {
            if (refine.getText().equals(searchFilterName)) {
                refine.click();
                break;
            }
        }
    }

    public List captureArticleTitles() {
        List<String> articleTitlesList = new ArrayList<String>();

        List<WebElement> articleTitleElements = driver.findElements(articleTitles);
        for (int i = 0; i < articleTitleElements.size(); i++) {
            articleTitlesList.add(articleTitleElements.get(i).getText());
        }

        return articleTitlesList;
    }

}
