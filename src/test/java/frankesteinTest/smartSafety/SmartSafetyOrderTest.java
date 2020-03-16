package frankesteinTest.smartSafety;

import org.junit.Test;
import parentTest.ParentTest;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static libs.DataForTests.carrierIdString;
import static libs.Prices.smartSafetyPrice;
import static libs.Utils.*;

public class SmartSafetyOrderTest extends ParentTest {
    Map dataForFleet = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "validFleetLogin");

    String login = dataForFleet.get("login").toString();
    String fleetId = dataForFleet.get("fleetId").toString();
    String pass = "testtest";

    public SmartSafetyOrderTest() throws IOException {
    }

    @Test
    public void orderSmartSafetyByCard() throws SQLException {
        String randomDriverEmail = utilsForDB.getRandomDriverEmail(fleetId);
        System.out.println(randomDriverEmail);
        String driverId = utilsForDB.getUserIdByEmail(randomDriverEmail);
        utilsForDB.deleteSmartSafetyFoDriver(driverId);

        loginFPage.userValidLogIn(login, pass);
        dashboardFPage.goToFleetPage();
        fleetDriversPage.goToDriverPage();

        fleetDriversFPage.enterDriverEmail(randomDriverEmail);
        fleetDriversFPage.clickOnDriverInList(driverId);
        fleetDriversFPage.clickOnDriverSettings();
        fleetDriversFPage.clickOnSmartSafety();
        checkAC("Agreement is not present", fleetDriversFPage.isAgreementPresent(), true);

        fleetDriversFPage.clickOnButtonIAgree();
        fleetDriversFPage.clickOnSaveButton();
        LocalDateTime currentDateTime = getLocalDateTimeUTC();
        waitABit(5);
        checkAC("Smart safety does not exist in User App table", utilsForDB.isSmartSafetyInUserApp(driverId), true);

        List<ArrayList> tempListAtTillDateTimeServices = utilsForDB.getAtTillDateTimeServices(fleetId, driverId);
        Map<String, Object> tempDataReminderMap = listArrayToMap(tempListAtTillDateTimeServices);
        LocalDateTime timeDateCreatedAt = getLocalDateTimeFromString(tempDataReminderMap.get("created_at").toString());
        LocalDateTime timeDateSubscribedTill = getLocalDateTimeFromString(tempDataReminderMap.get("subscribed_till").toString());
        checkAC("Created At dateTime is not correct", compareDiffDateTime(currentDateTime, timeDateCreatedAt, 2), true);
        checkAC("Subscribed Till dateTime is not correct", compareDiffDateTime(currentDateTime.plusMonths(1), timeDateSubscribedTill, 2), true);


    }
    @Test
    public void orderSmartSafetyByBalance() throws SQLException {
        double balanceBeforeTest = 100.00;
        String randomDriverEmail = utilsForDB.getRandomDriverEmail(fleetId);
        System.out.println(randomDriverEmail);

        String driverId = utilsForDB.getUserIdByEmail(randomDriverEmail);
        utilsForDB.deleteSmartSafetyFoDriver(driverId);
        utilsForDB.setCurrentDueForFleet(Double.toString(balanceBeforeTest), fleetId);
        utilsForDB.setCurrentCard_0_Fleet(fleetId);

        loginFPage.userValidLogIn(login, pass);
        checkAC("User wasn`t logined", dashboardFPage.isDashboardPresent(), true);
        dashboardFPage.goToFleetPage();
        fleetDriversFPage.goToDriverPage();

        fleetDriversFPage.enterDriverEmail(randomDriverEmail);
        fleetDriversFPage.clickOnDriverInList(driverId);
        fleetDriversFPage.clickOnDriverSettings();
        fleetDriversFPage.clickOnSmartSafety();
        checkAC("Agreement is not present", fleetDriversFPage.isAgreementPresent(), true);

        fleetDriversFPage.clickOnButtonIAgree();
        fleetDriversFPage.clickOnSaveButton();
        LocalDateTime currentDateTime = getLocalDateTimeUTC();
        waitABit(5);
        checkAC("Smart safety does not exist in User App table", utilsForDB.isSmartSafetyInUserApp(driverId), true);

        List<ArrayList> tempListAtTillDateTimeServices = utilsForDB.getAtTillDateTimeServices(fleetId, driverId);
        Map<String, Object> tempDataReminderMap = listArrayToMap(tempListAtTillDateTimeServices);
        LocalDateTime timeDateCreatedAt = getLocalDateTimeFromString(tempDataReminderMap.get("created_at").toString());
        LocalDateTime timeDateSubscribedTill = getLocalDateTimeFromString(tempDataReminderMap.get("subscribed_till").toString());
        checkAC("Created At dateTime is not correct", compareDiffDateTime(currentDateTime, timeDateCreatedAt, 2), true);
        checkAC("Subscribed Till dateTime is not correct", compareDiffDateTime(currentDateTime.plusMonths(1), timeDateSubscribedTill, 2), true);
        String balanceAfterTest = utilsForDB.getCurrentDueEzFinancesFleet(fleetId);
        checkAC("Balance is not correct",Double.parseDouble(balanceAfterTest) == Math.round(((smartSafetyPrice*2) - balanceBeforeTest)*100.0)/100.0, true);

        utilsForDB.setCurrentCard(carrierIdString, fleetId);


    }
    @Test
    public void orderSmartSafetyByBalanceAndCard() throws SQLException {
        double balanceBeforeTest = 10.00;
        String randomDriverEmail = utilsForDB.getRandomDriverEmail(fleetId);
        System.out.println(randomDriverEmail);

        String driverId = utilsForDB.getUserIdByEmail(randomDriverEmail);
        utilsForDB.deleteSmartSafetyFoDriver(driverId);
        utilsForDB.setCurrentDueForFleet(Double.toString(balanceBeforeTest), fleetId);

        loginFPage.userValidLogIn(login, pass);
        checkAC("User wasn`t logined", dashboardFPage.isDashboardPresent(), true);
        dashboardFPage.goToFleetPage();
        fleetDriversFPage.goToDriverPage();

        fleetDriversFPage.enterDriverEmail(randomDriverEmail);
        fleetDriversFPage.clickOnDriverInList(driverId);
        fleetDriversFPage.clickOnDriverSettings();
        fleetDriversFPage.clickOnSmartSafety();
        checkAC("Agreement is not present", fleetDriversFPage.isAgreementPresent(), true);

        fleetDriversFPage.clickOnButtonIAgree();
        fleetDriversFPage.clickOnSaveButton();
        LocalDateTime currentDateTime = getLocalDateTimeUTC();
        waitABit(5);
        checkAC("Smart safety does not exist in User App table", utilsForDB.isSmartSafetyInUserApp(driverId), true);

        List<ArrayList> tempListAtTillDateTimeServices = utilsForDB.getAtTillDateTimeServices(fleetId, driverId);
        Map<String, Object> tempDataReminderMap = listArrayToMap(tempListAtTillDateTimeServices);
        LocalDateTime timeDateCreatedAt = getLocalDateTimeFromString(tempDataReminderMap.get("created_at").toString());
        LocalDateTime timeDateSubscribedTill = getLocalDateTimeFromString(tempDataReminderMap.get("subscribed_till").toString());
        checkAC("Created At dateTime is not correct", compareDiffDateTime(currentDateTime, timeDateCreatedAt, 2), true);
        checkAC("Subscribed Till dateTime is not correct", compareDiffDateTime(currentDateTime.plusMonths(1), timeDateSubscribedTill, 2), true);
        String balanceAfterTest = utilsForDB.getCurrentDueEzFinancesFleet(fleetId);
        checkAC("Balance is not correct",Double.parseDouble(balanceAfterTest) == 0, true);

        utilsForDB.setCurrentCard(carrierIdString, fleetId);

    }

    @Test
    public void orderSmartSafetyDefaulters() throws SQLException {
        try {
            double balanceBeforeTest = 10.00;
            String randomDriverEmail = utilsForDB.getRandomDriverEmail(fleetId);
            System.out.println(randomDriverEmail);

            String driverId = utilsForDB.getUserIdByEmail(randomDriverEmail);
            utilsForDB.deleteSmartSafetyFoDriver(driverId);
            utilsForDB.setCurrentDueForFleet(Double.toString(balanceBeforeTest), fleetId);
            utilsForDB.setCurrentCard_0_Fleet(fleetId);

            loginFPage.userValidLogIn(login, pass);
            checkAC("User wasn`t logined", dashboardFPage.isDashboardPresent(), true);
            dashboardFPage.goToFleetPage();
            fleetDriversFPage.goToDriverPage();

            fleetDriversFPage.enterDriverEmail(randomDriverEmail);
            fleetDriversFPage.clickOnDriverInList(driverId);
            fleetDriversFPage.clickOnDriverSettings();
            fleetDriversFPage.clickOnSmartSafety();
            checkAC("Agreement is not present", fleetDriversFPage.isAgreementPresent(), true);

            fleetDriversFPage.clickOnButtonIAgree();
            fleetDriversFPage.clickOnSaveButton();
            LocalDateTime currentDateTime = getLocalDateTimeUTC();
            waitABit(5);
            checkAC("Smart safety does not exist in User App table", utilsForDB.isSmartSafetyInUserApp(driverId), true);

            List<ArrayList> tempListAtTillDateTimeServices = utilsForDB.getAtTillDateTimeServices(fleetId, driverId);
            Map<String, Object> tempDataReminderMap = listArrayToMap(tempListAtTillDateTimeServices);
            LocalDateTime timeDateCreatedAt = getLocalDateTimeFromString(tempDataReminderMap.get("created_at").toString());
            LocalDateTime timeDateSubscribedTill = getLocalDateTimeFromString(tempDataReminderMap.get("subscribed_till").toString());
            checkAC("Created At dateTime is not correct", compareDiffDateTime(currentDateTime, timeDateCreatedAt, 2), true);
            checkAC("Subscribed Till dateTime is not correct", compareDiffDateTime(currentDateTime.plusMonths(1), timeDateSubscribedTill, 2), true);
            String balanceAfterTest = utilsForDB.getCurrentDueEzFinancesFleet(fleetId);
            checkAC("Balance is not correct", Double.parseDouble(balanceAfterTest) == Math.round(((smartSafetyPrice * 2) - balanceBeforeTest) * 100.0) / 100.0, true);
        } finally {
            utilsForDB.setCurrentCard(carrierIdString, fleetId);
            utilsForDB.setCurrentDueForFleet( "0", fleetId);
        }






    }

}
