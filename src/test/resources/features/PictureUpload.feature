Feature: Oversize and Undersize picture upload

  Background:
    #Given user is navigated to HRMS application
    When user enters admin username and password
    And user clicks on login button
    Then user is successfully logged in


    Scenario: user upload undersize picture
      When user click on choose file button
      And user choose file and click on open button
      And user clicks on upload button
      Then picture successfully uploaded