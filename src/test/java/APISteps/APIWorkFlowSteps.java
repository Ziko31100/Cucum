package APISteps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import utils.APIConstants;
import utils.APIPayloadConstants;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class APIWorkFlowSteps {
    String baseURI = RestAssured.baseURI
            = "http://hrm.syntaxtechs.net/syntaxapi/api";
    String token;
    RequestSpecification request;
    Response response;
    public static String employee_id;
    public static String temporarEmpId;

    @Given("a JWT bearer token is created")
    public void a_jwt_bearer_token_is_created() {
        request = given().
                header(APIConstants.HEADER_CONTENT_TYPE_KEY, APIConstants.HEADER_CONTENT_TYPE_VALUE).
                body("{\n" +
                        "  \"name\": \"Ziko9039\",\n" +
                        "  \"email\": \"Ziko03939@gmail.com\",\n" +
                        "  \"password\": \"Hum@123test\"\n" +
                        "}");
        response = request.when().post(APIConstants.GENERATE_TOKEN);
        token = "Bearer " + response.jsonPath().getString("token");
        System.out.println(token);
    }

    @Given("a request is prepared to create an employee using api call")
    public void a_request_is_prepared_to_create_an_employee_using_api_call() {
        request = given().
                header(APIConstants.HEADER_CONTENT_TYPE_KEY, APIConstants.HEADER_CONTENT_TYPE_VALUE).
                header(APIConstants.HEADER_AUTHORIZATION_KEY, token).
                body(APIPayloadConstants.createEmployeePayload());
             /*   body("{\n" +
                        "  \"emp_firstname\": \"Voya\",\n" +
                        "  \"emp_lastname\": \"D\",\n" +
                        "  \"emp_middle_name\": \"ms\",\n" +
                        "  \"emp_gender\": \"M\",\n" +
                        "  \"emp_birthday\": \"1990-03-03\",\n" +
                        "  \"emp_status\": \"Active\",\n" +
                        "  \"emp_job_title\": \"IT\"\n" +
                        "}");
                  */
    }

    @When("a POST call is made to create the employee")
    public void a_post_call_is_made_to_create_the_employee() {
        response = request.when().post(APIConstants.CREATE_EMPLOYEE);
    }

    @Then("the status code for this request shoul de be {int}")
    public void the_status_code_for_this_request_shoul_de_be(Integer int1) {
        response.then().assertThat().statusCode(int1);
        response.prettyPrint();
    }

    @And("the employee created contains key {string} and value {string}")
    public void theEmployeeCreatedContainsKeyAndValue(String key, String value) {
        response.then().assertThat().body(key, equalTo(value));
    }

    @And("the employee id {string} is stored as global variable")
    public void theEmployeeIdIsStoredAsGlobalVariable(String employeeId) {
        employee_id = response.jsonPath().getString(employeeId);

    }

    @Given("a request is prepared to get the created employee")
    public void aRequestIsPreparedToGetTheCreatedEmployee() {
        request = given().
                header(APIConstants.HEADER_CONTENT_TYPE_KEY, APIConstants.HEADER_CONTENT_TYPE_VALUE).
                header(APIConstants.HEADER_AUTHORIZATION_KEY, token).
                queryParam("employee_id", employee_id);
    }

    @When("a GET call is made to retrieve the employee")
    public void aGETCallIsMadeToRetrieveTheEmployee() {
        response = request.when().get(APIConstants.GET_ONE_EMPLOYEE);
    }

    @Then("the status code for this get request is {int}")
    public void theStatusCodeForThisGetRequestIs(int code) {
        response.then().assertThat().statusCode(code);
        response.prettyPrint();
    }

    @And("the employee has ID {string} must match with global emp id")
    public void theEmployeeHasIDMustMatchWithEmpId(String emp) {
        temporarEmpId = response.jsonPath().getString(emp);
        Assert.assertEquals(temporarEmpId, employee_id);

    }

    @Then("the data coming from {string} object should match with the data used in post call")
    public void the_data_coming_from_object_should_match_with_the_data_used_in_post_call(String empObject, io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> expectedData = dataTable.asMaps();
        Map<String, String> actualData = response.jsonPath().get(empObject);
        for (Map<String, String> map : expectedData) {
            Set<String> keys = map.keySet();
            for (String key : keys) {
                String expectedValue = map.get(key);
                String actualValue = actualData.get(key);
                Assert.assertEquals(actualValue, expectedValue);
            }
        }
    }

    @Given("a request is prepared to create an employee using json payload")
    public void aRequestIsPreparedToCreateAnEmployeeUsingJsonPayload() {
        request = given().header(APIConstants.HEADER_CONTENT_TYPE_KEY, APIConstants.HEADER_CONTENT_TYPE_VALUE).
                header(APIConstants.HEADER_AUTHORIZATION_KEY, token).
                body(APIPayloadConstants.createEmployeeJsonPayload());

    }

    @Given("a request is prepared using data {string},{string},{string},{string},{string},{string},{string}")
    public void aRequestIsPreparedUsingData(String firstname, String lastname, String middle_name, String gender, String birthday, String status, String job_title) {
        request = given().header(APIConstants.HEADER_CONTENT_TYPE_KEY,
                        APIConstants.HEADER_CONTENT_TYPE_VALUE).
                header(APIConstants.HEADER_AUTHORIZATION_KEY, token).
                body(APIPayloadConstants.createEmployeeJsonPayloadDynamic(firstname, lastname, middle_name, gender, birthday, status, job_title));


    }

    @Given("a request is prepared to update an employee using employee_id {string},{string},{string},{string},{string},{string},{string},{string}")
    public void aRequestIsPreparedToUpdateAnEmployeeUsingEmployee_id(String empId, String firstname, String lastname, String middle_name, String gender, String birthday, String status, String job_title) {

        request = given().header(APIConstants.HEADER_CONTENT_TYPE_KEY, APIConstants.HEADER_CONTENT_TYPE_VALUE).
                header(APIConstants.HEADER_AUTHORIZATION_KEY, token).
                body(APIPayloadConstants.UpdateEmployee(empId, firstname, lastname, middle_name, gender, birthday, status, job_title));
    }

    @When("a PUT call is made to update the employee")
    public void a_put_call_is_made_to_update_the_employee() {
        response = request.when().put(APIConstants.UPDATE_EMPLOYEE);
    }

    @Then("the employee update response contains key {string} and value {string}")
    public void the_employee_update_response_contains_key_and_value(String key, String value) {
        response.then().assertThat().body(key, equalTo(value));
    }

    @Then("the data coming from {string} object should match with the data used in put call")
    public void the_data_coming_from_object_should_match_with_the_data_used_in_put_call(String empObject, io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> expectedData = dataTable.asMaps();
        Map<String, String> actualData = response.jsonPath().get(empObject);
        for (Map<String, String> map : expectedData) {
            Set<String> keys = map.keySet();
            for (String key : keys) {
                String expectedValue = map.get(key);
                String actualValue = actualData.get(key);
                Assert.assertEquals(actualValue, expectedValue);
            }
        }
    }

}

