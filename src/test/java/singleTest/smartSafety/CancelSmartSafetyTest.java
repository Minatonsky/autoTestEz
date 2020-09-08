package singleTest.smartSafety;

import libs.ChargeSmartSafetyMethods;
import org.junit.Test;
import parentTest.ParentTest;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static libs.Utils.*;

public class CancelSmartSafetyTest extends ParentTest {
    ChargeSmartSafetyMethods chargeSmartSafetyMethods = new ChargeSmartSafetyMethods();
    Map dataForFleet = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "validFleetLogin");

    String login = dataForFleet.get("login").toString();
    String fleetId = dataForFleet.get("fleetId").toString();
    String pass = "testtest";

    public CancelSmartSafetyTest() throws IOException {
    }

    @Test
    public void cancelSmartSafety() throws SQLException {
        double balanceBeforeTest = 100.00;
        int chargeDays = 12;

        String smartSafetyUserId = utilsForDB.getSmartSafetyUserId(fleetId);
        checkAC("Fleet does not have smart safety services", smartSafetyUserId.isEmpty(), false);

        String buyServicesDateTime = getLocalDateTimeUTC().minusMonths(2).minusDays(12).toString();
        LocalDateTime paidTillServicesDateTime = getLocalDateTimeUTC().plusDays(chargeDays);
        utilsForDB.updateServicesConnections(fleetId, smartSafetyUserId, buyServicesDateTime, paidTillServicesDateTime.toString());
        utilsForDB.setCurrentDueForFleet(Double.toString(balanceBeforeTest), fleetId);

        loginPage.userValidLogIn(login, pass);
        checkAC("User isn`t logined", dashboardPage.isDashboardPresent(), true);
        dashboardPage.goToFleetPage();
        fleetDriversPage.goToDriverPage();

        fleetDriversPage.enterDriverEmail(utilsForDB.getUserEmailById(smartSafetyUserId));
        fleetDriversPage.clickOnDriverInList(smartSafetyUserId);
        fleetDriversPage.clickOnDriverSettings();
        fleetDriversPage.clickOffSmartSafety();
        fleetDriversPage.clickOnSaveButton();
        LocalDateTime currentDateTime = getLocalDateTimeUTC();
        waitABit(2);

        List<ArrayList> tempListAtTillDateTimeServices = utilsForDB.getAtTillDateTimeServices(fleetId, smartSafetyUserId);
        Map<String, Object> tempDataReminderMap = listArrayToMap(tempListAtTillDateTimeServices);
        LocalDateTime timeDateCreatedAt = getLocalDateTimeFromString(tempDataReminderMap.get("noticed_at").toString());
        LocalDateTime timeDateSubscribedTill = getLocalDateTimeFromString(tempDataReminderMap.get("updated_at").toString());
        checkAC("noticed_at dateTime is not correct", compareDiffDateTime(currentDateTime, timeDateCreatedAt, 2), true);
        checkAC("updated_at dateTime is not correct", compareDiffDateTime(currentDateTime, timeDateSubscribedTill, 2), true);

        String balanceAfterTest = utilsForDB.getCurrentDueEzFinancesFleet(fleetId);
        double noticeFee = chargeSmartSafetyMethods.countSixtyDaysNoticeFeeSmartSafety(chargeDays);

        checkAC("Balance is not correct",Double.parseDouble(balanceAfterTest) == noticeFee-balanceBeforeTest, true);
        checkAC("Smart safety exist in User App table after deleted", utilsForDB.isSmartSafetyInUserApp(smartSafetyUserId), false);

    }
}
