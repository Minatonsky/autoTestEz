package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static libs.Utils.waitABit;

public class ModalEldPage extends ParentPage {
    LoginPage loginPage;

    @FindBy(id = "number_device")
    private WebElement quantityDeviseInput;

    @FindBy(id = "send_order")
    private WebElement orderButton;
    
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

    @FindBy(id = "leaseAndAgreementCheckbox")
    private WebElement agreement;

    @FindBy(xpath = ".//button[@class='fast_move']/i[@class='fa fa-arrow-down']")
    private WebElement buttonFastMove;

    @FindBy(xpath = ".//button[(text()= 'I Agree')]")
    private WebElement buttonAgree;

    @FindBy(xpath = ".//div[@class='col-sm-6 text-center text-md-left mb-1 hidden-xs']/button[(text()='Cancel order')]")
    private WebElement buttonCancel;

    @FindBy(xpath = ".//button[text()='Confirm']")
    private WebElement buttonConfirm;

    @FindBy(xpath = ".//select[@name='state']")
    private WebElement typeOfState;

    @FindBy(name = "pick_up")
    private WebElement checkBoxPickUp;

    @FindBy(name = "overnight_delivery")
    private WebElement checkBoxOvernightDelivery;

    @FindBy(xpath = ".//*[@data-id='2']/td[3]/input")
    private WebElement quantityPinCableInput;

    @FindBy(xpath = ".//*[@data-id='3']/td[3]/input")
    private WebElement quantityOBDPinCableInput;

    @FindBy(xpath = ".//*[@data-id='4']/td[3]/input")
    private WebElement quantityStickerInput;

    @FindBy(xpath = ".//*[@data-id='100']/td[3]/input")
    private WebElement quantityCamera1Input;

    @FindBy(xpath = ".//*[@data-id='101']/td[3]/input")
    private WebElement quantityCamera2Input;


    public ModalEldPage(WebDriver webDriver) {
        super(webDriver, "/dash/eld/");
    }
    

    /*
    PERSONAL DATA
     */
    public void selectState(String deliveryState) {actionsWithOurElements.selectValueInDropDown(typeOfState, deliveryState);}

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

    @Step
    public void enterPersonalData(String deliveryState, String firstName, String lastName, String phone, String addressLine, String aptNumber, String deliveryCity, String zipCode) {
        selectState(deliveryState);
        enterFirstName(firstName);
        enterLastName(lastName);
        enterPhone(phone);
        enterPrimaryAddressLine(addressLine);
        enterAptNumber(aptNumber);
        enterDeliveryCity(deliveryCity);
        enterZipCode(zipCode);
    }


/*
EQUIPMENT LEASE AND SOFTWARE SUBSCRIPTION SERVICE AGREEMENT
 */

    public void clickAgreement() { actionsWithOurElements.clickOnElement(agreement);}

    public void clickButtonFastMove() {actionsWithOurElements.clickOnElement(buttonFastMove);}

    public void clickButtonAgree() {actionsWithOurElements.clickOnElement(buttonAgree);}

    public void clickButtonCancel(){actionsWithOurElements.clickOnElement(buttonCancel);}

    public void clickButtonConfirm(){
        actionsWithOurElements.clickOnElement(buttonConfirm);
    }

    @Step
    public void doCancelAgreementForManagerOrder(){

            waitABit(3);
            clickButtonFastMove();
            waitABit(3);
            clickButtonCancel();
            waitABit(3);
            clickButtonConfirm();
            waitABit(3);
            logger.info("Order was canceled");

    }

    @Step
    public void doAgreeAgreementForManagerOrder(){
        waitABit(3);
        clickButtonFastMove();
        clickButtonAgree();
        waitABit(3);
    }

    @Step
    public void clickAgreements(String quantityOfDevices){
        int num = Integer.parseInt(quantityOfDevices);
        if (num > 0){
            clickAgreement();
            clickButtonFastMove();
            clickButtonAgree();
            clickButtonOrder();
            logger.info("quantityOfDevices > 0");
        }
        else {
            clickButtonOrder();
            logger.info("quantityOfDevices == 0");
        }
    }



/*
ORDER LIST
 */

