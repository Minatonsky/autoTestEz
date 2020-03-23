package frankesteinTest.settingsTest;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import parentTest.ParentTest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static libs.Utils.*;

public class FleetAccountSettingTest extends ParentTest {
    Map dataForValidLogIn = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "validFleetLogin");

    String login = dataForValidLogIn.get("login").toString();
    String pass = dataForValidLogIn.get("pass").toString();
    String carrierId = dataForValidLogIn.get("fleetId").toString();

    public FleetAccountSettingTest() throws IOException {
    }

    @Test
    public void updateAccountSetting() throws SQLException, IOException, ClassNotFoundException {

        List<ArrayList> tempDataListBeforeTest = utilsForDB.getUsersAndDriversRulesData(login);
        Map<String, Object> userDataBeforeTestMap = listArrayToMap(tempDataListBeforeTest);

        String odometer = userDataBeforeTestMap.get("odometerId").toString();
        String firstName = genRandomDataByRegex("[A-Z]{1}[a-z]{11}");
        String LastName = genRandomDataByRegex("[A-Z]{1}[a-z]{11}");
        String phone = RandomStringUtils.randomNumeric(10);

        String soundNotificaitonBT = "1";
        String locationIconWarningBT = "1";
        String showScoreCardBT = "1";
        String newNotificaitonsBoxBT = "1";

        loginFPage.logInWithOutOpenMenu(login, pass);
        dashboardFPage.openDashHeadMenu();
        dashboardFPage.goToSettingPage();
        waitABit(3);
        //ACCOUNT INFO
        accountSettingsFPage.enterFirstName(firstName);
        accountSettingsFPage.enterLastName(LastName);
//        accountSettingsFPage.enterPhone(phone);
        accountSettingsFPage.clickSaveAccountInfo();

        waitABit(3);

        List<ArrayList> tempDataList = utilsForDB.getUsersAndDriversRulesData(login);
        Map<String, Object> userDataAfterTestMap = listArrayToMap(tempDataList);

        checkAC("First name failed", firstName.equals(userDataAfterTestMap.get("name")), true);
        checkAC("Last name failed", LastName.equals(userDataAfterTestMap.get("last")), true);
//        checkAC("Phone failed", phone.equals(userDataAfterTestMap.get("phone")), true);

        accountSettingsFPage.setSoundNotification(soundNotificaitonBT);
        accountSettingsFPage.setCoordinatesIcon(locationIconWarningBT);
        accountSettingsFPage.setScoreCard(showScoreCardBT);
        accountSettingsFPage.setNotificationBox(newNotificaitonsBoxBT);

        waitABit(5);
        String soundNotificaiton = getValueCookieNamed("soundNotificaiton");
        String locationIconWarning = getValueCookieNamed("locationIconWarning");
        String showScoreCard = getValueCookieNamed("showScoreCard");
        String newNotificaitonsBox = getValueCookieNamed("newNotificaitonsBox");
        checkAC("Sound notification failed", soundNotificaitonBT.equals(soundNotificaiton), false);
        checkAC("Location icon failed", locationIconWarningBT.equals(locationIconWarning), false);
        checkAC("Show scorecard failed", showScoreCardBT.equals(showScoreCard), false);
        checkAC("notification box failed", newNotificaitonsBoxBT.equals(newNotificaitonsBox), false);

    }
    @Test
    public void updateFleetSettings() throws SQLException, IOException, ClassNotFoundException {

        utilsForDB.set_0_AobrdMPHCarrierSettings(carrierId);

        List<ArrayList> tempDataListBeforeTest = utilsForDB.getCarrierRulesData(carrierId);
        Map<String, Object> fleetDataBeforeTestMap = listArrayToMap(tempDataListBeforeTest);

        String fleetName = genRandomDataByRegex("[A-Z]{1}[a-z]{11}");
        String cycle = fleetDataBeforeTestMap.get("cycleId").toString();
        String timeZone = fleetDataBeforeTestMap.get("timeZoneId").toString();
        String state = fleetDataBeforeTestMap.get("state").toString();
        String agricultureDeliveries = fleetDataBeforeTestMap.get("agricultureDeliveries").toString();

        String cycleTypeRandomValue = Integer.toString(genRandomNumberBetweenTwoValues(0 , 8));
        String timeZoneTypeRandomValue = Integer.toString(genRandomNumberBetweenTwoValues(0 , 8));
        String fleetUsdot = RandomStringUtils.randomNumeric(9);
        String fleetEIN = RandomStringUtils.randomNumeric(9);
        String randomState = Integer.toString(genRandomNumberBetweenTwoValues(0 , 63));
        String city = genRandomDataByRegex("[A-Z]{1}[a-z]{11}");
        String street = genRandomDataByRegex("[A-Z]{1}[a-z]{11}");
        String zip = RandomStringUtils.randomNumeric(5);
        String size = RandomStringUtils.randomNumeric(5);

        loginFPage.logInWithOutOpenMenu(login, pass);
        dashboardFPage.openDashHeadMenu();
        dashboardFPage.goToSettingPage();
        waitABit(3);
        accountSettingsFPage.clickOnFleetSettings();
        accountSettingsFPage.enterUsdot(fleetUsdot);
        accountSettingsFPage.enterFleetName(fleetName);
        accountSettingsFPage.enterFleetEIN(fleetEIN);
        accountSettingsFPage.setFleetState(state, randomState);
        accountSettingsFPage.enterFleetCity(city);
        accountSettingsFPage.enterFleetStreet(street);
        accountSettingsFPage.enterFleetZip(zip);
        accountSettingsFPage.enterFleetSize(size);
        accountSettingsFPage.setFleetCycle(cycle, cycleTypeRandomValue);
        accountSettingsFPage.setFleetTimeZone(timeZone, timeZoneTypeRandomValue);
        accountSettingsFPage.moveSliderAobrd(3);
        accountSettingsFPage.checkAgricultureDelivery(agricultureDeliveries);
        accountSettingsFPage.clickOnSaveFleetInfoButton();
        waitABit(3);

        List<ArrayList> tempDataListAfterTest = utilsForDB.getCarrierRulesData(carrierId);
        Map<String, Object> fleetDataAfterTestMap = listArrayToMap(tempDataListAfterTest);

        checkAC("Usdot failed", fleetDataAfterTestMap.equals(fleetDataAfterTestMap.get("usdot").equals(fleetUsdot)), true);
        checkAC("Name failed", fleetDataAfterTestMap.equals(fleetDataAfterTestMap.get("name")), true);
        checkAC("Cycle failed", fleetDataAfterTestMap.get("cycleId").equals(cycle), true);
        checkAC("Time zone failed", fleetDataAfterTestMap.get("timeZoneId").equals(timeZone), true);
        checkAC("EIN failed", fleetDataAfterTestMap.get("ein").equals(fleetEIN), true);
        checkAC("State failed", fleetDataAfterTestMap.get("restBreakId").equals(randomState), true);
        checkAC("City failed", fleetDataAfterTestMap.get("city").equals(city), true);
        checkAC("Street failed", fleetDataAfterTestMap.get("address").equals(street), true);
        checkAC("Zip failed", fleetDataAfterTestMap.get("zip").equals(zip), true);
        checkAC("Size failed", fleetDataAfterTestMap.get("size").equals(size), true);
        checkAC("AOBRD failed", fleetDataAfterTestMap.get("aobrdMPH").equals(""), false);


    }
}
