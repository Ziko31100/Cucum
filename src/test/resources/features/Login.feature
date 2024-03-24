Feature: Login functionality

@smoke
Scenario: Valid admin login
    #Given user is navigated to HRMS application
    When user enters admin username and password
    And user clicks on login button
    Then user is successfully logged in

  @error @regression
  Scenario Outline: Validating the error message
    When user enters "<username>" and "<password>" and verify the "<errorMessage>"
    Examples:
      | username | password | errorMessage |
      |admin     |vnddd     |Invalid credentials|
      |vvnnfnf   |Hum@nhrm123|Invalid credentials|
      |          |Hum@nhrm123|Username cannot be empty|
      |admin     |           |Password cannot be empty|

@review
  Scenario: u want to validate that user can not login with incorrect credentials
    Then the user enters the username and password and clicks on login then sees the error message
      | username | password | errorMessage |
      |admin      |vnddd     |Invalid credentials|
      |vvnnfnf   |Hum@nhrm123|Invalid credentials|
      |3|Hum@nhrm123|Invalid credentials|
      |admin     |4|Invalid credentials|