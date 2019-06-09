package com.webtest.steps;

import com.webtest.PageObjects.HomePage;
import com.webtest.PageObjects.SearchPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.webtest.Configurations.fetchUrl;
import static com.webtest.InitializeDrivers.initializeBrowser;
import static com.webtest.ReadProperties.readConfigurationProperty;

public class StepDefinitions {
    HomePage newHomePage;
    SearchPage searchPage;

    String url;
    String countryLanguage;

    WebDriver driver;


    @Given("^web test url$")
    public void fetchApplicationUrl() {
        url = fetchUrl();
    }

    @When("^url is launched on (.*) browser$")
    public void openWebSiteUrl(String browser) {
        driver = initializeBrowser(browser);
        newHomePage = new HomePage(driver);
        newHomePage.launchBrowser();
        newHomePage.clickAgreeAndProceedOnCookies();

    }

    @Then("^web test site url is opened$")
    public void launchBrowser() {
        String actualUrl = newHomePage.getUrl();
        Assert.assertFalse("Failed as current Url is: " + actualUrl, actualUrl.equals(url));
    }

    @Given("^application url is launched in (.*) browser$")
    public void launchBroswer(String browser) {
        openWebSiteUrl(browser);
    }

    @When("^application website language is not (.*)$")
    public void captureApplicationWebsiteLanguage(String siteLanguage) {
        countryLanguage = newHomePage.captureWebSiteLanguage();
        String configLang = readConfigurationProperty("siteLanguage");
        if (!countryLanguage.equals(configLang)) {
            newHomePage.clickChangeWebSite();
            newHomePage.selectSiteLanguage(siteLanguage);
        }
    }

    @Then("^change language to (.*)$")
    public void changeSiteLanguage(String expectedSiteLanguage) {
        String actualLanguage = newHomePage.captureWebSiteLanguage();
        Assert.assertFalse("Failed: actualLanguage: " + actualLanguage, countryLanguage.equals(expectedSiteLanguage));

    }

    @When("^(.*) is searched$")
    public void textSearch(String text) {
        newHomePage.searchText(text);
    }

    @Then("^Results page is displayed$")
    public void resultsPageIsDisplayed() {
        searchPage = new SearchPage(driver);
        String actualSearchText = searchPage.displayResultsPage();
        Assert.assertEquals("Navigation to Search Page Failed", "Search", actualSearchText.trim());
    }

    @Then("^validate if results are sorted by date$")
    public void resultAreSortedByDate() {
        searchPage = new SearchPage(driver);
        searchPage.sortType();
        List<WebElement> sortDateList = searchPage.captureListOfResultsDate();
        Assert.assertNotEquals("Results are not sorted by date: ", 0, sortDateList.size());
    }

    @Given("^results are filtered by (.*)")
    public void resultsAreFilteredByTransportation(String refineFilter) {
        searchPage = new SearchPage(driver);
        searchPage.selectRefineSearch(refineFilter);
    }

    @Then("^capture all titles of the articles in a list$")
    public void captureAllArticleTitles() {
        List articleTitles = searchPage.captureArticleTitles();
        for (int i = 0; i < articleTitles.size(); i++) {
            System.out.println(articleTitles.get(i));
        }
        Assert.assertNotEquals("Article titles are not captured", 0, articleTitles.size());
    }
}
