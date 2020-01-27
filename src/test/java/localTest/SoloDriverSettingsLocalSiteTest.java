package localTest;

import libs.UtilsForDB;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import parentTest.Parent3Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static libs.Utils.*;

public class SoloDriverSettingsLocalSiteTest extends Parent3Test {
    UtilsForDB utilsForDB = new UtilsForDB();
    String login = "den36@gmail.com";
    String pass = "testtest";
    String userId = "4401";
   @Test
   public void updateDataSettings() throws SQLException, IOException, ClassNotFoundException{
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

        utilsForDB.setOffCheckBoxDriverSetting(userId, "0");
        utilsForDB.set_0_AobrdMPHDriverSettings(userId);
        loginLocalSitePage.userValidLogIn(login, pass);
        dashboardLocalSitePage.goToSettingPage();
        settingsLocalSitePage.clickOnDriverSettings();

        //    GENERAL
        settingsLocalSitePage.enterSsn(ssn);
        settingsLocalSitePage.enterEin(ein);
        settingsLocalSitePage.checkOnEngineScoreStatus();
        settingsLocalSitePage.checkOnYardMode();
        settingsLocalSitePage.checkOnConveyance();
        settingsLocalSitePage.moveSliderAobrd(10);
//    CONTACT INFO
        settingsLocalSitePage.selectState(state);
        settingsLocalSitePage.enterDriverCity(city);
        settingsLocalSitePage.enterDriverAddress(address);
        settingsLocalSitePage.enterPhone(phone);
        settingsLocalSitePage.checkOnSmsCheck();

//  ADMINISTRATIVE
        settingsLocalSitePage.enterDateMedCard(dateMedCard);
        settingsLocalSitePage.enterDateBirth(dateBirth);
        settingsLocalSitePage.enterDateHire(dateHire);
        settingsLocalSitePage.enterDateTerminate(dateTerminate);
        settingsLocalSitePage.enterDateNotice(datePullNotice);
        settingsLocalSitePage.checkOnHazMat();
        settingsLocalSitePage.checkOnInsurance();
        settingsLocalSitePage.checkOnTanker();


//    DRIVER'S LICENSE
        settingsLocalSitePage.enterNumberDl(dlNumber);
        settingsLocalSitePage.selectCountry("Canada");
        settingsLocalSitePage.selectStateDl("AB");
        settingsLocalSitePage.enterExpirationDl(dateDLExpiration);
        settingsLocalSitePage.enterNote(note);
        settingsLocalSitePage.clickOnBlankArea();

        waitABit(5);
        settingsLocalSitePage.clickOnSave();
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
