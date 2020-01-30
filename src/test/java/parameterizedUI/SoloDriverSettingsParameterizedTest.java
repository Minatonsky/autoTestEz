package parameterizedUI;

import libs.SpreadsheetData;
import libs.UtilsForDB;
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
    UtilsForDB utilsForDB = new UtilsForDB();
    String scannerType = "1";

    @Parameterized.Parameters()
    public static Collection testData() throws IOException {
        InputStream spreadsheet = new FileInputStream(configProperties.DATA_FILE_PATH() + "testLogin.xls");
        return new SpreadsheetData(spreadsheet, "loginPassParamsTest").getData();
    }

    @Test
    public void updateDataSettings() throws SQLException, IOException, ClassNotFoundException {
        String userId = utilsForDB.getUserIdByEmail(login);
        utilsForDB.setOffCheckBoxDriverSetting(userId, "0");
        utilsForDB.set_0_AobrdMPHDriverSettings(userId);
        loginPage.userValidLogIn(login, "testtest");

        dashboardPage.openMenuDash();
        dashboardPage.goToSettingPage();
        settingsPage.clickOnDriverSettings();

        String ssn = RandomStringUtils.randomNumeric(9);
        String ein = RandomStringUtils.randomNumeric(9);
        String state = "2";
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


 //    GENERAL
        settingsPage.enterSsn(ssn);
        settingsPage.enterEin(ein);
        settingsPage.checkOnEngineScoreStatus();
        settingsPage.checkOnYardMode();
        settingsPage.checkOnConveyance();
        settingsPage.moveSliderAobrd(10);
        settingsPage.clickOnScannerType(scannerType);
//    CONTACT INFO
        settingsPage.selectState(state);
        settingsPage.enterDriverCity(city);
        settingsPage.enterDriverAddress(address);
        settingsPage.enterPhone(phone);
        settingsPage.checkOnSmsCheck();

//  ADMINISTRATIVE
        settingsPage.enterDateMedCard(dateMedCard);
        settingsPage.enterDateBirth(dateBirth);
        settingsPage.enterDateHire(dateHire);
        settingsPage.enterDateTerminate(dateTerminate);
        settingsPage.enterDateNotice(datePullNotice);
        settingsPage.checkOnHazMat();
        settingsPage.checkOnInsurance();
        settingsPage.checkOnTanker();


//    DRIVER'S LICENSE
        settingsPage.enterNumberDl(dlNumber);
        settingsPage.selectCountry("Canada");
        settingsPage.selectStateDl("AB");
        settingsPage.enterExpirationDl(dateDLExpiration);
        settingsPage.enterNote(note);
        settingsPage.clickOnBlankArea();

        waitABit(5);
        settingsPage.clickOnSave();
        waitABit(5);
        List<ArrayList> tempDataSettingsList = utilsForDB.getDataDriverSettings(userId);
        Map<String, Object> tempDataSettingsMap = listArrayToMap(tempDataSettingsList);

        checkAC("HazMat failed", tempDataSettingsMap.get("HazMat").equals("1"), true);
        checkAC("Insurance failed", tempDataSettingsMap.get("Insurance").equals("1"), true);
        checkAC("Tanker Endorsement failed", tempDataSettingsMap.get( "TankerEndorsment").equals("1"), true);
        checkAC("Yard Mode failed", tempDataSettingsMap.get("yard").equals("1"), true);
        checkAC("Conveyance Mode failed", tempDataSettingsMap.get("conv").equals("1"), true);
        checkAC("Hide Engine and Scanner statuses failed", tempDataSettingsMap.get("hideEngineStatuses").equals("1"), true);
        checkAC("Sms failed", tempDataSettingsMap.get("Sms").equals("1"), true);

        checkAC("AOBRD MPH failed", utilsForDB.checkAobrdMPHDriverSettings(userId), true);
        checkAC("Scanner type failed", tempDataSettingsMap.get("scanner_type").equals(scannerType), true);

        checkACWithLogger("City failed", tempDataSettingsMap.get("City").equals(city), true, tempDataSettingsMap.get("City").toString(), city);
        checkACWithLogger("Address failed", tempDataSettingsMap.get( "Address").equals(address), true, tempDataSettingsMap.get( "Address").toString(), address);
        checkACWithLogger("Notes failed", tempDataSettingsMap.get("notes").equals(note), true, tempDataSettingsMap.get("notes").toString(), note);

        checkACWithLogger("Phone failed", tempDataSettingsMap.get("Phone").toString().replaceAll("\\s+","").equals(phone), true, tempDataSettingsMap.get("Phone").toString().replaceAll("\\s+",""), phone);
        checkACWithLogger("SSN failed", tempDataSettingsMap.get("SSN").toString().replaceAll("\\D+","").equals(ssn), true, tempDataSettingsMap.get("SSN").toString().replaceAll("\\D+",""), ssn);
        checkACWithLogger("EIN failed", tempDataSettingsMap.get("EIN").toString().replaceAll("\\D+","").equals(ein), true, tempDataSettingsMap.get("EIN").toString().replaceAll("\\D+",""), ein);

        checkACWithLogger("Med. Card Expiration failed", tempDataSettingsMap.get("MedCard").equals(dateMedCard), true, tempDataSettingsMap.get("MedCard").toString(), dateMedCard);
        checkACWithLogger("Date of Birth failed", tempDataSettingsMap.get("DateOfBirth").equals(dateBirth), true, tempDataSettingsMap.get("DateOfBirth").toString(), dateBirth);
        checkACWithLogger("Hire Date failed", tempDataSettingsMap.get("HireDate").equals(dateHire), true, tempDataSettingsMap.get("HireDate").toString(), dateHire);
        checkACWithLogger("Terminate day failed", tempDataSettingsMap.get("TermitaneDate").equals(dateTerminate), true, tempDataSettingsMap.get("TermitaneDate").toString(), dateTerminate);
        checkACWithLogger("Pull Notice failed", tempDataSettingsMap.get("PullNotice").equals(datePullNotice), true, tempDataSettingsMap.get("PullNotice").toString(), datePullNotice);
        checkACWithLogger("Expiration driver licence failed", tempDataSettingsMap.get("DLExpiration").equals(dateDLExpiration), true, tempDataSettingsMap.get("DLExpiration").toString(), dateDLExpiration);

        checkACWithLogger("Driver licence number failed", tempDataSettingsMap.get("DLNumber").equals(dlNumber), true, tempDataSettingsMap.get("DLNumber").toString(), dlNumber);
        checkACWithLogger("Driver licence state failed", tempDataSettingsMap.get("DLState").equals("51"), true, tempDataSettingsMap.get("DLState").toString(), "51");

    }

}
