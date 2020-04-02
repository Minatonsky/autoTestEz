package frankesteinTest.documentsTest;

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
    Map dataForValidLogIn = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "validFleetLogin");

    String login = dataForValidLogIn.get("login").toString();
    String pass = dataForValidLogIn.get("pass").toString();
    String carrierId = dataForValidLogIn.get("fleetId").toString();

    String fuelReceipts = "Fuel";
    String lumper = "Lumper";
    String scale = "Scale";
    String toll = "Toll";
    String truckRepairReceipts = "Truck repair";
    String trailerRepairReceipt = "Trailer Repair";
    String citationVehicleExaminationReport = "Citation";
    String accidentPhotoPoliceReport = "Accident";
    String annualInspectionReport = "Annual";
    String insurance = "Insurance";
    String truckRegistration = "Truck registration";
    String trailerRegistration = "Trailer registration";
    String others = "Others";
    String BOL = "BOL";
    String picturePath = "C:\\workspace\\testdevEzlogz\\src\\main\\java\\data\\Pink-Floyd.jpg";


    public CarrierCreateDocumentsTest() throws IOException {
    }

    @Test
    public void createFuelReceipts() throws SQLException{
        String userId = utilsForDB.getUserIdByEmail(login);
        String dateTime = getDateAndTimeFormated();
        String referenceRV = "FuelReceipts " + dateTime + "";
        String amountRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String gallonsRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String reeferAmountRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String reeferGallonsRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String notesTextRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String truckId = utilsForDB.getRandomEquipmentIdCarrier(carrierId, "0");
        String driverId = utilsForDB.getRandomDriverIdInFleet(carrierId);

        loginFPage.logInWithOutOpenMenu(login, pass);
        dashboardFPage.goToSafetyPage();
        dashboardFPage.goToDocumentsPage();

        documentsFPage.clickOnCreateButton();
        documentsFPage.selectTypeDocument(fuelReceipts);
        waitABit(5);
        documentsFPage.reference(referenceRV);
        documentsFPage.truckValue(truckId);
        documentsFPage.driverValue(driverId);
        documentsFPage.amount(amountRV);
        documentsFPage.gallons(gallonsRV);
        documentsFPage.reeferAmount(reeferAmountRV);
        documentsFPage.reeferGallons(reeferGallonsRV);
        documentsFPage.selectState("2");
        documentsFPage.notesText(notesTextRV);
        documentsFPage.addPictureByJs(picturePath);
        documentsFPage.documentDate();
        documentsFPage.clickOnSaveButton();
        waitABit(5);

        List<ArrayList> tempDataDocList = utilsForDB.getDocData(driverId, referenceRV);
        Map<String, Object> tempDataDocMap = listArrayToMap(tempDataDocList);
        String docId = tempDataDocMap.get("id").toString();

        checkAC("DocType is not correct", tempDataDocMap.get("type").equals(fuelReceipts), true);
        checkAC("DocDate is not correct", tempDataDocMap.get("date").equals(startOfDay()), true);

        checkAC("TruckId is not correct", tempDataDocMap.get("truckId").equals(truckId), true);
        checkAC("InitiatorId is not correct", tempDataDocMap.get("initiatorId").equals(userId), true);
        checkAC("CarrierId is not correct", tempDataDocMap.get("carrierId").equals(carrierId), true);
        checkAC("Note is not correct", tempDataDocMap.get("note").equals(notesTextRV), true);
//        checkAC("AWSName is empty", tempDataDocMap.get("awsName").toString().substring(0, 34).equals("https://s3.us-east-2.amazonaws.com"), true);

        checkAC("Amount is not correct", utilsForDB.getDocInfoData(docId, "amount").equals(amountRV), true);
        checkAC("Gallons is not correct", utilsForDB.getDocInfoData(docId, "gallons").equals(gallonsRV), true);
        checkAC("ReeferAmount is not correct", utilsForDB.getDocInfoData(docId, "reefer_amount").equals(reeferAmountRV), true);
        checkAC("ReeferGallons is not correct", utilsForDB.getDocInfoData(docId, "reefer_gallons").equals(reeferGallonsRV), true);
        checkAC("State is not correct", utilsForDB.getDocInfoData(docId, "state").equals("2"), true);
    }
    @Test
    public void createLumper() throws SQLException{
        String dateTime = getDateAndTimeFormated();
        String referenceRV = "Lumper " + dateTime + "";
        String amountRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String locationRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String notesTextRV = RandomStringUtils.randomAlphanumeric(1, 10);

        String userId = utilsForDB.getUserIdByEmail(login);
        String truckId = utilsForDB.getRandomEquipmentIdCarrier(carrierId, "0");
        String driverId = utilsForDB.getRandomDriverIdInFleet(carrierId);

        loginFPage.logInWithOutOpenMenu(login, pass);
        dashboardFPage.goToSafetyPage();
        dashboardFPage.goToDocumentsPage();
        documentsFPage.clickOnCreateButton();
        documentsFPage.selectTypeDocument(lumper);
        documentsFPage.reference(referenceRV);
        documentsFPage.truckValue(truckId);
        documentsFPage.driverValue(driverId);
        documentsFPage.amount(amountRV);
        documentsFPage.location(locationRV);
        documentsFPage.notesText(notesTextRV);
        documentsFPage.addPictureByJs(picturePath);
        documentsFPage.documentDate();
        documentsFPage.clickOnSaveButton();
        waitABit(5);

        List<ArrayList> tempDataDocList = utilsForDB.getDocData(driverId, referenceRV);
        Map<String, Object> tempDataDocMap = listArrayToMap(tempDataDocList);
        String docId = tempDataDocMap.get("id").toString();

        checkAC("DocType is not correct", tempDataDocMap.get("type").equals(lumper), true);
        checkAC("DocDate is not correct", tempDataDocMap.get("date").equals(startOfDay()), true);
        checkAC("TruckId is not correct", tempDataDocMap.get("truckId").equals(truckId), true);
        checkAC("InitiatorId is not correct", tempDataDocMap.get("initiatorId").equals(userId), true);
        checkAC("Note is not correct", tempDataDocMap.get("note").equals(notesTextRV), true);
        checkAC("CarrierId is not correct", tempDataDocMap.get("carrierId").equals(carrierId), true);
//        checkAC("AWSName is empty", tempDataDocMap.get("awsName").toString().substring(0, 34).equals("https://s3.us-east-2.amazonaws.com"), true);

        checkAC("Amount is not correct", utilsForDB.getDocInfoData(docId, "amount").equals(amountRV), true);
        checkAC("Location is not correct", utilsForDB.getDocInfoData(docId, "location").equals(locationRV), true);

    }
    @Test
    public void createScale() throws SQLException{
        String dateTime = getDateAndTimeFormated();
        String referenceRV = "Scale " + dateTime + "";
        String scaleRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String notesTextRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String userId = utilsForDB.getUserIdByEmail(login);
        String truckId = utilsForDB.getRandomEquipmentIdCarrier(carrierId, "0");
        String driverId = utilsForDB.getRandomDriverIdInFleet(carrierId);

        loginFPage.logInWithOutOpenMenu(login, pass);
        dashboardFPage.goToSafetyPage();
        dashboardFPage.goToDocumentsPage();
        documentsFPage.clickOnCreateButton();
        documentsFPage.selectTypeDocument(scale);
        documentsFPage.reference(referenceRV);
        documentsFPage.truckValue(truckId);
        documentsFPage.driverValue(driverId);
        documentsFPage.scale(scaleRV);
        documentsFPage.notesText(notesTextRV);
        documentsFPage.addPictureByJs(picturePath);
        documentsFPage.documentDate();
        documentsFPage.clickOnSaveButton();
        waitABit(5);

        List<ArrayList> tempDataDocList = utilsForDB.getDocData(driverId, referenceRV);
        Map<String, Object> tempDataDocMap = listArrayToMap(tempDataDocList);
        String docId = tempDataDocMap.get("id").toString();

        checkAC("DocType is not correct", tempDataDocMap.get("type").equals(scale), true);
        checkAC("DocDate is not correct", tempDataDocMap.get("date").equals(startOfDay()), true);
        checkAC("TruckId is not correct", tempDataDocMap.get("truckId").equals(truckId), true);
        checkAC("InitiatorId is not correct", tempDataDocMap.get("initiatorId").equals(userId), true);
        checkAC("Note is not correct", tempDataDocMap.get("note").equals(notesTextRV), true);
        checkAC("CarrierId Id is not correct", tempDataDocMap.get("carrierId").equals(carrierId), true);
//        checkAC("AWSName is empty", tempDataDocMap.get("awsName").toString().substring(0, 34).equals("https://s3.us-east-2.amazonaws.com"), true);

        checkAC("Scale is not correct", utilsForDB.getDocInfoData(docId, "scale").equals(scaleRV), true);

    }
    @Test
    public void createToll() throws SQLException{
        String dateTime = getDateAndTimeFormated();
        String referenceRV = "Toll " + dateTime + "";
        String amountRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String notesTextRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String userId = utilsForDB.getUserIdByEmail(login);
        String truckId = utilsForDB.getRandomEquipmentIdCarrier(carrierId, "0");
        String driverId = utilsForDB.getRandomDriverIdInFleet(carrierId);

        loginFPage.logInWithOutOpenMenu(login, pass);
        dashboardFPage.goToSafetyPage();
        dashboardFPage.goToDocumentsPage();
        documentsFPage.clickOnCreateButton();
        documentsFPage.selectTypeDocument(toll);
        documentsFPage.reference(referenceRV);
        documentsFPage.truckValue(truckId);
        documentsFPage.driverValue(driverId);
        documentsFPage.amount(amountRV);
        documentsFPage.notesText(notesTextRV);
        documentsFPage.addPictureByJs(picturePath);
        documentsFPage.documentDate();
        documentsFPage.clickOnSaveButton();
        waitABit(10);

        List<ArrayList> tempDataDocList = utilsForDB.getDocData(driverId, referenceRV);
        Map<String, Object> tempDataDocMap = listArrayToMap(tempDataDocList);
        String docId = tempDataDocMap.get("id").toString();

        checkAC("DocType is not correct", tempDataDocMap.get("type").equals(toll), true);
        checkAC("DocDate is not correct", tempDataDocMap.get("date").equals(startOfDay()), true);
        checkAC("TruckId is not correct", tempDataDocMap.get("truckId").equals(truckId), true);
        checkAC("InitiatorId is not correct", tempDataDocMap.get("initiatorId").equals(userId), true);
        checkAC("Note is not correct", tempDataDocMap.get("note").equals(notesTextRV), true);
        checkAC("CarrierId Id is not correct", tempDataDocMap.get("carrierId").equals(carrierId), true);
//        checkAC("AWSName is empty", tempDataDocMap.get("awsName").toString().substring(0, 34).equals("https://s3.us-east-2.amazonaws.com"), true);

        checkAC("Amount is not correct", utilsForDB.getDocInfoData(docId, "amount").equals(amountRV), true);
    }
    @Test
    public void createTruckRepairReceipts() throws SQLException{
        String dateTime = getDateAndTimeFormated();
        String referenceRV = "TruckRepair " + dateTime + "";
        String amountRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String dealerRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String notesTextRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String userId = utilsForDB.getUserIdByEmail(login);
        String truckId = utilsForDB.getRandomEquipmentIdCarrier(carrierId, "0");
        String driverId = utilsForDB.getRandomDriverIdInFleet(carrierId);

        loginFPage.logInWithOutOpenMenu(login, pass);
        dashboardFPage.goToSafetyPage();
        dashboardFPage.goToDocumentsPage();
        documentsFPage.clickOnCreateButton();
        documentsFPage.selectTypeDocument(truckRepairReceipts);
        documentsFPage.reference(referenceRV);
        documentsFPage.truckValue(truckId);
        documentsFPage.driverValue(driverId);
        documentsFPage.amount(amountRV);
        documentsFPage.dealer(dealerRV);
        documentsFPage.notesText(notesTextRV);
        documentsFPage.addPictureByJs(picturePath);
        documentsFPage.documentDate();
        documentsFPage.clickOnSaveButton();
        waitABit(5);

        List<ArrayList> tempDataDocList = utilsForDB.getDocData(driverId, referenceRV);
        Map<String, Object> tempDataDocMap = listArrayToMap(tempDataDocList);
        String docId = tempDataDocMap.get("id").toString();

        checkAC("DocType is not correct", tempDataDocMap.get("type").equals(truckRepairReceipts), true);
        checkAC("DocDate is not correct", tempDataDocMap.get("date").equals(startOfDay()), true);
        checkAC("TruckId is not correct", tempDataDocMap.get("truckId").equals(truckId), true);
        checkAC("InitiatorId is not correct", tempDataDocMap.get("initiatorId").equals(userId), true);
        checkAC("Note is not correct", tempDataDocMap.get("note").equals(notesTextRV), true);
        checkAC("CarrierId Id is not correct", tempDataDocMap.get("carrierId").equals(carrierId), true);
//        checkAC("AWSName is empty", tempDataDocMap.get("awsName").toString().substring(0, 34).equals("https://s3.us-east-2.amazonaws.com"), true);

        checkAC("Amount is not correct", utilsForDB.getDocInfoData(docId, "amount").equals(amountRV), true);
        checkAC("Location is not correct", utilsForDB.getDocInfoData(docId, "dealer").equals(dealerRV), true);

    }
    @Test
    public void createTrailerRepairReceipts() throws SQLException{
        String dateTime = getDateAndTimeFormated();
        String referenceRV = "TrailerRepair " + dateTime + "";
        String amountRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String dealerRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String notesTextRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String userId = utilsForDB.getUserIdByEmail(login);
        String trailerId = utilsForDB.getRandomEquipmentIdCarrier(carrierId, "1");
        String driverId = utilsForDB.getRandomDriverIdInFleet(carrierId);

        loginFPage.logInWithOutOpenMenu(login, pass);
        dashboardFPage.goToSafetyPage();
        dashboardFPage.goToDocumentsPage();
        documentsFPage.clickOnCreateButton();
        documentsFPage.selectTypeDocument(trailerRepairReceipt);
        documentsFPage.reference(referenceRV);
        documentsFPage.trailerValue(trailerId);
        documentsFPage.driverValue(driverId);
        documentsFPage.amount(amountRV);
        documentsFPage.dealer(dealerRV);
        documentsFPage.notesText(notesTextRV);
        documentsFPage.addPictureByJs(picturePath);
        documentsFPage.documentDate();
        documentsFPage.clickOnSaveButton();
        waitABit(5);

        List<ArrayList> tempDataDocList = utilsForDB.getDocData(driverId, referenceRV);
        Map<String, Object> tempDataDocMap = listArrayToMap(tempDataDocList);
        String docId = tempDataDocMap.get("id").toString();

        checkAC("DocType is not correct", tempDataDocMap.get("type").equals(trailerRepairReceipt), true);
        checkAC("DocDate is not correct", tempDataDocMap.get("date").equals(startOfDay()), true);
        checkAC("TruckId is not correct", tempDataDocMap.get("truckId").equals("0"), true);
        checkAC("InitiatorId is not correct", tempDataDocMap.get("initiatorId").equals(userId), true);
        checkAC("Note is not correct", tempDataDocMap.get("note").equals(notesTextRV), true);
        checkAC("CarrierId Id is not correct", tempDataDocMap.get("carrierId").equals(carrierId), true);
//        checkAC("AWSName is empty", tempDataDocMap.get("awsName").toString().substring(0, 34).equals("https://s3.us-east-2.amazonaws.com"), true);

        checkAC("TrailerId is not correct", utilsForDB.getDocInfoData(docId, "trailer_id").equals(trailerId), true);
        checkAC("Amount is not correct", utilsForDB.getDocInfoData(docId, "amount").equals(amountRV), true);
        checkAC("Location is not correct", utilsForDB.getDocInfoData(docId, "dealer").equals(dealerRV), true);

    }
    @Test
    public void createCitationVehicleExaminationReport() throws SQLException{
        String referenceRV = "CitationReport " + getDateAndTimeFormated() + "";
        String amountRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String notesTextRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String userId = utilsForDB.getUserIdByEmail(login);
        String truckId = utilsForDB.getRandomEquipmentIdCarrier(carrierId, "0");
        String driverId = utilsForDB.getRandomDriverIdInFleet(carrierId);

        loginFPage.logInWithOutOpenMenu(login, pass);
        dashboardFPage.goToSafetyPage();
        dashboardFPage.goToDocumentsPage();
        documentsFPage.clickOnCreateButton();
        documentsFPage.selectTypeDocument(citationVehicleExaminationReport);
        documentsFPage.reference(referenceRV);
        documentsFPage.truckValue(truckId);
        documentsFPage.driverValue(driverId);
        documentsFPage.amount(amountRV);
        documentsFPage.selectState("2");
        documentsFPage.notesText(notesTextRV);
        documentsFPage.addPictureByJs(picturePath);
        documentsFPage.documentDate();
        documentsFPage.clickOnSaveButton();
        waitABit(5);

        List<ArrayList> tempDataDocList = utilsForDB.getDocData(driverId, referenceRV);
        Map<String, Object> tempDataDocMap = listArrayToMap(tempDataDocList);
        String docId = tempDataDocMap.get("id").toString();

        checkAC("DocType is not correct", tempDataDocMap.get("type").equals(citationVehicleExaminationReport), true);
        checkAC("DocDate is not correct", tempDataDocMap.get("date").equals(startOfDay()), true);
        checkAC("TruckId is not correct", tempDataDocMap.get("truckId").equals(truckId), true);
        checkAC("InitiatorId is not correct", tempDataDocMap.get("initiatorId").equals(userId), true);
        checkAC("Note is not correct", tempDataDocMap.get("note").equals(notesTextRV), true);
        checkAC("CarrierId Id is not correct", tempDataDocMap.get("carrierId").equals(carrierId), true);
//        checkAC("AWSName is empty", tempDataDocMap.get("awsName").toString().substring(0, 34).equals("https://s3.us-east-2.amazonaws.com"), true);

        checkAC("Amount is not correct", utilsForDB.getDocInfoData(docId, "amount").equals(amountRV), true);
        checkAC("State is not correct", utilsForDB.getDocInfoData(docId, "state").equals("2"), true);
    }
    @Test
    public void createAccidentPhotoPoliceReport() throws SQLException{
        String referenceRV = "AccidentReport " + getDateAndTimeFormated() + "";
        String locationRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String notesTextRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String userId = utilsForDB.getUserIdByEmail(login);
        String truckId = utilsForDB.getRandomEquipmentIdCarrier(carrierId, "0");
        String driverId = utilsForDB.getRandomDriverIdInFleet(carrierId);

        loginFPage.logInWithOutOpenMenu(login, pass);
        dashboardFPage.goToSafetyPage();
        dashboardFPage.goToDocumentsPage();
        documentsFPage.clickOnCreateButton();
        documentsFPage.selectTypeDocument(accidentPhotoPoliceReport);
        documentsFPage.reference(referenceRV);
        documentsFPage.truckValue(truckId);
        documentsFPage.driverValue(driverId);
        documentsFPage.location(locationRV);
        documentsFPage.notesText(notesTextRV);
        documentsFPage.addPictureByJs(picturePath);
        documentsFPage.documentDate();
        documentsFPage.clickOnSaveButton();
        waitABit(5);

        List<ArrayList> tempDataDocList = utilsForDB.getDocData(driverId, referenceRV);
        Map<String, Object> tempDataDocMap = listArrayToMap(tempDataDocList);
        String docId = tempDataDocMap.get("id").toString();

        checkAC("DocType is not correct", tempDataDocMap.get("type").equals(accidentPhotoPoliceReport), true);
        checkAC("DocDate is not correct", tempDataDocMap.get("date").equals(startOfDay()), true);
        checkAC("TruckId is not correct", tempDataDocMap.get("truckId").equals(truckId), true);
        checkAC("InitiatorId is not correct", tempDataDocMap.get("initiatorId").equals(userId), true);
        checkAC("Note is not correct", tempDataDocMap.get("note").equals(notesTextRV), true);
        checkAC("CarrierId Id is not correct", tempDataDocMap.get("carrierId").equals(carrierId), true);
//        checkAC("AWSName is empty", tempDataDocMap.get("awsName").toString().substring(0, 34).equals("https://s3.us-east-2.amazonaws.com"), true);

        checkAC("Amount is not correct", utilsForDB.getDocInfoData(docId, "location").equals(locationRV), true);
    }
    @Test
    public void createAnnualInspectionReport() throws SQLException{

        String referenceRV = "AnnualInspection " + getDateAndTimeFormated() + "";
        String notesTextRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String userId = utilsForDB.getUserIdByEmail(login);
        String truckId = utilsForDB.getRandomEquipmentIdCarrier(carrierId, "0");
        String driverId = utilsForDB.getRandomDriverIdInFleet(carrierId);

        loginFPage.logInWithOutOpenMenu(login, pass);
        dashboardFPage.goToSafetyPage();
        dashboardFPage.goToDocumentsPage();
        documentsFPage.clickOnCreateButton();
        documentsFPage.selectTypeDocument(annualInspectionReport);
        documentsFPage.reference(referenceRV);
        documentsFPage.truckValue(truckId);
        documentsFPage.driverValue(driverId);
        documentsFPage.notesText(notesTextRV);
        documentsFPage.addPictureByJs(picturePath);
        documentsFPage.documentDate();
        documentsFPage.clickOnSaveButton();
        waitABit(5);

        List<ArrayList> tempDataDocList = utilsForDB.getDocData(driverId, referenceRV);
        Map<String, Object> tempDataDocMap = listArrayToMap(tempDataDocList);

        checkAC("DocType is not correct", tempDataDocMap.get("type").equals(annualInspectionReport), true);
        checkAC("DocDate is not correct", tempDataDocMap.get("date").equals(startOfDay()), true);
        checkAC("TruckId is not correct", tempDataDocMap.get("truckId").equals(truckId), true);
        checkAC("InitiatorId is not correct", tempDataDocMap.get("initiatorId").equals(userId), true);
        checkAC("Note is not correct", tempDataDocMap.get("note").equals(notesTextRV), true);
        checkAC("CarrierId Id is not correct", tempDataDocMap.get("carrierId").equals(carrierId), true);
//        checkAC("AWSName is empty", tempDataDocMap.get("awsName").toString().substring(0, 34).equals("https://s3.us-east-2.amazonaws.com"), true);

    }
    @Test
    public void createInsurance() throws SQLException{

        String referenceRV = "Insurance " + getDateAndTimeFormated() + "";
        String notesTextRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String userId = utilsForDB.getUserIdByEmail(login);
        String truckId = utilsForDB.getRandomEquipmentIdCarrier(carrierId, "0");
        String driverId = utilsForDB.getRandomDriverIdInFleet(carrierId);

        loginFPage.logInWithOutOpenMenu(login, pass);
        dashboardFPage.goToSafetyPage();
        dashboardFPage.goToDocumentsPage();
        documentsFPage.clickOnCreateButton();
        documentsFPage.selectTypeDocument(insurance);
        documentsFPage.reference(referenceRV);
        documentsFPage.truckValue(truckId);
        documentsFPage.driverValue(driverId);
        documentsFPage.notesText(notesTextRV);
        documentsFPage.addPictureByJs(picturePath);
        documentsFPage.documentDate();
        documentsFPage.clickOnSaveButton();
        waitABit(5);

        List<ArrayList> tempDataDocList = utilsForDB.getDocData(driverId, referenceRV);
        Map<String, Object> tempDataDocMap = listArrayToMap(tempDataDocList);

        checkAC("DocType is not correct", tempDataDocMap.get("type").equals(insurance), true);
        checkAC("DocDate is not correct", tempDataDocMap.get("date").equals(startOfDay()), true);
        checkAC("TruckId is not correct", tempDataDocMap.get("truckId").equals(truckId), true);
        checkAC("InitiatorId is not correct", tempDataDocMap.get("initiatorId").equals(userId), true);
        checkAC("Note is not correct", tempDataDocMap.get("note").equals(notesTextRV), true);
        checkAC("CarrierId Id is not correct", tempDataDocMap.get("carrierId").equals(carrierId), true);
//        checkAC("AWSName is empty", tempDataDocMap.get("awsName").toString().substring(0, 34).equals("https://s3.us-east-2.amazonaws.com"), true);

    }
    @Test
    public void createTruckRegistration() throws SQLException{

        String referenceRV = "TruckRegistration " + getDateAndTimeFormated() + "";
        String notesTextRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String userId = utilsForDB.getUserIdByEmail(login);
        String truckId = utilsForDB.getRandomEquipmentIdCarrier(carrierId, "0");
        String driverId = utilsForDB.getRandomDriverIdInFleet(carrierId);

        loginFPage.logInWithOutOpenMenu(login, pass);
        dashboardFPage.goToSafetyPage();
        dashboardFPage.goToDocumentsPage();
        documentsFPage.clickOnCreateButton();
        documentsFPage.selectTypeDocument(truckRegistration);
        documentsFPage.reference(referenceRV);
        documentsFPage.truckValue(truckId);
        documentsFPage.driverValue(driverId);
        documentsFPage.notesText(notesTextRV);
        documentsFPage.addPictureByJs(picturePath);
        documentsFPage.documentDate();
        documentsFPage.clickOnSaveButton();
        waitABit(5);

        List<ArrayList> tempDataDocList = utilsForDB.getDocData(driverId, referenceRV);
        Map<String, Object> tempDataDocMap = listArrayToMap(tempDataDocList);

        checkAC("DocType is not correct", tempDataDocMap.get("type").equals(truckRegistration), true);
        checkAC("DocDate is not correct", tempDataDocMap.get("date").equals(startOfDay()), true);
        checkAC("TruckId is not correct", tempDataDocMap.get("truckId").equals(truckId), true);
        checkAC("InitiatorId is not correct", tempDataDocMap.get("initiatorId").equals(userId), true);
        checkAC("Note is not correct", tempDataDocMap.get("note").equals(notesTextRV), true);
        checkAC("CarrierId Id is not correct", tempDataDocMap.get("carrierId").equals(carrierId), true);
//        checkAC("AWSName is empty", tempDataDocMap.get("awsName").toString().substring(0, 34).equals("https://s3.us-east-2.amazonaws.com"), true);

    }
    @Test
    public void createTrailerRegistration() throws SQLException{

        String referenceRV = "TrailerRegistration " + getDateAndTimeFormated() + "";
        String notesTextRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String userId = utilsForDB.getUserIdByEmail(login);
        String trailerId = utilsForDB.getRandomEquipmentIdCarrier(carrierId, "1");
        String driverId = utilsForDB.getRandomDriverIdInFleet(carrierId);

        loginFPage.logInWithOutOpenMenu(login, pass);
        dashboardFPage.goToSafetyPage();
        dashboardFPage.goToDocumentsPage();
        documentsFPage.clickOnCreateButton();
        documentsFPage.selectTypeDocument(trailerRegistration);
        documentsFPage.reference(referenceRV);
        documentsFPage.trailerValue(trailerId);
        documentsFPage.driverValue(driverId);
        documentsFPage.notesText(notesTextRV);
        documentsFPage.addPictureByJs(picturePath);
        documentsFPage.documentDate();
        documentsFPage.clickOnSaveButton();
        waitABit(5);

        List<ArrayList> tempDataDocList = utilsForDB.getDocData(driverId, referenceRV);
        Map<String, Object> tempDataDocMap = listArrayToMap(tempDataDocList);
        String docId = tempDataDocMap.get("id").toString();

        checkAC("DocType is not correct", tempDataDocMap.get("type").equals(trailerRegistration), true);
        checkAC("DocDate is not correct", tempDataDocMap.get("date").equals(startOfDay()), true);
        checkAC("TruckId is not correct", tempDataDocMap.get("truckId").equals("0"), true);
        checkAC("InitiatorId is not correct", tempDataDocMap.get("initiatorId").equals(userId), true);
        checkAC("Note is not correct", tempDataDocMap.get("note").equals(notesTextRV), true);
        checkAC("CarrierId Id is not correct", tempDataDocMap.get("carrierId").equals(carrierId), true);
//        checkAC("AWSName is empty", tempDataDocMap.get("awsName").toString().substring(0, 34).equals("https://s3.us-east-2.amazonaws.com"), true);
        checkAC("TrailerId is not correct", utilsForDB.getDocInfoData(docId, "trailer_id").equals(trailerId), true);

    }
    @Test
    public void createOthers() throws SQLException{
        String referenceRV = "Others" + getDateAndTimeFormated() + "";
        String notesTextRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String userId = utilsForDB.getUserIdByEmail(login);
        String truckId = utilsForDB.getRandomEquipmentIdCarrier(carrierId, "0");
        String driverId = utilsForDB.getRandomDriverIdInFleet(carrierId);

        loginFPage.logInWithOutOpenMenu(login, pass);
        dashboardFPage.goToSafetyPage();
        dashboardFPage.goToDocumentsPage();
        documentsFPage.clickOnCreateButton();
        documentsFPage.selectTypeDocument(others);
        documentsFPage.reference(referenceRV);
        documentsFPage.truckValue(truckId);
        documentsFPage.driverValue(driverId);
        documentsFPage.notesText(notesTextRV);
        documentsFPage.addPictureByJs(picturePath);
        documentsFPage.documentDate();
        documentsFPage.clickOnSaveButton();
        waitABit(5);

        List<ArrayList> tempDataDocList = utilsForDB.getDocData(driverId, referenceRV);
        Map<String, Object> tempDataDocMap = listArrayToMap(tempDataDocList);

        checkAC("DocType is not correct", tempDataDocMap.get("type").equals(others), true);
        checkAC("DocDate is not correct", tempDataDocMap.get("date").equals(startOfDay()), true);
        checkAC("TruckId is not correct", tempDataDocMap.get("truckId").equals(truckId), true);
        checkAC("InitiatorId is not correct", tempDataDocMap.get("initiatorId").equals(userId), true);
        checkAC("Note is not correct", tempDataDocMap.get("note").equals(notesTextRV), true);
        checkAC("CarrierId Id is not correct", tempDataDocMap.get("carrierId").equals(carrierId), true);
//        checkAC("AWSName is empty", tempDataDocMap.get("awsName").toString().substring(0, 34).equals("https://s3.us-east-2.amazonaws.com"), true);

    }
    @Test
    public void createBOL() throws SQLException{
        String dateTime = getDateAndTimeFormated();
        String referenceRV = "BOL" + dateTime + "";
        String shipperRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String shipDateRV = getDateRandom();
        String deliveryDateRV = getDateRandom();
        String notesTextRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String userId = utilsForDB.getUserIdByEmail(login);
        String truckId = utilsForDB.getRandomEquipmentIdCarrier(carrierId, "0");
        String driverId = utilsForDB.getRandomDriverIdInFleet(carrierId);

        loginFPage.logInWithOutOpenMenu(login, pass);
        dashboardFPage.goToSafetyPage();
        dashboardFPage.goToDocumentsPage();
        documentsFPage.clickOnCreateButton();
        documentsFPage.selectTypeDocument(BOL);
        documentsFPage.reference(referenceRV);
        documentsFPage.truckValue(truckId);
        documentsFPage.driverValue(driverId);
        documentsFPage.shipper(shipperRV);
        documentsFPage.shipDate(shipDateRV);
        documentsFPage.deliveryDate(deliveryDateRV);
        documentsFPage.notesText(notesTextRV);
        documentsFPage.addPictureByJs(picturePath);
        documentsFPage.documentDate();
        documentsFPage.clickOnSaveButton();
        waitABit(5);

        List<ArrayList> tempDataDocList = utilsForDB.getDocData(driverId, referenceRV);
        Map<String, Object> tempDataDocMap = listArrayToMap(tempDataDocList);
        String docId = tempDataDocMap.get("id").toString();

        checkAC("DocType is not correct", tempDataDocMap.get("type").equals(BOL), true);
        checkAC("DocDate is not correct", tempDataDocMap.get("date").equals(startOfDay()), true);
        checkAC("TruckId is not correct", tempDataDocMap.get("truckId").equals(truckId), true);
        checkAC("InitiatorId is not correct", tempDataDocMap.get("initiatorId").equals(userId), true);
        checkAC("Note is not correct", tempDataDocMap.get("note").equals(notesTextRV), true);
        checkAC("CarrierId Id is not correct", tempDataDocMap.get("carrierId").equals(carrierId), true);
//        checkAC("AWSName is empty", tempDataDocMap.get("awsName").toString().substring(0, 34).equals("https://s3.us-east-2.amazonaws.com"), true);

        checkAC("Amount is not correct", utilsForDB.getDocInfoData(docId, "shipper").equals(shipperRV), true);
        checkAC("Gallons is not correct", utilsForDB.getDocInfoData(docId, "ShipDate").equals(shipDateRV), true);
        checkAC("ReeferAmount is not correct", utilsForDB.getDocInfoData(docId, "DeliveryShipDate").equals(deliveryDateRV), true);

    }
}
