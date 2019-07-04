package loginTests;

import org.junit.Test;
import parentTest.ParentTest;

public class LoginTest extends ParentTest {
    @Test
    public void validLogin(){
        loginPage.openPage();
        loginPage.openLoginForm();
        loginPage.enterLogin("rose@emailate.com");
        loginPage.enterPass("testtest");
        loginPage.clickOnSubmitButton();

        checkAC("Dashbord is opened", dashboardPage.isDashboardPresent(), true);
    }

}
