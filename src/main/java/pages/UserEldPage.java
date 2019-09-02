package pages;

import io.qameta.allure.Step;
import libs.UtilsForDB;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UserEldPage extends ParentPage {

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
        if (Integer.parseInt(quantityOfDevices) > 0) {
            if (Integer.parseInt(quantityCameraCP) == 0)
                for (String element : localId) {
                    enterIdOrder(element);
                    clickOnOrderOnList(element);
                    clickOnButtonCancelOrderDevice();
                    clickOnOrderEldConfirm();
                }
            else if (Integer.parseInt(quantityCameraCP) > 0){
                enterIdOrder(localId.get(0));
                clickOnOrderOnList(localId.get(0));
                clickOnButtonCancelOrderDevice();
                clickOnOrderEldConfirm();
            }
        } else logger.info("Can not canceled devices, no devices in order");
    }

    public boolean compareCancelDeviceStatusOrder(String orderStatus, String quantityOfDevices){
        if (Integer.parseInt(quantityOfDevices) > 0){
            return orderStatus.equals("2");
        } else if (Integer.parseInt(quantityOfDevices) == 0){
            return true;
        } return false;
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
    public boolean compareEldStatusInCompletedOrder(String idOrder) throws SQLException, IOException, ClassNotFoundException {
        UtilsForDB utilsForDB = new UtilsForDB();
        List<String> tempStatusId = utilsForDB.getIdEldFromOrder(idOrder);
        for (String element : tempStatusId)
        if (element.equals("4")){
            return true;
        }
        return false;
    }
    @Step
    public boolean compareEldStatusInPaidOrder(String idOrder) throws SQLException, IOException, ClassNotFoundException {
        UtilsForDB utilsForDB = new UtilsForDB();
        List<String> tempStatusId = utilsForDB.getIdEldFromOrder(idOrder);
        for (String element : tempStatusId)
            if (element.equals("1")){
                return true;
            }
        return false;
    }

}