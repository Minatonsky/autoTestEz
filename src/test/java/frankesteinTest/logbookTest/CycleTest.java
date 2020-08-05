package frankesteinTest.logbookTest;

import org.junit.Test;
import parentTest.ParentTest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import static libs.DataForTests.USA_60hr_7days;
import static libs.Utils.dateWithMinusDay;

public class CycleTest extends ParentTest {

    Map dataForValidLogIn = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "driverLogin");
    String login = dataForValidLogIn.get("login").toString();
    String pass = dataForValidLogIn.get("pass").toString();

    public CycleTest() throws IOException {
    }

    @Test
    public void usa_60hr_7days() throws SQLException {
        String userId = utilsForDB.getUserIdByEmail(login);

        logsPage.cleanStatusesViolation(userId);
        logsPage.setCycle(userId, USA_60hr_7days);

        loginPage.userValidLogIn(login, pass);

        //        1 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(9));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("060000AM", "100000AM", "Dr");
        logsPage.addLastStatus("010000PM", "050000PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        //        2 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(8));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("060000AM", "100000AM", "Dr");
        logsPage.addLastStatus("010000PM", "050000PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        //        3 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(7));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("060000AM", "100000AM", "Dr");
        logsPage.addLastStatus("010000PM", "050000PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        //        4 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(6));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("060000AM", "100000AM", "Dr");
        logsPage.addLastStatus("010000PM", "060000PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        //        5 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(5));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("060000AM", "100000AM", "Dr");
        logsPage.addLastStatus("010000PM", "060000PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        //        6 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(4));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("060000AM", "100000AM", "Dr");
        logsPage.addLastStatus("010000PM", "060000PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        //        7 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(3));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("060000AM", "100000AM", "Dr");
        logsPage.addLastStatus("010000PM", "060000PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        checkAC("", logsPage.getStatusData(userId, dateWithMinusDay(3), "cycle").equals("0"), true);

    }
}
