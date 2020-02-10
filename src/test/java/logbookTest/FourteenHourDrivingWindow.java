package logbookTest;

import org.junit.Test;
import parentTest.ParentTest;

import java.io.IOException;
import java.util.Map;

import static libs.Utils.waitABit;

public class FourteenHourDrivingWindow extends ParentTest {
    Map dataForValidLogIn = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "driverLogin");
    String login = dataForValidLogIn.get("login").toString();
    String pass = dataForValidLogIn.get("pass").toString();

    public FourteenHourDrivingWindow() throws IOException {
    }
    @Test
    public void fourteenHourDrivingWindowNoViolation() {

        loginPage.userValidLogIn(login, pass);
        dashboardPage.openMenuDash();
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay("2020-02-02");
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();

        logsPage.clickOnTimeTo();
        logsPage.timeToInput("030000AM");
        waitABit(3);
        logsPage.clickOnSaveButton();
        waitABit(3);
        logsPage.clickOnTimeFrom();
        logsPage.timeToInput("010000AM");
        waitABit(3);
        logsPage.clickOnSaveButton();
        waitABit(3);
        logsPage.clickOnStatusOn();
        logsPage.clickOnInsertStatusButton();
        waitABit(3);

        logsPage.clickOnTimeTo();
        logsPage.timeToInput("060000PM");
        waitABit(3);
        logsPage.clickOnSaveButton();
        waitABit(3);
        logsPage.clickOnTimeFrom();
        logsPage.timeToInput("030000AM");
        waitABit(3);
        logsPage.clickOnSaveButton();
        waitABit(3);
        logsPage.clickOnStatusDr();
        logsPage.clickOnSaveInfoButton();

        waitABit(10);
    }
}
