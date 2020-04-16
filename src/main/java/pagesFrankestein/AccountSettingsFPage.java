package pagesFrankestein;

import libs.UtilsForDB;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static libs.Utils.waitABit;

public class AccountSettingsFPage extends ParentFPage {
    public AccountSettingsFPage(WebDriver webDriver, UtilsForDB utilsForDB) {
        super(webDriver, "/dash/settings/account/", utilsForDB);
    }
    @FindBy(xpath = ".//*[@class='active_tab']/..//a[@href='/dash/settings/account/']")
    private WebElement accountSetting;
    @FindBy(xpath = ".//*[@class='active_tab']/..//a[@href='/dash/settings/fleet_settings/']")
    private WebElement fleetSettingsPage;

//ACCOUNT INFO
    @FindBy(id="user_name")
    private WebElement firstName;

    @FindBy(id="user_last")
    private WebElement lastName;

    @FindBy(id="user_phone")
    private WebElement phoneNumber;

    @FindBy(id="user_email")
    private WebElement userEmail;

    @FindBy(id="user_role")
    private WebElement userRole;

    @FindBy(id="user_new_pass")
    private WebElement userPassword;

    @FindBy(id="user_new_pass_again")
    private WebElement repeatPassword;

    @FindBy(id = "acc_update_user")
    private WebElement buttonSaveAccountInfo;

//    DRIVER INFO

    @FindBy(id="driver_cycle")
    private WebElement cycleSelect;

    @FindBy(id="driver_timeZone")
    private WebElement timeZoneSelect;

    @FindBy(id="driver_odometer")
    private WebElement odometerSelect;

    @FindBy(id="driver_restBreak")
    private WebElement restBreakSelect;

    @FindBy(id="driver_logIncrement")
    private WebElement logIncrementSelect;

    @FindBy(id="driver_cargoType")
    private WebElement cargoTypeSelect;

    @FindBy(xpath = "//*[@onclick='updateDriverRules()']")
    private WebElement buttonSaveDriverInfo;

    @FindBy(xpath = "//*[@id='soundNotificaiton']//..//*[text()='On']")
    private WebElement soundCheckOn;

    @FindBy(xpath = "//*[@id='soundNotificaiton']//..//*[text()='Off']")
    private WebElement soundCheckOff;

    @FindBy(xpath = "//*[@id='newNotificaitonsBox']//..//*[text()='On']")
    private WebElement notificationCheckOn;

    @FindBy(xpath = "//*[@id='newNotificaitonsBox']//..//*[text()='Off']")
    private WebElement notificationCheckOff;

    @FindBy(xpath = "//*[@id='showScoreCard']//..//*[text()='On']")
    private WebElement scoreCheckOn;

    @FindBy(xpath = "//*[@id='showScoreCard']//..//*[text()='Off']")
    private WebElement scoreCheckOff;

    @FindBy(xpath = "//*[@id='locationIconWarning']//..//*[text()='On']")
    private WebElement iconCheckOn;

    @FindBy(xpath = "//*[@id='locationIconWarning']//..//*[text()='Off']")
    private WebElement iconCheckOff;

//    FLEET INFO
    @FindBy(id="fleet_usdot")
    private WebElement fleetUsdot;

    @FindBy(id="fleet_name")
    private WebElement fleetName;

    @FindBy(id="fleet_ein")
    private WebElement fleetEIN;

    @FindBy(id="fleet_state")
    private WebElement fleetState;

    @FindBy(id="fleet_jur")
    private WebElement fleetJurisdiction;

    @FindBy(id="fleet_city")
    private WebElement fleetCity;

    @FindBy(id="fleet_street")
    private WebElement fleetStreet;

    @FindBy(id="fleet_zip")
    private WebElement fleetZip;

    @FindBy(id="fleet_size")
    private WebElement fleetSize;

    @FindBy(id="fleet_cycle")
    private WebElement fleetCycle;

    @FindBy(id="fleet_tz")
    private WebElement fleetTimeZone;

    @FindBy(xpath = "//*[@id='agricultureDeliveries']//..//*[text()='On']")
    private WebElement possibleAgricultureOn;

    @FindBy(xpath = "//*[@id='agricultureDeliveries']//..//*[text()='Off']")
    private WebElement possibleAgricultureOff;

