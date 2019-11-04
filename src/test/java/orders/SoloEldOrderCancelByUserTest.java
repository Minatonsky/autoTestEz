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
        userEldPage.cancelEldDevices(idLastOrderAfterTest, dataForEldOrder.get("quantityOfDevices").toString(), dataForEldOrder.get("quantityCameraCP").toString());
        String orderStatus = utilsForDB.getOrderStatus(idLastOrderAfterTest);
        checkAC("Order with devices is not canceled", userEldPage.compareCancelStatusOrder(orderStatus, dataForEldOrder.get("quantityOfDevices").toString()), true);
        checkAC("ELD is present in canceled order", utilsForDB.isEldBlinded(idLastOrderAfterTest), false);
        dashboardPage.goToFinancesPage();
        String dueForLastOrder = utilsForDB.getLastDueForSolo(dataSoloId.get("soloId").toString());
        checkAC("Balance is not correct", financesPage.compareBalanceIfCanceled(dataForEldOrder.get("currentDue").toString(), dueForLastOrder, dataForEldOrder.get("quantityOfDevices").toString()), true);

    }
}
