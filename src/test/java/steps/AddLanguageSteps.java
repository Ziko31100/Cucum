package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.eo.Se;
import io.cucumber.java.it.Ma;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utils.CommonMethods;

import java.util.List;
import java.util.Map;

public class AddLanguageSteps extends CommonMethods {
    @When("“Admin” navigates to Qualifications")
    public void admin_navigates_to_qualifications() {
        click(addLanguagePage.adminOption);
        click(addLanguagePage.qualificationOption);
    }

    @When("selects Language")
    public void selects_language() {
        click(addLanguagePage.languageOption);
    }

    @When("Admin enters language name {string}")
    public void admin_enters_language_name(String languageName) {
        List<WebElement> Languages = driver.findElements(By.xpath("//table/tbody/tr/td[2]"));
        for (int i = 0; i < Languages.size(); i++) {
            String name = Languages.get(i).getText();
            if (name.equalsIgnoreCase(languageName)) {
                WebElement checkBox = driver.findElement(By.xpath("//table/tbody/tr[" + (i + 1) + "]/td[1]"));
                click(checkBox);
                click(addLanguagePage.languageDelBtn);
                WebElement mes = driver.findElement(By.xpath("//div[@class='message warning fadable']"));
                String m = "Successfully Deleted\n" +
                        "Close";
                String m1 = mes.getText();
                Assert.assertEquals(m, m1);
            }
        }
        click(addLanguagePage.languageAddBtn);
        sendText(languageName, addLanguagePage.languageNameOption);


    }

    @When("Admin clicks on save button")
    public void admin_clicks_on_save_button() {
        click(addLanguagePage.languageSaveBtn);

    }

    @Then("Language success added")
    public void language_success_added() {
        WebElement m2 = driver.findElement(By.xpath("//div[@class='message success fadable']"));
        String mm2 = m2.getText();
        String expecct = "Successfully Saved\n" +
                "Close";
        Assert.assertEquals(expecct, mm2);
    }




    @When("Admin should remove all language before adding")
    public void adminShouldRemoveAllLanguagesBeforeAdding() {
        List<WebElement> Languages = driver.findElements(By.xpath("//table/tbody/tr/td[2]"));

        for(int i=0;i<Languages.size();i++){
            WebElement checkBox=(driver.findElement(By.xpath("//table/tbody/tr["+ (i + 1) + "]/td[1]")));
                click(checkBox);
}click(addLanguagePage.languageDelBtn);

       // String expectedMsg="Successfully Deleted\n" + "Close";

        Assert.assertEquals(addLanguagePage.deletedMsg,addLanguagePage.Success.getText());
    }

    @Then("Admin add multiple language from datatable")
    public void adminAddMultipleLanguageFromDatatable(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String,String>>data=dataTable.asMaps();
        for(Map<String,String> d:data){
            click(addLanguagePage.languageAddBtn);
            sendText(d.get("Name"),addLanguagePage.languageNameOption);
            click(addLanguagePage.languageSaveBtn);
            //WebElement deleteMsg = driver.findElement(By.xpath("//div[@class='message success fadable']"));
          //  String expecct = "Successfully Saved\n" +
            //        "Close";
            Assert.assertEquals(addLanguagePage.saveMsg,addLanguagePage.Success.getText());

        }
    }

  @When("Admin add already present language and get message")
    public void adminAddAlreadyPresentLanguageAndGetMessage(io.cucumber.datatable.DataTable dataTable) {

        List<Map<String,String>> dataT=dataTable.asMaps();
        for (Map<String,String>d:dataT){
            click(addLanguagePage.languageAddBtn);
            sendText(d.get("Name"), addLanguagePage.languageNameOption);
            click(addLanguagePage.languageSaveBtn);
           Assert.assertEquals(addLanguagePage.warningMsg,addLanguagePage.warning.getText());

        }
    }
}











