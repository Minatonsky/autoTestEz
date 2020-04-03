package pagesFrankestein;

import libs.UtilsForDB;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DocumentsFPage extends ParentFPage {
    public DocumentsFPage(WebDriver webDriver, UtilsForDB utilsForDB) {
        super(webDriver, "dash/eld/",  utilsForDB);
    }
    @FindBy(xpath = ".//button[text() = 'Create Document']")
    private WebElement addDocumentButton;

    @FindBy(xpath = ".//*[@class='document-popup']//./input[@type='search']")
    private WebElement dropDawnTypeDocument;

    @FindBy(xpath = ".//*[@role='listbox']")
    private WebElement elementInDropDownMenu;

    @FindBy(xpath = "//*[text()='Reference#']//..//input")
    private WebElement referenceInput;

    @FindBy(xpath = "//*[@class='document-popup__body']//*[@name='date']")
    private WebElement dateInput;

    @FindBy(xpath = ".//*[@class='document-popup']//./input[@placeholder='Truck']")
    private WebElement truckPlaceHolder;

    @FindBy(xpath = ".//*[@class='document-popup']//./input[@placeholder='Trailer']")
    private WebElement trailerPlaceHolder;

    @FindBy(xpath = ".//*[@class='document-popup']//./input[@placeholder='Driver']")
    private WebElement driverPlaceHolder;

    @FindBy(xpath = ".//*[text()='Amount']//..//input")
    private WebElement amountInput;

    @FindBy(xpath = ".//*[text()='Gallons']//..//input")
    private WebElement gallonsInput;

    @FindBy(xpath = ".//*[text()='Reefer Amount']//..//input")
    private WebElement reeferAmountInput;

    @FindBy(xpath = ".//*[text()='Reefer Gallons']//..//input")
    private WebElement reeferGallonsInput;

    @FindBy(xpath = ".//*[text()='Scale']//..//input")
    private WebElement scaleInput;

    @FindBy(xpath = ".//*[text()='Location']//..//input")
    private WebElement locationInput;

    @FindBy(xpath = ".//*[text()='Shipper']//..//input")
    private WebElement shipperInput;

    @FindBy(xpath = ".//*[text()='Dealer']/..//input")
    private WebElement dealerInput;

    @FindBy(xpath = ".//*[text()='Ship date']/..//input")
    private WebElement shipDateInput;

    @FindBy(xpath = ".//*[text()='Delivery date']/..//input")
    private WebElement deliveryDateInput;

    @FindBy(xpath = ".//*[@class='document-popup']//./input[@placeholder='Select State']")
    private WebElement statePlaceHolder;

    @FindBy(xpath = "//input[@type='file']")
    private WebElement fileInput;

    @FindBy(xpath = ".//*[@placeholder='Note']")
    private WebElement textArea;

    @FindBy(xpath = ".//button[@type='submit']")
    private WebElement saveButton;

//    LIST PAGE

    @FindBy(xpath = "//*[text()='Reference #']/../input")
    private WebElement referencePlaceHolder;

    @FindBy(name="date")
    private WebElement datePlaceHolder;

    @FindBy(xpath = ".//input[@type='file']")
    private WebElement addFile;

    @FindBy(xpath = ".//*[text()='Today']")
    private WebElement toDayInCalendar;


    public void clickOnCreateButton(){actionsWithOurElements.clickOnElement(addDocumentButton);}
    public void clickOnSaveButton(){actionsWithOurElements.clickOnElement(saveButton);}
    public void selectTypeDocument(String documentTypeValue){
        actionsWithOurElements.clickOnElement(dropDawnTypeDocument);
        actionsWithOurElements.enterTextToElement(dropDawnTypeDocument, documentTypeValue);
        actionsWithOurElements.clickOnElement(elementInDropDownMenu);
    }
    public void notesText(String tempText){actionsWithOurElements.enterTextToElement(textArea, tempText);}
    public void selectState(String value){
        actionsWithOurElements.enterTextToElement(statePlaceHolder, value);
        actionsWithOurElements.clickOnElement(elementInDropDownMenu);
    }
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
    public void driverValue(String driverValue){
        actionsWithOurElements.enterTextToElement(driverPlaceHolder, driverValue);
        actionsWithOurElements.clickOnElement(elementInDropDownMenu);
    }
    public void trailerValue(String trailerValue){
        actionsWithOurElements.enterTextToElement(trailerPlaceHolder, trailerValue);
        actionsWithOurElements.clickOnElement(elementInDropDownMenu);
    }
    public void truckValue(String truckValue){
        actionsWithOurElements.enterTextToElement(truckPlaceHolder, truckValue);
        actionsWithOurElements.clickOnElement(elementInDropDownMenu);
    }
    public void documentDate(String date){actionsWithOurElements.clearAndEnterTextToElement(dateInput, date);}
    public void reference(String reference){actionsWithOurElements.enterTextToElement(referenceInput, reference);}
    public void enterReferenceInPlaceHolder(String referenceName){actionsWithOurElements.enterTextToElement(referencePlaceHolder, referenceName);}
    public void addPictureByJs(String path){actionsWithOurElements.addFileByJs(addFile, path);}
    public void clickOnDocumentInRow(String referenceName){actionsWithOurElements.clickOnElement("//div[@class='table-container__table-item' and text()='" + referenceName +"");}

}
