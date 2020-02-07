package parameterizedUI;

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
public class SoloDriverCreateEquipmentParameterizedLocalSiteTest extends ParentTest {
    String login;

    public SoloDriverCreateEquipmentParameterizedLocalSiteTest(String login) {
        this.login = login;
    }
    @Parameterized.Parameters()
    public static Collection testData() throws IOException {
        InputStream spreadsheet = new FileInputStream(configProperties.DATA_FILE_PATH() + "testLogin.xls");
        return new SpreadsheetData(spreadsheet, "loginPassParamsTest").getData();
    }

    String pass = "testtest";

    @Test
    public void addTruck() throws SQLException, IOException, ClassNotFoundException {
        String userId = utilsForDB.getUserIdByEmail(login);
        String unitName = RandomStringUtils.randomAlphanumeric(5, 10);
        String type = Integer.toString(genRandomNumberBetweenTwoValues(0, 4));
        String owner = RandomStringUtils.randomAlphabetic( 10);
        String year = Integer.toString(genRandomNumberBetweenTwoValues(1950, 2020));
        String vin = RandomStringUtils.randomAlphanumeric(17);
        String plate = RandomStringUtils.randomAlphanumeric(6);
        String state = genRandomState();
        String tireSize = RandomStringUtils.randomNumeric(6);
        String length = RandomStringUtils.randomNumeric(6);
        String fuel = equipmentLocalSitePage.genRandomFuelType();
        String axel = RandomStringUtils.randomAlphanumeric(6);
        String make = RandomStringUtils.randomAlphanumeric(6);
        String model = RandomStringUtils.randomAlphanumeric(6);
        String grossWeight = RandomStringUtils.randomAlphanumeric(6);
        String unlandWeight = RandomStringUtils.randomAlphanumeric(6);
        String color = RandomStringUtils.randomAlphabetic(6);
        String nyCert = RandomStringUtils.randomAlphanumeric(6);
        String inspectionDueDate = getDateRandom();
        String ninetyDayExpDate = getDateRandom();
        String proRateDueDate = getDateRandom();
        String expDateDate = getDateRandom();

        loginLocalSitePage.userValidLogIn(login, pass);
        dashboardLocalSitePage.goToEquipmentPage();
        equipmentLocalSitePage.clickOnAddTruckButton();
        waitABit(5);
//    General
        equipmentLocalSitePage.enterUnitName(unitName);
        equipmentLocalSitePage.enterOwner(owner);
        equipmentLocalSitePage.enterYear(year);
        equipmentLocalSitePage.selectType(type);
        equipmentLocalSitePage.enterVin(vin);
        equipmentLocalSitePage.enterPlate(plate);
        equipmentLocalSitePage.selectState(state);
//    Parameters
        equipmentLocalSitePage.enterTireSize(tireSize);
        equipmentLocalSitePage.enterLength(length);
        equipmentLocalSitePage.selectFuel(fuel);
        equipmentLocalSitePage.enterAxel(axel);
        equipmentLocalSitePage.enterMake(make);
        equipmentLocalSitePage.enterModel(model);
        equipmentLocalSitePage.enterGrossWeight(grossWeight);
        equipmentLocalSitePage.enterUnlandWeight(unlandWeight);
//    Others
        equipmentLocalSitePage.enterColor(color);
        equipmentLocalSitePage.enterNYCert(nyCert);
        equipmentLocalSitePage.enterInspectionDue(inspectionDueDate);
        equipmentLocalSitePage.enterNinetyDayExp(ninetyDayExpDate);
        equipmentLocalSitePage.enterProRate(proRateDueDate);
        equipmentLocalSitePage.enterExpDate(expDateDate);
//        equipmentPageLocal.clickOnActive();
        equipmentLocalSitePage.clickOnSubmit();
        waitABit(5);
        String equipId = utilsForDB.getEquipmentId(userId, unitName);

        List<ArrayList> tempDataSettingsList = utilsForDB.getDataEquipment(userId, equipId);
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
        checkAC("Model failed", tempDataSettingsMap.get("Model").equals(model), true);
        checkAC("GrossWeight failed", tempDataSettingsMap.get("GrossWeight").equals(grossWeight), true);
        checkAC("UnlandWeight failed", tempDataSettingsMap.get("UnlandWeight").equals(unlandWeight), true);

        checkAC("Color failed", tempDataSettingsMap.get("Color").equals(color), true);
        checkAC("NYCert failed", tempDataSettingsMap.get("NYCert").equals(nyCert), true);
        checkAC("InspectionDue failed", tempDataSettingsMap.get("InspectionDue").equals(inspectionDueDate), true);
        checkAC("90DayExp failed", tempDataSettingsMap.get("90DayExp").equals(ninetyDayExpDate), true);
        checkAC("ProRateExp failed", tempDataSettingsMap.get("ProRateExp").equals(proRateDueDate), true);
        checkAC("ExpDate failed", tempDataSettingsMap.get("ExpDate").equals(expDateDate), true);
        checkAC("isActive failed", tempDataSettingsMap.get("isActive").equals("1"), true);


    }
    @Test
    public void addTrailer() throws SQLException, IOException, ClassNotFoundException {
        String userId = utilsForDB.getUserIdByEmail(login);
        String unitName = RandomStringUtils.randomAlphanumeric(1, 10);
        String type = Integer.toString(genRandomNumberBetweenTwoValues(0, 4));
        String owner = RandomStringUtils.randomAlphabetic( 10);
        String year = Integer.toString(genRandomNumberBetweenTwoValues(1950, 2020));
        String vin = RandomStringUtils.randomAlphanumeric(17);
        String plate = RandomStringUtils.randomAlphanumeric(6);
        String state = genRandomState();
        String tireSize = RandomStringUtils.randomNumeric(6);
        String length = RandomStringUtils.randomNumeric(6);
        String fuel = equipmentLocalSitePage.genRandomFuelType();
        String axel = RandomStringUtils.randomAlphanumeric(6);
        String make = RandomStringUtils.randomAlphanumeric(6);
        String model = RandomStringUtils.randomAlphanumeric(6);
        String grossWeight = RandomStringUtils.randomAlphanumeric(6);
        String unlandWeight = RandomStringUtils.randomAlphanumeric(6);
        String color = RandomStringUtils.randomAlphabetic(6);
        String nyCert = RandomStringUtils.randomAlphanumeric(6);
        String inspectionDueDate = getDateRandom();
        String ninetyDayExpDate = getDateRandom();
        String proRateDueDate = getDateRandom();
        String expDateDate = getDateRandom();

        loginLocalSitePage.userValidLogIn(login, pass);
        dashboardLocalSitePage.goToEquipmentPage();
        equipmentLocalSitePage.clickOnAddTrailerButton();
        waitABit(5);
//    General
        equipmentLocalSitePage.enterUnitName(unitName);
        equipmentLocalSitePage.enterOwner(owner);
        equipmentLocalSitePage.enterYear(year);
        equipmentLocalSitePage.selectType(type);
        equipmentLocalSitePage.enterVin(vin);
        equipmentLocalSitePage.enterPlate(plate);
        equipmentLocalSitePage.selectState(state);
//    Parameters
        equipmentLocalSitePage.enterTireSize(tireSize);
        equipmentLocalSitePage.enterLength(length);
        equipmentLocalSitePage.selectFuel(fuel);
        equipmentLocalSitePage.enterAxel(axel);
        equipmentLocalSitePage.enterMake(make);
        equipmentLocalSitePage.enterModel(model);
        equipmentLocalSitePage.enterGrossWeight(grossWeight);
        equipmentLocalSitePage.enterUnlandWeight(unlandWeight);
//    Others
        equipmentLocalSitePage.enterColor(color);
        equipmentLocalSitePage.enterNYCert(nyCert);
        equipmentLocalSitePage.enterInspectionDue(inspectionDueDate);
        equipmentLocalSitePage.enterNinetyDayExp(ninetyDayExpDate);
        equipmentLocalSitePage.enterProRate(proRateDueDate);
        equipmentLocalSitePage.enterExpDate(expDateDate);
//        equipmentPageLocal.clickOnActive();
        equipmentLocalSitePage.clickOnSubmit();
        waitABit(5);
        String equipId = utilsForDB.getEquipmentId(userId, unitName);

        List<ArrayList> tempDataSettingsList = utilsForDB.getDataEquipment(userId, equipId);
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
        checkAC("Model failed", tempDataSettingsMap.get("Model").equals(model), true);
        checkAC("GrossWeight failed", tempDataSettingsMap.get("GrossWeight").equals(grossWeight), true);
        checkAC("UnlandWeight failed", tempDataSettingsMap.get("UnlandWeight").equals(unlandWeight), true);

        checkAC("Color failed", tempDataSettingsMap.get("Color").equals(color), true);
        checkAC("NYCert failed", tempDataSettingsMap.get("NYCert").equals(nyCert), true);
        checkAC("InspectionDue failed", tempDataSettingsMap.get("InspectionDue").equals(inspectionDueDate), true);
        checkAC("90DayExp failed", tempDataSettingsMap.get("90DayExp").equals(ninetyDayExpDate), true);
        checkAC("ProRateExp failed", tempDataSettingsMap.get("ProRateExp").equals(proRateDueDate), true);
        checkAC("ExpDate failed", tempDataSettingsMap.get("ExpDate").equals(expDateDate), true);
        checkAC("isActive failed", tempDataSettingsMap.get("isActive").equals("1"), true);
    }
}