    @FindBy(id="updateFleetDataButton")
    private WebElement saveFleetInfoButton;



//ACCOUNT INFO
    public void clickOnAccountSetting(){actionsWithOurElements.clickOnElement(accountSetting);}
    public void clickOnFleetSettings(){actionsWithOurElements.clickOnElement(fleetSettingsPage);}
    public void enterFirstName(String firsNameInput){actionsWithOurElements.clearAndEnterTextToElement(firstName, firsNameInput);}
    public void enterLastName(String lastNameInput){actionsWithOurElements.clearAndEnterTextToElement(lastName, lastNameInput);}
    public void enterPhone(String phoneInput){actionsWithOurElements.clearAndEnterTextToElement(phoneNumber, phoneInput);}
    public void clickSaveAccountInfo(){actionsWithOurElements.clickOnElement(buttonSaveAccountInfo);}

//    DRIVER INFO
    public void setCycle(String cycleValue, String cycleTypeValue){
        if (cycleValue.equals(cycleTypeValue)){
            String tempValue = Integer.toString(Math.abs(Integer.parseInt(cycleTypeValue) - 1));
            actionsWithOurElements.selectValueInDropDown(cycleSelect, tempValue);
            logger.info("Cycle set " + tempValue);
        } else actionsWithOurElements.selectValueInDropDown(cycleSelect, cycleTypeValue);
        logger.info("Cycle set " + cycleTypeValue);
    }
    public void setTimeZone(String timeZoneValue, String timeZoneRandomValue) {
        if (timeZoneValue.equals(timeZoneRandomValue)) {
            String tempValue = Integer.toString(Math.abs(Integer.parseInt(timeZoneRandomValue) - 1));
            actionsWithOurElements.selectValueInDropDown(timeZoneSelect, tempValue);
            logger.info("Time zone set " + tempValue);
        } else actionsWithOurElements.selectValueInDropDown(timeZoneSelect, timeZoneRandomValue);
        logger.info("Time zone set " + timeZoneRandomValue);
    }
    public void setOdometer(String odometerValue){
        if (odometerValue.equals("0")){
            String tempValue = "1";
        actionsWithOurElements.selectValueInDropDown(odometerSelect, tempValue);
        logger.info("Odometer set '1 = km' ");
        } else if (odometerValue.equals("1")){
            String tempValue = "0";
            actionsWithOurElements.selectValueInDropDown(odometerSelect, tempValue);
            logger.info("Odometer set '0 = miles' ");
        } else Assert.fail("Odometer value incorrect");
    }
    public void setRestBreak(String restBreakValue){
        if (restBreakValue.equals("0")){
            String tempValue = "1";
            actionsWithOurElements.selectValueInDropDown(restBreakSelect, tempValue);
            logger.info("Rest Break set '1 = 30 minute rest break required' ");
        } else if (restBreakValue.equals("1")){
            String tempValue = "0";
            actionsWithOurElements.selectValueInDropDown(restBreakSelect, tempValue);
            logger.info("Rest Break set '0 = No Rest Break Required' ");
        } else Assert.fail("Rest Break value incorrect");
    }
    public void setAppLog(String appLogValue){
        if (appLogValue.equals("0") || appLogValue.equals("")){
            String tempValue = "1";
            actionsWithOurElements.selectValueInDropDown(logIncrementSelect, tempValue);
            logger.info("AppLog set '1 = 1m' ");
        } else if (appLogValue.equals("1")){
            String tempValue = "0";
            actionsWithOurElements.selectValueInDropDown(logIncrementSelect, tempValue);
            logger.info("AppLog set '0 = 15m' ");
        } else Assert.fail("AppLog value incorrect");
    }
    public void setCargoType(String cargoType, String cargoTypeValue){
        if (cargoType.equals(cargoTypeValue)){
            String tempValue = Integer.toString(Math.abs(Integer.parseInt(cargoTypeValue) - 1));
            actionsWithOurElements.selectValueInDropDown(cargoTypeSelect, tempValue);
            logger.info("CargoType set " + tempValue);
        } else actionsWithOurElements.selectValueInDropDown(cargoTypeSelect, cargoTypeValue);
        logger.info("CargoType set " + cargoTypeValue);
    }
    public void clickSaveDriverInfo(){actionsWithOurElements.clickOnElement(buttonSaveDriverInfo);}

//    DASHBOARD SETTINGS
    public void setSoundNotification(String checkBoxValue){
        if (checkBoxValue.equals("0") || checkBoxValue.equals("")){
            actionsWithOurElements.clickJsOnElement(soundCheckOn);
        } else if(checkBoxValue.equals("1")){
            actionsWithOurElements.clickJsOnElement(soundCheckOff);
        } else {
            logger.error("Sound notification failed");
            Assert.fail("Cannot work with element");
        }
    }
    public void setNotificationBox(String checkBoxValue){
        if (checkBoxValue.equals("0") || checkBoxValue.equals("")){
            actionsWithOurElements.clickJsOnElement(notificationCheckOn);
        } else if(checkBoxValue.equals("1")){
            actionsWithOurElements.clickJsOnElement(notificationCheckOff);
        } else {
            logger.error("Notification box failed");
            Assert.fail("Cannot work with element");
        }
    }
    public void setScoreCard(String checkBoxValue){
        if (checkBoxValue.equals("0") || checkBoxValue.equals("")){
            actionsWithOurElements.clickJsOnElement(scoreCheckOn);
        } else if(checkBoxValue.equals("1")){
            actionsWithOurElements.clickJsOnElement(scoreCheckOff);
        } else {
            logger.error("Score Card failed");
            Assert.fail("Cannot work with element");
        }
    }
    public void setCoordinatesIcon(String checkBoxValue){
        if (checkBoxValue.equals("0") || checkBoxValue.equals("")){
            actionsWithOurElements.clickJsOnElement(iconCheckOn);
        } else if(checkBoxValue.equals("1")){
            actionsWithOurElements.clickJsOnElement(iconCheckOff);
        } else {
            logger.error("Coordinate Icon failed");
            Assert.fail("Cannot work with element");
        }
    }

//    FLEET INFO
    public void enterUsdot(String usdot){
        actionsWithOurElements.clearElement(fleetUsdot);
        actionsWithOurElements.enterTextOnElementJs(".//*[@id='fleet_usdot']", usdot);
    }
    public void enterFleetName(String name){
        actionsWithOurElements.clearAndEnterTextToElement(fleetName, name);
    }
    public void enterFleetEIN(String ein){
        actionsWithOurElements.clearElement(fleetEIN);
        waitABit(3);
        actionsWithOurElements.enterTextOnElementJs(".//*[@id='fleet_ein']", ein);
//        actionsWithOurElements.clearAndEnterTextToElement(fleetEIN, ein);
    }
    public void setFleetState(String stateRandomValue) {
        actionsWithOurElements.selectValueInDropDown(fleetState, stateRandomValue);
        logger.info("Fleet state set " + stateRandomValue);
    }
    public void enterFleetCity(String city){
        actionsWithOurElements.clearAndEnterTextToElement(fleetCity, city);
    }
    public void enterFleetStreet(String street){
        actionsWithOurElements.clearAndEnterTextToElement(fleetStreet, street);
    }
    public void enterFleetZip(String zip){
        actionsWithOurElements.clearAndEnterTextToElement(fleetZip, zip);
    }
    public void enterFleetSize(String size){
        actionsWithOurElements.clearAndEnterTextToElement(fleetSize, size);
    }
    public void setFleetCycle(String cycleRandomValue){
        actionsWithOurElements.selectValueInDropDown(fleetCycle, cycleRandomValue);
        logger.info("Cycle Set " + cycleRandomValue);
    }
    public void setFleetTimeZone(String timeZoneRandomValue) {
       actionsWithOurElements.selectValueInDropDown(fleetTimeZone, timeZoneRandomValue);
       logger.info("Time zone set " + timeZoneRandomValue);
    }
    public void moveSliderAobrd(int value){
        actionsWithOurElements.sliderMove("//*[@id='aobrdMPH']", value);
    }
    public void checkAgricultureDelivery(String checkBoxValue){
        if (checkBoxValue.equals("0") || checkBoxValue.equals("")){
            actionsWithOurElements.clickJsOnElement(possibleAgricultureOn);
        } else if(checkBoxValue.equals("1")){
            actionsWithOurElements.clickJsOnElement(possibleAgricultureOff);
        } else {
            logger.error("Agriculture Delivery failed");
            Assert.fail("Cannot work with element");
        }
    }
    public void clickOnSaveFleetInfoButton(){
        actionsWithOurElements.clickOnElement(saveFleetInfoButton);
    }



}
