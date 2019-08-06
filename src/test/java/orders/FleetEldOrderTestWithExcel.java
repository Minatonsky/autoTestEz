package orders;

import libs.ExcelDriver;
import libs.UtilsForDB;
import org.junit.Test;
import parentTest.ParentTest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import static libs.Utils.waitABit;

public class FleetEldOrderTestWithExcel extends ParentTest {


    @Test
    public void addNewOrder() throws InterruptedException, SQLException, IOException, ClassNotFoundException {
        ExcelDriver excelDriver = new ExcelDriver();
        int columnNumber = 1;

        Map dataForEldOrder = excelDriver.getMultipleData(configProperties.DATA_FILE_PATH() + "testEldOrder.xls", "orderListData", columnNumber);
        Map personalDataForEldOrder = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testEldOrder.xls", "personalData");
        Map dataForFleetValidLogIn = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "validFleetLogin");
        Map dataFleetId = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "validFleetLogin");

        UtilsForDB utilsForDB = new UtilsForDB();
        String idLastOrderBeforeTest = utilsForDB.getLastOrderIdForFleet(dataFleetId.get("fleetId").toString());
        utilsForDB.getSetCurrentDueForFleet(dataForEldOrder.get("currentDue").toString(), dataFleetId.get("fleetId").toString());

        loginPage.userValidLogIn(dataForFleetValidLogIn.get("login").toString(),dataForFleetValidLogIn.get("pass").toString());

        dashboardPage.openMenuDash();
        dashboardPage.goToEldPage();
        eldUserPage.clickOnOrderELD();

/*
PERSONAL DATA
 */     modalEldPage.enterPersonalData(personalDataForEldOrder.get("deliveryState").toString(), personalDataForEldOrder.get("firstName").toString(),
                personalDataForEldOrder.get("lastName").toString(), personalDataForEldOrder.get("phone").toString(),
                personalDataForEldOrder.get("addressLine").toString(), personalDataForEldOrder.get("aptNumber").toString(),
                personalDataForEldOrder.get("deliveryCity").toString(), personalDataForEldOrder.get("zipCode").toString());

 /*
CHECK BOX DELIVERY
 */
        modalEldPage.setPickUpFromOffice(dataForEldOrder.get("neededStatePickUpFromOffice").toString());
        modalEldPage.setOvernightDelivery(dataForEldOrder.get("neededStateOvernightDelivery").toString());


/*
ORDER LIST
 */
        modalEldPage.enterQuantityDevices(dataForEldOrder.get("quantityOfDevices").toString());
        modalEldPage.enterQuantityPinCable(dataForEldOrder.get("quantityPinCable").toString());
        modalEldPage.enterQuantityOBDPinCable(dataForEldOrder.get("quantityOBDPinCable").toString());
        modalEldPage.enterQuantitySticker(dataForEldOrder.get("quantitySticker").toString());


        waitABit(5);


        checkAC("First month is not correct", modalEldPage.compareFirstMonthFee(dataForEldOrder.get("eldFirstMonthFee").toString()), true);

//        modalEldPage.compareEldPrice(dataForEldOrder.get("quantityOfDevices").toString(), dataForEldOrder.get("typeOfPaymentMethod").toString(), dataForEldOrder.get("eldFirstMonthFee").toString(), dataForEldOrder.get("eldLastMonthFee").toString(), dataForEldOrder.get("eldOneYearPrice").toString(), dataForEldOrder.get("eldTwoYearPrice").toString());


        checkAC("Total Order is not correct", modalEldPage.compareTotalOrder(dataForEldOrder.get("defaultTotalOrder").toString()), true);

/*
EQUIPMENT LEASE AND SOFTWARE SUBSCRIPTION SERVICE AGREEMENT
 */

       // modalEldPage.doAgreeAgreement(dataForEldOrder.get("quantityOfDevices").toString());

/*
CHECK LAST ID ORDER BEFORE AND AFTER TEST
 */

        String idLastOrderAfterTest = utilsForDB.getLastOrderIdForFleet(dataFleetId.get("fleetId").toString());
        checkAC("New order wasn`t created", idLastOrderBeforeTest.equals(idLastOrderAfterTest) , false);

        dashboardPage.goToFinancesPage();


        financesPage.compareBalance(dataForEldOrder.get("defaultBalance").toString());
        checkAC("Balance is not correct", financesPage.compareBalance(dataForEldOrder.get("defaultBalance").toString()), true);



    }
}
