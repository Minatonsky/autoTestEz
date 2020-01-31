package pagesLocal;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountSettingsLocalSitePage extends ParentLocalSitePage {
    public AccountSettingsLocalSitePage(WebDriver webDriver) {
        super(webDriver, "/dash/settings/account/");
    }
    @FindBy(xpath = ".//a[@href='/dash/settings/account/']")
    private WebElement accountSetting;

//ACCOUNT INFO
    @FindBy(id="firstName")
    private WebElement firstName;

    @FindBy(id="lastName")
    private WebElement lastName;

    @FindBy(id="phoneNumber")
    private WebElement phoneNumber;

    @FindBy(id="userEmail")
    private WebElement userEmail;

    @FindBy(id="user_role")
    private WebElement userRole;

    @FindBy(id="userPassword")
    private WebElement userPassword;

    @FindBy(id="repeatPassword")
    private WebElement repeatPassword;

    @FindBy(xpath = ".//*[@class='col-title' and text()='ACCOUNT INFO']/..//button[@class='btn btn-transparent' and text()='Save']")
    private WebElement buttonSaveAccountInfo;

//    DRIVER INFO

    @FindBy(id="cycle_id")
    private WebElement cycleSelect;

    @FindBy(id="time_zone_id")
    private WebElement timeZoneSelect;

    @FindBy(id="odometer_id")
    private WebElement odometerSelect;

    @FindBy(id="rest_break_id")
    private WebElement restBreakSelect;

    @FindBy(id="log_increment_id")
    private WebElement logIncrementSelect;

    @FindBy(id="cargo_type_id")
    private WebElement cargoTypeSelect;

    @FindBy(xpath = ".//*[@class='col-title' and text()='DRIVER INFO']/..//button[@class='btn btn-transparent' and text()='Save']")
    private WebElement buttonSaveDriverInfo;

    @FindBy(xpath = "//label[@for='soundCheck']/..//*[text()='on']")
    private WebElement soundCheckOn;

    @FindBy(xpath = "//label[@for='soundCheck']/..//*[text()='off']")
    private WebElement soundCheckOff;

    @FindBy(xpath = "//label[@for='notificationCheck']/..//*[text()='on']")
    private WebElement notificationCheckOn;

    @FindBy(xpath = "//label[@for='notificationCheck']/..//*[text()='off']")
    private WebElement notificationCheckOff;

    @FindBy(xpath = "//label[@for='ScoreCheck']/..//*[text()='on']")
    private WebElement scoreCheckOn;

    @FindBy(xpath = "//label[@for='ScoreCheck']/..//*[text()='off']")
    private WebElement scoreCheckOff;

    @FindBy(xpath = "//label[@for='IconCheck']/..//*[text()='on']")
    private WebElement iconCheckOn;

    @FindBy(xpath = "//label[@for='IconCheck']/..//*[text()='off']")
    private WebElement iconCheckOff;

//ACCOUNT INFO
    public void clickOnAccountSetting(){actionsWithOurElements.clickOnElement(accountSetting);}
    public void enterFirstName(String firsNameInput){actionsWithOurElements.clearAndEnterTextToElement(firstName, firsNameInput);}
    public void enterLastName(String lastNameInput){actionsWithOurElements.clearAndEnterTextToElement(lastName, lastNameInput);}
    public void enterPhone(String phoneInput){actionsWithOurElements.clearAndEnterTextToElement(phoneNumber, phoneInput);}
    public void clickSaveAccountInfo(){actionsWithOurElements.clickOnElement(buttonSaveAccountInfo);}
//    DRIVER INFO
    public void setCycle(String cycleValue){actionsWithOurElements.selectValueInDropDown(cycleSelect, cycleValue);}
    public void setTimeZone(String timeZoneValue){actionsWithOurElements.selectValueInDropDown(timeZoneSelect, timeZoneValue);}
    public void setOdometer(String odometerValue){
        if (odometerValue.equals("0")){
            String tempValue = "1";
        actionsWithOurElements.selectValueInDropDown(odometerSelect, tempValue);
        logger.info("Odometer set '1' ");
        } else if (odometerValue.equals("1")){
            String tempValue = "0";
            actionsWithOurElements.selectValueInDropDown(odometerSelect, tempValue);
            logger.info("Odometer set '0' ");
        } else Assert.fail("Odometer value incorrect");
    }
    public void setRestBreak(String restBreakValue){actionsWithOurElements.selectValueInDropDown(restBreakSelect, restBreakValue);}
    public void setAppLog(String appLogValue){actionsWithOurElements.selectValueInDropDown(logIncrementSelect, appLogValue);}
    public void setCargoType(String cargoType){actionsWithOurElements.selectValueInDropDown(cargoTypeSelect, cargoType);}
    public void clickSaveDriverInfo(){actionsWithOurElements.clickOnElement(buttonSaveDriverInfo);}

//    DASHBOARD SETTINGS
    public void clickOnSoundNotification(){actionsWithOurElements.clickJsOnElement(soundCheckOn);}
    public void clickOffSoundNotification(){actionsWithOurElements.clickJsOnElement(soundCheckOff);}
    public void clickOnNotificationBox(){actionsWithOurElements.clickJsOnElement(notificationCheckOn);}
    public void clickOffNotificationBox(){actionsWithOurElements.clickJsOnElement(notificationCheckOff);}
    public void clickOnScoreCard(){actionsWithOurElements.clickJsOnElement(scoreCheckOn);}
    public void clickOffScoreCard(){actionsWithOurElements.clickJsOnElement(scoreCheckOff);}
    public void clickOnCoordinates(){actionsWithOurElements.clickJsOnElement(iconCheckOn);}
    public void clickOffCoordinates(){actionsWithOurElements.clickJsOnElement(iconCheckOff);}


    public void setSoundNotification(String checkBoxValue){
        if (checkBoxValue.equals("false") || checkBoxValue.equals("")){
            clickOnSoundNotification();
        } else if(checkBoxValue.equals("true")){
            clickOffSoundNotification();
        } else {
            logger.error("Sound notification failed");
            Assert.fail("Cannot work with element");
        }
    }
    public void setNotificationBox(String checkBoxValue){
        if (checkBoxValue.equals("false") || checkBoxValue.equals("")){
            clickOnNotificationBox();
        } else if(checkBoxValue.equals("true")){
            clickOffNotificationBox();
        } else {
            logger.error("Notification box failed");
            Assert.fail("Cannot work with element");
        }
    }
    public void setScoreCard(String checkBoxValue){
        if (checkBoxValue.equals("false") || checkBoxValue.equals("")){
            clickOnScoreCard();
        } else if(checkBoxValue.equals("true")){
            clickOffScoreCard();
        } else {
            logger.error("Score Card failed");
            Assert.fail("Cannot work with element");
        }
    }
    public void setCoordinatesIcon(String checkBoxValue){
        if (checkBoxValue.equals("false") || checkBoxValue.equals("")){
            clickOnCoordinates();
        } else if(checkBoxValue.equals("true")){
            clickOffCoordinates();
        } else {
            logger.error("Coordinate Icon failed");
            Assert.fail("Cannot work with element");
        }
    }


}
