package pagesLocal;

import libs.Database;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EquipmentLocalSitePage extends ParentLocalSitePage {
    public EquipmentLocalSitePage(WebDriver webDriver, Database dBMySQL) {
        super(webDriver, "/dash/equipment/");
    }

    @FindBy(xpath = "//button[text() = 'Add Truck']")
    private WebElement addTruckButton;

    @FindBy(xpath = "//button[text() = 'Add Trailer']")
    private WebElement addTrailerButton;

//GENERAL
    @FindBy(name = "name")
    private WebElement unitInput;

    @FindBy(name = "owner")
    private WebElement ownerInput;

    @FindBy(name = "year")
    private WebElement yearInput;

    @FindBy(name = "type")
    private WebElement typeValue;

    @FindBy(name = "vin")
    private WebElement vinInput;

    @FindBy(name = "plate")
    private WebElement plateInput;

    @FindBy(name = "state")
    private WebElement stateValue;

//Parameters
    @FindBy(name = "tire_size")
    private WebElement tire_sizeInput;

    @FindBy(name = "length")
    private WebElement lengthInput;

    @FindBy(name = "fuel")
    private WebElement fuelInput;

    @FindBy(name = "axel")
    private WebElement axelInput;

    @FindBy(name = "make")
    private WebElement makeInput;

    @FindBy(name = "model")
    private WebElement modelInput;

    @FindBy(name = "gross_weight")
    private WebElement gross_weightInput;

    @FindBy(name = "unland_weight")
    private WebElement unland_weightInput;

//Others
    @FindBy(name = "color")
    private WebElement colorInput;

    @FindBy(name = "ny_cert")
    private WebElement ny_certInput;

    @FindBy(xpath = ".//*[@name='inspection_due']//..//*[@type='text']")
    private WebElement inspection_dueInput;

    @FindBy(xpath = ".//*[@name='ninety_day_exp']//..//*[@type='text']")
    private WebElement ninety_day_expInput;

    @FindBy(xpath = ".//*[@name='pro_rate_exp']//..//*[@type='text']")
    private WebElement pro_rate_expInput;

    @FindBy(xpath = ".//*[@name='exp_date']//..//*[@type='text']")
    private WebElement exp_dateInput;

    @FindBy(id = "checkboxTruckState")
    private WebElement checkboxTruckState;

    @FindBy(xpath = ".//button[@type='submit']")
    private WebElement submitButton;

    @FindBy(xpath = ".//*[@placeholder='UNIT']")
    private WebElement truckNameHolder;

    @FindBy(xpath = ".//*[@class='btn-link__text']")
    private WebElement equipmentInRow;

    @FindBy(xpath = ".//*[text()='Edit ']")
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



    public void clickOnAddTruckButton(){actionsWithOurElements.clickOnElement(addTruckButton);}
    public void clickOnAddTrailerButton(){actionsWithOurElements.clickOnElement(addTrailerButton);}
    public String getVehicleIdText(){return vehicleId.getText();}

    //    General
    public void enterUnitName(String unitName){actionsWithOurElements.enterTextToElement(unitInput, unitName);}
    public void enterOwner(String owner){actionsWithOurElements.enterTextToElement(ownerInput, owner);}
    public void enterYear(String year){actionsWithOurElements.enterTextToElement(yearInput, year);}
    public void selectType(String type){actionsWithOurElements.selectValueInDropDown(typeValue, type);}
    public void enterVin(String vin){actionsWithOurElements.enterTextToElement(vinInput, vin);}
    public void enterPlate(String plate){actionsWithOurElements.enterTextToElement(plateInput, plate);}
    public void selectState(String state){actionsWithOurElements.selectValueInDropDown(stateValue, state);}
//    Parameters
    public void enterTireSize(String tireSize){actionsWithOurElements.enterTextToElement(tire_sizeInput, tireSize);}
    public void enterLength(String length){actionsWithOurElements.enterTextToElement(lengthInput, length);}
    public void selectFuel(String fuel){actionsWithOurElements.selectValueInDropDown(fuelInput, fuel);}
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
    public void clickOnEquipmentOnRow(){actionsWithOurElements.clickOnElement(equipmentInRow);}
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