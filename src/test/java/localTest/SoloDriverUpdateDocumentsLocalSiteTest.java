package localTest;

import libs.UtilsForDB;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

public class SoloDriverUpdateDocumentsLocalSiteTest {
    UtilsForDB utilsForDB = new UtilsForDB();
    String login = "den36@gmail.com";
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
    @Test
    public void updateFuelReceipts() throws SQLException, IOException, ClassNotFoundException {
        String userId = utilsForDB.getUserIdByEmail(login);
        String referenceRandom = utilsForDB.getRandomDocumentReference(userId, fuelReceipts);
    }
}
