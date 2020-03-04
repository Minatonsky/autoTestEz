package chargeTest;

import org.junit.Test;
import parentTest.ParentTest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import static libs.Utils.getDateAndTime;
import static libs.Utils.waitABit;

public class ChargeSmartSafety extends ParentTest {
    Map dataForFleet = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "validFleetLogin");

    String fleetId = dataForFleet.get("fleetId").toString();
    double balanceBeforeTest = 100.00;

    public ChargeSmartSafety() throws IOException {
    }

    @Test
    public void chargeSmartSafetyByBalance() throws SQLException, IOException, ClassNotFoundException {

        String buyServicesDateTime = chargeSmartSafetyMethods.buyServicesDateTime();
        String paidTillServicesDateTime = chargeSmartSafetyMethods.paidTillServicesDateTime();

        String smartSafetyUserId = utilsForDB.getSmartSafetyUserId(fleetId);
        checkAC("Fleet has not smart safety services", smartSafetyUserId.isEmpty(), false);

        utilsForDB.setCurrentDueForFleet(Double.toString(balanceBeforeTest), fleetId);
        utilsForDB.updateServicesConnections(fleetId, smartSafetyUserId, buyServicesDateTime, paidTillServicesDateTime);

        waitABit(70);
        String balanceAfterTest = utilsForDB.getCurrentDueEzFinancesFleet(fleetId);
        checkAC("Balance is not correct", chargeSmartSafetyMethods.compareBalance(balanceBeforeTest, Double.valueOf(balanceAfterTest)), true);

    }
    @Test
    public void chargeSmartSafetyByCard() throws SQLException, IOException, ClassNotFoundException {
        String buyServicesDateTime = chargeSmartSafetyMethods.buyServicesDateTime();
        String paidTillServicesDateTime = chargeSmartSafetyMethods.paidTillServicesDateTime();

        String smartSafetyUserId = utilsForDB.getSmartSafetyUserId(fleetId);
        checkAC("Fleet has not smart safety services", smartSafetyUserId.isEmpty(), false);


        utilsForDB.setCurrentDueForFleet(Double.toString(balanceBeforeTest), fleetId);
        utilsForDB.updateServicesConnections(fleetId, smartSafetyUserId, buyServicesDateTime, paidTillServicesDateTime);
        String dateTime = getDateAndTime("yyyy-MM-dd HH:mm:ss");

        waitABit(70);
        checkAC("No transactions after test", utilsForDB.checkForTransactions(fleetId, dateTime), true);

    }

}
