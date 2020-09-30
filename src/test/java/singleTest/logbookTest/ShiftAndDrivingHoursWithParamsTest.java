package singleTest.logbookTest;

import libs.SpreadsheetData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import parentTest.ParentTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;

import static libs.DataForTests.*;
import static libs.Utils.dateWithMinusDay;

@RunWith(Parameterized.class)

public class ShiftAndDrivingHoursWithParamsTest extends ParentTest {
    String cycleType, cargoType;

    Map dataForValidLogIn = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "driverLogin");
    String login = dataForValidLogIn.get("login").toString();
    String pass = dataForValidLogIn.get("pass").toString();


    public ShiftAndDrivingHoursWithParamsTest(String cycleType, String cargoType) throws IOException {
        this.cycleType = cycleType;
        this.cargoType = cargoType;
    }

    @Parameterized.Parameters(name = "Cycle: {0} and Cargo: {1}")
    public static Collection testData() throws IOException {

        InputStream spreadsheet = new FileInputStream(configProperties.DATA_FILE_PATH() + "testCycle.xls");
        return new SpreadsheetData(spreadsheet, "cycleAndCargoType").getData();
    }

    @Test
    public void shiftAndDriving() throws SQLException{

//        14-HOUR “DRIVING WINDOW” no violation

        String userId = utilsForDB.getUserIdByEmail(login);

        logsPage.cleanStatusesAndViolation(userId);
        logsPage.setCycle(userId, Integer.parseInt(cycleType));
        utilsForDB.setCargoTypeId(userId, Integer.parseInt(cargoType));

        loginPage.userValidLogIn(login, pass);
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(3));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.enterShiftHours(Integer.parseInt(cycleType), Integer.parseInt(cargoType));
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Violation exist", logsPage.checkAlertsExist(userId, dateWithMinusDay(3)), false);

        //        add 1 minute for get violation
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(3));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addLastMinuteWithViolationShiftHours(Integer.parseInt(cycleType), Integer.parseInt(cargoType));
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("driving with 14 hours violation(6) failed", logsPage.checkAlertsId(userId, dateWithMinusDay(3), DrivingTime14), true);
        checkAC("driving with 11 hours violation hours violation(5) failed", logsPage.checkAlertsId(userId, dateWithMinusDay(3), DrivingTime11), true);

    }
    @Test
    public void restBreak() throws SQLException {

        String userId = utilsForDB.getUserIdByEmail(login);

        logsPage.cleanStatusesAndViolation(userId);
        logsPage.setCycle(userId, Integer.parseInt(cycleType));
        utilsForDB.setCargoTypeId(userId, Integer.parseInt(cargoType));

        loginPage.userValidLogIn(login, pass);
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(3));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("01:00:00 AM", "09:00:00 AM", "Dr");
        logsPage.addStatus("09:00:00 AM", "09:30:00 AM", "On");
        logsPage.addStatus("09:30:00 AM", "12:00:00 PM", "Dr");
        logsPage.addStatus("12:00:00 PM", "12:30:00 PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Break Violation(8) failed", logsPage.checkAlertsId(userId, dateWithMinusDay(3), DrivingTime8), false);

        //        delete rest break for get violation
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(3));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("09:00:00 AM", "09:30:00 AM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Break Violation(8) failed", logsPage.checkAlertsId(userId, dateWithMinusDay(3), DrivingTime8), logsPage.isRestBreakRequired(Integer.parseInt(cycleType), Integer.parseInt(cargoType)));

    }
}
