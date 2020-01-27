package pagesLocal;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SettingsLocalSitePage extends ParentLocalSitePage {

    public SettingsLocalSitePage(WebDriver webDriver) {
        super(webDriver, "/dash/settings/account/");
    }


    @FindBy(xpath = ".//a[@href='/dash/settings/driver_settings']")
    private WebElement driverSettings;

    //    GENERAL
    @FindBy(name = "ssn")
    private WebElement ssnInput;

    @FindBy(name = "ein")
    private WebElement einInput;

    @FindBy(css = "#driver_settings > div:nth-child(1) > div:nth-child(4) > div > label > i")
    private WebElement hideEngineScoreStatus;

    @FindBy(css = "#driver_settings > div:nth-child(1) > div:nth-child(5) > div > label > i")
    private WebElement yardModeCheck;

    @FindBy(css = "#driver_settings > div:nth-child(1) > div:nth-child(6) > div > label > i")
    private WebElement conveyanceCheck;

    @FindBy(xpath = "//*[@class='vue-slider-rail']")
    private WebElement sliderAobrd;

    //    CONTACT INFO
    @FindBy(name = "state_id")
    private WebElement state_id;

    @FindBy(name = "driver_city")
    private WebElement driver_city;

    @FindBy(name = "driver_address")
    private WebElement driver_address;

    @FindBy(name = "phone")
    private WebElement phone;

    @FindBy(css = "#driver_settings > div:nth-child(4) > div:nth-child(6) > div > label > i")
    private WebElement smsCheck;


//  ADMINISTRATIVE

    @FindBy(xpath = "//label[@for='medCard']/..//input")
    private WebElement medCardInput;

    @FindBy(xpath = "//label[@for='dateBirth']/..//input")
    private WebElement dateBirthInput;

    @FindBy(xpath = "//label[@for='HireDate']/..//input")
    private WebElement hireDateInput;

    @FindBy(xpath = "//label[@for='TerminateDate']/..//input")
    private WebElement terminateDateInput;

    @FindBy(xpath = "//label[@for='PullNotice']/..//input")
    private WebElement pullNoticeInput;

    @FindBy(css = "#driver_settings > div:nth-child(2) > div:nth-child(7) > div > label > i")
    private WebElement hazMatCheck;

    @FindBy(css = "#driver_settings > div:nth-child(2) > div:nth-child(8) > div > label > i")
    private WebElement InsuranceCheck;

    @FindBy(css = "#driver_settings > div:nth-child(2) > div:nth-child(9) > div > label > i")
    private WebElement TankerCheck;

    @FindBy(id = "Notes")
    private WebElement notes;


//  DRIVER'S LICENSE

    @FindBy(xpath = "//span[text()='Number']/../input")
    private WebElement numberDlInput;

    @FindBy(xpath = "//span[text()='Country']/../select")
    private WebElement countryDlValue;

    @FindBy(xpath = "//*[@class='form-input form-group']//span[text()='State']/../select[@name]")
    private WebElement stateDlValue;

    @FindBy(xpath = "//span[text()='Expiration']/..//input")
    private WebElement expirationInput;

    @FindBy(css = ".btn")
    private WebElement buttonSave;


    public void clickOnDriverSettings() {
        actionsWithOurElements.clickOnElement(driverSettings);
    }

    //    GENERAL
    public void enterSsn(String ssn) {actionsWithOurElements.clearAndEnterTextToElement(ssnInput, ssn); }
    public void enterEin(String ein) {actionsWithOurElements.clearAndEnterTextToElement(einInput, ein); }
    public void checkOnEngineScoreStatus(){
        actionsWithOurElements.jsClickOnElement(hideEngineScoreStatus);
    }
    public void checkOnYardMode(){
        actionsWithOurElements.jsClickOnElement(yardModeCheck);
    }
    public void checkOnConveyance(){
        actionsWithOurElements.jsClickOnElement(conveyanceCheck);
    }
    public void moveSliderAobrd(int value){
        actionsWithOurElements.sliderMove("//*[@class='vue-slider-rail']", value);
    }

    //    CONTACT INFO
    public void selectState(String driverState) {actionsWithOurElements.selectValueInDropDown(state_id, driverState);}
    public void enterDriverCity(String driverCity) {actionsWithOurElements.clearAndEnterTextToElement(driver_city, driverCity); }
    public void enterDriverAddress(String driverAddress) {actionsWithOurElements.clearAndEnterTextToElement(driver_address, driverAddress); }
    public void enterPhone(String driverPhone) {actionsWithOurElements.clearAndEnterTextToElement(phone, driverPhone); }
    public void checkOnSmsCheck(){
        actionsWithOurElements.jsClickOnElement(smsCheck);
    }


//  ADMINISTRATIVE

    public void enterDateMedCard(String medCard) {actionsWithOurElements.clearAndEnterTextToElement(medCardInput, medCard); }
    public void enterDateBirth(String dateBirth) {actionsWithOurElements.clearAndEnterTextToElement(dateBirthInput, dateBirth); }
    public void enterDateHire(String hireDate) {actionsWithOurElements.clearAndEnterTextToElement(hireDateInput, hireDate); }
    public void enterDateTerminate(String terminateDate) {actionsWithOurElements.clearAndEnterTextToElement(terminateDateInput, terminateDate); }
    public void enterDateNotice(String pullNotice) {actionsWithOurElements.clearAndEnterTextToElement(pullNoticeInput, pullNotice); }
    public void checkOnHazMat(){
        actionsWithOurElements.jsClickOnElement(hazMatCheck);
    }
    public void checkOnInsurance(){
        actionsWithOurElements.jsClickOnElement(InsuranceCheck);
    }
    public void checkOnTanker(){
        actionsWithOurElements.jsClickOnElement(TankerCheck);
    }
    public void enterNote(String driverNote) {actionsWithOurElements.clearAndEnterTextToElement(notes, driverNote); }

    //    DRIVER'S LICENSE
    public void enterNumberDl(String driverNumberL) {actionsWithOurElements.clearAndEnterTextToElement(numberDlInput, driverNumberL); }
    public void selectCountry(String driverCountry) {actionsWithOurElements.selectValueInDropDown(countryDlValue, driverCountry);}
    public void selectStateDl(String driverStateDl) {actionsWithOurElements.selectValueInDropDown(stateDlValue, driverStateDl);}
    public void enterExpirationDl(String driverExpirationL) {actionsWithOurElements.clearAndEnterTextToElement(expirationInput, driverExpirationL); }
    public void clickOnBlankArea(){actionsWithOurElements.clickOnBlankArea();}


    public void clickOnSave(){
        actionsWithOurElements.scrollByVisibleElement(buttonSave);
        actionsWithOurElements.clickOnElement(buttonSave);
    }

}
