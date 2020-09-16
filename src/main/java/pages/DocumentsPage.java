package pages;

import libs.UtilsForDB;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static libs.Utils.waitABit;

public class DocumentsPage extends ParentPage{


    @FindBy(xpath = ".//button[text() = 'Create Document']")
    private WebElement addDocumentButton;

    @FindBy(xpath = ".//*[@id='edit_doc_type']//..//*[@label='Select a document']")
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

    @FindBy(name = "reference")
    private WebElement referencePlaceHolder;

    @FindBy(xpath = ".//*[@class='icon-icons-navigation-ic-action']")
    private WebElement actionButton;

    @FindBy(xpath = ".//*[@class='action-dropdown-picker__hidden-item' and text()='Edit']")
    private WebElement actionEdit;

    @FindBy(name="date")
    private WebElement datePlaceHolder;

    @FindBy(xpath = ".//input[@type='file']")
    private WebElement addFile;

    @FindBy(xpath = ".//button[text()='Edit']")
    private WebElement buttonEdit;

    public DocumentsPage(WebDriver webDriver, UtilsForDB utilsForDB) {
        super(webDriver, "/dash/live_scan/", utilsForDB);
    }


    public void clickOnCreateButton(){actionsWithOurElements.clickOnElement(addDocumentButton);
        waitABit(6);}
    public void clickOnSaveButton(){actionsWithOurElements.clickOnElement(saveButton);}
    public void clickOnEditButton(){actionsWithOurElements.clickOnElement(buttonEdit);}
    public void selectTypeDocument(String documentTypeValue){

        actionsWithOurElements.selectValueInDropDown(dropDawnTypeDocument, documentTypeValue);
        waitABit(3);
    }
    public void notesText(String tempText){actionsWithOurElements.clearAndEnterTextToElement(textArea, tempText);}
    public void selectState(String value){
        actionsWithOurElements.enterTextToElement(statePlaceHolder, value);
        actionsWithOurElements.clickOnElement(elementInDropDownMenu);
    }
    public void deliveryDate(String date){actionsWithOurElements.clearAndEnterTextToElement(deliveryDateInput, date);}
    public void shipDate(String date){actionsWithOurElements.clearAndEnterTextToElement(shipDateInput, date);}
    public void dealer(String dealer){actionsWithOurElements.clearAndEnterTextToElement(dealerInput, dealer);}
    public void shipper(String shipper){actionsWithOurElements.clearAndEnterTextToElement(shipperInput, shipper);}
    public void location(String location){actionsWithOurElements.clearAndEnterTextToElement(locationInput, location);}
    public void scale(String scale){actionsWithOurElements.clearAndEnterTextToElement(scaleInput, scale);}
    public void reeferGallons(String reeferGallons){actionsWithOurElements.clearAndEnterTextToElement(reeferGallonsInput, reeferGallons);}
    public void reeferAmount(String reeferAmount){actionsWithOurElements.clearAndEnterTextToElement(reeferAmountInput, reeferAmount);}
    public void gallons(String gallons){actionsWithOurElements.clearAndEnterTextToElement(gallonsInput, gallons);}
    public void amount(String amount){actionsWithOurElements.clearAndEnterTextToElement(amountInput, amount);}
    public void driverValue(String driverValue){
        actionsWithOurElements.enterTextToElement(driverPlaceHolder, driverValue);
        actionsWithOurElements.clickOnElement(".//li[@role='option']//../*[contains(text(),'" + driverValue + "')]");
    }
    public void trailerValue(String trailerValue){
        actionsWithOurElements.clearAndEnterTextToElement(trailerPlaceHolder, trailerValue);
        actionsWithOurElements.clickOnElement(".//li[@role='option']//../*[contains(text(),'" + trailerValue + "')]");
    }
    public void truckValue(String truckValue){
        actionsWithOurElements.clearAndEnterTextToElement(truckPlaceHolder, truckValue);
        actionsWithOurElements.clickOnElement(".//li[@role='option']//../*[contains(text(),'" + truckValue + "')]");
    }
    public void documentDate(String date){actionsWithOurElements.clearAndEnterTextToElement(dateInput, date);}
    public void reference(String reference){actionsWithOurElements.clearAndEnterTextToElement(referenceInput, reference);}
    public void enterReferenceInPlaceHolder(String referenceName){actionsWithOurElements.clearAndEnterTextToElement(referencePlaceHolder, referenceName);}
    public void addPictureByJs(String path){actionsWithOurElements.addFileByJs(addFile, path);}
    public void clickOnActionButton(String referenceName){actionsWithOurElements.clickOnElement(".//*[text()='" + referenceName + "']//..//*[@class='icon-icons-navigation-ic-action']");}
    public void clickOnEdidButton(){actionsWithOurElements.clickOnElement(actionEdit);}
    public void clickOnDropDownWithValue(String value, String element){
        actionsWithOurElements.enterTextToElement(".//*[@class='document-popup']//./*[contains(text(),'" + value + "')]//../input", element);
        actionsWithOurElements.clickOnElement(".//li[@role='option']//../*[contains(text(),'" + element + "')]");
    }
}
