package parameterizedTest;

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

        loginPage.userValidLogIn(dataForManagerValidLogIn.get("login").toString(),dataForManagerValidLogIn.get("pass").toString());
        dashboardPage.goToEldPage();
        managerEldPage.clickOnNewOrderButton();

    }
    @Test
    public void managerDoOrderSoloAgree() throws IOException, SQLException, ClassNotFoundException {
        eldPage.checkAndCancelNewOrderBeforeTest(userIdString, dataSoloId.get("soloId").toString());
        String idLastOrderBeforeTest = utilsForDB.getLastOrderId(userIdString, dataSoloId.get("soloId").toString());
        utilsForDB.setCurrentDueForSolo(currentDue, dataSoloId.get("soloId").toString());

        managerModalEldPage.selectSoloDriverInOrder(dataForSoloValidLogIn.get("login").toString());
        modalOrderPage.enterPersonalData(personalDataForEldOrder.get("deliveryState").toString(), personalDataForEldOrder.get("firstName").toString(), personalDataForEldOrder.get("lastName").toString(), personalDataForEldOrder.get("phone").toString(), personalDataForEldOrder.get("addressLine").toString(), personalDataForEldOrder.get("aptNumber").toString(), personalDataForEldOrder.get("deliveryCity").toString(), personalDataForEldOrder.get("zipCode").toString());

        modalOrderPage.enterOrderData(typeOfDevices, quantityOfDevices, quantityPinCable, quantityOBDPinCable, quantitySticker, quantityCameraCP, valueSdCard, quantityCameraSVA, neededStatePickUpFromOffice, neededStateOvernightDelivery);
        modalOrderPage.clickPaymentMethods(typeOfPaymentMethod, quantityOfDevices);
        modalOrderPage.clickPaymentMethodsCamera(typeOfPaymentMethod, typeOfPaymentMethodCamera, quantityCameraCP);

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

        modalOrderPage.clickButtonOrder();
        waitABit(3);
        String idLastOrderAfterTest = utilsForDB.getLastOrderId(userIdString, dataSoloId.get("soloId").toString());
        checkAC("New order was not created", idLastOrderBeforeTest.equals(idLastOrderAfterTest) , false);

        String orderStatus = utilsForDB.getOrderStatus(idLastOrderAfterTest);
        checkAC("Order is not New status", financesPage.compareNewOrderStatus(orderStatus, currentDue, quantityOfDevices, quantityCameraCP) , true);
        checkAC("Eld status in New order is not correct", eldPage.compareEldStatusInNewOrder(idLastOrderAfterTest, quantityOfDevices), true);

        tearDown();
        setUp();

// user agree order
        loginPage.userValidLogIn(dataForSoloValidLogIn.get("login").toString(),dataForSoloValidLogIn.get("pass").toString());
        waitABit(10);
        modalOrderPage.doAgreeAgreementForManagerOrder(typeOfDevices, quantityOfDevices, quantityCameraCP);
        waitABit(10);

        dashboardPage.goToFinancesPage();
        financesPage.payCurrentInvoiceForOrderByManager(currentDue, quantityOfDevices, quantityCameraCP);
        String dueForLastOrder = utilsForDB.getLastDueForSolo(dataSoloId.get("soloId").toString());
        String userBalance = utilsForDB.getCurrentDueEzFinancesSolo(dataSoloId.get("soloId").toString());
        checkAC("Balance is not correct", financesPage.compareBalance(currentDue, dueForLastOrder, userBalance), true);

        String orderStatusPaid = utilsForDB.getOrderStatus(idLastOrderAfterTest);
        checkAC("Order is not Paid", financesPage.comparePaidOrderStatus(orderStatusPaid) , true);
        checkAC("Eld status in Paid order is not correct", eldPage.compareEldStatusInPaidOrder(idLastOrderAfterTest, quantityOfDevices), true);

        tearDown();
        setUp();

// Manager completed order

        loginPage.userValidLogIn(dataForManagerValidLogIn.get("login").toString(),dataForManagerValidLogIn.get("pass").toString());
        dashboardPage.goToEldPage();

        managerEldPage.openOrderInfo(idLastOrderAfterTest);
        checkAC("Full Order Price is not correct", orderInfoPage.compareFullOrderPrice(dueForLastOrder), true);
        orderInfoPage.completedOrder();
        waitABit(3);

        String orderStatusCompleted = utilsForDB.getOrderStatus(idLastOrderAfterTest);
        checkAC("Order is not completed", financesPage.compareCompletedOrderStatus(orderStatusCompleted), true);
        checkAC("Eld status in Completed order is not correct", eldPage.compareEldStatusInCompletedOrder(idLastOrderAfterTest, quantityOfDevices), true);

        String userBalance2 = utilsForDB.getCurrentDueEzFinancesSolo(dataSoloId.get("soloId").toString());
        checkAC("Balance is not correct", financesPage.compareBalance(currentDue, dueForLastOrder, userBalance2), true);


    }
    @Test
    public void managerDoOrderSoloCanceled() throws IOException, SQLException, ClassNotFoundException {
        eldPage.checkAndCancelNewOrderBeforeTest(userIdString, dataSoloId.get("soloId").toString());
        String idLastOrderBeforeTest = utilsForDB.getLastOrderId(userIdString, dataSoloId.get("soloId").toString());
        utilsForDB.setCurrentDueForSolo(currentDue, dataSoloId.get("soloId").toString());

        managerModalEldPage.selectSoloDriverInOrder(dataForSoloValidLogIn.get("login").toString());
        modalOrderPage.enterPersonalData(personalDataForEldOrder.get("deliveryState").toString(), personalDataForEldOrder.get("firstName").toString(), personalDataForEldOrder.get("lastName").toString(), personalDataForEldOrder.get("phone").toString(), personalDataForEldOrder.get("addressLine").toString(), personalDataForEldOrder.get("aptNumber").toString(), personalDataForEldOrder.get("deliveryCity").toString(), personalDataForEldOrder.get("zipCode").toString());

        modalOrderPage.enterOrderData(typeOfDevices, quantityOfDevices, quantityPinCable, quantityOBDPinCable, quantitySticker, quantityCameraCP, valueSdCard, quantityCameraSVA, neededStatePickUpFromOffice, neededStateOvernightDelivery);
        modalOrderPage.clickPaymentMethods(typeOfPaymentMethod, quantityOfDevices);
        modalOrderPage.clickPaymentMethodsCamera(typeOfPaymentMethod, typeOfPaymentMethodCamera, quantityCameraCP);

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

        modalOrderPage.clickButtonOrder();
        waitABit(3);
        String idLastOrderAfterTest = utilsForDB.getLastOrderId(userIdString, dataSoloId.get("soloId").toString());
        checkAC("New order was not created", idLastOrderBeforeTest.equals(idLastOrderAfterTest) , false);

        String orderStatus = utilsForDB.getOrderStatus(idLastOrderAfterTest);
        checkAC("Order is not New status", financesPage.compareNewOrderStatus(orderStatus, currentDue, quantityOfDevices, quantityCameraCP) , true);
        checkAC("Eld status in New order is not correct", eldPage.compareEldStatusInNewOrder(idLastOrderAfterTest, quantityOfDevices), true);

        tearDown();
        setUp();

        // user canceled order
        String dueForLastOrder = utilsForDB.getLastDueForSolo(dataSoloId.get("soloId").toString());
        loginPage.userValidLogIn(dataForSoloValidLogIn.get("login").toString(),dataForSoloValidLogIn.get("pass").toString());

        waitABit(10);
        modalOrderPage.doCancelAgreementForManagerOrder(typeOfDevices, quantityOfDevices, quantityCameraCP);
        waitABit(10);

        String orderCancelStatus = utilsForDB.getOrderStatus(idLastOrderAfterTest);
        checkAC("Order with devices is not canceled", eldPage.compareCancelStatusOrder(orderCancelStatus, quantityOfDevices), true);
        checkAC("ELD is present in canceled order", utilsForDB.isEldBlinded(idLastOrderAfterTest), false);

        String userBalance = utilsForDB.getCurrentDueEzFinancesSolo(dataSoloId.get("soloId").toString());
        checkAC("Balance is not correct", financesPage.compareBalanceIfCanceled(currentDue, dueForLastOrder, quantityOfDevices, userBalance), true);

    }
    @Test
    public void managerDoOrderFleetAgree() throws IOException, InterruptedException, SQLException, ClassNotFoundException {
        eldPage.checkAndCancelNewOrderBeforeTest(fleetString, dataFleetId.get("fleetId").toString());
        String idLastOrderBeforeTest = utilsForDB.getLastOrderId(fleetString, dataFleetId.get("fleetId").toString());
        utilsForDB.setCurrentDueForFleet(currentDue, dataFleetId.get("fleetId").toString());

        managerModalEldPage.selectFleetInOrder(dataForFleetValidLogIn.get("usdot").toString());
        modalOrderPage.enterPersonalData(personalDataForEldOrder.get("deliveryState").toString(), personalDataForEldOrder.get("firstName").toString(), personalDataForEldOrder.get("lastName").toString(), personalDataForEldOrder.get("phone").toString(), personalDataForEldOrder.get("addressLine").toString(), personalDataForEldOrder.get("aptNumber").toString(), personalDataForEldOrder.get("deliveryCity").toString(), personalDataForEldOrder.get("zipCode").toString());

        modalOrderPage.enterOrderData(typeOfDevices, quantityOfDevices, quantityPinCable, quantityOBDPinCable, quantitySticker, quantityCameraCP, valueSdCard, quantityCameraSVA, neededStatePickUpFromOffice, neededStateOvernightDelivery);
        modalOrderPage.clickPaymentMethods(typeOfPaymentMethod, quantityOfDevices);
        modalOrderPage.clickPaymentMethodsCamera(typeOfPaymentMethod, typeOfPaymentMethodCamera, quantityCameraCP);

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

        modalOrderPage.clickButtonOrder();
        waitABit(3);
        String idLastOrderAfterTest = utilsForDB.getLastOrderId(fleetString, dataFleetId.get("fleetId").toString());
        checkAC("New order was not created", idLastOrderBeforeTest.equals(idLastOrderAfterTest) , false);

        String orderStatus = utilsForDB.getOrderStatus(idLastOrderAfterTest);
        checkAC("Order is not New status", financesPage.compareNewOrderStatus(orderStatus, currentDue, quantityOfDevices, quantityCameraCP) , true);
        checkAC("Eld status in New order is not correct", eldPage.compareEldStatusInNewOrder(idLastOrderAfterTest, quantityOfDevices), true);

        tearDown();
        setUp();
// user agree order
        loginPage.userValidLogIn(dataForFleetValidLogIn.get("login").toString(),dataForFleetValidLogIn.get("pass").toString());
        modalOrderPage.doAgreeAgreementForManagerOrder(typeOfDevices, quantityOfDevices, quantityCameraCP);
        waitABit(3);

        dashboardPage.goToSafetyPage();
        dashboardPage.goToFinancesPage();
        financesPage.payCurrentInvoiceForOrderByManager(currentDue, quantityOfDevices, quantityCameraCP);
        waitABit(5);
        String dueForLastOrder = utilsForDB.getLastDueForFleet(dataFleetId.get("fleetId").toString());
        String userBalance = utilsForDB.getCurrentDueEzFinancesFleet(dataFleetId.get("fleetId").toString());
        checkAC("Balance is not correct", financesPage.compareBalance(currentDue, dueForLastOrder, userBalance), true);

        String orderStatusPaid = utilsForDB.getOrderStatus(idLastOrderAfterTest);
        checkAC("Order is not Paid", financesPage.comparePaidOrderStatus(orderStatusPaid) , true);
        checkAC("Eld status in Paid order is not correct", eldPage.compareEldStatusInPaidOrder(idLastOrderAfterTest, quantityOfDevices), true);

        tearDown();
        setUp();
// Manager completed order
        loginPage.userValidLogIn(dataForManagerValidLogIn.get("login").toString(),dataForManagerValidLogIn.get("pass").toString());
        dashboardPage.goToEldPage();

        managerEldPage.openOrderInfo(idLastOrderAfterTest);
        checkAC("Full Order Price is not correct", orderInfoPage.compareFullOrderPrice(dueForLastOrder), true);
        orderInfoPage.completedOrder();

        waitABit(3);
        String orderStatusCompleted = utilsForDB.getOrderStatus(idLastOrderAfterTest);
        checkAC("Order is not completed", financesPage.compareCompletedOrderStatus(orderStatusCompleted), true);
        checkAC("Eld status in Completed order is not correct", eldPage.compareEldStatusInCompletedOrder(idLastOrderAfterTest, quantityOfDevices), true);

        String userBalance2 = utilsForDB.getCurrentDueEzFinancesFleet(dataFleetId.get("fleetId").toString());
        checkAC("Balance is not correct", financesPage.compareBalance(currentDue, dueForLastOrder, userBalance2), true);


    }
    @Test
    public void managerDoOrderFleetCanceled() throws IOException, InterruptedException, SQLException, ClassNotFoundException {
        eldPage.checkAndCancelNewOrderBeforeTest(fleetString, dataFleetId.get("fleetId").toString());
        String idLastOrderBeforeTest = utilsForDB.getLastOrderId(fleetString, dataFleetId.get("fleetId").toString());
        utilsForDB.setCurrentDueForFleet(currentDue, dataFleetId.get("fleetId").toString());

        managerModalEldPage.selectFleetInOrder(dataForFleetValidLogIn.get("usdot").toString());
        modalOrderPage.enterPersonalData(personalDataForEldOrder.get("deliveryState").toString(), personalDataForEldOrder.get("firstName").toString(), personalDataForEldOrder.get("lastName").toString(), personalDataForEldOrder.get("phone").toString(), personalDataForEldOrder.get("addressLine").toString(), personalDataForEldOrder.get("aptNumber").toString(), personalDataForEldOrder.get("deliveryCity").toString(), personalDataForEldOrder.get("zipCode").toString());

        modalOrderPage.enterOrderData(typeOfDevices, quantityOfDevices, quantityPinCable, quantityOBDPinCable, quantitySticker, quantityCameraCP, valueSdCard, quantityCameraSVA, neededStatePickUpFromOffice, neededStateOvernightDelivery);
        modalOrderPage.clickPaymentMethods(typeOfPaymentMethod, quantityOfDevices);
        modalOrderPage.clickPaymentMethodsCamera(typeOfPaymentMethod, typeOfPaymentMethodCamera, quantityCameraCP);

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

        modalOrderPage.clickButtonOrder();
        waitABit(3);
        String idLastOrderAfterTest = utilsForDB.getLastOrderId(fleetString, dataFleetId.get("fleetId").toString());
        checkAC("New order was not created", idLastOrderBeforeTest.equals(idLastOrderAfterTest) , false);

        String orderStatus = utilsForDB.getOrderStatus(idLastOrderAfterTest);
        checkAC("Order is not New status", financesPage.compareNewOrderStatus(orderStatus, currentDue, quantityOfDevices, quantityCameraCP) , true);
        checkAC("Eld status in New order is not correct", eldPage.compareEldStatusInNewOrder(idLastOrderAfterTest, quantityOfDevices), true);

        tearDown();
        setUp();

// fleet canceled order
        String dueForLastOrder = utilsForDB.getLastDueForFleet(dataFleetId.get("fleetId").toString());
        loginPage.userValidLogIn(dataForFleetValidLogIn.get("login").toString(),dataForFleetValidLogIn.get("pass").toString());

        waitABit(3);
        modalOrderPage.doCancelAgreementForManagerOrder(typeOfDevices, quantityOfDevices, quantityCameraCP);
        waitABit(3);

        String orderCancelStatus = utilsForDB.getOrderStatus(idLastOrderAfterTest);
        checkAC("Order with devices is not canceled", eldPage.compareCancelStatusOrder(orderCancelStatus, quantityOfDevices), true);
        checkAC("ELD is present in canceled order", utilsForDB.isEldBlinded(idLastOrderAfterTest), false);

        String userBalance = utilsForDB.getCurrentDueEzFinancesFleet(dataFleetId.get("fleetId").toString());
        checkAC("Balance is not correct", financesPage.compareBalanceIfCanceled(currentDue, dueForLastOrder, quantityOfDevices, userBalance), true);

    }

}
