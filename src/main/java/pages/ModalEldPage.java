package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
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

    @FindBy(xpath = ".//div[@data-id='7']")
    private WebElement buyoutContract;

    @FindBy(xpath = ".//div[@data-id='8']")
    private WebElement bankContract;


    @FindBy(xpath = "//*[text()='CP2 month fee $29.99']/../td[@class='text-center price']")
    private WebElement cP2MonthFeeText;

    @FindBy(xpath = "//*[text()='Camera setup fee $29.99']/../td[@class='text-center price']")
    private WebElement cameraSetupFeeText;

    @FindBy(xpath = "//*[text()='Camera installation fee $100']/../td[@class='text-center price']")
    private WebElement cameraInstallationFeeText;

    @FindBy(xpath = "//*[text()='EzSmartCam CP2']/../td[@class='text-center price']")
    private WebElement ezSmartCamCP2Text;

    @FindBy(xpath = "//*[text()='EzSmartCam SVA']/../td[@class='text-center price']")
    private WebElement ezSmartCamSVAText;

    @FindBy(xpath = "//*[text()='32GB-SD']/../td[@class='text-center price']")
    private WebElement sD32GbText;

    @FindBy(xpath = "//*[text()='64GB-SD']/../td[@class='text-center price']")
    private WebElement sD64GbText;

    @FindBy(xpath = "//*[text()='128GB-SD']/../td[@class='text-center price']")
    private WebElement sD128GbText;

    @FindBy(xpath = "//td[@class='hidden-xs hidden-sm']/select[@name='related_products[5][parent_id]']")
    private WebElement typeOfCdCard;





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

    public void doAgreementCamera() { actionsWithOurElements.clickOnElement(agreementCamera);}

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
            logger.info("Agreement camera was agreed");
        }
        else {
            logger.info("No agreement in order");
        }
    }

    @Step
    public void doAgreementCamera(String quantityCameraCP){
        int num = Integer.parseInt(quantityCameraCP);
        if (num > 0){
            doAgreementCamera();
            clickButtonFastMove();
            clickButtonAgree();
            logger.info("Agreement camera was agreed");
        }
        else {
            logger.info("No agreement camera in order");
        }
    }



/*
ORDER LIST
 */

    public void enterQuantityDevices(String quantityOfDevices) {actionsWithOurElements.enterTextToElement(quantityDeviseInput, quantityOfDevices);}

    public void enterQuantityPinCable(String quantityPinCable){actionsWithOurElements.enterTextToElement(quantityPinCableInput, quantityPinCable);}

    public void enterQuantityOBDPinCable(String quantityOBDPinCable){actionsWithOurElements.enterTextToElement(quantityOBDPinCableInput, quantityOBDPinCable);}

    public void enterQuantitySticker(String quantitySticker){actionsWithOurElements.enterTextToElement(quantityStickerInput, quantitySticker);}

    public void enterQuantityCameraCP(String quantityCameraCP){actionsWithOurElements.enterTextToElement(quantityCamera1Input, quantityCameraCP);}

    public void selectSdCard(String valueSdCard) {actionsWithOurElements.selectValueInDropDown(typeOfCdCard, valueSdCard);}

    public void enterQuantityCameraSVA(String quantityCameraSVA){actionsWithOurElements.enterTextToElement(quantityCamera2Input, quantityCameraSVA);}

    public void setPickUpFromOffice(String neededStatePickUpFromOffice){actionsWithOurElements.setNeededStateToCheckBox(checkBoxPickUp, neededStatePickUpFromOffice);}

    public void setOvernightDelivery(String neededStateOvernightDelivery){actionsWithOurElements.setNeededStateToCheckBox(checkBoxOvernightDelivery, neededStateOvernightDelivery);}

    @Step
    public void enterOrderData(String  quantityOfDevices, String quantityPinCable, String quantityOBDPinCable, String quantitySticker, String quantityCameraCP, String valueSdCard, String quantityCameraSVA, String neededStatePickUpFromOffice, String neededStateOvernightDelivery){
        enterQuantityDevices(quantityOfDevices);
        enterQuantityPinCable(quantityPinCable);
        enterQuantityOBDPinCable(quantityOBDPinCable);
        enterQuantitySticker(quantitySticker);
        enterQuantityCameraCP(quantityCameraCP);
        selectSdCard(valueSdCard);
        enterQuantityCameraSVA(quantityCameraSVA);
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
            } else Assert.fail();
        }
        else {
            logger.info("Payment Method was not selected, no devices in order");
        }
    }

