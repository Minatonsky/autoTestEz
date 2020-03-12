package oldSiteTest.settingsTest;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import parentTest.ParentTest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static libs.Utils.*;

public class SoloDriverSettingAccountTest extends ParentTest {

    Map dataForValidLogIn = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "driverLogin");

    String login = dataForValidLogIn.get("login").toString();
    String pass = dataForValidLogIn.get("pass").toString();

    public SoloDriverSettingAccountTest() throws IOException {
    }

    @Test
    public void upDateAccountSettings() throws SQLException, IOException, ClassNotFoundException {
        List<ArrayList> tempDataListBeforeTest = utilsForDB.getUsersAndDriversRulesData(login);
        Map<String, Object> userDataBeforeTestMap = listArrayToMap(tempDataListBeforeTest);

        String odometer = userDataBeforeTestMap.get("odometerId").toString();
        String firstName = genRandomDataByRegex("[A-Z]{1}[a-z]{11}");
        String LastName = genRandomDataByRegex("[A-Z]{1}[a-z]{11}");
        String phone = RandomStringUtils.randomNumeric(10);
        String cycle = userDataBeforeTestMap.get("cycleId").toString();
        String timeZone = userDataBeforeTestMap.get("timeZoneId").toString();
        String restBreak = userDataBeforeTestMap.get("restBreakId").toString();
        String appLog = userDataBeforeTestMap.get("logIncrementId").toString();
        String cargoType = userDataBeforeTestMap.get("cargoTypeId").toString();

        String cargoTypeRandomValue = Integer.toString(genRandomNumberBetweenTwoValues(0 , 4));
        String cycleTypeRandomValue = Integer.toString(genRandomNumberBetweenTwoValues(0 , 8));
        String timeZoneTypeRandomValue = Integer.toString(genRandomNumberBetweenTwoValues(0 , 8));

        loginPage.userValidLogIn(login, pass);
        dashboardPage.goToSettingPage();

        waitABit(5);
        String soundNotificaitonBT = "1";
        String locationIconWarningBT = "1";
        String showScoreCardBT = "1";
        String newNotificaitonsBoxBT = "1";

//ACCOUNT INFO
        accountSettingsPage.enterFirstName(firstName);
        accountSettingsPage.enterLastName(LastName);
//        accountSettingsPage.enterPhone(phone);
        accountSettingsPage.clickSaveAccountInfo();

 // DRIVER INFO
        accountSettingsPage.setCycle(cycle, cycleTypeRandomValue);
        accountSettingsPage.setTimeZone(timeZone, timeZoneTypeRandomValue);
        accountSettingsPage.setOdometer(odometer);
        accountSettingsPage.setRestBreak(restBreak);
        accountSettingsPage.setAppLog(appLog);
        accountSettingsPage.setCargoType(cargoType, cargoTypeRandomValue);
        accountSettingsPage.clickSaveDriverInfo();

        List<ArrayList> tempDataList = utilsForDB.getUsersAndDriversRulesData(login);
        Map<String, Object> userDataAfterTestMap = listArrayToMap(tempDataList);

        checkAC("First name failed", firstName.equals(userDataAfterTestMap.get("name")), true);
        checkAC("Last name failed", LastName.equals(userDataAfterTestMap.get("last")), true);
//        checkAC("Phone failed", phone.equals(userDataAfterTestMap.get("phone")), true);
        checkAC("Cycle failed", userDataAfterTestMap.get("cycleId").equals(cycle), false);
        checkAC("Time zone failed", userDataAfterTestMap.get("timeZoneId").equals(timeZone), false);
        checkAC("Odometer failed", userDataAfterTestMap.get("odometerId").equals(odometer), false);
        checkAC("Rest break failed", userDataAfterTestMap.get("restBreakId").equals(restBreak), false);
        checkAC("App log failed", userDataAfterTestMap.get("logIncrementId").equals(appLog), false);
        checkAC("Cargo type failed", userDataAfterTestMap.get("cargoTypeId").equals(cargoType), false);

        accountSettingsPage.setSoundNotification(soundNotificaitonBT);
        accountSettingsPage.setCoordinatesIcon(locationIconWarningBT);
        accountSettingsPage.setScoreCard(showScoreCardBT);
        accountSettingsPage.setNotificationBox(newNotificaitonsBoxBT);

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
}
