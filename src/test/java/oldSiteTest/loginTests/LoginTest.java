package oldSiteTest.loginTests;

import org.junit.Test;
import parentTest.ParentTest;

public class LoginTest extends ParentTest {
    @Test
    public void validLogin() throws InterruptedException {
        loginPage.openPage();
        loginPage.openLoginForm();
        loginPage.enterLogin("rose@emailate.com");
        loginPage.enterPass("testtest");
        loginPage.clickOnSubmitButton();

        checkAC("Dashbord is opened", dashboardPage.isDashboardPresent(), true);
        dashboardPage.clickOnMenuDash();
        dashboardPage.clickMenuSizeButton();

        dashboardPage.goToEldPage();
        dashboardPage.goToFinancesPage();
        dashboardPage.goToEldPage();
        dashboardPage.goToFinancesPage();


    }


}
