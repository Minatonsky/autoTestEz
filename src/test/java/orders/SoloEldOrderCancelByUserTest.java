package orders;

import libs.ExcelDriver;
import libs.UtilsForDB;
import org.junit.Test;
import parentTest.ParentTest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public class SoloEldOrderCancelByUserTest extends ParentTest {


    @Test
    public void addNewOrder() throws InterruptedException, SQLException, IOException, ClassNotFoundException {
        ExcelDriver excelDriver = new ExcelDriver();
        int columnNumber = 1;

        Map dataForEldOrder = excelDriver.getMultipleData(configProperties.DATA_FILE_PATH() + "testEldOrder.xls", "orderListData", columnNumber);
        Map personalDataForEldOrder = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testEldOrder.xls", "personalData");
        Map dataForSoloValidLogIn = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "validSoloLogin");
        Map dataSoloId = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "validSoloLogin");

        UtilsForDB utilsForDB = new UtilsForDB();
        String idLastOrderBeforeTest = utilsForDB.getLastOrderIdForFleet(dataSoloId.get("soloId").toString());
        utilsForDB.getSetCurrentDueForSolo(dataForEldOrder.get("currentDue").toString(), dataSoloId.get("soloId").toString());

        loginPage.userValidLogIn(dataForSoloValidLogIn.get("login").toString(),dataForSoloValidLogIn.get("pass").toString());

        dashboardPage.goToEldPage();
        Thread.sleep(1000);
        eldUserPage.clickOnOrderELD();

/*
PERSONAL DATA
 */

        modalEldPage.enterPersonalData(personalDataForEldOrder.get("deliveryState").toString(), personalDataForEldOrder.get("firstName").toString(),
                personalDataForEldOrder.get("lastName").toString(), personalDataForEldOrder.get("phone").toString(),
                personalDataForEldOrder.get("addressLine").toString(), personalDataForEldOrder.get("aptNumber").toString(),
                personalDataForEldOrder.get("deliveryCity").toString(), personalDataForEldOrder.get("zipCode").toString());

/*
ORDER LIST
 */
        modalEldPage.enterQuantityDevices(dataForEldOrder.get("quantityOfDevices").toString());
        modalEldPage.enterQuantityPinCable(dataForEldOrder.get("quantityPinCable").toString());
        modalEldPage.enterQuantityOBDPinCable(dataForEldOrder.get("quantityOBDPinCable").toString());
        modalEldPage.enterQuantitySticker(dataForEldOrder.get("quantitySticker").toString());
        modalEldPage.enterQuantityCamera1(dataForEldOrder.get("quantityCamera1").toString());
        modalEldPage.enterQuantityCamera2(dataForEldOrder.get("quantityCamera2").toString());

        modalEldPage.clickPaymentMethods(dataForEldOrder.get("typeOfPaymentMethod").toString());

/*
CHECK BOX DELIVERY
 */
        modalEldPage.setPickUpFromOffice(dataForEldOrder.get("neededStatePickUpFromOffice").toString());
        modalEldPage.setOvernightDelivery(dataForEldOrder.get("neededStateOvernightDelivery").toString());

        modalEldPage.compareTotalOrder(dataForEldOrder.get("defaultTotalOrder").toString());
        checkAC("Total Order is not correct", modalEldPage.compareTotalOrder(dataForEldOrder.get("defaultTotalOrder").toString()), true);

/*
EQUIPMENT LEASE AND SOFTWARE SUBSCRIPTION SERVICE AGREEMENT
 */
        modalEldPage.clickAgreements(dataForEldOrder.get("quantityOfDevices").toString());
/*
CHECK LAST ID ORDER BEFORE AND AFTER TEST
 */

        String idLastOrderAfterTest = utilsForDB.getLastOrderIdForSolo(dataSoloId.get("soloId").toString());
        checkAC("New order wasn`t created", idLastOrderBeforeTest.equals(idLastOrderAfterTest) , false);

        dashboardPage.clickOnMenuDash();
        Thread.sleep(1000);
        dashboardPage.clickOnMenuPageFinances();
        financesPage.checkCurrentUrl();

        financesPage.compareBalance(dataForEldOrder.get("defaultBalance").toString());
        checkAC("Balance is not correct", financesPage.compareBalance(dataForEldOrder.get("defaultBalance").toString()), true);

        tearDown();
        setUp();


/*
MANAGER COMPLETED OR CANCEL ORDER
 */

        Map dataForManagerValidLogIn = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "ManagerLogin");


        loginPage.userValidLogIn(dataForManagerValidLogIn.get("login").toString(),dataForManagerValidLogIn.get("pass").toString());

        dashboardPage.clickOnMenuDash();
        selectBrowserWindow("mainWindow");

        dashboardPage.clickOnMenuPageELD();
        Thread.sleep(5000);
        managerEldPage.clickOnEldOrders();
        Thread.sleep(5000);
        managerEldPage.enterIdOrder(idLastOrderAfterTest);
        Thread.sleep(5000);
        managerEldPage.clickOnOrderOnList(idLastOrderAfterTest);
        modalOrderPage.selectOrderStatus("2");
        modalOrderPage.clickButtonSave();


        tearDown();
        setUp();

/*
USER CHECK IF DEVICES IS ACTIVE AND BALANCE NOT CHANGED
 */

        loginPage.userValidLogIn(dataForSoloValidLogIn.get("login").toString(),dataForSoloValidLogIn.get("pass").toString());

        dashboardPage.clickOnMenuDash();
        Thread.sleep(1000);
        dashboardPage.clickOnMenuPageFinances();

        financesPage.compareBalance(dataForEldOrder.get("balanceIfCanceled").toString());
        checkAC("Balance is not correct", financesPage.compareBalance(dataForEldOrder.get("balanceIfCanceled").toString()), true);

/*
CHECK ORDER STATUS FROM DATABASE
 */

        Thread.sleep(5000);
        String orderStatus = utilsForDB.getOrderStatus(idLastOrderAfterTest);
        checkAC("Order is not completed", orderStatus.equals("1") , true);

    }

}
