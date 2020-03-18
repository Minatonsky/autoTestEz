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

public class ManagerOrderParamsTest extends ParentTest {
    String typeOfDevices, quantityOfDevices, typeOfPaymentMethod, quantityPinCable, quantityOBDPinCable, quantitySticker, quantityCameraCP, valueSdCard, quantityCameraSVA, typeOfPaymentMethodCamera, neededStatePickUpFromOffice, neededStateOvernightDelivery, currentDue;

    public ManagerOrderParamsTest(String typeOfDevices, String quantityOfDevices, String typeOfPaymentMethod, String quantityPinCable, String quantityOBDPinCable, String quantitySticker, String quantityCameraCP, String valueSdCard, String quantityCameraSVA, String typeOfPaymentMethodCamera, String neededStatePickUpFromOffice, String neededStateOvernightDelivery, String currentDue) throws IOException {
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
    Map dataForFleetValidLogIn = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "validFleetLogin");
    Map dataFleetId = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "validFleetLogin");

    String fleetString = "fleetId";
    String userIdString = "userId";

    @Parameterized.Parameters(name = "Type devices: {0}, quantity devices: {1}, payment method: {2}, Pin cable: {3}, OBD cable: {4}, sticker: {5}, cameraCP: {6}, cd card: {7}, cameraCVA: {8}, payment method cam: {9}, PickUpFromOffice: {10}, OvernightDelivery: {11}, current due: {12}")
    public static Collection testData() throws IOException {
        InputStream spreadsheet = new FileInputStream(configProperties.DATA_FILE_PATH() + "testParameterizedOrder.xls");
        return new SpreadsheetData(spreadsheet,"suitOrderListData").getData();
    }
    @Before

    public void managerOpenEldOrder() throws IOException, InterruptedException, SQLException, ClassNotFoundException {

        loginFPage.logInAndOpenMenu(dataForManagerValidLogIn.get("login").toString(),dataForManagerValidLogIn.get("pass").toString());
        dashboardFPage.goToEldPage();
        managerEldFPage.clickOnNewOrderButton();

    }
    @Test
    public void managerDoOrderSoloAgree() throws IOException, SQLException, ClassNotFoundException {
        eldFPage.checkAndCancelNewOrderBeforeTest(userIdString, dataSoloId.get("soloId").toString());
        String idLastOrderBeforeTest = utilsForDB.getLastOrderId(userIdString, dataSoloId.get("soloId").toString());
        utilsForDB.setCurrentDueForSolo(currentDue, dataSoloId.get("soloId").toString());

        managerModalEldFPage.selectSoloDriverInOrder(dataForSoloValidLogIn.get("login").toString());
        modalOrderFPage.enterPersonalData(personalDataForEldOrder.get("deliveryState").toString(), personalDataForEldOrder.get("firstName").toString(), personalDataForEldOrder.get("lastName").toString(), personalDataForEldOrder.get("phone").toString(), personalDataForEldOrder.get("addressLine").toString(), personalDataForEldOrder.get("aptNumber").toString(), personalDataForEldOrder.get("deliveryCity").toString(), personalDataForEldOrder.get("zipCode").toString());

        modalOrderFPage.enterOrderData(typeOfDevices, quantityOfDevices, quantityPinCable, quantityOBDPinCable, quantitySticker, quantityCameraCP, valueSdCard, quantityCameraSVA, neededStatePickUpFromOffice, neededStateOvernightDelivery);
        modalOrderFPage.clickPaymentMethods(typeOfPaymentMethod, quantityOfDevices);
        modalOrderFPage.clickPaymentMethodsCamera(typeOfPaymentMethod, typeOfPaymentMethodCamera, quantityCameraCP);

        checkAC("Eld prices is not correct", modalOrderFPage.compareEldPrice(quantityOfDevices, typeOfPaymentMethod, quantityCameraCP), true);
        checkAC("DepositFee is not correct", modalOrderFPage.compareDepositFee(typeOfDevices, quantityOfDevices), true);
        checkAC("DeliveryPrice is not correct", modalOrderFPage.compareDeliveryPrice(neededStatePickUpFromOffice), true);
        checkAC("EldPinCable prices is not correct", modalOrderFPage.compareEldPinCable(quantityPinCable, typeOfDevices), true);
        checkAC("EldOBDPinCable prices is not correct", modalOrderFPage.compareEldOBDPinCable(quantityOBDPinCable, typeOfDevices), true);
        checkAC("EldStickerLabel prices is not correct", modalOrderFPage.compareEldStickerLabel(quantitySticker, typeOfDevices), true);
        checkAC("CP2MonthFee is not correct", modalOrderFPage.compareCP2MonthFee(quantityCameraCP), true);
        checkAC("CameraSetupFee is not correct", modalOrderFPage.compareCameraSetupFee(quantityCameraCP), true);
        checkAC("CameraInstallationFee is not correct", modalOrderFPage.compareCameraInstallationFee(quantityCameraCP), true);
        checkAC("EzSmartCamCP2 prices is not correct", modalOrderFPage.compareEzSmartCamCP2(quantityCameraCP), true);
        checkAC("EzSmartCamSVA prices is not correct", modalOrderFPage.compareEzSmartCamSVA(quantityCameraSVA), true);
        checkAC("SdCard prices is not correct", modalOrderFPage.compareSdCard(quantityCameraCP, valueSdCard), true);
        String totalOrderFromFront = modalOrderFPage.getTotalOrder();
        double sumTotalOrder = modalOrderFPage.totalOrderPrice(typeOfDevices, quantityOfDevices, typeOfPaymentMethod, quantityPinCable, quantityOBDPinCable, quantitySticker, quantityCameraCP, quantityCameraSVA, valueSdCard);
        checkAC("Total Order is not correct", modalOrderFPage.compareTotalOrder(sumTotalOrder, totalOrderFromFront), true);

        modalOrderFPage.clickButtonOrder();
        waitABit(3);
        String idLastOrderAfterTest = utilsForDB.getLastOrderId(userIdString, dataSoloId.get("soloId").toString());
        checkAC("New order was not created", idLastOrderBeforeTest.equals(idLastOrderAfterTest) , false);

        String orderStatus = utilsForDB.getOrderStatus(idLastOrderAfterTest);
        checkAC("Order is not New status", financesFPage.compareNewOrderStatus(orderStatus, currentDue, quantityOfDevices, quantityCameraCP) , true);
        checkAC("Eld status in New order is not correct", eldFPage.compareEldStatusInNewOrder(idLastOrderAfterTest, quantityOfDevices), true);

        tearDown();
        setUp();

// user agree order
        loginFPage.logInAndOpenMenu(dataForSoloValidLogIn.get("login").toString(),dataForSoloValidLogIn.get("pass").toString());
        waitABit(10);
        modalOrderFPage.doAgreeAgreementForManagerOrder(typeOfDevices, quantityOfDevices, quantityCameraCP);
        waitABit(10);

        dashboardFPage.goToFinancesPage();
        financesFPage.payCurrentInvoiceForOrderByManager(currentDue, quantityOfDevices, quantityCameraCP);
        String dueForLastOrder = utilsForDB.getLastDueForSolo(dataSoloId.get("soloId").toString());
        String userBalance = utilsForDB.getCurrentDueEzFinancesSolo(dataSoloId.get("soloId").toString());
        checkAC("Balance is not correct", financesFPage.compareBalance(currentDue, dueForLastOrder, userBalance), true);

        String orderStatusPaid = utilsForDB.getOrderStatus(idLastOrderAfterTest);
        checkAC("Order is not Paid", financesFPage.comparePaidOrderStatus(orderStatusPaid) , true);
        checkAC("Eld status in Paid order is not correct", eldFPage.compareEldStatusInPaidOrder(idLastOrderAfterTest, quantityOfDevices), true);

        tearDown();
        setUp();

// Manager completed order

        loginFPage.logInAndOpenMenu(dataForManagerValidLogIn.get("login").toString(),dataForManagerValidLogIn.get("pass").toString());
        dashboardFPage.goToEldPage();

        managerEldPage.openOrderInfo(idLastOrderAfterTest);
        checkAC("Full Order Price is not correct", orderInfoFPage.compareFullOrderPrice(dueForLastOrder), true);
        orderInfoFPage.completedOrder();
        waitABit(3);

        String orderStatusCompleted = utilsForDB.getOrderStatus(idLastOrderAfterTest);
        checkAC("Order is not completed", financesFPage.compareCompletedOrderStatus(orderStatusCompleted), true);
        checkAC("Eld status in Completed order is not correct", eldFPage.compareEldStatusInCompletedOrder(idLastOrderAfterTest, quantityOfDevices), true);

        String userBalance2 = utilsForDB.getCurrentDueEzFinancesSolo(dataSoloId.get("soloId").toString());
        checkAC("Balance is not correct", financesFPage.compareBalance(currentDue, dueForLastOrder, userBalance2), true);


    }
    @Test
    public void managerDoOrderSoloCanceled() throws IOException, SQLException, ClassNotFoundException {
        eldFPage.checkAndCancelNewOrderBeforeTest(userIdString, dataSoloId.get("soloId").toString());
        String idLastOrderBeforeTest = utilsForDB.getLastOrderId(userIdString, dataSoloId.get("soloId").toString());
        utilsForDB.setCurrentDueForSolo(currentDue, dataSoloId.get("soloId").toString());

        managerModalEldFPage.selectSoloDriverInOrder(dataForSoloValidLogIn.get("login").toString());
        modalOrderFPage.enterPersonalData(personalDataForEldOrder.get("deliveryState").toString(), personalDataForEldOrder.get("firstName").toString(), personalDataForEldOrder.get("lastName").toString(), personalDataForEldOrder.get("phone").toString(), personalDataForEldOrder.get("addressLine").toString(), personalDataForEldOrder.get("aptNumber").toString(), personalDataForEldOrder.get("deliveryCity").toString(), personalDataForEldOrder.get("zipCode").toString());

        modalOrderFPage.enterOrderData(typeOfDevices, quantityOfDevices, quantityPinCable, quantityOBDPinCable, quantitySticker, quantityCameraCP, valueSdCard, quantityCameraSVA, neededStatePickUpFromOffice, neededStateOvernightDelivery);
        modalOrderFPage.clickPaymentMethods(typeOfPaymentMethod, quantityOfDevices);
        modalOrderFPage.clickPaymentMethodsCamera(typeOfPaymentMethod, typeOfPaymentMethodCamera, quantityCameraCP);

        checkAC("Eld prices is not correct", modalOrderFPage.compareEldPrice(quantityOfDevices, typeOfPaymentMethod, quantityCameraCP), true);
        checkAC("DepositFee is not correct", modalOrderFPage.compareDepositFee(typeOfDevices, quantityOfDevices), true);
        checkAC("DeliveryPrice is not correct", modalOrderFPage.compareDeliveryPrice(neededStatePickUpFromOffice), true);
        checkAC("EldPinCable prices is not correct", modalOrderFPage.compareEldPinCable(quantityPinCable, typeOfDevices), true);
        checkAC("EldOBDPinCable prices is not correct", modalOrderFPage.compareEldOBDPinCable(quantityOBDPinCable, typeOfDevices), true);
        checkAC("EldStickerLabel prices is not correct", modalOrderFPage.compareEldStickerLabel(quantitySticker, typeOfDevices), true);
        checkAC("CP2MonthFee is not correct", modalOrderFPage.compareCP2MonthFee(quantityCameraCP), true);
        checkAC("CameraSetupFee is not correct", modalOrderFPage.compareCameraSetupFee(quantityCameraCP), true);
        checkAC("CameraInstallationFee is not correct", modalOrderFPage.compareCameraInstallationFee(quantityCameraCP), true);
        checkAC("EzSmartCamCP2 prices is not correct", modalOrderFPage.compareEzSmartCamCP2(quantityCameraCP), true);
        checkAC("EzSmartCamSVA prices is not correct", modalOrderFPage.compareEzSmartCamSVA(quantityCameraSVA), true);
        checkAC("SdCard prices is not correct", modalOrderFPage.compareSdCard(quantityCameraCP, valueSdCard), true);
        String totalOrderFromFront = modalOrderFPage.getTotalOrder();
        double sumTotalOrder = modalOrderFPage.totalOrderPrice(typeOfDevices, quantityOfDevices, typeOfPaymentMethod, quantityPinCable, quantityOBDPinCable, quantitySticker, quantityCameraCP, quantityCameraSVA, valueSdCard);
        checkAC("Total Order is not correct", modalOrderFPage.compareTotalOrder(sumTotalOrder, totalOrderFromFront), true);

        modalOrderFPage.clickButtonOrder();
        waitABit(3);
        String idLastOrderAfterTest = utilsForDB.getLastOrderId(userIdString, dataSoloId.get("soloId").toString());
        checkAC("New order was not created", idLastOrderBeforeTest.equals(idLastOrderAfterTest) , false);

        String orderStatus = utilsForDB.getOrderStatus(idLastOrderAfterTest);
        checkAC("Order is not New status", financesFPage.compareNewOrderStatus(orderStatus, currentDue, quantityOfDevices, quantityCameraCP) , true);
        checkAC("Eld status in New order is not correct", eldFPage.compareEldStatusInNewOrder(idLastOrderAfterTest, quantityOfDevices), true);

        tearDown();
        setUp();

        // user canceled order
        String dueForLastOrder = utilsForDB.getLastDueForSolo(dataSoloId.get("soloId").toString());
        loginFPage.logInAndOpenMenu(dataForSoloValidLogIn.get("login").toString(),dataForSoloValidLogIn.get("pass").toString());

        waitABit(10);
        modalOrderFPage.doCancelAgreementForManagerOrder(typeOfDevices, quantityOfDevices, quantityCameraCP);
        waitABit(10);

        String orderCancelStatus = utilsForDB.getOrderStatus(idLastOrderAfterTest);
        checkAC("Order with devices is not canceled", eldFPage.compareCancelStatusOrder(orderCancelStatus, quantityOfDevices), true);
        checkAC("ELD is present in canceled order", utilsForDB.isEldBlinded(idLastOrderAfterTest), false);

        String userBalance = utilsForDB.getCurrentDueEzFinancesSolo(dataSoloId.get("soloId").toString());
        checkAC("Balance is not correct", financesFPage.compareBalanceIfCanceled(currentDue, dueForLastOrder, quantityOfDevices, userBalance), true);

    }
    @Test
    public void managerDoOrderFleetAgree() throws IOException, InterruptedException, SQLException, ClassNotFoundException {
        eldFPage.checkAndCancelNewOrderBeforeTest(fleetString, dataFleetId.get("fleetId").toString());
        String idLastOrderBeforeTest = utilsForDB.getLastOrderId(fleetString, dataFleetId.get("fleetId").toString());
        utilsForDB.setCurrentDueForFleet(currentDue, dataFleetId.get("fleetId").toString());

        managerModalEldFPage.selectFleetInOrder(dataForFleetValidLogIn.get("usdot").toString());
        modalOrderFPage.enterPersonalData(personalDataForEldOrder.get("deliveryState").toString(), personalDataForEldOrder.get("firstName").toString(), personalDataForEldOrder.get("lastName").toString(), personalDataForEldOrder.get("phone").toString(), personalDataForEldOrder.get("addressLine").toString(), personalDataForEldOrder.get("aptNumber").toString(), personalDataForEldOrder.get("deliveryCity").toString(), personalDataForEldOrder.get("zipCode").toString());

        modalOrderFPage.enterOrderData(typeOfDevices, quantityOfDevices, quantityPinCable, quantityOBDPinCable, quantitySticker, quantityCameraCP, valueSdCard, quantityCameraSVA, neededStatePickUpFromOffice, neededStateOvernightDelivery);
        modalOrderFPage.clickPaymentMethods(typeOfPaymentMethod, quantityOfDevices);
        modalOrderFPage.clickPaymentMethodsCamera(typeOfPaymentMethod, typeOfPaymentMethodCamera, quantityCameraCP);

        checkAC("Eld prices is not correct", modalOrderFPage.compareEldPrice(quantityOfDevices, typeOfPaymentMethod, quantityCameraCP), true);
        checkAC("DepositFee is not correct", modalOrderFPage.compareDepositFee(typeOfDevices, quantityOfDevices), true);
        checkAC("DeliveryPrice is not correct", modalOrderFPage.compareDeliveryPrice(neededStatePickUpFromOffice), true);
        checkAC("EldPinCable prices is not correct", modalOrderFPage.compareEldPinCable(quantityPinCable, typeOfDevices), true);
        checkAC("EldOBDPinCable prices is not correct", modalOrderFPage.compareEldOBDPinCable(quantityOBDPinCable, typeOfDevices), true);
        checkAC("EldStickerLabel prices is not correct", modalOrderFPage.compareEldStickerLabel(quantitySticker, typeOfDevices), true);
        checkAC("CP2MonthFee is not correct", modalOrderFPage.compareCP2MonthFee(quantityCameraCP), true);
        checkAC("CameraSetupFee is not correct", modalOrderFPage.compareCameraSetupFee(quantityCameraCP), true);
        checkAC("CameraInstallationFee is not correct", modalOrderFPage.compareCameraInstallationFee(quantityCameraCP), true);
        checkAC("EzSmartCamCP2 prices is not correct", modalOrderFPage.compareEzSmartCamCP2(quantityCameraCP), true);
        checkAC("EzSmartCamSVA prices is not correct", modalOrderFPage.compareEzSmartCamSVA(quantityCameraSVA), true);
        checkAC("SdCard prices is not correct", modalOrderFPage.compareSdCard(quantityCameraCP, valueSdCard), true);
        String totalOrderFromFront = modalOrderFPage.getTotalOrder();
        double sumTotalOrder = modalOrderFPage.totalOrderPrice(typeOfDevices, quantityOfDevices, typeOfPaymentMethod, quantityPinCable, quantityOBDPinCable, quantitySticker, quantityCameraCP, quantityCameraSVA, valueSdCard);
        checkAC("Total Order is not correct", modalOrderFPage.compareTotalOrder(sumTotalOrder, totalOrderFromFront), true);

        modalOrderFPage.clickButtonOrder();
        waitABit(3);
        String idLastOrderAfterTest = utilsForDB.getLastOrderId(fleetString, dataFleetId.get("fleetId").toString());
        checkAC("New order was not created", idLastOrderBeforeTest.equals(idLastOrderAfterTest) , false);

        String orderStatus = utilsForDB.getOrderStatus(idLastOrderAfterTest);
        checkAC("Order is not New status", financesFPage.compareNewOrderStatus(orderStatus, currentDue, quantityOfDevices, quantityCameraCP) , true);
        checkAC("Eld status in New order is not correct", eldFPage.compareEldStatusInNewOrder(idLastOrderAfterTest, quantityOfDevices), true);

        tearDown();
        setUp();
// user agree order
        loginFPage.logInWithOutOpenMenu(dataForFleetValidLogIn.get("login").toString(),dataForFleetValidLogIn.get("pass").toString());
        modalOrderFPage.doAgreeAgreementForManagerOrder(typeOfDevices, quantityOfDevices, quantityCameraCP);
        waitABit(3);

        dashboardFPage.goToSafetyPage();
        dashboardFPage.goToFinancesPage();
        financesFPage.payCurrentInvoiceForOrderByManager(currentDue, quantityOfDevices, quantityCameraCP);
        waitABit(5);
        String dueForLastOrder = utilsForDB.getLastDueForFleet(dataFleetId.get("fleetId").toString());
        String userBalance = utilsForDB.getCurrentDueEzFinancesFleet(dataFleetId.get("fleetId").toString());
        checkAC("Balance is not correct", financesFPage.compareBalance(currentDue, dueForLastOrder, userBalance), true);

        String orderStatusPaid = utilsForDB.getOrderStatus(idLastOrderAfterTest);
        checkAC("Order is not Paid", financesFPage.comparePaidOrderStatus(orderStatusPaid) , true);
        checkAC("Eld status in Paid order is not correct", eldFPage.compareEldStatusInPaidOrder(idLastOrderAfterTest, quantityOfDevices), true);

        tearDown();
        setUp();
// Manager completed order
        loginFPage.logInAndOpenMenu(dataForManagerValidLogIn.get("login").toString(),dataForManagerValidLogIn.get("pass").toString());
        dashboardFPage.goToEldPage();

        managerEldFPage.openOrderInfo(idLastOrderAfterTest);
        checkAC("Full Order Price is not correct", orderInfoFPage.compareFullOrderPrice(dueForLastOrder), true);
        orderInfoFPage.completedOrder();

        waitABit(3);
        String orderStatusCompleted = utilsForDB.getOrderStatus(idLastOrderAfterTest);
        checkAC("Order is not completed", financesFPage.compareCompletedOrderStatus(orderStatusCompleted), true);
        checkAC("Eld status in Completed order is not correct", eldFPage.compareEldStatusInCompletedOrder(idLastOrderAfterTest, quantityOfDevices), true);

        String userBalance2 = utilsForDB.getCurrentDueEzFinancesFleet(dataFleetId.get("fleetId").toString());
        checkAC("Balance is not correct", financesFPage.compareBalance(currentDue, dueForLastOrder, userBalance2), true);


    }
    @Test
    public void managerDoOrderFleetCanceled() throws IOException, InterruptedException, SQLException, ClassNotFoundException {
        eldFPage.checkAndCancelNewOrderBeforeTest(fleetString, dataFleetId.get("fleetId").toString());
        String idLastOrderBeforeTest = utilsForDB.getLastOrderId(fleetString, dataFleetId.get("fleetId").toString());
        utilsForDB.setCurrentDueForFleet(currentDue, dataFleetId.get("fleetId").toString());

        managerModalEldFPage.selectFleetInOrder(dataForFleetValidLogIn.get("usdot").toString());
        modalOrderFPage.enterPersonalData(personalDataForEldOrder.get("deliveryState").toString(), personalDataForEldOrder.get("firstName").toString(), personalDataForEldOrder.get("lastName").toString(), personalDataForEldOrder.get("phone").toString(), personalDataForEldOrder.get("addressLine").toString(), personalDataForEldOrder.get("aptNumber").toString(), personalDataForEldOrder.get("deliveryCity").toString(), personalDataForEldOrder.get("zipCode").toString());

        modalOrderFPage.enterOrderData(typeOfDevices, quantityOfDevices, quantityPinCable, quantityOBDPinCable, quantitySticker, quantityCameraCP, valueSdCard, quantityCameraSVA, neededStatePickUpFromOffice, neededStateOvernightDelivery);
        modalOrderFPage.clickPaymentMethods(typeOfPaymentMethod, quantityOfDevices);
        modalOrderFPage.clickPaymentMethodsCamera(typeOfPaymentMethod, typeOfPaymentMethodCamera, quantityCameraCP);

        checkAC("Eld prices is not correct", modalOrderFPage.compareEldPrice(quantityOfDevices, typeOfPaymentMethod, quantityCameraCP), true);
        checkAC("DepositFee is not correct", modalOrderFPage.compareDepositFee(typeOfDevices, quantityOfDevices), true);
        checkAC("DeliveryPrice is not correct", modalOrderFPage.compareDeliveryPrice(neededStatePickUpFromOffice), true);
        checkAC("EldPinCable prices is not correct", modalOrderFPage.compareEldPinCable(quantityPinCable, typeOfDevices), true);
        checkAC("EldOBDPinCable prices is not correct", modalOrderFPage.compareEldOBDPinCable(quantityOBDPinCable, typeOfDevices), true);
        checkAC("EldStickerLabel prices is not correct", modalOrderFPage.compareEldStickerLabel(quantitySticker, typeOfDevices), true);
        checkAC("CP2MonthFee is not correct", modalOrderFPage.compareCP2MonthFee(quantityCameraCP), true);
        checkAC("CameraSetupFee is not correct", modalOrderFPage.compareCameraSetupFee(quantityCameraCP), true);
        checkAC("CameraInstallationFee is not correct", modalOrderFPage.compareCameraInstallationFee(quantityCameraCP), true);
        checkAC("EzSmartCamCP2 prices is not correct", modalOrderFPage.compareEzSmartCamCP2(quantityCameraCP), true);
        checkAC("EzSmartCamSVA prices is not correct", modalOrderFPage.compareEzSmartCamSVA(quantityCameraSVA), true);
        checkAC("SdCard prices is not correct", modalOrderFPage.compareSdCard(quantityCameraCP, valueSdCard), true);
        String totalOrderFromFront = modalOrderFPage.getTotalOrder();
        double sumTotalOrder = modalOrderFPage.totalOrderPrice(typeOfDevices, quantityOfDevices, typeOfPaymentMethod, quantityPinCable, quantityOBDPinCable, quantitySticker, quantityCameraCP, quantityCameraSVA, valueSdCard);
        checkAC("Total Order is not correct", modalOrderFPage.compareTotalOrder(sumTotalOrder, totalOrderFromFront), true);

        modalOrderFPage.clickButtonOrder();
        waitABit(3);
        String idLastOrderAfterTest = utilsForDB.getLastOrderId(fleetString, dataFleetId.get("fleetId").toString());
        checkAC("New order was not created", idLastOrderBeforeTest.equals(idLastOrderAfterTest) , false);

        String orderStatus = utilsForDB.getOrderStatus(idLastOrderAfterTest);
        checkAC("Order is not New status", financesFPage.compareNewOrderStatus(orderStatus, currentDue, quantityOfDevices, quantityCameraCP) , true);
        checkAC("Eld status in New order is not correct", eldFPage.compareEldStatusInNewOrder(idLastOrderAfterTest, quantityOfDevices), true);

        tearDown();
        setUp();

// fleet canceled order
        String dueForLastOrder = utilsForDB.getLastDueForFleet(dataFleetId.get("fleetId").toString());
        loginFPage.logInWithOutOpenMenu(dataForFleetValidLogIn.get("login").toString(),dataForFleetValidLogIn.get("pass").toString());

        waitABit(3);
        modalOrderFPage.doCancelAgreementForManagerOrder(typeOfDevices, quantityOfDevices, quantityCameraCP);
        waitABit(3);

        String orderCancelStatus = utilsForDB.getOrderStatus(idLastOrderAfterTest);
        checkAC("Order with devices is not canceled", eldFPage.compareCancelStatusOrder(orderCancelStatus, quantityOfDevices), true);
        checkAC("ELD is present in canceled order", utilsForDB.isEldBlinded(idLastOrderAfterTest), false);

        String userBalance = utilsForDB.getCurrentDueEzFinancesFleet(dataFleetId.get("fleetId").toString());
        checkAC("Balance is not correct", financesFPage.compareBalanceIfCanceled(currentDue, dueForLastOrder, quantityOfDevices, userBalance), true);

    }

}
