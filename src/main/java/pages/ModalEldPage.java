package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static libs.Utils.waitABit;

public class ModalEldPage extends ParentPage {
    LoginPage loginPage;

    @FindBy(name ="amount")
    private WebElement quantityDeviseInput;

    @FindBy(id = "send_order")
    private WebElement orderButton;
    
    @FindBy(id = "name")
    private WebElement firstNameInput;

    @FindBy(id = "surname")
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

    @FindBy(id ="leaseAndAgreementCameraCheckbox")
    private WebElement agreementCamera;

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

    @FindBy(name = "overnight")
    private WebElement checkBoxOvernightDelivery;

    @FindBy(name = "products[2]")
    private WebElement quantityPinCableInput;

    @FindBy(name = "products[3]")
    private WebElement quantityOBDPinCableInput;

    @FindBy(name = "products[4]")
    private WebElement quantityStickerInput;

    @FindBy(name = "products[5]")
    private WebElement quantityCamera1Input;

    @FindBy(name = "products[6]")
    private WebElement quantityCamera2Input;

    @FindBy(xpath = ".//*[text()='TOTAL']/../../td[@class='text-center price']")
    private WebElement totalOrderText;

    @FindBy(xpath = "//*[text()='ELD first month fee $29.99']/../td[@class='text-center price']")
    private WebElement firstMonthFeeText;

    @FindBy(xpath = "//*[text()='ELD last month fee $29.99']/../td[@class='text-center price']")
    private WebElement lastMonthFeeText;

    @FindBy(xpath = "//*[text()='ELD price $329.89']/../td[@class='text-center price']")
    private WebElement eldOneYearPriceText;

    @FindBy(xpath = "//*[text()='ELD price $629.79']/../td[@class='text-center price']")
    private WebElement eldTwoYearPriceText;

    @FindBy(xpath = "//*[text()='ELD Deposit fee $49.99']/../td[@class='text-center price']")
    private WebElement eldDepositFeeText;

    @FindBy(xpath = "//*[text()='Delivery price']/../td[@class='text-center price']")
    private WebElement eldDeliveryPriceText;

    @FindBy(xpath = "//*[text()='6 Pin Cable']/../td[@class='text-center price']")
    private WebElement eldPinCableText;

    @FindBy(xpath = "//*[text()='OBDII 16 pin Cable']/../td[@class='text-center price']")
    private WebElement eldOBDPinCableText;

    @FindBy(xpath = "//*[text()='Sticker Label']/../td[@class='text-center price']")
    private WebElement eldStickerLabelText;

    @FindBy(xpath = ".//div[@data-id='0']")
    private WebElement monthToMonth;

    @FindBy(xpath = ".//*[@data-id='1']")
    private WebElement oneYearSubscription;

    @FindBy(xpath = ".//div[@data-id='2']")
    private WebElement twoYearSubscription;



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

    public void clickAgreementCamera() { actionsWithOurElements.clickOnElement(agreementCamera);}

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

    /**
     *
     * @param quantityOfDevices
     */
    @Step
    public void doAgreeAgreement(String quantityOfDevices){
        int num = Integer.parseInt(quantityOfDevices);
        if (num > 0){
            clickAgreement();
            clickButtonFastMove();
            clickButtonAgree();
            clickButtonOrder();
        }
        else {
            clickButtonOrder();
            logger.info("quantityOfDevices = 0");
        }
    }

