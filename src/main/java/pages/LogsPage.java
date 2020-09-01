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

import static libs.CycleRules.getCycleRules;
import static libs.Utils.*;

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

    @FindBy(xpath = ".//*[text()='11:59:59PM']")
    private WebElement lastMinute;

    public void clickOnRowDay(String dataDay){actionsWithOurElements.clickOnElement(".//*[@data-date='" + dataDay + "']");}
    public void clickOnCorrectionButton(){actionsWithOurElements.clickOnElement(buttonCorrection);}
    public void clickOnInsertStatusButton(){
        actionsWithOurElements.scrollByVisibleElement(buttonInsertStatus);
        actionsWithOurElements.clickOnElement(buttonInsertStatus);
        waitABit(3);
    }
    public void clickOnLastMinuteOnStatusList(){actionsWithOurElements.clickOnElement(lastMinute);}
    public void selectStatus(String status){
        if (status.equals("On")){
            actionsWithOurElements.clickOnElement(statusOn);
        } else if (status.equals("Dr")){
            actionsWithOurElements.clickOnElement(statusDr);
        } else if (status.equals("Sb")){
            actionsWithOurElements.clickOnElement(statusSb);
        } else if (status.equals("Off")){
            actionsWithOurElements.clickOnElement(statusOff);
        } else Assert.fail("Unexpected status");
    }

    public void addStatus(String timeFrom, String timeTo, String status){
        actionsWithOurElements.clickOnElement(timeToInput);
        actionsWithOurElements.enterTextToElement(timeInput, timeTo.replaceAll("\\W", ""));
        actionsWithOurElements.clickOnElement(saveButton);

        actionsWithOurElements.clickOnElement(timeFromInput);
        actionsWithOurElements.enterTextToElement(timeInput, timeFrom.replaceAll("\\W", ""));
        actionsWithOurElements.clickOnElement(saveButton);
        selectStatus(status);
        actionsWithOurElements.scrollByVisibleElement(buttonInsertStatus);
        actionsWithOurElements.clickOnElement(buttonInsertStatus);
        waitABit(3);
    }

    public void addLastStatus(String timeFrom, String timeTo, String status){
        actionsWithOurElements.clickOnElement(timeToInput);
        actionsWithOurElements.enterTextToElement(timeInput, timeTo.replaceAll("\\W", ""));
        actionsWithOurElements.clickOnElement(saveButton);

        actionsWithOurElements.clickOnElement(timeFromInput);
        actionsWithOurElements.enterTextToElement(timeInput, timeFrom.replaceAll("\\W", ""));
        actionsWithOurElements.clickOnElement(saveButton);
        selectStatus(status);
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
        waitABit(5);
        List<String> tempDataSettingsList = utilsForDB.getAlertsData(driverId, date);
        for (String element :
                tempDataSettingsList ) {
            if (element.equals(violationId)){
                return true;
            }
        } return false;
    }
    public boolean checkAlertsExist(String driverId, String date) throws SQLException {
        waitABit(5);
        List<String> tempDataSettingsList = utilsForDB.getAlertsData(driverId, date);
        for (String element :
                tempDataSettingsList ) {
            if (element.equals("4") | element.equals("5") | element.equals("6") | element.equals("7") | element.equals("8") | element.equals("9") | element.equals("10")){
                return true;
            }
        } return false;
    }

    public void cleanStatusesAndViolation(String userId) throws SQLException {
        String date = getDateAndTime("yyyy-MM");
        utilsForDB.deleteStatuses(userId);
        utilsForDB.deleteViolation(userId);
        utilsForDB.deleteStatusEvent(userId, date);
        utilsForDB.updateLastStatus(userId);
    }

    public void setCycle(String userId, int cycleId) throws SQLException {
        utilsForDB.setCycleDriversRules(userId, cycleId);
        utilsForDB.setCycleStatuses(userId, cycleId);

    }

    public int getStatusData(String userId, String data, String value) throws SQLException {
//      value =  drive, shift, cycle, eight, shiftWork, restart34
        List<ArrayList> statusData = utilsForDB.getCycleHoursLastStatus(userId, data);
        Map<String, Object> tempStatusData = listArrayToMap(statusData);
        int temp = Integer.parseInt(tempStatusData.get(value).toString());
        logger.info("getStatusDataFromDB " + value + " " + temp);
        return temp;

    }

    public int getCycleHours(int cycleId, int cargoType, int duration){
        int cycleRulesHours = getCycleRules(cycleId, cargoType).getCycleHours();
        int temp = (cycleRulesHours * 3600) - duration;
        logger.info("countCycleHours " + temp);
        return temp;
    }

    public int getDrivingHour(int cycleId, int cargoType, int drivingTime){
        int drivingRulesHours = getCycleRules(cycleId, cargoType).getDriveHours();
        int temp = (drivingRulesHours * 3600) - drivingTime;
        logger.info("countDrivingHour " + temp);
        return temp;
    }

    public int getShiftHour(int cycleId, int cargoType, int duration){
        int shiftRulesHours = getCycleRules(cycleId, cargoType).getShiftHours();
        int shiftHours = (shiftRulesHours * 3600) - duration;
        logger.info("countShiftHour " + shiftHours);
        return shiftHours;
    }

    public int getRestart34(int cycleId, int cargoType){
        int restartRulesHours = getCycleRules(cycleId, cargoType).getRestartHours();
        int restartHours = (restartRulesHours * 3600);
        logger.info("restartHours " + restartHours);
        return restartHours;
    }

    public int countHoursStatuses(List<String> list){
        int temp = 0;
        for (String i :
                list) {
            String[] parts = i.split("/");
            temp += (int) getDurationBetweenTime(parts[0], parts[1]);
        }
        return temp;
    }

    public void enterShiftHours(int cycleId, int cargoType){
        switch (cycleId){
            case 0:
            case 1:
                if (cargoType != 2){
                    //14 hours
                    addStatus("00:00:00 AM", "01:00:00 AM", "On");
                    addStatus("01:00:00 AM", "06:00:00 AM", "Dr");
                    addStatus("07:00:00 AM", "10:00:00 AM", "Dr");
                    addStatus("10:00:00 AM", "12:00:00 PM", "On");
                    addLastStatus("12:00:00 PM", "02:00:00 PM", "Dr");
                } else {
                    //15 hours
                    addStatus("00:00:00 AM", "01:00:00 AM", "On");
                    addStatus("01:00:00 AM", "06:00:00 AM", "Dr");
                    addStatus("07:00:00 AM", "10:00:00 AM", "Dr");
                    addStatus("10:00:00 AM", "12:00:00 PM", "On");
                    addLastStatus("01:00:00 PM", "03:00:00 PM", "Dr");
                } break;
            case 2:
            case 3:
            case 8:
                //20 hours
                addStatus("01:00:00 AM", "02:00:00 AM", "On");
                addStatus("02:00:00 AM", "09:00:00 AM", "Dr");
                addStatus("10:00:00 AM", "12:00:00 PM", "Dr");
                addStatus("12:00:00 PM", "01:00:00 PM", "On");
                addStatus("01:00:00 PM", "03:00:00 PM", "Dr");
                addStatus("04:00:00 PM", "05:00:00 PM", "On");
                addLastStatus("05:00:00 PM", "09:00:00 PM", "Dr");
                break;
            case 4:
            case 5:
                //16 hours
                addStatus("00:00:00 AM", "01:00:00 AM", "On");
                addStatus("01:00:00 AM", "06:00:00 AM", "Dr");
                addStatus("07:00:00 AM", "10:00:00 AM", "Dr");
                addStatus("11:00:00 AM", "12:00:00 PM", "Dr");
                addLastStatus("12:00:00 PM", "04:00:00 PM", "Dr");
                break;
            case 6:
                //15 hours
                addStatus("00:00:00 AM", "01:00:00 AM", "On");
                addStatus("01:00:00 AM", "06:00:00 AM", "Dr");
                addStatus("07:00:00 AM", "10:00:00 AM", "Dr");
                addStatus("10:00:00 AM", "11:00:00 AM", "On");
                addLastStatus("11:00:00 PM", "03:00:00 PM", "Dr");
                break;
            case 7:
                if (cargoType == 2){
                    //15 hours
                    addStatus("02:00:00 AM", "03:00:00 AM", "On");
                    addStatus("03:00:00 AM", "07:00:00 AM", "Dr");
                    addStatus("08:00:00 AM", "11:00:00 AM", "Dr");
                    addLastStatus("12:00:00 PM", "05:00:00 PM", "Dr");
                } else {
                    //16 hours
                    addStatus("01:00:00 AM", "07:00:00 AM", "Dr");
                    addStatus("08:00:00 AM", "11:00:00 AM", "Dr");
                    addLastStatus("12:00:00 PM", "05:00:00 PM", "Dr");
                } break;
        }

    }

    public void addLastMinuteWithViolationShiftHours(int cycleId, int cargoType) {
        switch (cycleId) {
            case 0:
            case 1:
                if (cargoType == 2) {
                    //15 hours
                    addLastStatus("03:00:00 PM", "03:02:00 PM", "Dr");

                } else {
                    //14 hours
                    addLastStatus("02:00:00 PM", "02:02:00 PM", "Dr");
                }
                break;
            case 2:
            case 3:
            case 8:
                //20 hours
                addLastStatus("09:00:00 PM", "09:02:00 PM", "Dr");
                break;
            case 4:
            case 5:
                addLastStatus("04:00:00 PM", "04:02:00 PM", "Dr");
                break;
            case 7:
                //16 hours
                addLastStatus("05:00:00 PM", "05:02:00 PM", "Dr");
                break;
            case 6:
                //15 hours
                addLastStatus("03:00:00 PM", "03:02:00 PM", "Dr");
                break;
        }
    }
    public void enterRestBreak(){
        addStatus("09:00:00 AM", "10:00:00 AM", "On");
        addStatus("10:00:00 AM", "12:00:00 PM", "Dr");
        addStatus("12:00:00 PM", "03:00:00 PM", "Dr");
        addStatus("03:00:00 PM", "05:00:00 PM", "On");
        addStatus("05:30:00 PM", "09:00:00 PM", "Dr");
        addStatus("09:00:00 PM", "10:00:00 PM", "On");
        addLastStatus("10:00:00 PM", "11:00:00 PM", "Dr");
    }
    public void addViolationForRestBreak(){
        addLastStatus("05:00:00 PM", "05:30:00 PM", "Dr");
    }
    public boolean isRestBreakRequired(int cycleId, int cargoType){
        switch (cycleId){
            case 0:
            case 1:
                if (cargoType == 2 | cargoType == 4){
                    return false;
                } else {
                    return true;
                }
            case 2:
            case 3:
            case 6:
            case 7:
                if (cargoType == 4) {return false;}
                else {return true;}
            case 4:
            case 5:
            case 8:
                return false;
            default: return true;
        }

    }
}
