package settingsTest;

import org.junit.Test;
import parentTest.ParentTest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static libs.Utils.*;

public class DriverLicenseParameterizedTest extends ParentTest {

    Map dataForValidLogIn = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testSettings.xls", "USDLRegex");

    String login = dataForValidLogIn.get("login").toString();
    String pass = dataForValidLogIn.get("pass").toString();
    String userId = utilsForDB.getUserIdByEmail(login);

    public DriverLicenseParameterizedTest() throws IOException, SQLException, ClassNotFoundException {
    }

    @Test
    public void validDriverLicenseUSA() throws SQLException, IOException, ClassNotFoundException {

        String dateDLExpiration = getDateRandom();

        loginPage.userValidLogIn(login, pass);
        dashboardPage.openMenuDash();
        dashboardPage.goToSettingPage();
        settingsPage.goToDriverSettings();

        String dlAL = dataForValidLogIn.get("AL").toString();
        settingsPage.enterNumberDl(dlAL);
        settingsPage.selectCountry("USA");
        settingsPage.selectStateDl(dlAL);
        settingsPage.enterExpirationDl(dateDLExpiration);
        settingsPage.clickOnBlankArea();

        waitABit(5);
        settingsPage.clickOnSave();
        waitABit(5);
        List<ArrayList> tempDataSettingsList = utilsForDB.getDataDriverSettings(userId);
        Map<String, Object> tempDataSettingsMap = listArrayToMap(tempDataSettingsList);
        checkAC("Driver licence number failed", tempDataSettingsMap.get("DLNumber").equals(dlAL), true);
        checkAC("Driver licence state failed", tempDataSettingsMap.get("DLState").equals("51"), true);
        checkAC("Expiration driver licence failed", tempDataSettingsMap.get("DLExpiration").equals(dateDLExpiration), true);



    }
}
