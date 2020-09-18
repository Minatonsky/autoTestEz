package singleTest.logbookTest;

import org.junit.Test;
import parentTest.ParentTest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import static libs.Utils.dateWithMinusDay;
import static libs.Utils.waitABit;

public class SplitSleeperBerthTest extends ParentTest {
    String cycleType = "0";
    String cargoType = "0";

    Map dataForValidLogIn = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "driverLogin");
    String login = dataForValidLogIn.get("login").toString();
    String pass = dataForValidLogIn.get("pass").toString();

    public SplitSleeperBerthTest() throws IOException {
    }

    @Test
    public void split_2_8_Test() throws SQLException {
        String userId = utilsForDB.getUserIdByEmail(login);

        logsPage.cleanStatusesAndViolation(userId);
        logsPage.setCycle(userId, Integer.parseInt(cycleType));
        utilsForDB.setCargoTypeId(userId, Integer.parseInt(cargoType));

        loginPage.userValidLogIn(login, pass);
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(3));
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
        logsPage.clickOnRowDay(dateWithMinusDay(2));
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

        checkAC("Violation exist", logsPage.checkAlertsExist(userId, dateWithMinusDay(2)), false);

    }

    @Test
    public void split_8_2_Test() throws SQLException {
        String userId = utilsForDB.getUserIdByEmail(login);

        logsPage.cleanStatusesAndViolation(userId);
        logsPage.setCycle(userId, Integer.parseInt(cycleType));
        utilsForDB.setCargoTypeId(userId, Integer.parseInt(cargoType));

        loginPage.userValidLogIn(login, pass);
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(3));
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
        logsPage.clickOnRowDay(dateWithMinusDay(2));
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

        checkAC("Violation exist", logsPage.checkAlertsExist(userId, dateWithMinusDay(2)), false);

    }

    @Test
    public void split_3_7_Test() throws SQLException {
        String userId = utilsForDB.getUserIdByEmail(login);

        logsPage.cleanStatusesAndViolation(userId);
        logsPage.setCycle(userId, Integer.parseInt(cycleType));
        utilsForDB.setCargoTypeId(userId, Integer.parseInt(cargoType));

        loginPage.userValidLogIn(login, pass);
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(3));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("00:00:00 AM", "01:00:00 AM", "On");
        logsPage.addStatus("01:00:00 AM", "07:00:00 AM", "Dr");
        logsPage.addStatus("10:00:00 AM", "12:00:00 PM", "On");
        logsPage.addStatus("12:00:00 PM", "05:00:00 PM", "Dr");
        logsPage.addStatus("05:00:00 PM", "11:59:59 PM", "Sb");
        waitABit(5);
        logsPage.lifeHackDeliteLastMinuteOnLogbook("Sb");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

//        checkAC("Violation exist", logsPage.checkAlertsExist(userId, dateWithMinusDay(3)), false);

        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(2));
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

        checkAC("Violation exist", logsPage.checkAlertsExist(userId, dateWithMinusDay(2)), false);

    }


}
