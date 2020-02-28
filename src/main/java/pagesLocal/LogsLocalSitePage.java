package pagesLocal;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static libs.Utils.waitABit;

public class LogsLocalSitePage extends ParentLocalSitePage {
    public LogsLocalSitePage(WebDriver webDriver) {
        super(webDriver, "/dash/drivers/");
    }
    @FindBy(xpath = ".//button[text()='Colection & Annotation']")
    private WebElement buttonCorrection;

    @FindBy(xpath = ".//*[text()='Insert Duty Status']")
    private WebElement buttonInsertStatus;

    @FindBy(name = "input-time_to")
    private WebElement timeTo;

    @FindBy(name = "input-time_from")
    private WebElement timeFrom;


    @FindBy(xpath = ".//*[text()='Save Duty Status']")
    private WebElement saveDutyStatusButton;

    @FindBy(xpath = ".//*[@data-type='on']")
    private WebElement statusOn;

    @FindBy(xpath = ".//*[@data-type='dr']")
    private WebElement statusDr;

    @FindBy(xpath = ".//*[@data-type='sb']")
    private WebElement statusSb;

    @FindBy(xpath = ".//*[@data-type='off']")
    private WebElement statusOff;

    @FindBy(xpath=".//*[text()='Save Duty Status']")
    private WebElement saveButton;

    @FindBy(xpath = "//*[@data-type='dr'")
    private WebElement driveHoursViolation;

    @FindBy(xpath = "//*[@data-type='b']")
    private WebElement breakViolation;

    @FindBy(xpath = "//*[@data-14='14' and text()='sh']")
    private WebElement shiftHoursViolation;

    @FindBy(xpath = "//*[text()='Correction Saved']/../button[@type='button']")
    private WebElement correctionSavedPopUpClose;

    public void clickOnRowDay(String dataDay){actionsWithOurElements.clickOnElement(".//*[text()='" + dataDay + "']");}
    public void clickOnCorrectionButton(){actionsWithOurElements.clickOnElement(buttonCorrection);}
    public void clickOnInsertStatusButton(){
        actionsWithOurElements.scrollByVisibleElement(buttonInsertStatus);
        actionsWithOurElements.clickOnElement(buttonInsertStatus);
        waitABit(3);
    }

    public void clickOnTimeTo(String time){
        actionsWithOurElements.scrollByVisibleElement(timeTo);
        actionsWithOurElements.enterTextToElement(timeTo, time);}
    public void clickOnTimeFrom(String time){
        actionsWithOurElements.scrollByVisibleElement(timeFrom);
        actionsWithOurElements.enterTextToElement(timeFrom, time);}

    public void clickOnStatusOn(){actionsWithOurElements.clickOnElement(statusOn);}
    public void clickOnStatusDr(){actionsWithOurElements.clickOnElement(statusDr);}
    public void clickOnStatusSb(){actionsWithOurElements.clickOnElement(statusSb);}
    public void clickOnStatusOff(){actionsWithOurElements.clickOnElement(statusOff);}

    public void clickOnSaveButton(){
        actionsWithOurElements.scrollByVisibleElement(saveButton);
        actionsWithOurElements.clickOnElement(saveButton);}

    public boolean isDriveHoursViolationPresent(){return actionsWithOurElements.isElementDisplay(driveHoursViolation);}
    public boolean isBreakViolationPresent(){return actionsWithOurElements.isElementDisplay(breakViolation);}
    public boolean isShiftHoursViolationPresent(){return actionsWithOurElements.isElementDisplay(shiftHoursViolation);}
    public void closeCorrectionSavePopUp(){actionsWithOurElements.clickOnElement(correctionSavedPopUpClose);}
}
