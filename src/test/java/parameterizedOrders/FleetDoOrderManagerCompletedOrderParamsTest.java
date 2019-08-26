package parameterizedOrders;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.sql.SQLException;

@RunWith(Parameterized.class)
public class FleetDoOrderManagerCompletedOrderParamsTest extends ParentFleetOrderParamsTest {

    public FleetDoOrderManagerCompletedOrderParamsTest(String quantityOfDevices, String typeOfPaymentMethod, String quantityPinCable, String quantityOBDPinCable, String quantitySticker, String quantityCameraCP, String valueSdCard, String quantityCameraSVA, String typeOfPaymentMethodCamera, String neededStatePickUpFromOffice, String neededStateOvernightDelivery, String currentDue) throws IOException {
        super(quantityOfDevices, typeOfPaymentMethod, quantityPinCable, quantityOBDPinCable, quantitySticker, quantityCameraCP, valueSdCard, quantityCameraSVA, typeOfPaymentMethodCamera, neededStatePickUpFromOffice, neededStateOvernightDelivery, currentDue);
    }

    @Test
    public void orderCompletedByManager() throws SQLException, IOException, ClassNotFoundException {
        tearDown();
        setUp();

        String idLastOrderAfterTest = utilsForDB.getLastOrderIdForFleet(dataFleetId.get("fleetId").toString());
        String dueForLastOrder = utilsForDB.getLastDueForFleet(dataFleetId.get("fleetId").toString());

        loginPage.userValidLogIn(dataForManagerValidLogIn.get("login").toString(),dataForManagerValidLogIn.get("pass").toString());
        dashboardPage.openMenuDash();
        dashboardPage.goToEldPage();
        managerEldPage.openOrderInfo(idLastOrderAfterTest);
        checkAC("Full Order Price is not correct", orderInfoPage.compareFullOrderPrice(dueForLastOrder), true);
        orderInfoPage.completedOrder();

        String orderStatus = utilsForDB.getOrderStatus(idLastOrderAfterTest);
        checkAC("Order is not completed", financesPage.compareCompletedOrderStatus(orderStatus) , true);

/*
USER CHECK IF BALANCE IS RETURNED
 */

        tearDown();
        setUp();

        loginPage.userValidLogIn(dataForFleetValidLogIn.get("login").toString(),dataForFleetValidLogIn.get("pass").toString());
        dashboardPage.openMenuDash();
        dashboardPage.goToFinancesPage();

        checkAC("Balance is not correct", financesPage.compareBalanceIfCanceled(currentDue, dueForLastOrder, quantityOfDevices), true);


    }
}

