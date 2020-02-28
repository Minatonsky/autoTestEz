package localSiteTest.logbookTest;

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

        loginLocalSitePage.userValidLogIn(login, pass);
        dashboardLocalSitePage.openMenuDash();
        dashboardLocalSitePage.goToLogsPage();
        logsLocalSitePage.clickOnRowDay(date);
        logsLocalSitePage.clickOnCorrectionButton();
        logsLocalSitePage.clickOnInsertStatusButton();

        logsLocalSitePage.clickOnTimeTo("");
        logsLocalSitePage.clickOnSaveButton();
        logsLocalSitePage.clickOnTimeFrom("");
        logsLocalSitePage.clickOnSaveButton();
        logsLocalSitePage.clickOnStatusOn();
        logsLocalSitePage.clickOnInsertStatusButton();

        logsLocalSitePage.clickOnTimeTo("");

        logsLocalSitePage.clickOnSaveButton();
        logsLocalSitePage.clickOnTimeFrom("");

        logsLocalSitePage.clickOnSaveButton();
        logsLocalSitePage.clickOnStatusDr();
        logsLocalSitePage.clickOnInsertStatusButton();

        logsLocalSitePage.clickOnTimeTo("");

        logsLocalSitePage.clickOnSaveButton();
        logsLocalSitePage.clickOnTimeFrom("");

        logsLocalSitePage.clickOnSaveButton();
        logsLocalSitePage.clickOnStatusDr();
        logsLocalSitePage.clickOnInsertStatusButton();

        logsLocalSitePage.clickOnTimeTo("");
        logsLocalSitePage.clickOnTimeFrom("");

        logsLocalSitePage.clickOnSaveButton();
        logsLocalSitePage.clickOnStatusOn();
        logsLocalSitePage.clickOnInsertStatusButton();

        logsLocalSitePage.clickOnTimeTo("");

        logsLocalSitePage.clickOnSaveButton();
        logsLocalSitePage.clickOnTimeFrom("");

        logsLocalSitePage.clickOnSaveButton();
        logsLocalSitePage.clickOnStatusDr();
        logsLocalSitePage.clickOnInsertStatusButton();

        logsLocalSitePage.clickOnSaveButton();
        waitABit(20);
        logsLocalSitePage.closeCorrectionSavePopUp();
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

        loginLocalSitePage.userValidLogIn(login, pass);
        dashboardLocalSitePage.goToLogsPage();
        logsLocalSitePage.clickOnRowDay(date);
        waitABit(5);
        logsLocalSitePage.clickOnInsertStatusButton();
        waitABit(5);
        logsLocalSitePage.clickOnTimeTo("10");
        logsLocalSitePage.clickOnTimeFrom("01");
        waitABit(5);

        logsLocalSitePage.clickOnStatusDr();
        logsLocalSitePage.clickOnSaveButton();
        waitABit(5);
        logsLocalSitePage.clickOnInsertStatusButton();
        waitABit(5);
        logsLocalSitePage.clickOnTimeTo("1815");
        logsLocalSitePage.clickOnTimeFrom("17");
        waitABit(5);
        logsLocalSitePage.clickOnStatusDr();
        logsLocalSitePage.clickOnSaveButton();
        waitABit(5);

    }
}
