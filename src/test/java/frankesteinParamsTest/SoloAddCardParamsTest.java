package frankesteinParamsTest;

import libs.SpreadsheetData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import parentTest.ParentTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Collection;

import static libs.Utils.genRandomCreditCard;
import static libs.Utils.waitABit;

@RunWith(Parameterized.class)
public class SoloAddCardParamsTest extends ParentTest {
    String login;

    public SoloAddCardParamsTest(String login) throws IOException {
        this.login = login;
    }

    @Parameterized.Parameters(name = "Parameters are: {0}")
    public static Collection testData() throws IOException {
        InputStream spreadsheet = new FileInputStream(configProperties.DATA_FILE_PATH() + "testLogin.xls");
        return new SpreadsheetData(spreadsheet, "loginPassParamsTest").getData();
    }

    String pass = "testtest";

    @Test
    public void addCard() throws SQLException, IOException, ClassNotFoundException {

        String cardNumber = genRandomCreditCard();
        String userId = utilsForDB.getUserIdByEmail(login);
        loginPage.userValidLogIn(login, pass);
        checkAC("User wasn`t logined", dashboardPage.isDashboardPresent(), true);
        dashboardPage.goToReportsPage();
        dashboardPage.goToLogsPage();
        dashboardPage.goToDVIRPage();
        dashboardPage.goToDocumentsPage();

        dashboardPage.goToEzChatPage();
        dashboardPage.goToFinancesPage();
        financesPage.goToRefunds();
        financesPage.goToOrders();
        financesPage.goToCards();
//        add card
        waitABit(3);
        financesPage.clickOnAddCard();
        waitABit(3);
        financesPage.enterNumberCard(cardNumber);
        financesPage.enterCvv("1234");
        financesPage.enterExpiryDateYY("2025");
        financesPage.enterExpiryDateMM("10");
        financesPage.clickOnPrimaryCard();
        waitABit(3);
        financesPage.clickOnCreateButton();
        waitABit(3);
        financesPage.clickOnCreateButtonConfirm();
        waitABit(5);

        checkAC("Card failed", utilsForDB.isCurrentCardSame(userId, cardNumber), true);
        dashboardPage.goToEldPage();
        eldPage.goToPaperLogPermissions();

        dashboardPage.goToCalendarPage();
        dashboardPage.goToHelpAndTrainingPage();
        helpAndTrainingPage.goToSupportTicketsPage();
        helpAndTrainingPage.goToUserReviewsOffersPage();
        helpAndTrainingPage.goToFaqPage();

        dashboardPage.goToSettingPage();
        settingsPage.goToDriverSettings();

    }
}
