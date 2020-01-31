package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EquipmentPage extends ParentPage {
    public EquipmentPage(WebDriver webDriver) {
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

    @FindBy(name = "inspection_due")
    private WebElement inspection_dueInput;

    @FindBy(name = "ninety_day_exp")
    private WebElement ninety_day_expInput;

    @FindBy(name = "pro_rate_exp")
    private WebElement pro_rate_expInput;

    @FindBy(name = "exp_date")
    private WebElement exp_dateInput;

    @FindBy(id = "checkboxTruckState")
    private WebElement checkboxTruckState;

    @FindBy(xpath = "//button[@type= 'submit']")
    private WebElement submitButton;

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
    public void enterInspectionDue(String inspectionDue){actionsWithOurElements.enterTextToElement(inspection_dueInput, inspectionDue);}
    public void enterNinetyDayExp(String ninetyDayExp){actionsWithOurElements.enterTextToElement(ninety_day_expInput, ninetyDayExp);}
    public void enterProRate(String proRateExp){actionsWithOurElements.enterTextToElement(pro_rate_expInput, proRateExp);}
    public void enterExpDate(String expDate){actionsWithOurElements.enterTextToElement(exp_dateInput, expDate);}
    public void clickOnActive(){actionsWithOurElements.clickJsOnElement(checkboxTruckState);}
    public void clickOnSubmit(){actionsWithOurElements.clickOnElement(submitButton);}
}