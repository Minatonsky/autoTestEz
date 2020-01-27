package pagesLocal;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EquipmentLocalSitePage extends ParentLocalSitePage {
    public EquipmentLocalSitePage(WebDriver webDriver) {
        super(webDriver, "/dash/equipment/");
    }

    @FindBy(xpath = "//button[text() = 'Add Truck']")
    private WebElement addTruckButton;
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

    public void clickOnAddTruckButton(){actionsWithOurElements.clickOnElement(addTruckButton);}
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
    public void clickOnActive(){actionsWithOurElements.jsClickOnElement(checkboxTruckState);}
    public void clickOnSubmit(){actionsWithOurElements.clickOnElement(submitButton);}

    public void enterOnEquipmentPlaceHolder(String equipmentName){actionsWithOurElements.enterTextToElement(truckNameHolder, equipmentName);}
    public void clickOnEquipmentOnRow(){actionsWithOurElements.clickOnElement(equipmentInRow);}
    public void clickOnEditButton(){actionsWithOurElements.clickOnElement(editButton);}
    public void clickOnSave(){actionsWithOurElements.clickOnElement(saveButton);}
}