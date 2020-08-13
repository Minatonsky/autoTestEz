package frankesteinTest.logbookTest;

import org.junit.Test;
import parentTest.ParentTest;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import static libs.DataForTests.*;
import static libs.Utils.dateWithMinusDay;

public class Hour14DrivingWindow extends ParentTest {
    Map dataForValidLogIn = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "driverLogin");
    String login = dataForValidLogIn.get("login").toString();
    String pass = dataForValidLogIn.get("pass").toString();


    public Hour14DrivingWindow() throws IOException {
    }
    @Test
    public void Hour14DrivingWindowNoViolation() throws SQLException{

//        14-HOUR “DRIVING WINDOW” no violation

        String userId = utilsForDB.getUserIdByEmail(login);

        logsPage.cleanStatusesViolation(userId);
        logsPage.setCycle(userId, USA_60hr_7days);
        utilsForDB.setCargoTypeId(userId, Property);

        loginPage.userValidLogIn(login, pass);
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(3));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();

        logsPage.addStatus("00:00:00 AM", "01:00:00 AM", "On");
        logsPage.addStatus("01:00:00 AM", "06:00:00 AM", "Dr");
        logsPage.addStatus("07:00:00 AM", "10:00:00 AM", "Dr");
        logsPage.addStatus("10:00:00 AM", "12:00:00 PM", "On");
        logsPage.addLastStatus("12:00:00 PM", "02:00:00 PM", "Dr");

        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Violation exist", logsPage.checkAlertsExist(userId, dateWithMinusDay(3)), false);

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
