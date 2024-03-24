package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.CommonMethods;

import java.util.List;
import java.util.Map;

public class DeleteLanguageSteps extends CommonMethods {
    @When("user select language and click on delete button")
    public void user_select_language_and_click_on_delete_button(io.cucumber.datatable.DataTable dataTable)
            throws InterruptedException{
        List <String> asd=dataTable.asList();
       List<WebElement> ids = driver.findElements(By.xpath("//table/tbody/tr/td[2]"));
        for (String s : asd) {
            for (int i = 0; i < ids.size(); i++) {
                String id = ids.get(i).getText();
                if (s.equalsIgnoreCase(id)) {
                    WebElement click = driver.findElement(By.xpath("//table/tbody/tr[" + (i + 1) + "]/td[1]"));
                    click.click();
                    click(addLanguagePage.languageDelBtn);
                }


            }
        }
    }
}




