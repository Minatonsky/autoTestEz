package oldSiteTest.loginTests;

import org.junit.Test;
import parentTest.ParentTest;

public class LoginTest extends ParentTest {
    @Test
    public void validLogin() throws InterruptedException {

        loginPage.userValidLogIn("den36@gmail.com", "testtest");

        checkAC("Dashbord is opened", dashboardPage.isDashboardPresent(), true);

        dashboardPage.goToEldPage();
        dashboardPage.goToFinancesPage();
        dashboardPage.goToEldPage();
        dashboardPage.goToFinancesPage();
    }
}
