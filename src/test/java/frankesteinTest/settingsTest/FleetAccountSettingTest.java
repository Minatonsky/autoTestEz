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

        String firstName = faker.name().firstName();
        String LastName = faker.name().lastName();
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
        accountSettingsFPage.clickSaveAccountInfo();

        waitABit(3);

        List<ArrayList> tempDataList = utilsForDB.getUsersAndDriversRulesData(login);
        Map<String, Object> userDataAfterTestMap = listArrayToMap(tempDataList);

        checkAC("First name failed", firstName.equals(userDataAfterTestMap.get("name")), true);
        checkAC("Last name failed", LastName.equals(userDataAfterTestMap.get("last")), true);

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

        String state = fleetDataBeforeTestMap.get("state").toString();
        String agricultureDeliveries = fleetDataBeforeTestMap.get("agricultureDeliveries").toString();

        String cycleTypeRandomValue = Integer.toString(genRandomNumberBetweenTwoValues(0 , 8));
        String timeZoneTypeRandomValue = Integer.toString(genRandomNumberBetweenTwoValues(0 , 8));
        String fleetUsdot = RandomStringUtils.randomNumeric(9);
        String fleetEIN = genRandomDataByRegex("[0-9]{2}[-]{1}[0-9]{7}");
        String randomState = Integer.toString(genRandomNumberBetweenTwoValues(0 , 63));
        String city = faker.address().city();
        String street = faker.address().streetAddress();
        String zip = RandomStringUtils.randomNumeric(5);
        String size = RandomStringUtils.randomNumeric(1);
        String fleetName = faker.beer().name();

        loginFPage.logInWithOutOpenMenu(login, pass);
        dashboardFPage.openDashHeadMenu();
        dashboardFPage.goToSettingPage();
        waitABit(3);
        accountSettingsFPage.clickOnFleetSettings();
        waitABit(3);
        accountSettingsFPage.enterUsdot(fleetUsdot);
        accountSettingsFPage.enterFleetName(fleetName);
//        accountSettingsFPage.enterFleetEIN(fleetEIN);
        accountSettingsFPage.setFleetState(randomState);
        accountSettingsFPage.enterFleetCity(city);
        accountSettingsFPage.enterFleetStreet(street);
        accountSettingsFPage.enterFleetZip(zip);
        accountSettingsFPage.enterFleetSize(size);
        accountSettingsFPage.setFleetCycle(cycleTypeRandomValue);
        accountSettingsFPage.setFleetTimeZone(timeZoneTypeRandomValue);
        accountSettingsFPage.moveSliderAobrd(3);
        accountSettingsFPage.checkAgricultureDelivery(agricultureDeliveries);
        accountSettingsFPage.clickOnSaveFleetInfoButton();
        waitABit(20);

        List<ArrayList> tempDataListAfterTest = utilsForDB.getCarrierRulesData(carrierId);
        Map<String, Object> fleetDataAfterTestMap = listArrayToMap(tempDataListAfterTest);

        System.out.println(fleetDataAfterTestMap.get("state"));
        System.out.println(randomState);

        checkAC("Usdot failed", fleetDataAfterTestMap.get("usdot").equals(fleetUsdot), true);
        checkAC("Name failed", fleetDataAfterTestMap.get("name").equals(fleetName), true);
        checkAC("Cycle failed", fleetDataAfterTestMap.get("cycleId").equals(cycleTypeRandomValue), true);
        checkAC("Time zone failed", fleetDataAfterTestMap.get("timeZoneId").equals(timeZoneTypeRandomValue), true);
//        checkAC("EIN failed", fleetDataAfterTestMap.get("ein").equals(fleetEIN), true);
        checkAC("State failed", fleetDataAfterTestMap.get("state").equals(randomState), true);
        checkAC("City failed", fleetDataAfterTestMap.get("city").equals(city), true);
        checkAC("Street failed", fleetDataAfterTestMap.get("address").equals(street), true);
        checkAC("Zip failed", fleetDataAfterTestMap.get("zip").equals(zip), true);
        checkAC("Size failed", fleetDataAfterTestMap.get("size").equals(size), true);
        checkAC("AOBRD failed", fleetDataAfterTestMap.get("aobrdMPH").equals(""), false);


    }
}
