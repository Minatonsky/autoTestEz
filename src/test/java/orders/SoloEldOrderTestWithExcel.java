package orders;

import libs.ExcelDriver;
import libs.UtilsForDB;
import org.junit.Test;
import parentTest.ParentTest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public class SoloEldOrderTestWithExcel extends ParentTest {


    @Test
    public void addNewOrder() throws InterruptedException, SQLException, IOException, ClassNotFoundException {
        ExcelDriver excelDriver = new ExcelDriver();
        int columnNumber = 2;

        Map dataForEldOrder = excelDriver.getMultipleData(configProperties.DATA_FILE_PATH() + "testEldOrder.xls", "orderListData", columnNumber);
        Map personalDataForEldOrder = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testEldOrder.xls", "personalData");
        Map dataForSoloValidLogIn = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "validSoloLogin");
        Map dataSoloId = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "validSoloLogin");

        UtilsForDB utilsForDB = new UtilsForDB();
        String idLastOrderBeforeTest = utilsForDB.getLastOrderIdForFleet(dataSoloId.get("soloId").toString());
        utilsForDB.getSetCurrentDueForSolo(dataForEldOrder.get("currentDue").toString(), dataSoloId.get("soloId").toString());

        loginPage.userValidLogIn(dataForSoloValidLogIn.get("login").toString(),dataForSoloValidLogIn.get("pass").toString());

        dashboardPage.clickOnMenuDash();
        dashboardPage.clickOnMenuPageELD();
        Thread.sleep(1000);
        dashboardPage.clickOnOrderELD();
        modalEldPage.checkCurrentUrl();

/*
PERSONAL DATA
 */

        modalEldPage.enterPrimaryAddressLine(personalDataForEldOrder.get("addressLine").toString());
        modalEldPage.enterAptNumber(personalDataForEldOrder.get("aptNumber").toString());
        modalEldPage.enterDeliveryCity(personalDataForEldOrder.get("deliveryCity").toString());
        modalEldPage.selectState(personalDataForEldOrder.get("deliveryState").toString());
        modalEldPage.enterZipCode(personalDataForEldOrder.get("zipCode").toString());
        modalEldPage.enterFirstName(personalDataForEldOrder.get("firstName").toString());
        modalEldPage.enterLastName(personalDataForEldOrder.get("lastName").toString());
        modalEldPage.enterPhone(personalDataForEldOrder.get("phone").toString());

/*
ORDER LIST
 */
        modalEldPage.enterQuantityDevices(dataForEldOrder.get("quantityOfDevices").toString());
        modalEldPage.enterQuantityPinCable(dataForEldOrder.get("quantityPinCable").toString());
        modalEldPage.enterQuantityOBDPinCable(dataForEldOrder.get("quantityOBDPinCable").toString());
        modalEldPage.enterQuantitySticker(dataForEldOrder.get("quantitySticker").toString());
        modalEldPage.enterQuantityCamera1(dataForEldOrder.get("quantityCamera1").toString());
        modalEldPage.enterQuantityCamera2(dataForEldOrder.get("quantityCamera2").toString());

        modalEldPage.clickPaymentMethods(dataForEldOrder.get("typeOfPaymentMethod").toString());

/*
CHECK BOX DELIVERY
 */
        modalEldPage.setPickUpFromOffice(dataForEldOrder.get("neededStatePickUpFromOffice").toString());
        modalEldPage.setOvernightDelivery(dataForEldOrder.get("neededStateOvernightDelivery").toString());

        modalEldPage.compareTotalOrder(dataForEldOrder.get("defaultTotalOrder").toString());
        checkAC("Total Order is not correct", modalEldPage.compareTotalOrder(dataForEldOrder.get("defaultTotalOrder").toString()), true);

/*
EQUIPMENT LEASE AND SOFTWARE SUBSCRIPTION SERVICE AGREEMENT
 */
        modalEldPage.clickAgreement();
        modalEldPage.clickButtonFastMove();
        modalEldPage.clickButtonAgree();
        modalEldPage.clickButtonOrder();

        String idLastOrderAfterTest = utilsForDB.getLastOrderIdForSolo(dataSoloId.get("soloId").toString());
        checkAC("New order wasn`t created", idLastOrderBeforeTest.equals(idLastOrderAfterTest) , false);

        dashboardPage.clickOnMenuDash();
        Thread.sleep(1000);
        dashboardPage.clickOnMenuPageFinances();
        financesPage.checkCurrentUrl();

        financesPage.compareBalance(dataForEldOrder.get("defaultBalance").toString());
        checkAC("Balance is not correct", financesPage.compareBalance(dataForEldOrder.get("defaultBalance").toString()), true);



    }
}
