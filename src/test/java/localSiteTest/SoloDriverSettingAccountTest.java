package localSiteTest;

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
        String firstName = RandomStringUtils.randomAlphabetic(5, 10);
        String LastName = RandomStringUtils.randomAlphabetic(5, 10);
        String phone = RandomStringUtils.randomNumeric(10);
        String cycle = userDataBeforeTestMap.get("cycleId").toString();
        String timeZone = userDataBeforeTestMap.get("timeZoneId").toString();
        String restBreak = userDataBeforeTestMap.get("restBreakId").toString();
        String appLog = userDataBeforeTestMap.get("logIncrementId").toString();
        String cargoType = userDataBeforeTestMap.get("cargoTypeId").toString();

        String cargoTypeRandomValue = Integer.toString(genRandomNumberBetweenTwoValues(0 , 4));
        String cycleTypeRandomValue = Integer.toString(genRandomNumberBetweenTwoValues(0 , 8));
        String timeZoneTypeRandomValue = Integer.toString(genRandomNumberBetweenTwoValues(0 , 8));


        loginLocalSitePage.userValidLogIn(login, pass);
        dashboardLocalSitePage.goToSettingPage();

        waitABit(5);
//        String soundNotificaitonBT = getValueCookieNamed("soundNotificaiton");
//        String locationIconWarningBT = getValueCookieNamed("locationIconWarning");
//        String showScoreCardBT = getValueCookieNamed("showScoreCard");
//        String newNotificaitonsBoxBT = getValueCookieNamed("newNotificaitonsBox");

//ACCOUNT INFO
        accountSettingsLocalSitePage.enterFirstName(firstName);
        accountSettingsLocalSitePage.enterLastName(LastName);
        accountSettingsLocalSitePage.enterPhone(phone);
        accountSettingsLocalSitePage.clickSaveAccountInfo();

        List<ArrayList> tempDataList = utilsForDB.getUsersAndDriversRulesData(login);
        Map<String, Object> userDataAfterTestMap = listArrayToMap(tempDataList);

        checkAC("First name failed", firstName.equals(userDataAfterTestMap.get("name")), true);
        checkAC("Last name failed", LastName.equals(userDataAfterTestMap.get("last")), true);
        checkAC("Phone failed", phone.equals(userDataAfterTestMap.get("phone")), true);

 // DRIVER INFO
        accountSettingsLocalSitePage.setCycle(cycle, cycleTypeRandomValue);
        accountSettingsLocalSitePage.setTimeZone(timeZone, timeZoneTypeRandomValue);
        accountSettingsLocalSitePage.setOdometer(odometer);
        accountSettingsLocalSitePage.setRestBreak(restBreak);
        accountSettingsLocalSitePage.setAppLog(appLog);
        accountSettingsLocalSitePage.setCargoType(cargoType, cargoTypeRandomValue);
        accountSettingsLocalSitePage.clickSaveDriverInfo();
        checkAC("Cycle failed", userDataAfterTestMap.get("cycleId").equals(cycle), false);
        checkAC("Time zone failed", userDataAfterTestMap.get("timeZoneId").equals(timeZone), false);
        checkAC("Odometer failed", userDataAfterTestMap.get("odometerId").equals(odometer), false);
        checkAC("Rest break failed", userDataAfterTestMap.get("restBreakId").equals(restBreak), false);
        checkAC("App log failed", userDataAfterTestMap.get("logIncrementId").equals(appLog), false);
        checkAC("Cargo type failed", userDataAfterTestMap.get("cargoTypeId").equals(cargoType), false);



//        accountSettingsLocalSitePage.setSoundNotification(soundNotificaitonBT);
//        accountSettingsLocalSitePage.setCoordinatesIcon(locationIconWarningBT);
//        accountSettingsLocalSitePage.setScoreCard(showScoreCardBT);
//        accountSettingsLocalSitePage.setNotificationBox(newNotificaitonsBoxBT);

        waitABit(5);
        String soundNotificaiton = getValueCookieNamed("soundNotificaiton");
        String locationIconWarning = getValueCookieNamed("locationIconWarning");
        String showScoreCard = getValueCookieNamed("showScoreCard");
        String newNotificaitonsBox = getValueCookieNamed("newNotificaitonsBox");
//        checkAC("Sound notification failed", soundNotificaitonBT.equals(soundNotificaiton), false);
//        checkAC("Location icon failed", locationIconWarningBT.equals(locationIconWarning), false);
//        checkAC("Show scorecard failed", showScoreCardBT.equals(showScoreCard), false);
//        checkAC("notification box failed", newNotificaitonsBoxBT.equals(newNotificaitonsBox), false);



    }
}
