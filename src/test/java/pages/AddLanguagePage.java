package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class AddLanguagePage extends CommonMethods {

    @FindBy(id="menu_admin_viewAdminModule")
    public   WebElement adminOption;

    @FindBy(id="menu_admin_Qualifications")
    public  WebElement qualificationOption;

    @FindBy(id="menu_admin_viewLanguages")
    public WebElement languageOption;
    @FindBy(id="language_name")
    public WebElement languageNameOption;

    @FindBy(id="btnSave")
    public WebElement languageSaveBtn;

    @FindBy(id="btnCancel")
    public WebElement languageCancelBtn;

    @FindBy(id="btnAdd")
    public  WebElement languageAddBtn;

    @FindBy(id="btnDel")
    public WebElement languageDelBtn;
    @FindBy(xpath = "//div[@class='message success fadable']")
public WebElement Success;
    @FindBy(xpath = "//div[@class='message warning fadable']")
    public  WebElement warning;

public String warningMsg="Name Already Exists\n" +
        "Close";
   public String deletedMsg="Successfully Deleted\n" + "Close";

   public String saveMsg="Successfully Saved\n" +"Close";

    public AddLanguagePage() {
        PageFactory.initElements(driver, this);
    }
}
