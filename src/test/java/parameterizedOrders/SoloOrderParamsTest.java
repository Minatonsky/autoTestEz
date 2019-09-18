package parameterizedOrders;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.sql.SQLException;
@RunWith(Parameterized.class)

public class SoloOrderParamsTest extends ParentSoloOrderParamsTest {
    public SoloOrderParamsTest(String quantityOfDevices, String typeOfPaymentMethod, String quantityPinCable, String quantityOBDPinCable, String quantitySticker, String quantityCameraCP, String valueSdCard, String quantityCameraSVA, String typeOfPaymentMethodCamera, String neededStatePickUpFromOffice, String neededStateOvernightDelivery, String currentDue) throws IOException {
        super(quantityOfDevices, typeOfPaymentMethod, quantityPinCable, quantityOBDPinCable, quantitySticker, quantityCameraCP, valueSdCard, quantityCameraSVA, typeOfPaymentMethodCamera, neededStatePickUpFromOffice, neededStateOvernightDelivery, currentDue);
    }


    @Test
    public void cancelDevicesByUser() throws SQLException, IOException, ClassNotFoundException {

        String idLastOrderAfterTest = utilsForDB.getLastOrderIdForSolo(dataSoloId.get("soloId").toString());
        dashboardPage.goToEldPage();
        userEldPage.cancelEldDevices(idLastOrderAfterTest, quantityOfDevices, quantityCameraCP);
        String orderStatus = utilsForDB.getOrderStatus(idLastOrderAfterTest);
        checkAC("Order with devices is not canceled", userEldPage.compareCancelStatusOrder(orderStatus, quantityOfDevices), true);
        checkAC("ELD is present in canceled order", utilsForDB.isEldBlinded(idLastOrderAfterTest), false);
        dashboardPage.goToFinancesPage();
        String dueForLastOrder = utilsForDB.getLastDueForSolo(dataSoloId.get("soloId").toString());
        checkAC("Balance is not correct", financesPage.compareBalanceIfCanceled(currentDue, dueForLastOrder, quantityOfDevices), true);

    }

    @Test
    public void managerCanceledOrder() throws SQLException, IOException, ClassNotFoundException {

        tearDown();
        setUp();

        String idLastOrderAfterTest = utilsForDB.getLastOrderIdForSolo(dataSoloId.get("soloId").toString());
        String dueForLastOrder = utilsForDB.getLastDueForSolo(dataSoloId.get("soloId").toString());
        loginPage.userValidLogIn(dataForManagerValidLogIn.get("login").toString(),dataForManagerValidLogIn.get("pass").toString());
        dashboardPage.openMenuDash();
        dashboardPage.goToEldPage();
        managerEldPage.openOrderInfo(idLastOrderAfterTest);
        checkAC("Full Order Price is not correct", orderInfoPage.compareFullOrderPrice(dueForLastOrder), true);
        orderInfoPage.selectOrderStatus("2");
        orderInfoPage.clickButtonSave();
        String orderCancelStatus = utilsForDB.getOrderStatus(idLastOrderAfterTest);
        checkAC("Order with devices is not canceled", financesPage.compareCancelOrderStatus(orderCancelStatus), true);
        checkAC("ELD is present in canceled order", utilsForDB.isEldBlinded(idLastOrderAfterTest), false);

        tearDown();
        setUp();


//USER CHECKED BALANCE

        loginPage.userValidLogIn(dataForSoloValidLogIn.get("login").toString(),dataForSoloValidLogIn.get("pass").toString());
        dashboardPage.openMenuDash();
        dashboardPage.goToFinancesPage();
        checkAC("Balance is not correct", financesPage.compareBalanceIfCanceled(currentDue, dueForLastOrder, quantityOfDevices), true);

    }

    @Test
    public void orderCompletedByManager() throws SQLException, IOException, ClassNotFoundException {

        tearDown();
        setUp();

        String idLastOrderAfterTest = utilsForDB.getLastOrderIdForSolo(dataSoloId.get("soloId").toString());
        String dueForLastOrder = utilsForDB.getLastDueForSolo(dataSoloId.get("soloId").toString());
        loginPage.userValidLogIn(dataForManagerValidLogIn.get("login").toString(),dataForManagerValidLogIn.get("pass").toString());
        dashboardPage.openMenuDash();
        dashboardPage.goToEldPage();
        managerEldPage.openOrderInfo(idLastOrderAfterTest);
        checkAC("Full Order Price is not correct", orderInfoPage.compareFullOrderPrice(dueForLastOrder), true);
        orderInfoPage.completedOrder();

        String orderStatus = utilsForDB.getOrderStatus(idLastOrderAfterTest);
        checkAC("Order is not completed", financesPage.compareCompletedOrderStatus(orderStatus), true);
        checkAC("Eld status in Completed order is not correct", userEldPage.compareEldStatusInCompletedOrder(idLastOrderAfterTest), true);


//USER CHECK IF BALANCE IS CORRECT

        tearDown();
        setUp();

        loginPage.userValidLogIn(dataForSoloValidLogIn.get("login").toString(),dataForSoloValidLogIn.get("pass").toString());
        dashboardPage.openMenuDash();
        dashboardPage.goToFinancesPage();
        checkAC("Balance is not correct", financesPage.compareBalance(currentDue, dueForLastOrder), true);

    }

}
