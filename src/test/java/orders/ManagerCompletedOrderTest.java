package orders;

import libs.ExcelDriver;
import libs.UtilsForDB;
import org.junit.Test;
import parentTest.ParentTest;

import java.io.IOException;
import java.util.Map;

public class ManagerCompletedOrderTest extends ParentTest {


    @Test
    public void completedNewOrder() throws IOException, InterruptedException {

        ExcelDriver excelDriver = new ExcelDriver();
        Map dataForManagerValidLogIn = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "ManagerLogin");

        UtilsForDB utilsForDB = new UtilsForDB();


        loginPage.userValidLogIn(dataForManagerValidLogIn.get("login").toString(),dataForManagerValidLogIn.get("pass").toString());

        dashboardPage.clickOnMenuDash();
        dashboardPage.clickOnMenuPageELD();
        Thread.sleep(5000);
        eldManagerPage.clickOnEldOrders();
        eldManagerPage.enterIdOrder("2422");
        Thread.sleep(5000);
        eldManagerPage.clickOnOrderOnList();
        modalOrderPage.selectOrderStatus("4");
        modalOrderPage.clickButtonSave();
        modalOrderPage.selectOrderStatus("1");
        modalOrderPage.clickButtonSave();


    }
}
