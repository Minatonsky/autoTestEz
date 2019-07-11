package orders;

import libs.ExcelDriver;
import libs.UtilsForDB;
import org.junit.Test;
import parentTest.ParentTest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public class EldOrderTestWithExcel extends ParentTest {


    @Test
    public void addNewOrder() throws InterruptedException, SQLException, IOException, ClassNotFoundException {
        ExcelDriver excelDriver = new ExcelDriver();
        Map dataForEldOrder = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testEldOrder.xls", "eldOrder");
        Map dataForFleetValidLogIn = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "validFleetLogin");
        UtilsForDB utilsForDB = new UtilsForDB();
        String idLastOrderBeforeTest = utilsForDB.getLastOrderIdForFleet(dataForEldOrder.get("fleetId").toString());
        utilsForDB.getSetCurrentDueForFleet(dataForEldOrder.get("currentDue").toString(), dataForEldOrder.get("fleetId").toString());

        loginPage.userValidLogIn(dataForFleetValidLogIn.get("login").toString(),dataForEldOrder.get("pass").toString());

        dashboardPage.clickOnMenuDash();
        dashboardPage.clickOnMenuPageELD();
        Thread.sleep(1000);
        dashboardPage.clickOnOrderELD();

/*
PERSONAL DATA
 */
        modalEldPage.checkCurrentUrl();
        modalEldPage.enterPrimaryAddressLine(dataForEldOrder.get("addressLine").toString());
        modalEldPage.enterAptNumber(dataForEldOrder.get("aptNumber").toString());
        modalEldPage.enterDeliveryCity(dataForEldOrder.get("deliveryCity").toString());
        modalEldPage.selectState(dataForEldOrder.get("deliveryState").toString());
        modalEldPage.enterZipCode(dataForEldOrder.get("zipCode").toString());
        modalEldPage.enterQuantityDevices(dataForEldOrder.get("quantityOfDevices").toString());
        modalEldPage.enterFirstName(dataForEldOrder.get("firstName").toString());
        modalEldPage.enterLastName(dataForEldOrder.get("lastName").toString());
        modalEldPage.enterPhone(dataForEldOrder.get("phone").toString());

/*
ORDER LIST
// */
//        modalEldPage.enterQuantityPinCable();
//        modalEldPage.enterQuantityOBDPinCable();
//        modalEldPage.enterQuantitySticker();
//        modalEldPage.enterQuantityCamera1();
//        modalEldPage.enterQuantityCamera2();
//
//        modalEldPage.clickPaymentMethods();
//
///*
//CHECK BOX DELIVERY
// */
//        modalEldPage.setPickUpFromOffice();
//        modalEldPage.setOvernightDelivery();
//
//        modalEldPage.compareTotalOrder(dataForEldOrder.get("defaultTotalOrder").toString());
//        checkAC("Total Order is not correct", modalEldPage.compareTotalOrder(dataForEldOrder.get("defaultTotalOrder").toString()), true);
//
///*
//EQUIPMENT LEASE AND SOFTWARE SUBSCRIPTION SERVICE AGREEMENT
// */
//        modalEldPage.clickAgreement();
//        modalEldPage.clickButtonFastMove();
//        modalEldPage.clickButtonAgree();
//        modalEldPage.clickButtonOrder();
//
//        String idLastOrderAfterTest = utilsForDB.getLastOrderIdForFleet(dataForEldOrder.get("fleetId").toString());
//        checkAC("New order wasn`t created", idLastOrderBeforeTest.equals(idLastOrderAfterTest) , false);
//
//        dashboardPage.clickOnMenuDash();
//        dashboardPage.clickOnMenuPageFinances();
//        financesPage.checkCurrentUrl();

        financesPage.compareBalance(dataForEldOrder.get("defaultBalance").toString());
        checkAC("Balance is not correct", financesPage.compareBalance(dataForEldOrder.get("defaultBalance").toString()), true);



    }
}
