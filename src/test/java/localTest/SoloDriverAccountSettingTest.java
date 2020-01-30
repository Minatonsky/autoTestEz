package localTest;

import libs.ExcelDriver;
import libs.UtilsForDB;
import org.junit.Test;
import parentTest.ParentTest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static libs.Utils.listArrayToMap;
import static libs.Utils.waitABit;

public class SoloDriverAccountSettingTest extends ParentTest {
    UtilsForDB utilsForDB = new UtilsForDB();
    ExcelDriver excelDriver = new ExcelDriver();

    Map dataForValidLogIn = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "driverLogin");

    String login = dataForValidLogIn.get("login").toString();
    String pass = dataForValidLogIn.get("pass").toString();
    String userId = dataForValidLogIn.get("userId").toString();

    public SoloDriverAccountSettingTest() throws IOException {
    }

    @Test
    public void upDateAccountSettings() throws SQLException, IOException, ClassNotFoundException {
        List<ArrayList> tempDataList = utilsForDB.getDataDriverSettings(userId);
        Map<String, Object> tempBeforeTestDataAccountMap = listArrayToMap(tempDataList);
        loginLocalSitePage.userValidLogIn(login, pass);
        dashboardLocalSitePage.goToSettingPage();
        accountSettingsLocalSitePage.clickOnAccountSetting();
        waitABit(5);
        accountSettingsLocalSitePage.setSoundNotification("1");

    }
}
