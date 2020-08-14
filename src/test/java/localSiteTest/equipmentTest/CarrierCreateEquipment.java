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
    Map dataForStateValue = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testSettings.xls", "stateValue");

    String login = dataForValidLogIn.get("login").toString();
    String pass = dataForValidLogIn.get("pass").toString();
    String carrierId = dataForValidLogIn.get("fleetId").toString();
    String path = "C:\\workspace\\testdevEzlogz\\src\\main\\java\\data\\testPdfFile.pdf";


    public CarrierCreateEquipment() throws IOException {
    }

    @Test
    public void createTrack() throws SQLException, IOException, ClassNotFoundException {
        String unitName = RandomStringUtils.randomAlphanumeric(5, 10);
        String type = genRandomEquipmentType();
        String owner = faker.twinPeaks().character();
        String year = Integer.toString(genRandomNumberBetweenTwoValues(1950, 2020));
        String vin = RandomStringUtils.randomAlphanumeric(17);
        String plate = RandomStringUtils.randomAlphanumeric(6);
        String state = genRandomStateName();
        String tireSize = Long.toString(faker.number().randomNumber());
        String length = Long.toString(faker.number().randomNumber());
        String fuel = genRandomFuelType();
        String axel = RandomStringUtils.randomAlphanumeric(6);
        String make = RandomStringUtils.randomAlphanumeric(6);
        String model = RandomStringUtils.randomAlphanumeric(3, 10);
        String grossWeight = Long.toString(faker.number().randomNumber());
        String unlandWeight = Long.toString(faker.number().randomNumber());
        String color = faker.color().name();
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
        checkAC("Fuel failed", tempDataSettingsMap.get("Fuel").equals(fuel), true);
        checkAC("Axel failed", tempDataSettingsMap.get("Axel").equals(axel), true);
        checkAC("Make failed", tempDataSettingsMap.get("Make").equals(make), true);
        checkAC("Model failed", tempDataSettingsMap.get("Model").equals(model), true);
        checkAC("GrossWeight failed", tempDataSettingsMap.get("GrossWeight").equals(grossWeight), true);
        checkAC("UnlandWeight failed", tempDataSettingsMap.get("UnlandWeight").equals(unlandWeight), true);

        checkAC("Color failed", tempDataSettingsMap.get("Color").equals(color), true);
        checkAC("NYCert failed", tempDataSettingsMap.get("NYCert").equals(nyCert), true);
        checkAC("InspectionDue failed", tempDataSettingsMap.get("InspectionDue").equals(getStringDateTimeUTC("yyyy-MM-dd")), true);
        checkAC("90DayExp failed", tempDataSettingsMap.get("90DayExp").equals(getStringDateTimeUTC("yyyy-MM-dd")), true);
        checkAC("ProRateExp failed", tempDataSettingsMap.get("ProRateExp").equals(getStringDateTimeUTC("yyyy-MM-dd")), true);
        checkAC("ExpDate failed", tempDataSettingsMap.get("ExpDate").equals(getStringDateTimeUTC("yyyy-MM-dd")), true);
        checkAC("isActive failed", tempDataSettingsMap.get("isActive").equals("1"), true);


    }
    @Test
    public void createTrailer() throws SQLException, IOException, ClassNotFoundException {
        String unitName = RandomStringUtils.randomAlphanumeric(5, 10);
        String type = genRandomEquipmentType();
        String owner = faker.twinPeaks().character();
        String year = Integer.toString(genRandomNumberBetweenTwoValues(1950, 2020));
        String vin = RandomStringUtils.randomAlphanumeric(17);
        String plate = RandomStringUtils.randomAlphanumeric(6);
        String state = genRandomStateName();
        String tireSize = Long.toString(faker.number().randomNumber());
        String length = Long.toString(faker.number().randomNumber());
        String fuel = genRandomFuelType();
        String axel = RandomStringUtils.randomAlphanumeric(6);
        String make = RandomStringUtils.randomAlphanumeric(6);
        String model = RandomStringUtils.randomAlphanumeric(3, 10);
        String grossWeight = Long.toString(faker.number().randomNumber());
        String unlandWeight = Long.toString(faker.number().randomNumber());
        String color = faker.color().name();
        String nyCert = RandomStringUtils.randomAlphanumeric(6);
        String date = getDateAndTime("dd/MM/yyyy");



        loginLocalSitePage.logInWithOutOpenMenu(login, pass);
        dashboardLocalSitePage.goToSafetyPage();
        waitABit(2);
        equipmentLocalSitePage.clickOnAddButton();
        waitABit(2);
        equipmentLocalSitePage.clickOnAddTrailerButton();
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
        String stateValue = dataForStateValue.get(state).toString();


        checkAC("TruckTrailer failed", tempDataSettingsMap.get("truckTrailer").equals("1"), true);
        checkAC("Name failed", tempDataSettingsMap.get("Name").equals(unitName), true);
        checkAC("Owner failed", tempDataSettingsMap.get("Owner").equals(owner), true);
        checkAC("Year failed", tempDataSettingsMap.get("Year").equals(year), true);
        checkAC("Type failed", tempDataSettingsMap.get("Type").equals(typeTemp), true);
        checkAC("VIN failed", tempDataSettingsMap.get("VIN").equals(vin), true);
        checkAC("Plate failed", tempDataSettingsMap.get("Plate").equals(plate), true);
        checkAC("State failed", tempDataSettingsMap.get("State").equals(stateValue), true);

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
        checkAC("InspectionDue failed", tempDataSettingsMap.get("InspectionDue").equals(getStringDateTimeUTC("yyyy-MM-dd")), true);
        checkAC("90DayExp failed", tempDataSettingsMap.get("90DayExp").equals(getStringDateTimeUTC("yyyy-MM-dd")), true);
        checkAC("ProRateExp failed", tempDataSettingsMap.get("ProRateExp").equals(getStringDateTimeUTC("yyyy-MM-dd")), true);
        checkAC("ExpDate failed", tempDataSettingsMap.get("ExpDate").equals(getStringDateTimeUTC("yyyy-MM-dd")), true);
        checkAC("isActive failed", tempDataSettingsMap.get("isActive").equals("1"), true);


    }
    @Test
    public void upDateTruck() throws SQLException, IOException, ClassNotFoundException {

        String unitName = RandomStringUtils.randomAlphanumeric(1, 10);
        String owner = RandomStringUtils.randomAlphabetic( 5) + " " + RandomStringUtils.randomAlphabetic( 10);
        String type = Integer.toString(genRandomNumberBetweenTwoValues(0, 4));
        String year = Integer.toString(genRandomNumberBetweenTwoValues(1950, 2020));
        String vin = RandomStringUtils.randomAlphanumeric(17);
        String plate = RandomStringUtils.randomAlphanumeric(6);
        String state = genRandomState();
        String tireSize = RandomStringUtils.randomNumeric(6);
        String length = RandomStringUtils.randomNumeric(6);
        String fuel = genRandomFuelType();
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

        String idTruckRandom = utilsForDB.getRandomEquipmentId("carrierId", carrierId, "0");
        String notes = RandomStringUtils.randomAlphanumeric(15) + " " + RandomStringUtils.randomAlphanumeric(15) + " " + RandomStringUtils.randomAlphanumeric(10) + ".";

        List<ArrayList> tempDataList = utilsForDB.getDataEquipment(idTruckRandom);
        Map<String, Object> tempDataEquipmentBeforeTestMap = listArrayToMap(tempDataList);

        String isActive = tempDataEquipmentBeforeTestMap.get("isActive").toString();
        String nameBeforeTest = tempDataEquipmentBeforeTestMap.get("Name").toString();


        loginLocalSitePage.logInWithOutOpenMenu(login, pass);
        dashboardLocalSitePage.goToSafetyPage();
        waitABit(2);
        equipmentLocalSitePage.enterOnEquipmentPlaceHolder(nameBeforeTest);
        waitABit(3);
        equipmentLocalSitePage.clickOnEquipmentOnRow(nameBeforeTest);
        waitABit(2);
        equipmentLocalSitePage.clickOnEditButton();
        waitABit(2);
        String idTruck = equipmentLocalSitePage.getVehicleIdText();
        String permitDocRegistration = utilsForDB.getUrlPermitDoc( idTruck, "equipmentRegistration");
        String permitDocReport = utilsForDB.getUrlPermitDoc( idTruck, "equipmentAnnualReport");
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
        equipmentLocalSitePage.clickOnActive();
        equipmentLocalSitePage.enterNote(notes);
        equipmentLocalSitePage.enterNYCert(nyCert);
        equipmentLocalSitePage.enterInspectionDue(inspectionDueDate);
        equipmentLocalSitePage.enterNinetyDayExp(ninetyDayExpDate);
        equipmentLocalSitePage.enterProRate(proRateDueDate);
        equipmentLocalSitePage.enterExpDate(expDateDate);

        equipmentLocalSitePage.addFileInspection(path);
        equipmentLocalSitePage.addFileRegistration(path);

        waitABit(5);
        equipmentLocalSitePage.clickOnSave();
        waitABit(5);

        List<ArrayList> tempDataListUpdatedTruck = utilsForDB.getDataEquipment(idTruck);
        Map<String, Object> tempDataEquipmentMap = listArrayToMap(tempDataListUpdatedTruck);

        checkAC("TruckTrailer failed", tempDataEquipmentMap.get("truckTrailer").equals("0"), true);
        checkACWithLogger("Name failed", tempDataEquipmentMap.get("Name").equals(unitName), true, tempDataEquipmentMap.get("Name").toString(), unitName);
        checkACWithLogger("Owner failed", tempDataEquipmentMap.get("Owner").equals(owner), true, tempDataEquipmentMap.get("Owner").toString(), owner);
        checkACWithLogger("Year failed", tempDataEquipmentMap.get("Year").equals(year), true, tempDataEquipmentMap.get("Year").toString(), year);
        checkACWithLogger("Type failed", tempDataEquipmentMap.get("Type").equals(type), true, tempDataEquipmentMap.get("Type").toString(), type);
        checkACWithLogger("VIN failed", tempDataEquipmentMap.get("VIN").equals(vin), true, tempDataEquipmentMap.get("VIN").toString(), vin);
        checkACWithLogger("Plate failed", tempDataEquipmentMap.get("Plate").equals(plate), true, tempDataEquipmentMap.get("Plate").toString(), plate);
        checkACWithLogger("State failed", tempDataEquipmentMap.get("State").equals(state), true, tempDataEquipmentMap.get("State").toString(), state);

        checkACWithLogger("TireSize failed", tempDataEquipmentMap.get("TireSize").equals(tireSize), true, tempDataEquipmentMap.get("TireSize").toString(), tireSize);
        checkACWithLogger("Length failed", tempDataEquipmentMap.get("Length").equals(length), true, tempDataEquipmentMap.get("Length").toString(), length);
        checkACWithLogger("Fuel failed", tempDataEquipmentMap.get("Fuel").equals(fuel), true, tempDataEquipmentMap.get("Fuel").toString(), fuel);
        checkACWithLogger("Axel failed", tempDataEquipmentMap.get("Axel").equals(axel), true, tempDataEquipmentMap.get("Axel").toString(), axel);
        checkACWithLogger("Make failed", tempDataEquipmentMap.get("Make").equals(make), true, tempDataEquipmentMap.get("Make").toString(), make);
        checkACWithLogger("Model failed", tempDataEquipmentMap.get("Model").equals(model), true, tempDataEquipmentMap.get("Model").toString(), model);
        checkACWithLogger("GrossWeight failed", tempDataEquipmentMap.get("GrossWeight").equals(grossWeight), true, tempDataEquipmentMap.get("GrossWeight").toString(), grossWeight);
        checkACWithLogger("UnlandWeight failed", tempDataEquipmentMap.get("UnlandWeight").equals(unlandWeight), true, tempDataEquipmentMap.get("UnlandWeight").toString(), unlandWeight);

        checkACWithLogger("Color failed", tempDataEquipmentMap.get("Color").equals(color), true, tempDataEquipmentMap.get("Color").toString(), color);
        checkACWithLogger("NYCert failed", tempDataEquipmentMap.get("NYCert").equals(nyCert), true, tempDataEquipmentMap.get("NYCert").toString(), nyCert);
        checkACWithLogger("InspectionDue failed", tempDataEquipmentMap.get("InspectionDue").equals(inspectionDueDate), true, tempDataEquipmentMap.get("InspectionDue").toString(), inspectionDueDate);
        checkACWithLogger("90DayExp failed", tempDataEquipmentMap.get("90DayExp").equals(ninetyDayExpDate), true, tempDataEquipmentMap.get("90DayExp").toString(), ninetyDayExpDate);
        checkACWithLogger("ProRateExp failed", tempDataEquipmentMap.get("ProRateExp").equals(proRateDueDate), true, tempDataEquipmentMap.get("ProRateExp").toString(), proRateDueDate);
        checkACWithLogger("ExpDate failed", tempDataEquipmentMap.get("ExpDate").equals(expDateDate), true, tempDataEquipmentMap.get("ExpDate").toString(), expDateDate);
        checkACWithLogger("isActive failed", tempDataEquipmentMap.get("isActive").equals(isActive), false, tempDataEquipmentMap.get("isActive").toString(), isActive);
        checkACWithLogger("Notes failed", tempDataEquipmentMap.get("Notes").equals(notes), true, tempDataEquipmentMap.get("Notes").toString(), notes);
        checkACWithLogger("File failed", utilsForDB.getUrlPermitDoc(idTruck, "equipmentRegistration").equals(permitDocRegistration), false, utilsForDB.getUrlPermitDoc(idTruck, "equipmentRegistration"), permitDocRegistration);
        checkAC("File failed", utilsForDB.getUrlPermitDoc(idTruck, "equipmentAnnualReport").equals(permitDocReport), false);

    }
    @Test
    public void upDateTrailer() throws SQLException, IOException, ClassNotFoundException {

        String unitName = RandomStringUtils.randomAlphanumeric(1, 10);
        String owner = RandomStringUtils.randomAlphabetic( 5) + " " + RandomStringUtils.randomAlphabetic( 10);
        String type = Integer.toString(genRandomNumberBetweenTwoValues(0, 4));
        String year = Integer.toString(genRandomNumberBetweenTwoValues(1950, 2020));
        String vin = RandomStringUtils.randomAlphanumeric(17);
        String plate = RandomStringUtils.randomAlphanumeric(6);
        String state = genRandomState();
        String tireSize = RandomStringUtils.randomNumeric(6);
        String length = RandomStringUtils.randomNumeric(6);
        String fuel = genRandomFuelType();
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

        String idTrailerRandom = utilsForDB.getRandomEquipmentId("carrierId", carrierId, "1");
        String notes = RandomStringUtils.randomAlphanumeric(15) + " " + RandomStringUtils.randomAlphanumeric(15) + " " + RandomStringUtils.randomAlphanumeric(10) + ".";

        List<ArrayList> tempDataList = utilsForDB.getDataEquipment(idTrailerRandom);
        Map<String, Object> tempDataEquipmentBeforeTestMap = listArrayToMap(tempDataList);
        String isActive = tempDataEquipmentBeforeTestMap.get("isActive").toString();
        String nameBeforeTest = tempDataEquipmentBeforeTestMap.get("Name").toString();

        loginLocalSitePage.userValidLogIn(login, pass);
        dashboardLocalSitePage.goToEquipmentPage();
        equipmentLocalSitePage.enterOnEquipmentPlaceHolder(nameBeforeTest);
        waitABit(3);
        equipmentLocalSitePage.clickOnEquipmentOnRow(nameBeforeTest);
        waitABit(2);
        equipmentLocalSitePage.clickOnEditButton();
        waitABit(2);
        String idTrailer = equipmentLocalSitePage.getVehicleIdText();
        String permitDocRegistration = utilsForDB.getUrlPermitDoc(idTrailer, "equipmentRegistration");
        String permitDocReport = utilsForDB.getUrlPermitDoc(idTrailer, "equipmentAnnualReport");
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
        equipmentLocalSitePage.clickOnActive();
        equipmentLocalSitePage.enterNote(notes);
        equipmentLocalSitePage.enterNYCert(nyCert);
        equipmentLocalSitePage.enterInspectionDue(inspectionDueDate);
        equipmentLocalSitePage.enterNinetyDayExp(ninetyDayExpDate);
        equipmentLocalSitePage.enterProRate(proRateDueDate);
        equipmentLocalSitePage.enterExpDate(expDateDate);

        equipmentLocalSitePage.addFileInspection(path);
        equipmentLocalSitePage.addFileRegistration(path);

        waitABit(5);
        equipmentLocalSitePage.clickOnSave();
        waitABit(5);

        List<ArrayList> tempDataListUpdatedTruck = utilsForDB.getDataEquipment(idTrailer);
        Map<String, Object> tempDataEquipmentMap = listArrayToMap(tempDataListUpdatedTruck);

        checkAC("TruckTrailer failed", tempDataEquipmentMap.get("truckTrailer").equals("1"), true);
        checkACWithLogger("Name failed", tempDataEquipmentMap.get("Name").equals(unitName), true, tempDataEquipmentMap.get("Name").toString(), unitName);
        checkACWithLogger("Owner failed", tempDataEquipmentMap.get("Owner").equals(owner), true, tempDataEquipmentMap.get("Owner").toString(), owner);
        checkACWithLogger("Year failed", tempDataEquipmentMap.get("Year").equals(year), true, tempDataEquipmentMap.get("Year").toString(), year);
        checkACWithLogger("Type failed", tempDataEquipmentMap.get("Type").equals(type), true, tempDataEquipmentMap.get("Type").toString(), type);
        checkACWithLogger("VIN failed", tempDataEquipmentMap.get("VIN").equals(vin), true, tempDataEquipmentMap.get("VIN").toString(), vin);
        checkACWithLogger("Plate failed", tempDataEquipmentMap.get("Plate").equals(plate), true, tempDataEquipmentMap.get("Plate").toString(), plate);
        checkACWithLogger("State failed", tempDataEquipmentMap.get("State").equals(state), true, tempDataEquipmentMap.get("State").toString(), state);

        checkACWithLogger("TireSize failed", tempDataEquipmentMap.get("TireSize").equals(tireSize), true, tempDataEquipmentMap.get("TireSize").toString(), tireSize);
        checkACWithLogger("Length failed", tempDataEquipmentMap.get("Length").equals(length), true, tempDataEquipmentMap.get("Length").toString(), length);
        checkACWithLogger("Fuel failed", tempDataEquipmentMap.get("Fuel").equals(fuel), true, tempDataEquipmentMap.get("Fuel").toString(), fuel);
        checkACWithLogger("Axel failed", tempDataEquipmentMap.get("Axel").equals(axel), true, tempDataEquipmentMap.get("Axel").toString(), axel);
        checkACWithLogger("Make failed", tempDataEquipmentMap.get("Make").equals(make), true, tempDataEquipmentMap.get("Make").toString(), make);
        checkACWithLogger("Model failed", tempDataEquipmentMap.get("Model").equals(model), true, tempDataEquipmentMap.get("Model").toString(), model);
        checkACWithLogger("GrossWeight failed", tempDataEquipmentMap.get("GrossWeight").equals(grossWeight), true, tempDataEquipmentMap.get("GrossWeight").toString(), grossWeight);
        checkACWithLogger("UnlandWeight failed", tempDataEquipmentMap.get("UnlandWeight").equals(unlandWeight), true, tempDataEquipmentMap.get("UnlandWeight").toString(), unlandWeight);

        checkACWithLogger("Color failed", tempDataEquipmentMap.get("Color").equals(color), true, tempDataEquipmentMap.get("Color").toString(), color);
        checkACWithLogger("NYCert failed", tempDataEquipmentMap.get("NYCert").equals(nyCert), true, tempDataEquipmentMap.get("NYCert").toString(), nyCert);
        checkACWithLogger("InspectionDue failed", tempDataEquipmentMap.get("InspectionDue").equals(inspectionDueDate), true, tempDataEquipmentMap.get("InspectionDue").toString(), inspectionDueDate);
        checkACWithLogger("90DayExp failed", tempDataEquipmentMap.get("90DayExp").equals(ninetyDayExpDate), true, tempDataEquipmentMap.get("90DayExp").toString(), ninetyDayExpDate);
        checkACWithLogger("ProRateExp failed", tempDataEquipmentMap.get("ProRateExp").equals(proRateDueDate), true, tempDataEquipmentMap.get("ProRateExp").toString(), proRateDueDate);
        checkACWithLogger("ExpDate failed", tempDataEquipmentMap.get("ExpDate").equals(expDateDate), true, tempDataEquipmentMap.get("ExpDate").toString(), expDateDate);
        checkACWithLogger("isActive failed", tempDataEquipmentMap.get("isActive").equals(isActive), false, tempDataEquipmentMap.get("isActive").toString(), isActive);
        checkACWithLogger("Notes failed", tempDataEquipmentMap.get("Notes").equals(notes), true, tempDataEquipmentMap.get("Notes").toString(), notes);
        checkACWithLogger("File failed", utilsForDB.getUrlPermitDoc(idTrailer, "equipmentRegistration").equals(permitDocRegistration), false, utilsForDB.getUrlPermitDoc(idTrailer, "equipmentRegistration"), permitDocRegistration);
        checkAC("File failed", utilsForDB.getUrlPermitDoc(idTrailer, "equipmentAnnualReport").equals(permitDocReport), false);

    }

}