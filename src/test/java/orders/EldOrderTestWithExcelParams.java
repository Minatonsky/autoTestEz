package orders;

import libs.ExcelDriver;
import libs.SpreadsheetData;
import libs.UtilsForDB;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import parentTest.ParentTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;

@RunWith(Parameterized.class)

public class EldOrderTestWithExcelParams extends ParentTest {
    int columnNumber;

    public EldOrderTestWithExcelParams(int columnNumber) {
        this.columnNumber = columnNumber;
    }


    @Parameterized.Parameters()
    public static Collection testEldOrder() throws IOException {
        InputStream spreadsheet = new FileInputStream(configProperties.DATA_FILE_PATH() + "testEldOrder.xls");
        return new SpreadsheetData(spreadsheet,"orderListData").getData();

    }


    @Test
    public void addNewOrder() throws InterruptedException, SQLException, IOException, ClassNotFoundException {
        ExcelDriver excelDriver = new ExcelDriver();

        Map dataForEldOrder = excelDriver.getMultipleData(configProperties.DATA_FILE_PATH() + "testEldOrder.xls", "orderListData", columnNumber);
        Map personalDataForEldOrder = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testEldOrder.xls", "personalData");
        Map dataForFleetValidLogIn = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "validFleetLogin");
        Map dataFleetId = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "fleetId");

        UtilsForDB utilsForDB = new UtilsForDB();
        String idLastOrderBeforeTest = utilsForDB.getLastOrderIdForFleet(dataFleetId.get("fleetId").toString());
        utilsForDB.getSetCurrentDueForFleet(dataForEldOrder.get("currentDue").toString(), dataFleetId.get("fleetId").toString());

        loginPage.userValidLogIn(dataForFleetValidLogIn.get("login").toString(),dataForFleetValidLogIn.get("pass").toString());

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
// */
        modalEldPage.enterQuantityDevices(dataForEldOrder.get("quantityOfDevices").toString());
        modalEldPage.enterQuantityPinCable(dataForEldOrder.get("quantityPinCable").toString());
        modalEldPage.enterQuantityOBDPinCable(dataForEldOrder.get("quantityOBDPinCable").toString());
        modalEldPage.enterQuantitySticker(dataForEldOrder.get("quantitySticker").toString());
        modalEldPage.enterQuantityCamera1(dataForEldOrder.get("quantityCamera1").toString());
        modalEldPage.enterQuantityCamera2(dataForEldOrder.get("quantityCamera2").toString());

//        modalEldPage.clickPaymentMethods();

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
//        modalEldPage.clickButtonOrder();

        String idLastOrderAfterTest = utilsForDB.getLastOrderIdForFleet(dataFleetId.get("fleetId").toString());
        checkAC("New order wasn`t created", idLastOrderBeforeTest.equals(idLastOrderAfterTest) , true);

//        dashboardPage.clickOnMenuDash();
//        dashboardPage.clickOnMenuPageFinances();
//        financesPage.checkCurrentUrl();
//
//        financesPage.compareBalance(dataForEldOrder.get("defaultBalance").toString());
//        checkAC("Balance is not correct", financesPage.compareBalance(dataForEldOrder.get("defaultBalance").toString()), true);



    }
}
