package singleTest.logbookTest;

import org.junit.Test;
import parentTest.ParentTest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
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

        int cycleType = 0;
        int cargoType = 4;

        String userId = utilsForDB.getUserIdByEmail(login);

        logsPage.cleanStatusesAndViolation(userId);
        logsPage.setCycle(userId, cycleType);
        utilsForDB.setCargoTypeId(userId, cargoType);

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
        checkAC("Break Violation(8) failed", logsPage.checkAlertsId(userId, dateWithMinusDay(3), DrivingTime8), logsPage.isRestBreakRequired(cycleType, cargoType));

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
    @Test
    public void usa_60hr_7days() throws Exception {

        int cargoType = 0;

        String userId = utilsForDB.getUserIdByEmail(login);

        logsPage.cleanStatusesAndViolation(userId);
        logsPage.setCycle(userId, USA_60hr_7days);
        utilsForDB.setCargoTypeId(userId, cargoType);
        loginPage.userValidLogIn(login, pass);

        //        1 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(9));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("06:00:00 AM", "10:00:00 AM", "Dr");//4
        logsPage.addStatus("10:00:00 AM", "11:00:00 AM", "On");//1
        logsPage.addStatus("01:00:00 PM", "05:00:00 PM", "Dr");//4
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        int cycleHours = logsPage.countHoursStatuses(Arrays.asList("06:00:00 AM/10:00:00 AM", "01:00:00 PM/05:00:00 PM", "10:00:00 AM/11:00:00 AM"));
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(9), "cycle") == logsPage.getCycleHours(USA_60hr_7days, cargoType, cycleHours), true);

        //        2 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(8));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("06:00:00 AM", "10:00:00 AM", "Dr");
        logsPage.addStatus("10:00:00 AM", "11:00:00 AM", "On");
        logsPage.addStatus("01:00:00 PM", "05:00:00 PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        int cycleHours2 = cycleHours + (logsPage.countHoursStatuses(Arrays.asList("06:00:00 AM/10:00:00 AM", "01:00:00 PM/05:00:00 PM", "10:00:00 AM/11:00:00 AM")));
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(8), "cycle") == logsPage.getCycleHours(USA_60hr_7days, cargoType, cycleHours2), true);

        //        3 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(7));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("06:00:00 AM", "10:00:00 AM", "Dr");
        logsPage.addStatus("10:00:00 AM", "11:00:00 AM", "On");
        logsPage.addStatus("01:00:00 PM", "05:00:00 PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        int cycleHours3 = cycleHours2 + (logsPage.countHoursStatuses(Arrays.asList("06:00:00 AM/10:00:00 AM", "01:00:00 PM/05:00:00 PM", "10:00:00 AM/11:00:00 AM")));
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(7), "cycle") == logsPage.getCycleHours(USA_60hr_7days, cargoType, cycleHours3), true);

        //        4 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(6));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("06:00:00 AM", "10:00:00 AM", "Dr");
        logsPage.addStatus("10:00:00 AM", "11:00:00 AM", "On");
        logsPage.addStatus("01:00:00 PM", "05:00:00 PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        int cycleHours4 = cycleHours3 + (logsPage.countHoursStatuses(Arrays.asList("06:00:00 AM/10:00:00 AM", "01:00:00 PM/05:00:00 PM", "10:00:00 AM/11:00:00 AM")));
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(6), "cycle") == logsPage.getCycleHours(USA_60hr_7days, cargoType, cycleHours4), true);

        //        5 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(5));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("06:00:00 AM", "10:00:00 AM", "Dr");
        logsPage.addStatus("10:00:00 AM", "11:00:00 AM", "On");
        logsPage.addStatus("01:00:00 PM", "05:00:00 PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        int cycleHours5 = cycleHours4 + (logsPage.countHoursStatuses(Arrays.asList("06:00:00 AM/10:00:00 AM", "01:00:00 PM/05:00:00 PM", "10:00:00 AM/11:00:00 AM")));
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(5), "cycle") == logsPage.getCycleHours(USA_60hr_7days, cargoType, cycleHours5), true);

        //        6 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(4));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("06:00:00 AM", "10:00:00 AM", "Dr");
        logsPage.addStatus("10:00:00 AM", "11:00:00 AM", "On");
        logsPage.addStatus("01:00:00 PM", "05:00:00 PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        int cycleHours6 = cycleHours5 + (logsPage.countHoursStatuses(Arrays.asList("06:00:00 AM/10:00:00 AM", "01:00:00 PM/05:00:00 PM", "10:00:00 AM/11:00:00 AM")));
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(4), "cycle") == logsPage.getCycleHours(USA_60hr_7days, cargoType, cycleHours6), true);

        //        7 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(3));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("06:00:00 AM", "10:00:00 AM", "Dr");//4
        logsPage.addStatus("10:00:00 AM", "11:00:00 AM", "On");//1
        logsPage.addStatus("01:00:00 PM", "02:00:00 PM", "Dr");//1
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        int cycleHours7 = cycleHours6 + (logsPage.countHoursStatuses(Arrays.asList("06:00:00 AM/10:00:00 AM", "01:00:00 PM/02:00:00 PM", "10:00:00 AM/11:00:00 AM")));
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(3), "cycle") == logsPage.getCycleHours(USA_60hr_7days, cargoType, cycleHours7), true);
        checkAC("Restart hours are incorrect", logsPage.getStatusData(userId, dateWithMinusDay(3), "restart34") == logsPage.getRestart34(USA_60hr_7days, cargoType), true);
        checkAC("Violation exist", logsPage.checkAlertsExist(userId, dateWithMinusDay(3)), false);

        //        7 day with violation
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(3));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("02:00:00 PM", "02:01:00 PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Overworked Cycle Violation(7) failed", logsPage.checkAlertsId(userId, dateWithMinusDay(3), OverworkedCycle), true);
    }

}
