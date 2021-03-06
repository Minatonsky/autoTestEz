package parameterizedTest;

import libs.SpreadsheetData;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import parentTest.ParentTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static libs.Utils.*;

@RunWith(Parameterized.class)
public class SafetyCreateEquipmentParamTest extends ParentTest {
    String login;

    public SafetyCreateEquipmentParamTest(String login) {
        this.login = login;
    }
    @Parameterized.Parameters(name = "Parameters are: {0}")
    public static Collection testData() throws IOException {
        InputStream spreadsheet = new FileInputStream(configProperties.DATA_FILE_PATH() + "testLogin.xls");
        return new SpreadsheetData(spreadsheet, "loginSafety").getData();
    }

    String pass = "testtest";

    @Test
    public void addTruck() throws SQLException, IOException, ClassNotFoundException {
        String fleetId = utilsForDB.getFleetIdByEmail(login);
        // equipment data
        String unitName = RandomStringUtils.randomAlphanumeric(1, 10);
        String owner = RandomStringUtils.randomAlphanumeric( 10);
        String year = Integer.toString(genRandomNumberBetweenTwoValues(1999, 2020));
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
        String date = "11111111";
        String dateDb = "1111-11-11";

        loginPage.userValidLogIn(login, pass);

//        ADD TRUCK
        dashboardPage.goToFleetPage();
        equipmentPage.clickOnAddUnit();
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


        waitABit(5);
        String equipId = utilsForDB.getEquipmentId("carrierId", fleetId, unitName);

        List<ArrayList> tempDataSettingsList = utilsForDB.getDataEquipment(equipId);
        Map<String, Object> tempDataSettingsMap = listArrayToMap(tempDataSettingsList);

        checkAC("TruckTrailer failed", tempDataSettingsMap.get("truckTrailer").equals("0"), true);
        checkAC("Name failed", tempDataSettingsMap.get("Name").equals(unitName), true);
        checkAC("Owner failed", tempDataSettingsMap.get("Owner").equals(owner), true);
        checkAC("Year failed", tempDataSettingsMap.get("Year").equals(year), true);
        checkAC("Type failed", tempDataSettingsMap.get("Type").equals(type), true);
        checkAC("VIN failed", tempDataSettingsMap.get("VIN").equals(vin), true);
        checkAC("Plate failed", tempDataSettingsMap.get("Plate").equals(plate), true);
        checkAC("State failed", tempDataSettingsMap.get("State").equals(state), true);

        checkAC("TireSize failed", tempDataSettingsMap.get("TireSize").equals(tireSize), true);
        checkAC("Length failed", tempDataSettingsMap.get("Length").equals(length), true);
        checkAC("Fuel failed", tempDataSettingsMap.get("Fuel").equals(fuel), true);
        checkAC("Axel failed", tempDataSettingsMap.get("Axel").equals(axel), true);
        checkAC("Make failed", tempDataSettingsMap.get("Make").equals(make), true);
//        checkAC("Model failed", tempDataSettingsMap.get("Model").equals(model), true);
        checkAC("GrossWeight failed", tempDataSettingsMap.get("GrossWeight").equals(grossWeight), true);
        checkAC("UnlandWeight failed", tempDataSettingsMap.get("UnlandWeight").equals(unlandWeight), true);

        checkAC("Color failed", tempDataSettingsMap.get("Color").equals(color), true);
        checkAC("NYCert failed", tempDataSettingsMap.get("NYCert").equals(nyCert), true);
        checkAC("InspectionDue failed", tempDataSettingsMap.get("InspectionDue").equals(dateDb), true);
        checkAC("90DayExp failed", tempDataSettingsMap.get("90DayExp").equals(dateDb), true);
        checkAC("ProRateExp failed", tempDataSettingsMap.get("ProRateExp").equals(dateDb), true);
        checkAC("ExpDate failed", tempDataSettingsMap.get("ExpDate").equals(dateDb), true);
        checkAC("isActive failed", tempDataSettingsMap.get("isActive").equals("1"), true);


    }
    @Test
    public void addTrailer() throws SQLException, IOException, ClassNotFoundException {
        String fleetId = utilsForDB.getFleetIdByEmail(login);
        // equipment data
        String unitName = RandomStringUtils.randomAlphanumeric(1, 10);
        String owner = RandomStringUtils.randomAlphanumeric( 10);
        String year = Integer.toString(genRandomNumberBetweenTwoValues(184, 2020));
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
        String date = "11111111";
        String dateDb = "1111-11-11";


        loginPage.userValidLogIn(login, pass);
        dashboardPage.goToFleetPage();
        waitABit(3);
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

        waitABit(5);
        String equipId = utilsForDB.getEquipmentId("carrierId", fleetId, unitName);

        List<ArrayList> tempDataSettingsList = utilsForDB.getDataEquipment(equipId);
        Map<String, Object> tempDataSettingsMap = listArrayToMap(tempDataSettingsList);

        checkAC("TruckTrailer failed", tempDataSettingsMap.get("truckTrailer").equals("1"), true);
        checkAC("Name failed", tempDataSettingsMap.get("Name").equals(unitName), true);
        checkAC("Owner failed", tempDataSettingsMap.get("Owner").equals(owner), true);
        checkAC("Year failed", tempDataSettingsMap.get("Year").equals(year), true);
        checkAC("Type failed", tempDataSettingsMap.get("Type").equals(type), true);
        checkAC("VIN failed", tempDataSettingsMap.get("VIN").equals(vin), true);
        checkAC("Plate failed", tempDataSettingsMap.get("Plate").equals(plate), true);
        checkAC("State failed", tempDataSettingsMap.get("State").equals(state), true);

        checkAC("TireSize failed", tempDataSettingsMap.get("TireSize").equals(tireSize), true);
        checkAC("Length failed", tempDataSettingsMap.get("Length").equals(length), true);
        checkAC("Fuel failed", tempDataSettingsMap.get("Fuel").equals(fuel), true);
        checkAC("Axel failed", tempDataSettingsMap.get("Axel").equals(axel), true);
        checkAC("Make failed", tempDataSettingsMap.get("Make").equals(make), true);
//        checkAC("Model failed", tempDataSettingsMap.get("Model").equals(model), true);
        checkAC("GrossWeight failed", tempDataSettingsMap.get("GrossWeight").equals(grossWeight), true);
        checkAC("UnlandWeight failed", tempDataSettingsMap.get("UnlandWeight").equals(unlandWeight), true);

        checkAC("Color failed", tempDataSettingsMap.get("Color").equals(color), true);
        checkAC("NYCert failed", tempDataSettingsMap.get("NYCert").equals(nyCert), true);
        checkAC("InspectionDue failed", tempDataSettingsMap.get("InspectionDue").equals(dateDb), true);
        checkAC("90DayExp failed", tempDataSettingsMap.get("90DayExp").equals(dateDb), true);
        checkAC("ProRateExp failed", tempDataSettingsMap.get("ProRateExp").equals(dateDb), true);
        checkAC("ExpDate failed", tempDataSettingsMap.get("ExpDate").equals(dateDb), true);
        checkAC("isActive failed", tempDataSettingsMap.get("isActive").equals("1"), true);
    }
}
