package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.CommonMethods;

public class PictureUploadSteps extends CommonMethods {
    @When("user click on choose file button")
    public void user_click_on_choose_file_button() {
        driver.navigate().to("http://hrm.syntaxtechs.net/humanresources/symfony/web/index.php/pim/viewPhotograph/empNumber/93603");



    }
    @When("user choose file and click on open button")
    public void user_choose_file_and_click_on_open_button() {
    driver.findElement(By.xpath("//input[@id='photofile']")).
          sendKeys("C:\\Users\\Zinur\\OneDrive\\Pictures\\Screenshots\\save.png");

    }
    @When("user clicks on upload button")
    public void user_clicks_on_upload_button() {
click(pictureUploadPage.pictureSaveBtn);
    }


    @Then("picture successfully uploaded")
    public void picture_successfully_uploaded() {
        WebElement pic=driver.findElement(By.xpath("//*[@id='empPic']"));

        if(pic.getAttribute("width").equals("200") && pic.getAttribute("height").equals("200")){
            System.out.println("your picture uploaded successful");
        } else{
            System.out.println("Recommended dimensions for the profile picture should be 200px x 200px");
        }

    }

}
