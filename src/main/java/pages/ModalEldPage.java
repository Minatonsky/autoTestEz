package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ModalEldPage extends ParentPage {

    @FindBy(id = "number_device")
    private WebElement quantityDeviseInput;

    @FindBy(id = "send_order")
    private WebElement orderButton;

//    @FindBy(xpath = "//*[@id=\"state\"]/option[2]")
//    private WebElement stateList;
    
    @FindBy(id = "first_name")
    private WebElement firstNameInput;

    @FindBy(id = "last_name")
    private WebElement lastNameInput;

    @FindBy(id = "phone")
    private WebElement phoneInput;

    @FindBy(id = "address")
    private WebElement addressLineInput;

    @FindBy(id = "address1")
    private WebElement aptNumberInput;

    @FindBy(id = "city")
    private WebElement deliveryCityInput;

    @FindBy(id = "zip")
    private WebElement zipCodeInput;

    @FindBy(xpath = "//*[@id='eldTariffs']/*[@data-id='0']")
    private WebElement paymentMethods;

    @FindBy(id = "leaseAndAgreementCheckbox")
    private WebElement agreement;

    @FindBy(xpath = "//button[@class='fast_move']/i[@class='fa fa-arrow-down']")
    private WebElement buttonFastMove;

    @FindBy(xpath = "//div[@class='modal-body']/*[contains(text(), 'I Agree')]")
    private WebElement buttonAgree;

    @FindBy(xpath = ".//select[@name='state']")
    private WebElement typeOfState;

    @FindBy(name = "pick_up")
    private WebElement checkBoxPickUp;

    @FindBy(name = "overnight_delivery")
    private WebElement checkBoxOvernightDelivery;

    @FindBy(xpath = "//*[@data-id='2']/td[3]/input ")
    private WebElement QuantityPinCableInput;

    @FindBy(xpath = "//*[@data-id='3']/td[3]/input ")
    private WebElement QuantityOBDPinCableInput;

    @FindBy(xpath = "//*[@data-id='4']/td[3]/input ")
    private WebElement QuantityStickerInput;

    @FindBy(xpath = "//*[@data-id='100']/td[3]/input ")
    private WebElement QuantityCamera1Input;

    @FindBy(xpath = "//*[@data-id='101']/td[3]/input ")
    private WebElement QuantityCamera2Input;


    public ModalEldPage(WebDriver webDriver) {
        super(webDriver, "/dash/eld/");
    }


/*
PERSONAL DATA
 */
    public void selectState(String value) {actionsWithOurElements.selectValueInDropDown(typeOfState, value);}

    public void enterFirstName(String firstName) {actionsWithOurElements.enterTextToElement(firstNameInput, firstName);}

    public void enterLastName(String lastName) {actionsWithOurElements.enterTextToElement(lastNameInput, lastName); }

    public void enterPhone(String phone)
    {
        actionsWithOurElements.enterTextToElement(phoneInput, phone);
    }

    public void enterPrimaryAddressLine(String addressLine) {actionsWithOurElements.enterTextToElement(addressLineInput, addressLine);}

    public void enterAptNumber(String aptNumber) { actionsWithOurElements.enterTextToElement(aptNumberInput, aptNumber);}

    public void enterDeliveryCity(String deliveryCity) {actionsWithOurElements.enterTextToElement(deliveryCityInput, deliveryCity);}

    public void enterZipCode(String zipCode) {actionsWithOurElements.enterTextToElement(zipCodeInput, zipCode);}

/*
CHECK BOX DELIVERY
 */

    public void setPickUpFromOffice(String neededStatePickUpFromOffice){actionsWithOurElements.setNeededStateToCheckBox(checkBoxPickUp, neededStatePickUpFromOffice);}

    public void setOvernightDelivery(String neededStateOvernightDelivery){actionsWithOurElements.setNeededStateToCheckBox(checkBoxOvernightDelivery, neededStateOvernightDelivery);}

/*
EQUIPMENT LEASE AND SOFTWARE SUBSCRIPTION SERVICE AGREEMENT
 */

    public void clickAgreement() { actionsWithOurElements.clickOnElement(agreement);}

    public void clickButtonFastMove() {actionsWithOurElements.clickOnElement(buttonFastMove);}

    public void clickButtonAgree() {actionsWithOurElements.clickOnElement(buttonAgree);}

/*
ORDER LIST
 */

    public void enterQuantityDevices(String quantityOfDevices) {actionsWithOurElements.enterTextToElement(quantityDeviseInput, quantityOfDevices);}

    public void enterQuantityPinCable(String QuantityPinCable){actionsWithOurElements.enterTextToElement(QuantityPinCableInput, QuantityPinCable);}

    public void enterQuantityOBDPinCable(String QuantityOBDPinCable){actionsWithOurElements.enterTextToElement(QuantityOBDPinCableInput, QuantityOBDPinCable);}

    public void enterQuantitySticker(String QuantitySticker){actionsWithOurElements.enterTextToElement(QuantityStickerInput, QuantitySticker);}

    public void enterQuantityCamera1(String QuantityCamera1){actionsWithOurElements.enterTextToElement(QuantityCamera1Input, QuantityCamera1);}

    public void enterQuantityCamera2(String QuantityCamera2){actionsWithOurElements.enterTextToElement(QuantityCamera2Input, QuantityCamera2);}

/*
PAYMENT METHODS
 */

    public void clickPaymentMethods() {actionsWithOurElements.clickOnElement(paymentMethods);}

/*
BUTTON ORDER
 */

    public void clickButtonOrder() {actionsWithOurElements.clickOnElement(orderButton);}



    public boolean compareTotalOrder(String eldStandardTotalOrder){
        return actionsWithOurElements.isElementInOrder(".//*[@id='eld_total_order' and text()='$" + eldStandardTotalOrder + "']");


    }
}
