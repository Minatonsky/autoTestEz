package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static libs.Utils.waitABit;

public class SettingsPage extends ParentPage {

    public SettingsPage(WebDriver webDriver) {
        super(webDriver, "/dash/settings/account/");
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
    public void checkOnEngineScoreStatus(){
        actionsWithOurElements.clickJsOnElement(hideEngineScoreStatusOn);
    }
    public void checkOffEngineScoreStatus(){
        actionsWithOurElements.clickJsOnElement(hideEngineScoreStatusOff);
    }
    public void checkOnYardMode(){
        actionsWithOurElements.clickJsOnElement(yardModeCheckOn);
    }
    public void checkOffYardMode(){
        actionsWithOurElements.clickJsOnElement(yardModeCheckOff);
    }
    public void checkOnConveyance(){
        actionsWithOurElements.clickJsOnElement(conveyanceCheckOn);
    }
    public void checkOffConveyance(){
        actionsWithOurElements.clickJsOnElement(conveyanceCheckOff);
    }
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
    public void checkOnSmsCheck(){
        actionsWithOurElements.clickJsOnElement(smsCheckOn);
    }
    public void checkOffSmsCheck(){
        actionsWithOurElements.clickJsOnElement(smsCheckOff);
    }

//  ADMINISTRATIVE
    public void enterDateMedCard(String medCard) {actionsWithOurElements.clearAndEnterTextToElement(medCardInput, medCard); }
    public void enterDateBirth(String dateBirth) {actionsWithOurElements.clearAndEnterTextToElement(dateBirthInput, dateBirth); }
    public void enterDateHire(String hireDate) {actionsWithOurElements.clearAndEnterTextToElement(hireDateInput, hireDate); }
    public void enterDateTerminate(String terminateDate) {actionsWithOurElements.clearAndEnterTextToElement(terminateDateInput, terminateDate); }
    public void enterDateNotice(String pullNotice) {actionsWithOurElements.clearAndEnterTextToElement(pullNoticeInput, pullNotice); }
    public void checkOnHazMat(){
        actionsWithOurElements.clickJsOnElement(hazMatCheckOn);
    }
    public void checkOffHazMat(){
        actionsWithOurElements.clickJsOnElement(hazMatCheckOff);
    }
    public void checkOnInsurance(){
        actionsWithOurElements.clickJsOnElement(InsuranceCheckOn);
    }
    public void checkOffInsurance(){
        actionsWithOurElements.clickJsOnElement(InsuranceCheckOff);
    }
    public void checkOnTanker(){
        actionsWithOurElements.clickJsOnElement(TankerCheckOn);
    }
    public void checkOffTanker(){
        actionsWithOurElements.clickJsOnElement(TankerCheckOff);
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

//     METHODS FOR CHECK BOXES
    public void checkEngineScoreStatus(String checkBoxValue){
        if (checkBoxValue.equals("0") || checkBoxValue.equals("")){
            checkOnEngineScoreStatus();
        } else if(checkBoxValue.equals("1")){
            checkOffEngineScoreStatus();
        } else {
            logger.error("Engine Score status failed");
            Assert.fail("Cannot work with element");
        }
    }
    public void checkYardMode(String checkBoxValue){
        if (checkBoxValue.equals("0") || checkBoxValue.equals("")){
            checkOnYardMode();
        } else if(checkBoxValue.equals("1")){
            checkOffYardMode();
        } else {
            logger.error("Yard Mode failed");
            Assert.fail("Cannot work with element");
        }
    }
    public void checkConveyance(String checkBoxValue){
        if (checkBoxValue.equals("0") || checkBoxValue.equals("")){
            checkOnConveyance();
        } else if(checkBoxValue.equals("1")){
            checkOffConveyance();
        } else {
            logger.error("Conveyance Mode failed");
            Assert.fail("Cannot work with element");
        }
    }
    public void checkSmsCheck(String checkBoxValue){
        if (checkBoxValue.equals("0") || checkBoxValue.equals("")){
            checkOnSmsCheck();
        } else if(checkBoxValue.equals("1")){
            checkOffSmsCheck();
        } else {
            logger.error("SMS failed");
            Assert.fail("Cannot work with element");
        }
    }
    public void checkHazMat(String checkBoxValue){
        if (checkBoxValue.equals("0") || checkBoxValue.equals("")){
            checkOnHazMat();
        } else if(checkBoxValue.equals("1")){
            checkOffHazMat();
        } else {
            logger.error("HazMat failed");
            Assert.fail("Cannot work with element");
        }
    }
    public void checkInsurance(String checkBoxValue){
        if (checkBoxValue.equals("0") || checkBoxValue.equals("")){
            checkOnInsurance();
        } else if(checkBoxValue.equals("1")){
            checkOffInsurance();
        } else {
            logger.error("Insurance failed");
            Assert.fail("Cannot work with element");
        }
    }
    public void checkTanker(String checkBoxValue){
        if (checkBoxValue.equals("0") || checkBoxValue.equals("")){
            checkOnTanker();
        } else if(checkBoxValue.equals("1")){
            checkOffTanker();
        } else {
            logger.error("Tanker failed");
            Assert.fail("Cannot work with element");
        }
    }

}



