package settingsTest;

import libs.UtilsForDB;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import parentTest.ParentTest;

import java.io.IOException;
import java.sql.SQLException;

import static libs.Utils.getDateFormat;
import static libs.Utils.waitABit;

public class SoloDriverSettingsTest extends ParentTest {
    UtilsForDB utilsForDB = new UtilsForDB();
    String login = "den36@gmail.com";
    String pass = "testtest";
    String userId = "4401";

    @Test
    public void setNewDataSettings() throws SQLException, IOException, ClassNotFoundException {
        utilsForDB.setOffCheckBoxDriverSetting(userId, "0");
        utilsForDB.set_0_AobrdMPHDriverSettings(userId);
        loginPage.userValidLogIn(login, pass);
        dashboardPage.openMenuDash();
        dashboardPage.goToSettingPage();
        settingsPage.clickOnDriverSettings();

        String ssn = RandomStringUtils.randomNumeric(9);
        String ein = RandomStringUtils.randomNumeric(9);
        String state = "2";
        String city = RandomStringUtils.randomAlphabetic(5);
        String address = RandomStringUtils.randomAlphabetic(10);
        String phone = RandomStringUtils.randomNumeric(10);
        String date = getDateFormat();
        String note = RandomStringUtils.randomAlphabetic(20);
        String dlNumber = RandomStringUtils.randomNumeric(7);

 //    GENERAL
        settingsPage.enterSsn(ssn);
        settingsPage.enterEin(ein);
        settingsPage.checkOnEngineScoreStatus();
        settingsPage.checkOnYardMode();
        settingsPage.checkOnConveyance();
        settingsPage.moveSliderAobrd(10);
//    CONTACT INFO
        settingsPage.selectState(state);
        settingsPage.enterDriverCity(city);
        settingsPage.enterDriverAddress(address);
        settingsPage.enterPhone(phone);
        settingsPage.checkOnSmsCheck();

//  ADMINISTRATIVE
        settingsPage.enterDateMedCard(date);
        settingsPage.enterDateBirth(date);
        settingsPage.enterDateHire(date);
        settingsPage.enterDateTerminate(date);
        settingsPage.enterDateNotice(date);
        settingsPage.checkOnHazMat();
        settingsPage.checkOnInsurance();
        settingsPage.checkOnTanker();


//    DRIVER'S LICENSE
        settingsPage.enterNumberDl(dlNumber);
        settingsPage.selectCountry("Canada");
        settingsPage.selectStateDl("AB");
        settingsPage.enterExpirationDl(date);
        settingsPage.enterNote(note);
        settingsPage.clickOnBlankArea();

        waitABit(5);
        settingsPage.clickOnSave();
        waitABit(5);

        checkAC("HazMat failed", utilsForDB.getValueFromSettings(userId, "HazMat").equals("1"), true);
        checkAC("Insurance failed", utilsForDB.getValueFromSettings(userId, "Insurance").equals("1"), true);
        checkAC("Tanker Endorsement failed", utilsForDB.getValueFromSettings(userId, "TankerEndorsment").equals("1"), true);
        checkAC("Yard Mode failed", utilsForDB.getValueFromSettings(userId, "yard").equals("1"), true);
        checkAC("Conveyance Mode failed", utilsForDB.getValueFromSettings(userId, "conv").equals("1"), true);
//        checkAC("Hide Engine and Scanner statuses failed", utilsForDB.getValueFromSettings(userId, "hideEngineStatuses").equals("1"), true);
        checkAC("Sms failed", utilsForDB.getValueFromSettings(userId, "Sms").equals("1"), true);

        checkAC("AOBRD MPH failed", utilsForDB.checkAobrdMPHDriverSettings(userId), true);

        checkAC("City failed", utilsForDB.getValueFromSettings(userId, "City").equals(city), true);
        checkAC("Address failed", utilsForDB.getValueFromSettings(userId, "Address").equals(address), true);
        checkAC("Notes failed", utilsForDB.getValueFromSettings(userId, "notes").equals(note), true);

        checkAC("Phone failed", utilsForDB.getValueFromSettings(userId, "Phone").replaceAll("\\s+","").equals(phone), true);
        checkAC("SSN failed", utilsForDB.getValueFromSettings(userId, "SSN").replaceAll("\\D+","").equals(ssn), true);
        System.out.println(utilsForDB.getValueFromSettings(userId, "SSN").replaceAll("\\D+",""));
        checkAC("EIN failed", utilsForDB.getValueFromSettings(userId, "EIN").replaceAll("\\D+","").equals(ein), true);

    }


}
