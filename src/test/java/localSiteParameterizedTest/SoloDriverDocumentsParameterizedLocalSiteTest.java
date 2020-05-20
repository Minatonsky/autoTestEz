package localSiteParameterizedTest;

import libs.SpreadsheetData;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
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
public class SoloDriverDocumentsParameterizedLocalSiteTest extends ParentTest {
    String login;

    public SoloDriverDocumentsParameterizedLocalSiteTest(String login) throws IOException {
        this.login = login;
    }
    @Parameterized.Parameters(name = "{0}")
    public static Collection testData() throws IOException {
        InputStream spreadsheet = new FileInputStream(configProperties.DATA_FILE_PATH() + "testLogin.xls");
        return new SpreadsheetData(spreadsheet, "loginPassParamsTest").getData();
    }

    String pass = "testtest";
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
    String picturePath = "C:\\Users\\1\\Pictures\\Saved Pictures\\274px-Nick_Cannon_by_David_Shankbone.jpg";


    @Test
    public void createFuelReceipts() throws SQLException, IOException, ClassNotFoundException {
        String dateTime = getDateAndTimeFormated();
        String referenceRV = "FuelReceipts " + dateTime + "";
        String amountRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String gallonsRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String reeferAmountRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String reeferGallonsRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String notesTextRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String documentDateRV = getDateFormat();
        String userId = utilsForDB.getUserIdByEmail(login);
        String truckId = utilsForDB.getRandomEquipmentId("userId", userId, "0");

        loginLocalSitePage.userValidLogIn(login, pass);
        dashboardLocalSitePage.goToDocumentPage();
        documentsLocalSitePage.clickOnCreateButton();
        documentsLocalSitePage.selectTypeDocument(fuelReceipts);
        documentsLocalSitePage.reference(referenceRV);
        documentsLocalSitePage.truckValue(truckId);
        documentsLocalSitePage.driverValue(userId);
        documentsLocalSitePage.amount(amountRV);
        documentsLocalSitePage.gallons(gallonsRV);
        documentsLocalSitePage.reeferAmount(reeferAmountRV);
        documentsLocalSitePage.reeferGallons(reeferGallonsRV);
        documentsLocalSitePage.selectState("2");
        documentsLocalSitePage.notesText(notesTextRV);
        documentsLocalSitePage.addPictureByJs(picturePath);
        documentsLocalSitePage.documentDate(documentDateRV);
        documentsLocalSitePage.clickOnSaveButton();
        waitABit(5);

        List<ArrayList> tempDataDocList = utilsForDB.getDocData(userId, referenceRV);
        Map<String, Object> tempDataDocMap = listArrayToMap(tempDataDocList);
        String docId = tempDataDocMap.get("id").toString();
        checkAC("DocType is not correct", tempDataDocMap.get("type").equals(fuelReceipts), true);

        String docDate = tempDataDocMap.get("date").toString();
        String tempDate = StringUtils.substring(docDate, 0, docDate.length() - 9);
        checkAC("DocDate is not correct", tempDate.equals(documentDateRV), true);

        checkAC("TruckId is not correct", tempDataDocMap.get("truckId").equals(truckId), true);
        checkAC("InitiatorId is not correct", tempDataDocMap.get("initiatorId").equals(userId), true);
        checkAC("Note is not correct", tempDataDocMap.get("note").equals(notesTextRV), true);
        checkAC("AWSName is empty", tempDataDocMap.get("awsName").toString().substring(0, 34).equals("https://s3.us-east-2.amazonaws.com"), true);

        checkAC("Amount is not correct", utilsForDB.getDocInfoData(docId, "amount").equals(amountRV), true);
        checkAC("Gallons is not correct", utilsForDB.getDocInfoData(docId, "gallons").equals(gallonsRV), true);
        checkAC("ReeferAmount is not correct", utilsForDB.getDocInfoData(docId, "reefer_amount").equals(reeferAmountRV), true);
        checkAC("ReeferGallons is not correct", utilsForDB.getDocInfoData(docId, "reefer_gallons").equals(reeferGallonsRV), true);
        checkAC("State is not correct", utilsForDB.getDocInfoData(docId, "state").equals("2"), true);
    }
    @Test
    public void createLumper() throws SQLException, IOException, ClassNotFoundException {
        String dateTime = getDateAndTimeFormated();
        String referenceRV = "Lumper " + dateTime + "";
        String amountRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String locationRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String notesTextRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String documentDateRV = getDateFormat();
        String userId = utilsForDB.getUserIdByEmail(login);
        String truckId = utilsForDB.getRandomEquipmentId("userId", userId, "0");

        loginLocalSitePage.userValidLogIn(login, pass);
        dashboardLocalSitePage.goToDocumentPage();
        documentsLocalSitePage.clickOnCreateButton();
        documentsLocalSitePage.selectTypeDocument(lumper);
        documentsLocalSitePage.reference(referenceRV);
        documentsLocalSitePage.truckValue(truckId);
        documentsLocalSitePage.driverValue(userId);
        documentsLocalSitePage.amount(amountRV);
        documentsLocalSitePage.location(locationRV);
        documentsLocalSitePage.notesText(notesTextRV);
        documentsLocalSitePage.addPictureByJs(picturePath);
        documentsLocalSitePage.documentDate(documentDateRV);
        documentsLocalSitePage.clickOnSaveButton();
        waitABit(5);

        List<ArrayList> tempDataDocList = utilsForDB.getDocData(userId, referenceRV);
        Map<String, Object> tempDataDocMap = listArrayToMap(tempDataDocList);
        String docId = tempDataDocMap.get("id").toString();
        checkAC("DocType is not correct", tempDataDocMap.get("type").equals(lumper), true);
        String docDate = tempDataDocMap.get("date").toString();
        String tempDate = StringUtils.substring(docDate, 0, docDate.length() - 9);
        checkAC("DocDate is not correct", tempDate.equals(documentDateRV), true);
        checkAC("TruckId is not correct", tempDataDocMap.get("truckId").equals(truckId), true);
        checkAC("InitiatorId is not correct", tempDataDocMap.get("initiatorId").equals(userId), true);
        checkAC("Note is not correct", tempDataDocMap.get("note").equals(notesTextRV), true);
        checkAC("AWSName is empty", tempDataDocMap.get("awsName").toString().substring(0, 34).equals("https://s3.us-east-2.amazonaws.com"), true);

        checkAC("Amount is not correct", utilsForDB.getDocInfoData(docId, "amount").equals(amountRV), true);
        checkAC("Location is not correct", utilsForDB.getDocInfoData(docId, "location").equals(locationRV), true);

    }
    @Test
    public void createScale() throws SQLException, IOException, ClassNotFoundException {
        String dateTime = getDateAndTimeFormated();
        String referenceRV = "Scale " + dateTime + "";
        String scaleRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String notesTextRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String documentDateRV = getDateFormat();
        String userId = utilsForDB.getUserIdByEmail(login);
        String truckId = utilsForDB.getRandomEquipmentId("userId", userId, "0");

        loginLocalSitePage.userValidLogIn(login, pass);
        dashboardLocalSitePage.goToDocumentPage();
        documentsLocalSitePage.clickOnCreateButton();
        documentsLocalSitePage.selectTypeDocument(scale);
        documentsLocalSitePage.reference(referenceRV);
        documentsLocalSitePage.truckValue(truckId);
        documentsLocalSitePage.driverValue(userId);
        documentsLocalSitePage.scale(scaleRV);
        documentsLocalSitePage.notesText(notesTextRV);
        documentsLocalSitePage.addPictureByJs(picturePath);
        documentsLocalSitePage.documentDate(documentDateRV);
        documentsLocalSitePage.clickOnSaveButton();
        waitABit(5);

        List<ArrayList> tempDataDocList = utilsForDB.getDocData(userId, referenceRV);
        Map<String, Object> tempDataDocMap = listArrayToMap(tempDataDocList);
        String docId = tempDataDocMap.get("id").toString();
        checkAC("DocType is not correct", tempDataDocMap.get("type").equals(scale), true);
        String docDate = tempDataDocMap.get("date").toString();
        String tempDate = StringUtils.substring(docDate, 0, docDate.length() - 9);
        checkAC("DocDate is not correct", tempDate.equals(documentDateRV), true);
        checkAC("TruckId is not correct", tempDataDocMap.get("truckId").equals(truckId), true);
        checkAC("InitiatorId is not correct", tempDataDocMap.get("initiatorId").equals(userId), true);
        checkAC("Note is not correct", tempDataDocMap.get("note").equals(notesTextRV), true);
        checkAC("AWSName is empty", tempDataDocMap.get("awsName").toString().substring(0, 34).equals("https://s3.us-east-2.amazonaws.com"), true);

        checkAC("Scale is not correct", utilsForDB.getDocInfoData(docId, "scale").equals(scaleRV), true);

    }
    @Test
    public void createToll() throws SQLException, IOException, ClassNotFoundException {
        String dateTime = getDateAndTimeFormated();
        String referenceRV = "Toll " + dateTime + "";
        String amountRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String notesTextRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String documentDateRV = getDateFormat();
        String userId = utilsForDB.getUserIdByEmail(login);
        String truckId = utilsForDB.getRandomEquipmentId("userId", userId, "0");

        loginLocalSitePage.userValidLogIn(login, pass);
        dashboardLocalSitePage.goToDocumentPage();
        documentsLocalSitePage.clickOnCreateButton();
        documentsLocalSitePage.selectTypeDocument(toll);
        documentsLocalSitePage.reference(referenceRV);
        documentsLocalSitePage.truckValue(truckId);
        documentsLocalSitePage.driverValue(userId);
        documentsLocalSitePage.amount(amountRV);
        documentsLocalSitePage.notesText(notesTextRV);
        documentsLocalSitePage.addPictureByJs(picturePath);
        documentsLocalSitePage.documentDate(documentDateRV);
        documentsLocalSitePage.clickOnSaveButton();
        waitABit(10);

        List<ArrayList> tempDataDocList = utilsForDB.getDocData(userId, referenceRV);
        Map<String, Object> tempDataDocMap = listArrayToMap(tempDataDocList);
        String docId = tempDataDocMap.get("id").toString();
        checkAC("DocType is not correct", tempDataDocMap.get("type").equals(toll), true);
        String docDate = tempDataDocMap.get("date").toString();
        String tempDate = StringUtils.substring(docDate, 0, docDate.length() - 9);
        checkAC("DocDate is not correct", tempDate.equals(documentDateRV), true);
        checkAC("TruckId is not correct", tempDataDocMap.get("truckId").equals(truckId), true);
        checkAC("InitiatorId is not correct", tempDataDocMap.get("initiatorId").equals(userId), true);
        checkAC("Note is not correct", tempDataDocMap.get("note").equals(notesTextRV), true);
        checkAC("AWSName is empty", tempDataDocMap.get("awsName").toString().substring(0, 34).equals("https://s3.us-east-2.amazonaws.com"), true);

        checkAC("Amount is not correct", utilsForDB.getDocInfoData(docId, "amount").equals(amountRV), true);
    }
    @Test
    public void createTruckRepairReceipts() throws SQLException, IOException, ClassNotFoundException {
        String dateTime = getDateAndTimeFormated();
        String referenceRV = "TruckRepair " + dateTime + "";
        String amountRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String dealerRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String notesTextRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String documentDateRV = getDateFormat();
        String userId = utilsForDB.getUserIdByEmail(login);
        String truckId = utilsForDB.getRandomEquipmentId("userId", userId, "0");

        loginLocalSitePage.userValidLogIn(login, pass);
        dashboardLocalSitePage.goToDocumentPage();
        documentsLocalSitePage.clickOnCreateButton();
        documentsLocalSitePage.selectTypeDocument(truckRepairReceipts);
        documentsLocalSitePage.reference(referenceRV);
        documentsLocalSitePage.truckValue(truckId);
        documentsLocalSitePage.driverValue(userId);
        documentsLocalSitePage.amount(amountRV);
        documentsLocalSitePage.dealer(dealerRV);
        documentsLocalSitePage.notesText(notesTextRV);
        documentsLocalSitePage.addPictureByJs(picturePath);
        documentsLocalSitePage.documentDate(documentDateRV);
        documentsLocalSitePage.clickOnSaveButton();
        waitABit(5);

        List<ArrayList> tempDataDocList = utilsForDB.getDocData(userId, referenceRV);
        Map<String, Object> tempDataDocMap = listArrayToMap(tempDataDocList);
        String docId = tempDataDocMap.get("id").toString();
        checkAC("DocType is not correct", tempDataDocMap.get("type").equals(truckRepairReceipts), true);
        String docDate = tempDataDocMap.get("date").toString();
        String tempDate = StringUtils.substring(docDate, 0, docDate.length() - 9);
        checkAC("DocDate is not correct", tempDate.equals(documentDateRV), true);
        checkAC("TruckId is not correct", tempDataDocMap.get("truckId").equals(truckId), true);
        checkAC("InitiatorId is not correct", tempDataDocMap.get("initiatorId").equals(userId), true);
        checkAC("Note is not correct", tempDataDocMap.get("note").equals(notesTextRV), true);
        checkAC("AWSName is empty", tempDataDocMap.get("awsName").toString().substring(0, 34).equals("https://s3.us-east-2.amazonaws.com"), true);

        checkAC("Amount is not correct", utilsForDB.getDocInfoData(docId, "amount").equals(amountRV), true);
        checkAC("Location is not correct", utilsForDB.getDocInfoData(docId, "dealer").equals(dealerRV), true);

    }
    @Test
    public void createTrailerRepairReceipts() throws SQLException, IOException, ClassNotFoundException {
        String dateTime = getDateAndTimeFormated();
        String referenceRV = "TrailerRepair " + dateTime + "";
        String amountRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String dealerRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String notesTextRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String documentDateRV = getDateFormat();
        String userId = utilsForDB.getUserIdByEmail(login);
        String trailerId = utilsForDB.getRandomEquipmentId("userId", userId, "1");

        loginLocalSitePage.userValidLogIn(login, pass);
        dashboardLocalSitePage.goToDocumentPage();
        documentsLocalSitePage.clickOnCreateButton();
        documentsLocalSitePage.selectTypeDocument(trailerRepairReceipt);
        documentsLocalSitePage.reference(referenceRV);
        documentsLocalSitePage.trailerValue(trailerId);
        documentsLocalSitePage.driverValue(userId);
        documentsLocalSitePage.amount(amountRV);
        documentsLocalSitePage.dealer(dealerRV);
        documentsLocalSitePage.notesText(notesTextRV);
        documentsLocalSitePage.addPictureByJs(picturePath);
        documentsLocalSitePage.documentDate(documentDateRV);
        documentsLocalSitePage.clickOnSaveButton();
        waitABit(5);

        List<ArrayList> tempDataDocList = utilsForDB.getDocData(userId, referenceRV);
        Map<String, Object> tempDataDocMap = listArrayToMap(tempDataDocList);
        String docId = tempDataDocMap.get("id").toString();
        checkAC("DocType is not correct", tempDataDocMap.get("type").equals(trailerRepairReceipt), true);
        String docDate = tempDataDocMap.get("date").toString();
        String tempDate = StringUtils.substring(docDate, 0, docDate.length() - 9);
        checkAC("DocDate is not correct", tempDate.equals(documentDateRV), true);
        checkAC("TruckId is not correct", tempDataDocMap.get("truckId").equals("0"), true);
        checkAC("InitiatorId is not correct", tempDataDocMap.get("initiatorId").equals(userId), true);
        checkAC("Note is not correct", tempDataDocMap.get("note").equals(notesTextRV), true);
        checkAC("AWSName is empty", tempDataDocMap.get("awsName").toString().substring(0, 34).equals("https://s3.us-east-2.amazonaws.com"), true);

        checkAC("TrailerId is not correct", utilsForDB.getDocInfoData(docId, "trailer_id").equals(trailerId), true);
        checkAC("Amount is not correct", utilsForDB.getDocInfoData(docId, "amount").equals(amountRV), true);
        checkAC("Location is not correct", utilsForDB.getDocInfoData(docId, "dealer").equals(dealerRV), true);

    }
    @Test
    public void createCitationVehicleExaminationReport() throws SQLException, IOException, ClassNotFoundException {
        String dateTime = getDateAndTimeFormated();
        String referenceRV = "CitationReport " + dateTime + "";
        String amountRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String notesTextRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String documentDateRV = getDateFormat();
        String userId = utilsForDB.getUserIdByEmail(login);
        String truckId = utilsForDB.getRandomEquipmentId("userId", userId, "0");

        loginLocalSitePage.userValidLogIn(login, pass);
        dashboardLocalSitePage.goToDocumentPage();
        documentsLocalSitePage.clickOnCreateButton();
        documentsLocalSitePage.selectTypeDocument(citationVehicleExaminationReport);
        documentsLocalSitePage.reference(referenceRV);
        documentsLocalSitePage.truckValue(truckId);
        documentsLocalSitePage.driverValue(userId);
        documentsLocalSitePage.amount(amountRV);
        documentsLocalSitePage.selectState("2");
        documentsLocalSitePage.notesText(notesTextRV);
        documentsLocalSitePage.addPictureByJs(picturePath);
        documentsLocalSitePage.documentDate(documentDateRV);
        documentsLocalSitePage.clickOnSaveButton();
        waitABit(5);

        List<ArrayList> tempDataDocList = utilsForDB.getDocData(userId, referenceRV);
        Map<String, Object> tempDataDocMap = listArrayToMap(tempDataDocList);
        String docId = tempDataDocMap.get("id").toString();
        checkAC("DocType is not correct", tempDataDocMap.get("type").equals(citationVehicleExaminationReport), true);

        String docDate = tempDataDocMap.get("date").toString();
        String tempDate = StringUtils.substring(docDate, 0, docDate.length() - 9);
        checkAC("DocDate is not correct", tempDate.equals(documentDateRV), true);

        checkAC("TruckId is not correct", tempDataDocMap.get("truckId").equals(truckId), true);
        checkAC("InitiatorId is not correct", tempDataDocMap.get("initiatorId").equals(userId), true);
        checkAC("Note is not correct", tempDataDocMap.get("note").equals(notesTextRV), true);
        checkAC("AWSName is empty", tempDataDocMap.get("awsName").toString().substring(0, 34).equals("https://s3.us-east-2.amazonaws.com"), true);

        checkAC("Amount is not correct", utilsForDB.getDocInfoData(docId, "amount").equals(amountRV), true);
        checkAC("State is not correct", utilsForDB.getDocInfoData(docId, "state").equals("2"), true);
    }
    @Test
    public void createAccidentPhotoPoliceReport() throws SQLException, IOException, ClassNotFoundException {
        String dateTime = getDateAndTimeFormated();
        String referenceRV = "AccidentReport " + dateTime + "";
        String locationRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String notesTextRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String documentDateRV = getDateFormat();
        String userId = utilsForDB.getUserIdByEmail(login);
        String truckId = utilsForDB.getRandomEquipmentId("userId", userId, "0");
        String trailerId = utilsForDB.getRandomEquipmentId("userId", userId, "1");

        loginLocalSitePage.userValidLogIn(login, pass);
        dashboardLocalSitePage.goToDocumentPage();
        documentsLocalSitePage.clickOnCreateButton();
        documentsLocalSitePage.selectTypeDocument(accidentPhotoPoliceReport);
        documentsLocalSitePage.reference(referenceRV);
        documentsLocalSitePage.truckValue(truckId);
        documentsLocalSitePage.trailerValue(trailerId);
        documentsLocalSitePage.driverValue(userId);
        documentsLocalSitePage.location(locationRV);
        documentsLocalSitePage.notesText(notesTextRV);
        documentsLocalSitePage.addPictureByJs(picturePath);
        documentsLocalSitePage.documentDate(documentDateRV);
        documentsLocalSitePage.clickOnSaveButton();
        waitABit(5);

        List<ArrayList> tempDataDocList = utilsForDB.getDocData(userId, referenceRV);
        Map<String, Object> tempDataDocMap = listArrayToMap(tempDataDocList);
        String docId = tempDataDocMap.get("id").toString();
        checkAC("DocType is not correct", tempDataDocMap.get("type").equals(accidentPhotoPoliceReport), true);

        String docDate = tempDataDocMap.get("date").toString();
        String tempDate = StringUtils.substring(docDate, 0, docDate.length() - 9);
        checkAC("DocDate is not correct", tempDate.equals(documentDateRV), true);

        checkAC("TruckId is not correct", tempDataDocMap.get("truckId").equals(truckId), true);
        checkAC("InitiatorId is not correct", tempDataDocMap.get("initiatorId").equals(userId), true);
        checkAC("Note is not correct", tempDataDocMap.get("note").equals(notesTextRV), true);
        checkAC("AWSName is empty", tempDataDocMap.get("awsName").toString().substring(0, 34).equals("https://s3.us-east-2.amazonaws.com"), true);

        checkAC("Amount is not correct", utilsForDB.getDocInfoData(docId, "location").equals(locationRV), true);
    }
    @Test
    public void createAnnualInspectionReport() throws SQLException, IOException, ClassNotFoundException {
        String dateTime = getDateAndTimeFormated();
        String referenceRV = "AnnualInspection " + dateTime + "";
        String notesTextRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String documentDateRV = getDateFormat();
        String userId = utilsForDB.getUserIdByEmail(login);
        String truckId = utilsForDB.getRandomEquipmentId("userId", userId, "0");

        loginLocalSitePage.userValidLogIn(login, pass);
        dashboardLocalSitePage.goToDocumentPage();
        documentsLocalSitePage.clickOnCreateButton();
        documentsLocalSitePage.selectTypeDocument(annualInspectionReport);
        documentsLocalSitePage.reference(referenceRV);
        documentsLocalSitePage.truckValue(truckId);
        documentsLocalSitePage.driverValue(userId);
        documentsLocalSitePage.notesText(notesTextRV);
        documentsLocalSitePage.addPictureByJs(picturePath);
        documentsLocalSitePage.documentDate(documentDateRV);
        documentsLocalSitePage.clickOnSaveButton();
        waitABit(5);

        List<ArrayList> tempDataDocList = utilsForDB.getDocData(userId, referenceRV);
        Map<String, Object> tempDataDocMap = listArrayToMap(tempDataDocList);
        checkAC("DocType is not correct", tempDataDocMap.get("type").equals(annualInspectionReport), true);

        String docDate = tempDataDocMap.get("date").toString();
        String tempDate = StringUtils.substring(docDate, 0, docDate.length() - 9);
        checkAC("DocDate is not correct", tempDate.equals(documentDateRV), true);

        checkAC("TruckId is not correct", tempDataDocMap.get("truckId").equals(truckId), true);
        checkAC("InitiatorId is not correct", tempDataDocMap.get("initiatorId").equals(userId), true);
        checkAC("Note is not correct", tempDataDocMap.get("note").equals(notesTextRV), true);
        checkAC("AWSName is empty", tempDataDocMap.get("awsName").toString().substring(0, 34).equals("https://s3.us-east-2.amazonaws.com"), true);

    }
    @Test
    public void createInsurance() throws SQLException, IOException, ClassNotFoundException {
        String dateTime = getDateAndTimeFormated();
        String referenceRV = "Insurance " + dateTime + "";
        String notesTextRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String documentDateRV = getDateFormat();
        String userId = utilsForDB.getUserIdByEmail(login);
        String truckId = utilsForDB.getRandomEquipmentId("userId", userId, "0");

        loginLocalSitePage.userValidLogIn(login, pass);
        dashboardLocalSitePage.goToDocumentPage();
        documentsLocalSitePage.clickOnCreateButton();
        documentsLocalSitePage.selectTypeDocument(insurance);
        documentsLocalSitePage.reference(referenceRV);
        documentsLocalSitePage.truckValue(truckId);
        documentsLocalSitePage.driverValue(userId);
        documentsLocalSitePage.notesText(notesTextRV);
        documentsLocalSitePage.addPictureByJs(picturePath);
        documentsLocalSitePage.documentDate(documentDateRV);
        documentsLocalSitePage.clickOnSaveButton();
        waitABit(5);

        List<ArrayList> tempDataDocList = utilsForDB.getDocData(userId, referenceRV);
        Map<String, Object> tempDataDocMap = listArrayToMap(tempDataDocList);
        checkAC("DocType is not correct", tempDataDocMap.get("type").equals(insurance), true);

        String docDate = tempDataDocMap.get("date").toString();
        String tempDate = StringUtils.substring(docDate, 0, docDate.length() - 9);
        checkAC("DocDate is not correct", tempDate.equals(documentDateRV), true);

        checkAC("TruckId is not correct", tempDataDocMap.get("truckId").equals(truckId), true);
        checkAC("InitiatorId is not correct", tempDataDocMap.get("initiatorId").equals(userId), true);
        checkAC("Note is not correct", tempDataDocMap.get("note").equals(notesTextRV), true);
        checkAC("AWSName is empty", tempDataDocMap.get("awsName").toString().substring(0, 34).equals("https://s3.us-east-2.amazonaws.com"), true);

    }
    @Test
    public void createTruckRegistration() throws SQLException, IOException, ClassNotFoundException {
        String dateTime = getDateAndTimeFormated();
        String referenceRV = "TruckRegistration " + dateTime + "";
        String notesTextRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String documentDateRV = getDateFormat();
        String userId = utilsForDB.getUserIdByEmail(login);
        String truckId = utilsForDB.getRandomEquipmentId("userId", userId, "0");

        loginLocalSitePage.userValidLogIn(login, pass);
        dashboardLocalSitePage.goToDocumentPage();
        documentsLocalSitePage.clickOnCreateButton();
        documentsLocalSitePage.selectTypeDocument(truckRegistration);
        documentsLocalSitePage.reference(referenceRV);
        documentsLocalSitePage.truckValue(truckId);
        documentsLocalSitePage.driverValue(userId);
        documentsLocalSitePage.notesText(notesTextRV);
        documentsLocalSitePage.addPictureByJs(picturePath);
        documentsLocalSitePage.documentDate(documentDateRV);
        documentsLocalSitePage.clickOnSaveButton();
        waitABit(5);

        List<ArrayList> tempDataDocList = utilsForDB.getDocData(userId, referenceRV);
        Map<String, Object> tempDataDocMap = listArrayToMap(tempDataDocList);
        checkAC("DocType is not correct", tempDataDocMap.get("type").equals(truckRegistration), true);

        String docDate = tempDataDocMap.get("date").toString();
        String tempDate = StringUtils.substring(docDate, 0, docDate.length() - 9);
        checkAC("DocDate is not correct", tempDate.equals(documentDateRV), true);

        checkAC("TruckId is not correct", tempDataDocMap.get("truckId").equals(truckId), true);
        checkAC("InitiatorId is not correct", tempDataDocMap.get("initiatorId").equals(userId), true);
        checkAC("Note is not correct", tempDataDocMap.get("note").equals(notesTextRV), true);
        checkAC("AWSName is empty", tempDataDocMap.get("awsName").toString().substring(0, 34).equals("https://s3.us-east-2.amazonaws.com"), true);

    }
    @Test
    public void createTrailerRegistration() throws SQLException, IOException, ClassNotFoundException {
        String dateTime = getDateAndTimeFormated();
        String referenceRV = "TrailerRegistration " + dateTime + "";
        String notesTextRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String documentDateRV = getDateFormat();
        String userId = utilsForDB.getUserIdByEmail(login);
        String trailerId = utilsForDB.getRandomEquipmentId("userId", userId, "1");

        loginLocalSitePage.userValidLogIn(login, pass);
        dashboardLocalSitePage.goToDocumentPage();
        documentsLocalSitePage.clickOnCreateButton();
        documentsLocalSitePage.selectTypeDocument(trailerRegistration);
        documentsLocalSitePage.reference(referenceRV);
        documentsLocalSitePage.trailerValue(trailerId);
        documentsLocalSitePage.driverValue(userId);
        documentsLocalSitePage.notesText(notesTextRV);
        documentsLocalSitePage.addPictureByJs(picturePath);
        documentsLocalSitePage.documentDate(documentDateRV);
        documentsLocalSitePage.clickOnSaveButton();
        waitABit(5);

        List<ArrayList> tempDataDocList = utilsForDB.getDocData(userId, referenceRV);
        Map<String, Object> tempDataDocMap = listArrayToMap(tempDataDocList);
        checkAC("DocType is not correct", tempDataDocMap.get("type").equals(trailerRegistration), true);

        String docDate = tempDataDocMap.get("date").toString();
        String tempDate = StringUtils.substring(docDate, 0, docDate.length() - 9);
        checkAC("DocDate is not correct", tempDate.equals(documentDateRV), true);

        checkAC("TruckId is not correct", tempDataDocMap.get("truckId").equals("0"), true);
        checkAC("InitiatorId is not correct", tempDataDocMap.get("initiatorId").equals(userId), true);
        checkAC("Note is not correct", tempDataDocMap.get("note").equals(notesTextRV), true);
        checkAC("AWSName is empty", tempDataDocMap.get("awsName").toString().substring(0, 34).equals("https://s3.us-east-2.amazonaws.com"), true);
        String docId = tempDataDocMap.get("id").toString();
        checkAC("TrailerId is not correct", utilsForDB.getDocInfoData(docId, "trailer_id").equals(trailerId), true);

    }
    @Test
    public void createOthers() throws SQLException, IOException, ClassNotFoundException {
        String dateTime = getDateAndTimeFormated();
        String referenceRV = "Others" + dateTime + "";
        String notesTextRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String documentDateRV = getDateFormat();
        String userId = utilsForDB.getUserIdByEmail(login);
        String truckId = utilsForDB.getRandomEquipmentId("userId", userId, "0");

        loginLocalSitePage.userValidLogIn(login, pass);
        dashboardLocalSitePage.goToDocumentPage();
        documentsLocalSitePage.clickOnCreateButton();
        documentsLocalSitePage.selectTypeDocument(others);
        documentsLocalSitePage.reference(referenceRV);
        documentsLocalSitePage.truckValue(truckId);
        documentsLocalSitePage.driverValue(userId);
        documentsLocalSitePage.notesText(notesTextRV);
        documentsLocalSitePage.addPictureByJs(picturePath);
        documentsLocalSitePage.documentDate(documentDateRV);
        documentsLocalSitePage.clickOnSaveButton();
        waitABit(5);

        List<ArrayList> tempDataDocList = utilsForDB.getDocData(userId, referenceRV);
        Map<String, Object> tempDataDocMap = listArrayToMap(tempDataDocList);
        checkAC("DocType is not correct", tempDataDocMap.get("type").equals(others), true);

        String docDate = tempDataDocMap.get("date").toString();
        String tempDate = StringUtils.substring(docDate, 0, docDate.length() - 9);
        checkAC("DocDate is not correct", tempDate.equals(documentDateRV), true);

        checkAC("TruckId is not correct", tempDataDocMap.get("truckId").equals(truckId), true);
        checkAC("InitiatorId is not correct", tempDataDocMap.get("initiatorId").equals(userId), true);
        checkAC("Note is not correct", tempDataDocMap.get("note").equals(notesTextRV), true);
        checkAC("AWSName is empty", tempDataDocMap.get("awsName").toString().substring(0, 34).equals("https://s3.us-east-2.amazonaws.com"), true);

    }
    @Test
    public void createBOL() throws SQLException, IOException, ClassNotFoundException {
        String dateTime = getDateAndTimeFormated();
        String referenceRV = "BOL" + dateTime + "";
        String shipperRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String shipDateRV = getDateRandom();
        String deliveryDateRV = getDateRandom();
        String notesTextRV = RandomStringUtils.randomAlphanumeric(1, 10);
        String documentDateRV = getDateFormat();
        String userId = utilsForDB.getUserIdByEmail(login);
        String truckId = utilsForDB.getRandomEquipmentId("userId", userId, "0");

        loginLocalSitePage.userValidLogIn(login, pass);
        dashboardLocalSitePage.goToDocumentPage();
        documentsLocalSitePage.clickOnCreateButton();
        documentsLocalSitePage.selectTypeDocument(BOL);
        documentsLocalSitePage.reference(referenceRV);
        documentsLocalSitePage.truckValue(truckId);
        documentsLocalSitePage.driverValue(userId);
        documentsLocalSitePage.shipper(shipperRV);
        documentsLocalSitePage.shipDate(shipDateRV);
        documentsLocalSitePage.deliveryDate(deliveryDateRV);
        documentsLocalSitePage.notesText(notesTextRV);
        documentsLocalSitePage.addPictureByJs(picturePath);
        documentsLocalSitePage.documentDate(documentDateRV);
        documentsLocalSitePage.clickOnSaveButton();
        waitABit(5);

        List<ArrayList> tempDataDocList = utilsForDB.getDocData(userId, referenceRV);
        Map<String, Object> tempDataDocMap = listArrayToMap(tempDataDocList);
        String docId = tempDataDocMap.get("id").toString();
        checkAC("DocType is not correct", tempDataDocMap.get("type").equals(BOL), true);

        String docDate = tempDataDocMap.get("date").toString();
        String tempDate = StringUtils.substring(docDate, 0, docDate.length() - 9);
        checkAC("DocDate is not correct", tempDate.equals(documentDateRV), true);

        checkAC("TruckId is not correct", tempDataDocMap.get("truckId").equals(truckId), true);
        checkAC("InitiatorId is not correct", tempDataDocMap.get("initiatorId").equals(userId), true);
        checkAC("Note is not correct", tempDataDocMap.get("note").equals(notesTextRV), true);
        checkAC("AWSName is empty", tempDataDocMap.get("awsName").toString().substring(0, 34).equals("https://s3.us-east-2.amazonaws.com"), true);

        checkAC("Amount is not correct", utilsForDB.getDocInfoData(docId, "shipper").equals(shipperRV), true);
        checkAC("Gallons is not correct", utilsForDB.getDocInfoData(docId, "ShipDate").equals(shipDateRV), true);
        checkAC("ReeferAmount is not correct", utilsForDB.getDocInfoData(docId, "DeliveryShipDate").equals(deliveryDateRV), true);

    }
}
