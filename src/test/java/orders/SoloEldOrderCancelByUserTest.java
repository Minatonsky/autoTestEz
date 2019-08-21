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
        String orderStatus = utilsForDB.getOrderStatus(idLastOrderAfterTest);
        String dueForLastOrder = utilsForDB.getLastDueForSolo(dataSoloId.get("soloId").toString());

        dashboardPage.goToEldPage();
        userEldPage.cancelEldDevices(idLastOrderAfterTest, dataForEldOrder.get("quantityOfDevices").toString(), dataForEldOrder.get("quantityCameraCP").toString());
        checkAC("Order is not canceled", orderStatus.equals("2") , true);
        dashboardPage.goToFinancesPage();
        checkAC("Balance is not correct", financesPage.compareBalanceIfCanceled(dataForEldOrder.get("currentDue").toString(), dueForLastOrder, dataForEldOrder.get("quantityOfDevices").toString()), true);

    }
}
