package pagesFrankestein;

import libs.UtilsForDB;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static libs.Utils.waitABit;

public class LogsFPage extends ParentFPage {
    public LogsFPage(WebDriver webDriver, UtilsForDB utilsForDB) {
        super(webDriver, "/dash/drivers/", utilsForDB);
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

    @FindBy(xpath = "//*[@data-11='11' and text()='dr']")
    private WebElement driveHoursViolation;

    @FindBy(xpath = "//*[@data-8='8' and text()='b']")
    private WebElement breakViolation;

    @FindBy(xpath = "//*[@data-14='14' and text()='sh']")
    private WebElement shiftHoursViolation;

    @FindBy(xpath = "//*[text()='Correction Saved']/../button[@type='button']")
    private WebElement correctionSavedPopUpClose;

    public void clickOnRowDay(String dataDay){actionsWithOurElements.clickOnElement(".//*[@data-date='" + dataDay + "']");}
    public void clickOnCorrectionButton(){actionsWithOurElements.clickOnElement(buttonCorrection);}
    public void clickOnInsertStatusButton(){
        actionsWithOurElements.scrollByVisibleElement(buttonInsertStatus);
        actionsWithOurElements.clickOnElement(buttonInsertStatus);
        waitABit(3);
    }

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
    public void clickOnSaveButton(){ actionsWithOurElements.clickOnElement(saveButton); }
    public boolean isDriveHoursViolationPresent(){return actionsWithOurElements.isElementDisplay(driveHoursViolation);}
    public boolean isBreakViolationPresent(){return actionsWithOurElements.isElementDisplay(breakViolation);}
    public boolean isShiftHoursViolationPresent(){return actionsWithOurElements.isElementDisplay(shiftHoursViolation);}
    public void closeCorrectionSavePopUp(){actionsWithOurElements.clickOnElement(correctionSavedPopUpClose);}
}
