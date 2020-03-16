package pages;

import io.qameta.allure.Step;
import libs.UtilsForDB;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static libs.Utils.waitABit;

public class EldPage extends ParentPage {
    public EldPage(WebDriver webDriver, UtilsForDB utilsForDB) {
        super(webDriver, "dash/eld/",  utilsForDB);
    }


    @FindBy(xpath = ".//button[@data-tutorial='addELD']")
    private WebElement orderELD;

    @FindBy(xpath = ".//*[@placeholder='Id']")
    private WebElement idHolder;

    @FindBy(xpath = "//a[@href='/dash/paper_logs/']")
    private WebElement paperLogPermissionsPage;

    @FindBy(xpath = "//a[@href='/dash/eld/']")
    private WebElement manageELDPage;

    @FindBy(xpath = ".//button[@class='btn btn-default replacementRequestButton']")
    private WebElement replacementRequestButton;

    @FindBy(xpath = ".//input[@name='return_reason'][@value='2']")
    private WebElement warrantyReplacement;

    @FindBy(xpath = ".//input[@name='return_reason'][@value='1']")
    private WebElement deviceBroken;

    @FindBy(id = "return_description")
    private WebElement descriptionTextArea;

    @FindBy(id = "btnReturnScanner")
    private WebElement confirmButton;

    @FindBy(xpath = ".//*[@class='modal-content']//./*[text()='Return request was created, an email with more info was sent to your email address.']")
    private WebElement modalWindowRequestCreated;


    @Step
    public void clickOnOrderELD(){
        actionsWithOurElements.clickOnElement(orderELD);
    }
    @Step
    public void goToPaperLogPermissions(){waitABit(2);actionsWithOurElements.clickOnElement(paperLogPermissionsPage);}
    @Step
    public void goToManageELDPage(){waitABit(2);actionsWithOurElements.clickOnElement(manageELDPage);}
    @Step
    public void cancelEldDevices(String idOrder, String quantityOfDevices, String quantityCameraCP) throws SQLException, IOException, ClassNotFoundException {
        List<String> localId = utilsForDB.getLocalIdDevices(idOrder);
        if (Integer.parseInt(quantityOfDevices) > 0 && Integer.parseInt(quantityCameraCP) == 0) {
            for (String element : localId) {
                waitABit(3);
                enterIdOrder(element);
                waitABit(3);
                clickOnOrderOnList(element);
                waitABit(3);
                clickOnButtonCancelOrderDevice();
                waitABit(3);
                clickOnOrderEldConfirm();
            }
        } else if (Integer.parseInt(quantityOfDevices) > 0 && Integer.parseInt(quantityCameraCP) > 0) {
            waitABit(3);
            enterIdOrder(localId.get(0));
            waitABit(3);
            clickOnOrderOnList(localId.get(0));
            waitABit(3);
            clickOnButtonCancelOrderDevice();
            waitABit(3);
            clickOnOrderEldConfirm();
        } else if (Integer.parseInt(quantityOfDevices) == 0) {
            logger.info("Can not canceled devices, no devices in order");
        } else logger.info("Can not canceled devices");
    }
    @Step
    public boolean compareCancelStatusOrder(String orderStatus, String quantityOfDevices){
        if (Integer.parseInt(quantityOfDevices) > 0){
            return orderStatus.equals("2");
        } else if (Integer.parseInt(quantityOfDevices) == 0){
            return true;
        } else return false;
    }
    @Step
    private void clickOnOrderEldConfirm() {
        actionsWithOurElements.clickOnElement("//*[@class='btn btn-primary changeStatus' and text()='Confirm']");
    }
    @Step
    private void clickOnButtonCancelOrderDevice() {
        actionsWithOurElements.clickOnElement("//*[@class='btn btn-default' and text()='Cancel Order Device']");
    }
    @Step
    private void clickOnOrderOnList(String idLocalDevice) {
        actionsWithOurElements.clickOnElement(".//*[@id='eld_table']//td[text()='" + idLocalDevice + "']");
    }
    @Step
    public void enterIdOrder(String idLocalDevice){
        actionsWithOurElements.enterTextToElement(idHolder, idLocalDevice);
    }
    @Step
    public void enterLocalId(String idLocalDevice){
        actionsWithOurElements.enterTextToElement(idHolder, idLocalDevice);
        waitABit(3);
    }
    @Step
    public void clickOnDeviceInList(String localId){
        actionsWithOurElements.clickOnElement(".//*[text()='" + localId + "']");
    }
    @Step
    public boolean compareEldStatusInCompletedOrder(String idOrder, String quantityOfDevices) throws SQLException, IOException, ClassNotFoundException {
        List<String> tempStatusId = utilsForDB.getStatusEldInOrder(idOrder);
        if (Integer.parseInt(quantityOfDevices) > 0) {
            for (String element : tempStatusId)
                if (element.equals("4")){
                } else return false;
        } return true;
    }
    @Step
    public boolean compareEldStatusInPaidOrder(String idOrder, String quantityOfDevices) throws SQLException, IOException, ClassNotFoundException {
        List<String> tempStatusId = utilsForDB.getStatusEldInOrder(idOrder);
        if (Integer.parseInt(quantityOfDevices) > 0) {
            for (String element : tempStatusId)
                if (element.equals("1")) {
                } else return false;
        } return true;
    }
    @Step
    public boolean compareEldStatusInNewOrder(String idOrder, String quantityOfDevices) throws SQLException, IOException, ClassNotFoundException {
        List<String> tempStatusId = utilsForDB.getStatusEldInOrder(idOrder);
        if (Integer.parseInt(quantityOfDevices) > 0) {
            for (String element : tempStatusId)
                if (element.equals("0")){
                } else return false;
        } return true;

    }
    @Step
    public void checkAndCancelNewOrderBeforeTest(String fleetUser, String fleetId) throws SQLException, IOException, ClassNotFoundException {
        int tempCountOrder = utilsForDB.getCountNewOrder(fleetUser, fleetId);
        if (tempCountOrder > 0){
            List<String> tempListWithOrderId = utilsForDB.getIdOrderWithStatusNew(fleetUser, fleetId);
            for (String element :
                    tempListWithOrderId) {
                utilsForDB.deleteEventNewOrder(element);
                utilsForDB.changeStatusOrderToCancel(element);
                logger.info("Order with status New was canceled");
            }
        } else logger.info("User have not Order with status New");
    }
    @Step
    public void clickOnReplacementRequestButton(){
        actionsWithOurElements.clickOnElement(replacementRequestButton);
    }
    @Step
    public void clickOnWarrantyReplacementRadioButton(){
        actionsWithOurElements.clickOnElement(warrantyReplacement);
    }
    @Step
    public void clickOnDeviceBrokenRadioButton(){
        actionsWithOurElements.clickOnElement(deviceBroken);
    }
    @Step
    public void enterTextToDescription(String text){actionsWithOurElements.enterTextToElement(descriptionTextArea, text);}
    @Step
    public void clickOnConfirmButton(){actionsWithOurElements.clickOnElement(confirmButton);}
    @Step
    public boolean popUpRequestCreatedIsDisplayed(){return actionsWithOurElements.isElementDisplay(modalWindowRequestCreated);}



}
