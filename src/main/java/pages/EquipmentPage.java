package pages;

import libs.UtilsForDB;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static libs.Utils.waitABit;

public class EquipmentPage extends ParentPage {
    public EquipmentPage(WebDriver webDriver, UtilsForDB utilsForDB) {
        super(webDriver, "/dash/equipment/", utilsForDB);
    }

    @FindBy(xpath = "//button[text() = 'Add Truck']")
    private WebElement addTruckButton;

    @FindBy(xpath = "//button[text() = 'Add Trailer']")
    private WebElement addTrailerButton;

    @FindBy(xpath = "//*[@class='ui-state-default ui-state-highlight']")
    private WebElement dateCalendar;

    @FindBy(xpath = "//button[text() = 'Add Unit']")
    private WebElement addUnit;

//GENERAL
    @FindBy(id = "edit_tr_Name")
    private WebElement unitInput;

    @FindBy(id = "edit_tr_Owner")
    private WebElement ownerInput;

    @FindBy(id = "edit_tr_Year")
    private WebElement yearInput;

    @FindBy(id = "edit_tr_Type")
    private WebElement typeValue;

    @FindBy(id = "edit_tr_VIN")
    private WebElement vinInput;

    @FindBy(id = "edit_tr_Plate")
    private WebElement plateInput;

    @FindBy(id = "edit_tr_State")
    private WebElement stateValue;

//Parameters
    @FindBy(id = "edit_tr_TireSize")
    private WebElement tire_sizeInput;

    @FindBy(id = "edit_tr_Length")
    private WebElement lengthInput;

    @FindBy(id = "edit_tr_Fuel")
    private WebElement fuelInput;

    @FindBy(id = "edit_tr_Axel")
    private WebElement axelInput;

    @FindBy(id = "edit_tr_Make")
    private WebElement makeInput;

    @FindBy(id = "edit_tr_Model")
    private WebElement modelInput;

    @FindBy(id = "edit_tr_GrossWeight")
    private WebElement gross_weightInput;

    @FindBy(id = "edit_tr_UnlandWeight")
    private WebElement unland_weightInput;

//Others
    @FindBy(id = "edit_tr_Color")
    private WebElement colorInput;

    @FindBy(id = "edit_tr_NYCert")
    private WebElement ny_certInput;

    @FindBy(id = "edit_tr_InspectionDue")
    private WebElement inspection_dueInput;

    @FindBy(id = "edit_tr_90DayExp")
    private WebElement ninety_day_expInput;

    @FindBy(id = "edit_tr_ProRateExp")
    private WebElement pro_rate_expInput;

    @FindBy(id = "edit_tr_ExpDate")
    private WebElement exp_dateInput;

    @FindBy(id = "checkboxTruckState")
    private WebElement checkboxTruckState;

    @FindBy(xpath = "//button[text() = 'Add']")
    private WebElement submitButton;

    public void clickOnAddTruckButton(){actionsWithOurElements.clickOnElement(addTruckButton);}
    public void clickOnAddTrailerButton(){actionsWithOurElements.clickOnElement(addTrailerButton);}
    public void clickOnAddUnit(){actionsWithOurElements.clickOnElement(addUnit);}
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
    public void clickOnSubmit(){waitABit(3);actionsWithOurElements.clickOnElement(submitButton);waitABit(3);}
    public void clickOnBlankArea(){actionsWithOurElements.clickOnBlankArea();}


}