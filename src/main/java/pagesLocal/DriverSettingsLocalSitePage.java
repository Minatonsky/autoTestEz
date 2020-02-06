package pagesLocal;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DriverSettingsLocalSitePage extends ParentLocalSitePage {

    public DriverSettingsLocalSitePage(WebDriver webDriver) {
        super(webDriver, "/dash/settings/account/");
    }


    @FindBy(xpath = ".//a[@href='/dash/settings/driver_settings']")
    private WebElement driverSettings;

    //    GENERAL
    @FindBy(name = "ssn")
    private WebElement ssnInput;

    @FindBy(name = "ein")
    private WebElement einInput;

    @FindBy(xpath = "//label[@for='hideEngineScoreStatus']/..//*[text()='on']")
    private WebElement hideEngineScoreStatusOn;

    @FindBy(xpath = "//label[@for='hideEngineScoreStatus']/..//*[text()='off']")
    private WebElement hideEngineScoreStatusOff;

    @FindBy(xpath = "//label[@for='yardModeCheck']/..//*[text()='on']")
    private WebElement yardModeCheckOn;

    @FindBy(xpath = "//label[@for='yardModeCheck']/..//*[text()='off']")
    private WebElement yardModeCheckOff;

    @FindBy(xpath = "//label[@for='ConveyanceCheck']/..//*[text()='on']")
    private WebElement conveyanceCheckOn;

    @FindBy(xpath = "//label[@for='ConveyanceCheck']/..//*[text()='off']")
    private WebElement conveyanceCheckOff;

    @FindBy(xpath = "//*[@class='vue-slider-rail']")
    private WebElement sliderAobrd;

    @FindBy(id = "ezSimple")
    private WebElement ezSimpleType;

    @FindBy(id = "ezSmart")
    private WebElement ezSmartType;

    @FindBy(id = "ezHard")
    private WebElement ezHardType;

    //    CONTACT INFO
    @FindBy(name = "state_id")
    private WebElement state_id;

    @FindBy(name = "driver_city")
    private WebElement driver_city;

    @FindBy(name = "driver_address")
    private WebElement driver_address;

    @FindBy(name = "phone")
    private WebElement phone;

    @FindBy(xpath = "//label[@for='smsCheck']/..//*[text()='on']")
    private WebElement smsCheckOn;

    @FindBy(xpath = "//label[@for='smsCheck']/..//*[text()='off']")
    private WebElement smsCheckOff;


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

    @FindBy(xpath = "//label[@for='HazMatCheck']/..//*[text()='on']")
    private WebElement hazMatCheckOn;

    @FindBy(xpath = "//label[@for='HazMatCheck']/..//*[text()='off']")
    private WebElement hazMatCheckOff;

    @FindBy(xpath = "//label[@for='InsuranceCheck']/..//*[text()='on']")
    private WebElement InsuranceCheckOn;

    @FindBy(xpath = "//label[@for='InsuranceCheck']/..//*[text()='off']")
    private WebElement InsuranceCheckOff;

    @FindBy(xpath = "//label[@for='TankerCheck']/..//*[text()='on']")
    private WebElement TankerCheckOn;

    @FindBy(xpath = "//label[@for='TankerCheck']/..//*[text()='off']")
    private WebElement TankerCheckOff;

    @FindBy(id = "Notes")
    private WebElement notes;


//  DRIVER'S LICENSE

    @FindBy(xpath = "//span[text()='Number']/../input")
    private WebElement numberDlInput;

    @FindBy(xpath = "//span[text()='Country']/../select")
    private WebElement countryDlValue;

    @FindBy(xpath = "//*[@class='form-input form-group form-group__input-full']//span[text()='State']/../select")
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
        actionsWithOurElements.sliderMove("//*[@class='vue-slider-rail']", value);
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
