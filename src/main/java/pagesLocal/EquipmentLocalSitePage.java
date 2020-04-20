package pagesLocal;

import libs.UtilsForDB;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static libs.Utils.waitABit;

public class EquipmentLocalSitePage extends ParentLocalSitePage {
    public EquipmentLocalSitePage(WebDriver webDriver, UtilsForDB utilsForDB) {
        super(webDriver, "/dash/equipment/", utilsForDB);
    }

    @FindBy(xpath = ".//*[text() = 'ADD ']")
    private WebElement addButton;

    @FindBy(xpath = ".//button[text() = 'Unit']")
    private WebElement addTruckButton;

    @FindBy(xpath = "//button[text() = 'Trailer']")
    private WebElement addTrailerButton;

//GENERAL
    @FindBy(xpath = ".//*[@class='form-container__inputs form-container__form-section']//..//*[text()='Unit']//../input")
    private WebElement unitInput;

    @FindBy(xpath = ".//*[@class='form-container__inputs form-container__form-section']//..//*[text()='Owner']//../input")
    private WebElement ownerInput;

    @FindBy(xpath = ".//*[@class='form-container__inputs form-container__form-section']//..//*[text()='Year']//../input")
    private WebElement yearInput;

    @FindBy(xpath = ".//*[@class='popup-container__body']//..//input[@placeholder='Type']")
    private WebElement typeMenu;

    @FindBy(xpath = ".//*[@class='form-container__inputs form-container__form-section']//..//*[text()='VIN']//../input")
    private WebElement vinInput;

    @FindBy(xpath = ".//*[@class='form-container__inputs form-container__form-section']//..//*[text()='Plate']//../input")
    private WebElement plateInput;

    @FindBy(xpath = ".//*[@placeholder='State']")
    private WebElement stateValue;

//Parameters
    @FindBy(xpath = ".//*[text()='Tire Size']//../input")
    private WebElement tire_sizeInput;

    @FindBy(xpath = ".//*[text()='Length']//../input")
    private WebElement lengthInput;

    @FindBy(xpath = ".//*[contains(text(),'Gasoline')]")
    private WebElement fuelInput;

    @FindBy(xpath = ".//*[@class='form-container__inputs form-container__form-section']//..//*[text()='Axel']//../input")
    private WebElement axelInput;

    @FindBy(xpath = ".//*[@class='form-container__inputs form-container__form-section']//..//*[text()='Make']//../input")
    private WebElement makeInput;

    @FindBy(xpath = ".//*[@class='form-container__inputs form-container__form-section']//..//*[text()='Model']//../input")
    private WebElement modelInput;

    @FindBy(xpath = ".//*[text()='Gross Weight']//../input")
    private WebElement gross_weightInput;

    @FindBy(xpath = ".//*[text()='Unland Weight']//../input")
    private WebElement unland_weightInput;

//Others
    @FindBy(xpath = ".//*[@class='form-container__inputs form-container__form-section']//..//*[text()='Color']//../input")
    private WebElement colorInput;

    @FindBy(xpath = ".//*[@class='form-container__inputs form-container__form-section']//..//*[text()='NY Certificate']//../input")
    private WebElement ny_certInput;

    @FindBy(xpath = ".//*[text()='Inspection Due']//..//input[@name='date']")
    private WebElement inspection_dueInput;

    @FindBy(xpath = ".//*[text()='90 Day Exp']//..//input[@name='date']")
    private WebElement ninety_day_expInput;

    @FindBy(xpath = ".//*[text()='Pro Rate Exp']//..//input[@name='date']")
    private WebElement pro_rate_expInput;

    @FindBy(xpath = ".//*[@class='popup-container']//*[text()='Exp Date']//..//input[@name='date']")
    private WebElement exp_dateInput;

    @FindBy(xpath = ".//input[@type='checkbox']")
    private WebElement checkboxTruckState;

    @FindBy(xpath = ".//button[text()='Add']")
    private WebElement submitButton;

    @FindBy(xpath = ".//input[@name='name']")
    private WebElement truckNameHolder;

    @FindBy(xpath = ".//button[text()='Truck Settings ']")
    private WebElement editButton;

    @FindBy(xpath = ".//button[text()='Save']")
    private WebElement saveButton;

    @FindBy(xpath = ".//textarea")
    private WebElement textArea;

    @FindBy(id = "file_permit")
    private WebElement addFileReg;

    @FindBy(id = "file_permit_anual")
    private WebElement addFileInsp;
//reminders
    @FindBy(xpath = "//*[text()='Reminders']")
    private WebElement remindersButton;

    @FindBy(xpath = "//button[text()='Reminder by date']")
    private WebElement remindByDateButton;

    @FindBy(xpath = "//button[text()='Mile reminder']")
    private WebElement remindByMileButton;

    @FindBy(name = "name")
    private WebElement nameReminderInput;

    @FindBy(name = "reminder_date")
    private WebElement dateReminderInput;

    @FindBy(xpath = "//*[@class='add-box__chosen-by-date']//button[text()='Save']")
    private WebElement saveReminderButton;

    @FindBy(xpath = "//*[@class='trailer-popup-info__title' and text()='VEHICLE ID']/../*[@class='trailer-popup-info__desc']")
    private WebElement vehicleId;

    @FindBy(name = "term")
    private WebElement milesInput;


