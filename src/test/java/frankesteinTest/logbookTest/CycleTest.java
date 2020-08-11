package frankesteinTest.logbookTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import parentTest.ParentTest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import static libs.DataForTests.*;
import static libs.StatusTime.getStatusTime;
import static libs.Utils.dateWithMinusDay;

@RunWith(Parameterized.class)

public class CycleTest extends ParentTest {
    int cargoType;


    Map dataForValidLogIn = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "driverLogin");
    String login = dataForValidLogIn.get("login").toString();
    String pass = dataForValidLogIn.get("pass").toString();

    public CycleTest(int cargoType) throws IOException {
        this.cargoType = cargoType;
    }
    @Parameterized.Parameters(name = "Type cargo: {0}")
    public static Collection testData() {
        return Arrays.asList(new Object[][] {
                { 0 },
                { 1 },
                { 2 },
                { 3 },
                { 4 }
        });
    }


    @Test
    public void usa_60hr_7days() throws Exception {

        String userId = utilsForDB.getUserIdByEmail(login);

        logsPage.cleanStatusesViolation(userId);
        logsPage.setCycle(userId, USA_60hr_7days);
        utilsForDB.setCargoTypeId(userId, cargoType);
        loginPage.userValidLogIn(login, pass);

        //        1 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(9));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("06:00:00 AM", "10:00:00 AM", "Dr");
        logsPage.addStatus("10:00:00 AM", "11:00:00 AM", "On");
        logsPage.addLastStatus("01:00:00 PM", "05:00:00 PM", "Dr");
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
        logsPage.addLastStatus("01:00:00 PM", "05:00:00 PM", "Dr");
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
        logsPage.addLastStatus("01:00:00 PM", "05:00:00 PM", "Dr");
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
        logsPage.addLastStatus("01:00:00 PM", "05:00:00 PM", "Dr");
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
        logsPage.addLastStatus("01:00:00 PM", "05:00:00 PM", "Dr");
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
        logsPage.addLastStatus("01:00:00 PM", "05:00:00 PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        int cycleHours6 = cycleHours5 + (logsPage.countHoursStatuses(Arrays.asList("06:00:00 AM/10:00:00 AM", "01:00:00 PM/05:00:00 PM", "10:00:00 AM/11:00:00 AM")));
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(4), "cycle") == logsPage.getCycleHours(USA_60hr_7days, cargoType, cycleHours6), true);

        //        7 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(3));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("06:00:00 AM", "10:00:00 AM", "Dr");
        logsPage.addStatus("10:00:00 AM", "11:00:00 AM", "On");
        logsPage.addLastStatus("01:00:00 PM", "02:01:00 PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        int cycleHours7 = cycleHours6 + (logsPage.countHoursStatuses(Arrays.asList("06:00:00 AM/10:00:00 AM", "01:00:00 PM/02:01:00 PM", "10:00:00 AM/11:00:00 AM")));
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(3), "cycle") == logsPage.getCycleHours(USA_60hr_7days, cargoType, cycleHours7), true);
        checkAC("Overworked Cycle Violation(7) failed", logsPage.checkAlertsId(userId, dateWithMinusDay(3), OverworkedCycle), true);
    }

    @Test
    public void usa_70hr_8days() throws SQLException {
        String userId = utilsForDB.getUserIdByEmail(login);

        logsPage.cleanStatusesViolation(userId);
        logsPage.setCycle(userId, USA_70hr_8days);

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
        checkAC("Drive hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(9), "drive") == 3, true);
        checkAC("Shift hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(9), "shift") == 3, true);
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(9), "cycle") == 62, true);

        //        2 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(8));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("060000AM", "100000AM", "Dr");
        logsPage.addLastStatus("010000PM", "050000PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Drive hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(8), "drive") == 3, true);
        checkAC("Shift hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(8), "shift") == 3, true);
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(8), "cycle") == 54, true);

        //        3 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(7));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("060000AM", "100000AM", "Dr");
        logsPage.addLastStatus("010000PM", "050000PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Drive hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(7), "drive") == 3, true);
        checkAC("Shift hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(7), "shift") == 3, true);
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(7), "cycle") == 46, true);

        //        4 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(6));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("060000AM", "100000AM", "Dr");
        logsPage.addLastStatus("010000PM", "050000PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Drive hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(6), "drive") == 3, true);
        checkAC("Shift hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(6), "shift") == 3, true);
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(6), "cycle") == 38, true);

        //        5 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(5));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("060000AM", "100000AM", "Dr");
        logsPage.addLastStatus("010000PM", "050000PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Drive hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(5), "drive") == 3, true);
        checkAC("Shift hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(5), "shift") == 3, true);
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(5), "cycle") == 30, true);

        //        6 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(4));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus(getStatusTime(10).getStatus1From(), getStatusTime(8).getStatus1To(), getStatusTime(8).getStatus1Type());
        logsPage.addLastStatus(getStatusTime(10).getStatus2From(), getStatusTime(8).getStatus2To(), getStatusTime(8).getStatus2Type());
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Drive hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(4), "drive") == 1, true);
        checkAC("Shift hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(4), "shift") == 2, true);
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(4), "cycle") == 20, true);

        //        7 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(3));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("060000AM", "110000AM", "Dr");
        logsPage.addLastStatus("010000PM", "060000PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Drive hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(3), "drive") == 1, true);
        checkAC("Shift hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(3), "shift") == 2, true);
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(3), "cycle") == 10, true);

        //        8 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(2));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("060000AM", "110000AM", "Dr");
        logsPage.addLastStatus("010000PM", "060000PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Drive hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(2), "drive") == 1, true);
        checkAC("Shift hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(2), "shift") == 2, true);
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(2), "cycle") == 0, true);

    }

    @Test
    public void alaska_70hr_7days() throws SQLException {
        String userId = utilsForDB.getUserIdByEmail(login);

        logsPage.cleanStatusesViolation(userId);
        logsPage.setCycle(userId, Alaska_70hr_7days);

        loginPage.userValidLogIn(login, pass);

        //        1 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(9));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("060000AM", "110000AM", "Dr");
        logsPage.addLastStatus("010000PM", "060000PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Drive hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(9), "drive") == 5, true);
        checkAC("Shift hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(9), "shift") == 8, true);
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(9), "cycle") == 60, true);

        //        2 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(8));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("060000AM", "110000AM", "Dr");
        logsPage.addLastStatus("010000PM", "060000PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Drive hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(8), "drive") == 5, true);
        checkAC("Shift hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(8), "shift") == 8, true);
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(8), "cycle") == 50, true);

        //        3 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(7));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("060000AM", "110000AM", "Dr");
        logsPage.addLastStatus("010000PM", "060000PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Drive hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(7), "drive") == 5, true);
        checkAC("Shift hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(7), "shift") == 8, true);
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(7), "cycle") == 40, true);

        //        4 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(6));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("060000AM", "110000AM", "Dr");
        logsPage.addLastStatus("010000PM", "060000PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Drive hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(6), "drive") == 5, true);
        checkAC("Shift hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(6), "shift") == 8, true);
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(6), "cycle") == 30, true);

        //        5 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(5));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("060000AM", "110000AM", "Dr");
        logsPage.addLastStatus("010000PM", "060000PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Drive hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(5), "drive") == 5, true);
        checkAC("Shift hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(5), "shift") == 8, true);
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(5), "cycle") == 20, true);

        //        6 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(4));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("060000AM", "110000AM", "Dr");
        logsPage.addLastStatus("010000PM", "060000PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Drive hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(4), "drive") == 5, true);
        checkAC("Shift hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(4), "shift") == 8, true);
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(4), "cycle") == 10, true);

        //        7 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(3));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("060000AM", "110000AM", "Dr");
        logsPage.addLastStatus("010000PM", "060000PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Drive hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(3), "drive") == 5, true);
        checkAC("Shift hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(3), "shift") == 8, true);
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(3), "cycle") == 0, true);

    }

    @Test
    public void alaska_80hr_8days() throws SQLException {
        String userId = utilsForDB.getUserIdByEmail(login);

        logsPage.cleanStatusesViolation(userId);
        logsPage.setCycle(userId, Alaska_80hr_8days);

        loginPage.userValidLogIn(login, pass);

        //        1 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(9));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("060000AM", "110000AM", "Dr");
        logsPage.addLastStatus("010000PM", "060000PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Drive hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(9), "drive") == 5, true);
        checkAC("Shift hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(9), "shift") == 8, true);
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(9), "cycle") == 70, true);

        //        2 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(8));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("060000AM", "110000AM", "Dr");
        logsPage.addLastStatus("010000PM", "060000PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Drive hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(8), "drive") == 5, true);
        checkAC("Shift hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(8), "shift") == 8, true);
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(8), "cycle") == 60, true);

        //        3 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(7));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("060000AM", "110000AM", "Dr");
        logsPage.addLastStatus("010000PM", "060000PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Drive hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(7), "drive") == 5, true);
        checkAC("Shift hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(7), "shift") == 8, true);
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(7), "cycle") == 50, true);

        //        4 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(6));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("060000AM", "110000AM", "Dr");
        logsPage.addLastStatus("010000PM", "060000PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Drive hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(6), "drive") == 5, true);
        checkAC("Shift hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(6), "shift") == 8, true);
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(6), "cycle") == 40, true);

        //        5 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(5));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("060000AM", "110000AM", "Dr");
        logsPage.addLastStatus("010000PM", "060000PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Drive hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(5), "drive") == 5, true);
        checkAC("Shift hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(5), "shift") == 8, true);
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(5), "cycle") == 30, true);

        //        6 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(4));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("060000AM", "110000AM", "Dr");
        logsPage.addLastStatus("010000PM", "060000PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Drive hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(4), "drive") == 5, true);
        checkAC("Shift hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(4), "shift") == 8, true);
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(4), "cycle") == 20, true);

        //        7 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(3));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("060000AM", "110000AM", "Dr");
        logsPage.addLastStatus("010000PM", "060000PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Drive hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(3), "drive") == 5, true);
        checkAC("Shift hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(3), "shift") == 8, true);
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(3), "cycle") == 10, true);

        //        8 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(2));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("060000AM", "110000AM", "Dr");
        logsPage.addLastStatus("010000PM", "060000PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Drive hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(2), "drive") == 5, true);
        checkAC("Shift hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(2), "shift") == 8, true);
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(2), "cycle") == 0, true);

    }

    @Test
    public void canada_70hr_7days() throws SQLException {
        String userId = utilsForDB.getUserIdByEmail(login);

        logsPage.cleanStatusesViolation(userId);
        logsPage.setCycle(userId, Canada_70hr_7days);

        loginPage.userValidLogIn(login, pass);

        //        1 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(9));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("060000AM", "110000AM", "Dr");
        logsPage.addLastStatus("010000PM", "060000PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Drive hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(9), "drive") == 3, true);
        checkAC("Shift hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(9), "shift") == 4, true);
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(9), "cycle") == 60, true);

        //        2 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(8));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("060000AM", "110000AM", "Dr");
        logsPage.addLastStatus("010000PM", "060000PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Drive hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(8), "drive") == 3, true);
        checkAC("Shift hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(8), "shift") == 4, true);
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(8), "cycle") == 50, true);

        //        3 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(7));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("060000AM", "110000AM", "Dr");
        logsPage.addLastStatus("010000PM", "060000PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Drive hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(7), "drive") == 3, true);
        checkAC("Shift hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(7), "shift") == 4, true);
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(7), "cycle") == 40, true);

        //        4 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(6));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("060000AM", "110000AM", "Dr");
        logsPage.addLastStatus("010000PM", "060000PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Drive hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(6), "drive") == 3, true);
        checkAC("Shift hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(6), "shift") == 4, true);
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(6), "cycle") == 30, true);

        //        5 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(5));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("060000AM", "110000AM", "Dr");
        logsPage.addLastStatus("010000PM", "060000PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Drive hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(5), "drive") == 3, true);
        checkAC("Shift hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(5), "shift") == 4, true);
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(5), "cycle") == 20, true);

        //        6 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(4));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("060000AM", "110000AM", "Dr");
        logsPage.addLastStatus("010000PM", "060000PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Drive hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(4), "drive") == 3, true);
        checkAC("Shift hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(4), "shift") == 4, true);
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(4), "cycle") == 10, true);

        //        7 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(3));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("060000AM", "110000AM", "Dr");
        logsPage.addLastStatus("010000PM", "060000PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Drive hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(3), "drive") == 3, true);
        checkAC("Shift hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(3), "shift") == 4, true);
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(3), "cycle") == 0, true);

    }

    @Test
    public void canada_120hr_14days() throws SQLException {
        String userId = utilsForDB.getUserIdByEmail(login);

        logsPage.cleanStatusesViolation(userId);
        logsPage.setCycle(userId, Canada_120hr_14days);

        loginPage.userValidLogIn(login, pass);

        //        1 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(16));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("060000AM", "110000AM", "Dr");
        logsPage.addLastStatus("010000PM", "060000PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Drive hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(16), "drive") == 3, true);
        checkAC("Shift hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(16), "shift") == 4, true);
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(16), "cycle") == 110, true);

        //        2 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(15));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("060000AM", "110000AM", "Dr");
        logsPage.addLastStatus("010000PM", "060000PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Drive hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(15), "drive") == 3, true);
        checkAC("Shift hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(15), "shift") == 4, true);
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(15), "cycle") == 100, true);

        //        3 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(14));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("060000AM", "110000AM", "Dr");
        logsPage.addLastStatus("010000PM", "060000PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Drive hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(14), "drive") == 3, true);
        checkAC("Shift hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(14), "shift") == 4, true);
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(14), "cycle") == 90, true);

        //        4 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(13));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("060000AM", "110000AM", "Dr");
        logsPage.addLastStatus("010000PM", "060000PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Drive hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(13), "drive") == 3, true);
        checkAC("Shift hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(13), "shift") == 4, true);
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(13), "cycle") == 80, true);

        //        5 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(12));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("060000AM", "110000AM", "Dr");
        logsPage.addLastStatus("010000PM", "060000PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Drive hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(12), "drive") == 3, true);
        checkAC("Shift hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(12), "shift") == 4, true);
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(12), "cycle") == 70, true);

        //        6 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(11));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("060000AM", "110000AM", "Dr");
        logsPage.addLastStatus("010000PM", "060000PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Drive hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(11), "drive") == 3, true);
        checkAC("Shift hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(11), "shift") == 4, true);
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(11), "cycle") == 60, true);

        //        7 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(10));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("060000AM", "110000AM", "Dr");
        logsPage.addLastStatus("010000PM", "060000PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Drive hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(10), "drive") == 3, true);
        checkAC("Shift hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(10), "shift") == 4, true);
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(10), "cycle") == 50, true);

        //        8 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(9));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("060000AM", "110000AM", "Dr");
        logsPage.addLastStatus("010000PM", "060000PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Drive hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(9), "drive") == 3, true);
        checkAC("Shift hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(9), "shift") == 4, true);
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(9), "cycle") == 40, true);

        //        9 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(8));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("060000AM", "110000AM", "Dr");
        logsPage.addLastStatus("010000PM", "060000PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Drive hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(8), "drive") == 3, true);
        checkAC("Shift hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(8), "shift") == 4, true);
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(8), "cycle") == 30, true);

        //        10 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(7));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("060000AM", "110000AM", "Dr");
        logsPage.addLastStatus("010000PM", "060000PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Drive hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(7), "drive") == 3, true);
        checkAC("Shift hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(7), "shift") == 4, true);
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(7), "cycle") == 20, true);

        //        11 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(6));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("060000AM", "090000AM", "Dr");
        logsPage.addLastStatus("040000PM", "060000PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Drive hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(6), "drive") == 4, true);
        checkAC("Shift hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(6), "shift") == 4, true);
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(6), "cycle") == 15, true);

        //        12 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(5));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("060000AM", "090000AM", "Dr");
        logsPage.addLastStatus("040000PM", "060000PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Drive hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(5), "drive") == 4, true);
        checkAC("Shift hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(5), "shift") == 4, true);
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(5), "cycle") == 10, true);

        //        13 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(4));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("060000AM", "090000AM", "Dr");
        logsPage.addLastStatus("040000PM", "060000PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Drive hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(4), "drive") == 4, true);
        checkAC("Shift hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(4), "shift") == 4, true);
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(4), "cycle") == 5, true);

        //        14 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(3));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("060000AM", "090000AM", "Dr");
        logsPage.addLastStatus("040000PM", "060000PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Drive hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(3), "drive") == 4, true);
        checkAC("Shift hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(3), "shift") == 4, true);
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(3), "cycle") == 0, true);

    }

    @Test
    public void texas70hr_7days() throws SQLException {
        String userId = utilsForDB.getUserIdByEmail(login);

        logsPage.cleanStatusesViolation(userId);
        logsPage.setCycle(userId, Texas70hr_7days);

        loginPage.userValidLogIn(login, pass);

        //        1 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(9));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("060000AM", "110000AM", "Dr");
        logsPage.addLastStatus("010000PM", "060000PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Drive hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(9), "drive") == 2, true);
        checkAC("Shift hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(9), "shift") == 3, true);
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(9), "cycle") == 60, true);

        //        2 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(8));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("060000AM", "110000AM", "Dr");
        logsPage.addLastStatus("010000PM", "060000PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Drive hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(8), "drive") == 2, true);
        checkAC("Shift hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(8), "shift") == 3, true);
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(8), "cycle") == 50, true);

        //        3 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(7));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("060000AM", "110000AM", "Dr");
        logsPage.addLastStatus("010000PM", "060000PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Drive hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(7), "drive") == 2, true);
        checkAC("Shift hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(7), "shift") == 3, true);
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(7), "cycle") == 40, true);

        //        4 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(6));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("060000AM", "110000AM", "Dr");
        logsPage.addLastStatus("010000PM", "060000PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Drive hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(6), "drive") == 2, true);
        checkAC("Shift hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(6), "shift") == 3, true);
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(6), "cycle") == 30, true);

        //        5 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(5));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("060000AM", "110000AM", "Dr");
        logsPage.addLastStatus("010000PM", "060000PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Drive hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(5), "drive") == 2, true);
        checkAC("Shift hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(5), "shift") == 3, true);
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(5), "cycle") == 20, true);

        //        6 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(4));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("060000AM", "110000AM", "Dr");
        logsPage.addLastStatus("010000PM", "060000PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Drive hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(4), "drive") == 2, true);
        checkAC("Shift hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(4), "shift") == 3, true);
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(4), "cycle") == 10, true);

        //        7 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(3));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("060000AM", "110000AM", "Dr");
        logsPage.addLastStatus("010000PM", "060000PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Drive hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(3), "drive") == 2, true);
        checkAC("Shift hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(3), "shift") == 3, true);
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(3), "cycle") == 0, true);

    }

    @Test
    public void california_80hr_7days() throws SQLException {
        String userId = utilsForDB.getUserIdByEmail(login);

        logsPage.cleanStatusesViolation(userId);
        logsPage.setCycle(userId, California_80hr_7days);

        loginPage.userValidLogIn(login, pass);

        //        1 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(9));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("060000AM", "110000AM", "Dr");
        logsPage.addLastStatus("010000PM", "070000PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        System.out.println(logsPage.getStatusData(userId, dateWithMinusDay(9), "drive"));
        checkAC("Drive hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(9), "drive") == 3, true);
        checkAC("Shift hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(9), "shift") == 3, true);
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(9), "cycle") == 69, true);
        checkAC("Shift work hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(9), "shiftWork") == 7, true);
        checkAC("Restart cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(9), "restart34") == 36, true);

        //        2 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(8));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("060000AM", "110000AM", "Dr");//5
        logsPage.addLastStatus("010000PM", "070000PM", "Dr");//6
        int driveHours = 11;
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Drive hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(8), "drive") == 3, true);
        checkAC("Shift hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(8), "shift") == 3, true);
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(8), "cycle") == 58, true);

        //        3 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(7));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("060000AM", "110000AM", "Dr");
        logsPage.addLastStatus("010000PM", "070000PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Drive hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(7), "drive") == 3, true);
        checkAC("Shift hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(7), "shift") == 3, true);
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(7), "cycle") == 47, true);

        //        4 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(6));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("060000AM", "110000AM", "Dr");
        logsPage.addLastStatus("010000PM", "070000PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Drive hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(6), "drive") == 3, true);
        checkAC("Shift hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(6), "shift") == 3, true);
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(6), "cycle") == 36, true);

        //        5 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(5));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("050000AM", "110000AM", "Dr");
        logsPage.addLastStatus("010000PM", "070000PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Drive hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(5), "drive") == 2, true);
        checkAC("Shift hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(5), "shift") == 2, true);
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(5), "cycle") == 24, true);

        //        6 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(4));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("050000AM", "110000AM", "Dr");
        logsPage.addLastStatus("010000PM", "070000PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Drive hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(4), "drive") == 2, true);
        checkAC("Shift hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(4), "shift") == 2, true);
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(4), "cycle") == 12, true);

        //        7 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(3));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("050000AM", "110000AM", "Dr");
        logsPage.addLastStatus("010000PM", "070000PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Drive hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(3), "drive") == 2, true);
        checkAC("Shift hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(3), "shift") == 2, true);
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(3), "cycle") == 0, true);

    }

    @Test
    public void canadaNorth_60_80_7() throws SQLException {
        String userId = utilsForDB.getUserIdByEmail(login);

        logsPage.cleanStatusesViolation(userId);
        logsPage.setCycle(userId, CanadaNorth_60_80_7);

        loginPage.userValidLogIn(login, pass);

        //        1 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(9));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("060000AM", "110000AM", "Dr");
        logsPage.addLastStatus("010000PM", "070000PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        System.out.println(logsPage.getStatusData(userId, dateWithMinusDay(9), "drive"));
        checkAC("Drive hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(9), "drive") == 4, true);
        checkAC("Shift hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(9), "shift") == 7, true);
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(9), "cycle") == 69, true);

        //        2 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(8));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("060000AM", "110000AM", "Dr");
        logsPage.addLastStatus("010000PM", "070000PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Drive hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(8), "drive") == 4, true);
        checkAC("Shift hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(8), "shift") == 7, true);
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(8), "cycle") == 58, true);

        //        3 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(7));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("060000AM", "110000AM", "Dr");
        logsPage.addLastStatus("010000PM", "070000PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Drive hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(7), "drive") == 4, true);
        checkAC("Shift hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(7), "shift") == 7, true);
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(7), "cycle") == 47, true);

        //        4 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(6));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("060000AM", "110000AM", "Dr");
        logsPage.addLastStatus("010000PM", "070000PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Drive hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(6), "drive") == 4, true);
        checkAC("Shift hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(6), "shift") == 7, true);
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(6), "cycle") == 36, true);

        //        5 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(5));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("050000AM", "110000AM", "Dr");
        logsPage.addLastStatus("010000PM", "070000PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Drive hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(5), "drive") == 3, true);
        checkAC("Shift hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(5), "shift") == 6, true);
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(5), "cycle") == 24, true);

        //        6 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(4));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("050000AM", "110000AM", "Dr");
        logsPage.addLastStatus("010000PM", "070000PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Drive hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(4), "drive") == 3, true);
        checkAC("Shift hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(4), "shift") == 6, true);
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(4), "cycle") == 12, true);

        //        7 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(3));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("050000AM", "110000AM", "Dr");
        logsPage.addLastStatus("010000PM", "070000PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Drive hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(3), "drive") == 3, true);
        checkAC("Shift hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(3), "shift") == 6, true);
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(3), "cycle") == 0, true);

    }

    @Test
    public void other() throws SQLException {

    }
}
