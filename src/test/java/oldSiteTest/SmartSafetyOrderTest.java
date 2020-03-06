package oldSiteTest;

import org.junit.Test;
import parentTest.ParentTest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static libs.Utils.listArrayToMap;
import static libs.Utils.waitABit;

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

        loginPage.userValidLogIn(login, pass);
        checkAC("User wasn`t logined", dashboardPage.isDashboardPresent(), true);
        dashboardPage.goToFleetPage();
        fleetDriversPage.goToDriverPage();

        fleetDriversPage.enterDriverEmail(randomDriverEmail);
        fleetDriversPage.clickOnDriverInList(driverId);
        fleetDriversPage.clickOnDriverSettings();
        fleetDriversPage.clickOnSmartSafety();
        checkAC("Agreement is not present", fleetDriversPage.isAgreementPresent(), true);

        fleetDriversPage.clickOnButtonIAgree();
        fleetDriversPage.clickOnSaveButton();
        waitABit(5);
        checkAC("Smart safety does not exist in User App table", utilsForDB.isSmartSafetyInUserApp(driverId), true);

        List<ArrayList> tempListAtTillDateTimeServices = utilsForDB.getAtTillDateTimeServices(fleetId, driverId);
        Map<String, Object> tempDataReminderMap = listArrayToMap(tempListAtTillDateTimeServices);
        tempDataReminderMap.get("created_at");
        System.out.println(tempDataReminderMap.get("created_at"));
        waitABit(5);

    }
}
