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

public class UserEldPage extends ParentPage {
    UtilsForDB utilsForDB = new UtilsForDB();

    @FindBy(xpath = ".//button[@data-tutorial='addELD']")
    private WebElement orderELD;

    @FindBy(xpath = ".//*[@placeholder='Id']")
    private WebElement idHolder;


    public UserEldPage(WebDriver webDriver) {
        super(webDriver, "dash/eld/");
    }

    public void clickOnOrderELD(){

        actionsWithOurElements.clickOnElement(orderELD);
    }

    @Step
    public void cancelEldDevices(String idOrder, String quantityOfDevices, String quantityCameraCP) throws SQLException, IOException, ClassNotFoundException {
        UtilsForDB utilsForDB = new UtilsForDB();
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

    public boolean compareCancelStatusOrder(String orderStatus, String quantityOfDevices){
        if (Integer.parseInt(quantityOfDevices) > 0){
            return orderStatus.equals("2");
        } else if (Integer.parseInt(quantityOfDevices) == 0){
            return true;
        } else return false;
    }

    private void clickOnOrderEldConfirm() {
        actionsWithOurElements.clickOnElement("//*[@class='btn btn-primary changeStatus' and text()='Confirm']");
    }
    private void clickOnButtonCancelOrderDevice() {
        actionsWithOurElements.clickOnElement("//*[@class='btn btn-default' and text()='Cancel Order Device']");
    }
    private void clickOnOrderOnList(String idLocalDevice) {
        actionsWithOurElements.clickOnElement(".//*[@id='eld_table']//td[text()='" + idLocalDevice + "']");
    }
    public void enterIdOrder(String idLocalDevice){
        actionsWithOurElements.enterTextToElement(idHolder, idLocalDevice);
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
    public void checkAndCancelNewOrderBeforeTestFleet(String fleetId) throws SQLException, IOException, ClassNotFoundException {
        int tempCountOrder = utilsForDB.getCountNewOrderForFleet(fleetId);
        if (tempCountOrder > 0){
            List<String> tempListWithOrderId = utilsForDB.getIdOrderWithStatusNewForFleet(fleetId);
            for (String element :
                    tempListWithOrderId) {
                utilsForDB.deleteEventNewOrder(element);
                utilsForDB.changeStatusOrderToCancel(element);
                logger.info("Order with status New was canceled");
            }
        } else logger.info("User have not Order with status New");
    }
    @Step
    public void checkAndCancelNewOrderBeforeTestSolo(String soloId) throws SQLException, IOException, ClassNotFoundException {
        int tempCountOrder = utilsForDB.getCountNewOrderForSolo(soloId);
        if (tempCountOrder > 0){
            List<String> tempListWithOrderId = utilsForDB.getIdOrderWithStatusNewForSolo(soloId);
            for (String element :
                    tempListWithOrderId) {
                utilsForDB.deleteEventNewOrder(element);
                utilsForDB.changeStatusOrderToCancel(element);
                logger.info("Order with status New was delete");
            }
        } else logger.info("User have not Order with status New");
    }


}
