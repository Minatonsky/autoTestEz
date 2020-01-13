package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SettingsPage extends ParentPage {

    public SettingsPage(WebDriver webDriver) {
        super(webDriver, "/dash/settings/account/");
    }

    @FindBy(xpath = ".//a[@href='/dash/settings/driver_settings/']")
    private WebElement driverSettings;

    @FindBy(name = "ssn")
    private WebElement ssn;

    @FindBy(name = "ein")
    private WebElement ein;

    @FindBy(name = "state_id")
    private WebElement state_id;

    @FindBy(name = "driver_city")
    private WebElement driver_city;

    @FindBy(name = "driver_address")
    private WebElement driver_address;

    @FindBy(name = "phone")
    private WebElement phone;

    @FindBy(id = "Notes")
    private WebElement notes;

    @FindBy(xpath = "//button[@class='btn btn-transparent']")
    private WebElement buttonSave;

    @FindBy(id = "hideEngineScoreStatus")
    private WebElement hideEngineScoreStatus;

    @FindBy(id = "yardModeCheck")
    private WebElement yardModeCheck;

    @FindBy(id = "ConveyanceCheck")
    private WebElement ConveyanceCheck;

    @FindBy(css = "#driver_settings > div:nth-child(4) > div:nth-child(6) > div > label > i")
    private WebElement smsCheck;

    @FindBy(id = "HazMatCheck")
    private WebElement hazMatCheck;

    @FindBy(id = "InsuranceCheck")
    private WebElement InsuranceCheck;

    @FindBy(id = "TankerCheck")
    private WebElement TankerCheck;

//    @FindBy(id = "")
//    private WebElement ;


    public void clickOnDriverSettings() {
        actionsWithOurElements.clickOnElement(driverSettings);
    }
    public  void clickOnSmsCheck(){
        actionsWithOurElements.clickOnElement(smsCheck);
    }
}
