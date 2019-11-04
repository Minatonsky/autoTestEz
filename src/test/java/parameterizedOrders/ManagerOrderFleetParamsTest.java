package parameterizedOrders;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.sql.SQLException;

import static libs.Utils.waitABit;

@RunWith(Parameterized.class)

public class ManagerOrderFleetParamsTest extends ParentManagerOrderParamsTest {

    public ManagerOrderFleetParamsTest(String quantityOfDevices, String typeOfPaymentMethod, String quantityPinCable, String quantityOBDPinCable, String quantitySticker, String quantityCameraCP, String valueSdCard, String quantityCameraSVA, String typeOfPaymentMethodCamera, String neededStatePickUpFromOffice, String neededStateOvernightDelivery, String currentDue) throws IOException {
        super(quantityOfDevices, typeOfPaymentMethod, quantityPinCable, quantityOBDPinCable, quantitySticker, quantityCameraCP, valueSdCard, quantityCameraSVA, typeOfPaymentMethodCamera, neededStatePickUpFromOffice, neededStateOvernightDelivery, currentDue);
    }

    @Test
    public void managerDoOrderFleetAgree() throws IOException, InterruptedException, SQLException, ClassNotFoundException {
        userEldPage.checkAndCancelNewOrderBeforeTestFleet(dataFleetId.get("fleetId").toString());
        String idLastOrderBeforeTest = utilsForDB.getLastOrderIdForFleet(dataFleetId.get("fleetId").toString());
        utilsForDB.setCurrentDueForFleet(currentDue, dataFleetId.get("fleetId").toString());

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
        waitABit(3);
        String idLastOrderAfterTest = utilsForDB.getLastOrderIdForFleet(dataFleetId.get("fleetId").toString());
        checkAC("New order was not created", idLastOrderBeforeTest.equals(idLastOrderAfterTest) , false);

        String orderStatus = utilsForDB.getOrderStatus(idLastOrderAfterTest);
        checkAC("Order is not New status", financesPage.compareNewOrderStatus(orderStatus, currentDue, quantityOfDevices, quantityCameraCP) , true);
        checkAC("Eld status in New order is not correct", userEldPage.compareEldStatusInNewOrder(idLastOrderAfterTest, quantityOfDevices), true);

        tearDown();
        setUp();
// user agree order
        loginPage.userValidLogIn(dataForFleetValidLogIn.get("login").toString(),dataForFleetValidLogIn.get("pass").toString());
        modalEldPage.doAgreeAgreementForManagerOrder(quantityOfDevices, quantityCameraCP);
        waitABit(10);

        dashboardPage.openMenuDash();
        dashboardPage.goToFinancesPage();
        financesPage.payCurrentInvoiceForOrderByManager(currentDue, quantityOfDevices, quantityCameraCP);
        String dueForLastOrder = utilsForDB.getLastDueForFleet(dataFleetId.get("fleetId").toString());
        checkAC("Balance is not correct", financesPage.compareBalance(currentDue, dueForLastOrder), true);

        String orderStatusPaid = utilsForDB.getOrderStatus(idLastOrderAfterTest);
        checkAC("Order is not Paid", financesPage.comparePaidOrderStatus(orderStatusPaid) , true);
        checkAC("Eld status in Paid order is not correct", userEldPage.compareEldStatusInPaidOrder(idLastOrderAfterTest, quantityOfDevices), true);

        tearDown();
        setUp();
// Manager completed order
        loginPage.userValidLogIn(dataForManagerValidLogIn.get("login").toString(),dataForManagerValidLogIn.get("pass").toString());
        dashboardPage.openMenuDash();
        dashboardPage.goToEldPage();

        managerEldPage.openOrderInfo(idLastOrderAfterTest);
        checkAC("Full Order Price is not correct", orderInfoPage.compareFullOrderPrice(dueForLastOrder), true);
        orderInfoPage.completedOrder();

        String orderStatusCompleted = utilsForDB.getOrderStatus(idLastOrderAfterTest);
        checkAC("Order is not completed", financesPage.compareCompletedOrderStatus(orderStatusCompleted), true);
        checkAC("Eld status in Completed order is not correct", userEldPage.compareEldStatusInCompletedOrder(idLastOrderAfterTest, quantityOfDevices), true);


    }
    @Test
    public void managerDoOrderFleetCanceled() throws IOException, InterruptedException, SQLException, ClassNotFoundException {
        userEldPage.checkAndCancelNewOrderBeforeTestFleet(dataFleetId.get("fleetId").toString());
        String idLastOrderBeforeTest = utilsForDB.getLastOrderIdForFleet(dataFleetId.get("fleetId").toString());
        utilsForDB.setCurrentDueForFleet(currentDue, dataFleetId.get("fleetId").toString());

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
        waitABit(3);
        String idLastOrderAfterTest = utilsForDB.getLastOrderIdForFleet(dataFleetId.get("fleetId").toString());
        checkAC("New order was not created", idLastOrderBeforeTest.equals(idLastOrderAfterTest) , false);

        String orderStatus = utilsForDB.getOrderStatus(idLastOrderAfterTest);
        checkAC("Order is not New status", financesPage.compareNewOrderStatus(orderStatus, currentDue, quantityOfDevices, quantityCameraCP) , true);
        checkAC("Eld status in New order is not correct", userEldPage.compareEldStatusInNewOrder(idLastOrderAfterTest, quantityOfDevices), true);

        tearDown();
        setUp();

// fleet canceled order
        String dueForLastOrder = utilsForDB.getLastDueForSolo(dataSoloId.get("soloId").toString());
        loginPage.userValidLogIn(dataForFleetValidLogIn.get("login").toString(),dataForFleetValidLogIn.get("pass").toString());

        waitABit(10);
        modalEldPage.doCancelAgreementForManagerOrder(quantityOfDevices, quantityCameraCP);
        waitABit(10);
        dashboardPage.openMenuDash();
        waitABit(10);

        String orderCancelStatus = utilsForDB.getOrderStatus(idLastOrderAfterTest);
        checkAC("Order with devices is not canceled", userEldPage.compareCancelStatusOrder(orderCancelStatus, quantityOfDevices), true);
        checkAC("ELD is present in canceled order", utilsForDB.isEldBlinded(idLastOrderAfterTest), false);
        dashboardPage.goToFinancesPage();

        checkAC("Balance is not correct", financesPage.compareBalanceIfCanceled(currentDue, dueForLastOrder, quantityOfDevices), true);

    }
}
