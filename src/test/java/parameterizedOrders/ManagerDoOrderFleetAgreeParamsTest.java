package parameterizedOrders;

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

public class ManagerDoOrderFleetAgreeParamsTest extends ParentTest {
    String quantityOfDevices, typeOfPaymentMethod, quantityPinCable, quantityOBDPinCable, quantitySticker, quantityCameraCP, valueSdCard, quantityCameraSVA, typeOfPaymentMethodCamera, neededStatePickUpFromOffice, neededStateOvernightDelivery, currentDue;

    public ManagerDoOrderFleetAgreeParamsTest(String quantityOfDevices, String typeOfPaymentMethod, String quantityPinCable, String quantityOBDPinCable, String quantitySticker, String quantityCameraCP, String valueSdCard, String quantityCameraSVA, String typeOfPaymentMethodCamera, String neededStatePickUpFromOffice, String neededStateOvernightDelivery, String currentDue) throws IOException {

        this.quantityOfDevices = quantityOfDevices;
        this.typeOfPaymentMethod = typeOfPaymentMethod;
        this.quantityPinCable = quantityPinCable;
        this.quantityOBDPinCable = quantityOBDPinCable;
        this.quantitySticker = quantitySticker;
        this.quantityCameraCP = quantityCameraCP;
        this.valueSdCard = valueSdCard;
        this.quantityCameraSVA = quantityCameraSVA;
        this.typeOfPaymentMethodCamera = typeOfPaymentMethodCamera;
        this.neededStatePickUpFromOffice = neededStatePickUpFromOffice;
        this.neededStateOvernightDelivery = neededStateOvernightDelivery;
        this.currentDue = currentDue;
    }

    UtilsForDB utilsForDB = new UtilsForDB();
    ExcelDriver excelDriver = new ExcelDriver();
    Map personalDataForEldOrder = ExcelDriver.getData(configProperties.DATA_FILE_PATH() + "testEldOrder.xls", "personalData");
    Map dataForSoloValidLogIn = ExcelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "validSoloLogin");
    Map dataSoloId = ExcelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "validSoloLogin");
    Map dataForManagerValidLogIn = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "ManagerLogin");
    Map dataForFleetValidLogIn = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "validFleetLogin");
    Map dataFleetId = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "validFleetLogin");

    public ManagerDoOrderFleetAgreeParamsTest() throws IOException {
    }

    @Parameterized.Parameters()
    public static Collection testData() throws IOException {
        InputStream spreadsheet = new FileInputStream(configProperties.DATA_FILE_PATH() + "testParameterizedOrder.xls");
        return new SpreadsheetData(spreadsheet,"suitOrderListData").getData();
    }

    @Test
    public void managerDoOrderFleetAgree() throws IOException, InterruptedException, SQLException, ClassNotFoundException {
        String idLastOrderBeforeTest = utilsForDB.getLastOrderIdForFleet(dataFleetId.get("fleetId").toString());
        utilsForDB.getSetCurrentDueForFleet(currentDue, dataFleetId.get("fleetId").toString());

        loginPage.userValidLogIn(dataForManagerValidLogIn.get("login").toString(),dataForManagerValidLogIn.get("pass").toString());
        dashboardPage.openMenuDash();
        dashboardPage.goToEldPage();
        managerEldPage.clickOnNewOrderButton();
        managerModalEldPage.selectFleetInOrder(dataForFleetValidLogIn.get("usdot").toString());
        modalEldPage.enterPersonalData(personalDataForEldOrder.get("deliveryState").toString(), personalDataForEldOrder.get("firstName").toString(), personalDataForEldOrder.get("lastName").toString(), personalDataForEldOrder.get("phone").toString(), personalDataForEldOrder.get("addressLine").toString(), personalDataForEldOrder.get("aptNumber").toString(), personalDataForEldOrder.get("deliveryCity").toString(), personalDataForEldOrder.get("zipCode").toString());

        modalEldPage.enterOrderData(quantityOfDevices, quantityPinCable, quantityOBDPinCable, quantitySticker, quantityCameraCP, valueSdCard, quantityCameraSVA, neededStatePickUpFromOffice, neededStateOvernightDelivery);
        modalEldPage.clickPaymentMethods(typeOfPaymentMethod, quantityOfDevices);
        modalEldPage.clickPaymentMethodsCamera(typeOfPaymentMethodCamera, quantityCameraCP);

        checkAC("Eld prices is not correct", modalEldPage.compareEldPrice(quantityOfDevices, typeOfPaymentMethod, quantityCameraCP), true);
        checkAC("DepositFee is not correct", modalEldPage.compareDepositFee(quantityOfDevices), true);
        checkAC("DeliveryPrice is not correct", modalEldPage.compareDeliveryPrice(neededStatePickUpFromOffice), true);
        checkAC("EldPinCable prices is not correct", modalEldPage.compareEldPinCable(quantityPinCable), true);
        checkAC("EldOBDPinCable prices is not correct", modalEldPage.compareEldOBDPinCable(quantityOBDPinCable), true);
        checkAC("EldStickerLabel prices is not correct", modalEldPage.compareEldStickerLabel(quantitySticker), true);
        checkAC("CP2MonthFee is not correct", modalEldPage.compareCP2MonthFee(quantityCameraCP), true);
        checkAC("CameraSetupFee is not correct", modalEldPage.compareCameraSetupFee(quantityCameraCP), true);
        checkAC("CameraInstallationFee is not correct", modalEldPage.compareCameraInstallationFee(quantityCameraCP), true);
        checkAC("EzSmartCamCP2 prices is not correct", modalEldPage.compareEzSmartCamCP2(quantityCameraCP), true);
        checkAC("EzSmartCamSVA prices is not correct", modalEldPage.compareEzSmartCamSVA(quantityCameraSVA), true);
        checkAC("SdCard prices is not correct", modalEldPage.compareSdCard(quantityCameraCP, valueSdCard), true);
        checkAC("Total Order is not correct", modalEldPage.compareTotalOrder(quantityOfDevices, typeOfPaymentMethod, quantityPinCable, quantityOBDPinCable, quantitySticker, quantityCameraCP, quantityCameraSVA, valueSdCard), true);

        modalEldPage.clickButtonOrder();
        String idLastOrderAfterTest = utilsForDB.getLastOrderIdForFleet(dataFleetId.get("fleetId").toString());
        checkAC("New order was not created", idLastOrderBeforeTest.equals(idLastOrderAfterTest) , false);

        String orderStatus = utilsForDB.getOrderStatus(idLastOrderAfterTest);
        checkAC("Order is not New status", financesPage.compareNewOrderStatus(orderStatus) , true);

        tearDown();
        setUp();

        loginPage.userValidLogIn(dataForFleetValidLogIn.get("login").toString(),dataForFleetValidLogIn.get("pass").toString());
        modalEldPage.doAgreeAgreementForManagerOrder();

        dashboardPage.openMenuDash();
        dashboardPage.goToFinancesPage();

        String dueForLastOrder = utilsForDB.getLastDueForFleet(dataFleetId.get("fleetId").toString());
        checkAC("Balance is not correct", financesPage.compareBalance(currentDue, dueForLastOrder), true);

        String orderStatusPaid = utilsForDB.getOrderStatus(idLastOrderAfterTest);
        checkAC("Order is not Paid", financesPage.comparePaidOrderStatus(orderStatusPaid) , true);


    }
}
