package loginTests;

import libs.SpreadsheetData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import parentTest.Parent2Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;


@RunWith(Parameterized.class)
public class UnValidLoginWithParamsWithExcel extends Parent2Test {
    String login, pass;

    public UnValidLoginWithParamsWithExcel(String login, String pass) {
        this.login = login;
        this.pass = pass;
    }

    @Parameterized.Parameters(name = "Parameters are {0} and {1}")
    public static Collection testData() throws IOException {
                InputStream spreadsheet = new FileInputStream(configProperties.DATA_FILE_PATH() + "testDataSuit.xls");
                return new SpreadsheetData(spreadsheet, "InvalidLogOn").getData();
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
