package singleTest.logbookTest;

import org.junit.Test;
import parentTest.ParentTest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import static libs.Utils.dateWithMinusDay;

public class SplitSleeperBerthTest extends ParentTest {
    String cycleType = "0";
    String cargoType = "0";

    int firstDay = 2;
    int secondDay = 1;

    Map dataForValidLogIn = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "driverLogin");
    String login = dataForValidLogIn.get("login").toString();
    String pass = dataForValidLogIn.get("pass").toString();

    public SplitSleeperBerthTest() throws IOException {
    }

    @Test
    public void split_2_8_MultiDayTest() throws SQLException {
        String userId = utilsForDB.getUserIdByEmail(login);

        logsPage.cleanStatusesAndViolation(userId);
        logsPage.setCycle(userId, Integer.parseInt(cycleType));
        utilsForDB.setCargoTypeId(userId, Integer.parseInt(cargoType));

        loginPage.userValidLogIn(login, pass);
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(firstDay));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("02:00:00 AM", "03:00:00 AM", "On");
        logsPage.addStatus("03:00:00 AM", "08:00:00 AM", "Dr");
        logsPage.addStatus("08:00:00 AM", "10:00:00 AM", "Sb");
        logsPage.addStatus("10:00:00 AM", "12:00:00 PM", "Dr");
        logsPage.addStatus("12:00:00 PM", "04:00:00 PM", "Dr");
        logsPage.addStatus("04:00:00 PM", "11:59:59 PM", "Sb");
        logsPage.lifeHackDeliteLastMinuteOnLogbook("Sb");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(secondDay));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("00:00:00 AM", "01:00:00 AM", "On");
        logsPage.addStatus("01:00:00 AM", "05:00:00 AM", "Dr");
        logsPage.addStatus("05:00:00 AM", "07:00:00 AM", "Sb");
        logsPage.addStatus("07:00:00 AM", "12:00:00 PM", "Dr");
        logsPage.addStatus("12:00:00 PM", "01:00:00 PM", "Dr");
        logsPage.addStatus("01:00:00 PM", "09:00:00 PM", "Sb");
        logsPage.addStatus("09:00:00 PM", "11:59:59 PM", "Dr");
        logsPage.lifeHackDeliteLastMinuteOnLogbook("Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        checkAC("Violation exist", logsPage.checkAlertsExist(userId, dateWithMinusDay(secondDay)), false);

    }

    @Test
    public void split_8_2_MultiDayTest() throws SQLException {
        String userId = utilsForDB.getUserIdByEmail(login);

        logsPage.cleanStatusesAndViolation(userId);
        logsPage.setCycle(userId, Integer.parseInt(cycleType));
        utilsForDB.setCargoTypeId(userId, Integer.parseInt(cargoType));

        loginPage.userValidLogIn(login, pass);
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(firstDay));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("02:00:00 AM", "03:00:00 AM", "On");
        logsPage.addStatus("03:00:00 AM", "08:00:00 AM", "Dr");
        logsPage.addStatus("08:00:00 AM", "12:00:00 PM", "Sb");
        logsPage.addStatus("12:00:00 PM", "04:00:00 PM", "Sb");
        logsPage.addStatus("04:00:00 PM", "10:00:00 PM", "Dr");
        logsPage.addStatus("10:00:00 PM", "11:59:59 PM", "Sb");
        logsPage.lifeHackDeliteLastMinuteOnLogbook("Sb");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(secondDay));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("00:00:00 AM", "01:00:00 AM", "On");
        logsPage.addStatus("01:00:00 AM", "05:00:00 AM", "Dr");
        logsPage.addStatus("05:00:00 AM", "12:00:00 PM", "Sb");
        logsPage.addStatus("12:00:00 PM", "01:00:00 PM", "Sb");
        logsPage.addStatus("01:00:00 PM", "07:00:00 PM", "Dr");
        logsPage.addStatus("07:00:00 PM", "09:00:00 PM", "Sb");
        logsPage.addStatus("09:00:00 PM", "11:59:59 PM", "Dr");
        logsPage.lifeHackDeliteLastMinuteOnLogbook("Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        checkAC("Violation exist", logsPage.checkAlertsExist(userId, dateWithMinusDay(secondDay)), false);

    }

    @Test
    public void split_3_7_MultiDayTest() throws SQLException {
        String userId = utilsForDB.getUserIdByEmail(login);

        logsPage.cleanStatusesAndViolation(userId);
        logsPage.setCycle(userId, Integer.parseInt(cycleType));
        utilsForDB.setCargoTypeId(userId, Integer.parseInt(cargoType));

        loginPage.userValidLogIn(login, pass);
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(firstDay));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("00:00:00 AM", "01:00:00 AM", "On");
        logsPage.addStatus("01:00:00 AM", "07:00:00 AM", "Dr");
        logsPage.addStatus("10:00:00 AM", "12:00:00 PM", "On");
        logsPage.addStatus("12:00:00 PM", "05:00:00 PM", "Dr");
        logsPage.addStatus("05:00:00 PM", "11:59:59 PM", "Sb");
        logsPage.lifeHackDeliteLastMinuteOnLogbook("Sb");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        checkAC("Violation exist", logsPage.checkAlertsExist(userId, dateWithMinusDay(firstDay)), false);

        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(secondDay));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("00:00:00 AM", "01:00:00 AM", "On");
        logsPage.addStatus("01:00:00 AM", "07:00:00 AM", "Dr");
        logsPage.addStatus("10:00:00 AM", "12:00:00 PM", "On");
        logsPage.addStatus("12:00:00 PM", "05:00:00 PM", "Dr");
        logsPage.addStatus("05:00:00 PM", "11:59:59 PM", "Sb");
        logsPage.lifeHackDeliteLastMinuteOnLogbook("Sb");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        checkAC("Violation exist", logsPage.checkAlertsExist(userId, dateWithMinusDay(secondDay)), false);

    }

    @Test
    public void split_7_3_MultiDayTest() throws SQLException {
        String userId = utilsForDB.getUserIdByEmail(login);

        logsPage.cleanStatusesAndViolation(userId);
        logsPage.setCycle(userId, Integer.parseInt(cycleType));
        utilsForDB.setCargoTypeId(userId, Integer.parseInt(cargoType));

        loginPage.userValidLogIn(login, pass);
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(firstDay));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("00:00:00 AM", "01:00:00 AM", "On");
        logsPage.addStatus("01:00:00 AM", "07:00:00 AM", "Dr");
        logsPage.addStatus("07:00:00 AM", "12:00:00 PM", "Sb");
        logsPage.addStatus("12:00:00 PM", "02:00:00 PM", "Sb");
        logsPage.addStatus("02:00:00 PM", "04:00:00 PM", "On");
        logsPage.addLastStatus("04:00:00 PM", "09:00:00 PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        checkAC("Violation exist", logsPage.checkAlertsExist(userId, dateWithMinusDay(firstDay)), false);

        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(secondDay));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("00:00:00 AM", "01:00:00 AM", "On");
        logsPage.addStatus("01:00:00 AM", "07:00:00 AM", "Dr");
        logsPage.addStatus("07:00:00 AM", "12:00:00 PM", "Sb");
        logsPage.addStatus("12:00:00 PM", "02:00:00 PM", "Sb");
        logsPage.addStatus("02:00:00 PM", "04:00:00 PM", "On");
        logsPage.addLastStatus("04:00:00 PM", "09:00:00 PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        checkAC("Violation exist", logsPage.checkAlertsExist(userId, dateWithMinusDay(secondDay)), false);

    }

    @Test
    public void split_3_7_TransitoryFullStatusTest() throws SQLException {
        String userId = utilsForDB.getUserIdByEmail(login);

        logsPage.cleanStatusesAndViolation(userId);
        logsPage.setCycle(userId, Integer.parseInt(cycleType));
        utilsForDB.setCargoTypeId(userId, Integer.parseInt(cargoType));

        loginPage.userValidLogIn(login, pass);
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(firstDay));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("03:00:00 PM", "04:00:00 PM", "On");
        logsPage.addStatus("04:00:00 PM", "09:00:00 PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        checkAC("Violation exist", logsPage.checkAlertsExist(userId, dateWithMinusDay(firstDay)), false);

        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(secondDay));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("00:00:00 AM", "01:00:00 AM", "On");
        logsPage.addStatus("01:00:00 AM", "07:00:00 AM", "Dr");
        logsPage.addStatus("07:00:00 AM", "12:00:00 PM", "Sb");
        logsPage.addStatus("12:00:00 PM", "02:00:00 PM", "Sb");
        logsPage.addLastStatus("02:00:00 PM", "07:00:00 PM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        checkAC("Violation exist", logsPage.checkAlertsExist(userId, dateWithMinusDay(secondDay)), false);

    }
    @Test
    public void split_7_3_TransitoryFullFirstStatusTest() throws SQLException {
        String userId = utilsForDB.getUserIdByEmail(login);

        logsPage.cleanStatusesAndViolation(userId);
        logsPage.setCycle(userId, Integer.parseInt(cycleType));
        utilsForDB.setCargoTypeId(userId, Integer.parseInt(cargoType));

        loginPage.userValidLogIn(login, pass);
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(firstDay));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("11:00:00 AM", "12:00:00 PM", "On");
        logsPage.addStatus("12:00:00 PM", "05:00:00 PM", "Dr");
        logsPage.addStatus("05:00:00 PM", "11:59:59 PM", "Sb");
        logsPage.lifeHackDeliteLastMinuteOnLogbook("Sb");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        checkAC("Violation exist", logsPage.checkAlertsExist(userId, dateWithMinusDay(firstDay)), false);

        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(secondDay));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("00:00:00 AM", "01:00:00 AM", "On");
        logsPage.addStatus("01:00:00 AM", "07:00:00 AM", "Dr");
        logsPage.addStatus("07:00:00 AM", "10:00:00 AM", "Sb");
        logsPage.addStatus("10:00:00 AM", "12:00:00 PM", "Dr");
        logsPage.addStatus("12:00:00 PM", "03:00:00 PM", "Dr");
        logsPage.addLastStatus("03:00:00 PM", "05:00:00 PM", "On");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        checkAC("Violation exist", logsPage.checkAlertsExist(userId, dateWithMinusDay(secondDay)), false);

    }

    @Test
    public void split_7_3_TransitoryHalfFirstStatusTest() throws SQLException {
        String userId = utilsForDB.getUserIdByEmail(login);

        logsPage.cleanStatusesAndViolation(userId);
        logsPage.setCycle(userId, Integer.parseInt(cycleType));
        utilsForDB.setCargoTypeId(userId, Integer.parseInt(cargoType));

        loginPage.userValidLogIn(login, pass);
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(firstDay));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("01:00:00 PM", "02:00:00 PM", "On");
        logsPage.addStatus("02:00:00 PM", "07:00:00 PM", "Dr");
        logsPage.addStatus("07:00:00 PM", "11:59:59 PM", "Sb");
        logsPage.lifeHackDeliteLastMinuteOnLogbook("Sb");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        checkAC("Violation exist", logsPage.checkAlertsExist(userId, dateWithMinusDay(firstDay)), false);

        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(secondDay));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("00:30:00 AM", "02:00:00 AM", "Sb");
        logsPage.addStatus("02:00:00 AM", "03:00:00 AM", "On");
        logsPage.addStatus("03:00:00 AM", "09:00:00 AM", "Dr");
        logsPage.addStatus("09:00:00 AM", "12:00:00 PM", "Sb");
        logsPage.addStatus("12:00:00 PM", "05:00:00 PM", "Dr");
        logsPage.addLastStatus("05:00:00 PM", "07:00:00 PM", "On");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        checkAC("Violation exist", logsPage.checkAlertsExist(userId, dateWithMinusDay(secondDay)), false);

    }

}
