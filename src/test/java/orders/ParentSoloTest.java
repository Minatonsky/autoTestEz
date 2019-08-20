package orders;

import libs.ExcelDriver;
import libs.UtilsForDB;
import org.junit.Before;
import org.junit.Test;
import parentTest.ParentTest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import static libs.Utils.waitABit;

public class ParentSoloTest extends ParentTest {
    ExcelDriver excelDriver = new ExcelDriver();
    UtilsForDB utilsForDB = new UtilsForDB();
    int columnNumber = 1;

    Map dataForEldOrder = excelDriver.getMultipleData(configProperties.DATA_FILE_PATH() + "testEldOrder.xls", "orderListData", columnNumber);
    Map personalDataForEldOrder = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testEldOrder.xls", "personalData");
    Map dataForSoloValidLogIn = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "validSoloLogin");
    Map dataSoloId = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "validSoloLogin");
    Map dataForManagerValidLogIn = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "managerLogin");


    public ParentSoloTest() throws IOException {
    }

    @Before

    @Test
    public void addNewOrder() throws SQLException, IOException, ClassNotFoundException {
        String idLastOrderBeforeTest = utilsForDB.getLastOrderIdForFleet(dataSoloId.get("soloId").toString());
        utilsForDB.getSetCurrentDueForSolo(dataForEldOrder.get("currentDue").toString(), dataSoloId.get("soloId").toString());

        loginPage.userValidLogIn(dataForSoloValidLogIn.get("login").toString(),dataForSoloValidLogIn.get("pass").toString());

        dashboardPage.openMenuDash();
        dashboardPage.goToEldPage();
        userEldPage.clickOnOrderELD();
        modalEldPage.enterPersonalData(personalDataForEldOrder.get("deliveryState").toString(), personalDataForEldOrder.get("firstName").toString(), personalDataForEldOrder.get("lastName").toString(), personalDataForEldOrder.get("phone").toString(), personalDataForEldOrder.get("addressLine").toString(), personalDataForEldOrder.get("aptNumber").toString(), personalDataForEldOrder.get("deliveryCity").toString(), personalDataForEldOrder.get("zipCode").toString());
        modalEldPage.enterOrderData(dataForEldOrder.get("quantityOfDevices").toString(), dataForEldOrder.get("quantityPinCable").toString(), dataForEldOrder.get("quantityOBDPinCable").toString(), dataForEldOrder.get("quantitySticker").toString(), dataForEldOrder.get("quantityCameraCP").toString(), dataForEldOrder.get("valueSdCard").toString(), dataForEldOrder.get("quantityCameraSVA").toString(), dataForEldOrder.get("neededStatePickUpFromOffice").toString(), dataForEldOrder.get("neededStateOvernightDelivery").toString());
        modalEldPage.clickPaymentMethods(dataForEldOrder.get("typeOfPaymentMethod").toString(), dataForEldOrder.get("quantityOfDevices").toString());
        modalEldPage.clickPaymentMethodsCamera(dataForEldOrder.get("typeOfPaymentMethodCamera").toString(), dataForEldOrder.get("quantityCameraCP").toString());
        waitABit(1);
        checkAC("Eld prices is not correct", modalEldPage.compareEldPrice(dataForEldOrder.get("quantityOfDevices").toString(), dataForEldOrder.get("typeOfPaymentMethod").toString(), dataForEldOrder.get("quantityCameraCP").toString()), true);
        checkAC("DepositFee is not correct", modalEldPage.compareDepositFee(dataForEldOrder.get("quantityOfDevices").toString()), true);
        checkAC("DeliveryPrice is not correct", modalEldPage.compareDeliveryPrice(dataForEldOrder.get("neededStatePickUpFromOffice").toString()), true);
        checkAC("EldPinCable prices is not correct", modalEldPage.compareEldPinCable(dataForEldOrder.get("quantityPinCable").toString()), true);
        checkAC("EldOBDPinCable prices is not correct", modalEldPage.compareEldOBDPinCable(dataForEldOrder.get("quantityOBDPinCable").toString()), true);
        checkAC("EldStickerLabel prices is not correct", modalEldPage.compareEldStickerLabel(dataForEldOrder.get("quantitySticker").toString()), true);
        checkAC("CP2MonthFee is not correct", modalEldPage.compareCP2MonthFee(dataForEldOrder.get("quantityCameraCP").toString()), true);
        checkAC("CameraSetupFee is not correct", modalEldPage.compareCameraSetupFee(dataForEldOrder.get("quantityCameraCP").toString()), true);
        checkAC("CameraInstallationFee is not correct", modalEldPage.compareCameraInstallationFee(dataForEldOrder.get("quantityCameraCP").toString()), true);
        checkAC("EzSmartCamCP2 prices is not correct", modalEldPage.compareEzSmartCamCP2(dataForEldOrder.get("quantityCameraCP").toString()), true);
        checkAC("EzSmartCamSVA prices is not correct", modalEldPage.compareEzSmartCamSVA(dataForEldOrder.get("quantityCameraSVA").toString()), true);
        checkAC("SdCard prices is not correct", modalEldPage.compareSdCard(dataForEldOrder.get("quantityCameraCP").toString(), dataForEldOrder.get("valueSdCard").toString()), true);
//        System.out.println(modalEldPage.totalOrderPrice(dataForEldOrder.get("quantityOfDevices").toString(), dataForEldOrder.get("typeOfPaymentMethod").toString(), dataForEldOrder.get("quantityPinCable").toString(), dataForEldOrder.get("quantityOBDPinCable").toString(), dataForEldOrder.get("quantitySticker").toString(), dataForEldOrder.get("quantityCameraCP").toString(), dataForEldOrder.get("quantityCameraSVA").toString(), dataForEldOrder.get("valueSdCard").toString()));
        checkAC("Total Order is not correct", modalEldPage.compareTotalOrder(dataForEldOrder.get("quantityOfDevices").toString(), dataForEldOrder.get("typeOfPaymentMethod").toString(), dataForEldOrder.get("quantityPinCable").toString(), dataForEldOrder.get("quantityOBDPinCable").toString(), dataForEldOrder.get("quantitySticker").toString(), dataForEldOrder.get("quantityCameraCP").toString(), dataForEldOrder.get("quantityCameraSVA").toString(), dataForEldOrder.get("valueSdCard").toString()), true);


        modalEldPage.doAgreeAgreement(dataForEldOrder.get("quantityOfDevices").toString());
        modalEldPage.doAgreementCamera(dataForEldOrder.get("quantityCameraCP").toString());
        modalEldPage.clickButtonOrder();

        String idLastOrderAfterTest = utilsForDB.getLastOrderIdForSolo(dataSoloId.get("soloId").toString());
        checkAC("New order wasn`t created", idLastOrderBeforeTest.equals(idLastOrderAfterTest) , false);

        String orderStatus = utilsForDB.getOrderStatus(idLastOrderAfterTest);
        checkAC("Order is not completed", orderStatus.equals("3") , true);

        dashboardPage.goToFinancesPage();
        String dueForLastOrder = utilsForDB.getLastDueForSolo(dataSoloId.get("soloId").toString());
        checkAC("Balance is not correct", financesPage.compareBalance(dataForEldOrder.get("currentDue").toString(), dueForLastOrder), true);

    }

}
