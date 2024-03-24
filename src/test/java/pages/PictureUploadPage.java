package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class PictureUploadPage  extends CommonMethods {
    @FindBy(id="photofile")
  public   WebElement photoFile;

    @FindBy(id="btnSave")
  public   WebElement pictureSaveBtn;


    public PictureUploadPage(){
        PageFactory.initElements(driver,this);
    }
}
