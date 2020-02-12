package logbookTest;

import org.junit.Test;
import parentTest.ParentTest;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import static libs.Utils.waitABit;

public class FourteenHourDrivingWindow extends ParentTest {
    Map dataForValidLogIn = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "driverLogin");
    String login = dataForValidLogIn.get("login").toString();
    String pass = dataForValidLogIn.get("pass").toString();


    public FourteenHourDrivingWindow() throws IOException {
    }
    @Test
    public void fourteenHourDrivingWindowNoViolation() throws SQLException, IOException, ClassNotFoundException {
//        14-HOUR “DRIVING WINDOW” no violation
        String userId = utilsForDB.getUserIdByEmail(login);
        LocalDateTime yesterday = LocalDateTime.parse(LocalDateTime.now().minusDays(1).toString());
        String date = yesterday.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        utilsForDB.deleteStatuses(userId);
        utilsForDB.updateLastStatus(userId);

        loginPage.userValidLogIn(login, pass);
        dashboardPage.openMenuDash();
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(date);
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();

        logsPage.clickOnTimeTo();
        logsPage.timeToInput("010000AM");
        logsPage.clickOnSaveButton();
        logsPage.clickOnTimeFrom();
        logsPage.timeToInput("000000AM");
        logsPage.clickOnSaveButton();
        logsPage.clickOnStatusOn();
        logsPage.clickOnInsertStatusButton();

        logsPage.clickOnTimeTo();
        logsPage.timeToInput("060000AM");
        logsPage.clickOnSaveButton();
        logsPage.clickOnTimeFrom();
        logsPage.timeToInput("010000AM");
        logsPage.clickOnSaveButton();
        logsPage.clickOnStatusDr();
        logsPage.clickOnInsertStatusButton();

        logsPage.clickOnTimeTo();
        logsPage.timeToInput("100000AM");
        logsPage.clickOnSaveButton();
        logsPage.clickOnTimeFrom();
        logsPage.timeToInput("070000AM");
        logsPage.clickOnSaveButton();
        logsPage.clickOnStatusDr();
        logsPage.clickOnInsertStatusButton();

        logsPage.clickOnTimeTo();
        logsPage.timeToInput("120000PM");
        logsPage.clickOnSaveButton();
        logsPage.clickOnTimeFrom();
        logsPage.timeToInput("100000AM");
        logsPage.clickOnSaveButton();
        logsPage.clickOnStatusOn();
        logsPage.clickOnInsertStatusButton();

        logsPage.clickOnTimeTo();
        logsPage.timeToInput("020000PM");
        logsPage.clickOnSaveButton();
        logsPage.clickOnTimeFrom();
        logsPage.timeToInput("120000PM");
        logsPage.clickOnSaveButton();
        logsPage.clickOnStatusDr();
        logsPage.clickOnInsertStatusButton();

        logsPage.clickOnSaveInfoButton();
        waitABit(20);
        logsPage.closeCorrectionSavePopUp();
        checkAC("Break Violation failed", logsPage.isBreakViolationPresent(), false);
        checkAC("Drive Hours Violation failed", logsPage.isDriveHoursViolationPresent(), false);
        checkAC("Shift Hours Violation failed", logsPage.isShiftHoursViolationPresent(), false);

    }
    @Test
    public void fourteenHourDrivingWindowWithViolation() throws SQLException, IOException, ClassNotFoundException {
//       11 Hours Driving Within 14-Hour “Driving Window” (With Violations)
        String userId = utilsForDB.getUserIdByEmail(login);
        LocalDateTime yesterday = LocalDateTime.parse(LocalDateTime.now().minusDays(1).toString());
        String date = yesterday.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        utilsForDB.deleteStatuses(userId);
        utilsForDB.updateLastStatus(userId);

        loginPage.userValidLogIn(login, pass);
        dashboardPage.openMenuDash();
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(date);
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();

        logsPage.clickOnTimeTo();
        logsPage.timeToInput("060000PM");
        logsPage.clickOnSaveButton();
        waitABit(10);
        logsPage.clickOnTimeFrom();
        waitABit(10);
        logsPage.timeToInput("1100000AM");
        waitABit(10);
        logsPage.clickOnSaveButton();
        logsPage.clickOnStatusDr();
        logsPage.clickOnInsertStatusButton();

        logsPage.clickOnSaveInfoButton();
        waitABit(20);
        logsPage.closeCorrectionSavePopUp();

        checkAC("Break Violation(8) failed", logsPage.isBreakViolationPresent(), false);
        checkAC("Drive Hours Violation(11) failed", logsPage.isDriveHoursViolationPresent(), true);
        checkAC("Shift Hours Violation(14) failed", logsPage.isShiftHoursViolationPresent(), true);


    }
}
