package chargeTest;

import org.junit.Test;
import parentTest.ParentTestWithoutWebDriver;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Map;

import static libs.Utils.*;

public class ChargeSmartSafety extends ParentTestWithoutWebDriver {
    Map dataForFleet = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "validFleetLogin");

    String fleetId = dataForFleet.get("fleetId").toString();


    public ChargeSmartSafety() throws IOException {
    }

    @Test
    public void chargeSmartSafetyByBalance() throws SQLException {

        double balanceBeforeTest = 100.00;
        String buyServicesDateTime = chargeSmartSafetyMethods.buyServicesDateTime();
        String paidTillServicesDateTime = chargeSmartSafetyMethods.paidTillServicesDateTime();

        String smartSafetyUserId = utilsForDB.getSmartSafetyUserId(fleetId);
        checkAC("Fleet has not smart safety services", smartSafetyUserId.isEmpty(), false);

        utilsForDB.setCurrentDueForFleet(Double.toString(balanceBeforeTest), fleetId);
        String dateTime = getDateTimeUTC("yyyy-MM-dd HH:mm:ss");
        utilsForDB.updateServicesConnections(fleetId, smartSafetyUserId, buyServicesDateTime, paidTillServicesDateTime);

        waitABit(70);
        String balanceAfterTest = utilsForDB.getCurrentDueEzFinancesFleet(fleetId);
        System.out.println(" balanceBeforeTest = " + balanceBeforeTest);
        System.out.println(" balanceAfterTest = " + Double.parseDouble(balanceAfterTest));
        checkAC("Balance is not correct", chargeSmartSafetyMethods.compareBalance(balanceBeforeTest, Double.parseDouble(balanceAfterTest)), true);
        checkAC("No service transactions after test", utilsForDB.checkForTransactions(fleetId, dateTime), true);

        LocalDateTime dateTimeSubscribedTill = getLocalDateTimeFromString(utilsForDB.getSubscribedTillDateTime(fleetId, smartSafetyUserId));

        checkAC("Subscribed Till dateTime is not correct", chargeSmartSafetyMethods.compareSubscribedTill(dateTimeSubscribedTill), true);

    }
    @Test
    public void chargeSmartSafetyByCard() throws SQLException {
        double balanceBeforeTest = 0;
        String buyServicesDateTime = chargeSmartSafetyMethods.buyServicesDateTime();
        String paidTillServicesDateTime = chargeSmartSafetyMethods.paidTillServicesDateTime();

        String smartSafetyUserId = utilsForDB.getSmartSafetyUserId(fleetId);
        checkAC("Fleet has not smart safety services", smartSafetyUserId.isEmpty(), false);


        utilsForDB.setCurrentDueForFleet(Double.toString(balanceBeforeTest), fleetId);
        String dateTime = getDateTimeUTC("yyyy-MM-dd HH:mm:ss");
        utilsForDB.updateServicesConnections(fleetId, smartSafetyUserId, buyServicesDateTime, paidTillServicesDateTime);


        waitABit(70);
        checkAC("No transactions after test", utilsForDB.checkForTransactions(fleetId, dateTime), true);
        LocalDateTime dateTimeSubscribedTill = getLocalDateTimeFromString(utilsForDB.getSubscribedTillDateTime(fleetId, smartSafetyUserId));

        checkAC("Subscribed Till dateTime is not correct", chargeSmartSafetyMethods.compareSubscribedTill(dateTimeSubscribedTill), true);

    }

}
