package pages;

import libs.UtilsForDB;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountSettingsPage extends ParentPage {
    public AccountSettingsPage(WebDriver webDriver, UtilsForDB utilsForDB) {
        super(webDriver, "/dash/settings/account/", utilsForDB);
    }
    @FindBy(xpath = ".//a[@href='/dash/settings/account/']")
    private WebElement accountSetting;

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

//ACCOUNT INFO
    public void clickOnAccountSetting(){actionsWithOurElements.clickOnElement(accountSetting);}
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


}
