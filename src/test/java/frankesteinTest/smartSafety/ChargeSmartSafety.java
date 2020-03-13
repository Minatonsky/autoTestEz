package frankesteinTest.smartSafety;

import org.junit.Test;
import parentTest.ParentTestWithoutWebDriver;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Map;

import static libs.DataForTests.carrierIdString;
import static libs.Prices.smartSafetyPrice;
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
        String dateTime = getStringDateTimeUTC("yyyy-MM-dd HH:mm:ss");
        utilsForDB.updateServicesConnections(fleetId, smartSafetyUserId, buyServicesDateTime, paidTillServicesDateTime);

        waitABit(70);
        LocalDateTime currentDateTime = getLocalDateTimeUTC();
        String balanceAfterTest = utilsForDB.getCurrentDueEzFinancesFleet(fleetId);
        checkAC("Balance is not correct", Math.round((balanceBeforeTest + Double.parseDouble(balanceAfterTest)) * 100.0) / 100.0 == smartSafetyPrice, true);
        checkAC("No service transactions after test", utilsForDB.checkForTransactions(fleetId, dateTime), true);

        LocalDateTime dateTimeSubscribedTill = getLocalDateTimeFromString(utilsForDB.getSubscribedTillDateTime(fleetId, smartSafetyUserId));
        checkAC("Subscribed Till dateTime is not correct", compareDiffDateTime(currentDateTime.plusMonths(1), dateTimeSubscribedTill, 2), true);

    }
    @Test
    public void chargeSmartSafetyByBalanceAndCard() throws SQLException {

        double balanceBeforeTest = 10.00;
        String buyServicesDateTime = chargeSmartSafetyMethods.buyServicesDateTime();
        String paidTillServicesDateTime = chargeSmartSafetyMethods.paidTillServicesDateTime();

        String smartSafetyUserId = utilsForDB.getSmartSafetyUserId(fleetId);
        checkAC("Fleet has not smart safety services", smartSafetyUserId.isEmpty(), false);

        utilsForDB.setCurrentDueForFleet(Double.toString(balanceBeforeTest), fleetId);
        String dateTime = getStringDateTimeUTC("yyyy-MM-dd HH:mm:ss");
        utilsForDB.updateServicesConnections(fleetId, smartSafetyUserId, buyServicesDateTime, paidTillServicesDateTime);

        waitABit(70);
        LocalDateTime currentDateTime = getLocalDateTimeUTC();
        String balanceAfterTest = utilsForDB.getCurrentDueEzFinancesFleet(fleetId);
        checkAC("Balance is not correct",Double.parseDouble(balanceAfterTest) == 0, true);
        checkAC("No service transactions after test", utilsForDB.checkForTransactions(fleetId, dateTime), true);

        LocalDateTime dateTimeSubscribedTill = getLocalDateTimeFromString(utilsForDB.getSubscribedTillDateTime(fleetId, smartSafetyUserId));
        checkAC("Subscribed Till dateTime is not correct", compareDiffDateTime(currentDateTime.plusMonths(1), dateTimeSubscribedTill, 2), true);

    }
    @Test
    public void chargeSmartSafetyByCard() throws SQLException {
        double balanceBeforeTest = 0;
        String buyServicesDateTime = chargeSmartSafetyMethods.buyServicesDateTime();
        String paidTillServicesDateTime = chargeSmartSafetyMethods.paidTillServicesDateTime();

        String smartSafetyUserId = utilsForDB.getSmartSafetyUserId(fleetId);
        checkAC("Fleet has not smart safety services", smartSafetyUserId.isEmpty(), false);

        utilsForDB.setCurrentDueForFleet(Double.toString(balanceBeforeTest), fleetId);
        String dateTime = getStringDateTimeUTC("yyyy-MM-dd HH:mm:ss");
        utilsForDB.updateServicesConnections(fleetId, smartSafetyUserId, buyServicesDateTime, paidTillServicesDateTime);

        waitABit(70);
        LocalDateTime currentDateTime = getLocalDateTimeUTC();
        checkAC("No transactions after test", utilsForDB.checkForTransactions(fleetId, dateTime), true);
        LocalDateTime dateTimeSubscribedTill = getLocalDateTimeFromString(utilsForDB.getSubscribedTillDateTime(fleetId, smartSafetyUserId));
        checkAC("Subscribed Till dateTime is not correct", compareDiffDateTime(currentDateTime.plusMonths(1), dateTimeSubscribedTill, 2), true);

    }
    @Test
    public void chargeSmartSafetyDefaulters() throws SQLException {
        try {
            double balanceBeforeTest = 10.00;
            String buyServicesDateTime = chargeSmartSafetyMethods.buyServicesDateTime();
            String paidTillServicesDateTime = chargeSmartSafetyMethods.paidTillServicesDateTime();

            String smartSafetyUserId = utilsForDB.getSmartSafetyUserId(fleetId);
            checkAC("Fleet has not smart safety services", smartSafetyUserId.isEmpty(), false);

            utilsForDB.setCurrentDueForFleet(Double.toString(balanceBeforeTest), fleetId);
            utilsForDB.setCurrentCard_0_Fleet(fleetId);
            String dateTime = getStringDateTimeUTC("yyyy-MM-dd HH:mm:ss");
            utilsForDB.updateServicesConnections(fleetId, smartSafetyUserId, buyServicesDateTime, paidTillServicesDateTime);

            waitABit(70);
            LocalDateTime currentDateTime = getLocalDateTimeUTC();
            String balanceAfterTest = utilsForDB.getCurrentDueEzFinancesFleet(fleetId);

            checkAC("Balance is not correct",Double.parseDouble(balanceAfterTest) == Math.round((smartSafetyPrice - balanceBeforeTest)*100.0)/100.0, true);
            checkAC("No service transactions after test", utilsForDB.checkForTransactions(fleetId, dateTime), true);

            LocalDateTime dateTimeSubscribedTill = getLocalDateTimeFromString(utilsForDB.getSubscribedTillDateTime(fleetId, smartSafetyUserId));
            checkAC("Subscribed Till dateTime is not correct", compareDiffDateTime(currentDateTime.plusMonths(1), dateTimeSubscribedTill, 2), true);

        } finally {
            utilsForDB.setCurrentCard(carrierIdString, fleetId);
        }



    }

}
