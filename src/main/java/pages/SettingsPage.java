package pages;

import libs.Database;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static libs.Utils.waitABit;

public class SettingsPage extends ParentPage {

    public SettingsPage(WebDriver webDriver, Database dBMySQL) {
        super(webDriver, "/dash/settings/account/", dBMySQL);
    }

    @FindBy(xpath = ".//a[@href='/dash/settings/driver_settings/']")
    private WebElement driverSettings;

//    GENERAL
    @FindBy(id = "dr_ssn")
    private WebElement ssnInput;

    @FindBy(id = "dr_ein")
    private WebElement einInput;

    @FindBy(xpath = "//*[@id='hideEngineStatuses']//*[text()='On']")
    private WebElement hideEngineScoreStatusOn;

    @FindBy(xpath = "//*[@id='hideEngineStatuses']//*[text()='Off']")
    private WebElement hideEngineScoreStatusOff;

    @FindBy(xpath = "//*[@id='dr_yardMode']//*[text()='On']")
    private WebElement yardModeCheckOn;

    @FindBy(xpath = "//*[@id='dr_yardMode']//*[text()='Off']")
    private WebElement yardModeCheckOff;

    @FindBy(xpath = "//*[@id='dr_conveyanceMode']//*[text()='On']")
    private WebElement conveyanceCheckOn;

    @FindBy(xpath = "//*[@id='dr_conveyanceMode']//*[text()='Off']")
    private WebElement conveyanceCheckOff;

    @FindBy(id = "soloAobrdMPH")
    private WebElement sliderAobrd;

    @FindBy(id = "ezSimple")
    private WebElement ezSimpleType;

    @FindBy(id = "ezSmart")
    private WebElement ezSmartType;

    @FindBy(id = "ezHard")
    private WebElement ezHardType;

//    CONTACT INFO
    @FindBy(id = "dr_cont_st")
    private WebElement state_id;

    @FindBy(id = "dr_cont_city")
    private WebElement driver_city;

    @FindBy(id = "dr_cont_addr")
    private WebElement driver_address;

    @FindBy(id = "dr_cont_phone")
    private WebElement phone;

    @FindBy(xpath = "//*[@id='dr_cont_sms']//*[text()='On']")
    private WebElement smsCheckOn;

    @FindBy(xpath = "//*[@id='dr_cont_sms']//*[text()='Off']")
    private WebElement smsCheckOff;

//  ADMINISTRATIVE
    @FindBy(id = "dr_med")
    private WebElement medCardInput;

    @FindBy(id = "dr_birth")
    private WebElement dateBirthInput;

    @FindBy(id = "dr_hire")
    private WebElement hireDateInput;

    @FindBy(id = "dr_term_date")
    private WebElement terminateDateInput;

    @FindBy(id = "dr_pull")
    private WebElement pullNoticeInput;

    @FindBy(xpath = "//*[@id='dr_hazmat']//*[text()='On']")
    private WebElement hazMatCheckOn;

    @FindBy(xpath = "//*[@id='dr_hazmat']//*[text()='Off']")
    private WebElement hazMatCheckOff;

    @FindBy(xpath = "//*[@id='dr_insur']//*[text()='On']")
    private WebElement InsuranceCheckOn;

    @FindBy(xpath = "//*[@id='dr_insur']//*[text()='Off']")
    private WebElement InsuranceCheckOff;

    @FindBy(xpath = "//*[@id='dr_tank']//*[text()='On']")
    private WebElement TankerCheckOn;

    @FindBy(xpath = "//*[@id='dr_tank']//*[text()='Off']")
    private WebElement TankerCheckOff;

    @FindBy(id = "dr_notes")
    private WebElement notes;

//  DRIVER'S LICENSE
    @FindBy(id = "dr_lic_num")
    private WebElement numberDlInput;

    @FindBy(id = "dr_lic_st")
    private WebElement stateDlValue;

    @FindBy(id = "dr_lic_exp")
    private WebElement expirationInput;

    @FindBy(id = "dr_lic_exp")
    private WebElement countryDlValue;

    @FindBy(xpath = "//*[text()='Save']")
    private WebElement buttonSave;

