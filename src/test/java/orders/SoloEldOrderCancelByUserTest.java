package orders;

import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

public class SoloEldOrderCancelByUserTest extends ParentSoloTest {


    public SoloEldOrderCancelByUserTest() throws IOException {
    }

    @Test
    public void cancelDevicesByUser() throws SQLException, IOException, ClassNotFoundException {

        String idLastOrderAfterTest = utilsForDB.getLastOrderIdForSolo(dataSoloId.get("soloId").toString());
        dashboardPage.goToEldPage();
        userEldPage.cancelEldDevices(idLastOrderAfterTest);
        dashboardPage.goToFinancesPage();
        financesPage.compareBalance(dataForEldOrder.get("balanceIfCanceled").toString());
        checkAC("Balance is not correct", financesPage.compareBalance(dataForEldOrder.get("balanceIfCanceled").toString()), true);
    }
}
