package oldSiteTest.loginTests;

import libs.SpreadsheetData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import parentTest.ParentTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import static libs.Utils.waitABit;


@RunWith(Parameterized.class)
public class ValidLoginWithParams extends ParentTest {
    String login;

    public ValidLoginWithParams(String login) {
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
        loginPage.closePhoneVerificationPopUp();
        waitABit(3);
        checkAC("User wasn`t logined", dashboardPage.isDashboardPresent(), true);
    }
}
