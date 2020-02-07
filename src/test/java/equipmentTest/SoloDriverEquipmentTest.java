package equipmentTest;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import parentTest.ParentTest;

import java.io.IOException;
import java.util.Map;

import static libs.Utils.getDateFormat;
import static libs.Utils.waitABit;

public class SoloDriverEquipmentTest extends ParentTest {

    Map dataForValidLogIn = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "driverLogin");

    String login = dataForValidLogIn.get("login").toString();
    String pass = dataForValidLogIn.get("pass").toString();

    public SoloDriverEquipmentTest() throws IOException {
    }

    @Test
    public void addTruck(){

        loginPage.userValidLogIn(login, pass);
        dashboardPage.openMenuDash();
        dashboardPage.goToEquipmentPage();

        String unitName = RandomStringUtils.randomAlphanumeric(1, 10);
        String owner = RandomStringUtils.randomAlphanumeric( 10);
        String year = RandomStringUtils.randomNumeric(4);
        String type = "0";
        String vin = RandomStringUtils.randomAlphanumeric(17);
        String plate = RandomStringUtils.randomAlphanumeric(6);
        String state = "AR";
        String tireSize = RandomStringUtils.randomAlphanumeric(6);
        String length = RandomStringUtils.randomAlphanumeric(6);
        String fuel = "Diesel";
        String axel = RandomStringUtils.randomAlphanumeric(6);
        String make = RandomStringUtils.randomAlphanumeric(6);
        String model = RandomStringUtils.randomAlphanumeric(6);
        String grossWeight = RandomStringUtils.randomAlphanumeric(6);
        String unlandWeight = RandomStringUtils.randomAlphanumeric(6);
        String color = RandomStringUtils.randomAlphanumeric(6);
        String nyCert = RandomStringUtils.randomAlphanumeric(6);
        String date = getDateFormat();


        equipmentPage.clickOnAddTruckButton();
        waitABit(5);
//    General
        equipmentPage.enterUnitName(unitName);
        equipmentPage.enterOwner(owner);
        equipmentPage.enterYear(year);
        equipmentPage.selectType(type);
        equipmentPage.enterVin(vin);
        equipmentPage.enterPlate(plate);
        equipmentPage.selectState(state);
//    Parameters
        equipmentPage.enterTireSize(tireSize);
        equipmentPage.enterLength(length);
        equipmentPage.selectFuel(fuel);
        equipmentPage.enterAxel(axel);
        equipmentPage.enterMake(make);
        equipmentPage.enterModel(model);
        equipmentPage.enterGrossWeight(grossWeight);
        equipmentPage.enterUnlandWeight(unlandWeight);
//    Others
        equipmentPage.enterColor(color);
        equipmentPage.enterNYCert(nyCert);
        equipmentPage.enterInspectionDue(date);
        equipmentPage.enterNinetyDayExp(date);
        equipmentPage.enterProRate(date);
        equipmentPage.enterExpDate(date);
        equipmentPage.clickOnActive();
        equipmentPage.clickOnSubmit();
    }
}
