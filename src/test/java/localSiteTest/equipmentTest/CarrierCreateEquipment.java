package localSiteTest.equipmentTest;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import parentTest.ParentTest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static libs.Utils.*;

public class CarrierCreateEquipment extends ParentTest {
    Map dataForValidLogIn = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "validFleetLogin");
    Map dataForEquipmentType = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testSettings.xls", "equipmentType");
    Map dataForFuelType = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testSettings.xls", "fuelType");
    Map dataForStateValue = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testSettings.xls", "stateValue");

    String login = dataForValidLogIn.get("login").toString();
    String pass = dataForValidLogIn.get("pass").toString();
    String carrierId = dataForValidLogIn.get("fleetId").toString();


    public CarrierCreateEquipment() throws IOException {
    }

    @Test
    public void createTrack() throws SQLException, IOException, ClassNotFoundException {
        String unitName = "name";
        String type = genRandomEquipmentType();
        String owner = "owner";
        String year = Integer.toString(genRandomNumberBetweenTwoValues(1950, 2020));
        String vin = RandomStringUtils.randomAlphanumeric(17);
        String plate = RandomStringUtils.randomAlphanumeric(6);
        String state = genRandomStateName();
        String tireSize = RandomStringUtils.randomNumeric(6);
        String length = RandomStringUtils.randomNumeric(6);
        String fuel = genRandomFuelType();
        String axel = RandomStringUtils.randomAlphanumeric(6);
        String make = RandomStringUtils.randomAlphanumeric(6);
        String model = "model";
        String grossWeight = RandomStringUtils.randomNumeric(3);
        String unlandWeight = RandomStringUtils.randomNumeric(3);
        String color = "color";
        String nyCert = RandomStringUtils.randomAlphanumeric(6);
        String date = getDateAndTime("dd/MM/yyyy");



        loginLocalSitePage.logInWithOutOpenMenu(login, pass);
        dashboardLocalSitePage.goToSafetyPage();
        waitABit(2);
        equipmentLocalSitePage.clickOnAddButton();
        waitABit(2);
        equipmentLocalSitePage.clickOnAddTruckButton();
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
        equipmentLocalSitePage.enterInspectionDue(date);
        equipmentLocalSitePage.enterNinetyDayExp(date);
        equipmentLocalSitePage.enterProRate(date);
        equipmentLocalSitePage.enterExpDate(date);
//        equipmentLocalSitePage.clickOnActive();
        equipmentLocalSitePage.clickOnSubmit();
        waitABit(20);

        String equipId = utilsForDB.getEquipmentId("carrierId", carrierId, unitName);

        List<ArrayList> tempDataEquipmentList = utilsForDB.getDataEquipment(equipId);
        Map<String, Object> tempDataSettingsMap = listArrayToMap(tempDataEquipmentList);
        String typeTemp = dataForEquipmentType.get(type).toString();
        String fuelTemp = dataForFuelType.get(fuel).toString();
        String stateValue = dataForStateValue.get(state).toString();


        checkAC("TruckTrailer failed", tempDataSettingsMap.get("truckTrailer").equals("0"), true);
        checkAC("Name failed", tempDataSettingsMap.get("Name").equals(unitName), true);
        checkAC("Owner failed", tempDataSettingsMap.get("Owner").equals(owner), true);
        checkAC("Year failed", tempDataSettingsMap.get("Year").equals(year), true);
        checkAC("Type failed", tempDataSettingsMap.get("Type").equals(typeTemp), true);
        checkAC("VIN failed", tempDataSettingsMap.get("VIN").equals(vin), true);
        checkAC("Plate failed", tempDataSettingsMap.get("Plate").equals(plate), true);
        checkAC("State failed", tempDataSettingsMap.get("State").equals(stateValue), true);

        checkAC("TireSize failed", tempDataSettingsMap.get("TireSize").equals(tireSize), true);
        checkAC("Length failed", tempDataSettingsMap.get("Length").equals(length), true);
        checkAC("Fuel failed", tempDataSettingsMap.get("Fuel").equals(fuelTemp), true);
        checkAC("Axel failed", tempDataSettingsMap.get("Axel").equals(axel), true);
        checkAC("Make failed", tempDataSettingsMap.get("Make").equals(make), true);
        checkAC("Model failed", tempDataSettingsMap.get("Model").equals(model), true);
        checkAC("GrossWeight failed", tempDataSettingsMap.get("GrossWeight").equals(grossWeight), true);
        checkAC("UnlandWeight failed", tempDataSettingsMap.get("UnlandWeight").equals(unlandWeight), true);

        checkAC("Color failed", tempDataSettingsMap.get("Color").equals(color), true);
        checkAC("NYCert failed", tempDataSettingsMap.get("NYCert").equals(nyCert), true);
        checkAC("InspectionDue failed", tempDataSettingsMap.get("InspectionDue").equals(date), true);
        checkAC("90DayExp failed", tempDataSettingsMap.get("90DayExp").equals(date), true);
        checkAC("ProRateExp failed", tempDataSettingsMap.get("ProRateExp").equals(date), true);
        checkAC("ExpDate failed", tempDataSettingsMap.get("ExpDate").equals(date), true);
        checkAC("isActive failed", tempDataSettingsMap.get("isActive").equals("1"), true);


    }
}
