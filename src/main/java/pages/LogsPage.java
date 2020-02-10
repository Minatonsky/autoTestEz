package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogsPage extends ParentPage {
    public LogsPage(WebDriver webDriver) {
        super(webDriver, "/dash/drivers/");
    }
    @FindBy(xpath = ".//button[text()='Correction & Annotation']")
    private WebElement buttonCorrection;

    @FindBy(xpath = ".//*[text()=' Duty']")
    private WebElement buttonInsertStatus;

    @FindBy(xpath = ".//input[@id='time_to']")
    private WebElement timeTo;

    @FindBy(xpath = ".//input[@id='time_from']")
    private WebElement timeFrom;

    @FindBy(xpath = ".//input[@id='time_control']")
    private WebElement timeInput;

    @FindBy(xpath = ".//button[@id='time_save']")
    private WebElement saveButton;

    @FindBy(id = "status_on")
    private WebElement statusOn;

    @FindBy(id = "status_dr")
    private WebElement statusDr;

    @FindBy(id = "status_sb")
    private WebElement statusSb;

    @FindBy(id = "status_off")
    private WebElement statusOff;

    @FindBy(id="save_info")
    private WebElement saveInfoButton;

    public void clickOnRowDay(String dataDay){actionsWithOurElements.clickOnElement(".//*[@data-date='" + dataDay + "']");}
    public void clickOnCorrectionButton(){actionsWithOurElements.clickOnElement(buttonCorrection);}
    public void clickOnInsertStatusButton(){
        actionsWithOurElements.scrollByVisibleElement(buttonInsertStatus);
        actionsWithOurElements.clickOnElement(buttonInsertStatus);}

    public void clickOnTimeTo(){
        actionsWithOurElements.scrollByVisibleElement(timeTo);
        actionsWithOurElements.clickOnElement(timeTo);}
    public void clickOnTimeFrom(){
        actionsWithOurElements.scrollByVisibleElement(timeFrom);
        actionsWithOurElements.clickOnElement(timeFrom);}

    public void clickOnStatusOn(){actionsWithOurElements.clickOnElement(statusOn);}
    public void clickOnStatusDr(){actionsWithOurElements.clickOnElement(statusDr);}
    public void clickOnStatusSb(){actionsWithOurElements.clickOnElement(statusSb);}
    public void clickOnStatusOff(){actionsWithOurElements.clickOnElement(statusOff);}

    public void clickOnSaveInfoButton(){
        actionsWithOurElements.scrollByVisibleElement(saveInfoButton);
        actionsWithOurElements.clickOnElement(saveInfoButton);}
    public void timeToInput(String time){actionsWithOurElements.enterTextToElement(timeInput, time);}
    public void clickOnSaveButton(){actionsWithOurElements.clickOnElement(saveButton);}
}