    public void clickOnAddButton(){actionsWithOurElements.clickOnElement(addButton);}
    public void clickOnAddTruckButton(){actionsWithOurElements.clickOnElement(addTruckButton);}
    public void clickOnAddTrailerButton(){actionsWithOurElements.clickOnElement(addTrailerButton);}
    public String getVehicleIdText(){return vehicleId.getText();}

    //    General
    public void enterUnitName(String unitName){actionsWithOurElements.enterTextToElement(unitInput, unitName);}
    public void enterOwner(String owner){actionsWithOurElements.enterTextToElement(ownerInput, owner);}
    public void enterYear(String year){actionsWithOurElements.enterTextToElement(yearInput, year);}

    public void selectType(String type){
        actionsWithOurElements.clickOnElement(typeMenu);
        waitABit(2);
        actionsWithOurElements.clickOnElement(".//li[@role='option']//../*[contains(text(),'" + type + "')]");
        logger.info("Type is: " + type);
    }
    public void enterVin(String vin){actionsWithOurElements.enterTextToElement(vinInput, vin);}
    public void enterPlate(String plate){actionsWithOurElements.enterTextToElement(plateInput, plate);}
    public void selectState(String state){
        actionsWithOurElements.clickOnElement(stateValue);
        waitABit(2);
        actionsWithOurElements.clickOnElement(".//li[@role='option']//../*[contains(text(),'" + state + "')]");
        logger.info("State is: " + state);
    }
//    Parameters
    public void enterTireSize(String tireSize){actionsWithOurElements.enterTextToElement(tire_sizeInput, tireSize);}
    public void enterLength(String length){actionsWithOurElements.enterTextToElement(lengthInput, length);}
    public void selectFuel(String fuel){
        actionsWithOurElements.clickOnElement(fuelInput);
        waitABit(2);
        actionsWithOurElements.clickOnElement(".//li[@role='option']//../*[contains(text(),'" + fuel + "')]");
        logger.info("Fuel is: " + fuel);
    }
    public void enterAxel(String axel){actionsWithOurElements.enterTextToElement(axelInput, axel);}
    public void enterMake(String make){actionsWithOurElements.enterTextToElement(makeInput, make);}
    public void enterModel(String model){actionsWithOurElements.enterTextToElement(modelInput, model);}
    public void enterGrossWeight(String grossWeight){actionsWithOurElements.enterTextToElement(gross_weightInput, grossWeight);}
    public void enterUnlandWeight(String unlandWeight){actionsWithOurElements.enterTextToElement(unland_weightInput, unlandWeight);}
//    Others
    public void enterColor(String color){actionsWithOurElements.enterTextToElement(colorInput, color);}
    public void enterNYCert(String nyCert){actionsWithOurElements.enterTextToElement(ny_certInput, nyCert);}
    public void enterInspectionDue(String inspectionDue){actionsWithOurElements.clearAndEnterTextToElement(inspection_dueInput, inspectionDue);}
    public void enterNinetyDayExp(String ninetyDayExp){actionsWithOurElements.clearAndEnterTextToElement(ninety_day_expInput, ninetyDayExp);}
    public void enterProRate(String proRateExp){actionsWithOurElements.clearAndEnterTextToElement(pro_rate_expInput, proRateExp);}
    public void enterExpDate(String expDate){actionsWithOurElements.clearAndEnterTextToElement(exp_dateInput, expDate);}
    public void clickOnActive(){actionsWithOurElements.clickJsOnElement(checkboxTruckState);}
    public void clickOnSubmit(){actionsWithOurElements.clickOnElement(submitButton);}

    public void enterOnEquipmentPlaceHolder(String equipmentName){actionsWithOurElements.enterTextToElement(truckNameHolder, equipmentName);}
    public void clickOnEquipmentOnRow(String name){actionsWithOurElements.clickOnElement(".//*[text()='" + name + "']");}
    public void clickOnEditButton(){actionsWithOurElements.clickOnElement(editButton);}
    public void clickOnSave(){actionsWithOurElements.clickOnElement(saveButton);}
    public void enterNote(String note){actionsWithOurElements.clearAndEnterTextToElement(textArea, note);}
    public void addFileRegistration(String path){actionsWithOurElements.addFileByJs(addFileReg, path);}
    public void addFileInspection(String path){actionsWithOurElements.addFileByJs(addFileInsp, path);}

    // REMINDERS
    public void clickOnRemindersButton(){actionsWithOurElements.clickOnElement(remindersButton);}
    public void clickOnRemindByDateButton(){actionsWithOurElements.clickOnElement(remindByDateButton);}
    public void clickOnRemindByMileButton(){actionsWithOurElements.clickOnElement(remindByMileButton);}
    public void enterNameReminder(String nameReminder){actionsWithOurElements.enterTextToElement(nameReminderInput, nameReminder);}
    public void enterDateReminder(String dateReminder){actionsWithOurElements.enterTextToElement(dateReminderInput, dateReminder);}
    public void moveSlider(int value){actionsWithOurElements.sliderMove("//*[@class='add-box__chosen-by-date']//div[@class='vue-slider-rail']", value);}
    public void clickSaveButton(){actionsWithOurElements.clickOnElement(saveReminderButton);}
    public void enterMiles(String countMiles){actionsWithOurElements.enterTextToElement(milesInput, countMiles);}


}