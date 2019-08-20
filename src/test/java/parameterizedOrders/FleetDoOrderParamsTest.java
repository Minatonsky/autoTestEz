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

public class FleetDoOrderParamsTest extends ParentTest {String  quantityOfDevices, typeOfPaymentMethod, quantityPinCable, quantityOBDPinCable, quantitySticker, quantityCameraCP, valueSdCard, quantityCameraSVA, typeOfPaymentMethodCamera, neededStatePickUpFromOffice, neededStateOvernightDelivery, currentDue, eldDeliveryPrice, eldFirstMonthFee, eldLastMonthFee, eldOneYearPrice, eldTwoYearPrice, eldDepositFee, eldPinCablePrice, eldEldOBDPinCablePrice, eldEldStickerLabelPrice, cP2MonthFee, cameraSetupFee, cameraInstallationFee, ezSmartCamCP2, ezSmartCamSVA, sD32Gb, sD64Gb, sD128Gb, defaultTotalOrder, defaultBalance, balanceIfCanceled;

    public FleetDoOrderParamsTest(String quantityOfDevices, String typeOfPaymentMethod, String quantityPinCable, String quantityOBDPinCable, String quantitySticker, String quantityCameraCP, String valueSdCard, String quantityCameraSVA, String typeOfPaymentMethodCamera, String neededStatePickUpFromOffice, String neededStateOvernightDelivery, String currentDue, String eldDeliveryPrice, String eldFirstMonthFee, String eldLastMonthFee, String eldOneYearPrice, String eldTwoYearPrice, String eldDepositFee, String eldPinCablePrice, String eldEldOBDPinCablePrice, String eldEldStickerLabelPrice, String cP2MonthFee, String cameraSetupFee, String cameraInstallationFee, String ezSmartCamCP2, String ezSmartCamSVA, String sD32Gb, String sD64Gb, String sD128Gb, String defaultTotalOrder, String defaultBalance, String balanceIfCanceled) {

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
        this.eldDeliveryPrice = eldDeliveryPrice;
        this.eldFirstMonthFee = eldFirstMonthFee;
        this.eldLastMonthFee = eldLastMonthFee;
        this.eldOneYearPrice = eldOneYearPrice;
        this.eldTwoYearPrice = eldTwoYearPrice;
        this.eldDepositFee = eldDepositFee;
        this.eldPinCablePrice = eldPinCablePrice;
        this.eldEldOBDPinCablePrice = eldEldOBDPinCablePrice;
        this.eldEldStickerLabelPrice = eldEldStickerLabelPrice;
        this.cP2MonthFee = cP2MonthFee;
        this.cameraSetupFee = cameraSetupFee;
        this.cameraInstallationFee = cameraInstallationFee;
        this.ezSmartCamCP2 = ezSmartCamCP2;
        this.ezSmartCamSVA = ezSmartCamSVA;
        this.sD32Gb = sD32Gb;
        this.sD64Gb = sD64Gb;
        this.sD128Gb = sD128Gb;
        this.defaultTotalOrder = defaultTotalOrder;
        this.defaultBalance = defaultBalance;
        this.balanceIfCanceled = balanceIfCanceled;
    }

    @Parameterized.Parameters()
    public static Collection testData() throws IOException {
        InputStream spreadsheet = new FileInputStream(configProperties.DATA_FILE_PATH() + "testParameterizedOrder.xls");
        return new SpreadsheetData(spreadsheet,"suitOrderListData").getData();

    }

    @Test
    public void addNewOrder() throws  SQLException, IOException, ClassNotFoundException {
        ExcelDriver excelDriver = new ExcelDriver();

        Map personalDataForEldOrder = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testEldOrder.xls", "personalData");
        Map dataForFleetValidLogIn = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "validFleetLogin");
        Map dataFleetId = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "validFleetLogin");

        UtilsForDB utilsForDB = new UtilsForDB();
        String idLastOrderBeforeTest = utilsForDB.getLastOrderIdForFleet(dataFleetId.get("fleetId").toString());
        utilsForDB.getSetCurrentDueForFleet(currentDue, dataFleetId.get("fleetId").toString());

        loginPage.userValidLogIn(dataForFleetValidLogIn.get("login").toString(),dataForFleetValidLogIn.get("pass").toString());

        dashboardPage.openMenuDash();
        dashboardPage.goToEldPage();
        userEldPage.clickOnOrderELD();

        modalEldPage.enterPersonalData(personalDataForEldOrder.get("deliveryState").toString(), personalDataForEldOrder.get("firstName").toString(), personalDataForEldOrder.get("lastName").toString(), personalDataForEldOrder.get("phone").toString(), personalDataForEldOrder.get("addressLine").toString(), personalDataForEldOrder.get("aptNumber").toString(), personalDataForEldOrder.get("deliveryCity").toString(), personalDataForEldOrder.get("zipCode").toString());

        modalEldPage.enterOrderData(quantityOfDevices, quantityPinCable, quantityOBDPinCable, quantitySticker, quantityCameraCP, valueSdCard, quantityCameraSVA, neededStatePickUpFromOffice, neededStateOvernightDelivery);
        modalEldPage.clickPaymentMethods(typeOfPaymentMethod, quantityOfDevices);
        modalEldPage.clickPaymentMethodsCamera(typeOfPaymentMethodCamera, quantityCameraCP);

//        checkAC("Eld prices is not correct", modalEldPage.compareEldPrice(quantityOfDevices, typeOfPaymentMethod), true);
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
//        checkAC("Total Order is not correct", modalEldPage.compareTotalOrder(defaultTotalOrder), true);

        modalEldPage.doAgreeAgreement(quantityOfDevices);
        modalEldPage.doAgreementCamera(quantityCameraCP);
        modalEldPage.clickButtonOrder();

        String idLastOrderAfterTest = utilsForDB.getLastOrderIdForFleet(dataFleetId.get("fleetId").toString());
        checkAC("New order wasn`t created", idLastOrderBeforeTest.equals(idLastOrderAfterTest) , false);

        dashboardPage.goToFinancesPage();
//        financesPage.compareBalance(defaultBalance);
//        checkAC("Balance is not correct", financesPage.compareBalance(defaultBalance), true);

    }
}
