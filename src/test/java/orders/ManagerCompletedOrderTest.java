package orders;

import libs.ExcelDriver;
import libs.UtilsForDB;
import org.junit.Test;
import parentTest.ParentTest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public class ManagerCompletedOrderTest extends ParentTest {


    @Test
    public void completedNewOrder() throws IOException, InterruptedException, SQLException, ClassNotFoundException {

        String idLastOrderAfterTest = "2420";

        ExcelDriver excelDriver = new ExcelDriver();
        Map dataForManagerValidLogIn = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "ManagerLogin");

        UtilsForDB utilsForDB = new UtilsForDB();


        loginPage.userValidLogIn(dataForManagerValidLogIn.get("login").toString(),dataForManagerValidLogIn.get("pass").toString());

        dashboardPage.clickOnMenuDash();

        selectBrowserWindow("mainWindow");

        dashboardPage.clickOnMenuPageELD();
        Thread.sleep(5000);
        eldManagerPage.clickOnEldOrders();
        eldManagerPage.enterIdOrder(idLastOrderAfterTest);
        Thread.sleep(5000);
        eldManagerPage.clickOnOrderOnList(idLastOrderAfterTest);
        modalOrderPage.selectOrderStatus("4");
        Thread.sleep(5000);
        modalOrderPage.clickButtonSave();
        Thread.sleep(5000);
        modalOrderPage.selectOrderStatus("1");
        modalOrderPage.clickButtonSave();
        Thread.sleep(5000);

        String orderStatus = utilsForDB.getOrderStatus(idLastOrderAfterTest);

        checkAC("Order is not completed", orderStatus.equals("1") , true);

    }
}
