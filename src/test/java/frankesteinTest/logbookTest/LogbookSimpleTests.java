package frankesteinTest.logbookTest;

import org.junit.Test;
import parentTest.ParentTest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import static libs.DataForTests.*;
import static libs.Utils.dateWithMinusDay;

public class LogbookSimpleTests extends ParentTest {

    Map dataForValidLogIn = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "driverLogin");
    String login = dataForValidLogIn.get("login").toString();
    String pass = dataForValidLogIn.get("pass").toString();

    public LogbookSimpleTests() throws IOException {
    }

    @Test
    public void restBreak() throws SQLException {

        String userId = utilsForDB.getUserIdByEmail(login);

        logsPage.cleanStatusesAndViolation(userId);
        logsPage.setCycle(userId, 4);
        utilsForDB.setCargoTypeId(userId, 1);

        loginPage.userValidLogIn(login, pass);
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(3));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.enterRestBreak();
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Break Violation(8) failed", logsPage.checkAlertsId(userId, dateWithMinusDay(3), DrivingTime8), false);

        //        delete rest break for get violation
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(3));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addViolationForRestBreak();
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Break Violation(8) failed", logsPage.checkAlertsId(userId, dateWithMinusDay(3), DrivingTime8), logsPage.isRestBreakRequired(4, 1));

    }
    @Test
    public void shiftAndDriving() throws SQLException{

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
        logsPage.enterShiftHours(cycleType, cargoType);
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Violation exist", logsPage.checkAlertsExist(userId, dateWithMinusDay(3)), false);

        //        add 1 minute for get violation
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(3));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addLastMinuteWithViolationShiftHours(cycleType, cargoType);
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("driving with 14 hours violation(6) failed", logsPage.checkAlertsId(userId, dateWithMinusDay(3), DrivingTime14), true);
        checkAC("driving with 11 hours violation hours violation(5) failed", logsPage.checkAlertsId(userId, dateWithMinusDay(3), DrivingTime11), true);
//        checkAC("Canada hours off duty a day violation(9) failed", logsPage.checkAlertsId(userId, dateWithMinusDay(3), Canada10HoursOff), true);

    }
    @Test
    public void SB_2_8_hours() throws SQLException {
        int cycleType = 1;
        int cargoType = 0;

        String userId = utilsForDB.getUserIdByEmail(login);

        logsPage.cleanStatusesAndViolation(userId);
        logsPage.setCycle(userId, cycleType);
        utilsForDB.setCargoTypeId(userId, cargoType);

        loginPage.userValidLogIn(login, pass);
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(3));
//        logsPage.setListOfElements();
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();

        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

    }
}
