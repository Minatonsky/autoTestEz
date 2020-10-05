package singleTest.logbookTest;

import org.junit.Test;
import parentTest.ParentTest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import static libs.DataForTests.DrivingTime8;
import static libs.Utils.dateWithMinusDay;

public class RestBreak extends ParentTest {
    Map dataForValidLogIn = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "driverLogin");
    String login = dataForValidLogIn.get("login").toString();
    String pass = dataForValidLogIn.get("pass").toString();

    public RestBreak() throws IOException {
    }

    @Test
    public void restBreak() throws SQLException {

        int cycleType = 0;
        int cargoType = 0;

        String userId = utilsForDB.getUserIdByEmail(login);

        logsPage.cleanStatusesAndViolation(userId);
        logsPage.setCycle(userId, cycleType);
        utilsForDB.setCargoTypeId(userId, cargoType);

        loginPage.userValidLogIn(login, pass);
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(3));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("12:00:00 AM", "08:00:00 AM", "Dr");
        logsPage.addStatus("08:00:00 AM", "08:30:00 AM", "On");
        logsPage.addLastStatus("08:30:00 AM", "11:30:00 AM", "Dr");

        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Break Violation(8) failed", logsPage.checkAlertsId(userId, dateWithMinusDay(3), DrivingTime8), false);

    }
}
