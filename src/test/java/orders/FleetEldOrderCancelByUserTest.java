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
        dashboardPage.goToEldPage();
        userEldPage.cancelEldDevices(idLastOrderAfterTest);
        dashboardPage.goToFinancesPage();
        financesPage.compareBalance(dataForEldOrder.get("balanceIfCanceled").toString());
        checkAC("Balance is not correct", financesPage.compareBalance(dataForEldOrder.get("balanceIfCanceled").toString()), true);

    }
}