/*
PAYMENTS METHODS CAMERA
 */
    @Step
    public void clickPaymentMethodsCamera(String typeOfPaymentMethodCamera, String quantityCameraCP){
        if (Integer.parseInt(quantityCameraCP) > 0) {

            if (Integer.parseInt(typeOfPaymentMethodCamera) == 7) {
                actionsWithOurElements.doubleClickElement(buyoutContract);
                logger.info("Payment Method buyoutContract was selected");
            } else if (Integer.parseInt(typeOfPaymentMethodCamera) == 8){
                actionsWithOurElements.doubleClickElement(bankContract);
                logger.info("Payment Method bankContract was selected");
            } else Assert.fail();
        }
        else {
            logger.info("Payment Method Camera was not selected, no camera in order");
        }
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

/*
COMPARE METHODS CAMERA
 */
    public String getCP2MonthFee(){
    return cP2MonthFeeText.getText();
    }
    public String getCameraSetupFee() {
        return cameraSetupFeeText.getText();
    }
    public String getCameraInstallationFee() {
        return cameraInstallationFeeText.getText();
    }
    public String getEzSmartCamCP2() {
        return ezSmartCamCP2Text.getText();
    }
    public String getEzSmartCamSVA() {
        return ezSmartCamSVAText.getText();
    }
    public String getSd32Gb() {
        return sD32GbText.getText();
    }
    public String getSd64Gb() {
        return sD64GbText.getText();
    }
    public String getSd128Gb() {
        return sD128GbText.getText();
    }

    public boolean compareCP2MonthFee(String quantityCameraCP, String cP2MonthFee){
        if (Integer.parseInt(quantityCameraCP) > 0 )
            return getCP2MonthFee().equals("$" + cP2MonthFee);
        else return true;
    }
    public boolean compareCameraSetupFee(String quantityCameraCP, String cameraSetupFee){
        if (Integer.parseInt(quantityCameraCP) > 0 )
            return getCameraSetupFee().equals("$" + cameraSetupFee);
        else return true;
    }
    public boolean compareCameraInstallationFee(String quantityCameraCP, String cameraInstallationFee){
        if (Integer.parseInt(quantityCameraCP) > 0 )
            return getCameraInstallationFee().equals("$" + cameraInstallationFee);
        else return true;
    }
    public boolean compareEzSmartCamCP2(String quantityCameraCP, String ezSmartCamCP2){
        if (Integer.parseInt(quantityCameraCP) > 0 )
            return getEzSmartCamCP2().equals("$" + ezSmartCamCP2);
        else return true;
    }
    public boolean compareEzSmartCamSVA(String quantityCameraCP, String ezSmartCamSVA){
        if (Integer.parseInt(quantityCameraCP) > 0 )
            return getEzSmartCamSVA().equals("$" + ezSmartCamSVA);
        else return true;
    }
    public boolean compareSdCard(String quantityCameraCP, String valueSdCard, String sD32Gb, String sD64Gb, String sD128Gb){
        if (Integer.parseInt(quantityCameraCP) > 0 ){
            if (Integer.parseInt(valueSdCard) == 7 )
                return getSd32Gb().equals("$" + sD32Gb);
            else if (Integer.parseInt(valueSdCard) == 8 )
                return getSd64Gb().equals("$" + sD64Gb);
            else if (Integer.parseInt(valueSdCard) == 9 )
                return getSd128Gb().equals("$" + sD128Gb);
            else Assert.fail();
        } return true;
    }
}


