package api;

import io.cucumber.java.bs.A;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.checkerframework.checker.units.qual.C;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.hamcrest.Matchers.*;
import org.junit.runners.MethodSorters;

import java.lang.reflect.Array;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HardCodedExample {
    String baseURI = RestAssured.baseURI
            = "http://hrm.syntaxtechs.net/syntaxapi/api";
    static String employee_id;
    String token="Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE3MTA4OTM1MzAsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTcxMDkzNjczMCwidXNlcklkIjoiNjQ3OSJ9.q7GrnAlFZ-mN2BWRCgGizNnExub2Tyg54GaUzlrl2LU";
  @Test
    public void acreateEmployee(){
       RequestSpecification request = given().
               header("Content-Type","application/json").
               header("Authorization",token).
                body("{\n" +
                        "  \"emp_firstname\": \"Voya\",\n" +
                        "  \"emp_lastname\": \"D\",\n" +
                        "  \"emp_middle_name\": \"ms\",\n" +
                        "  \"emp_gender\": \"M\",\n" +
                        "  \"emp_birthday\": \"1990-03-03\",\n" +
                        "  \"emp_status\": \"Active\",\n" +
                        "  \"emp_job_title\": \"IT\"\n" +
                        "}");

       Response response =  request.when().post("/createEmployee.php");

       //assertthat is the method we use to validate the response
       response.then().assertThat().statusCode(201);
       //this method is used to print the response in console
       response.prettyPrint();
       response.then().assertThat().body("Message",equalTo("Employee Created"));
        response.then().assertThat().body("Employee.emp_firstname",equalTo("Voya"));
        response.then().assertThat().body("Employee.emp_lastname",equalTo("D"));
       response.then().assertThat().header("Connection",equalTo("Keep-Alive"));

       employee_id=response.jsonPath().getString("Employee.employee_id");
   }


  @Test
    public void bgetCreatedEmployee(){
       RequestSpecification request= given().
               header("Content-Type","application/json").
               header("Authorization",token).
               queryParam("employee_id",employee_id);
       Response response=request.when().get("/getOneEmployee.php");
       response.then().assertThat().statusCode(200);
       response.prettyPrint();

       String temporarEmpId=response.jsonPath().getString("employee.employee_id");
    Assert.assertEquals(temporarEmpId,employee_id);
    response.then().assertThat().body("employee.emp_firstname",equalTo("Voya"));
    response.then().assertThat().body("employee.emp_lastname",equalTo("D"));
    }

    @Test
    public void cUpdateEmployee(){
       RequestSpecification request=given().
               header("Content-Type","application/json").
               header("Authorization",token).
               body("{\n" +
                       "  \"employee_id\": \""+employee_id+"\",\n" +
                       "  \"emp_firstname\": \"Craft\",\n" +
                       "  \"emp_lastname\": \"Solo\",\n" +
                       "  \"emp_middle_name\": \"ms\",\n" +
                       "  \"emp_gender\": \"M\",\n" +
                       "  \"emp_birthday\": \"2004-03-12\",\n" +
                       "  \"emp_status\": \"jobfull\",\n" +
                       "  \"emp_job_title\": \"IT prog\"\n" +
                       "}");
       Response response=request.when().put("/updateEmployee.php");
       response.then().assertThat().statusCode(200);
       response.then().assertThat().body("Message",equalTo("Employee record Updated"));
       response.prettyPrint();
   }


    @Test
    public void dGetUpdateEmployee(){
        RequestSpecification request=given().
                header("Content-Type","application/json").
                header("Authorization",token).
                queryParam("employee_id",employee_id);
        Response response=request.when().get("/getOneEmployee.php");
        response.then().assertThat().statusCode(200);
        response.prettyPrint();
    }


    @Test
    public void fGetAllEmployee(){
       RequestSpecification request=given().
               header("Content-Type","application/json").
               header("Authorization",token);
       Response response=request.when().get("/getAllEmployees.php");
       response.then().assertThat().statusCode(200);
       response.prettyPrint();


   }

   @Test
    public void gGetJobTitle(){
       RequestSpecification request =given().header("Content-Type","application/json").
               header("Authorization",token);
       Response response=request.when().get("/jobTitle.php");
       response.then().assertThat().statusCode(200);
      response.prettyPrint();

   }

   @Test
           public void hPartialUpdateEmployee() {

       RequestSpecification request = given().
               header("Content-Type", "application/json").
               header("Authorization", token).
               body("{\n" +
                       "  \"employee_id\": \""+employee_id+"\",\n" +
                       "  \"emp_middle_name\": \"Micrsos\",\n" +
                       "  \"emp_status\": \"Wait\",\n" +
                       "  \"emp_job_title\": \"API a\"\n" +
                       "}");
       Response response=request.patch("/updatePartialEmplyeesDetails.php");
        response.prettyPrint();
       response.then().statusCode(201);
response.then().assertThat().body("Message",equalTo("Employee record updated successfully"));

   }

   @Test
    public void iPartialUpdateEmployee(){
       RequestSpecification request=given().header("Content-Type","application/json").
               header("Authorization",token).
               queryParam("employee_id",employee_id);
       Response response=request.when().get("/getOneEmployee.php");
            response.prettyPrint();
       response.then().assertThat().statusCode(200);
   }

}
