package orders;

import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

public class SoloEldOrderCancelByManagerTest extends ParentSoloTest {


    public SoloEldOrderCancelByManagerTest() throws IOException {
    }

    @Test
    public void orderCancelByManager() throws InterruptedException, SQLException, IOException, ClassNotFoundException {

        String idLastOrderAfterTest = utilsForDB.getLastOrderIdForFleet(dataSoloId.get("fleetId").toString());
        loginPage.userValidLogIn(dataForManagerValidLogIn.get("login").toString(),dataForManagerValidLogIn.get("pass").toString());

        dashboardPage.openMenuDash();
        dashboardPage.goToEldPage();
        managerEldPage.openOrderInfo(idLastOrderAfterTest);
        orderInfoPage.selectOrderStatus("2");
        orderInfoPage.clickButtonSave();

        tearDown();
        setUp();

/*
USER CHECK IF BALANCE IS RETURNED
 */
        loginPage.userValidLogIn(dataForSoloValidLogIn.get("login").toString(),dataForSoloValidLogIn.get("pass").toString());

        dashboardPage.openMenuDash();
        dashboardPage.goToFinancesPage();
        checkAC("Balance is not correct", financesPage.compareBalance(dataForEldOrder.get("balanceIfCanceled").toString()), true);

        String orderStatus = utilsForDB.getOrderStatus(idLastOrderAfterTest);
        checkAC("Order is not completed", orderStatus.equals("2") , true);

    }

}
