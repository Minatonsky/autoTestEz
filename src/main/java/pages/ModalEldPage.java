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
            logger.info("quantityOfDevices > 0");
        }
        else {
            clickButtonOrder();
            logger.info("quantityOfDevices == 0");
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
        actionsWithOurElements.clickOnElement(".//*[@data-id='" + typeOfPaymentMethod + "']");
    }

    private boolean isPaymentInList(String typeOfPaymentMethod) {
        return actionsWithOurElements.isElementInOrder(".//*[@data-id='" + typeOfPaymentMethod + "']");
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


    @Step
    public boolean compareTotalOrder(String eldTotalOrder){
        return actionsWithOurElements.isElementInOrder("//td[2][text()='$" + eldTotalOrder + "' and //*[text()='TOTAL' ]]");
    }

    public boolean compareFirstMonthFee(String eldFirstMonthFee){
        return actionsWithOurElements.isElementInOrder(".//input[@value='ELD first month fee $29.99']/following-sibling::input[@name='credit[services][fee_price][total]' and @value='" + eldFirstMonthFee + "']");
    }

    public boolean compareLastMonthFee(String eldLastMonthFee) {
        return actionsWithOurElements.isElementInOrder(".//input[@value='ELD last month fee $29.99']/following-sibling::input[@name='credit[services][last_month_fee][total]' and @value='" + eldLastMonthFee + "']");
    }

    public boolean compareEldOneYearPrice(String eldOneYearPrice) {
        return actionsWithOurElements.isElementInOrder(".//input[@value='ELD price $329.89']/following-sibling::input[@name='credit[services][orderPrice][total]' and @value='" + eldOneYearPrice + "']");
    }

    public boolean compareEldTwoYearPrice(String eldTwoYearPrice) {
        return actionsWithOurElements.isElementInOrder(".//input[@value='ELD price $629.79']/following-sibling::input[@name='credit[services][orderPrice][total]' and @value='" + eldTwoYearPrice + "']");
    }

    @Step
    public boolean compareDepositFee(String eldDepositFee){
        return actionsWithOurElements.isElementInOrder(".//input[@value='ELD Deposit fee $49.99']/following-sibling::input[@name='credit[services][deposit_fee][total]' and @value='" + eldDepositFee + "']");
    }

    @Step
    public boolean compareDeliveryPrice(String eldDeliveryPrice){
        return actionsWithOurElements.isElementInOrder(".//input[@value='Delivery price']/following-sibling::input[@name='credit[services][delivery_price][total]' and @value='" + eldDeliveryPrice + "']");
    }

    @Step
    public boolean comparePinCable(String eldPinCable){
        return actionsWithOurElements.isElementInOrder(".//input[@value='6 Pin Cable']/following-sibling::input[@name='credit[products][2][total]' and @value='" + eldPinCable + "']");
    }

    @Step
    public boolean compareOBDPinCable(String eldOBDPinCable){
        return actionsWithOurElements.isElementInOrder(".//input[@value='OBDII 16 pin Cable']/following-sibling::input[@name='credit[products][3][total]' and @value='" + eldOBDPinCable + "']");
    }

    @Step
    public boolean compareStickerLabel(String eldStickerLabel){
        return actionsWithOurElements.isElementInOrder(".//input[@value='Sticker Label']/following-sibling::input[@name='credit[products][4][total]' and @value='" + eldStickerLabel + "']");
    }

    public void compareEldPrice(String  quantityOfDevices, String typeOfPaymentMethod, String eldFirstMonthFee, String eldLastMonthFee, String eldOneYearPrice, String eldTwoYearPrice){

        if (Integer.parseInt(quantityOfDevices) > 0){

            if (Integer.parseInt(typeOfPaymentMethod) == 0){
                if (compareFirstMonthFee(eldFirstMonthFee) == true){
                    logger.info("AC failed: FirstMonthFee is correct");
                }
                if (compareLastMonthFee(eldLastMonthFee) == true){
                    logger.info("AC failed: LastMonthFee is correct");
                }
                else {
                    logger.info("AC failed: LastMonthFee/FirstMonthFee is not correct");
                }

            }
            else if (typeOfPaymentMethod == "1"){
                compareEldOneYearPrice(eldOneYearPrice);
                if (compareEldOneYearPrice(eldOneYearPrice) == true){
                    logger.info("AC failed: EldOneYearPrice is correct");
                }
                else {
                    logger.info("AC failed: EldOneYearPrice is not correct");
                }
            }
            else if (typeOfPaymentMethod == "2") {
                compareEldTwoYearPrice(eldTwoYearPrice);
                if (compareEldTwoYearPrice(eldTwoYearPrice) == true){
                    logger.info("AC failed: EldOneYearPrice is correct");
                }
                else {
                    logger.info("AC failed: EldOneYearPrice is not correct");
                }
            }
            else {
                logger.info(" no compareable");
            }
        }
        else {
            logger.info("No ELD Devices in Order");
        }

    }

    public void compareOrderPrices(String eldTotalOrder, String eldPinCable, String eldDeliveryPrice, String eldDepositFee, String eldStickerLabel, String eldOBDPinCable) {
        compareTotalOrder(eldTotalOrder);
        comparePinCable(eldPinCable);
        compareDeliveryPrice(eldDeliveryPrice);
        compareDepositFee(eldDepositFee);
        compareStickerLabel(eldStickerLabel);
        compareOBDPinCable(eldOBDPinCable);

    }
}
