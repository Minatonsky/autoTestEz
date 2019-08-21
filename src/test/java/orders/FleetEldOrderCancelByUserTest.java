package orders;

import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

public class FleetEldOrderCancelByUserTest extends ParentFleetTest {


    public FleetEldOrderCancelByUserTest() throws IOException {
    }

    @Test
    public void cancelDevicesByUser() throws SQLException, IOException, ClassNotFoundException {

        String idLastOrderAfterTest = utilsForDB.getLastOrderIdForFleet(dataFleetId.get("fleetId").toString());

        String dueForLastOrder = utilsForDB.getLastDueForFleet(dataFleetId.get("fleetId").toString());
        dashboardPage.goToEldPage();
        userEldPage.cancelEldDevices(idLastOrderAfterTest, dataForEldOrder.get("quantityOfDevices").toString(), dataForEldOrder.get("quantityCameraCP").toString());
        dashboardPage.goToFinancesPage();
        String orderStatus = utilsForDB.getOrderStatus(idLastOrderAfterTest);
        checkAC("Order is not canceled", orderStatus.equals("2") , true);
        checkAC("Balance is not correct", financesPage.compareBalanceIfCanceled(dataForEldOrder.get("currentDue").toString(), dueForLastOrder, dataForEldOrder.get("quantityOfDevices").toString()), true);

    }
}
