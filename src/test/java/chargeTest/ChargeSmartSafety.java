package chargeTest;

import org.junit.Test;
import parentTest.ParentTest;

import java.io.IOException;
import java.sql.SQLException;

public class ChargeSmartSafety extends ParentTest {
    String fleetId = "518";

    @Test
    public void chargeFleetForSmartSafety() throws SQLException, IOException, ClassNotFoundException {
        String smartSafetyUserId = utilsForDB.getSmartSafetyUserId(fleetId);
        System.out.println("smartSafetyUserId = " + smartSafetyUserId);
        checkAC("Fleet has not smart safety services", smartSafetyUserId.isEmpty(), false);
        String buyServicesDateTime = chargeSmartSafetyMethods.buyServicesDateTime();
        String paidTillServicesDateTime = chargeSmartSafetyMethods.paidTillServicesDateTime();

        System.out.println("smartSafetyUserId = " + smartSafetyUserId);
        System.out.println("buyServicesDateTime = " + buyServicesDateTime);
        System.out.println("paidTillServicesDateTime = " + paidTillServicesDateTime);

        utilsForDB.updateServicesConnections(fleetId, smartSafetyUserId, buyServicesDateTime, paidTillServicesDateTime);
    }

}