    public void goToDriverSettings() {waitABit(2);actionsWithOurElements.clickOnElement(driverSettings); }

//    GENERAL
    public void enterSsn(String ssn) {actionsWithOurElements.enterTextToElement(ssnInput, ssn); }
    public void enterEin(String ein) {actionsWithOurElements.enterTextToElement(einInput, ein); }
    public void moveSliderAobrd(int value){
        actionsWithOurElements.sliderMove("//*[@id='soloAobrdMPH']", value);
    }
    public void clickOnSmartScannerType(){actionsWithOurElements.clickJsOnElement(ezSmartType);}
    public void clickOnSimpleScannerType(){actionsWithOurElements.clickJsOnElement(ezSimpleType);}
    public void clickOnHardScannerType(){actionsWithOurElements.clickJsOnElement(ezHardType);}
    public void clickOnScannerType(String scannerType){
        if (scannerType.equals("0")){
            clickOnSimpleScannerType();
        } else if (scannerType.equals("1")){
            clickOnSmartScannerType();
        } else if(scannerType.equals("2")){
            clickOnHardScannerType();
        } else {
            logger.info("Scanner type was not clicked");
        }
    }

//    CONTACT INFO
    public void selectState(String driverState) {actionsWithOurElements.selectValueInDropDown(state_id, driverState);}
    public void enterDriverCity(String driverCity) {actionsWithOurElements.clearAndEnterTextToElement(driver_city, driverCity); }
    public void enterDriverAddress(String driverAddress) {actionsWithOurElements.clearAndEnterTextToElement(driver_address, driverAddress); }
    public void enterPhone(String driverPhone) {actionsWithOurElements.clearAndEnterTextToElement(phone, driverPhone); }

//  ADMINISTRATIVE
    public void enterDateMedCard(String medCard) {actionsWithOurElements.clearAndEnterTextToElement(medCardInput, medCard); }
    public void enterDateBirth(String dateBirth) {actionsWithOurElements.clearAndEnterTextToElement(dateBirthInput, dateBirth); }
    public void enterDateHire(String hireDate) {actionsWithOurElements.clearAndEnterTextToElement(hireDateInput, hireDate); }
    public void enterDateTerminate(String terminateDate) {actionsWithOurElements.clearAndEnterTextToElement(terminateDateInput, terminateDate); }
    public void enterDateNotice(String pullNotice) {actionsWithOurElements.clearAndEnterTextToElement(pullNoticeInput, pullNotice); }
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

//     METHODS FOR CHECK BOXES
    public void checkEngineScoreStatus(String checkBoxValue){
        if (checkBoxValue.equals("0") || checkBoxValue.equals("")){
            actionsWithOurElements.clickJsOnElement(hideEngineScoreStatusOn);
        } else if(checkBoxValue.equals("1")){
            actionsWithOurElements.clickJsOnElement(hideEngineScoreStatusOff);
        } else {
            logger.error("Engine Score status failed");
            Assert.fail("Cannot work with element");
        }
    }
    public void checkYardMode(String checkBoxValue){
        if (checkBoxValue.equals("0") || checkBoxValue.equals("")){
            actionsWithOurElements.clickJsOnElement(yardModeCheckOn);
        } else if(checkBoxValue.equals("1")){
            actionsWithOurElements.clickJsOnElement(yardModeCheckOff);
        } else {
            logger.error("Yard Mode failed");
            Assert.fail("Cannot work with element");
        }
    }
    public void checkConveyance(String checkBoxValue){
        if (checkBoxValue.equals("0") || checkBoxValue.equals("")){
            actionsWithOurElements.clickJsOnElement(conveyanceCheckOn);
        } else if(checkBoxValue.equals("1")){
            actionsWithOurElements.clickJsOnElement(conveyanceCheckOff);
        } else {
            logger.error("Conveyance Mode failed");
            Assert.fail("Cannot work with element");
        }
    }
    public void checkSmsCheck(String checkBoxValue){
        if (checkBoxValue.equals("0") || checkBoxValue.equals("")){
            actionsWithOurElements.clickJsOnElement(smsCheckOn);
        } else if(checkBoxValue.equals("1")){
            actionsWithOurElements.clickJsOnElement(smsCheckOff);
        } else {
            logger.error("SMS failed");
            Assert.fail("Cannot work with element");
        }
    }
    public void checkHazMat(String checkBoxValue){
        if (checkBoxValue.equals("0") || checkBoxValue.equals("")){
            actionsWithOurElements.clickJsOnElement(hazMatCheckOn);
        } else if(checkBoxValue.equals("1")){
            actionsWithOurElements.clickJsOnElement(hazMatCheckOff);
        } else {
            logger.error("HazMat failed");
            Assert.fail("Cannot work with element");
        }
    }
    public void checkInsurance(String checkBoxValue){
        if (checkBoxValue.equals("0") || checkBoxValue.equals("")){
            actionsWithOurElements.clickJsOnElement(InsuranceCheckOn);
        } else if(checkBoxValue.equals("1")){
            actionsWithOurElements.clickJsOnElement(InsuranceCheckOff);
        } else {
            logger.error("Insurance failed");
            Assert.fail("Cannot work with element");
        }
    }
    public void checkTanker(String checkBoxValue){
        if (checkBoxValue.equals("0") || checkBoxValue.equals("")){
            actionsWithOurElements.clickJsOnElement(TankerCheckOn);
        } else if(checkBoxValue.equals("1")){
            actionsWithOurElements.clickJsOnElement(TankerCheckOff);
        } else {
            logger.error("Tanker failed");
            Assert.fail("Cannot work with element");
        }
    }

}



