package parameterizedOrders;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.sql.SQLException;

@RunWith(Parameterized.class)

public class ManagerDoOrderSoloCanceledParamsTest extends ParentManagerOrderParamsTest {
    public ManagerDoOrderSoloCanceledParamsTest(String quantityOfDevices, String typeOfPaymentMethod, String quantityPinCable, String quantityOBDPinCable, String quantitySticker, String quantityCameraCP, String valueSdCard, String quantityCameraSVA, String typeOfPaymentMethodCamera, String neededStatePickUpFromOffice, String neededStateOvernightDelivery, String currentDue) throws IOException {
        super(quantityOfDevices, typeOfPaymentMethod, quantityPinCable, quantityOBDPinCable, quantitySticker, quantityCameraCP, valueSdCard, quantityCameraSVA, typeOfPaymentMethodCamera, neededStatePickUpFromOffice, neededStateOvernightDelivery, currentDue);
    }

    @Test
    public void managerDoOrderSoloCanceled() throws IOException, SQLException, ClassNotFoundException {

        String idLastOrderBeforeTest = utilsForDB.getLastOrderIdForFleet(dataSoloId.get("soloId").toString());
        utilsForDB.setCurrentDueForSolo(currentDue, dataSoloId.get("soloId").toString());

        managerModalEldPage.selectSoloDriverInOrder(dataForSoloValidLogIn.get("login").toString());
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
        String idLastOrderAfterTest = utilsForDB.getLastOrderIdForSolo(dataSoloId.get("soloId").toString());
        checkAC("New order was not created", idLastOrderBeforeTest.equals(idLastOrderAfterTest) , false);

        String orderStatus = utilsForDB.getOrderStatus(idLastOrderAfterTest);
        checkAC("Order is not New status", financesPage.compareNewOrderStatus(orderStatus) , true);

        tearDown();
        setUp();

 // user canceled order

        loginPage.userValidLogIn(dataForSoloValidLogIn.get("login").toString(),dataForSoloValidLogIn.get("pass").toString());
        modalEldPage.doCancelAgreementForManagerOrder(quantityOfDevices, quantityCameraCP);
        dashboardPage.openMenuDash();
        String orderCancelStatus = utilsForDB.getOrderStatus(idLastOrderAfterTest);
        checkAC("Order with devices is not canceled", financesPage.compareCancelOrderStatus(orderCancelStatus), true);
        checkAC("ELD is present in canceled order", utilsForDB.isEldBlinded(idLastOrderAfterTest), false);
        dashboardPage.goToFinancesPage();

        checkAC("Balance is not correct", financesPage.compareBalanceIfCanceledNewOrder(currentDue), true);

    }
}
