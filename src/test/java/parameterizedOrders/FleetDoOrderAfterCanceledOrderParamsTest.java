package parameterizedOrders;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.sql.SQLException;

@RunWith(Parameterized.class)
public class FleetDoOrderAfterCanceledOrderParamsTest extends ParentFleetOrderParamsTest {

    public FleetDoOrderAfterCanceledOrderParamsTest(String quantityOfDevices, String typeOfPaymentMethod, String quantityPinCable, String quantityOBDPinCable, String quantitySticker, String quantityCameraCP, String valueSdCard, String quantityCameraSVA, String typeOfPaymentMethodCamera, String neededStatePickUpFromOffice, String neededStateOvernightDelivery, String currentDue) throws IOException {
        super(quantityOfDevices, typeOfPaymentMethod, quantityPinCable, quantityOBDPinCable, quantitySticker, quantityCameraCP, valueSdCard, quantityCameraSVA, typeOfPaymentMethodCamera, neededStatePickUpFromOffice, neededStateOvernightDelivery, currentDue);
    }

    @Test
    public void cancelDevicesByUser() throws SQLException, IOException, ClassNotFoundException {

        String idLastOrderAfterTest = utilsForDB.getLastOrderIdForFleet(dataFleetId.get("fleetId").toString());
        dashboardPage.goToEldPage();
        userEldPage.cancelEldDevices(idLastOrderAfterTest, quantityOfDevices, quantityCameraCP);
        String orderStatus = utilsForDB.getOrderStatus(idLastOrderAfterTest);
        checkAC("Order with devices is not canceled", userEldPage.compareCancelDeviceStatusOrder(orderStatus, quantityOfDevices), true);
        checkAC("ELD is present in canceled order", utilsForDB.isEldBlinded(idLastOrderAfterTest), false);
        dashboardPage.goToFinancesPage();
        String dueForLastOrder = utilsForDB.getLastDueForFleet(dataFleetId.get("fleetId").toString());
        checkAC("Balance is not correct", financesPage.compareBalanceIfCanceled(currentDue, dueForLastOrder, quantityOfDevices), true);

    }
}
