package orders;

import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

public class FleetEldOrderCompletedByManagerTest extends ParentFleetTest {


    public FleetEldOrderCompletedByManagerTest() throws IOException {
    }

    @Test
    public void orderCompletedByManager() throws SQLException, IOException, ClassNotFoundException {

        String idLastOrderAfterTest = utilsForDB.getLastOrderIdForFleet(dataFleetId.get("fleetId").toString());

        loginPage.userValidLogIn(dataForManagerValidLogIn.get("login").toString(),dataForManagerValidLogIn.get("pass").toString());
        dashboardPage.openMenuDash();
        dashboardPage.goToEldPage();
        managerEldPage.openOrderInfo(idLastOrderAfterTest);
        orderInfoPage.completedOrder();

        String orderStatus = utilsForDB.getOrderStatus(idLastOrderAfterTest);
        checkAC("Order is not completed", orderStatus.equals("1") , true);
    }
}

