Feature: Lang
  Background:
    #Given user is navigated to HRMS application
    When user enters admin username and password
    And user clicks on login button
    Then user is successfully logged in
    When “Admin” navigates to Qualifications
    And selects Language

  Scenario: Adding language via hard coded data
    When Admin enters language name "Russian"
    And Admin clicks on save button
    Then Language success added

  Scenario: Adding Language from data table
    When Admin should remove all language before adding
    Then Admin add multiple language from datatable
      |Name|
      |German|
      |Turkish|
      |Spain|
      |Chinese|

Scenario: Adding langage
  When Admin add already present language and get message
    |Name|
    |German|




