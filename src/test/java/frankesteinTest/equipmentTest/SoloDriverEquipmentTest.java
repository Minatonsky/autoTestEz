package frankesteinTest.equipmentTest;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import parentTest.ParentTest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import static libs.Utils.*;

public class SoloDriverEquipmentTest extends ParentTest {

    Map dataForValidLogIn = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "driverLogin");

    String login = dataForValidLogIn.get("login").toString();
    String pass = dataForValidLogIn.get("pass").toString();

    public SoloDriverEquipmentTest() throws IOException {
    }

    @Test
    public void addTruck() throws SQLException, IOException, ClassNotFoundException {

        // equipment data
        String unitName = RandomStringUtils.randomAlphanumeric(1, 10);
        String owner = RandomStringUtils.randomAlphanumeric( 10);
        String year = RandomStringUtils.randomNumeric(4);
        String type = Integer.toString(genRandomNumberBetweenTwoValues(0, 4));
        String vin = "1GNEK12ZX2R298984";
        String plate = RandomStringUtils.randomAlphanumeric(6);
        String state = Integer.toString(genRandomNumberBetweenTwoValues(1, 63));
        String tireSize = RandomStringUtils.randomNumeric(3);
        String length = RandomStringUtils.randomNumeric(3);
        String fuel = genRandomFuelType();
        String axel = RandomStringUtils.randomAlphanumeric(6);
        String make = RandomStringUtils.randomAlphanumeric(6);
        String model = RandomStringUtils.randomAlphanumeric(6);
        String grossWeight = RandomStringUtils.randomNumeric(2);
        String unlandWeight = RandomStringUtils.randomNumeric(2);
        String color = RandomStringUtils.randomAlphanumeric(6);
        String nyCert = RandomStringUtils.randomAlphanumeric(6);
        String date = getDateAndTime("dd-MM-yyyy");

        loginPage.userValidLogIn(login, pass);
        checkAC("User wasn`t logined", dashboardPage.isDashboardPresent(), true);
        dashboardPage.goToReportsPage();
        dashboardPage.goToLogsPage();
        dashboardPage.goToDVIRPage();
        dashboardPage.goToDocumentsPage();

//        ADD TRUCK
        dashboardPage.goToEquipmentPage();
        equipmentPage.clickOnAddTruckButton();
        waitABit(3);
        equipmentPage.enterUnitName(unitName);
        equipmentPage.enterOwner(owner);
        equipmentPage.enterYear(year);
        equipmentPage.selectType(type);
        equipmentPage.enterVin(vin);
        equipmentPage.enterPlate(plate);
        equipmentPage.selectState(state);
        equipmentPage.enterColor(color);
        equipmentPage.enterNYCert(nyCert);
        equipmentPage.enterInspectionDue(date);
        equipmentPage.enterNinetyDayExp(date);
        equipmentPage.enterProRate(date);
        equipmentPage.enterExpDate(date);
        equipmentPage.enterTireSize(tireSize);
        equipmentPage.enterLength(length);
        equipmentPage.selectFuel(fuel);
        equipmentPage.enterAxel(axel);
        equipmentPage.enterMake(make);
        equipmentPage.enterModel(model);
        equipmentPage.enterGrossWeight(grossWeight);
        equipmentPage.enterUnlandWeight(unlandWeight);
        equipmentPage.clickOnBlankArea();
        equipmentPage.clickOnSubmit();

// ADD TRAILER
        equipmentPage.clickOnAddTrailerButton();
        waitABit(3);
        equipmentPage.enterUnitName(unitName);
        equipmentPage.enterOwner(owner);
        equipmentPage.enterYear(year);
        equipmentPage.selectType(type);
        equipmentPage.enterVin(vin);
        equipmentPage.enterPlate(plate);
        equipmentPage.selectState(state);
        equipmentPage.enterTireSize(tireSize);
        equipmentPage.enterLength(length);
        equipmentPage.selectFuel(fuel);
        equipmentPage.enterAxel(axel);
        equipmentPage.enterMake(make);
        equipmentPage.enterModel(model);
        equipmentPage.enterGrossWeight(grossWeight);
        equipmentPage.enterUnlandWeight(unlandWeight);
        equipmentPage.enterColor(color);
        equipmentPage.enterNYCert(nyCert);
        equipmentPage.enterInspectionDue(date);
        equipmentPage.enterNinetyDayExp(date);
        equipmentPage.enterProRate(date);
        equipmentPage.enterExpDate(date);
        equipmentPage.clickOnBlankArea();
        equipmentPage.clickOnSubmit();

    }
}
