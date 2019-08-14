package orders;

import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

public class FleetEldOrderCancelByManagerTest extends ParentFleetTest {


    public FleetEldOrderCancelByManagerTest() throws IOException {
    }

    @Test
    public void orderCancelByManager() throws SQLException, IOException, ClassNotFoundException {

        String idLastOrderAfterTest = utilsForDB.getLastOrderIdForFleet(dataFleetId.get("fleetId").toString());

        loginPage.userValidLogIn(dataForManagerValidLogIn.get("login").toString(),dataForManagerValidLogIn.get("pass").toString());
        dashboardPage.openMenuDash();
        dashboardPage.goToEldPage();
        managerEldPage.openOrderInfo(idLastOrderAfterTest);
        orderInfoPage.selectOrderStatus("2");
        orderInfoPage.clickButtonSave();

        tearDown();
        setUp();

/*
USER CHECK IF DEVICES IS ACTIVE AND BALANCE NOT CHANGED
 */

        loginPage.userValidLogIn(dataForFleetValidLogIn.get("login").toString(),dataForFleetValidLogIn.get("pass").toString());
        dashboardPage.openMenuDash();
        dashboardPage.goToFinancesPage();
        financesPage.compareBalance(dataForEldOrder.get("balanceIfCanceled").toString());
        checkAC("Balance is not correct", financesPage.compareBalance(dataForEldOrder.get("balanceIfCanceled").toString()), true);

    }

}

