package parameterizedUI;

import libs.SpreadsheetData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import parentTest.ParentTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
@RunWith(Parameterized.class)
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
    public void uiTest(){
        loginPage.userValidLogIn(login, pass);
        dashboardPage.openMenuDash();
        checkAC("User wasn`t logined", dashboardPage.isDashboardPresent(), true);
        dashboardPage.goToReportsPage();
        dashboardPage.goToLogsPage();
        dashboardPage.goToDVIRPage();
        dashboardPage.goToDocumentsPage();
        dashboardPage.goToEquipmentPage();
        dashboardPage.goToEzChatPage();

        dashboardPage.goToEldPage();
        eldPage.goToPaperLogPermissions();

        dashboardPage.goToFinancesPage();
        financesPage.goToRefunds();
        financesPage.goToOrders();
        financesPage.goToCards();

        dashboardPage.goToCalendarPage();

        dashboardPage.goToHelpAndTrainingPage();
        helpAndTrainingPage.goToSupportTicketsPage();
        helpAndTrainingPage.goToUserReviewsOffersPage();
        helpAndTrainingPage.goToFaqPage();

        dashboardPage.goToSettingPage();
        settingsPage.goToDriverSettings();
    }
}
