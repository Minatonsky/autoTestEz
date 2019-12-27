package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static libs.Utils.waitABit;

public class ModalEldPage extends ParentPage {

    @FindBy(name = "device_type_id")
    private WebElement device_type_idInput;

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

    @FindBy(name = "products[10]")
    private WebElement quantityEzHardWareCable;

    @FindBy(xpath = ".//*[text()='ELD device price $169.99']/../td[@class='text-center price']")
    private WebElement ELDDevicePriceTextSmart;

    @FindBy(xpath = ".//*[text()='ELD device price $149.99']/../td[@class='text-center price']")
    private WebElement ELDDevicePriceTextHard;

    @FindBy(xpath = ".//*[text()='TOTAL']/../../td[@class='text-center price']")
    private WebElement totalOrderText;

    @FindBy(xpath = ".//*[text()='ELD first month fee $29.99']/../td[@class='text-center price']")
    private WebElement firstMonthFeeText;

    @FindBy(xpath = ".//*[text()='ELD last month fee $29.99']/../td[@class='text-center price']")
    private WebElement lastMonthFeeText;

    @FindBy(xpath = ".//*[text()='ELD price $329.89']/../td[@class='text-center price']")
    private WebElement eldOneYearPriceText;

    @FindBy(xpath = ".//*[text()='ELD price $299.88']/../td[@class='text-center price']")
    private WebElement eldOneYearPriceText2;

    @FindBy(xpath = ".//*[text()='ELD price $629.79']/../td[@class='text-center price']")
    private WebElement eldTwoYearPriceText;

    @FindBy(xpath = ".//*[text()='ELD price $599.76']/../td[@class='text-center price']")
    private WebElement eldTwoYearPriceText2;

    @FindBy(xpath = ".//*[text()='ELD Deposit fee $49.99']/../td[@class='text-center price']")
    private WebElement eldDepositFeeText;

    @FindBy(xpath = ".//*[text()='Delivery price']/../td[@class='text-center price']")
    private WebElement eldDeliveryPriceText;

    @FindBy(xpath = ".//*[text()='6 Pin Cable']/../td[@class='text-center price']")
    private WebElement eldPinCableText;

    @FindBy(xpath = ".//*[text()='Ez-hard-ware-cable']/../td[@class='text-center price']")
    private WebElement eldEzHardCableText;

    @FindBy(xpath = ".//*[text()='OBDII 16 pin Cable']/../td[@class='text-center price']")
    private WebElement eldOBDPinCableText;

    @FindBy(xpath = ".//*[text()='Sticker Label']/../td[@class='text-center price']")
    private WebElement eldStickerLabelText;

    @FindBy(xpath = ".//div[@data-id='0']")
    private WebElement monthToMonth;

    @FindBy(xpath = ".//div[@data-id='9']")
    private WebElement monthToMonth9;

    @FindBy(xpath = ".//div[@data-id='12']")
    private WebElement monthToMonth12;

    @FindBy(xpath = ".//*[@data-id='1']")
    private WebElement oneYearSubscription;

    @FindBy(xpath = ".//*[@data-id='10']")
    private WebElement oneYearSubscription10;

    @FindBy(xpath = ".//*[@data-id='13']")
    private WebElement oneYearSubscription13;

    @FindBy(xpath = ".//div[@data-id='2']")
    private WebElement twoYearSubscription;

    @FindBy(xpath = ".//div[@data-id='11']")
    private WebElement twoYearSubscription11;

    @FindBy(xpath = ".//div[@data-id='14']")
    private WebElement twoYearSubscription14;

    @FindBy(xpath = ".//div[@data-id='7']")
    private WebElement buyoutContract;

    @FindBy(xpath = ".//div[@data-id='8']")
    private WebElement bankContract;


    @FindBy(xpath = ".//*[text()='CP2 month fee $29.99']/../td[@class='text-center price']")
    private WebElement cP2MonthFeeText;

    @FindBy(xpath = ".//*[text()='Camera setup fee $29.99']/../td[@class='text-center price']")
    private WebElement cameraSetupFeeText;

    @FindBy(xpath = ".//*[text()='Camera installation fee $100']/../td[@class='text-center price']")
    private WebElement cameraInstallationFeeText;

    @FindBy(xpath = ".//*[text()='EzSmartCam CP2']/../td[@class='text-center price']")
    private WebElement ezSmartCamCP2Text;

    @FindBy(xpath = ".//*[text()='EzSmartCam SVA']/../td[@class='text-center price']")
    private WebElement ezSmartCamSVAText;

    @FindBy(xpath = ".//*[text()='32GB-SD']/../td[@class='text-center price']")
    private WebElement sD32GbText;

    @FindBy(xpath = ".//*[text()='64GB-SD']/../td[@class='text-center price']")
    private WebElement sD64GbText;

    @FindBy(xpath = ".//*[text()='128GB-SD']/../td[@class='text-center price']")
    private WebElement sD128GbText;

    @FindBy(xpath = ".//td[@class='hidden-xs hidden-sm']/select[@name='related_products[5][parent_id]']")
    private WebElement typeOfCdCard;

    @FindBy(xpath = ".//h1[text()='EQUIPMENT LEASE']")
    private WebElement leasTitle;

    @FindBy(xpath = ".//h1[text()='SOFTWARE SUBSCRIPTION SERVICE (SaaS) AGREEMENT']")
    private WebElement saasTitle;

    @FindBy(xpath = ".//h1[text()='SOFTWARE SUBSCRIPTION SERVICE (SaaS) AGREEMENT']")
    private WebElement saasGeometricsTitle;

