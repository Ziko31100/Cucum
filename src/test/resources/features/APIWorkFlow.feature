Feature: API workflow
Background:
  Given a JWT bearer token is created

  @api
  Scenario: create employee from framework with rest assured
       Given a request is prepared to create an employee using api call
    When a POST call is made to create the employee
    Then the status code for this request shoul de be 201
    And the employee created contains key "Message" and value "Employee Created"
    And the employee id "Employee.employee_id" is stored as global variable

    @api
    Scenario: Get the created employee
      Given a request is prepared to get the created employee
      When a GET call is made to retrieve the employee
      Then the status code for this get request is 200
      And the employee has ID "employee.employee_id" must match with global emp id
      And the data coming from "employee" object should match with the data used in post call
      |emp_firstname|emp_lastname|emp_middle_name|emp_gender|emp_birthday|emp_status|emp_job_title|
      |Voya        |D            |ms             |Male      |1990-03-03  |active    |IT           |

@json
      Scenario: Create employee using json payload
        Given a request is prepared to create an employee using json payload
        When a POST call is made to create the employee
        Then the status code for this request shoul de be 201
        And the employee created contains key "Message" and value "Employee Created"
        And the employee id "Employee.employee_id" is stored as global variable

  @jsondynamic
  Scenario: Create employee using more dynamic json payload
    Given a request is prepared using data "Voya","D","ms","M","1990-03-03","Active","IT"
    When a POST call is made to create the employee
    Then the status code for this request shoul de be 201
    And the employee created contains key "Message" and value "Employee record Updated"
    And the employee id "Employee.employee_id" is stored as global variable

  @json1
  Scenario: prepare the request for updating the employee and validate the body and the status code
    Given a request is prepared to update an employee using employee_id "103992A","Voya","mds","KsKK","M","1991-04-09","Validddd","manageeeeer"
    When a PUT call is made to update the employee
    Then the status code for this get request is 200
    And the employee update response contains key "Message" and value "Employee record Updated"
    And the data coming from "employee" object should match with the data used in put call
      | emp_firstname | emp_middle_name | emp_lastname | emp_birthday | emp_gender | emp_job_title | emp_status  |
      | Voya       | mds             | KsKK         | 1991-04-09   | Male       | Validddd      | manageeeeer |