    public void enterQuantityDevices(String quantityOfDevices) {actionsWithOurElements.enterTextToElement(quantityDeviseInput, quantityOfDevices);}

    public void enterQuantityPinCable(String quantityPinCable){actionsWithOurElements.enterTextToElement(quantityPinCableInput, quantityPinCable);}

    public void enterQuantityOBDPinCable(String quantityOBDPinCable){actionsWithOurElements.enterTextToElement(quantityOBDPinCableInput, quantityOBDPinCable);}

    public void enterQuantitySticker(String quantitySticker){actionsWithOurElements.enterTextToElement(quantityStickerInput, quantitySticker);}

    public void enterQuantityCamera1(String quantityCamera1){actionsWithOurElements.enterTextToElement(quantityCamera1Input, quantityCamera1);}

    public void enterQuantityCamera2(String quantityCamera2){actionsWithOurElements.enterTextToElement(quantityCamera2Input, quantityCamera2);}

    public void setPickUpFromOffice(String neededStatePickUpFromOffice){actionsWithOurElements.setNeededStateToCheckBox(checkBoxPickUp, neededStatePickUpFromOffice);}

    public void setOvernightDelivery(String neededStateOvernightDelivery){actionsWithOurElements.setNeededStateToCheckBox(checkBoxOvernightDelivery, neededStateOvernightDelivery);}

    @Step
    public void enterOrderData(String  quantityOfDevices, String quantityPinCable, String quantityOBDPinCable, String quantitySticker, String quantityCamera1, String quantityCamera2, String neededStatePickUpFromOffice, String neededStateOvernightDelivery){
        enterQuantityDevices(quantityOfDevices);
        enterQuantityPinCable(quantityPinCable);
        enterQuantityOBDPinCable(quantityOBDPinCable);
        enterQuantitySticker(quantitySticker);
        enterQuantityCamera1(quantityCamera1);
        enterQuantityCamera2(quantityCamera2);
        setPickUpFromOffice(neededStatePickUpFromOffice);
        setOvernightDelivery(neededStateOvernightDelivery);

    }


/*
PAYMENT METHODS
 */

    @Step
    public void clickPaymentMethods(String typeOfPaymentMethod){
        if (isPaymentInList(typeOfPaymentMethod)){
            clickOnPaymentMethod(typeOfPaymentMethod);
            logger.info("Payment Method " + typeOfPaymentMethod + " was selected");
        }
        else {
            logger.info("Payment Method was not selected");
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
    @Step
    public void clickButtonOrder() {actionsWithOurElements.clickOnElement(orderButton);}


/*
COMPARE METHODS
 */


    @Step
    public boolean compareTotalOrder(String eldStandardTotalOrder){
        return actionsWithOurElements.isElementInOrder(".//*[@id='eld_total_order' and text()='$" + eldStandardTotalOrder + "']");
    }

    @Step
    public boolean compareOrderPrice(String eldOrderPrice){
        return actionsWithOurElements.isElementInOrder(".//*[@id='eld_order_price' and text()='$" + eldOrderPrice + "']");
    }

    @Step
    public boolean compareDeliveryPrice(String eldDeliveryPrice){
        return actionsWithOurElements.isElementInOrder(".//*[@id='eld_delivery_price' and text()='$" + eldDeliveryPrice + "']");
    }

    @Step
    public boolean compareFirstMonthFee(String eldFirstMonthFee){
        return actionsWithOurElements.isElementInOrder(".//*[@id='eld_first_price' and text()='$" + eldFirstMonthFee + "']");
    }

    @Step
    public boolean compareLastMonthFee(String eldLastMonthFee) {
        return actionsWithOurElements.isElementInOrder(".//*[@id='eld_last_price' and text()='$" + eldLastMonthFee + "']");
    }

    @Step
    public boolean compareDepositFee(String eldDepositFee){
        return actionsWithOurElements.isElementInOrder(".//*[@id='eld_deposit_fee' and text()='$" + eldDepositFee + "']");
    }
}