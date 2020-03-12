package pages;

import io.qameta.allure.Step;
import libs.UtilsForDB;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ReturnsPage extends ParentPage {
    public ReturnsPage(WebDriver webDriver, UtilsForDB utilsForDB) {
        super(webDriver, "/dash/returns/", utilsForDB);
    }

    @FindBy(xpath = ".//*[@placeholder = 'Device Id']")
    private WebElement deviceIdInput;

    @FindBy(xpath = ".//*[text()='Confirm to send']")
    private WebElement confirmToSendButton;

    @FindBy(xpath = ".//*[text()='Confirm']")
    private WebElement confirmPopUpButton;


    @Step
    public void enterIdDevice(String idDevice){
        actionsWithOurElements.enterTextToElement(deviceIdInput, idDevice);
    }
    @Step
    public void clickOnDeviceInList(String idDevice){
        actionsWithOurElements.clickOnElement(".//*[@data-scanner_id='" + idDevice + "']");
    }
    @Step
    public void clickOnConfirmToSend(){
        actionsWithOurElements.clickOnElement(confirmToSendButton);
    }
    @Step
    public void clickOnConfirmInPopUp(){
        actionsWithOurElements.clickOnElement(confirmPopUpButton);
    }

}
