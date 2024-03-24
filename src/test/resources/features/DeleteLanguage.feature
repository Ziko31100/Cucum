Feature: Delete Languages in HRMS

  Background:
    #Given user is navigated to HRMS application
    When user enters admin username and password
    And user clicks on login button
    Then user is successfully logged in
    When “Admin” navigates to Qualifications
    And selects Language


  Scenario: Delete language for data driven testing from data table
    When user select language and click on delete button
      |Name|
      |Chinese|
      |Russian|
      |turkish|
      |Spain|
      |German|