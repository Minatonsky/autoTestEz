package localSiteParameterizedTest;

import libs.SpreadsheetData;
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
public class DriverLicenseParameterizedLocalSiteTest extends ParentTest {
    String state;

    Map dataForValidLogIn = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "driverLogin");
    Map dataForDLRegexState = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testSettings.xls", "USDLRegex");
    Map dataForDLNumberState = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testSettings.xls", "USDLNumber");

    String login = dataForValidLogIn.get("login").toString();
    String pass = dataForValidLogIn.get("pass").toString();
    String userId = dataForValidLogIn.get("userId").toString();

    public DriverLicenseParameterizedLocalSiteTest(String state) throws IOException {
        this.state = state;

    }
    @Parameterized.Parameters(name = "Parameters are: {0}")
    public static Collection testData() throws IOException {
        InputStream spreadsheet = new FileInputStream(configProperties.DATA_FILE_PATH() + "testSettings.xls");
        return new SpreadsheetData(spreadsheet,"parametrizedNumberState").getData();
    }

    @Test
    public void validDriverLicenseUSA() throws SQLException, IOException, ClassNotFoundException {

        String regexState = dataForDLRegexState.get(state).toString();
        String dlNumber = genRandomDataByRegex(regexState);
        String stateNumber = dataForDLNumberState.get(state).toString();
        String dateDLExpiration = getDateRandom();

        loginLocalSitePage.userValidLogIn(login, pass);
        dashboardLocalSitePage.goToSettingPage();
        driverSettingsLocalSitePage.clickOnDriverSettings();

        driverSettingsLocalSitePage.enterNumberDl(dlNumber);
        driverSettingsLocalSitePage.selectCountry("USA");
        driverSettingsLocalSitePage.selectStateDl(state);
        driverSettingsLocalSitePage.enterExpirationDl(dateDLExpiration);

        waitABit(3);
        driverSettingsLocalSitePage.clickOnSave();
        waitABit(5);
        List<ArrayList> tempDataSettingsList = utilsForDB.getDataDriverSettings(userId);
        Map<String, Object> tempDataSettingsMap = listArrayToMap(tempDataSettingsList);
        checkAC("Driver licence number failed", tempDataSettingsMap.get("DLNumber").equals(dlNumber), true);
        checkAC("Driver licence state failed", tempDataSettingsMap.get("DLState").equals(stateNumber), true);
        checkAC("Expiration driver licence failed", tempDataSettingsMap.get("DLExpiration").equals(dateDLExpiration), true);

    }
}
