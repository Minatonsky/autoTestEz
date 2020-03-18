package frankesteinParamsTest;

import libs.SpreadsheetData;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import parentTest.ParentTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static libs.Utils.*;

@RunWith(Parameterized.class)
public class SoloDriverSettingsParameterizedTest extends ParentTest {

    String login;

    public SoloDriverSettingsParameterizedTest(String login) throws IOException {
        this.login = login;
    }

    Map dataForDLRegexState = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testSettings.xls", "USDLRegex");
    Map dataForDLNumberState = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testSettings.xls", "USDLNumber");

    String scannerType = "1";
    String pass = "testtest";

    @Parameterized.Parameters(name = "Parameters are: {0}")
    public static Collection testData() throws IOException {
        InputStream spreadsheet = new FileInputStream(configProperties.DATA_FILE_PATH() + "testLogin.xls");
        return new SpreadsheetData(spreadsheet, "loginPassParamsTest").getData();
    }

    @Test
    public void updateDataSettings() throws SQLException, IOException, ClassNotFoundException {

        String userId = utilsForDB.getUserIdByEmail(login);
        utilsForDB.set_0_AobrdMPHDriverSettings(userId);

        List<ArrayList> tempDataList = utilsForDB.getDataDriverSettings(userId);
        Map<String, Object> tempBeforeTestDataSettingsMap = listArrayToMap(tempDataList);

        String ssn = "222222222";
        String ein = "333333333";
        String state = Integer.toString(genRandomNumberBetweenTwoValues(1, 63));
        String city = RandomStringUtils.randomAlphabetic(5);
        String address = RandomStringUtils.randomAlphabetic(10);
        String phone = RandomStringUtils.randomNumeric(10);
        String note = RandomStringUtils.randomAlphanumeric(20);
        String date = "11111111";

        String hideEngineStatuses = tempBeforeTestDataSettingsMap.get("hideEngineStatuses").toString();
        String yard = tempBeforeTestDataSettingsMap.get("yard").toString();
        String conveyance = tempBeforeTestDataSettingsMap.get("conv").toString();
        String sms = tempBeforeTestDataSettingsMap.get("Sms").toString();
        String hazMat= tempBeforeTestDataSettingsMap.get("HazMat").toString();
        String insurance = tempBeforeTestDataSettingsMap.get("Insurance").toString();
        String tankerEndorsment = tempBeforeTestDataSettingsMap.get("TankerEndorsment").toString();

        String randomState = genRandomState();
        String regexState = dataForDLRegexState.get(randomState).toString();
        String dlNumber = genRandomDataByRegex(regexState);
        String stateNumber = dataForDLNumberState.get(randomState).toString();


        loginFPage.logInAndOpenMenu(login, pass);
        dashboardFPage.goToSettingPage();
        settingsFPage.goToDriverSettings();
        //    GENERAL
        waitABit(5);
        settingsFPage.enterSsn(ssn);
        settingsFPage.enterEin(ein);
        settingsFPage.checkEngineScoreStatus(hideEngineStatuses);
        settingsFPage.checkYardMode(yard);
        settingsFPage.checkConveyance(conveyance);
        settingsFPage.moveSliderAobrd(10);

//    CONTACT INFO
        settingsFPage.selectState(state);
        settingsFPage.enterDriverCity(city);
        settingsFPage.enterDriverAddress(address);
        settingsFPage.enterPhone(phone);
        settingsFPage.checkSmsCheck(sms);

//  ADMINISTRATIVE
        settingsFPage.enterDateMedCard(date);
        settingsFPage.enterDateBirth(date);
        settingsFPage.enterDateHire(date);
        settingsFPage.enterDateTerminate(date);
        settingsFPage.enterDateNotice(date);
        settingsFPage.checkHazMat(hazMat);
        settingsFPage.checkInsurance(insurance);
        settingsFPage.checkTanker(tankerEndorsment);


//    DRIVER'S LICENSE
        settingsFPage.enterNumberDl(dlNumber);
        settingsFPage.selectStateDl(stateNumber);
        settingsFPage.enterExpirationDl(date);
        settingsFPage.enterNote(note);
        settingsFPage.clickOnBlankArea();

        waitABit(3);
        settingsFPage.clickOnSave();
        waitABit(5);
        List<ArrayList> tempDataSettingsList = utilsForDB.getDataDriverSettings(userId);
        Map<String, Object> tempDataSettingsMap = listArrayToMap(tempDataSettingsList);

        checkAC("HazMat failed", tempDataSettingsMap.get("HazMat").equals(hazMat), false);
        checkAC("Insurance failed", tempDataSettingsMap.get("Insurance").equals(insurance), false);
        checkAC("Tanker Endorsement failed", tempDataSettingsMap.get( "TankerEndorsment").equals(tankerEndorsment), false);
        checkAC("Yard Mode failed", tempDataSettingsMap.get("yard").equals(yard), false);
        checkAC("Conveyance Mode failed", tempDataSettingsMap.get("conv").equals(conveyance), false);
        checkAC("Hide Engine and Scanner statuses failed", tempDataSettingsMap.get("hideEngineStatuses").equals(hideEngineStatuses), false);
        checkAC("Sms failed", tempDataSettingsMap.get("Sms").equals(sms), false);

        checkAC("AOBRD MPH failed", utilsForDB.checkAobrdMPHDriverSettings(userId), true);

        checkAC("City failed", tempDataSettingsMap.get("City").equals(city), true);
        checkAC("Address failed", tempDataSettingsMap.get( "Address").equals(address), true);
        checkAC("Notes failed", tempDataSettingsMap.get("notes").equals(note), true);

//        checkAC("Phone failed", tempDataSettingsMap.get("Phone").toString().replaceAll("\\s+","").equals(phone), true);
        checkAC("SSN failed", tempDataSettingsMap.get("SSN").toString().replaceAll("\\D+","").equals(ssn), true);
        checkAC("EIN failed", tempDataSettingsMap.get("EIN").toString().replaceAll("\\D+","").equals(ein), true);

        checkAC("Med. Card Expiration failed", tempDataSettingsMap.get("MedCard").toString().replaceAll("\\D+","").equals(date), true);
        checkAC("Date of Birth failed", tempDataSettingsMap.get("DateOfBirth").toString().replaceAll("\\D+","").equals(date), true);
        checkAC("Hire Date failed", tempDataSettingsMap.get("HireDate").toString().replaceAll("\\D+","").equals(date), true);
        checkAC("Terminate day failed", tempDataSettingsMap.get("TermitaneDate").toString().replaceAll("\\D+","").equals(date), true);
        checkAC("Pull Notice failed", tempDataSettingsMap.get("PullNotice").toString().replaceAll("\\D+","").equals(date), true);
        checkAC("Expiration driver licence failed", tempDataSettingsMap.get("DLExpiration").toString().replaceAll("\\D+","").equals(date), true);

        checkAC("Driver licence number failed", tempDataSettingsMap.get("DLNumber").equals(dlNumber), true);
        checkAC("Driver licence state failed", tempDataSettingsMap.get("DLState").equals(stateNumber), true);
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

        loginFPage.logInAndOpenMenu(login, pass);
        dashboardFPage.goToSettingPage();

        waitABit(5);
        String soundNotificaitonBT = "1";
        String locationIconWarningBT = "1";
        String showScoreCardBT = "1";
        String newNotificaitonsBoxBT = "1";

//ACCOUNT INFO
        accountSettingsFPage.enterFirstName(firstName);
        accountSettingsFPage.enterLastName(LastName);
//        accountSettingsPage.enterPhone(phone);
        accountSettingsFPage.clickSaveAccountInfo();

        // DRIVER INFO
        accountSettingsFPage.setCycle(cycle, cycleTypeRandomValue);
        accountSettingsFPage.setTimeZone(timeZone, timeZoneTypeRandomValue);
        accountSettingsFPage.setOdometer(odometer);
        accountSettingsFPage.setRestBreak(restBreak);
        accountSettingsFPage.setAppLog(appLog);
        accountSettingsFPage.setCargoType(cargoType, cargoTypeRandomValue);
        accountSettingsFPage.clickSaveDriverInfo();

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

}