    @FindBy(xpath = ".//h1[text()='EQUIPMENT PURCHASE']")
    private WebElement equipmentPurchaseTitle;

    @FindBy(xpath = ".//h1[text()='EZSMARTCAM PURCHASE AND DATA SERVICES AGREEMENT']")
    private WebElement cameraTitle;

    double ELDDevicePriceSmart = 169.99;
    double ELDDevicePriceHard = 149.99;
    double eldMonthToMonthPrice = 29.99;
    double eld1YearSubscriptionPrice = 329.89;
    double eld2YearsSubscriptionPrice = 629.79;
    double eld1YearSubscriptionDiscountPrice = 299.88;
    double eld2YearsSubscriptionDiscountPrice = 599.76;
    double pinCablePrice = 34.99;
    double pinCableOBDIIPrice = 34.99;
    double stickerLabelPrice = 3.00;
    double ezSmartCamCP2Price = 564.99;
    double ezSmartCamSVAPrice = 399.99;
    double sD32GbPrice = 39.99;
    double sD64GbPrice = 59.99;
    double sD128GbPrice = 109.99;
    double cameraInstallationFee = 100.00;
    double cameraSetupFee = 29.99;
    double cP2MonthFee = 29.99;
    double eldDepositFee = 49.99;

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
    public void checkForSaasLeasTitleInAgreement(){
        actionsWithOurElements.isElementDisplay(saasTitle);
    }
    public void checkForLeasTitleInAgreement(){
        actionsWithOurElements.isElementDisplay(leasTitle);
    }
    public void checkForCameraTitleInAgreement(){
        actionsWithOurElements.isElementDisplay(cameraTitle);
    }
    public void checkForSaasGeometricsTitleInAgreement(){
        actionsWithOurElements.isElementDisplay(saasTitle);
    }
    public void checkEquipmentPurchaseTitleInAgreement(){
        actionsWithOurElements.isElementDisplay(equipmentPurchaseTitle);
    }

    public void checkTitlesInDevicesAgreement(String valueDeviceTypeId){
        if (Integer.parseInt(valueDeviceTypeId) == 1){
            waitABit(3);
            checkForLeasTitleInAgreement();
            waitABit(2);
            checkForSaasLeasTitleInAgreement();
            waitABit(2);
        } else if (Integer.parseInt(valueDeviceTypeId) == 2){
            waitABit(3);
            checkForSaasGeometricsTitleInAgreement();
            waitABit(2);
            checkEquipmentPurchaseTitleInAgreement();
            waitABit(2);
        } else if (Integer.parseInt(valueDeviceTypeId) == 3){
            waitABit(3);
            checkForSaasGeometricsTitleInAgreement();
            waitABit(2);
            checkEquipmentPurchaseTitleInAgreement();
            waitABit(2);
        }

    }

    @Step
    public void doCancelAgreementForManagerOrder(String valueDeviceTypeId, String quantityOfDevices, String quantityCameraCP){
        if (Integer.parseInt(quantityOfDevices) > 0 & Integer.parseInt(quantityCameraCP) > 0 ){
            checkTitlesInDevicesAgreement(valueDeviceTypeId);
            checkForCameraTitleInAgreement();
            clickButtonFastMove();
            clickButtonCancel();
            waitABit(3);
            clickButtonConfirm();
            waitABit(3);
            logger.info("Order was canceled");
        } else if (Integer.parseInt(quantityOfDevices) > 0 & Integer.parseInt(quantityCameraCP) == 0){
            checkTitlesInDevicesAgreement(valueDeviceTypeId);
            clickButtonFastMove();
            clickButtonCancel();
            waitABit(3);
            clickButtonConfirm();
            waitABit(3);
            logger.info("Order was canceled");
        } else if (Integer.parseInt(quantityOfDevices) == 0 & Integer.parseInt(quantityCameraCP) > 0) {
            waitABit(3);
            checkForCameraTitleInAgreement();
            clickButtonFastMove();
            clickButtonCancel();
            waitABit(3);
            clickButtonConfirm();
            waitABit(3);
            logger.info("Order was canceled");
        } else logger.info("No device and camera in order");

    }

    @Step
    public void doAgreeAgreementForManagerOrder(String valueDeviceTypeId, String quantityOfDevices, String quantityCameraCP){
        if (Integer.parseInt(quantityOfDevices) > 0 & Integer.parseInt(quantityCameraCP) > 0 ){
            waitABit(3);
            checkForLeasTitleInAgreement();
            waitABit(2);
            checkForSaasLeasTitleInAgreement();
            waitABit(2);
            checkForCameraTitleInAgreement();
            clickButtonFastMove();
            clickButtonAgree();
            waitABit(10);
            logger.info("Order was approve");
        } else if (Integer.parseInt(quantityOfDevices) > 0 & Integer.parseInt(quantityCameraCP) == 0){
            checkTitlesInDevicesAgreement(valueDeviceTypeId);
            clickButtonFastMove();
            clickButtonAgree();
            waitABit(10);
            logger.info("Order was approve");
        } else if (Integer.parseInt(quantityOfDevices) == 0 & Integer.parseInt(quantityCameraCP) > 0) {
            waitABit(3);
            checkForCameraTitleInAgreement();
            clickButtonFastMove();
            clickButtonAgree();
            waitABit(10);
            logger.info("Order was approve");
        } else logger.info("No device and camera in order");


    }

