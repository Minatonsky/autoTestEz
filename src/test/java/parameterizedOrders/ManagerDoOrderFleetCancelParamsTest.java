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

public class ManagerDoOrderFleetCancelParamsTest extends ParentTest {String  quantityOfDevices, typeOfPaymentMethod, quantityPinCable, quantityOBDPinCable, quantitySticker, quantityCamera1, quantityCamera2, neededStateDeliveryCost, neededStateOvernightDelivery, currentDue, eldOrderPrice, eldDeliveryPrice, eldFirstMonthFee, eldLastMonthFee, eldDepositFee, defaultTotalOrder, defaultBalance, balanceIfCanceled;

    public ManagerDoOrderFleetCancelParamsTest(String quantityOfDevices, String typeOfPaymentMethod, String quantityPinCable, String quantityOBDPinCable, String quantitySticker, String quantityCamera1, String quantityCamera2, String neededStateDeliveryCost, String neededStateOvernightDelivery, String currentDue, String eldOrderPrice, String eldDeliveryPrice, String eldFirstMonthFee, String eldLastMonthFee, String eldDepositFee, String defaultTotalOrder, String defaultBalance, String balanceIfCanceled) {
        this.quantityOfDevices = quantityOfDevices;
        this.typeOfPaymentMethod = typeOfPaymentMethod;
        this.quantityPinCable = quantityPinCable;
        this.quantityOBDPinCable = quantityOBDPinCable;
        this.quantitySticker = quantitySticker;
        this.quantityCamera1 = quantityCamera1;
        this.quantityCamera2 = quantityCamera2;
        this.neededStateDeliveryCost = neededStateDeliveryCost;
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
        return new SpreadsheetData(spreadsheet,"suitOrderDataForManager").getData();

    }
    @Test
    public void addNewOrder() throws IOException, InterruptedException, SQLException, ClassNotFoundException {
        ExcelDriver excelDriver = new ExcelDriver();
        UtilsForDB utilsForDB = new UtilsForDB();

        Map personalDataForEldOrder = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testEldOrder.xls", "personalData");
        Map dataForFleetValidLogIn = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "validFleetLogin");
        Map dataForManagerValidLogIn = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "ManagerLogin");
        Map dataFleetId = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "validFleetLogin");

//        int countNewOrder = utilsForDB.getCountNewOrder(dataFleetId.get("fleetId").toString());
//        if (countNewOrder > 0)
//            loginPage.userValidLogIn(dataForFleetValidLogIn.get("login").toString(),dataForFleetValidLogIn.get("pass").toString());
//        waitABit(3);
//        modalEldPage.doCancelAgreementForManagerOrder();
//
//
//        tearDown();
//        setUp();


        utilsForDB.getSetCurrentDueForFleet(currentDue, dataFleetId.get("fleetId").toString());

        loginPage.userValidLogIn(dataForManagerValidLogIn.get("login").toString(),dataForManagerValidLogIn.get("pass").toString());

        dashboardPage.openMenuDash();

        dashboardPage.goToEldPage();

        managerEldPage.clickOnNewOrderButton();

        managerModalEldPage.selectFleetInOrder(dataForFleetValidLogIn.get("usdot").toString());
        managerModalEldPage.enterPersonalData(personalDataForEldOrder.get("deliveryState").toString(), personalDataForEldOrder.get("firstName").toString(), personalDataForEldOrder.get("lastName").toString(), personalDataForEldOrder.get("phone").toString(), personalDataForEldOrder.get("addressLine").toString(), personalDataForEldOrder.get("aptNumber").toString(), personalDataForEldOrder.get("deliveryCity").toString(), personalDataForEldOrder.get("zipCode").toString());
        managerModalEldPage.enterOrderData(quantityOfDevices, quantityPinCable, quantityOBDPinCable, quantitySticker, quantityCamera1, quantityCamera2, neededStateDeliveryCost, neededStateOvernightDelivery);

        managerModalEldPage.setDeliveryCost(neededStateDeliveryCost);
        managerModalEldPage.setPaymentMethod(typeOfPaymentMethod);
        Thread.sleep(2000);


        managerModalEldPage.compareOrderPrice(eldOrderPrice);
        checkAC("OrderPrice is not correct", managerModalEldPage.compareOrderPrice(eldOrderPrice), true);

        managerModalEldPage.compareDeliveryPrice(eldDeliveryPrice);
        checkAC("DeliveryPrice is not correct", managerModalEldPage.compareDeliveryPrice(eldDeliveryPrice), true);

        managerModalEldPage.compareFirstMonthFee(eldFirstMonthFee);
        checkAC("FirstMonthFee is not correct", managerModalEldPage.compareFirstMonthFee(eldFirstMonthFee), true);

        managerModalEldPage.compareLastMonthFee(eldLastMonthFee);
        checkAC("LastMonthFee is not correct", managerModalEldPage.compareLastMonthFee(eldLastMonthFee), true);

        managerModalEldPage.compareDepositFee(eldDepositFee);
        checkAC("DepositFee is not correct", managerModalEldPage.compareDepositFee(eldDepositFee), true);

        managerModalEldPage.compareTotalOrder(defaultTotalOrder);
        checkAC("Total Order is not correct", managerModalEldPage.compareTotalOrder(defaultTotalOrder), true);


        managerModalEldPage.clickButtonOrder();

        tearDown();
        setUp();

        loginPage.userValidLogIn(dataForFleetValidLogIn.get("login").toString(),dataForFleetValidLogIn.get("pass").toString());

        modalEldPage.doCancelAgreementForManagerOrder();

        dashboardPage.openMenuDash();
        dashboardPage.goToFinancesPage();

//        financesPage.compareBalance(balanceIfCanceled);
//        checkAC("Balance is not correct", financesPage.compareBalance(balanceIfCanceled), true);



    }
}
