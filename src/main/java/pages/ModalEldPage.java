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

//    @FindBy(xpath = "//*[@id='eldTariffs']/*[@data-id='1']")
//    private WebElement paymentMethods;

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
    private WebElement quantityPinCableInput;

    @FindBy(xpath = "//*[@data-id='3']/td[3]/input ")
    private WebElement quantityOBDPinCableInput;

    @FindBy(xpath = "//*[@data-id='4']/td[3]/input ")
    private WebElement quantityStickerInput;

    @FindBy(xpath = "//*[@data-id='100']/td[3]/input ")
    private WebElement quantityCamera1Input;

    @FindBy(xpath = "//*[@data-id='101']/td[3]/input ")
    private WebElement quantityCamera2Input;


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

    public void enterQuantityPinCable(String quantityPinCable){actionsWithOurElements.enterTextToElement(quantityPinCableInput, quantityPinCable);}

    public void enterQuantityOBDPinCable(String quantityOBDPinCable){actionsWithOurElements.enterTextToElement(quantityOBDPinCableInput, quantityOBDPinCable);}

    public void enterQuantitySticker(String quantitySticker){actionsWithOurElements.enterTextToElement(quantityStickerInput, quantitySticker);}

    public void enterQuantityCamera1(String quantityCamera1){actionsWithOurElements.enterTextToElement(quantityCamera1Input, quantityCamera1);}

    public void enterQuantityCamera2(String quantityCamera2){actionsWithOurElements.enterTextToElement(quantityCamera2Input, quantityCamera2);}

/*
PAYMENT METHODS
 */


    public void clickPaymentMethods(String typeOfPaymentMethod){
        if (isPaymentInList(typeOfPaymentMethod)){
            clickOnPaymentMethod(typeOfPaymentMethod);
            logger.info("Payment Method " + typeOfPaymentMethod + " was selected");
        }
    }

    private void clickOnPaymentMethod(String typeOfPaymentMethod) {
        actionsWithOurElements.clickOnElement(".//*[@id='eldTariffs']/*[@data-id='" + typeOfPaymentMethod + "']");
    }

    private boolean isPaymentInList(String typeOfPaymentMethod) {
        return actionsWithOurElements.isElementInOrder(".//*[@id='eldTariffs']/*[@data-id='" + typeOfPaymentMethod + "']");
    }



/*
BUTTON ORDER
 */

    public void clickButtonOrder() {actionsWithOurElements.clickOnElement(orderButton);}



    public boolean compareTotalOrder(String eldStandardTotalOrder){
        return actionsWithOurElements.isElementInOrder(".//*[@id='eld_total_order' and text()='$" + eldStandardTotalOrder + "']");


    }
}
