package loginTests;

import libs.ExcelDriver;
import org.junit.Test;
import parentTest.ParentTest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public class TestRule extends ParentTest {


    @Test
    public void validLogin() throws IOException, InterruptedException, SQLException, ClassNotFoundException {
        ExcelDriver excelDriver = new ExcelDriver();
        Map dataForValidLogIn = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "validFleetLogin");
        loginPage.openPage();
        loginPage.openLoginForm();
        loginPage.enterLogin(dataForValidLogIn.get("login").toString());
        loginPage.enterPass(dataForValidLogIn.get("pass").toString());
        loginPage.clickOnSubmitButton();
        //String idLastOrderAfterTest = utilsForDB.getLastOrderIdForFleet(dataFleetId.get("fleetId").toString());

        checkAC("Dashbord is opened", dashboardPage.isDashboardPresent(), true);

        tearDown();
        setUp();

        Map dataForManagerValidLogIn = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "ManagerLogin");

        loginPage.userValidLogIn(dataForManagerValidLogIn.get("login").toString(),dataForManagerValidLogIn.get("pass").toString());
        dashboardPage.clickOnMenuDash();
        modalOrderPage.selectBrowserWindow("mainWindow");
        dashboardPage.clickOnMenuPageELD();
        Thread.sleep(5000);
        eldManagerPage.clickOnEldOrders();
        eldManagerPage.enterIdOrder("2422");
        Thread.sleep(5000);
//        eldManagerPage.clickOnOrderOnList(idLastOrderAfterTest);
        modalOrderPage.selectOrderStatus("4");
        modalOrderPage.clickButtonSave();
        modalOrderPage.selectOrderStatus("1");
        modalOrderPage.clickButtonSave();


    }



}


