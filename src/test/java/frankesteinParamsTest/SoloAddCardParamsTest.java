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
        loginFPage.logInAndOpenMenu(login, pass);
        checkAC("User wasn`t logined", dashboardFPage.isDashboardPresent(), true);
        dashboardFPage.goToReportsPage();
        dashboardFPage.goToLogsPage();
        dashboardFPage.goToDVIRPage();
        dashboardFPage.goToDocumentsPage();

        dashboardFPage.goToEzChatPage();
        dashboardFPage.goToFinancesPage();
        financesFPage.goToRefunds();
        financesFPage.goToOrders();
        financesFPage.goToCards();
//        add card
        waitABit(3);
        financesFPage.clickOnAddCard();
        waitABit(3);
        financesFPage.enterNumberCard(cardNumber);
        financesFPage.enterCvv("1234");
        financesFPage.enterExpiryDateYY("2025");
        financesFPage.enterExpiryDateMM("10");
        financesFPage.clickOnPrimaryCard();
        waitABit(3);
        financesFPage.clickOnCreateButton();
        waitABit(3);
        financesFPage.clickOnCreateButtonConfirm();
        waitABit(5);

        checkAC("Card failed", utilsForDB.isCurrentCardSame(userId, cardNumber), true);

    }
}
