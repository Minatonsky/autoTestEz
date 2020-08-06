package pages;

import libs.UtilsForDB;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static libs.Utils.listArrayToMap;
import static libs.Utils.waitABit;

public class LogsPage extends ParentPage {
    public LogsPage(WebDriver webDriver, UtilsForDB utilsForDB) {
        super(webDriver, "/dash/drivers/", utilsForDB);
    }
    @FindBy(xpath = ".//button[text()='Correction & Annotation']")
    private WebElement buttonCorrection;

    @FindBy(xpath = ".//*[text()=' Duty']")
    private WebElement buttonInsertStatus;

    @FindBy(xpath = ".//input[@id='time_to']")
    private WebElement timeToInput;

    @FindBy(xpath = ".//input[@id='time_from']")
    private WebElement timeFromInput;

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

    @FindBy(id="datepicker")
    private WebElement dateInput;

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

    public void addStatus(String timeFrom, String timeTo, String status){
        actionsWithOurElements.clickOnElement(timeToInput);
        actionsWithOurElements.enterTextToElement(timeInput, timeTo);
        actionsWithOurElements.clickOnElement(saveButton);

        actionsWithOurElements.clickOnElement(timeFromInput);
        actionsWithOurElements.enterTextToElement(timeInput, timeFrom);
        actionsWithOurElements.clickOnElement(saveButton);

        if (status.equals("On")){
            actionsWithOurElements.clickOnElement(statusOn);
        } else if (status.equals("Dr")){
            actionsWithOurElements.clickOnElement(statusDr);
        } else if (status.equals("Sb")){
            actionsWithOurElements.clickOnElement(statusDr);
        } else if (status.equals("Off")){
            actionsWithOurElements.clickOnElement(statusDr);
        } else Assert.fail("Unexpected status");

        actionsWithOurElements.scrollByVisibleElement(buttonInsertStatus);
        actionsWithOurElements.clickOnElement(buttonInsertStatus);
        waitABit(3);
    }
    public void addLastStatus(String timeFrom, String timeTo, String status){
        actionsWithOurElements.clickOnElement(timeToInput);
        actionsWithOurElements.enterTextToElement(timeInput, timeTo);
        actionsWithOurElements.clickOnElement(saveButton);

        actionsWithOurElements.clickOnElement(timeFromInput);
        actionsWithOurElements.enterTextToElement(timeInput, timeFrom);
        actionsWithOurElements.clickOnElement(saveButton);

        if (status.equals("On")){
            actionsWithOurElements.clickOnElement(statusOn);
        } else if (status.equals("Dr")){
            actionsWithOurElements.clickOnElement(statusDr);
        } else if (status.equals("Sb")){
            actionsWithOurElements.clickOnElement(statusDr);
        } else if (status.equals("Off")){
            actionsWithOurElements.clickOnElement(statusDr);
        } else Assert.fail("Unexpected status");

        waitABit(3);
    }

    public void clickOnSaveInfoButton(){
        actionsWithOurElements.scrollByVisibleElement(saveInfoButton);
        actionsWithOurElements.clickOnElement(saveInfoButton);
        waitABit(10);
    }

    public void clickOnSaveButton(){ actionsWithOurElements.clickOnElement(saveButton); }

    public boolean isDriveHoursViolationPresent(){return actionsWithOurElements.isElementDisplay(driveHoursViolation);}
    public boolean isBreakViolationPresent(){return actionsWithOurElements.isElementDisplay(breakViolation);}
    public boolean isShiftHoursViolationPresent(){return actionsWithOurElements.isElementDisplay(shiftHoursViolation);}

    public void closeCorrectionSavePopUp(){actionsWithOurElements.clickOnElement(correctionSavedPopUpClose);}

    public boolean checkAlertsId(String driverId, String date, String violationId) throws SQLException {
        List<String> tempDataSettingsList = utilsForDB.getAlertsData(driverId, date);
        for (String element :
                tempDataSettingsList ) {
            if (element.equals(violationId)){
                return true;
            }
        } return false;
    } public boolean checkAlertsExist(String driverId, String date) throws SQLException {
        List<String> tempDataSettingsList = utilsForDB.getAlertsData(driverId, date);
        for (String element :
                tempDataSettingsList ) {
            if (element.equals("4") | element.equals("5") | element.equals("6") | element.equals("7") | element.equals("8") | element.equals("9") | element.equals("10")){
                return true;
            }
        } return false;
    }

    public void cleanStatusesViolation(String userId) throws SQLException {
        utilsForDB.deleteStatuses(userId);
        utilsForDB.deleteViolation(userId);
        utilsForDB.updateLastStatus(userId);
    }

    public void setCycle(String userId, String cycleId) throws SQLException {
        utilsForDB.setCycleDriversRules(userId, cycleId);
        utilsForDB.setCycleStatuses(userId, cycleId);
    }

    public int getStatusData(String userId, String data, String value) throws SQLException {
//      value =  drive, shift, cycle, eight, shiftWork, restart34
        List<ArrayList> statusData = utilsForDB.getCycleHoursLastStatus(userId, data);
        Map<String, Object> tempStatusData = listArrayToMap(statusData);
        int temp = Integer.parseInt(tempStatusData.get(value).toString());
        int result = temp / 3600;
        return result;

    }


}
