package frankesteinTest.logbookTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import parentTest.ParentTest;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import static libs.DataForTests.*;
import static libs.Utils.dateWithMinusDay;

@RunWith(Parameterized.class)

public class Hour14DrivingWindow extends ParentTest {
    Map dataForValidLogIn = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "driverLogin");
    String login = dataForValidLogIn.get("login").toString();
    String pass = dataForValidLogIn.get("pass").toString();

    int cycleType;
    int cargoType;



    public Hour14DrivingWindow(int cycleType, int cargoType) throws IOException {
        this.cycleType = cycleType;
        this.cargoType = cargoType;
    }

    @Parameterized.Parameters(name = "Cycle: {0} and Cargo: {1}")
    public static Collection testData() {
        return Arrays.asList(new Object[][] {
                { USA_70hr_8days, Property }, { USA_70hr_8days, Agriculture }, { USA_70hr_8days, Passenger }, { USA_70hr_8days, OilGas }, { USA_70hr_8days, ShortHaul },

                { USA_60hr_7days, Property }, { USA_60hr_7days, Agriculture }, { USA_60hr_7days, Passenger }, { USA_60hr_7days, OilGas }, { USA_60hr_7days, ShortHaul },

                { Alaska_70hr_7days, Property }, { Alaska_70hr_7days, Agriculture }, { Alaska_70hr_7days, Passenger }, { Alaska_70hr_7days, OilGas }, { Alaska_70hr_7days, ShortHaul },

                { Alaska_80hr_8days, Property }, { Alaska_80hr_8days, Agriculture }, { Alaska_80hr_8days, Passenger }, { Alaska_80hr_8days, OilGas }, { Alaska_80hr_8days, ShortHaul },

                { Canada_70hr_7days, Property }, { Canada_70hr_7days, Agriculture }, { Canada_70hr_7days, Passenger }, { Canada_70hr_7days, OilGas }, { Canada_70hr_7days, ShortHaul },

                { Canada_120hr_14days, Property }, { Canada_120hr_14days, Agriculture }, { Canada_120hr_14days, Passenger }, { Canada_120hr_14days, OilGas }, { Canada_120hr_14days, ShortHaul },

                { Texas70hr_7days, Property }, { Texas70hr_7days, Agriculture }, { Texas70hr_7days, Passenger }, { Texas70hr_7days, OilGas }, { Texas70hr_7days, ShortHaul },

                { California_80hr_7days, Property }, { California_80hr_7days, Agriculture }, { California_80hr_7days, Passenger }, { California_80hr_7days, OilGas }, { California_80hr_7days, ShortHaul },

                { CanadaNorth_60_80_7, Property }, { CanadaNorth_60_80_7, Agriculture }, { CanadaNorth_60_80_7, Passenger }, { CanadaNorth_60_80_7, OilGas }, { CanadaNorth_60_80_7, ShortHaul },

        });
    }
    @Test
    public void Hour14DrivingWindowNoViolation() throws SQLException{

//        14-HOUR “DRIVING WINDOW” no violation

        String userId = utilsForDB.getUserIdByEmail(login);

        logsPage.cleanStatusesViolation(userId);
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
