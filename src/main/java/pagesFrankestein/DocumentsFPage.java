package pagesFrankestein;

import libs.UtilsForDB;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static libs.Utils.waitABit;

public class DocumentsFPage extends ParentFPage {
    public DocumentsFPage(WebDriver webDriver, UtilsForDB utilsForDB) {
        super(webDriver, "dash/eld/",  utilsForDB);
    }
    @FindBy(xpath = ".//button[text() = 'Create Document']")
    private WebElement addDocumentButton;

    @FindBy(id="edit_doc_type")
    private WebElement dropDawnTypeDocument;

    @FindBy(id="edit_reference")
    private WebElement referenceInput;

    @FindBy(id="edit_dateTime")
    private WebElement dateInput;

    @FindBy(id="edit_truck")
    private WebElement selectTruckValue;

    @FindBy(id = "edit_trailer")
    private WebElement selectTrailerValue;

    @FindBy(id = "edit_driver")
    private WebElement selectDriverValue;

    @FindBy(id="edit_amount")
    private WebElement amountInput;

    @FindBy(id="edit_gallons")
    private WebElement gallonsInput;

    @FindBy(id="edit_reefer_amount")
    private WebElement reeferAmountInput;

    @FindBy(id="edit_reefer_gallons")
    private WebElement reeferGallonsInput;

    @FindBy(id="edit_scale")
    private WebElement scaleInput;

    @FindBy(id="edit_location")
    private WebElement locationInput;

    @FindBy(id="edit_shipper")
    private WebElement shipperInput;

    @FindBy(id="edit_dealer")
    private WebElement dealerInput;

    @FindBy(id="edit_ship_date")
    private WebElement shipDateInput;

    @FindBy(id="edit_delivery_date")
    private WebElement deliveryDateInput;

    @FindBy(id = "edit_state")
    private WebElement selectStateValue;

    @FindBy(id="doc_image")
    private WebElement imageButton;

    @FindBy(xpath = "//input[@type='file']")
    private WebElement fileInput;

    @FindBy(id="edit_notes")
    private WebElement textArea;

    @FindBy(xpath = ".//button[text()='Save']")
    private WebElement saveButton;

    @FindBy(xpath = ".//*[text()='Today']")
    private WebElement toDayInCalendar;



    @FindBy(xpath = "//*[@placeholder='Reference #']")
    private WebElement referencePlaceHolder;

    @FindBy(xpath = "//div[@class='table-container__table-item' and text()='TruckRepair 01/28/2020 18:33:31']")
    private WebElement documentInRow;

    @FindBy(xpath = ".//input[@type='file']")
    private WebElement addFile;

    public void clickOnCreateButton(){actionsWithOurElements.clickOnElement(addDocumentButton);}
    public void clickOnSaveButton(){actionsWithOurElements.clickOnElement(saveButton);}
    public void selectTypeDocument(String documentTypeValue){actionsWithOurElements.selectValueInDropDown(dropDawnTypeDocument, documentTypeValue);}
    public void notesText(String tempText){actionsWithOurElements.enterTextToElement(textArea, tempText);}
    public void selectState(String value){actionsWithOurElements.selectValueInDropDown(selectStateValue, value);}
    public void deliveryDate(String date){actionsWithOurElements.clearAndEnterTextToElement(deliveryDateInput, date);}
    public void shipDate(String date){actionsWithOurElements.clearAndEnterTextToElement(shipDateInput, date);}
    public void dealer(String dealer){actionsWithOurElements.enterTextToElement(dealerInput, dealer);}
    public void shipper(String shipper){actionsWithOurElements.enterTextToElement(shipperInput, shipper);}
    public void location(String location){actionsWithOurElements.enterTextToElement(locationInput, location);}
    public void scale(String scale){actionsWithOurElements.enterTextToElement(scaleInput, scale);}
    public void reeferGallons(String reeferGallons){actionsWithOurElements.enterTextToElement(reeferGallonsInput, reeferGallons);}
    public void reeferAmount(String reeferAmount){actionsWithOurElements.enterTextToElement(reeferAmountInput, reeferAmount);}
    public void gallons(String gallons){actionsWithOurElements.enterTextToElement(gallonsInput, gallons);}
    public void amount(String amount){actionsWithOurElements.enterTextToElement(amountInput, amount);}
    public void driverValue(String driverValue){actionsWithOurElements.selectValueInDropDown(selectDriverValue, driverValue);}
    public void trailerValue(String trailerValue){actionsWithOurElements.selectValueInDropDown(selectTrailerValue, trailerValue);}
    public void truckValue(String truckValue){actionsWithOurElements.selectValueInDropDown(selectTruckValue, truckValue);}
    public void documentDate(){
        actionsWithOurElements.clickOnElement(dateInput);
        waitABit(5);
        actionsWithOurElements.clickOnElement(toDayInCalendar);
//        actionsWithOurElements.enterTextToElement(dateInput, date);
    }
    public void reference(String reference){actionsWithOurElements.enterTextToElement(referenceInput, reference);}
    public void enterReferenceInPlaceHolder(String referenceName){actionsWithOurElements.enterTextToElement(referencePlaceHolder, referenceName);}
    public void addPictureByJs(String path){actionsWithOurElements.addFileByJs(addFile, path);}
    public void clickOnDocumentInRow(String referenceName){actionsWithOurElements.clickOnElement("//div[@class='table-container__table-item' and text()='" + referenceName +"");}
    public void clickOnToDayInCalendar(){

    }

}
