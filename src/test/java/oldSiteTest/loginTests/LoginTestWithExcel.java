package oldSiteTest.loginTests;

import libs.ExcelDriver;
import org.junit.Test;
import parentTest.ParentTest;

import java.io.IOException;
import java.util.Map;

public class LoginTestWithExcel extends ParentTest {


    @Test
    public void validLogin() throws IOException {
        ExcelDriver excelDriver = new ExcelDriver();
        Map dataForValidLogIn = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "validFleetLogin");
        loginPage.openPage();
        loginPage.openLoginForm();
        loginPage.enterLogin(dataForValidLogIn.get("login").toString());
        loginPage.enterPass(dataForValidLogIn.get("pass").toString());
        loginPage.clickOnSubmitButton();

        checkAC("Dashbord is opened", dashboardPage.isDashboardPresent(), true);


    }

}