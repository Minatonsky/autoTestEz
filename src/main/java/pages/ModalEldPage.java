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

    @FindBy(xpath = "//*[@id=\"eldTariffs\"]/*[@data-id=\"0\"]")
    private WebElement paymentMethods;

    @FindBy(id = "leaseAndAgreementCheckbox")
    private WebElement agreement;

    @FindBy(xpath = "//button[@class=\"fast_move\"]/i[@class=\"fa fa-arrow-down\"]")
    private WebElement buttonFastMove;

    @FindBy(xpath = "//div[@class=\"modal-body\"]/*[contains(text(), 'I Agree')]")
    private WebElement buttonAgree;

    @FindBy(xpath = ".//select[@name=\"state\"]")
    private WebElement typeOfState;

    public ModalEldPage(WebDriver webDriver) {
        super(webDriver, "/dash/eld/");
    }

    public void enterQuantityDevices(String quantityOfDevices) {
        actionsWithOurElements.enterTextToElement(quantityDeviseInput, quantityOfDevices);
    }

    public void selectState(String value) {
        actionsWithOurElements.selectValueInDropDown(typeOfState, value);
    }

    public void clickButtonOrder() {
        actionsWithOurElements.clickOnElement(orderButton);
    }

    public void enterFirstName(String firstName) {
        actionsWithOurElements.enterTextToElement(firstNameInput, firstName);
    }

    public void enterLastName(String lastName) {
        actionsWithOurElements.enterTextToElement(lastNameInput, lastName);
    }

    public void enterPhone(String phone) {
        actionsWithOurElements.enterTextToElement(phoneInput, phone);
    }

    public void enterPrimaryAddressLine(String addressLine) {
        actionsWithOurElements.enterTextToElement(addressLineInput, addressLine);
    }

    public void enterAptNumber(String aptNumber) {
        actionsWithOurElements.enterTextToElement(aptNumberInput, aptNumber);
    }

    public void enterDeliveryCity(String deliveryCity) {
        actionsWithOurElements.enterTextToElement(deliveryCityInput, deliveryCity);
    }

    public void enterZipCode(String zipCode) {
        actionsWithOurElements.enterTextToElement(zipCodeInput, zipCode);
    }

    public void clickPaymentMethods() {
        actionsWithOurElements.clickOnElement(paymentMethods);
    }

    public void clickAgreement() {
        actionsWithOurElements.clickOnElement(agreement);
    }

    public void clickButtonFastMove() {
        actionsWithOurElements.clickOnElement(buttonFastMove);
    }

    public void clickButtonAgree() {
        actionsWithOurElements.clickOnElement(buttonAgree);
    }
}
