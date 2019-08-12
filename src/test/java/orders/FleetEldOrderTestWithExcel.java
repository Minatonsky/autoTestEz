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

        modalEldPage.enterPersonalData(personalDataForEldOrder.get("deliveryState").toString(), personalDataForEldOrder.get("firstName").toString(), personalDataForEldOrder.get("lastName").toString(), personalDataForEldOrder.get("phone").toString(), personalDataForEldOrder.get("addressLine").toString(), personalDataForEldOrder.get("aptNumber").toString(), personalDataForEldOrder.get("deliveryCity").toString(), personalDataForEldOrder.get("zipCode").toString());
        modalEldPage.setPickUpFromOffice(dataForEldOrder.get("neededStatePickUpFromOffice").toString());
        modalEldPage.setOvernightDelivery(dataForEldOrder.get("neededStateOvernightDelivery").toString());
        modalEldPage.enterQuantityDevices(dataForEldOrder.get("quantityOfDevices").toString());
        modalEldPage.enterQuantityPinCable(dataForEldOrder.get("quantityPinCable").toString());
        modalEldPage.enterQuantityOBDPinCable(dataForEldOrder.get("quantityOBDPinCable").toString());
        modalEldPage.enterQuantitySticker(dataForEldOrder.get("quantitySticker").toString());
        modalEldPage.enterQuantityCameraCP(dataForEldOrder.get("quantityCameraCP").toString());
        modalEldPage.selectSdCard(dataForEldOrder.get("valueSdCard").toString());
        modalEldPage.enterQuantityCameraSVA(dataForEldOrder.get("quantityCameraSVA").toString());
        modalEldPage.clickPaymentMethods(dataForEldOrder.get("typeOfPaymentMethod").toString(), dataForEldOrder.get("quantityOfDevices").toString());
        modalEldPage.clickPaymentMethodsCamera(dataForEldOrder.get("typeOfPaymentMethodCamera").toString(), dataForEldOrder.get("quantityCameraCP").toString());

        waitABit(3);
        checkAC("Eld prices is not correct", modalEldPage.compareEldPrice(dataForEldOrder.get("quantityOfDevices").toString(), dataForEldOrder.get("typeOfPaymentMethod").toString(), dataForEldOrder.get("eldFirstMonthFee").toString(), dataForEldOrder.get("eldLastMonthFee").toString(), dataForEldOrder.get("eldOneYearPrice").toString(), dataForEldOrder.get("eldTwoYearPrice").toString()), true);
        checkAC("DepositFee is not correct", modalEldPage.compareDepositFee(dataForEldOrder.get("quantityOfDevices").toString(), dataForEldOrder.get("eldDepositFee").toString()), true);
        checkAC("DeliveryPrice is not correct", modalEldPage.compareDeliveryPrice(dataForEldOrder.get("neededStatePickUpFromOffice").toString(), dataForEldOrder.get("eldDeliveryPrice").toString()), true);
        checkAC("EldPinCable prices is not correct", modalEldPage.compareEldPinCable(dataForEldOrder.get("quantityPinCable").toString(), dataForEldOrder.get("eldPinCablePrice").toString()), true);
        checkAC("EldOBDPinCable prices is not correct", modalEldPage.compareEldOBDPinCable(dataForEldOrder.get("quantityOBDPinCable").toString(), dataForEldOrder.get("eldEldOBDPinCablePrice").toString()), true);
        checkAC("EldStickerLabel prices is not correct", modalEldPage.compareEldStickerLabel(dataForEldOrder.get("quantitySticker").toString(), dataForEldOrder.get("eldEldStickerLabelPrice").toString()), true);


        checkAC("CP2MonthFee is not correct", modalEldPage.compareCP2MonthFee(dataForEldOrder.get("quantityCameraCP").toString(), dataForEldOrder.get("cP2MonthFee").toString()), true);
        checkAC("CameraSetupFee is not correct", modalEldPage.compareCameraSetupFee(dataForEldOrder.get("quantityCameraCP").toString(), dataForEldOrder.get("cameraSetupFee").toString()), true);
        checkAC("CameraInstallationFee is not correct", modalEldPage.compareCameraInstallationFee(dataForEldOrder.get("quantityCameraCP").toString(), dataForEldOrder.get("cameraInstallationFee").toString()), true);
        checkAC("EzSmartCamCP2 prices is not correct", modalEldPage.compareEzSmartCamCP2(dataForEldOrder.get("quantityCameraCP").toString(), dataForEldOrder.get("ezSmartCamCP2").toString()), true);
        checkAC("EzSmartCamSVA prices is not correct", modalEldPage.compareEzSmartCamSVA(dataForEldOrder.get("quantityCameraCP").toString(), dataForEldOrder.get("ezSmartCamSVA").toString()), true);
        checkAC("SdCard prices is not correct", modalEldPage.compareSdCard(dataForEldOrder.get("quantityCameraCP").toString(), dataForEldOrder.get("valueSdCard").toString(), dataForEldOrder.get("sD32Gb").toString(), dataForEldOrder.get("sD64Gb").toString(), dataForEldOrder.get("sD128Gb").toString()), true);
        System.out.println(modalEldPage.getTotalOrder());
        checkAC("Total Order is not correct", modalEldPage.compareTotalOrder(dataForEldOrder.get("defaultTotalOrder").toString()), true);
        modalEldPage.doAgreeAgreement(dataForEldOrder.get("quantityOfDevices").toString());
        modalEldPage.doAgreementCamera(dataForEldOrder.get("quantityCameraCP").toString());
        modalEldPage.clickButtonOrder();

        String idLastOrderAfterTest = utilsForDB.getLastOrderIdForFleet(dataFleetId.get("fleetId").toString());
        waitABit(3);
        checkAC("New order wasn`t created", idLastOrderBeforeTest.equals(idLastOrderAfterTest) , false);

        dashboardPage.goToFinancesPage();
        waitABit(3);

        checkAC("Balance is not correct", financesPage.compareBalance(dataForEldOrder.get("defaultBalance").toString()), true);
    }
}
