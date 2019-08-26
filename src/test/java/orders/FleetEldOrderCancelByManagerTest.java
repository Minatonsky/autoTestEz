package orders;

import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

public class FleetEldOrderCancelByManagerTest extends ParentFleetTest {


    public FleetEldOrderCancelByManagerTest() throws IOException {
    }

    @Test
    public void orderCancelByManager() throws SQLException, IOException, ClassNotFoundException {
        tearDown();
        setUp();

        String idLastOrderAfterTest = utilsForDB.getLastOrderIdForFleet(dataFleetId.get("fleetId").toString());
        String dueForLastOrder = utilsForDB.getLastDueForFleet(dataFleetId.get("fleetId").toString());

        loginPage.userValidLogIn(dataForManagerValidLogIn.get("login").toString(),dataForManagerValidLogIn.get("pass").toString());
        dashboardPage.openMenuDash();
        dashboardPage.goToEldPage();
        managerEldPage.openOrderInfo(idLastOrderAfterTest);
        checkAC("Full Order Price is not correct", orderInfoPage.compareFullOrderPrice(dueForLastOrder), true);
        orderInfoPage.selectOrderStatus("2");
        orderInfoPage.clickButtonSave();
        String orderStatus = utilsForDB.getOrderStatus(idLastOrderAfterTest);
        checkAC("Order is not canceled", financesPage.compareCancelOrderStatus(orderStatus) , true);

        tearDown();
        setUp();

/*
USER CHECK IF BALANCE IS RETURNED
 */

        loginPage.userValidLogIn(dataForFleetValidLogIn.get("login").toString(),dataForFleetValidLogIn.get("pass").toString());
        dashboardPage.openMenuDash();
        dashboardPage.goToFinancesPage();

        checkAC("Balance is not correct", financesPage.compareBalanceIfCanceled(dataForEldOrder.get("currentDue").toString(), dueForLastOrder, dataForEldOrder.get("quantityOfDevices").toString()), true);

    }
}

