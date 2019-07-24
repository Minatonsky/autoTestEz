package parameterizedOrders;

import libs.ExcelDriver;
import libs.SpreadsheetData;
import libs.UtilsForDB;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import parentTest.ParentTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;

@RunWith(Parameterized.class)

public class SoloOrderCompletedByManagerParameterizedTest extends ParentTest {String  quantityOfDevices, typeOfPaymentMethod, quantityPinCable, quantityOBDPinCable, quantitySticker, quantityCamera1, quantityCamera2, neededStatePickUpFromOffice, neededStateOvernightDelivery, currentDue, eldOrderPrice, eldDeliveryPrice, eldFirstMonthFee, eldLastMonthFee, eldDepositFee, defaultTotalOrder, defaultBalance, balanceIfCanceled;

    public SoloOrderCompletedByManagerParameterizedTest(String quantityOfDevices, String typeOfPaymentMethod, String quantityPinCable, String quantityOBDPinCable, String quantitySticker, String quantityCamera1, String quantityCamera2, String neededStatePickUpFromOffice, String neededStateOvernightDelivery, String currentDue, String eldOrderPrice, String eldDeliveryPrice, String eldFirstMonthFee, String eldLastMonthFee, String eldDepositFee, String defaultTotalOrder, String defaultBalance, String balanceIfCanceled) {

        this.quantityOfDevices = quantityOfDevices;
        this.typeOfPaymentMethod = typeOfPaymentMethod;
        this.quantityPinCable = quantityPinCable;
        this.quantityOBDPinCable = quantityOBDPinCable;
        this.quantitySticker = quantitySticker;
        this.quantityCamera1 = quantityCamera1;
        this.quantityCamera2 = quantityCamera2;
        this.neededStatePickUpFromOffice = neededStatePickUpFromOffice;
        this.neededStateOvernightDelivery = neededStateOvernightDelivery;
        this.currentDue = currentDue;
        this.eldOrderPrice = eldOrderPrice;
        this.eldDeliveryPrice = eldDeliveryPrice;
        this.eldFirstMonthFee = eldFirstMonthFee;
        this.eldLastMonthFee = eldLastMonthFee;
        this.eldDepositFee = eldDepositFee;
        this.defaultTotalOrder = defaultTotalOrder;
        this.defaultBalance = defaultBalance;
        this.balanceIfCanceled = balanceIfCanceled;
    }

    @Parameterized.Parameters()
    public static Collection testData() throws IOException {
        InputStream spreadsheet = new FileInputStream(configProperties.DATA_FILE_PATH() + "testParameterizedOrder.xls");
        return new SpreadsheetData(spreadsheet,"suitCancelOrder").getData();

    }

    @Test
    public void addNewOrder() throws InterruptedException, SQLException, IOException, ClassNotFoundException {
        ExcelDriver excelDriver = new ExcelDriver();
        UtilsForDB utilsForDB = new UtilsForDB();

        Map personalDataForEldOrder = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testEldOrder.xls", "personalData");
        Map dataForSoloValidLogIn = ExcelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "validSoloLogin");
        Map dataSoloId = ExcelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "validSoloLogin");


        String idLastOrderBeforeTest = utilsForDB.getLastOrderIdForFleet(dataSoloId.get("soloId").toString());
        utilsForDB.getSetCurrentDueForSolo(currentDue, dataSoloId.get("soloId").toString());

        loginPage.userValidLogIn(dataForSoloValidLogIn.get("login").toString(),dataForSoloValidLogIn.get("pass").toString());

        dashboardPage.clickOnMenuDash();
        dashboardPage.clickMenuSizeButton();
        dashboardPage.goToEldPage();

        eldUserPage.clickOnOrderELD();

        modalEldPage.enterPersonalData(personalDataForEldOrder.get("deliveryState").toString(), personalDataForEldOrder.get("firstName").toString(), personalDataForEldOrder.get("lastName").toString(), personalDataForEldOrder.get("phone").toString(), personalDataForEldOrder.get("addressLine").toString(), personalDataForEldOrder.get("aptNumber").toString(), personalDataForEldOrder.get("deliveryCity").toString(), personalDataForEldOrder.get("zipCode").toString());


        modalEldPage.enterOrderData(quantityOfDevices, quantityPinCable, quantityOBDPinCable, quantitySticker, quantityCamera1, quantityCamera2, neededStatePickUpFromOffice, neededStateOvernightDelivery);
        modalEldPage.clickPaymentMethods(typeOfPaymentMethod);

        modalEldPage.compareTotalOrder(defaultTotalOrder);
        checkAC("Total Order is not correct", modalEldPage.compareTotalOrder(defaultTotalOrder), true);

        modalEldPage.compareOrderPrice(eldOrderPrice);
        checkAC("OrderPrice is not correct", modalEldPage.compareOrderPrice(eldOrderPrice), true);

        modalEldPage.compareDeliveryPrice(eldDeliveryPrice);
        checkAC("DeliveryPrice is not correct", modalEldPage.compareDeliveryPrice(eldDeliveryPrice), true);

        modalEldPage.compareFirstMonthFee(eldFirstMonthFee);
        checkAC("FirstMonthFee is not correct", modalEldPage.compareFirstMonthFee(eldFirstMonthFee), true);

        modalEldPage.compareLastMonthFee(eldLastMonthFee);
        checkAC("LastMonthFee is not correct", modalEldPage.compareLastMonthFee(eldLastMonthFee), true);

        modalEldPage.compareDepositFee(eldDepositFee);
        checkAC("DepositFee is not correct", modalEldPage.compareDepositFee(eldDepositFee), true);

        modalEldPage.compareTotalOrder(defaultTotalOrder);
        checkAC("Total Order is not correct", modalEldPage.compareTotalOrder(defaultTotalOrder), true);


        modalEldPage.clickAgreements(quantityOfDevices);


        String idLastOrderAfterTest = utilsForDB.getLastOrderIdForSolo(dataSoloId.get("soloId").toString());
        checkAC("New order wasn`t created", idLastOrderBeforeTest.equals(idLastOrderAfterTest) , false);

        dashboardPage.goToFinancesPage();

        financesPage.compareBalance(defaultBalance);
        checkAC("Balance is not correct", financesPage.compareBalance(defaultBalance), true);




        tearDown();
        setUp();

/*
MANAGER COMPLETED ORDER
 */

        Map dataForManagerValidLogIn = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "ManagerLogin");

        loginPage.userValidLogIn(dataForManagerValidLogIn.get("login").toString(),dataForManagerValidLogIn.get("pass").toString());


        dashboardPage.clickOnMenuDash();
        dashboardPage.clickMenuSizeButton();
//        selectBrowserWindow("mainWindow");

        dashboardPage.clickOnMenuPageELD();
        Thread.sleep(5000);
        managerEldPage.clickOnEldOrders();
        Thread.sleep(5000);
        managerEldPage.enterIdOrder(idLastOrderAfterTest);
        Thread.sleep(5000);
        managerEldPage.clickOnOrderOnList(idLastOrderAfterTest);
        modalOrderPage.selectOrderStatus("4");
        modalOrderPage.clickButtonSave();
        modalOrderPage.selectOrderStatus("1");
        modalOrderPage.clickButtonSave();

/*
CHECK ORDER STATUS FROM DATABASE
 */

        Thread.sleep(5000);
        String orderStatus = utilsForDB.getOrderStatus(idLastOrderAfterTest);
        checkAC("Order is not completed", orderStatus.equals("1") , true);

    }


}

