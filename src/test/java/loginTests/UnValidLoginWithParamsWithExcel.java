package loginTests;

import libs.SpreadsheetData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import parentTest.ParentTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;


@RunWith(Parameterized.class)
public class UnValidLoginWithParamsWithExcel extends ParentTest {
    String login, pass;

    public UnValidLoginWithParamsWithExcel(String login, String pass) {
        this.login = login;
        this.pass = pass;
    }

    @Parameterized.Parameters(name = "Parameters are {0} and {1}")
    public static Collection testData() throws IOException {
//        return Arrays.asList(new Object[][] {
//                {"notrose@emailate.com", "testtest"},
//                {"rose@emailate.com", "ttestttest"}
//        });
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
