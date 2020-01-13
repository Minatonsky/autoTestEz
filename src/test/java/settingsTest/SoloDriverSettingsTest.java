package settingsTest;

import libs.ExcelDriver;
import libs.UtilsForDB;
import org.junit.Test;
import parentTest.ParentTest;

import java.io.IOException;
import java.util.Map;

import static libs.Utils.waitABit;

public class SoloDriverSettingsTest extends ParentTest {
    UtilsForDB utilsForDB = new UtilsForDB();
    ExcelDriver excelDriver = new ExcelDriver();

    Map dataForFleet = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "driverLogin");
    Map personalDataForEldOrder = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testSettings.xls", "personalData");

    public SoloDriverSettingsTest() throws IOException {
    }

    @Test
    public void setNewDataSettings(){
        loginPage.userValidLogIn(dataForFleet.get("login").toString(), dataForFleet.get("pass").toString());
        dashboardPage.openMenuDash();
        dashboardPage.goToSettingPage();
        settingsPage.clickOnDriverSettings();
        waitABit(3);
        settingsPage.clickOnSmsCheck();
        waitABit(3);

    }


}