    @Step
    public void clickAgreementCamera(String quantityOfCamera){
        int num = Integer.parseInt(quantityOfCamera);
        if (num > 0){
            clickAgreementCamera();
            clickButtonFastMove();
            clickButtonAgree();
            clickButtonOrder();
            logger.info("quantityOfCamera > 0");
        }
        else {
            clickButtonOrder();
            logger.info("quantityOfCamera == 0");
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
    public void clickPaymentMethods(String typeOfPaymentMethod, String quantityOfDevices){

        if (Integer.parseInt(quantityOfDevices) > 0) {

            if (Integer.parseInt(typeOfPaymentMethod) == 0) {
                actionsWithOurElements.doubleClickElement(monthToMonth);
                logger.info("Payment Method monthToMonth was selected");
            } else if (Integer.parseInt(typeOfPaymentMethod) == 1){
                actionsWithOurElements.doubleClickElement(oneYearSubscription);
                logger.info("Payment Method oneYearSubscription was selected");
            } else if (Integer.parseInt(typeOfPaymentMethod) == 2){
                actionsWithOurElements.doubleClickElement(twoYearSubscription);
                logger.info("Payment Method twoYearSubscription was selected");
            }
        }
        else {
            logger.info("Payment Method was not selected no devices in order");
        }
    }

/*
PAYMENTS METHODS CAMERA
 */
    @Step
    public void clickPaymentMethodsCamera(String typeOfPaymentMethodCamera){
        if (isPaymentCameraInList(typeOfPaymentMethodCamera)){
            clickOnPaymentMethodCamera(typeOfPaymentMethodCamera);
            logger.info("Payment Method Camera" + typeOfPaymentMethodCamera + " was selected");
        }
        else {
            logger.info("Payment Method Camera was not selected");
        }
    }

    private void clickOnPaymentMethodCamera(String typeOfPaymentMethodCamera) {
        actionsWithOurElements.clickOnElement(".//*[@data-id='" + typeOfPaymentMethodCamera + "']");
    }

    private boolean isPaymentCameraInList(String typeOfPaymentMethodCamera) {
        return actionsWithOurElements.isElementInOrder(".//*[@data-id='" + typeOfPaymentMethodCamera + "']");
    }

 /*
BUTTON ORDER
 */
    @Step
    public void clickButtonOrder() {actionsWithOurElements.clickOnElement(orderButton);}


/*
COMPARE METHODS
 */

    public boolean compareTotalOrder(String eldTotalOrder){
        return getTotalOrder().equals("$" + eldTotalOrder);
    }
    public String getTotalOrder(){
        return totalOrderText.getText();
    }

    public String getFirstMonthFee(){
        return firstMonthFeeText.getText();
    }

    public String getLastMonthFee(){
        return lastMonthFeeText.getText();
    }

    public String getEldOneYearPrice(){
        return eldOneYearPriceText.getText();
    }

    public String getEldTwoYearPrice(){
        return eldTwoYearPriceText.getText();
    }

    public String getEldDepositFee(){
        return eldDepositFeeText.getText();
    }

    public String getEldDeliveryPrice(){
        return eldDeliveryPriceText.getText();
    }

    public String getEldPinCable(){
        return eldPinCableText.getText();
    }

    public String getEldOBDPinCable(){
        return eldOBDPinCableText.getText();
    }

    public String getEldStickerLabel(){
        return eldStickerLabelText.getText();
    }

    public boolean compareEldPrice(String  quantityOfDevices, String typeOfPaymentMethod, String eldFirstMonthFee, String eldLastMonthFee, String eldOneYearPrice, String eldTwoYearPrice) {

        if (Integer.parseInt(quantityOfDevices) > 0) {

            if (Integer.parseInt(typeOfPaymentMethod) == 0) {
                if (getFirstMonthFee().equals("$" + eldFirstMonthFee) == true)
                    return getLastMonthFee().equals("$" + eldLastMonthFee);
                else return false;

            } else if (Integer.parseInt(typeOfPaymentMethod) == 1) {
                return getEldOneYearPrice().equals("$" + eldOneYearPrice);

            } else if (Integer.parseInt(typeOfPaymentMethod) == 2) {
                return getEldTwoYearPrice().equals("$" + eldTwoYearPrice);
            } else return false;
        } return true;
    }

    public boolean compareDepositFee(String  quantityOfDevices, String eldDepositFee){
        if (Integer.parseInt(quantityOfDevices) > 0)
            return getEldDepositFee().equals("$" + eldDepositFee);
        else return true;
    }
    public boolean compareDeliveryPrice(String neededStatePickUpFromOffice, String eldDeliveryPrice){
        if (neededStatePickUpFromOffice.equals("uncheck"))
            return getEldDeliveryPrice().equals("$" + eldDeliveryPrice);
        else return true;
    }
    public boolean compareEldPinCable(String quantityPinCable, String eldPinCablePrice){
        if (Integer.parseInt(quantityPinCable) > 0 )
            return getEldPinCable().equals("$" + eldPinCablePrice);
        else return true;
    }
    public boolean compareEldOBDPinCable(String quantityOBDPinCable, String eldEldOBDPinCablePrice){
        if (Integer.parseInt(quantityOBDPinCable) > 0 )
            return getEldOBDPinCable().equals("$" + eldEldOBDPinCablePrice);
        else return true;
    }
    public boolean compareEldStickerLabel(String quantitySticker, String eldEldStickerLabelPrice){
        if (Integer.parseInt(quantitySticker) > 0 )
            return getEldStickerLabel().equals("$" + eldEldStickerLabelPrice);
        else return true;
    }

}


