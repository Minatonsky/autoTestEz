package frankesteinTest.logbookTest;

import org.junit.Test;
import parentTest.ParentTest;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import static libs.DataForTests.*;
import static libs.Utils.getLocalDateTimeUTC;

public class FourteenHourDrivingWindow extends ParentTest {
    Map dataForValidLogIn = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "driverLogin");
    String login = dataForValidLogIn.get("login").toString();
    String pass = dataForValidLogIn.get("pass").toString();


    public FourteenHourDrivingWindow() throws IOException {
    }
    @Test
    public void fourteenHourDrivingWindowNoViolation() throws SQLException{

//        14-HOUR “DRIVING WINDOW” no violation

        String userId = utilsForDB.getUserIdByEmail(login);
        String date = getLocalDateTimeUTC().minusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        logsPage.cleanStatusesViolation(userId);

        loginPage.userValidLogIn(login, pass);
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(date);
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();

        logsPage.addStatus("000000AM", "010000AM", "On");

        logsPage.addStatus("010000AM", "060000AM", "Dr");

        logsPage.addStatus("070000AM", "100000AM", "Dr");

        logsPage.addStatus("100000AM", "120000PM", "On");

        logsPage.addLastStatus("120000PM", "020000PM", "Dr");

        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Violation exist", logsPage.checkAlertsExist(userId, date), false);

    }
    @Test
    public void fourteenHourDrivingWindowWithViolation() throws SQLException {

//       11 Hours Driving Within 14-Hour “Driving Window” (With Violations)

        String userId = utilsForDB.getUserIdByEmail(login);
        LocalDateTime yesterday = LocalDateTime.parse(LocalDateTime.now().minusDays(1).toString());
        String date = yesterday.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        utilsForDB.deleteViolation(userId);
        utilsForDB.deleteStatuses(userId);
        utilsForDB.updateLastStatus(userId);

        loginPage.userValidLogIn(login, pass);

        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(date);
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();

        logsPage.addStatus("1100000AM", "060000PM", "Dr");

        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        checkAC("Break Violation(8) failed", logsPage.checkAlertsId(userId, date, DrivingTime8), false);
        checkAC("Drive Hours Violation(11) failed", logsPage.checkAlertsId(userId, date, DrivingTime11), true);
        checkAC("Shift Hours Violation(14) failed", logsPage.checkAlertsId(userId, date, DrivingTime14), true);


    }
}
