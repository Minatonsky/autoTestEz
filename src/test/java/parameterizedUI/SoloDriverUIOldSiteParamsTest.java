package parameterizedUI;

import libs.SpreadsheetData;
import org.junit.Test;
import org.junit.runners.Parameterized;
import parentTest.ParentTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import static libs.Utils.waitABit;

public class SoloDriverUIOldSiteParamsTest extends ParentTest {
    String login;

    public SoloDriverUIOldSiteParamsTest(String login) {
        this.login = login;
    }

    @Parameterized.Parameters(name = "Parameters are: {0}")
    public static Collection testData() throws IOException {
        InputStream spreadsheet = new FileInputStream(configProperties.DATA_FILE_PATH() + "testLogin.xls");
        return new SpreadsheetData(spreadsheet, "loginPassParamsTest").getData();
    }
    String pass = "testtest";

    @Test
    public void validLogIn(){
        loginPage.openPage();
        loginPage.openLoginForm();
        loginPage.enterLogin(login);
        loginPage.enterPass(pass);
        loginPage.clickOnSubmitButton();
        dashboardPage.closePhoneVerificationPopUp();
        waitABit(3);
        checkAC("User wasn`t logined", dashboardPage.isDashboardPresent(), true);
        dashboardPage.goToReportsPage();
        dashboardPage.goToLogsPage();
        dashboardPage.goToDVIRPage();
        dashboardPage.goToDocumentsPage();
        dashboardPage.goToEquipmentPage();
        dashboardPage.goToEzChatPage();
        dashboardPage.goToEldPage();
        dashboardPage.goToFinancesPage();
        dashboardPage.goToCalendarPage();
        dashboardPage.goToHelpAndTrainingPage();
        dashboardPage.goToSettingPage();
    }
}
