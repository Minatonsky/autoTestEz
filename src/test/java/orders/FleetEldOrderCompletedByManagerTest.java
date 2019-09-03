package orders;

import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

public class FleetEldOrderCompletedByManagerTest extends ParentFleetTest {


    public FleetEldOrderCompletedByManagerTest() throws IOException {
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
        checkAC("Eld status in Completed order is not correct", userEldPage.compareEldStatusInCompletedOrder(idLastOrderAfterTest), true);

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

