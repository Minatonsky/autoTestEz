package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ManagerModalEldPage extends ParentPage {

    @FindBy(id = "fleet_select")
    private WebElement usdotFleetInput;

    @FindBy(id = "fleetList")
    private WebElement fleetInFleetList;

    @FindBy(id = "user_select")
    private WebElement userSelectField;

    @FindBy(xpath = "//*[@id='userList']/li[1]")
    private WebElement userList;

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

    @FindBy(id = "deliveryCalculate")
    private WebElement deliveryCost;

    @FindBy(id = "reg_state")
    private WebElement typeOfState;

    @FindBy(id = "overnightDelivery")
    private WebElement checkBoxOvernightDelivery;


    @FindBy(xpath = "//*[@id='cablePriceList']/div[2]/div[2]/input")
    private WebElement quantityPinCableInput;

    @FindBy(xpath = ".//*[@id='cablePriceList']/div[3]/div[2]/input")
    private WebElement quantityOBDPinCableInput;

    @FindBy(xpath = ".//*[@id='cablePriceList']/div[4]/div[2]/input")
    private WebElement quantityStickerInput;


//    @FindBy(xpath = ".//*[@data-id='100']/td[3]/input")
//    private WebElement quantityCamera1Input;
//
//    @FindBy(xpath = ".//*[@data-id='101']/td[3]/input")
//    private WebElement quantityCamera2Input;

    @FindBy(id = "order_type")
    private WebElement typeOfPaymentMethodInput;


    public ManagerModalEldPage(WebDriver webDriver) {
        super(webDriver, "/dash/eld/");
    }

///////////////
    public void clickOnUsdotField(){
        actionsWithOurElements.clickOnElement(usdotFleetInput);
    }
    public void enterUsdotFleet(String usdotFleet){
        actionsWithOurElements.enterTextToElement(usdotFleetInput, usdotFleet);
        logger.info(" USDOT is " + usdotFleet);
    }

    public void clickOnFleetInFleetList(){
        actionsWithOurElements.clickOnElement(fleetInFleetList);
    }

    public void clickOnUserField(){
        actionsWithOurElements.clickOnElement(userSelectField);
    }

    public void selectUser(){
        actionsWithOurElements.clickOnElement(userList);
    }

    @Step
    public void selectFleetInOrder(String usdotFleet) throws InterruptedException {
        Thread.sleep(2000);
        clickOnUsdotField();
        Thread.sleep(2000);
        enterUsdotFleet(usdotFleet);
        Thread.sleep(2000);
        clickOnFleetInFleetList();
        Thread.sleep(2000);
        clickOnUserField();
        Thread.sleep(2000);
        selectUser();
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
ORDER LIST
 */

    public void enterQuantityDevices(String quantityOfDevices) {actionsWithOurElements.enterTextToElement(quantityDeviseInput, quantityOfDevices);}

    public void enterQuantityPinCable(String quantityPinCable){actionsWithOurElements.enterTextToElement(quantityPinCableInput, quantityPinCable);}

    public void enterQuantityOBDPinCable(String quantityOBDPinCable){actionsWithOurElements.enterTextToElement(quantityOBDPinCableInput, quantityOBDPinCable);}

    public void enterQuantitySticker(String quantitySticker){actionsWithOurElements.enterTextToElement(quantityStickerInput, quantitySticker);}

//    public void enterQuantityCamera1(String quantityCamera1){actionsWithOurElements.enterTextToElement(quantityCamera1Input, quantityCamera1);}

//    public void enterQuantityCamera2(String quantityCamera2){actionsWithOurElements.enterTextToElement(quantityCamera2Input, quantityCamera2);}

//    public void setPickUpFromOffice(String neededStatePickUpFromOffice){actionsWithOurElements.setNeededStateToCheckBox(checkBoxPickUp, neededStatePickUpFromOffice);}

    public void setOvernightDelivery(String neededStateOvernightDelivery){actionsWithOurElements.setNeededStateToCheckBox(checkBoxOvernightDelivery, neededStateOvernightDelivery);}

    @Step
    public void enterOrderData(String  quantityOfDevices, String quantityPinCable, String quantityOBDPinCable, String quantitySticker, String quantityCamera1, String quantityCamera2, String neededStatePickUpFromOffice, String neededStateOvernightDelivery){
        enterQuantityDevices(quantityOfDevices);
        enterQuantityPinCable(quantityPinCable);
        enterQuantityOBDPinCable(quantityOBDPinCable);
        enterQuantitySticker(quantitySticker);
//        enterQuantityCamera1(quantityCamera1);
//        enterQuantityCamera2(quantityCamera2);
//        setPickUpFromOffice(neededStatePickUpFromOffice);
        setOvernightDelivery(neededStateOvernightDelivery);

    }




    public void setPaymentMethod(String typeOfPaymentMethod) {
        actionsWithOurElements.selectValueInDropDown(typeOfPaymentMethodInput, typeOfPaymentMethod);
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
        return actionsWithOurElements.isElementInOrder(".//*[@id='eld_total_order']/b[text()='$ " + eldStandardTotalOrder + "']");
    }

    @Step
    public boolean compareOrderPrice(String eldOrderPrice){
        return actionsWithOurElements.isElementInOrder(".//*[@id='eld_order_price']/b[text()='$ " + eldOrderPrice + "']");
    }

    @Step
    public boolean compareDeliveryPrice(String eldDeliveryPrice){
        return actionsWithOurElements.isElementInOrder(".//*[@id='eld_delivery_price']/b[text()='$ " + eldDeliveryPrice + "']");
    }

    @Step
    public boolean compareFirstMonthFee(String eldFirstMonthFee){
        return actionsWithOurElements.isElementInOrder(".//*[@id='eld_first_price']/b[text()='$ " + eldFirstMonthFee + "']");
    }

    @Step
    public boolean compareLastMonthFee(String eldLastMonthFee) {
        return actionsWithOurElements.isElementInOrder(".//*[@id='eld_last_price']/b[text()='$ " + eldLastMonthFee + "']");
    }

    @Step
    public boolean compareDepositFee(String eldDepositFee){
        return actionsWithOurElements.isElementInOrder(".//*[@id='eld_deposit_fee']/b[text()='$ " + eldDepositFee + "']");
    }
}