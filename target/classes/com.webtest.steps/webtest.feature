Feature: Web test

  Scenario: Web Test url launch
    Given web test url
    When url is launched on chrome browser
    Then web test site url is opened

  Scenario: Validate if application page is on global site
    Given application url is launched in chrome browser
    When application website language is not Global | English
    Then change language to Global | English

  Scenario: Search for a text
    Given application url is launched in chrome browser
    When text is searched
    Then Results page is displayed

  Scenario: Sort results by date on search page
    Given application url is launched in chrome browser
    When text is searched
    Then validate if results are sorted by date

  Scenario: capture titles of all articles
    Given application url is launched in chrome browser
    When text is searched
    And results are filtered by Transportation
    Then capture all titles of the articles in a list

