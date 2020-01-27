package pagesLocal;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DocumentsLocalSitePage extends ParentLocalSitePage {

    public DocumentsLocalSitePage(WebDriver webDriver) {
        super(webDriver, "/dash/settings/account/");
    }

    @FindBy(xpath = ".//button[text() = 'Create document']")
    private WebElement addDocumentButton;

    @FindBy(name = "type")
    private WebElement dropDawnTypeDocument;

    @FindBy(name = "reference")
    private WebElement referenceInput;

    @FindBy(name = "date")
    private WebElement dateInput;

    @FindBy(id = "edit_truck")
    private WebElement selectTruckValue;

    @FindBy(id = "edit_trailer")
    private WebElement selectTrailerValue;

    @FindBy(id = "edit_driver")
    private WebElement selectDriverValue;

    @FindBy(xpath = ".//*[@placeholder='Amount']")
    private WebElement amountInput;

    @FindBy(xpath = ".//*[@placeholder='Gallons']")
    private WebElement gallonsInput;

    @FindBy(xpath = ".//*[@placeholder='Reefer Amount']")
    private WebElement reeferAmountInput;

    @FindBy(xpath = ".//*[@placeholder='Reefer Gallons']")
    private WebElement reeferGallonsInput;

    @FindBy(xpath = ".//*[@placeholder='Scale']")
    private WebElement scaleInput;

    @FindBy(xpath = ".//*[@placeholder='Location']")
    private WebElement locationInput;

    @FindBy(xpath = ".//*[@placeholder='Shipper']")
    private WebElement shipperInput;

    @FindBy(xpath = ".//*[@placeholder='Dealer']")
    private WebElement dealerInput;

    @FindBy(xpath = ".//*[text()='Ship date']/..//input")
    private WebElement shipDateInput;

    @FindBy(xpath = ".//*[text()='Delivery date']/..//input")
    private WebElement deliveryDateInput;

    @FindBy(id = "edit_state")
    private WebElement selectStateValue;

    @FindBy(name = "image")
    private WebElement imageButton;

    @FindBy(xpath = "//input[@type='file']")
    private WebElement fileInput;

    @FindBy(xpath = ".//textarea")
    private WebElement textArea;

    @FindBy(xpath = ".//button[@type='submit']")
    private WebElement saveButton;

    public void clickOnCreateButton(){actionsWithOurElements.clickOnElement(addDocumentButton);}
    public void clickOnSaveButton(){actionsWithOurElements.clickOnElement(saveButton);}
    public void clickOnImageButton(){actionsWithOurElements.jsClickOnElement(imageButton);}
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
    public void documentDate(String date){actionsWithOurElements.clearAndEnterTextToElement(dateInput, date);}
    public void reference(String reference){actionsWithOurElements.enterTextToElement(referenceInput, reference);}
    public void sendImages(String imagesPath){actionsWithOurElements.enterTextToElement(fileInput, imagesPath);}

}
