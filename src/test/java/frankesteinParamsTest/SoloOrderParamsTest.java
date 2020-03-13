package frankesteinParamsTest;

import libs.ExcelDriver;
import libs.SpreadsheetData;
import org.junit.Before;
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

import static libs.Utils.waitABit;

@RunWith(Parameterized.class)


public class SoloOrderParamsTest extends ParentTest { String  typeOfDevices, quantityOfDevices, typeOfPaymentMethod, quantityPinCable, quantityOBDPinCable, quantitySticker, quantityCameraCP, valueSdCard, quantityCameraSVA, typeOfPaymentMethodCamera, neededStatePickUpFromOffice, neededStateOvernightDelivery, currentDue;

    public SoloOrderParamsTest(String typeOfDevices, String quantityOfDevices, String typeOfPaymentMethod, String quantityPinCable, String quantityOBDPinCable, String quantitySticker, String quantityCameraCP, String valueSdCard, String quantityCameraSVA, String typeOfPaymentMethodCamera, String neededStatePickUpFromOffice, String neededStateOvernightDelivery, String currentDue) throws IOException {
        this.typeOfDevices = typeOfDevices;
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

    Map personalDataForEldOrder = ExcelDriver.getData(configProperties.DATA_FILE_PATH() + "testSettings.xls", "personalData");
    Map dataForSoloValidLogIn = ExcelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "validSoloLogin");
    Map dataSoloId = ExcelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "validSoloLogin");
    Map dataForManagerValidLogIn = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "ManagerLogin");

    String userIdString = "userId";

    @Parameterized.Parameters(name = "Type devices: {0}, quantity devices: {1}, payment method: {2}, Pin cable: {3}, OBD cable: {4}, sticker: {5}, cameraCP: {6}, cd card: {7}, cameraCVA: {8}, payment method cam: {9}, PickUpFromOffice: {10}, OvernightDelivery: {11}, current due: {12}")
    public static Collection testData() throws IOException {
        InputStream spreadsheet = new FileInputStream(configProperties.DATA_FILE_PATH() + "testParameterizedOrder.xls");
        return new SpreadsheetData(spreadsheet,"suitOrderListData").getData();
    }
    @Before
    public void soloDoNewOrder() throws SQLException, IOException, ClassNotFoundException {
        eldPage.checkAndCancelNewOrderBeforeTest(userIdString, dataSoloId.get("soloId").toString());
        String idLastOrderBeforeTest = utilsForDB.getLastOrderId(userIdString, dataSoloId.get("soloId").toString());
        utilsForDB.setCurrentDueForSolo(currentDue, dataSoloId.get("soloId").toString());

        loginPage.userValidLogIn(dataForSoloValidLogIn.get("login").toString(),dataForSoloValidLogIn.get("pass").toString());
        dashboardPage.goToEldPage();
        eldPage.clickOnOrderELD();
        modalOrderPage.enterPersonalData(personalDataForEldOrder.get("deliveryState").toString(), personalDataForEldOrder.get("firstName").toString(), personalDataForEldOrder.get("lastName").toString(), personalDataForEldOrder.get("phone").toString(), personalDataForEldOrder.get("addressLine").toString(), personalDataForEldOrder.get("aptNumber").toString(), personalDataForEldOrder.get("deliveryCity").toString(), personalDataForEldOrder.get("zipCode").toString());
        modalOrderPage.enterOrderData(typeOfDevices, quantityOfDevices, quantityPinCable, quantityOBDPinCable, quantitySticker, quantityCameraCP, valueSdCard, quantityCameraSVA, neededStatePickUpFromOffice, neededStateOvernightDelivery);
        modalOrderPage.clickPaymentMethods(typeOfPaymentMethod, quantityOfDevices);
        modalOrderPage.clickPaymentMethodsCamera(typeOfPaymentMethod, typeOfPaymentMethodCamera, quantityCameraCP);
        waitABit(3);
        checkAC("Eld prices is not correct", modalOrderPage.compareEldPrice(quantityOfDevices, typeOfPaymentMethod, quantityCameraCP), true);
        checkAC("DepositFee is not correct", modalOrderPage.compareDepositFee(typeOfDevices, quantityOfDevices), true);
        checkAC("DeliveryPrice is not correct", modalOrderPage.compareDeliveryPrice(neededStatePickUpFromOffice), true);
        checkAC("EldPinCable prices is not correct", modalOrderPage.compareEldPinCable(quantityPinCable, typeOfDevices), true);
        checkAC("EldOBDPinCable prices is not correct", modalOrderPage.compareEldOBDPinCable(quantityOBDPinCable, typeOfDevices), true);
        checkAC("EldStickerLabel prices is not correct", modalOrderPage.compareEldStickerLabel(quantitySticker, typeOfDevices), true);
        checkAC("CP2MonthFee is not correct", modalOrderPage.compareCP2MonthFee(quantityCameraCP), true);
        checkAC("CameraSetupFee is not correct", modalOrderPage.compareCameraSetupFee(quantityCameraCP), true);
        checkAC("CameraInstallationFee is not correct", modalOrderPage.compareCameraInstallationFee(quantityCameraCP), true);
        checkAC("EzSmartCamCP2 prices is not correct", modalOrderPage.compareEzSmartCamCP2(quantityCameraCP), true);
        checkAC("EzSmartCamSVA prices is not correct", modalOrderPage.compareEzSmartCamSVA(quantityCameraSVA), true);
        checkAC("SdCard prices is not correct", modalOrderPage.compareSdCard(quantityCameraCP, valueSdCard), true);
        String totalOrderFromFront = modalOrderPage.getTotalOrder();
        double sumTotalOrder = modalOrderPage.totalOrderPrice(typeOfDevices, quantityOfDevices, typeOfPaymentMethod, quantityPinCable, quantityOBDPinCable, quantitySticker, quantityCameraCP, quantityCameraSVA, valueSdCard);
        checkAC("Total Order is not correct", modalOrderPage.compareTotalOrder(sumTotalOrder, totalOrderFromFront), true);


        modalOrderPage.doAgreeAgreement(typeOfDevices, quantityOfDevices);
        modalOrderPage.doAgreementCamera(quantityCameraCP);
        modalOrderPage.clickButtonOrder();
        waitABit(2);

        String idLastOrderAfterTest = utilsForDB.getLastOrderId(userIdString, dataSoloId.get("soloId").toString());
        checkAC("New order was not created", idLastOrderBeforeTest.equals(idLastOrderAfterTest) , false);

        String orderStatus = utilsForDB.getOrderStatus(idLastOrderAfterTest);
        checkAC("Order is not Paid", financesPage.comparePaidOrderStatus(orderStatus) , true);
        checkAC("Eld status in Paid order is not correct", eldPage.compareEldStatusInPaidOrder(idLastOrderAfterTest, quantityOfDevices), true);

        dashboardPage.goToFinancesPage();
        String dueForLastOrder = utilsForDB.getLastDueForSolo(dataSoloId.get("soloId").toString());
        String userBalance = utilsForDB.getCurrentDueEzFinancesSolo(dataSoloId.get("soloId").toString());
        checkAC("Balance is not correct", financesPage.compareBalance(currentDue, dueForLastOrder, userBalance), true);
    }
    @Test
    public void cancelDevicesBySolo() throws SQLException, IOException, ClassNotFoundException {

        String idLastOrderAfterTest = utilsForDB.getLastOrderId(userIdString, dataSoloId.get("soloId").toString());
        String dueForLastOrder = utilsForDB.getLastDueForSolo(dataSoloId.get("soloId").toString());
        dashboardPage.goToEldPage();
        eldPage.cancelEldDevices(idLastOrderAfterTest, quantityOfDevices, quantityCameraCP);
        String orderStatus = utilsForDB.getOrderStatus(idLastOrderAfterTest);
        checkAC("Order with devices is not canceled", eldPage.compareCancelStatusOrder(orderStatus, quantityOfDevices), true);
        checkAC("ELD is present in canceled order", utilsForDB.isEldBlinded(idLastOrderAfterTest), false);

        String userBalance = utilsForDB.getCurrentDueEzFinancesSolo(dataSoloId.get("soloId").toString());
        checkAC("Balance is not correct", financesPage.compareBalanceIfCanceled(currentDue, dueForLastOrder, quantityOfDevices, userBalance), true);

    }
    @Test
    public void managerCanceledOrder() throws SQLException, IOException, ClassNotFoundException {

        tearDown();
        setUp();

        String idLastOrderAfterTest = utilsForDB.getLastOrderId(userIdString, dataSoloId.get("soloId").toString());
        String dueForLastOrder = utilsForDB.getLastDueForSolo(dataSoloId.get("soloId").toString());
        loginPage.userValidLogIn(dataForManagerValidLogIn.get("login").toString(),dataForManagerValidLogIn.get("pass").toString());
        dashboardPage.goToEldPage();
        managerEldPage.openOrderInfo(idLastOrderAfterTest);
        checkAC("Full Order Price is not correct", orderInfoPage.compareFullOrderPrice(dueForLastOrder), true);
        orderInfoPage.selectOrderStatus("2");
        orderInfoPage.clickButtonSave();
        String orderCancelStatus = utilsForDB.getOrderStatus(idLastOrderAfterTest);
        checkAC("Order with devices is not canceled", financesPage.compareCancelOrderStatus(orderCancelStatus), true);
        checkAC("ELD is present in canceled order", utilsForDB.isEldBlinded(idLastOrderAfterTest), false);

        String userBalance = utilsForDB.getCurrentDueEzFinancesSolo(dataSoloId.get("soloId").toString());
        checkAC("Balance is not correct", financesPage.compareBalanceIfCanceledManager(currentDue, dueForLastOrder, userBalance), true);

    }

    @Test
    public void managerCompletedOrder() throws SQLException, IOException, ClassNotFoundException {

        tearDown();
        setUp();

        String idLastOrderAfterTest = utilsForDB.getLastOrderId(userIdString, dataSoloId.get("soloId").toString());
        String dueForLastOrder = utilsForDB.getLastDueForSolo(dataSoloId.get("soloId").toString());
        loginPage.userValidLogIn(dataForManagerValidLogIn.get("login").toString(), dataForManagerValidLogIn.get("pass").toString());
        dashboardPage.goToEldPage();
        managerEldPage.openOrderInfo(idLastOrderAfterTest);
        checkAC("Full Order Price is not correct", orderInfoPage.compareFullOrderPrice(dueForLastOrder), true);
        orderInfoPage.completedOrder();

        String orderStatus = utilsForDB.getOrderStatus(idLastOrderAfterTest);
        checkAC("Order is not completed", financesPage.compareCompletedOrderStatus(orderStatus), true);
        checkAC("Eld status in Completed order is not correct", eldPage.compareEldStatusInCompletedOrder(idLastOrderAfterTest, quantityOfDevices), true);

        String userBalance = utilsForDB.getCurrentDueEzFinancesSolo(dataSoloId.get("soloId").toString());
        checkAC("Balance is not correct", financesPage.compareBalance(currentDue, dueForLastOrder, userBalance), true);


    }
}
