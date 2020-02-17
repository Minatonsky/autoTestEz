package oldSiteParameterizedTest;

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


        loginPage.userValidLogIn(login, pass);
        dashboardPage.openMenuDash();
        dashboardPage.goToSettingPage();
        settingsPage.goToDriverSettings();
        //    GENERAL
        waitABit(5);
        settingsPage.enterSsn(ssn);
        settingsPage.enterEin(ein);
        settingsPage.checkEngineScoreStatus(hideEngineStatuses);
        settingsPage.checkYardMode(yard);
        settingsPage.checkConveyance(conveyance);
        settingsPage.moveSliderAobrd(10);

//    CONTACT INFO
        settingsPage.selectState(state);
        settingsPage.enterDriverCity(city);
        settingsPage.enterDriverAddress(address);
        settingsPage.enterPhone(phone);
        settingsPage.checkSmsCheck(sms);

//  ADMINISTRATIVE
        settingsPage.enterDateMedCard(date);
        settingsPage.enterDateBirth(date);
        settingsPage.enterDateHire(date);
        settingsPage.enterDateTerminate(date);
        settingsPage.enterDateNotice(date);
        settingsPage.checkHazMat(hazMat);
        settingsPage.checkInsurance(insurance);
        settingsPage.checkTanker(tankerEndorsment);


//    DRIVER'S LICENSE
        settingsPage.enterNumberDl(dlNumber);
        settingsPage.selectStateDl(stateNumber);
        settingsPage.enterExpirationDl(date);
        settingsPage.enterNote(note);
        settingsPage.clickOnBlankArea();

        waitABit(3);
        settingsPage.clickOnSave();
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

}
