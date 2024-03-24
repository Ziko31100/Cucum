package steps;


import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.xml.DOMConfigurator;
import org.junit.Assert;
import utils.CommonMethods;
import utils.ConfigReader;
import utils.Log;

import java.util.List;
import java.util.Map;

public class LoginSteps extends CommonMethods {
    private Scenario scenario;


    //we don't need this driver, since it is coming from common methods class
    // public WebDriver driver;

    @Given("user is navigated to HRMS application")
    public void user_is_navigated_to_hrms_application(){
        openBrowserAndLaunchApplication();
        // driver = new ChromeDriver();
        //driver.get("http://hrm.syntaxtechs.net/humanresources/symfony/web/index.php/auth/login");
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }


    @When("user enters admin username and password")
    public void user_enters_admin_username_and_password() {
        //   LoginPage login = new LoginPage();
        // driver.findElement(By.id("txtUsername")).sendKeys("admin");
        //   WebElement usernameField = driver.findElement(By.id("txtUsername"));
        //  WebElement passwordField = driver.findElement(By.id("txtPassword"));
        // dom configurator - its a class which allows us to add log4j.xml in our code
        DOMConfigurator.configure("log4j.xml");
        Log.startTestCase("My login start case");
        Log.info("My test case is executing");
        Log.info("I am testing log4j functionality");
       // Log.error("error message");
        sendText(ConfigReader.read("userName"),login.usernameField);
        sendText(ConfigReader.read("password"),login.passwordField);
        Log.endTestCase("FINISH");

        //driver.findElement(By.id("txtPassword")).sendKeys("Hum@nhrm123");
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @When("user clicks on login button")
    public void user_clicks_on_login_button() {
        // LoginPage login = new LoginPage();
        //  WebElement loginButton =  driver.findElement(By.id("btnLogin"));

        click(login.loginButton);
        //driver.findElement(By.id("btnLogin")).click();
    }

    @Then("user is successfully logged in")
    public void user_is_successfully_logged_in() {
        //  WebElement welcomeMessage = driver.findElement(By.id("welcome"));
     //   System.out.println(0/10);
        Assert.assertTrue(dashboardPage.welcomeMessageOption.isDisplayed());

        System.out.println("My test is passed");
    }

    @When("user enters {string} and {string} and verify the {string}")
    public void user_enters_and_and_verify_the
            (String usernameValue, String passwordValue, String errorMessage) {
        //  WebElement usernameField = driver.findElement(By.id("txtUsername"));
        //WebElement passwordField = driver.findElement(By.id("txtPassword"));
        //WebElement loginButton =  driver.findElement(By.id("btnLogin"));
        //  LoginPage login = new LoginPage();
        sendText(usernameValue, login.usernameField);
        sendText(passwordValue, login.passwordField);
        click(login.loginButton);
        //to get the error message, we need this web element
        //  WebElement errorLoc = driver.findElement(By.id("spanMessage"));
        String errorMessageValue = login.errorMessageLoc.getText();
        // System.out.println(errorMessageValue);
        Assert.assertEquals(errorMessage,errorMessageValue);
    }


    @Then("the user enters the username and password and clicks on login then sees the error message")
    public void the_user_enters_the_username_and_password_and_clicks_on_login_then_sees_the_error_message(io.cucumber.datatable.DataTable dataTable) {

        List<Map<String,String>> wrongCredentials=dataTable.asMaps();
        for(Map<String,String> data:wrongCredentials){
          String username=  data.get("username");
           String password= data.get("password");
            String errormsg =data.get("error message");
            sendText(username,login.usernameField);
            sendText(password,login.passwordField);
            click(login.loginButton);
            String actualMessage=login.errorMessageLoc.getText();
            Assert.assertEquals(actualMessage,errormsg);
        }
    }



}
