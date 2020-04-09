package frankesteinTest.documentsTest;

import libs.ExcelDriver;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import parentTest.ParentTest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static libs.Utils.*;

public class CarrierCreateDocumentsTest extends ParentTest {
    Map dataForValidLogIn = ExcelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "validFleetLogin");

    String login = dataForValidLogIn.get("login").toString();
    String pass = dataForValidLogIn.get("pass").toString();
    String carrierId = dataForValidLogIn.get("fleetId").toString();

    String fuelReceipts = "0";
    String lumper = "1";
    String scale = "2";
    String toll = "3";
    String truckRepairReceipts = "4";
    String trailerRepairReceipt = "5";
    String citationVehicleExaminationReport = "7";
    String accidentPhotoPoliceReport = "8";
    String annualInspectionReport = "10";
    String insurance = "11";
    String truckRegistration = "12";
    String trailerRegistration = "13";
    String others = "9";
    String BOL = "6";
    String picturePath = "C:\\workspace\\testdevEzlogz\\src\\main\\java\\data\\Pink-Floyd.jpg";


    public CarrierCreateDocumentsTest() throws IOException {
    }

    @Test
    public void createFuelReceipts() throws SQLException{
        String userId = utilsForDB.getUserIdByEmail(login);
        String dateTime = getDateAndTime("dd/MM/yyyy");
        String referenceRV = "FuelReceipts " + getDateAndTimeFormated() + "";
        String amountRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String gallonsRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String reeferAmountRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String reeferGallonsRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String notesTextRV = RandomStringUtils.randomAlphanumeric(5, 20);

        String truckId = utilsForDB.getRandomEquipmentIdCarrier(carrierId, "0");
        String truckName = utilsForDB.getEquipmentName(truckId);
        String driverId = utilsForDB.getRandomDriverIdInFleet(carrierId);
        String driverName = utilsForDB.getDriverNameById(driverId);

        String state = genRandomStateName();

        loginFPage.logInWithOutOpenMenu(login, pass);
        dashboardFPage.goToSafetyPage();
        dashboardFPage.goToDocumentsPage();

        documentsFPage.clickOnCreateButton();
        documentsFPage.selectTypeDocument("Fuel Receipts");
        waitABit(3);
        documentsFPage.reference(referenceRV);
        documentsFPage.documentDate(dateTime);
        documentsFPage.truckValue(truckName);

        documentsFPage.driverValue(driverName);
        documentsFPage.amount(amountRV);
        documentsFPage.gallons(gallonsRV);
        documentsFPage.reeferAmount(reeferAmountRV);
        documentsFPage.reeferGallons(reeferGallonsRV);
        documentsFPage.selectState(state);
        waitABit(5);
        documentsFPage.notesText(notesTextRV);
        documentsFPage.addPictureByJs(picturePath);

        documentsFPage.clickOnSaveButton();
        waitABit(5);

        List<ArrayList> tempDataDocList = utilsForDB.getDocData(carrierId, referenceRV);
        Map<String, Object> tempDataDocMap = listArrayToMap(tempDataDocList);
        String docId = tempDataDocMap.get("id").toString();

        checkAC("DocType is not correct", tempDataDocMap.get("type").equals(fuelReceipts), true);
        checkAC("DocDate is not correct", tempDataDocMap.get("date").equals(startDayPlusHours(12)), true);

        checkAC("TruckId is not correct", tempDataDocMap.get("truckId").equals(truckId), true);
        checkAC("InitiatorId is not correct", tempDataDocMap.get("initiatorId").equals(userId), true);
        checkAC("CarrierId is not correct", tempDataDocMap.get("carrierId").equals(carrierId), true);
        checkAC("Note is not correct", tempDataDocMap.get("note").equals(notesTextRV), true);
        checkAC("AWSName is empty", tempDataDocMap.get("awsName").toString().substring(0, 34).equals("https://s3.us-east-2.amazonaws.com"), true);

        checkAC("Amount is not correct", utilsForDB.getDocInfoData(docId, "amount").equals(amountRV), true);
        checkAC("Gallons is not correct", utilsForDB.getDocInfoData(docId, "gallons").equals(gallonsRV), true);
        checkAC("ReeferAmount is not correct", utilsForDB.getDocInfoData(docId, "reefer_amount").equals(reeferAmountRV), true);
        checkAC("ReeferGallons is not correct", utilsForDB.getDocInfoData(docId, "reefer_gallons").equals(reeferGallonsRV), true);
        checkAC("State is not correct", utilsForDB.getDocInfoData(docId, "state").equals("2"), true);
    }
    @Test
    public void createLumper() throws SQLException{
        String dateTime = getDateAndTime("dd/MM/yyyy");
        String referenceRV = "Lumper " + getDateAndTimeFormated() + "";
        String amountRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String locationRV = faker.twinPeaks().location();
        String notesTextRV = RandomStringUtils.randomAlphanumeric(5, 10);
        String userId = utilsForDB.getUserIdByEmail(login);

        String truckId = utilsForDB.getRandomEquipmentIdCarrier(carrierId, "0");
        String truckName = utilsForDB.getEquipmentName(truckId);
        String driverId = utilsForDB.getRandomDriverIdInFleet(carrierId);
        String driverName = utilsForDB.getDriverNameById(driverId);

        loginFPage.logInWithOutOpenMenu(login, pass);
        dashboardFPage.goToSafetyPage();
        dashboardFPage.goToDocumentsPage();
        documentsFPage.clickOnCreateButton();
        documentsFPage.selectTypeDocument("Lumper");
        waitABit(3);
        documentsFPage.reference(referenceRV);
        documentsFPage.truckValue(truckName);
        documentsFPage.driverValue(driverName);
        documentsFPage.amount(amountRV);
        documentsFPage.location(locationRV);
        documentsFPage.notesText(notesTextRV);
        documentsFPage.addPictureByJs(picturePath);
        documentsFPage.documentDate(dateTime);
        documentsFPage.clickOnSaveButton();
        waitABit(5);

        List<ArrayList> tempDataDocList = utilsForDB.getDocData(carrierId, referenceRV);
        Map<String, Object> tempDataDocMap = listArrayToMap(tempDataDocList);
        String docId = tempDataDocMap.get("id").toString();

        checkAC("DocType is not correct", tempDataDocMap.get("type").equals(lumper), true);
        checkAC("DocDate is not correct", tempDataDocMap.get("date").equals(startDayPlusHours(12)), true);
        checkAC("TruckId is not correct", tempDataDocMap.get("truckId").equals(truckId), true);
        checkAC("InitiatorId is not correct", tempDataDocMap.get("initiatorId").equals(userId), true);
        checkAC("Note is not correct", tempDataDocMap.get("note").equals(notesTextRV), true);
        checkAC("CarrierId is not correct", tempDataDocMap.get("carrierId").equals(carrierId), true);
        checkAC("AWSName is empty", tempDataDocMap.get("awsName").toString().substring(0, 34).equals("https://s3.us-east-2.amazonaws.com"), true);
        checkAC("Amount is not correct", utilsForDB.getDocInfoData(docId, "amount").equals(amountRV), true);
        checkAC("Location is not correct", utilsForDB.getDocInfoData(docId, "location").equals(locationRV), true);

    }
    @Test
    public void createScale() throws SQLException{
        String dateTime = getDateAndTime("dd/MM/yyyy");
        String referenceRV = "Scale " + getDateAndTimeFormated() + "";
        String scaleRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String notesTextRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String userId = utilsForDB.getUserIdByEmail(login);
        String truckId = utilsForDB.getRandomEquipmentIdCarrier(carrierId, "0");
        String driverId = utilsForDB.getRandomDriverIdInFleet(carrierId);
        String driverName = utilsForDB.getDriverNameById(driverId);
        String truckName = utilsForDB.getEquipmentName(truckId);

        loginFPage.logInWithOutOpenMenu(login, pass);
        dashboardFPage.goToSafetyPage();
        dashboardFPage.goToDocumentsPage();
        documentsFPage.clickOnCreateButton();
        documentsFPage.selectTypeDocument("Scale");
        waitABit(3);
        documentsFPage.reference(referenceRV);
        documentsFPage.truckValue(truckName);
        documentsFPage.driverValue(driverName);
        documentsFPage.scale(scaleRV);
        documentsFPage.notesText(notesTextRV);
        documentsFPage.addPictureByJs(picturePath);
        documentsFPage.documentDate(dateTime);
        documentsFPage.clickOnSaveButton();
        waitABit(5);

        List<ArrayList> tempDataDocList = utilsForDB.getDocData(carrierId, referenceRV);
        Map<String, Object> tempDataDocMap = listArrayToMap(tempDataDocList);
        String docId = tempDataDocMap.get("id").toString();

        checkAC("DocType is not correct", tempDataDocMap.get("type").equals(scale), true);
        checkAC("DocDate is not correct", tempDataDocMap.get("date").equals(startDayPlusHours(12)), true);
        checkAC("TruckId is not correct", tempDataDocMap.get("truckId").equals(truckId), true);
        checkAC("InitiatorId is not correct", tempDataDocMap.get("initiatorId").equals(userId), true);
        checkAC("Note is not correct", tempDataDocMap.get("note").equals(notesTextRV), true);
        checkAC("CarrierId Id is not correct", tempDataDocMap.get("carrierId").equals(carrierId), true);
        checkAC("AWSName is empty", tempDataDocMap.get("awsName").toString().substring(0, 34).equals("https://s3.us-east-2.amazonaws.com"), true);
        checkAC("Scale is not correct", utilsForDB.getDocInfoData(docId, "scale").equals(scaleRV), true);

    }
    @Test
    public void createToll() throws SQLException{
        String dateTime = getDateAndTime("dd/MM/yyyy");
        String referenceRV = "Toll " + getDateAndTimeFormated() + "";
        String amountRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String notesTextRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String userId = utilsForDB.getUserIdByEmail(login);
        String truckId = utilsForDB.getRandomEquipmentIdCarrier(carrierId, "0");
        String driverId = utilsForDB.getRandomDriverIdInFleet(carrierId);
        String driverName = utilsForDB.getDriverNameById(driverId);
        String truckName = utilsForDB.getEquipmentName(truckId);

        loginFPage.logInWithOutOpenMenu(login, pass);
        dashboardFPage.goToSafetyPage();
        dashboardFPage.goToDocumentsPage();
        documentsFPage.clickOnCreateButton();
        documentsFPage.selectTypeDocument("Toll");
        waitABit(3);
        documentsFPage.reference(referenceRV);
        documentsFPage.truckValue(truckName);
        documentsFPage.driverValue(driverName);
        documentsFPage.amount(amountRV);
        documentsFPage.notesText(notesTextRV);
        documentsFPage.addPictureByJs(picturePath);
        documentsFPage.documentDate(dateTime);
        documentsFPage.clickOnSaveButton();
        waitABit(10);

        List<ArrayList> tempDataDocList = utilsForDB.getDocData(carrierId, referenceRV);
        Map<String, Object> tempDataDocMap = listArrayToMap(tempDataDocList);
        String docId = tempDataDocMap.get("id").toString();

        checkAC("DocType is not correct", tempDataDocMap.get("type").equals(toll), true);
        checkAC("DocDate is not correct", tempDataDocMap.get("date").equals(startDayPlusHours(12)), true);
        checkAC("TruckId is not correct", tempDataDocMap.get("truckId").equals(truckId), true);
        checkAC("InitiatorId is not correct", tempDataDocMap.get("initiatorId").equals(userId), true);
        checkAC("Note is not correct", tempDataDocMap.get("note").equals(notesTextRV), true);
        checkAC("CarrierId Id is not correct", tempDataDocMap.get("carrierId").equals(carrierId), true);
        checkAC("AWSName is empty", tempDataDocMap.get("awsName").toString().substring(0, 34).equals("https://s3.us-east-2.amazonaws.com"), true);
        checkAC("Amount is not correct", utilsForDB.getDocInfoData(docId, "amount").equals(amountRV), true);
    }
    @Test
    public void createTruckRepairReceipts() throws SQLException{
        String dateTime =getDateAndTime("dd/MM/yyyy");
        String referenceRV = "TruckRepair " + getDateAndTimeFormated() + "";
        String amountRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String dealerRV = faker.funnyName().name();
        String notesTextRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String userId = utilsForDB.getUserIdByEmail(login);
        String truckId = utilsForDB.getRandomEquipmentIdCarrier(carrierId, "0");
        String driverId = utilsForDB.getRandomDriverIdInFleet(carrierId);
        String driverName = utilsForDB.getDriverNameById(driverId);
        String truckName = utilsForDB.getEquipmentName(truckId);

        loginFPage.logInWithOutOpenMenu(login, pass);
        dashboardFPage.goToSafetyPage();
        dashboardFPage.goToDocumentsPage();
        documentsFPage.clickOnCreateButton();
        documentsFPage.selectTypeDocument("Truck Repair");
        waitABit(3);
        documentsFPage.reference(referenceRV);
        documentsFPage.truckValue(truckName);
        documentsFPage.driverValue(driverName);
        documentsFPage.amount(amountRV);
        documentsFPage.dealer(dealerRV);
        documentsFPage.notesText(notesTextRV);
        documentsFPage.addPictureByJs(picturePath);
        documentsFPage.documentDate(dateTime);
        documentsFPage.clickOnSaveButton();
        waitABit(5);

        List<ArrayList> tempDataDocList = utilsForDB.getDocData(carrierId, referenceRV);
        Map<String, Object> tempDataDocMap = listArrayToMap(tempDataDocList);
        String docId = tempDataDocMap.get("id").toString();

        checkAC("DocType is not correct", tempDataDocMap.get("type").equals(truckRepairReceipts), true);
        checkAC("DocDate is not correct", tempDataDocMap.get("date").equals(startDayPlusHours(12)), true);
        checkAC("TruckId is not correct", tempDataDocMap.get("truckId").equals(truckId), true);
        checkAC("InitiatorId is not correct", tempDataDocMap.get("initiatorId").equals(userId), true);
        checkAC("Note is not correct", tempDataDocMap.get("note").equals(notesTextRV), true);
        checkAC("CarrierId Id is not correct", tempDataDocMap.get("carrierId").equals(carrierId), true);
        checkAC("AWSName is empty", tempDataDocMap.get("awsName").toString().substring(0, 34).equals("https://s3.us-east-2.amazonaws.com"), true);
        checkAC("Amount is not correct", utilsForDB.getDocInfoData(docId, "amount").equals(amountRV), true);
        checkAC("Location is not correct", utilsForDB.getDocInfoData(docId, "dealer").equals(dealerRV), true);

    }
    @Test
    public void createTrailerRepairReceipts() throws SQLException{
        String dateTime = getDateAndTime("dd/MM/yyyy");
        String referenceRV = "TrailerRepair " + getDateAndTimeFormated() + "";
        String amountRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String dealerRV = faker.funnyName().name();
        String notesTextRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String userId = utilsForDB.getUserIdByEmail(login);
        String trailerId = utilsForDB.getRandomEquipmentIdCarrier(carrierId, "1");
        String driverId = utilsForDB.getRandomDriverIdInFleet(carrierId);
        String driverName = utilsForDB.getDriverNameById(driverId);
        String trailerName = utilsForDB.getEquipmentName(trailerId);

        loginFPage.logInWithOutOpenMenu(login, pass);
        dashboardFPage.goToSafetyPage();
        dashboardFPage.goToDocumentsPage();
        documentsFPage.clickOnCreateButton();
        documentsFPage.selectTypeDocument("Trailer Repair");
        waitABit(3);
        documentsFPage.reference(referenceRV);
        documentsFPage.trailerValue(trailerName);
        documentsFPage.driverValue(driverName);
        documentsFPage.amount(amountRV);
        documentsFPage.dealer(dealerRV);
        documentsFPage.notesText(notesTextRV);
        documentsFPage.addPictureByJs(picturePath);
        documentsFPage.documentDate(dateTime);
        documentsFPage.clickOnSaveButton();
        waitABit(5);

        List<ArrayList> tempDataDocList = utilsForDB.getDocData(carrierId, referenceRV);
        Map<String, Object> tempDataDocMap = listArrayToMap(tempDataDocList);
        String docId = tempDataDocMap.get("id").toString();

        checkAC("DocType is not correct", tempDataDocMap.get("type").equals(trailerRepairReceipt), true);
        checkAC("DocDate is not correct", tempDataDocMap.get("date").equals(startDayPlusHours(12)), true);
        checkAC("InitiatorId is not correct", tempDataDocMap.get("initiatorId").equals(userId), true);
        checkAC("Note is not correct", tempDataDocMap.get("note").equals(notesTextRV), true);
        checkAC("CarrierId Id is not correct", tempDataDocMap.get("carrierId").equals(carrierId), true);
        checkAC("AWSName is empty", tempDataDocMap.get("awsName").toString().substring(0, 34).equals("https://s3.us-east-2.amazonaws.com"), true);

        checkAC("TrailerId is not correct", utilsForDB.getDocInfoData(docId, "trailer_id").equals(trailerId), true);
        checkAC("Amount is not correct", utilsForDB.getDocInfoData(docId, "amount").equals(amountRV), true);
        checkAC("Location is not correct", utilsForDB.getDocInfoData(docId, "dealer").equals(dealerRV), true);

    }
    @Test
    public void createCitationVehicleExaminationReport() throws SQLException{
        String referenceRV = "CitationReport " + getDateAndTimeFormated() + "";
        String date = getDateAndTime("dd/MM/yyyy");
        String amountRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String notesTextRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String userId = utilsForDB.getUserIdByEmail(login);
        String truckId = utilsForDB.getRandomEquipmentIdCarrier(carrierId, "0");
        String driverId = utilsForDB.getRandomDriverIdInFleet(carrierId);
        String driverName = utilsForDB.getDriverNameById(driverId);
        String truckName = utilsForDB.getEquipmentName(truckId);
        String state = genRandomStateName();

        loginFPage.logInWithOutOpenMenu(login, pass);
        dashboardFPage.goToSafetyPage();
        dashboardFPage.goToDocumentsPage();
        documentsFPage.clickOnCreateButton();
        documentsFPage.selectTypeDocument("Citation");
        waitABit(3);
        documentsFPage.reference(referenceRV);
        documentsFPage.truckValue(truckName);
        documentsFPage.driverValue(driverName);
        documentsFPage.amount(amountRV);
        documentsFPage.selectState(state);
        documentsFPage.notesText(notesTextRV);
        documentsFPage.addPictureByJs(picturePath);
        documentsFPage.documentDate(date);
        documentsFPage.clickOnSaveButton();
        waitABit(5);

        List<ArrayList> tempDataDocList = utilsForDB.getDocData(carrierId, referenceRV);
        Map<String, Object> tempDataDocMap = listArrayToMap(tempDataDocList);
        String docId = tempDataDocMap.get("id").toString();

        checkAC("DocType is not correct", tempDataDocMap.get("type").equals(citationVehicleExaminationReport), true);
        checkAC("DocDate is not correct", tempDataDocMap.get("date").equals(startDayPlusHours(12)), true);
        checkAC("TruckId is not correct", tempDataDocMap.get("truckId").equals(truckId), true);
        checkAC("InitiatorId is not correct", tempDataDocMap.get("initiatorId").equals(userId), true);
        checkAC("Note is not correct", tempDataDocMap.get("note").equals(notesTextRV), true);
        checkAC("CarrierId Id is not correct", tempDataDocMap.get("carrierId").equals(carrierId), true);
        checkAC("AWSName is empty", tempDataDocMap.get("awsName").toString().substring(0, 34).equals("https://s3.us-east-2.amazonaws.com"), true);

        checkAC("Amount is not correct", utilsForDB.getDocInfoData(docId, "amount").equals(amountRV), true);
//        checkAC("State is not correct", utilsForDB.getDocInfoData(docId, "state").equals("2"), true);
    }
    @Test
    public void createAccidentPhotoPoliceReport() throws SQLException{
        String referenceRV = "AccidentReport " + getDateAndTimeFormated() + "";
        String date = getDateAndTime("dd/MM/yyyy");
        String locationRV = faker.twinPeaks().location();
        String notesTextRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String userId = utilsForDB.getUserIdByEmail(login);
        String truckId = utilsForDB.getRandomEquipmentIdCarrier(carrierId, "0");
        String driverId = utilsForDB.getRandomDriverIdInFleet(carrierId);
        String driverName = utilsForDB.getDriverNameById(driverId);
        String truckName = utilsForDB.getEquipmentName(truckId);

        loginFPage.logInWithOutOpenMenu(login, pass);
        dashboardFPage.goToSafetyPage();
        dashboardFPage.goToDocumentsPage();
        documentsFPage.clickOnCreateButton();
        documentsFPage.selectTypeDocument("Accident");
        waitABit(3);
        documentsFPage.reference(referenceRV);
        documentsFPage.truckValue(truckName);
        documentsFPage.driverValue(driverName);
        documentsFPage.location(locationRV);
        documentsFPage.notesText(notesTextRV);
        documentsFPage.addPictureByJs(picturePath);
        documentsFPage.documentDate(date);
        documentsFPage.clickOnSaveButton();
        waitABit(5);

        List<ArrayList> tempDataDocList = utilsForDB.getDocData(carrierId, referenceRV);
        Map<String, Object> tempDataDocMap = listArrayToMap(tempDataDocList);
        String docId = tempDataDocMap.get("id").toString();

        checkAC("DocType is not correct", tempDataDocMap.get("type").equals(accidentPhotoPoliceReport), true);
        checkAC("DocDate is not correct", tempDataDocMap.get("date").equals(startDayPlusHours(12)), true);
        checkAC("TruckId is not correct", tempDataDocMap.get("truckId").equals(truckId), true);
        checkAC("InitiatorId is not correct", tempDataDocMap.get("initiatorId").equals(userId), true);
        checkAC("Note is not correct", tempDataDocMap.get("note").equals(notesTextRV), true);
        checkAC("CarrierId Id is not correct", tempDataDocMap.get("carrierId").equals(carrierId), true);
        checkAC("AWSName is empty", tempDataDocMap.get("awsName").toString().substring(0, 34).equals("https://s3.us-east-2.amazonaws.com"), true);

        checkAC("Amount is not correct", utilsForDB.getDocInfoData(docId, "location").equals(locationRV), true);
    }
    @Test
    public void createAnnualInspectionReport() throws SQLException{

        String referenceRV = "AnnualInspection " + getDateAndTimeFormated() + "";
        String date = getDateAndTime("dd/MM/yyyy");
        String notesTextRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String userId = utilsForDB.getUserIdByEmail(login);
        String truckId = utilsForDB.getRandomEquipmentIdCarrier(carrierId, "0");
        String driverId = utilsForDB.getRandomDriverIdInFleet(carrierId);
        String driverName = utilsForDB.getDriverNameById(driverId);
        String truckName = utilsForDB.getEquipmentName(truckId);

        loginFPage.logInWithOutOpenMenu(login, pass);
        dashboardFPage.goToSafetyPage();
        dashboardFPage.goToDocumentsPage();
        documentsFPage.clickOnCreateButton();
        documentsFPage.selectTypeDocument("Annual");
        waitABit(3);
        documentsFPage.reference(referenceRV);
        documentsFPage.truckValue(truckName);
        documentsFPage.driverValue(driverName);
        documentsFPage.notesText(notesTextRV);
        documentsFPage.addPictureByJs(picturePath);
        documentsFPage.documentDate(date);
        documentsFPage.clickOnSaveButton();
        waitABit(5);

        List<ArrayList> tempDataDocList = utilsForDB.getDocData(carrierId, referenceRV);
        Map<String, Object> tempDataDocMap = listArrayToMap(tempDataDocList);

        checkAC("DocType is not correct", tempDataDocMap.get("type").equals(annualInspectionReport), true);
        checkAC("DocDate is not correct", tempDataDocMap.get("date").equals(startDayPlusHours(12)), true);
        checkAC("TruckId is not correct", tempDataDocMap.get("truckId").equals(truckId), true);
        checkAC("InitiatorId is not correct", tempDataDocMap.get("initiatorId").equals(userId), true);
        checkAC("Note is not correct", tempDataDocMap.get("note").equals(notesTextRV), true);
        checkAC("CarrierId Id is not correct", tempDataDocMap.get("carrierId").equals(carrierId), true);
        checkAC("AWSName is empty", tempDataDocMap.get("awsName").toString().substring(0, 34).equals("https://s3.us-east-2.amazonaws.com"), true);

    }
    @Test
    public void createInsurance() throws SQLException{

        String referenceRV = "Insurance " + getDateAndTimeFormated() + "";
        String date = getDateAndTime("dd/MM/yyyy");
        String notesTextRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String userId = utilsForDB.getUserIdByEmail(login);
        String truckId = utilsForDB.getRandomEquipmentIdCarrier(carrierId, "0");
        String driverId = utilsForDB.getRandomDriverIdInFleet(carrierId);
        String driverName = utilsForDB.getDriverNameById(driverId);
        String truckName = utilsForDB.getEquipmentName(truckId);

        loginFPage.logInWithOutOpenMenu(login, pass);
        dashboardFPage.goToSafetyPage();
        dashboardFPage.goToDocumentsPage();
        documentsFPage.clickOnCreateButton();
        documentsFPage.selectTypeDocument("Insurance");
        waitABit(3);
        documentsFPage.reference(referenceRV);
        documentsFPage.truckValue(truckName);
        documentsFPage.driverValue(driverName);
        documentsFPage.notesText(notesTextRV);
        documentsFPage.addPictureByJs(picturePath);
        documentsFPage.documentDate(date);
        documentsFPage.clickOnSaveButton();
        waitABit(5);

        List<ArrayList> tempDataDocList = utilsForDB.getDocData(carrierId, referenceRV);
        Map<String, Object> tempDataDocMap = listArrayToMap(tempDataDocList);

        checkAC("DocType is not correct", tempDataDocMap.get("type").equals(insurance), true);
        checkAC("DocDate is not correct", tempDataDocMap.get("date").equals(startDayPlusHours(12)), true);
        checkAC("TruckId is not correct", tempDataDocMap.get("truckId").equals(truckId), true);
        checkAC("InitiatorId is not correct", tempDataDocMap.get("initiatorId").equals(userId), true);
        checkAC("Note is not correct", tempDataDocMap.get("note").equals(notesTextRV), true);
        checkAC("CarrierId Id is not correct", tempDataDocMap.get("carrierId").equals(carrierId), true);
        checkAC("AWSName is empty", tempDataDocMap.get("awsName").toString().substring(0, 34).equals("https://s3.us-east-2.amazonaws.com"), true);

    }
    @Test
    public void createTruckRegistration() throws SQLException{

        String referenceRV = "TruckRegistration " + getDateAndTimeFormated() + "";
        String notesTextRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String date = getDateAndTime("dd/MM/yyyy");
        String userId = utilsForDB.getUserIdByEmail(login);
        String truckId = utilsForDB.getRandomEquipmentIdCarrier(carrierId, "0");
        String driverId = utilsForDB.getRandomDriverIdInFleet(carrierId);
        String driverName = utilsForDB.getDriverNameById(driverId);
        String truckName = utilsForDB.getEquipmentName(truckId);

        loginFPage.logInWithOutOpenMenu(login, pass);
        dashboardFPage.goToSafetyPage();
        dashboardFPage.goToDocumentsPage();
        documentsFPage.clickOnCreateButton();
        documentsFPage.selectTypeDocument("Truck registration");
        waitABit(3);
        documentsFPage.reference(referenceRV);
        documentsFPage.truckValue(truckName);
        documentsFPage.driverValue(driverName);
        documentsFPage.notesText(notesTextRV);
        documentsFPage.addPictureByJs(picturePath);
        documentsFPage.documentDate(date);
        documentsFPage.clickOnSaveButton();
        waitABit(5);

        List<ArrayList> tempDataDocList = utilsForDB.getDocData(carrierId, referenceRV);
        Map<String, Object> tempDataDocMap = listArrayToMap(tempDataDocList);

        checkAC("DocType is not correct", tempDataDocMap.get("type").equals(truckRegistration), true);
        checkAC("DocDate is not correct", tempDataDocMap.get("date").equals(startDayPlusHours(12)), true);
        checkAC("TruckId is not correct", tempDataDocMap.get("truckId").equals(truckId), true);
        checkAC("InitiatorId is not correct", tempDataDocMap.get("initiatorId").equals(userId), true);
        checkAC("Note is not correct", tempDataDocMap.get("note").equals(notesTextRV), true);
        checkAC("CarrierId Id is not correct", tempDataDocMap.get("carrierId").equals(carrierId), true);
        checkAC("AWSName is empty", tempDataDocMap.get("awsName").toString().substring(0, 34).equals("https://s3.us-east-2.amazonaws.com"), true);

    }
    @Test
    public void createTrailerRegistration() throws SQLException{

        String referenceRV = "TrailerRegistration " + getDateAndTimeFormated() + "";
        String date = getDateAndTime("dd/MM/yyyy");
        String notesTextRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String userId = utilsForDB.getUserIdByEmail(login);
        String trailerId = utilsForDB.getRandomEquipmentIdCarrier(carrierId, "1");
        String driverId = utilsForDB.getRandomDriverIdInFleet(carrierId);
        String driverName = utilsForDB.getDriverNameById(driverId);
        String trailerName = utilsForDB.getEquipmentName(trailerId);

        loginFPage.logInWithOutOpenMenu(login, pass);
        dashboardFPage.goToSafetyPage();
        dashboardFPage.goToDocumentsPage();
        documentsFPage.clickOnCreateButton();
        documentsFPage.selectTypeDocument("Trailer registration");
        waitABit(3);
        documentsFPage.reference(referenceRV);
        documentsFPage.trailerValue(trailerName);
        documentsFPage.driverValue(driverName);
        documentsFPage.notesText(notesTextRV);
        documentsFPage.addPictureByJs(picturePath);
        documentsFPage.documentDate(date);
        documentsFPage.clickOnSaveButton();
        waitABit(5);

        List<ArrayList> tempDataDocList = utilsForDB.getDocData(carrierId, referenceRV);
        Map<String, Object> tempDataDocMap = listArrayToMap(tempDataDocList);
        String docId = tempDataDocMap.get("id").toString();

        checkAC("DocType is not correct", tempDataDocMap.get("type").equals(trailerRegistration), true);
        checkAC("DocDate is not correct", tempDataDocMap.get("date").equals(startDayPlusHours(12)), true);
        checkAC("InitiatorId is not correct", tempDataDocMap.get("initiatorId").equals(userId), true);
        checkAC("Note is not correct", tempDataDocMap.get("note").equals(notesTextRV), true);
        checkAC("CarrierId Id is not correct", tempDataDocMap.get("carrierId").equals(carrierId), true);
        checkAC("AWSName is empty", tempDataDocMap.get("awsName").toString().substring(0, 34).equals("https://s3.us-east-2.amazonaws.com"), true);
        checkAC("TrailerId is not correct", utilsForDB.getDocInfoData(docId, "trailer_id").equals(trailerId), true);

    }
    @Test
    public void createOthers() throws SQLException{
        String referenceRV = "Others" + getDateAndTimeFormated() + "";
        String date = getDateAndTime("dd/MM/yyyy");
        String notesTextRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String userId = utilsForDB.getUserIdByEmail(login);
        String truckId = utilsForDB.getRandomEquipmentIdCarrier(carrierId, "0");
        String driverId = utilsForDB.getRandomDriverIdInFleet(carrierId);
        String driverName = utilsForDB.getDriverNameById(driverId);
        String truckName = utilsForDB.getEquipmentName(truckId);

        loginFPage.logInWithOutOpenMenu(login, pass);
        dashboardFPage.goToSafetyPage();
        dashboardFPage.goToDocumentsPage();
        documentsFPage.clickOnCreateButton();
        documentsFPage.selectTypeDocument("Others");
        waitABit(3);
        documentsFPage.reference(referenceRV);
        documentsFPage.truckValue(truckName);
        documentsFPage.driverValue(driverName);
        documentsFPage.notesText(notesTextRV);
        documentsFPage.addPictureByJs(picturePath);
        documentsFPage.documentDate(date);
        documentsFPage.clickOnSaveButton();
        waitABit(5);

        List<ArrayList> tempDataDocList = utilsForDB.getDocData(carrierId, referenceRV);
        Map<String, Object> tempDataDocMap = listArrayToMap(tempDataDocList);

        checkAC("DocType is not correct", tempDataDocMap.get("type").equals(others), true);
        checkAC("DocDate is not correct", tempDataDocMap.get("date").equals(startDayPlusHours(12)), true);
        checkAC("TruckId is not correct", tempDataDocMap.get("truckId").equals(truckId), true);
        checkAC("InitiatorId is not correct", tempDataDocMap.get("initiatorId").equals(userId), true);
        checkAC("Note is not correct", tempDataDocMap.get("note").equals(notesTextRV), true);
        checkAC("CarrierId Id is not correct", tempDataDocMap.get("carrierId").equals(carrierId), true);
        checkAC("AWSName is empty", tempDataDocMap.get("awsName").toString().substring(0, 34).equals("https://s3.us-east-2.amazonaws.com"), true);

    }
    @Test
    public void createBOL() throws SQLException{
        String dateTime =  getDateAndTime("dd/MM/yyyy");
        String referenceRV = "BOL" + getDateAndTimeFormated() + "";
        String shipperRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String shipDateRV = getCurrentDateTimePlusDays("dd/MM/yyyy", 2);
        String deliveryDateRV = getCurrentDateTimePlusDays("dd/MM/yyyy", 10);
        String shipDate = getCurrentDateTimePlusDays("yyyy-MM-dd", 2) + " " + "12:00:00";
        String deliveryDate = getCurrentDateTimePlusDays("yyyy-MM-dd", 10) + " " + "12:00:00";
        String notesTextRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String userId = utilsForDB.getUserIdByEmail(login);
        String truckId = utilsForDB.getRandomEquipmentIdCarrier(carrierId, "0");
        String driverId = utilsForDB.getRandomDriverIdInFleet(carrierId);
        String driverName = utilsForDB.getDriverNameById(driverId);
        String truckName = utilsForDB.getEquipmentName(truckId);

        loginFPage.logInWithOutOpenMenu(login, pass);
        dashboardFPage.goToSafetyPage();
        dashboardFPage.goToDocumentsPage();
        documentsFPage.clickOnCreateButton();
        documentsFPage.selectTypeDocument("BOL");
        waitABit(3);
        documentsFPage.reference(referenceRV);
        documentsFPage.truckValue(truckName);
        documentsFPage.driverValue(driverName);
        documentsFPage.shipper(shipperRV);
        documentsFPage.shipDate(shipDateRV);
        documentsFPage.deliveryDate(deliveryDateRV);
        documentsFPage.notesText(notesTextRV);
        documentsFPage.addPictureByJs(picturePath);
        documentsFPage.documentDate(dateTime);
        documentsFPage.clickOnSaveButton();
        waitABit(5);

        List<ArrayList> tempDataDocList = utilsForDB.getDocData(carrierId, referenceRV);
        Map<String, Object> tempDataDocMap = listArrayToMap(tempDataDocList);
        String docId = tempDataDocMap.get("id").toString();

        checkAC("DocType is not correct", tempDataDocMap.get("type").equals(BOL), true);
        checkAC("DocDate is not correct", tempDataDocMap.get("date").equals(startDayPlusHours(12)), true);
        checkAC("TruckId is not correct", tempDataDocMap.get("truckId").equals(truckId), true);
        checkAC("InitiatorId is not correct", tempDataDocMap.get("initiatorId").equals(userId), true);
        checkAC("Note is not correct", tempDataDocMap.get("note").equals(notesTextRV), true);
        checkAC("CarrierId Id is not correct", tempDataDocMap.get("carrierId").equals(carrierId), true);
        checkAC("AWSName is empty", tempDataDocMap.get("awsName").toString().substring(0, 34).equals("https://s3.us-east-2.amazonaws.com"), true);

        checkAC("Shipper is not correct", utilsForDB.getDocInfoData(docId, "shipper").equals(shipperRV), true);
        checkAC("ShipDate is not correct", utilsForDB.getDocInfoData(docId, "ShipDate").equals(shipDate), true);
        checkAC("DeliveryShipDate is not correct", utilsForDB.getDocInfoData(docId, "DeliveryDate").equals(deliveryDate), true);

    }
}
