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
                { Property },
                { Agriculture },
                { Passenger },
                { OilGas },
                { ShortHaul }
        });
    }




    @Test
    public void usa_60hr_7days() throws Exception {

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

    @Test
    public void usa_70hr_8days() throws SQLException {
        String userId = utilsForDB.getUserIdByEmail(login);

        logsPage.cleanStatusesAndViolation(userId);
        logsPage.setCycle(userId, USA_70hr_8days);
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
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(9), "cycle") == logsPage.getCycleHours(USA_70hr_8days, cargoType, cycleHours), true);


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
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(8), "cycle") == logsPage.getCycleHours(USA_70hr_8days, cargoType, cycleHours2), true);

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
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(7), "cycle") == logsPage.getCycleHours(USA_70hr_8days, cargoType, cycleHours3), true);

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
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(6), "cycle") == logsPage.getCycleHours(USA_70hr_8days, cargoType, cycleHours4), true);

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
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(5), "cycle") == logsPage.getCycleHours(USA_70hr_8days, cargoType, cycleHours5), true);

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
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(4), "cycle") == logsPage.getCycleHours(USA_70hr_8days, cargoType, cycleHours6), true);

        //        7 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(3));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("06:00:00 AM", "10:00:00 AM", "Dr");//4
        logsPage.addStatus("10:00:00 AM", "11:00:00 AM", "On");//1
        logsPage.addStatus("01:00:00 PM", "04:00:00 PM", "Dr");//3
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        int cycleHours7 = cycleHours6 + (logsPage.countHoursStatuses(Arrays.asList("06:00:00 AM/10:00:00 AM", "01:00:00 PM/04:00:00 PM", "10:00:00 AM/11:00:00 AM")));
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(3), "cycle") == logsPage.getCycleHours(USA_70hr_8days, cargoType, cycleHours7), true);

        //        8 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(2));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("06:00:00 AM", "10:00:00 AM", "Dr");
        logsPage.addStatus("10:00:00 AM", "11:00:00 AM", "On");
        logsPage.addStatus("01:00:00 PM", "04:00:00 PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        int cycleHours8 = cycleHours7 + (logsPage.countHoursStatuses(Arrays.asList("06:00:00 AM/10:00:00 AM", "01:00:00 PM/04:00:00 PM", "10:00:00 AM/11:00:00 AM")));
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(2), "cycle") == logsPage.getCycleHours(USA_70hr_8days, cargoType, cycleHours8), true);
        checkAC("Restart hours are incorrect", logsPage.getStatusData(userId, dateWithMinusDay(2), "restart34") == logsPage.getRestart34(USA_70hr_8days, cargoType), true);
        checkAC("Violation exist", logsPage.checkAlertsExist(userId, dateWithMinusDay(2)), false);

        // 8 day with violation
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(2));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("04:00:00 PM", "04:01:00 PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Overworked Cycle Violation(7) failed", logsPage.checkAlertsId(userId, dateWithMinusDay(2), OverworkedCycle), true);

    }

    @Test
    public void alaska_70hr_7days() throws SQLException {
        String userId = utilsForDB.getUserIdByEmail(login);

        logsPage.cleanStatusesAndViolation(userId);
        logsPage.setCycle(userId, Alaska_70hr_7days);
        utilsForDB.setCargoTypeId(userId, cargoType);

        loginPage.userValidLogIn(login, pass);

        //        1 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(9));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("06:00:00 AM", "10:00:00 AM", "Dr");
        logsPage.addStatus("10:00:00 AM", "11:00:00 AM", "On");
        logsPage.addStatus("01:00:00 PM", "06:00:00 PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        int cycleHours = logsPage.countHoursStatuses(Arrays.asList("06:00:00 AM/10:00:00 AM", "10:00:00 AM/11:00:00 AM", "01:00:00 PM/06:00:00 PM"));
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(9), "cycle") == logsPage.getCycleHours(Alaska_70hr_7days, cargoType, cycleHours), true);

        //        2 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(8));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("06:00:00 AM", "10:00:00 AM", "Dr");
        logsPage.addStatus("10:00:00 AM", "11:00:00 AM", "On");
        logsPage.addStatus("01:00:00 PM", "06:00:00 PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        int cycleHours2 = cycleHours + logsPage.countHoursStatuses(Arrays.asList("06:00:00 AM/10:00:00 AM", "10:00:00 AM/11:00:00 AM", "01:00:00 PM/06:00:00 PM"));
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(8), "cycle") == logsPage.getCycleHours(Alaska_70hr_7days, cargoType, cycleHours2), true);

        //        3 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(7));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("06:00:00 AM", "10:00:00 AM", "Dr");
        logsPage.addStatus("10:00:00 AM", "11:00:00 AM", "On");
        logsPage.addStatus("01:00:00 PM", "06:00:00 PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        int cycleHours3 = cycleHours2 + logsPage.countHoursStatuses(Arrays.asList("06:00:00 AM/10:00:00 AM", "10:00:00 AM/11:00:00 AM", "01:00:00 PM/06:00:00 PM"));
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(7), "cycle") == logsPage.getCycleHours(Alaska_70hr_7days, cargoType, cycleHours3), true);

        //        4 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(6));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("06:00:00 AM", "10:00:00 AM", "Dr");
        logsPage.addStatus("10:00:00 AM", "11:00:00 AM", "On");
        logsPage.addStatus("01:00:00 PM", "06:00:00 PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        int cycleHours4 = cycleHours3 + logsPage.countHoursStatuses(Arrays.asList("06:00:00 AM/10:00:00 AM", "10:00:00 AM/11:00:00 AM", "01:00:00 PM/06:00:00 PM"));
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(6), "cycle") == logsPage.getCycleHours(Alaska_70hr_7days, cargoType, cycleHours4), true);

        //        5 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(5));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("06:00:00 AM", "10:00:00 AM", "Dr");
        logsPage.addStatus("10:00:00 AM", "11:00:00 AM", "On");
        logsPage.addStatus("01:00:00 PM", "06:00:00 PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        int cycleHours5 = cycleHours4 + logsPage.countHoursStatuses(Arrays.asList("06:00:00 AM/10:00:00 AM", "10:00:00 AM/11:00:00 AM", "01:00:00 PM/06:00:00 PM"));
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(5), "cycle") == logsPage.getCycleHours(Alaska_70hr_7days, cargoType, cycleHours5), true);

        //        6 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(4));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("06:00:00 AM", "10:00:00 AM", "Dr");
        logsPage.addStatus("10:00:00 AM", "11:00:00 AM", "On");
        logsPage.addStatus("01:00:00 PM", "06:00:00 PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        int cycleHours6 = cycleHours5 + logsPage.countHoursStatuses(Arrays.asList("06:00:00 AM/10:00:00 AM", "10:00:00 AM/11:00:00 AM", "01:00:00 PM/06:00:00 PM"));
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(4), "cycle") == logsPage.getCycleHours(Alaska_70hr_7days, cargoType, cycleHours6), true);

        //        7 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(3));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("06:00:00 AM", "10:00:00 AM", "Dr");//4
        logsPage.addStatus("10:00:00 AM", "11:00:00 AM", "On");//1
        logsPage.addStatus("01:00:00 PM", "06:00:00 PM", "Dr");//5
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        int cycleHours7 = cycleHours6 + logsPage.countHoursStatuses(Arrays.asList("06:00:00 AM/10:00:00 AM", "10:00:00 AM/11:00:00 AM", "01:00:00 PM/06:00:00 PM"));
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(3), "cycle") == logsPage.getCycleHours(Alaska_70hr_7days, cargoType, cycleHours7), true);
        checkAC("Restart hours are incorrect", logsPage.getStatusData(userId, dateWithMinusDay(3), "restart34") == logsPage.getRestart34(Alaska_70hr_7days, cargoType), true);
        checkAC("Violation exist", logsPage.checkAlertsExist(userId, dateWithMinusDay(3)), false);

        // 7 day with violation
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(3));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("06:00:00 PM", "06:01:00 PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Overworked Cycle Violation(7) failed", logsPage.checkAlertsId(userId, dateWithMinusDay(3), OverworkedCycle), true);

    }

    @Test
    public void alaska_80hr_8days() throws SQLException {
        String userId = utilsForDB.getUserIdByEmail(login);

        logsPage.cleanStatusesAndViolation(userId);
        logsPage.setCycle(userId, Alaska_80hr_8days);
        utilsForDB.setCargoTypeId(userId, cargoType);

        loginPage.userValidLogIn(login, pass);

        //        1 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(9));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("06:00:00 AM", "10:00:00 AM", "Dr");//4
        logsPage.addStatus("10:00:00 AM", "11:00:00 AM", "On");//1
        logsPage.addStatus("01:00:00 PM", "06:00:00 PM", "Dr");//5
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        int cycleHours = logsPage.countHoursStatuses(Arrays.asList("06:00:00 AM/10:00:00 AM", "10:00:00 AM/11:00:00 AM", "01:00:00 PM/06:00:00 PM"));
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(9), "cycle") == logsPage.getCycleHours(Alaska_80hr_8days, cargoType, cycleHours), true);

        //        2 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(8));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("06:00:00 AM", "10:00:00 AM", "Dr");//4
        logsPage.addStatus("10:00:00 AM", "11:00:00 AM", "On");//1
        logsPage.addStatus("01:00:00 PM", "06:00:00 PM", "Dr");//5
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        int cycleHours2 = cycleHours + logsPage.countHoursStatuses(Arrays.asList("06:00:00 AM/10:00:00 AM", "10:00:00 AM/11:00:00 AM", "01:00:00 PM/06:00:00 PM"));
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(8), "cycle") == logsPage.getCycleHours(Alaska_80hr_8days, cargoType, cycleHours2), true);

        //        3 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(7));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("06:00:00 AM", "10:00:00 AM", "Dr");//4
        logsPage.addStatus("10:00:00 AM", "11:00:00 AM", "On");//1
        logsPage.addStatus("01:00:00 PM", "06:00:00 PM", "Dr");//5
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        int cycleHours3 = cycleHours2 + logsPage.countHoursStatuses(Arrays.asList("06:00:00 AM/10:00:00 AM", "10:00:00 AM/11:00:00 AM", "01:00:00 PM/06:00:00 PM"));
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(7), "cycle") == logsPage.getCycleHours(Alaska_80hr_8days, cargoType, cycleHours3), true);

        //        4 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(6));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("06:00:00 AM", "10:00:00 AM", "Dr");//4
        logsPage.addStatus("10:00:00 AM", "11:00:00 AM", "On");//1
        logsPage.addStatus("01:00:00 PM", "06:00:00 PM", "Dr");//5
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        int cycleHours4 = cycleHours3 + logsPage.countHoursStatuses(Arrays.asList("06:00:00 AM/10:00:00 AM", "10:00:00 AM/11:00:00 AM", "01:00:00 PM/06:00:00 PM"));
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(6), "cycle") == logsPage.getCycleHours(Alaska_80hr_8days, cargoType, cycleHours4), true);

        //        5 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(5));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("06:00:00 AM", "10:00:00 AM", "Dr");//4
        logsPage.addStatus("10:00:00 AM", "11:00:00 AM", "On");//1
        logsPage.addStatus("01:00:00 PM", "06:00:00 PM", "Dr");//5
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        int cycleHours5 = cycleHours4 + logsPage.countHoursStatuses(Arrays.asList("06:00:00 AM/10:00:00 AM", "10:00:00 AM/11:00:00 AM", "01:00:00 PM/06:00:00 PM"));
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(5), "cycle") == logsPage.getCycleHours(Alaska_80hr_8days, cargoType, cycleHours5), true);

        //        6 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(4));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("06:00:00 AM", "10:00:00 AM", "Dr");//4
        logsPage.addStatus("10:00:00 AM", "11:00:00 AM", "On");//1
        logsPage.addStatus("01:00:00 PM", "06:00:00 PM", "Dr");//5
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        int cycleHours6 = cycleHours5 + logsPage.countHoursStatuses(Arrays.asList("06:00:00 AM/10:00:00 AM", "10:00:00 AM/11:00:00 AM", "01:00:00 PM/06:00:00 PM"));
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(4), "cycle") == logsPage.getCycleHours(Alaska_80hr_8days, cargoType, cycleHours6), true);

        //        7 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(3));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("06:00:00 AM", "10:00:00 AM", "Dr");//4
        logsPage.addStatus("10:00:00 AM", "11:00:00 AM", "On");//1
        logsPage.addStatus("01:00:00 PM", "06:00:00 PM", "Dr");//5
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        int cycleHours7 = cycleHours6 + logsPage.countHoursStatuses(Arrays.asList("06:00:00 AM/10:00:00 AM", "10:00:00 AM/11:00:00 AM", "01:00:00 PM/06:00:00 PM"));
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(3), "cycle") == logsPage.getCycleHours(Alaska_80hr_8days, cargoType, cycleHours7), true);

        //        8 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(2));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("06:00:00 AM", "10:00:00 AM", "Dr");//4
        logsPage.addStatus("10:00:00 AM", "11:00:00 AM", "On");//1
        logsPage.addStatus("01:00:00 PM", "06:00:00 PM", "Dr");//5
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        int cycleHours8 = cycleHours7 + logsPage.countHoursStatuses(Arrays.asList("06:00:00 AM/10:00:00 AM", "10:00:00 AM/11:00:00 AM", "01:00:00 PM/06:00:00 PM"));
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(2), "cycle") == logsPage.getCycleHours(Alaska_80hr_8days, cargoType, cycleHours8), true);
        checkAC("Restart hours are incorrect", logsPage.getStatusData(userId, dateWithMinusDay(2), "restart34") == logsPage.getRestart34(Alaska_80hr_8days, cargoType), true);
        checkAC("Violation exist", logsPage.checkAlertsExist(userId, dateWithMinusDay(2)), false);

        // 8 day with violation
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(2));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("06:00:00 PM", "06:01:00 PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Overworked Cycle Violation(7) failed", logsPage.checkAlertsId(userId, dateWithMinusDay(2), OverworkedCycle), true);


    }

    @Test
    public void canada_70hr_7days() throws SQLException {
        String userId = utilsForDB.getUserIdByEmail(login);

        logsPage.cleanStatusesAndViolation(userId);
        logsPage.setCycle(userId, Canada_70hr_7days);
        utilsForDB.setCargoTypeId(userId, cargoType);

        loginPage.userValidLogIn(login, pass);

        //        1 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(9));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("06:00:00 AM", "10:00:00 AM", "Dr");//4
        logsPage.addStatus("10:00:00 AM", "11:00:00 AM", "On");//1
        logsPage.addStatus("01:00:00 PM", "06:00:00 PM", "Dr");//5
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        int cycleHours = logsPage.countHoursStatuses(Arrays.asList("06:00:00 AM/10:00:00 AM", "10:00:00 AM/11:00:00 AM", "01:00:00 PM/06:00:00 PM"));
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(9), "cycle") == logsPage.getCycleHours(Canada_70hr_7days, cargoType, cycleHours), true);

        //        2 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(8));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("06:00:00 AM", "10:00:00 AM", "Dr");
        logsPage.addStatus("10:00:00 AM", "11:00:00 AM", "On");
        logsPage.addStatus("01:00:00 PM", "06:00:00 PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        int cycleHours2 = cycleHours + logsPage.countHoursStatuses(Arrays.asList("06:00:00 AM/10:00:00 AM", "10:00:00 AM/11:00:00 AM", "01:00:00 PM/06:00:00 PM"));
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(8), "cycle") == logsPage.getCycleHours(Canada_70hr_7days, cargoType, cycleHours2), true);

        //        3 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(7));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("06:00:00 AM", "10:00:00 AM", "Dr");
        logsPage.addStatus("10:00:00 AM", "11:00:00 AM", "On");
        logsPage.addStatus("01:00:00 PM", "06:00:00 PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        int cycleHours3 = cycleHours2 + logsPage.countHoursStatuses(Arrays.asList("06:00:00 AM/10:00:00 AM", "10:00:00 AM/11:00:00 AM", "01:00:00 PM/06:00:00 PM"));
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(7), "cycle") == logsPage.getCycleHours(Canada_70hr_7days, cargoType, cycleHours3), true);

        //        4 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(6));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("06:00:00 AM", "10:00:00 AM", "Dr");
        logsPage.addStatus("10:00:00 AM", "11:00:00 AM", "On");
        logsPage.addStatus("01:00:00 PM", "06:00:00 PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        int cycleHours4 = cycleHours3 + logsPage.countHoursStatuses(Arrays.asList("06:00:00 AM/10:00:00 AM", "10:00:00 AM/11:00:00 AM", "01:00:00 PM/06:00:00 PM"));
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(6), "cycle") == logsPage.getCycleHours(Canada_70hr_7days, cargoType, cycleHours4), true);

        //        5 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(5));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("06:00:00 AM", "10:00:00 AM", "Dr");
        logsPage.addStatus("10:00:00 AM", "11:00:00 AM", "On");
        logsPage.addStatus("01:00:00 PM", "06:00:00 PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        int cycleHours5 = cycleHours4 + logsPage.countHoursStatuses(Arrays.asList("06:00:00 AM/10:00:00 AM", "10:00:00 AM/11:00:00 AM", "01:00:00 PM/06:00:00 PM"));
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(5), "cycle") == logsPage.getCycleHours(Canada_70hr_7days, cargoType, cycleHours5), true);

        //        6 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(4));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("06:00:00 AM", "10:00:00 AM", "Dr");
        logsPage.addStatus("10:00:00 AM", "11:00:00 AM", "On");
        logsPage.addStatus("01:00:00 PM", "06:00:00 PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        int cycleHours6 = cycleHours5 + logsPage.countHoursStatuses(Arrays.asList("06:00:00 AM/10:00:00 AM", "10:00:00 AM/11:00:00 AM", "01:00:00 PM/06:00:00 PM"));
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(4), "cycle") == logsPage.getCycleHours(Canada_70hr_7days, cargoType, cycleHours6), true);

        //        7 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(3));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("06:00:00 AM", "10:00:00 AM", "Dr");
        logsPage.addStatus("10:00:00 AM", "11:00:00 AM", "On");
        logsPage.addStatus("01:00:00 PM", "06:00:00 PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        int cycleHours7 = cycleHours6 + logsPage.countHoursStatuses(Arrays.asList("06:00:00 AM/10:00:00 AM", "10:00:00 AM/11:00:00 AM", "01:00:00 PM/06:00:00 PM"));
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(3), "cycle") == logsPage.getCycleHours(Canada_70hr_7days, cargoType, cycleHours7), true);
        checkAC("Restart hours are incorrect", logsPage.getStatusData(userId, dateWithMinusDay(3), "restart34") == logsPage.getRestart34(Canada_70hr_7days, cargoType), true);
        checkAC("Violation exist", logsPage.checkAlertsExist(userId, dateWithMinusDay(3)), false);

        // 7 day with violation
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(3));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("06:00:00 PM", "06:01:00 PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Overworked Cycle Violation(7) failed", logsPage.checkAlertsId(userId, dateWithMinusDay(3), OverworkedCycle), true);

    }

    @Test
    public void canada_120hr_14days() throws SQLException {
        String userId = utilsForDB.getUserIdByEmail(login);

        logsPage.cleanStatusesAndViolation(userId);
        logsPage.setCycle(userId, Canada_120hr_14days);
        utilsForDB.setCargoTypeId(userId, cargoType);

        loginPage.userValidLogIn(login, pass);

        //        1 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(16));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("06:00:00 AM", "10:00:00 AM", "Dr");//4
        logsPage.addStatus("10:00:00 AM", "11:00:00 AM", "On");//1
        logsPage.addStatus("01:00:00 PM", "06:00:00 PM", "Dr");//5
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        int cycleHours = logsPage.countHoursStatuses(Arrays.asList("06:00:00 AM/10:00:00 AM", "10:00:00 AM/11:00:00 AM", "01:00:00 PM/06:00:00 PM"));
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(16), "cycle") == logsPage.getCycleHours(Canada_120hr_14days, cargoType, cycleHours), true);

        //        2 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(15));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("06:00:00 AM", "10:00:00 AM", "Dr");//4
        logsPage.addStatus("10:00:00 AM", "11:00:00 AM", "On");//1
        logsPage.addStatus("01:00:00 PM", "06:00:00 PM", "Dr");//5
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        int cycleHours2 = cycleHours + logsPage.countHoursStatuses(Arrays.asList("06:00:00 AM/10:00:00 AM", "10:00:00 AM/11:00:00 AM", "01:00:00 PM/06:00:00 PM"));
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(15), "cycle") == logsPage.getCycleHours(Canada_120hr_14days, cargoType, cycleHours2), true);

        //        3 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(14));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("06:00:00 AM", "10:00:00 AM", "Dr");//4
        logsPage.addStatus("10:00:00 AM", "11:00:00 AM", "On");//1
        logsPage.addStatus("01:00:00 PM", "06:00:00 PM", "Dr");//5
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        int cycleHours3 = cycleHours2 + logsPage.countHoursStatuses(Arrays.asList("06:00:00 AM/10:00:00 AM", "10:00:00 AM/11:00:00 AM", "01:00:00 PM/06:00:00 PM"));
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(14), "cycle") == logsPage.getCycleHours(Canada_120hr_14days, cargoType, cycleHours3), true);

        //        4 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(13));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("06:00:00 AM", "10:00:00 AM", "Dr");//4
        logsPage.addStatus("10:00:00 AM", "11:00:00 AM", "On");//1
        logsPage.addStatus("01:00:00 PM", "06:00:00 PM", "Dr");//5
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        int cycleHours4 = cycleHours3 + logsPage.countHoursStatuses(Arrays.asList("06:00:00 AM/10:00:00 AM", "10:00:00 AM/11:00:00 AM", "01:00:00 PM/06:00:00 PM"));
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(13), "cycle") == logsPage.getCycleHours(Canada_120hr_14days, cargoType, cycleHours4), true);

        //        5 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(12));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("06:00:00 AM", "10:00:00 AM", "Dr");//4
        logsPage.addStatus("10:00:00 AM", "11:00:00 AM", "On");//1
        logsPage.addStatus("01:00:00 PM", "06:00:00 PM", "Dr");//5
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        int cycleHours5 = cycleHours4 + logsPage.countHoursStatuses(Arrays.asList("06:00:00 AM/10:00:00 AM", "10:00:00 AM/11:00:00 AM", "01:00:00 PM/06:00:00 PM"));
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(12), "cycle") == logsPage.getCycleHours(Canada_120hr_14days, cargoType, cycleHours5), true);

        //        6 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(11));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("06:00:00 AM", "10:00:00 AM", "Dr");//4
        logsPage.addStatus("10:00:00 AM", "11:00:00 AM", "On");//1
        logsPage.addStatus("01:00:00 PM", "06:00:00 PM", "Dr");//5
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        int cycleHours6 = cycleHours5 + logsPage.countHoursStatuses(Arrays.asList("06:00:00 AM/10:00:00 AM", "10:00:00 AM/11:00:00 AM", "01:00:00 PM/06:00:00 PM"));
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(11), "cycle") == logsPage.getCycleHours(Canada_120hr_14days, cargoType, cycleHours6), true);

        //        7 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(10));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("06:00:00 AM", "10:00:00 AM", "Dr");//4
        logsPage.addStatus("10:00:00 AM", "11:00:00 AM", "On");//1
        logsPage.addStatus("01:00:00 PM", "06:00:00 PM", "Dr");//5
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        int cycleHours7 = cycleHours6 + logsPage.countHoursStatuses(Arrays.asList("06:00:00 AM/10:00:00 AM", "10:00:00 AM/11:00:00 AM", "01:00:00 PM/06:00:00 PM"));
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(10), "cycle") == logsPage.getCycleHours(Canada_120hr_14days, cargoType, cycleHours7), true);

        //        8 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(9));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("06:00:00 AM", "10:00:00 AM", "Dr");//4
        logsPage.addStatus("10:00:00 AM", "11:00:00 AM", "On");//1
        logsPage.addStatus("01:00:00 PM", "06:00:00 PM", "Dr");//5
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        int cycleHours8 = cycleHours7 + logsPage.countHoursStatuses(Arrays.asList("06:00:00 AM/10:00:00 AM", "10:00:00 AM/11:00:00 AM", "01:00:00 PM/06:00:00 PM"));
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(9), "cycle") == logsPage.getCycleHours(Canada_120hr_14days, cargoType, cycleHours8), true);

        //        9 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(8));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("06:00:00 AM", "10:00:00 AM", "Dr");//4
        logsPage.addStatus("10:00:00 AM", "11:00:00 AM", "On");//1
        logsPage.addStatus("01:00:00 PM", "06:00:00 PM", "Dr");//5
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        int cycleHours9 = cycleHours8 + logsPage.countHoursStatuses(Arrays.asList("06:00:00 AM/10:00:00 AM", "10:00:00 AM/11:00:00 AM", "01:00:00 PM/06:00:00 PM"));
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(8), "cycle") == logsPage.getCycleHours(Canada_120hr_14days, cargoType, cycleHours9), true);

        //        10 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(7));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("06:00:00 AM", "10:00:00 AM", "Dr");//4
        logsPage.addStatus("10:00:00 AM", "11:00:00 AM", "On");//1
        logsPage.addStatus("01:00:00 PM", "06:00:00 PM", "Dr");//5
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        int cycleHours10 = cycleHours9 + logsPage.countHoursStatuses(Arrays.asList("06:00:00 AM/10:00:00 AM", "10:00:00 AM/11:00:00 AM", "01:00:00 PM/06:00:00 PM"));
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(7), "cycle") == logsPage.getCycleHours(Canada_120hr_14days, cargoType, cycleHours10), true);

        //        11 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(6));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("08:00:00 AM", "10:00:00 AM", "Dr");//2
        logsPage.addStatus("10:00:00 AM", "11:00:00 AM", "On");//1
        logsPage.addStatus("04:00:00 PM", "06:00:00 PM", "Dr");//2
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        int cycleHours11 = cycleHours10 + logsPage.countHoursStatuses(Arrays.asList("08:00:00 AM/10:00:00 AM", "10:00:00 AM/11:00:00 AM", "04:00:00 PM/06:00:00 PM"));
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(6), "cycle") == logsPage.getCycleHours(Canada_120hr_14days, cargoType, cycleHours11), true);

        //        12 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(5));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("08:00:00 AM", "10:00:00 AM", "Dr");//2
        logsPage.addStatus("10:00:00 AM", "11:00:00 AM", "On");//1
        logsPage.addStatus("04:00:00 PM", "06:00:00 PM", "Dr");//2
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        int cycleHours12 = cycleHours11 + logsPage.countHoursStatuses(Arrays.asList("08:00:00 AM/10:00:00 AM", "10:00:00 AM/11:00:00 AM", "04:00:00 PM/06:00:00 PM"));
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(5), "cycle") == logsPage.getCycleHours(Canada_120hr_14days, cargoType, cycleHours12), true);

        //        13 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(4));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("08:00:00 AM", "10:00:00 AM", "Dr");//2
        logsPage.addStatus("10:00:00 AM", "11:00:00 AM", "On");//1
        logsPage.addStatus("04:00:00 PM", "06:00:00 PM", "Dr");//2
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        int cycleHours13 = cycleHours12 + logsPage.countHoursStatuses(Arrays.asList("08:00:00 AM/10:00:00 AM", "10:00:00 AM/11:00:00 AM", "04:00:00 PM/06:00:00 PM"));
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(4), "cycle") == logsPage.getCycleHours(Canada_120hr_14days, cargoType, cycleHours13), true);

        //        14 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(3));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("08:00:00 AM", "10:00:00 AM", "Dr");//2
        logsPage.addStatus("10:00:00 AM", "11:00:00 AM", "On");//1
        logsPage.addStatus("04:00:00 PM", "06:00:00 PM", "Dr");//2
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        int cycleHours14 = cycleHours13 + logsPage.countHoursStatuses(Arrays.asList("08:00:00 AM/10:00:00 AM", "10:00:00 AM/11:00:00 AM", "04:00:00 PM/06:00:00 PM"));
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(3), "cycle") == logsPage.getCycleHours(Canada_120hr_14days, cargoType, cycleHours14), true);
        checkAC("Restart hours are incorrect", logsPage.getStatusData(userId, dateWithMinusDay(3), "restart34") == logsPage.getRestart34(Canada_120hr_14days, cargoType), true);
        checkAC("Violation exist", logsPage.checkAlertsExist(userId, dateWithMinusDay(3)), false);

        // 14 day with violation
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(3));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("06:00:00 PM", "06:01:00 PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Overworked Cycle Violation(7) failed", logsPage.checkAlertsId(userId, dateWithMinusDay(3), OverworkedCycle), true);

    }

    @Test
    public void texas70hr_7days() throws SQLException {
        String userId = utilsForDB.getUserIdByEmail(login);

        logsPage.cleanStatusesAndViolation(userId);
        logsPage.setCycle(userId, Texas70hr_7days);
        utilsForDB.setCargoTypeId(userId, cargoType);

        loginPage.userValidLogIn(login, pass);

        //        1 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(9));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("06:00:00 AM", "10:00:00 AM", "Dr");//4
        logsPage.addStatus("10:00:00 AM", "11:00:00 AM", "On");//1
        logsPage.addStatus("01:00:00 PM", "06:00:00 PM", "Dr");//5
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        int cycleHours = logsPage.countHoursStatuses(Arrays.asList("06:00:00 AM/10:00:00 AM", "10:00:00 AM/11:00:00 AM", "01:00:00 PM/06:00:00 PM"));
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(9), "cycle") == logsPage.getCycleHours(Texas70hr_7days, cargoType, cycleHours), true);

        //        2 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(8));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("06:00:00 AM", "10:00:00 AM", "Dr");//4
        logsPage.addStatus("10:00:00 AM", "11:00:00 AM", "On");//1
        logsPage.addStatus("01:00:00 PM", "06:00:00 PM", "Dr");//5
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        int cycleHours2 = cycleHours + logsPage.countHoursStatuses(Arrays.asList("06:00:00 AM/10:00:00 AM", "10:00:00 AM/11:00:00 AM", "01:00:00 PM/06:00:00 PM"));
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(8), "cycle") == logsPage.getCycleHours(Texas70hr_7days, cargoType, cycleHours2), true);

        //        3 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(7));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("06:00:00 AM", "10:00:00 AM", "Dr");//4
        logsPage.addStatus("10:00:00 AM", "11:00:00 AM", "On");//1
        logsPage.addStatus("01:00:00 PM", "06:00:00 PM", "Dr");//5
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        int cycleHours3 = cycleHours2 + logsPage.countHoursStatuses(Arrays.asList("06:00:00 AM/10:00:00 AM", "10:00:00 AM/11:00:00 AM", "01:00:00 PM/06:00:00 PM"));
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(7), "cycle") == logsPage.getCycleHours(Texas70hr_7days, cargoType, cycleHours3), true);

        //        4 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(6));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("06:00:00 AM", "10:00:00 AM", "Dr");//4
        logsPage.addStatus("10:00:00 AM", "11:00:00 AM", "On");//1
        logsPage.addStatus("01:00:00 PM", "06:00:00 PM", "Dr");//5
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        int cycleHours4 = cycleHours3 + logsPage.countHoursStatuses(Arrays.asList("06:00:00 AM/10:00:00 AM", "10:00:00 AM/11:00:00 AM", "01:00:00 PM/06:00:00 PM"));
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(6), "cycle") == logsPage.getCycleHours(Texas70hr_7days, cargoType, cycleHours4), true);

        //        5 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(5));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("06:00:00 AM", "10:00:00 AM", "Dr");//4
        logsPage.addStatus("10:00:00 AM", "11:00:00 AM", "On");//1
        logsPage.addStatus("01:00:00 PM", "06:00:00 PM", "Dr");//5
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        int cycleHours5 = cycleHours4 + logsPage.countHoursStatuses(Arrays.asList("06:00:00 AM/10:00:00 AM", "10:00:00 AM/11:00:00 AM", "01:00:00 PM/06:00:00 PM"));
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(5), "cycle") == logsPage.getCycleHours(Texas70hr_7days, cargoType, cycleHours5), true);

        //        6 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(4));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("06:00:00 AM", "10:00:00 AM", "Dr");//4
        logsPage.addStatus("10:00:00 AM", "11:00:00 AM", "On");//1
        logsPage.addStatus("01:00:00 PM", "06:00:00 PM", "Dr");//5
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        int cycleHours6 = cycleHours5 + logsPage.countHoursStatuses(Arrays.asList("06:00:00 AM/10:00:00 AM", "10:00:00 AM/11:00:00 AM", "01:00:00 PM/06:00:00 PM"));
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(4), "cycle") == logsPage.getCycleHours(Texas70hr_7days, cargoType, cycleHours6), true);

        //        7 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(3));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("06:00:00 AM", "10:00:00 AM", "Dr");//4
        logsPage.addStatus("10:00:00 AM", "11:00:00 AM", "On");//1
        logsPage.addStatus("01:00:00 PM", "06:00:00 PM", "Dr");//5
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        int cycleHours7 = cycleHours6 + logsPage.countHoursStatuses(Arrays.asList("06:00:00 AM/10:00:00 AM", "10:00:00 AM/11:00:00 AM", "01:00:00 PM/06:00:00 PM"));
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(3), "cycle") == logsPage.getCycleHours(Texas70hr_7days, cargoType, cycleHours7), true);
        checkAC("Restart hours are incorrect", logsPage.getStatusData(userId, dateWithMinusDay(3), "restart34") == logsPage.getRestart34(Texas70hr_7days, cargoType), true);
        checkAC("Violation exist", logsPage.checkAlertsExist(userId, dateWithMinusDay(3)), false);

        // 7 day with violation
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(3));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("06:00:00 PM", "06:01:00 PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Overworked Cycle Violation(7) failed", logsPage.checkAlertsId(userId, dateWithMinusDay(3), OverworkedCycle), true);

    }

    @Test
    public void california_80hr_7days() throws SQLException {
        String userId = utilsForDB.getUserIdByEmail(login);

        logsPage.cleanStatusesAndViolation(userId);
        logsPage.setCycle(userId, California_80hr_7days);
        utilsForDB.setCargoTypeId(userId, cargoType);

        loginPage.userValidLogIn(login, pass);

        //        1 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(9));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("06:00:00 AM", "11:00:00 AM", "Dr");//5
        logsPage.addStatus("11:00:00 AM", "11:30:00 AM", "On");//0.5
        logsPage.addStatus("01:00:00 PM", "07:00:00 PM", "Dr");//6
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        int cycleHours = logsPage.countHoursStatuses(Arrays.asList("06:00:00 AM/11:00:00 AM", "11:00:00 AM/11:30:00 AM", "01:00:00 PM/07:00:00 PM"));
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(9), "cycle") == logsPage.getCycleHours(California_80hr_7days, cargoType, cycleHours), true);

        //        2 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(8));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("06:00:00 AM", "11:00:00 AM", "Dr");
        logsPage.addStatus("11:00:00 AM", "11:30:00 AM", "On");
        logsPage.addStatus("01:00:00 PM", "07:00:00 PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        int cycleHours2 = cycleHours + logsPage.countHoursStatuses(Arrays.asList("06:00:00 AM/11:00:00 AM", "11:00:00 AM/11:30:00 AM", "01:00:00 PM/07:00:00 PM"));
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(8), "cycle") == logsPage.getCycleHours(California_80hr_7days, cargoType, cycleHours2), true);

        //        3 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(7));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("06:00:00 AM", "11:00:00 AM", "Dr");
        logsPage.addStatus("11:00:00 AM", "11:30:00 AM", "On");
        logsPage.addStatus("01:00:00 PM", "07:00:00 PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        int cycleHours3 = cycleHours2 + logsPage.countHoursStatuses(Arrays.asList("06:00:00 AM/11:00:00 AM", "11:00:00 AM/11:30:00 AM", "01:00:00 PM/07:00:00 PM"));
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(7), "cycle") == logsPage.getCycleHours(California_80hr_7days, cargoType, cycleHours3), true);

        //        4 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(6));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("06:00:00 AM", "11:00:00 AM", "Dr");
        logsPage.addStatus("11:00:00 AM", "11:30:00 AM", "On");
        logsPage.addStatus("01:00:00 PM", "07:00:00 PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        int cycleHours4 = cycleHours3 + logsPage.countHoursStatuses(Arrays.asList("06:00:00 AM/11:00:00 AM", "11:00:00 AM/11:30:00 AM", "01:00:00 PM/07:00:00 PM"));
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(6), "cycle") == logsPage.getCycleHours(California_80hr_7days, cargoType, cycleHours4), true);

        //        5 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(5));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("06:00:00 AM", "11:00:00 AM", "Dr");
        logsPage.addStatus("11:00:00 AM", "11:30:00 AM", "On");
        logsPage.addStatus("01:00:00 PM", "07:00:00 PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        int cycleHours5 = cycleHours4 + logsPage.countHoursStatuses(Arrays.asList("06:00:00 AM/11:00:00 AM", "11:00:00 AM/11:30:00 AM", "01:00:00 PM/07:00:00 PM"));
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(5), "cycle") == logsPage.getCycleHours(California_80hr_7days, cargoType, cycleHours5), true);

        //        6 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(4));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("06:00:00 AM", "11:00:00 AM", "Dr");
        logsPage.addStatus("11:00:00 AM", "11:30:00 AM", "On");
        logsPage.addStatus("01:00:00 PM", "07:00:00 PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        int cycleHours6 = cycleHours5 + logsPage.countHoursStatuses(Arrays.asList("06:00:00 AM/11:00:00 AM", "11:00:00 AM/11:30:00 AM", "01:00:00 PM/07:00:00 PM"));
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(4), "cycle") == logsPage.getCycleHours(California_80hr_7days, cargoType, cycleHours6), true);

        //        7 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(3));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("06:00:00 AM", "11:00:00 AM", "Dr");
        logsPage.addStatus("01:00:00 PM", "07:00:00 PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        int cycleHours7 = cycleHours6 + logsPage.countHoursStatuses(Arrays.asList("06:00:00 AM/11:00:00 AM", "01:00:00 PM/07:00:00 PM"));
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(3), "cycle") == logsPage.getCycleHours(California_80hr_7days, cargoType, cycleHours7), true);
        checkAC("Restart hours are incorrect", logsPage.getStatusData(userId, dateWithMinusDay(3), "restart34") == logsPage.getRestart34(California_80hr_7days, cargoType), true);
        checkAC("Violation exist", logsPage.checkAlertsExist(userId, dateWithMinusDay(3)), false);

        // 7 day with violation
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(3));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("07:00:00 PM", "07:01:00 PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Overworked Cycle Violation(7) failed", logsPage.checkAlertsId(userId, dateWithMinusDay(3), OverworkedCycle), true);

    }

    @Test
    public void canadaNorth_60_80_7() throws SQLException {
        String userId = utilsForDB.getUserIdByEmail(login);

        logsPage.cleanStatusesAndViolation(userId);
        logsPage.setCycle(userId, CanadaNorth_60_80_7);
        utilsForDB.setCargoTypeId(userId, cargoType);

        loginPage.userValidLogIn(login, pass);

        //        1 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(9));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("06:00:00 AM", "11:00:00 AM", "Dr");//5
        logsPage.addStatus("11:00:00 AM", "11:30:00 AM", "On");//0.5
        logsPage.addStatus("01:00:00 PM", "07:00:00 PM", "Dr");//6
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        int cycleHours = logsPage.countHoursStatuses(Arrays.asList("06:00:00 AM/11:00:00 AM", "11:00:00 AM/11:30:00 AM", "01:00:00 PM/07:00:00 PM"));
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(9), "cycle") == logsPage.getCycleHours(CanadaNorth_60_80_7, cargoType, cycleHours), true);

        //        2 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(8));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("06:00:00 AM", "11:00:00 AM", "Dr");//5
        logsPage.addStatus("11:00:00 AM", "11:30:00 AM", "On");//0.5
        logsPage.addStatus("01:00:00 PM", "07:00:00 PM", "Dr");//6
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        int cycleHours2 = cycleHours + logsPage.countHoursStatuses(Arrays.asList("06:00:00 AM/11:00:00 AM", "11:00:00 AM/11:30:00 AM", "01:00:00 PM/07:00:00 PM"));
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(8), "cycle") == logsPage.getCycleHours(CanadaNorth_60_80_7, cargoType, cycleHours2), true);

        //        3 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(7));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("06:00:00 AM", "11:00:00 AM", "Dr");//5
        logsPage.addStatus("11:00:00 AM", "11:30:00 AM", "On");//0.5
        logsPage.addStatus("01:00:00 PM", "07:00:00 PM", "Dr");//6
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        int cycleHours3 = cycleHours2 + logsPage.countHoursStatuses(Arrays.asList("06:00:00 AM/11:00:00 AM", "11:00:00 AM/11:30:00 AM", "01:00:00 PM/07:00:00 PM"));
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(7), "cycle") == logsPage.getCycleHours(CanadaNorth_60_80_7, cargoType, cycleHours3), true);

        //        4 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(6));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("06:00:00 AM", "11:00:00 AM", "Dr");//5
        logsPage.addStatus("11:00:00 AM", "11:30:00 AM", "On");//0.5
        logsPage.addStatus("01:00:00 PM", "07:00:00 PM", "Dr");//6
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        int cycleHours4 = cycleHours3 + logsPage.countHoursStatuses(Arrays.asList("06:00:00 AM/11:00:00 AM", "11:00:00 AM/11:30:00 AM", "01:00:00 PM/07:00:00 PM"));
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(6), "cycle") == logsPage.getCycleHours(CanadaNorth_60_80_7, cargoType, cycleHours4), true);

        //        5 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(5));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("06:00:00 AM", "11:00:00 AM", "Dr");//5
        logsPage.addStatus("11:00:00 AM", "11:30:00 AM", "On");//0.5
        logsPage.addStatus("01:00:00 PM", "07:00:00 PM", "Dr");//6
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        int cycleHours5 = cycleHours4 + logsPage.countHoursStatuses(Arrays.asList("06:00:00 AM/11:00:00 AM", "11:00:00 AM/11:30:00 AM", "01:00:00 PM/07:00:00 PM"));
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(5), "cycle") == logsPage.getCycleHours(CanadaNorth_60_80_7, cargoType, cycleHours5), true);

        //        6 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(4));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("06:00:00 AM", "11:00:00 AM", "Dr");//5
        logsPage.addStatus("11:00:00 AM", "11:30:00 AM", "On");//0.5
        logsPage.addStatus("01:00:00 PM", "07:00:00 PM", "Dr");//6
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        int cycleHours6 = cycleHours5 + logsPage.countHoursStatuses(Arrays.asList("06:00:00 AM/11:00:00 AM", "11:00:00 AM/11:30:00 AM", "01:00:00 PM/07:00:00 PM"));
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(4), "cycle") == logsPage.getCycleHours(CanadaNorth_60_80_7, cargoType, cycleHours6), true);

        //        7 day
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(3));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("06:00:00 AM", "11:00:00 AM", "Dr");//5
        logsPage.addStatus("01:00:00 PM", "07:00:00 PM", "Dr");//6
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        int cycleHours7 = cycleHours6 + logsPage.countHoursStatuses(Arrays.asList("06:00:00 AM/11:00:00 AM", "01:00:00 PM/07:00:00 PM"));
        checkAC("Cycle hours is incorrect", logsPage.getStatusData(userId, dateWithMinusDay(3), "cycle") == logsPage.getCycleHours(CanadaNorth_60_80_7, cargoType, cycleHours7), true);
        checkAC("Restart hours are incorrect", logsPage.getStatusData(userId, dateWithMinusDay(3), "restart34") == logsPage.getRestart34(CanadaNorth_60_80_7, cargoType), true);
        checkAC("Violation exist", logsPage.checkAlertsExist(userId, dateWithMinusDay(3)), false);

        // 7 day with violation
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(3));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("07:00:00 PM", "07:01:00 PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Overworked Cycle Violation(7) failed", logsPage.checkAlertsId(userId, dateWithMinusDay(3), OverworkedCycle), true);

    }

//    @Test
//    public void other() throws SQLException {
//
//    }
}
