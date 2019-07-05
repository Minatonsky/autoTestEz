package loginTests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import parentTest.ParentTest;

import java.util.Arrays;
import java.util.Collection;


@RunWith(Parameterized.class)
public class unValidLoginWithParams extends ParentTest {
    String login, pass;

    public unValidLoginWithParams(String login, String pass) {
        this.login = login;
        this.pass = pass;
    }

    @Parameterized.Parameters(name = "Parameters are {0} and {1}")
    public static Collection testData(){
        return Arrays.asList(new Object[][] {
                {"notrose@emailate.com", "testtest"},
                {"rose@emailate.com", "ttestttest"}
        });
    }

    @Test
    public void inValidLogIn(){
        loginPage.openPage();
        loginPage.openLoginForm();
        loginPage.enterLogin(login);
        loginPage.enterPass(pass);
        loginPage.clickOnSubmitButton();

        checkAC("User wasn`t logined", dashboardPage.isDashboardPresent(), false);
    }
}