    @Step
    public void doAgreeAgreement(String valueDeviceTypeId, String quantityOfDevices){

        if (Integer.parseInt(quantityOfDevices) > 0){
            clickAgreement();
            checkTitlesInDevicesAgreement(valueDeviceTypeId);
            clickButtonFastMove();
            clickButtonAgree();
            logger.info("Agreement ELD was agreed");
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
            checkForCameraTitleInAgreement();
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
    public void selectDeviceTypeId(String valueDeviceTypeId, String quantityOfDevices){
//        if (Integer.parseInt(quantityOfDevices) > 0) {
            actionsWithOurElements.selectValueInDropDown(device_type_idInput, valueDeviceTypeId);
//        } else logger.info("No devices in order");
    }
    public void enterQuantityDevices(String quantityOfDevices) {
        if (Integer.parseInt(quantityOfDevices) > 0) {
            actionsWithOurElements.enterTextToElement(quantityDeviseInput, quantityOfDevices);
        } else logger.info("No devices in order");
    }
    public void enterQuantityPinCable(String quantityPinCable, String valueDeviceTypeId){
        if (Integer.parseInt(quantityPinCable) > 0 && valueDeviceTypeId.equals("1")) {
            actionsWithOurElements.enterTextToElement(quantityPinCableInput, quantityPinCable);
        } else if (Integer.parseInt(quantityPinCable) > 0 && valueDeviceTypeId.equals("3")){
            actionsWithOurElements.enterTextToElement(quantityEzHardWareCable, quantityPinCable);
        } else logger.info("No Pin Cable in order");
    }
    public void enterQuantityOBDPinCable(String quantityOBDPinCable){
        if (Integer.parseInt(quantityOBDPinCable) > 0) {
            actionsWithOurElements.enterTextToElement(quantityOBDPinCableInput, quantityOBDPinCable);
        } else logger.info("No OBDP Cable in order");
    }
    public void enterQuantitySticker(String quantitySticker) {
        if (Integer.parseInt(quantitySticker) > 0) {
            actionsWithOurElements.enterTextToElement(quantityStickerInput, quantitySticker);
        } else logger.info("No Sticker in order");
    }
    public void enterQuantityCameraCP(String quantityCameraCP){
        if (Integer.parseInt(quantityCameraCP) > 0) {
            actionsWithOurElements.enterTextToElement(quantityCamera1Input, quantityCameraCP);
        } else logger.info("No Camera CP in order");
    }
    public void selectSdCard(String valueSdCard, String quantityCameraCP) {
        if (Integer.parseInt(quantityCameraCP) > 0) {
            actionsWithOurElements.selectValueInDropDown(typeOfCdCard, valueSdCard);
        }else logger.info("No cd card in order");
    }
    public void enterQuantityCameraSVA(String quantityCameraSVA, String quantityCameraCP){
        if (Integer.parseInt(quantityCameraCP) > 0) {
            actionsWithOurElements.enterTextToElement(quantityCamera2Input, quantityCameraSVA);
        }else logger.info("No camera sva in order");
       }
    public void setPickUpFromOffice(String neededStatePickUpFromOffice){actionsWithOurElements.setNeededStateToCheckBox(checkBoxPickUp, neededStatePickUpFromOffice);}
    public void setOvernightDelivery(String neededStateOvernightDelivery){actionsWithOurElements.setNeededStateToCheckBox(checkBoxOvernightDelivery, neededStateOvernightDelivery);}

    @Step
    public void enterOrderData(String valueDeviceTypeId, String  quantityOfDevices, String quantityPinCable, String quantityOBDPinCable, String quantitySticker, String quantityCameraCP, String valueSdCard, String quantityCameraSVA, String neededStatePickUpFromOffice, String neededStateOvernightDelivery){
        selectDeviceTypeId(valueDeviceTypeId, quantityOfDevices);
        waitABit(2);
        enterQuantityDevices(quantityOfDevices);
        waitABit(5);
        enterQuantityPinCable(quantityPinCable, valueDeviceTypeId);
        enterQuantityOBDPinCable(quantityOBDPinCable);
        enterQuantitySticker(quantitySticker);
        enterQuantityCameraCP(quantityCameraCP);
        selectSdCard(valueSdCard, quantityCameraCP);
        enterQuantityCameraSVA(quantityCameraSVA, quantityCameraCP);
        waitABit(2);
        setPickUpFromOffice(neededStatePickUpFromOffice);
        setOvernightDelivery(neededStateOvernightDelivery);
    }


/*
PAYMENT METHODS ELD, CAMERA AND BUTTON ORDER
 */

    @Step
    public void clickPaymentMethods(String typeOfPaymentMethod, String quantityOfDevices){

        if (Integer.parseInt(quantityOfDevices) > 0) {
            if (Integer.parseInt(typeOfPaymentMethod) == 0) {
                actionsWithOurElements.clickOnElement(monthToMonth);
                logger.info("Payment Method monthToMonth was selected");
            } else if (Integer.parseInt(typeOfPaymentMethod) == 1){
                actionsWithOurElements.clickOnElement(oneYearSubscription);
                actionsWithOurElements.clickOnElement(oneYearSubscription);
                logger.info("Payment Method oneYearSubscription was selected");
            } else if (Integer.parseInt(typeOfPaymentMethod) == 2){
                actionsWithOurElements.clickOnElement(twoYearSubscription);
                actionsWithOurElements.clickOnElement(twoYearSubscription);
                logger.info("Payment Method twoYearSubscription was selected");
            } else if (Integer.parseInt(typeOfPaymentMethod) == 9){
                actionsWithOurElements.clickOnElement(monthToMonth9);
                actionsWithOurElements.clickOnElement(monthToMonth9);
                logger.info("Payment Method monthToMonth was selected");
            } else if (Integer.parseInt(typeOfPaymentMethod) == 10){
                actionsWithOurElements.clickOnElement(oneYearSubscription10);
                actionsWithOurElements.clickOnElement(oneYearSubscription10);
                logger.info("Payment Method oneYearSubscription was selected");
            } else if (Integer.parseInt(typeOfPaymentMethod) == 11){
                actionsWithOurElements.clickOnElement(twoYearSubscription11);
                actionsWithOurElements.clickOnElement(twoYearSubscription11);
                logger.info("Payment Method twoYearSubscription was selected");
            } else if (Integer.parseInt(typeOfPaymentMethod) == 12){
                actionsWithOurElements.clickOnElement(monthToMonth12);
                actionsWithOurElements.clickOnElement(monthToMonth12);
                logger.info("Payment Method monthToMonth was selected");
            } else if (Integer.parseInt(typeOfPaymentMethod) == 13){
                actionsWithOurElements.clickOnElement(oneYearSubscription13);
                actionsWithOurElements.clickOnElement(oneYearSubscription13);
                logger.info("Payment Method oneYearSubscription was selected");
            } else if (Integer.parseInt(typeOfPaymentMethod) == 14){
                actionsWithOurElements.clickOnElement(twoYearSubscription14);
                actionsWithOurElements.clickOnElement(twoYearSubscription14);
                logger.info("Payment Method twoYearSubscription was selected");
            }
        } else {
            logger.info("Payment Method was not selected, no devices in order");
        }
    }

    @Step
    public void clickPaymentMethodsCamera(String typeOfPaymentMethodCamera, String quantityCameraCP){
        if (Integer.parseInt(quantityCameraCP) > 0) {
            if (Integer.parseInt(typeOfPaymentMethodCamera) == 7) {
                actionsWithOurElements.clickOnElement(buyoutContract);
                logger.info("Payment Method buyoutContract was selected");
            } else if (Integer.parseInt(typeOfPaymentMethodCamera) == 8){
                actionsWithOurElements.clickOnElement(bankContract);
                logger.info("Payment Method bankContract was selected");
            } else Assert.fail();
        } else {
            logger.info("Payment Method Camera was not selected, no camera in order");
        }
    }

    @Step
    public void clickButtonOrder() {actionsWithOurElements.clickOnElement(orderButton);}


/*
COMPARE METHODS
 */
    public String getELDDevicePrice(String typeOfDevices){
        if (typeOfDevices.equals("2")) {
            return ELDDevicePriceTextSmart.getText();
        } else if (typeOfDevices.equals("3")) {
            return ELDDevicePriceTextHard.getText();
        } else return "0";
    }

    public String getTotalOrder(){
        return totalOrderText.getText();
    }
    public String getFirstMonthFee(){
        return firstMonthFeeText.getText();
    }
    public String getLastMonthFee(){ return lastMonthFeeText.getText(); }
    public String getEldOneYearPrice(){
        return eldOneYearPriceText.getText();
    }
    public String getEldOneYearPrice2(){
        return eldOneYearPriceText2.getText();
    }
    public String getEldTwoYearPrice(){
        return eldTwoYearPriceText.getText();
    }
    public String getEldTwoYearPrice2(){
        return eldTwoYearPriceText2.getText();
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
    public String getEzHardPinCable(){
        return eldEzHardCableText.getText();
    }
    public String getEldOBDPinCable(){
        return eldOBDPinCableText.getText();
    }
    public String getEldStickerLabel(){
        return eldStickerLabelText.getText();
    }

    public boolean compareEldPrice(String  quantityOfDevices, String typeOfPaymentMethod, String quantityCameraCP) {
        int tempQuantityDevices1 = (Integer.parseInt(quantityOfDevices) - Integer.parseInt(quantityCameraCP));
        int tempQuantityDevices2 = (Integer.parseInt(quantityOfDevices) - tempQuantityDevices1);

        if (Integer.parseInt(quantityOfDevices) > 0 && Integer.parseInt(quantityCameraCP) == 0) {
            if (Integer.parseInt(typeOfPaymentMethod) == 0 | Integer.parseInt(typeOfPaymentMethod) == 9 | Integer.parseInt(typeOfPaymentMethod) == 12) {
                double tempPrice = Math.round((Integer.parseInt(quantityOfDevices) * eldMonthToMonthPrice) * 100.0) / 100.0;
                if ((Double.parseDouble(getFirstMonthFee().substring(1)) == tempPrice)) {
                    return Double.parseDouble(getLastMonthFee().substring(1)) == tempPrice;
                }
            } else if (Integer.parseInt(typeOfPaymentMethod) == 1 | Integer.parseInt(typeOfPaymentMethod) == 10 | Integer.parseInt(typeOfPaymentMethod) == 13) {
                double tempPrice1 = Math.round((Integer.parseInt(quantityOfDevices) * eld1YearSubscriptionPrice) * 100.0) / 100.0;
                return Double.parseDouble(getEldOneYearPrice().substring(1)) == tempPrice1;

            } else if (Integer.parseInt(typeOfPaymentMethod) == 2 | Integer.parseInt(typeOfPaymentMethod) == 11 | Integer.parseInt(typeOfPaymentMethod) == 14) {
                double tempPrice2 = Math.round((Integer.parseInt(quantityOfDevices) * eld2YearsSubscriptionPrice) * 100.0) / 100.0;
                return Double.parseDouble(getEldTwoYearPrice().substring(1)) == tempPrice2;
            }

        } else if (Integer.parseInt(quantityOfDevices) > 0 && Integer.parseInt(quantityCameraCP) > 0){
                if (Integer.parseInt(quantityOfDevices) <= Integer.parseInt(quantityCameraCP)){
                    if (Integer.parseInt(typeOfPaymentMethod) == 0) {
                        double tempPrice = Math.round((Integer.parseInt(quantityOfDevices) * eldMonthToMonthPrice) * 100.0) / 100.0;
                        if ((Double.parseDouble(getFirstMonthFee().substring(1)) == tempPrice)) {
                            return Double.parseDouble(getLastMonthFee().substring(1)) == tempPrice;
                        }
                    } else if (Integer.parseInt(typeOfPaymentMethod) == 1) {
                        double tempPrice = Math.round((Integer.parseInt(quantityOfDevices) * eld1YearSubscriptionDiscountPrice) * 100.0) / 100.0;
                        return Double.parseDouble(getEldOneYearPrice2().substring(1)) == tempPrice;
                    } else if (Integer.parseInt(typeOfPaymentMethod) == 2) {
                        double tempPrice = Math.round((Integer.parseInt(quantityOfDevices) * eld2YearsSubscriptionDiscountPrice) * 100.0) / 100.0;
                        return Double.parseDouble(getEldTwoYearPrice2().substring(1)) == tempPrice;
                    }
                } else if (Integer.parseInt(quantityOfDevices) > Integer.parseInt(quantityCameraCP)){
                    if (Integer.parseInt(typeOfPaymentMethod) == 0) {
                        double tempPrice = Math.round((Integer.parseInt(quantityOfDevices) * eldMonthToMonthPrice) * 100.0) / 100.0;
                        if ((Double.parseDouble(getFirstMonthFee().substring(1)) == tempPrice)) {
                            return Double.parseDouble(getLastMonthFee().substring(1)) == tempPrice;
                        }
                    } else if (Integer.parseInt(typeOfPaymentMethod) == 1) {
                        double tempPrice1 = Math.round((tempQuantityDevices1 * eld1YearSubscriptionPrice) * 100.0) / 100.0;
                        double tempPrice2 = Math.round((tempQuantityDevices2 * eld1YearSubscriptionDiscountPrice) * 100.0) / 100.0;
                        if ((Double.parseDouble(getEldOneYearPrice().substring(1)) == tempPrice1)) {
                            return Double.parseDouble(getEldOneYearPrice2().substring(1)) == tempPrice2;
                        }
                    } else if (Integer.parseInt(typeOfPaymentMethod) == 2) {
                        double tempPrice1 = Math.round((tempQuantityDevices1 * eld2YearsSubscriptionPrice) * 100.0) / 100.0;
                        double tempPrice2 = Math.round((tempQuantityDevices2 * eld2YearsSubscriptionDiscountPrice) * 100.0) / 100.0;
                        if ((Double.parseDouble(getEldTwoYearPrice().substring(1)) == tempPrice1)) {
                            return Double.parseDouble(getEldTwoYearPrice2().substring(1)) == tempPrice2;
                        }
                    }
                } else return false;

        } else if (Integer.parseInt(quantityOfDevices) == 0){
            return true;

        } return false;

    }
    public boolean compareDepositFee(String typeOfDevices, String  quantityOfDevices){
        double tempPriceDeposit = Math.round((Integer.parseInt(quantityOfDevices) * eldDepositFee) * 100.0) / 100.0;
        double tempPriceEldPriceSmart = Math.round((Integer.parseInt(quantityOfDevices) * ELDDevicePriceSmart) * 100.0) / 100.0;
        double tempPriceEldPriceHard = Math.round((Integer.parseInt(quantityOfDevices) * ELDDevicePriceHard) * 100.0) / 100.0;
        if (Integer.parseInt(quantityOfDevices) > 0 && Integer.parseInt(typeOfDevices) == 1) {
            return Double.parseDouble(getEldDepositFee().substring(1)) == tempPriceDeposit;
        } else if (Integer.parseInt(quantityOfDevices) > 0 && Integer.parseInt(typeOfDevices) == 2){
            return Double.parseDouble(getELDDevicePrice(typeOfDevices).substring(1)) == tempPriceEldPriceSmart;
        } else if (Integer.parseInt(quantityOfDevices) > 0 && Integer.parseInt(typeOfDevices) == 3){
            return Double.parseDouble(getELDDevicePrice(typeOfDevices).substring(1)) == tempPriceEldPriceHard;
        } else return true;
    }
    public boolean compareDeliveryPrice(String neededStatePickUpFromOffice){
        if (neededStatePickUpFromOffice.equals("uncheck")) {
            return actionsWithOurElements.isElementDisplay(eldDeliveryPriceText);
        } else if (neededStatePickUpFromOffice.equals("check")){
            return !actionsWithOurElements.isElementDisplay(eldDeliveryPriceText);
        } return false;
    }
    public boolean compareEldPinCable(String quantityPinCable, String typeOfDevices){
        double tempPrice = Math.round((Integer.parseInt(quantityPinCable) * pinCablePrice) * 100.0) / 100.0;
        if (Integer.parseInt(quantityPinCable) > 0 &&  typeOfDevices.equals("1")) {
            return Double.parseDouble(getEldPinCable().substring(1)) == tempPrice;
        } else if (Integer.parseInt(quantityPinCable) > 0 &&  typeOfDevices.equals("3")) {
            return Double.parseDouble(getEzHardPinCable().substring(1)) == tempPrice;
        }else return true;
    }
    public boolean compareEldOBDPinCable(String quantityOBDPinCable){
        double tempPrice = Math.round((Integer.parseInt(quantityOBDPinCable) * pinCableOBDIIPrice) * 100.0) / 100.0;
        if (Integer.parseInt(quantityOBDPinCable) > 0 ) {
            return Double.parseDouble(getEldOBDPinCable().substring(1)) == tempPrice;
        } else return true;
    }
    public boolean compareEldStickerLabel(String quantitySticker){
        double tempPrice = Math.round((Integer.parseInt(quantitySticker) * stickerLabelPrice) * 100.0) / 100.0;
        if (Integer.parseInt(quantitySticker) > 0 ) {
            return Double.parseDouble(getEldStickerLabel().substring(1)) == tempPrice;
        } else return true;
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

    public boolean compareCP2MonthFee(String quantityCameraCP){
        double tempPrice = Math.round((Integer.parseInt(quantityCameraCP) * cP2MonthFee) * 100.0) / 100.0;
        if (Integer.parseInt(quantityCameraCP) > 0 ) {
            return Double.parseDouble(getCP2MonthFee().substring(1)) == tempPrice;
        }else return true;
    }
    public boolean compareCameraSetupFee(String quantityCameraCP){
        double tempPrice = Math.round((Integer.parseInt(quantityCameraCP) * cameraSetupFee) * 100.0) / 100.0;
        if (Integer.parseInt(quantityCameraCP) > 0 ) {
            return Double.parseDouble(getCameraSetupFee().substring(1)) == tempPrice;
        }else return true;
    }
    public boolean compareCameraInstallationFee(String quantityCameraCP){
        double tempPrice = Math.round((Integer.parseInt(quantityCameraCP) * cameraInstallationFee) * 100.0) / 100.0;
        if (Integer.parseInt(quantityCameraCP) > 0 ) {
            return Double.parseDouble(getCameraInstallationFee().substring(1)) == tempPrice;
        }else return true;
    }
    public boolean compareEzSmartCamCP2(String quantityCameraCP){
        double tempPrice = Math.round((Integer.parseInt(quantityCameraCP) * ezSmartCamCP2Price) * 100.0) / 100.0;
        if (Integer.parseInt(quantityCameraCP) > 0 ) {
            return Double.parseDouble(getEzSmartCamCP2().substring(1)) == tempPrice;
        }else return true;
    }
    public boolean compareEzSmartCamSVA(String quantityCameraSVA){
        double tempPrice = Math.round((Integer.parseInt(quantityCameraSVA) * ezSmartCamSVAPrice) * 100.0) / 100.0;
        if (Integer.parseInt(quantityCameraSVA) > 0 ) {
            return Double.parseDouble(getEzSmartCamSVA().substring(1)) == tempPrice;
        }else return true;
    }
    public boolean compareSdCard(String quantityCameraCP, String valueSdCard){
        if (Integer.parseInt(quantityCameraCP) > 0 ){
            double tempPricesD32Gb = Math.round((Integer.parseInt(quantityCameraCP) * sD32GbPrice) * 100.0) / 100.0;
            double tempPricesSD64Gb = Math.round((Integer.parseInt(quantityCameraCP) * sD64GbPrice) * 100.0) / 100.0;
            double tempPricesSD128Gb = Math.round((Integer.parseInt(quantityCameraCP) * sD128GbPrice) * 100.0) / 100.0;
            if (Integer.parseInt(valueSdCard) == 7 ) {
                return Double.parseDouble(getSd32Gb().substring(1)) == tempPricesD32Gb;
            }else if (Integer.parseInt(valueSdCard) == 8 ) {
                return Double.parseDouble(getSd64Gb().substring(1)) == tempPricesSD64Gb;
            }else if (Integer.parseInt(valueSdCard) == 9 ) {
                return Double.parseDouble(getSd128Gb().substring(1)) == tempPricesSD128Gb;
            }else return false;
        } return true;
    }
/*
COMPARE METHODS TOTAL PRICE
*/
    public double totalOrderPrice(String typeOfDevices, String  quantityOfDevices, String typeOfPaymentMethod, String quantityPinCable, String quantityOBDPinCable, String quantitySticker, String quantityCameraCP, String quantityCameraSVA, String valueSdCard) {
        double totalPrice = Math.round((countEldPrice(quantityOfDevices, typeOfPaymentMethod, quantityCameraCP) + countDepositFeePrice(typeOfDevices, quantityOfDevices) + countDeliveryPrice() +
                countEldPinCablePrice(quantityPinCable) + countOBDPinCablePrice(quantityOBDPinCable) + countStickerPrice(quantitySticker) +
                countCP2MonthFeePrice(quantityCameraCP) + countCameraSetupFeePrice(quantityCameraCP) + countCameraInstallationFeePrice(quantityCameraCP) + countEzSmartCamCP2Price(quantityCameraCP) +
                countCameraSVAPrice(quantityCameraSVA) + countSdCardPrice(quantityCameraCP, valueSdCard))*100.0) / 100.0;
        return totalPrice;
    }

    public boolean compareTotalOrder(String typeOfDevices, String  quantityOfDevices, String typeOfPaymentMethod, String quantityPinCable, String quantityOBDPinCable, String quantitySticker, String quantityCameraCP, String quantityCameraSVA, String valueSdCard){
        double totalPrice =  Math.round((countEldPrice(quantityOfDevices, typeOfPaymentMethod, quantityCameraCP) +
                countDepositFeePrice(typeOfDevices, quantityOfDevices) +
                countDeliveryPrice() + countEldPinCablePrice(quantityPinCable) + countOBDPinCablePrice(quantityOBDPinCable) + countStickerPrice(quantitySticker) +
                countCP2MonthFeePrice(quantityCameraCP) + countCameraSetupFeePrice(quantityCameraCP) + countCameraInstallationFeePrice(quantityCameraCP) + countEzSmartCamCP2Price(quantityCameraCP) + countCameraSVAPrice(quantityCameraSVA) + countSdCardPrice(quantityCameraCP, valueSdCard))*100.0) / 100.0;
        System.out.println("totalPrice " + totalPrice);
        System.out.println("countEldPrice " + countEldPrice(quantityOfDevices, typeOfPaymentMethod, quantityCameraCP));
        System.out.println("countDepositFeePrice " + countDepositFeePrice(typeOfDevices, quantityOfDevices));
        return Double.parseDouble(getTotalOrder().substring(1)) == totalPrice;
    }
    public double countEldPrice(String  quantityOfDevices, String typeOfPaymentMethod, String quantityCameraCP) {
        int tempQuantityDevices1 = (Integer.parseInt(quantityOfDevices) - Integer.parseInt(quantityCameraCP));
        int tempQuantityDevices2 = (Integer.parseInt(quantityOfDevices) - tempQuantityDevices1);

        if (Integer.parseInt(quantityOfDevices) > 0 && Integer.parseInt(quantityCameraCP) == 0) {
            if (Integer.parseInt(typeOfPaymentMethod) == 0) {
                double tempPrice = Math.round(((Integer.parseInt(quantityOfDevices) * eldMonthToMonthPrice)*2) * 100.0) / 100.0;
                return tempPrice;
            } else if (Integer.parseInt(typeOfPaymentMethod) == 1) {
                double tempPrice1 = Math.round((Integer.parseInt(quantityOfDevices) * eld1YearSubscriptionPrice) * 100.0) / 100.0;
                return tempPrice1;

            } else if (Integer.parseInt(typeOfPaymentMethod) == 2) {
                double tempPrice2 = Math.round((Integer.parseInt(quantityOfDevices) * eld2YearsSubscriptionPrice) * 100.0) / 100.0;
                return tempPrice2;
            } else if (Integer.parseInt(typeOfPaymentMethod) == 9) {
                double tempPrice2 = Math.round(((Integer.parseInt(quantityOfDevices) * eldMonthToMonthPrice)*2) * 100.0) / 100.0;
                return tempPrice2;
            } else if (Integer.parseInt(typeOfPaymentMethod) == 10) {
                double tempPrice2 = Math.round((Integer.parseInt(quantityOfDevices) * eld1YearSubscriptionPrice) * 100.0) / 100.0;
                return tempPrice2;
            } else if (Integer.parseInt(typeOfPaymentMethod) == 11) {
                double tempPrice2 = Math.round((Integer.parseInt(quantityOfDevices) * eld2YearsSubscriptionPrice) * 100.0) / 100.0;
                return tempPrice2;
            }else if (Integer.parseInt(typeOfPaymentMethod) == 12) {
                double tempPrice2 = Math.round(((Integer.parseInt(quantityOfDevices) * eldMonthToMonthPrice)*2) * 100.0) / 100.0;
                return tempPrice2;
            } else if (Integer.parseInt(typeOfPaymentMethod) == 13) {
                double tempPrice2 = Math.round((Integer.parseInt(quantityOfDevices) * eld1YearSubscriptionPrice) * 100.0) / 100.0;
                return tempPrice2;
            } else if (Integer.parseInt(typeOfPaymentMethod) == 14) {
                double tempPrice2 = Math.round((Integer.parseInt(quantityOfDevices) * eld2YearsSubscriptionPrice) * 100.0) / 100.0;
                return tempPrice2;
            } else return 0;

        } else if (Integer.parseInt(quantityOfDevices) > 0 && Integer.parseInt(quantityCameraCP) > 0){
            if (Integer.parseInt(quantityOfDevices) <= Integer.parseInt(quantityCameraCP)){
                if (Integer.parseInt(typeOfPaymentMethod) == 0) {
                    double tempPrice = Math.round(((Integer.parseInt(quantityOfDevices) * eldMonthToMonthPrice)*2) * 100.0) / 100.0;
                    return tempPrice;
                } else if (Integer.parseInt(typeOfPaymentMethod) == 1) {
                    double tempPrice = Math.round((Integer.parseInt(quantityOfDevices) * eld1YearSubscriptionDiscountPrice) * 100.0) / 100.0;
                    return tempPrice;
                } else if (Integer.parseInt(typeOfPaymentMethod) == 2) {
                    double tempPrice = Math.round((Integer.parseInt(quantityOfDevices) * eld2YearsSubscriptionDiscountPrice) * 100.0) / 100.0;
                    return tempPrice;
                }
            } else if (Integer.parseInt(quantityOfDevices) > Integer.parseInt(quantityCameraCP)){
                if (Integer.parseInt(typeOfPaymentMethod) == 0) {
                    double tempPrice = Math.round(((Integer.parseInt(quantityOfDevices) * eldMonthToMonthPrice)*2) * 100.0) / 100.0;
                    return tempPrice;
                } else if (Integer.parseInt(typeOfPaymentMethod) == 1) {
                    double tempPrice1 = Math.round((tempQuantityDevices1 * eld1YearSubscriptionPrice) * 100.0) / 100.0;
                    double tempPrice2 = Math.round((tempQuantityDevices2 * eld1YearSubscriptionDiscountPrice) * 100.0) / 100.0;
                    return tempPrice1 + tempPrice2;
                } else if (Integer.parseInt(typeOfPaymentMethod) == 2) {
                    double tempPrice1 = Math.round((tempQuantityDevices1 * eld2YearsSubscriptionPrice) * 100.0) / 100.0;
                    double tempPrice2 = Math.round((tempQuantityDevices2 * eld2YearsSubscriptionDiscountPrice) * 100.0) / 100.0;
                    return tempPrice1 + tempPrice2;
                }
            } else return 0;

        } else if (Integer.parseInt(quantityOfDevices) == 0){
            return 0;
        } return 0;
    }
    private double countDepositFeePrice(String typeOfDevices, String  quantityOfDevices){
        double tempDepositFee = Math.round((Integer.parseInt(quantityOfDevices) * eldDepositFee) * 100.0) / 100.0;
        double tempPriceEldPriceSmart = Math.round((Integer.parseInt(quantityOfDevices) * ELDDevicePriceSmart) * 100.0) / 100.0;
        double tempPriceEldPriceHard = Math.round((Integer.parseInt(quantityOfDevices) * ELDDevicePriceHard) * 100.0) / 100.0;
        if (Integer.parseInt(typeOfDevices) == 1) {
            return tempDepositFee;
        } else if (Integer.parseInt(typeOfDevices) == 2){
            return tempPriceEldPriceSmart;
        } else if (Integer.parseInt(typeOfDevices) == 3){
            return tempPriceEldPriceHard;
        } else return 0;
    }

    private double countDeliveryPrice(){
        if (actionsWithOurElements.isElementDisplay(eldDeliveryPriceText)) {
            double tempDeliveryPrice = Double.parseDouble(getEldDeliveryPrice().substring(1));
            return tempDeliveryPrice;
        } else return 0;
    }
    private double countEldPinCablePrice(String quantityPinCable){
        double tempPinCablePrice = Math.round((Integer.parseInt(quantityPinCable) * pinCablePrice) * 100.0) / 100.0;
        return tempPinCablePrice;
    }
    private double countOBDPinCablePrice(String quantityOBDPinCable){
        double tempOBDPinCablePrice = Math.round((Integer.parseInt(quantityOBDPinCable) * pinCableOBDIIPrice) * 100.0) / 100.0;
        return tempOBDPinCablePrice;
    }
    private double countStickerPrice(String quantitySticker){
        double tempStickerPrice = Math.round((Integer.parseInt(quantitySticker) * stickerLabelPrice) * 100.0) / 100.0;
        return tempStickerPrice;
    }
    private double countCP2MonthFeePrice(String quantityCameraCP){
        if (Integer.parseInt(quantityCameraCP) > 0 ) {
            double tempCP2MonthFeePrice = Math.round((Integer.parseInt(quantityCameraCP) * cP2MonthFee) * 100.0) / 100.0;
            return tempCP2MonthFeePrice;
        } else return 0;
    }
    private double countCameraSetupFeePrice(String quantityCameraCP){
        if (Integer.parseInt(quantityCameraCP) > 0 ) {
            double tempCameraSetupFeePrice = Math.round((Integer.parseInt(quantityCameraCP) * cameraSetupFee) * 100.0) / 100.0;
            return tempCameraSetupFeePrice;
        } else return 0;
    }
    private double countCameraInstallationFeePrice(String quantityCameraCP){
        if (Integer.parseInt(quantityCameraCP) > 0 ) {
            double tempCameraInstallationFeePrice = Math.round((Integer.parseInt(quantityCameraCP) * cameraInstallationFee) * 100.0) / 100.0;
            return tempCameraInstallationFeePrice;
        } else return 0;
    }
    private double countEzSmartCamCP2Price(String quantityCameraCP){
        double tempSmartCamCP2Price = Math.round((Integer.parseInt(quantityCameraCP) * ezSmartCamCP2Price) * 100.0) / 100.0;
        return tempSmartCamCP2Price;
    }
    private double countCameraSVAPrice(String quantityCameraSVA){
        double tempCameraSVAPrice = Math.round((Integer.parseInt(quantityCameraSVA) * ezSmartCamSVAPrice) * 100.0) / 100.0;
        return tempCameraSVAPrice;
    }
    public double countSdCardPrice(String quantityCameraCP, String valueSdCard){
        if (Integer.parseInt(quantityCameraCP) > 0 ){

            if (Integer.parseInt(valueSdCard) == 7 ){
                double tempPricesD32Gb = Math.round((Integer.parseInt(quantityCameraCP) * sD32GbPrice) * 100.0) / 100.0;
                return tempPricesD32Gb;
            }

            else if (Integer.parseInt(valueSdCard) == 8 ){
                double tempPricesSD64Gb = Math.round((Integer.parseInt(quantityCameraCP) * sD64GbPrice) * 100.0) / 100.0;
                return tempPricesSD64Gb;
            }

            else if (Integer.parseInt(valueSdCard) == 9 ){
                double tempPricesSD128Gb = Math.round((Integer.parseInt(quantityCameraCP) * sD128GbPrice) * 100.0) / 100.0;
                return tempPricesSD128Gb;
            } else return 0;

        } else return 0;
    }

}


