package frankesteinTest.logbookTest;

import org.junit.Test;
import parentTest.ParentTest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import static libs.Utils.dateWithMinusDay;

public class restart_34_Hours extends ParentTest {

    int cycleType = 0;
    int cargoType = 0;

    Map dataForValidLogIn = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "driverLogin");
    String login = dataForValidLogIn.get("login").toString();
    String pass = dataForValidLogIn.get("pass").toString();

    public restart_34_Hours() throws IOException {
    }

    @Test
    public void restartFirstExample() throws SQLException {
        String userId = utilsForDB.getUserIdByEmail(login);

        logsPage.cleanStatusesAndViolation(userId);
        logsPage.setCycle(userId, cycleType);
        utilsForDB.setCargoTypeId(userId, cargoType);

        loginPage.userValidLogIn(login, pass);
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(3));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("10:00:00 AM", "12:00:00 PM", "Dr");
        logsPage.addStatus("12:00:00 PM", "04:00:00 PM", "Dr");
        logsPage.addStatus("05:00:00 PM", "09:00:00 PM", "Dr");
        logsPage.addStatus("09:00:00 PM", "11:00:00 PM", "On");
        logsPage.addStatus("11:00:00 PM", "11:59:59 PM", "Dr");
        logsPage.lifeHackDeliteLastMinuteOnLogbook("Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(2));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("10:00:00 AM", "12:00:00 PM", "Dr");
        logsPage.addStatus("12:00:00 PM", "03:00:00 PM", "Dr");
        logsPage.addStatus("04:00:00 PM", "09:00:00 PM", "Dr");
        logsPage.addStatus("09:00:00 PM", "11:00:00 PM", "On");
        logsPage.addStatus("11:00:00 PM", "11:59:59 PM", "Dr");
        logsPage.lifeHackDeliteLastMinuteOnLogbook("Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        checkAC("Restart hours are incorrect", logsPage.getStatusData(userId, dateWithMinusDay(2), "restart34") ==
                logsPage.getRestart34(cycleType, cargoType), true);
        checkAC("Violation exist", logsPage.checkAlertsExist(userId, dateWithMinusDay(2)), false);

    }

    @Test
    public void restartSecondExample() throws SQLException {
        String userId = utilsForDB.getUserIdByEmail(login);

        logsPage.cleanStatusesAndViolation(userId);
        logsPage.setCycle(userId, cycleType);
        utilsForDB.setCargoTypeId(userId, cargoType);

        loginPage.userValidLogIn(login, pass);
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(4));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("12:00:00 AM", "01:00:00 AM", "On");
        logsPage.addStatus("01:00:00 AM", "03:00:00 AM", "Dr");
        logsPage.addStatus("05:00:00 AM", "11:00:00 AM", "Dr");
        logsPage.addStatus("12:00:00 PM", "01:00:00 PM", "Dr");
        logsPage.addLastStatus("09:00:00 PM", "11:59:59 PM", "Sb");
        logsPage.lifeHackDeliteLastMinuteOnLogbook("Sb");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(3));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("12:00:00 AM", "08:00:00 AM", "Sb");
        logsPage.addLastStatus("11:00:00 PM", "11:59:59 PM", "Dr");
        logsPage.lifeHackDeliteLastMinuteOnLogbook("Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(2));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("12:00:00 AM", "04:00:00 AM", "Dr");
        logsPage.addStatus("05:00:00 AM", "09:00:00 AM", "Dr");
        logsPage.addStatus("10:00:00 AM", "12:00:00 PM", "Dr");
        logsPage.addLastStatus("12:00:00 PM", "14:00:00 PM", "On");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();

        checkAC("Restart hours are incorrect", logsPage.getStatusData(userId, dateWithMinusDay(2), "restart34") ==
                logsPage.getRestart34(cycleType, cargoType), true);
        checkAC("Violation exist", logsPage.checkAlertsExist(userId, dateWithMinusDay(2)), false);


    }
}
