package orders;

import libs.ExcelDriver;
import libs.UtilsForDB;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public class TestFoFleetParent extends FleetEldOrderTestWithExcel {
    @Test
    public void completedNewOrder() throws IOException, InterruptedException, SQLException, ClassNotFoundException {

        String idLastOrderAfterTest = "2459";
        ExcelDriver excelDriver = new ExcelDriver();
        Map dataForManagerValidLogIn = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "ManagerLogin");
        UtilsForDB utilsForDB = new UtilsForDB();

        loginPage.userValidLogIn(dataForManagerValidLogIn.get("login").toString(),dataForManagerValidLogIn.get("pass").toString());
        dashboardPage.clickOnMenuDash();

        dashboardPage.clickOnMenuPageELD();
        Thread.sleep(5000);
        managerEldPage.clickOnEldOrders();
        managerEldPage.enterIdOrder(idLastOrderAfterTest);
        Thread.sleep(5000);
        managerEldPage.clickOnOrderOnList(idLastOrderAfterTest);
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
