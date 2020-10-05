package singleTest.logbookTest;

import org.junit.Test;
import parentTest.ParentTest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import static libs.Utils.dateWithMinusDay;

public class SBWithViolationTest extends ParentTest {
    String cycleType = "0";
    String cargoType = "0";

    int firstDay = 2;
    int secondDay = 1;

    Map dataForValidLogIn = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "driverLogin");
    String login = dataForValidLogIn.get("login").toString();
    String pass = dataForValidLogIn.get("pass").toString();

    public SBWithViolationTest() throws IOException {
    }


    @Test
    public void notFullLittlePeriodTest() throws SQLException {
        String userId = utilsForDB.getUserIdByEmail(login);

        logsPage.cleanStatusesAndViolation(userId);
        logsPage.setCycle(userId, Integer.parseInt(cycleType));
        utilsForDB.setCargoTypeId(userId, Integer.parseInt(cargoType));

        loginPage.userValidLogIn(login, pass);
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(firstDay));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("00:00:00 AM", "01:00:00 AM", "On");
        logsPage.addStatus("01:00:00 AM", "05:00:00 AM", "Dr");
        logsPage.addStatus("05:00:00 AM", "07:00:00 AM", "On");
        logsPage.addStatus("08:00:00 AM", "12:00:00 PM", "Dr");
        logsPage.addStatus("01:00:00 PM", "02:00:00 PM", "Dr");
        logsPage.addStatus("02:00:00 PM", "09:00:00 PM", "Sb");
        logsPage.addLastStatus("11:00:00 PM", "11:59:59 PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        checkAC("Violation exist", logsPage.checkAlertsExist(userId, dateWithMinusDay(firstDay)), true);
        checkAC("Driving hours violation", logsPage.getStatusData(userId, dateWithMinusDay(firstDay), "drive")<0, true);
        checkAC("Shift hours violation", logsPage.getStatusData(userId, dateWithMinusDay(firstDay), "shift")<0, true);

    }
}
