package orders;

import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

public class SoloEldOrderCompletedByManagerTest extends ParentSoloTest {


    public SoloEldOrderCompletedByManagerTest() throws IOException {
    }

    @Test
    public void orderCompletedByManager() throws SQLException, IOException, ClassNotFoundException {
        tearDown();
        setUp();

        String idLastOrderAfterTest = utilsForDB.getLastOrderIdForSolo(dataSoloId.get("soloId").toString());

        loginPage.userValidLogIn(dataForManagerValidLogIn.get("login").toString(),dataForManagerValidLogIn.get("pass").toString());
        dashboardPage.openMenuDash();
        dashboardPage.goToEldPage();
        managerEldPage.openOrderInfo(idLastOrderAfterTest);
        orderInfoPage.completedOrder();

        String orderStatus = utilsForDB.getOrderStatus(idLastOrderAfterTest);
        checkAC("Order is not completed", orderStatus.equals("1") , true);

    }

}


