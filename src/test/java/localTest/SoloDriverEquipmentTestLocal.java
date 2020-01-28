package localTest;

import libs.UtilsForDB;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import parentTest.Parent3Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static libs.Utils.*;

public class SoloDriverEquipmentTestLocal extends Parent3Test {
    UtilsForDB utilsForDB = new UtilsForDB();
    String login = "den36@gmail.com";
    String pass = "testtest";
    String userId = "4401";



    @Test
    public void addTruck() throws SQLException, IOException, ClassNotFoundException {
        String unitName = RandomStringUtils.randomAlphanumeric(1, 10);
        String owner = RandomStringUtils.randomAlphabetic( 10);
        String year = RandomStringUtils.randomNumeric(4);
        String type = "0";
        String vin = RandomStringUtils.randomAlphanumeric(17);
        String plate = RandomStringUtils.randomAlphanumeric(6);
        String state = "AR";
        String tireSize = RandomStringUtils.randomNumeric(6);
        String length = RandomStringUtils.randomNumeric(6);
        String fuel = "Diesel";
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

        List<ArrayList> tempDataSettingsList = utilsForDB.getDataTruckEquipment(userId);
        Map<String, Object> tempDataSettingsMap = listArrayToMap(tempDataSettingsList);

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
    public void upDateTruck() throws SQLException, IOException, ClassNotFoundException {

        String unitName = RandomStringUtils.randomAlphanumeric(1, 10);
        String owner = RandomStringUtils.randomAlphabetic( 10);
        String year = RandomStringUtils.randomNumeric(4);
        String type = "0";
        String vin = RandomStringUtils.randomAlphanumeric(17);
        String plate = RandomStringUtils.randomAlphanumeric(6);
        String state = "AR";
        String tireSize = RandomStringUtils.randomNumeric(6);
        String length = RandomStringUtils.randomNumeric(6);
        String fuel = "Diesel";
        String axel = RandomStringUtils.randomAlphanumeric(6);
        String make = RandomStringUtils.randomAlphanumeric(6);
        String model = RandomStringUtils.randomAlphanumeric(6);
        String grossWeight = RandomStringUtils.randomNumeric(6);
        String unlandWeight = RandomStringUtils.randomNumeric(6);
        String color = RandomStringUtils.randomAlphabetic(6);
        String nyCert = RandomStringUtils.randomAlphanumeric(6);
        String inspectionDueDate = getDateRandom();
        String ninetyDayExpDate = getDateRandom();
        String proRateDueDate = getDateRandom();
        String expDateDate = getDateRandom();
        String nameTruck = utilsForDB.getRandomEquipmentName(userId, "1");

        loginLocalSitePage.userValidLogIn(login, pass);
        dashboardLocalSitePage.goToEquipmentPage();
        equipmentLocalSitePage.enterOnEquipmentPlaceHolder(nameTruck);
        waitABit(3);
        equipmentLocalSitePage.clickOnEquipmentOnRow();
        waitABit(2);
        equipmentLocalSitePage.clickOnEditButton();
        waitABit(2);
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
        equipmentLocalSitePage.clickOnActive();


        waitABit(5);
        equipmentLocalSitePage.clickOnSave();
        waitABit(5);

        List<ArrayList> tempDataListUpdatedTruck = utilsForDB.getDataTruckEquipmentByName(unitName);
        Map<String, Object> tempDataSettingsMap = listArrayToMap(tempDataListUpdatedTruck);

        checkACWithLogger("Name failed", tempDataSettingsMap.get("Name").equals(unitName), true, tempDataSettingsMap.get("Name").toString(), unitName);
        checkACWithLogger("Owner failed", tempDataSettingsMap.get("Owner").equals(owner), true, tempDataSettingsMap.get("Owner").toString(), owner);
        checkACWithLogger("Year failed", tempDataSettingsMap.get("Year").equals(year), true, tempDataSettingsMap.get("Year").toString(), year);
        checkACWithLogger("Type failed", tempDataSettingsMap.get("Type").equals(type), true, tempDataSettingsMap.get("Type").toString(), type);
        checkACWithLogger("VIN failed", tempDataSettingsMap.get("VIN").equals(vin), true, tempDataSettingsMap.get("VIN").toString(), vin);
        checkACWithLogger("Plate failed", tempDataSettingsMap.get("Plate").equals(plate), true, tempDataSettingsMap.get("Plate").toString(), plate);
        checkACWithLogger("State failed", tempDataSettingsMap.get("State").equals(state), true, tempDataSettingsMap.get("State").toString(), state);

        checkACWithLogger("TireSize failed", tempDataSettingsMap.get("TireSize").equals(tireSize), true, tempDataSettingsMap.get("TireSize").toString(), tireSize);
        checkACWithLogger("Length failed", tempDataSettingsMap.get("Length").equals(length), true, tempDataSettingsMap.get("Length").toString(), length);
        checkACWithLogger("Fuel failed", tempDataSettingsMap.get("Fuel").equals(fuel), true, tempDataSettingsMap.get("Fuel").toString(), fuel);
        checkACWithLogger("Axel failed", tempDataSettingsMap.get("Axel").equals(axel), true, tempDataSettingsMap.get("Axel").toString(), axel);
        checkACWithLogger("Make failed", tempDataSettingsMap.get("Make").equals(make), true, tempDataSettingsMap.get("Make").toString(), make);
        checkACWithLogger("Model failed", tempDataSettingsMap.get("Model").equals(model), true, tempDataSettingsMap.get("Model").toString(), model);
        checkACWithLogger("GrossWeight failed", tempDataSettingsMap.get("GrossWeight").equals(grossWeight), true, tempDataSettingsMap.get("GrossWeight").toString(), grossWeight);
        checkACWithLogger("UnlandWeight failed", tempDataSettingsMap.get("UnlandWeight").equals(unlandWeight), true, tempDataSettingsMap.get("UnlandWeight").toString(), unlandWeight);

        checkACWithLogger("Color failed", tempDataSettingsMap.get("Color").equals(color), true, tempDataSettingsMap.get("Color").toString(), color);
        checkACWithLogger("NYCert failed", tempDataSettingsMap.get("NYCert").equals(nyCert), true, tempDataSettingsMap.get("NYCert").toString(), nyCert);
        checkACWithLogger("InspectionDue failed", tempDataSettingsMap.get("InspectionDue").equals(inspectionDueDate), true, tempDataSettingsMap.get("InspectionDue").toString(), inspectionDueDate);
        checkACWithLogger("90DayExp failed", tempDataSettingsMap.get("90DayExp").equals(ninetyDayExpDate), true, tempDataSettingsMap.get("90DayExp").toString(), ninetyDayExpDate);
        checkACWithLogger("ProRateExp failed", tempDataSettingsMap.get("ProRateExp").equals(proRateDueDate), true, tempDataSettingsMap.get("ProRateExp").toString(), proRateDueDate);
        checkACWithLogger("ExpDate failed", tempDataSettingsMap.get("ExpDate").equals(expDateDate), true, tempDataSettingsMap.get("ExpDate").toString(), expDateDate);
        checkACWithLogger("isActive failed", tempDataSettingsMap.get("isActive").equals("0"), true, tempDataSettingsMap.get("isActive").toString(), "0");


    }
}
