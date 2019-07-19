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
    String  quantityOfDevices, typeOfPaymentMethod, quantityPinCable, quantityOBDPinCable, quantitySticker, quantityCamera1, quantityCamera2, neededStatePickUpFromOffice, neededStateOvernightDelivery, currentDue, defaultTotalOrder, defaultBalance;

    public EldOrderTestWithExcelParams(String quantityOfDevices, String typeOfPaymentMethod, String quantityPinCable, String quantityOBDPinCable, String quantitySticker, String quantityCamera1, String quantityCamera2, String neededStatePickUpFromOffice, String neededStateOvernightDelivery, String currentDue, String defaultTotalOrder, String defaultBalance) {
        this.quantityOfDevices = quantityOfDevices;
        this.typeOfPaymentMethod = typeOfPaymentMethod;
        this.quantityPinCable = quantityPinCable;
        this.quantityOBDPinCable = quantityOBDPinCable;
        this.quantitySticker = quantitySticker;
        this.quantityCamera1 = quantityCamera1;
        this.quantityCamera2 = quantityCamera2;
        this.neededStatePickUpFromOffice = neededStatePickUpFromOffice;
        this.neededStateOvernightDelivery = neededStateOvernightDelivery;
        this.currentDue = currentDue;
        this.defaultTotalOrder = defaultTotalOrder;
        this.defaultBalance = defaultBalance;
    }


    @Parameterized.Parameters()
    public static Collection testData() throws IOException {
        InputStream spreadsheet = new FileInputStream(configProperties.DATA_FILE_PATH() + "testEldOrder.xls");
        return new SpreadsheetData(spreadsheet,"suitOrderListData").getData();

    }


    @Test
    public void addNewOrder() throws InterruptedException, SQLException, IOException, ClassNotFoundException {
        ExcelDriver excelDriver = new ExcelDriver();

        Map personalDataForEldOrder = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testEldOrder.xls", "personalData");
        Map dataForFleetValidLogIn = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "validFleetLogin");
        Map dataFleetId = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "validFleetLogin");

        UtilsForDB utilsForDB = new UtilsForDB();
        String idLastOrderBeforeTest = utilsForDB.getLastOrderIdForFleet(dataFleetId.get("fleetId").toString());
        utilsForDB.getSetCurrentDueForFleet((currentDue), dataFleetId.get("fleetId").toString());

        loginPage.userValidLogIn(dataForFleetValidLogIn.get("login").toString(),dataForFleetValidLogIn.get("pass").toString());

        dashboardPage.goToEldPage();
        eldUserPage.clickOnOrderELD();

/*
PERSONAL DATA
 */
        modalEldPage.enterPersonalData(personalDataForEldOrder.get("deliveryState").toString(), personalDataForEldOrder.get("firstName").toString(),
                personalDataForEldOrder.get("lastName").toString(), personalDataForEldOrder.get("phone").toString(),
                personalDataForEldOrder.get("addressLine").toString(), personalDataForEldOrder.get("aptNumber").toString(),
                personalDataForEldOrder.get("deliveryCity").toString(), personalDataForEldOrder.get("zipCode").toString());

/*
ORDER LIST
// */
        modalEldPage.enterQuantityDevices(quantityOfDevices);
        modalEldPage.enterQuantityPinCable(quantityPinCable);
        modalEldPage.enterQuantityOBDPinCable(quantityOBDPinCable);
        modalEldPage.enterQuantitySticker(quantitySticker);
        modalEldPage.enterQuantityCamera1(quantityCamera1);
        modalEldPage.enterQuantityCamera2(quantityCamera2);

        modalEldPage.clickPaymentMethods(typeOfPaymentMethod);

/*
CHECK BOX DELIVERY
 */
        modalEldPage.setPickUpFromOffice(neededStatePickUpFromOffice);
        modalEldPage.setOvernightDelivery(neededStateOvernightDelivery);

        modalEldPage.compareTotalOrder(defaultTotalOrder);
        checkAC("Total Order is not correct", modalEldPage.compareTotalOrder(defaultTotalOrder), true);

/*
//EQUIPMENT LEASE AND SOFTWARE SUBSCRIPTION SERVICE AGREEMENT
 */

        modalEldPage.clickAgreements(quantityOfDevices);

        String idLastOrderAfterTest = utilsForDB.getLastOrderIdForFleet(dataFleetId.get("fleetId").toString());
        checkAC("New order wasn`t created", idLastOrderBeforeTest.equals(idLastOrderAfterTest) , true);

//        dashboardPage.clickOnMenuDash();
        dashboardPage.clickOnMenuPageFinances();
        financesPage.checkCurrentUrl();
        financesPage.compareBalance(defaultBalance);
        checkAC("Balance is not correct", financesPage.compareBalance(defaultBalance), true);



    }
}
