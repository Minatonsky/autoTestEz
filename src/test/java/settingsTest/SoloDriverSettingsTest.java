package settingsTest;

import libs.ExcelDriver;
import libs.UtilsForDB;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import parentTest.ParentTest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import static libs.Utils.getDateFormat;
import static libs.Utils.waitABit;

public class SoloDriverSettingsTest extends ParentTest {
    UtilsForDB utilsForDB = new UtilsForDB();
    ExcelDriver excelDriver = new ExcelDriver();


    Map dataForDriver = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "driverLogin");

    public SoloDriverSettingsTest() throws IOException {
    }

    @Test
    public void setNewDataSettings() throws SQLException, IOException, ClassNotFoundException {
        utilsForDB.setOffCheckBoxDriverSetting(dataForDriver.get("userId").toString());
        utilsForDB.set_0_AobrdMPHDriverSettings(dataForDriver.get("userId").toString());
        loginPage.userValidLogIn(dataForDriver.get("login").toString(), dataForDriver.get("pass").toString());
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

    }


}
