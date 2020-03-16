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

public class SoloDriverSettingsTest extends ParentTest {

    Map dataForValidLogIn = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "driverLogin");

    String login = dataForValidLogIn.get("login").toString();
    String pass = dataForValidLogIn.get("pass").toString();
    String userId = utilsForDB.getUserIdByEmail(login);
    String scannerType = "1";

    public SoloDriverSettingsTest() throws IOException, SQLException, ClassNotFoundException {
    }

    @Test
    public void updateDataSettings() throws SQLException, IOException, ClassNotFoundException {

        List<ArrayList> tempDataList = utilsForDB.getDataDriverSettings(userId);
        Map<String, Object> tempBeforeTestDataSettingsMap = listArrayToMap(tempDataList);

        utilsForDB.set_0_AobrdMPHDriverSettings(userId);
        loginPage.userValidLogIn(login, pass);
        dashboardPage.goToSettingPage();
        settingsPage.goToDriverSettings();

        String ssn = RandomStringUtils.randomNumeric(9);
        String ein = RandomStringUtils.randomNumeric(9);
        String state = genRandomState();
        String city = RandomStringUtils.randomAlphabetic(5);
        String address = RandomStringUtils.randomAlphabetic(10);
        String phone = RandomStringUtils.randomNumeric(10);
        String note = RandomStringUtils.randomAlphanumeric(20);
        String dlNumber = RandomStringUtils.randomNumeric(7);
        String dateBirth = getDateRandom();
        String dateHire = getDateRandom();
        String dateMedCard = getDateRandom();
        String dateTerminate = getDateRandom();
        String datePullNotice = getDateRandom();
        String dateDLExpiration = getDateRandom();

        String hideEngineStatuses = tempBeforeTestDataSettingsMap.get("hideEngineStatuses").toString();
        String yard = tempBeforeTestDataSettingsMap.get("yard").toString();
        String conveyance = tempBeforeTestDataSettingsMap.get("conv").toString();
        String sms = tempBeforeTestDataSettingsMap.get("Sms").toString();
        String hazMat= tempBeforeTestDataSettingsMap.get("HazMat").toString();
        String insurance = tempBeforeTestDataSettingsMap.get("Insurance").toString();
        String tankerEndorsment = tempBeforeTestDataSettingsMap.get("TankerEndorsment").toString();

 //    GENERAL
        waitABit(5);
        settingsPage.enterSsn(ssn);
        settingsPage.enterEin(ein);
        settingsPage.checkEngineScoreStatus(hideEngineStatuses);
        settingsPage.checkYardMode(yard);
        settingsPage.checkConveyance(conveyance);
        settingsPage.moveSliderAobrd(10);
        settingsPage.clickOnScannerType(scannerType);
//    CONTACT INFO
        settingsPage.selectState(state);
        settingsPage.enterDriverCity(city);
        settingsPage.enterDriverAddress(address);
        settingsPage.enterPhone(phone);
        settingsPage.checkSmsCheck(sms);

//  ADMINISTRATIVE
        settingsPage.enterDateMedCard(dateMedCard);
        settingsPage.enterDateBirth(dateBirth);
        settingsPage.enterDateHire(dateHire);
        settingsPage.enterDateTerminate(dateTerminate);
        settingsPage.enterDateNotice(datePullNotice);
        settingsPage.checkHazMat(hazMat);
        settingsPage.checkInsurance(insurance);
        settingsPage.checkTanker(tankerEndorsment);


//    DRIVER'S LICENSE
        settingsPage.enterNumberDl(dlNumber);
//        settingsPage.selectCountry("Canada");
        settingsPage.selectStateDl("AB");
        settingsPage.enterExpirationDl(dateDLExpiration);
        settingsPage.enterNote(note);
        settingsPage.clickOnBlankArea();

        waitABit(5);
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

        checkAC("Scanner type failed", tempDataSettingsMap.get("scanner_type").equals(scannerType), true);

        checkAC("AOBRD MPH failed", utilsForDB.checkAobrdMPHDriverSettings(userId), true);

        checkAC("City failed", tempDataSettingsMap.get("City").equals(city), true);
        checkAC("Address failed", tempDataSettingsMap.get( "Address").equals(address), true);
        checkAC("Notes failed", tempDataSettingsMap.get("notes").equals(note), true);

        checkAC("Phone failed", tempDataSettingsMap.get("Phone").toString().replaceAll("\\s+","").equals(phone), true);
        checkAC("SSN failed", tempDataSettingsMap.get("SSN").toString().replaceAll("\\D+","").equals(ssn), true);
        checkAC("EIN failed", tempDataSettingsMap.get("EIN").toString().replaceAll("\\D+","").equals(ein), true);

        checkAC("Med. Card Expiration failed", tempDataSettingsMap.get("MedCard").equals(dateMedCard), true);
        checkAC("Date of Birth failed", tempDataSettingsMap.get("DateOfBirth").equals(dateBirth), true);
        checkAC("Hire Date failed", tempDataSettingsMap.get("HireDate").equals(dateHire), true);
        checkAC("Terminate day failed", tempDataSettingsMap.get("TermitaneDate").equals(dateTerminate), true);
        checkAC("Pull Notice failed", tempDataSettingsMap.get("PullNotice").equals(datePullNotice), true);
        checkAC("Expiration driver licence failed", tempDataSettingsMap.get("DLExpiration").equals(dateDLExpiration), true);

        checkAC("Driver licence number failed", tempDataSettingsMap.get("DLNumber").equals(dlNumber), true);
        checkAC("Driver licence state failed", tempDataSettingsMap.get("DLState").equals("51"), true);

    }

}
