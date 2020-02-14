package oldSiteParameterizedTest;

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

import static libs.Utils.waitABit;

@RunWith(Parameterized.class)
public class SafetyAddCardParamsTest extends ParentTest {
    String login;

    public SafetyAddCardParamsTest(String login) throws IOException {
        this.login = login;
    }

    @Parameterized.Parameters(name = "Parameters are: {0}")
    public static Collection testData() throws IOException {
        InputStream spreadsheet = new FileInputStream(configProperties.DATA_FILE_PATH() + "testLogin.xls");
        return new SpreadsheetData(spreadsheet, "loginSafety").getData();
    }

    String pass = "testtest";

    @Test
    public void addCard() throws SQLException, IOException, ClassNotFoundException {

        String cardNumber = genRandomCreditCard();
        String userId = utilsForDB.getUserIdByEmail(login);
        loginPage.userValidLogIn(login, pass);
        dashboardPage.openMenuDash();
        checkAC("User wasn`t logined", dashboardPage.isDashboardPresent(), true);

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


    }
}
