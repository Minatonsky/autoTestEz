package localTest;

import org.junit.Test;
import parentTest.ParentTest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import static libs.Utils.waitABit;

public class SoloDriverDocumentsUpdateLocalSiteTest extends ParentTest {

    Map dataForValidLogIn = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "driverLogin");

    String login = dataForValidLogIn.get("login").toString();
    String pass = dataForValidLogIn.get("pass").toString();
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

    public SoloDriverDocumentsUpdateLocalSiteTest() throws IOException {
    }

    @Test
    public void updateFuelReceipts() throws SQLException, IOException, ClassNotFoundException {
        String userId = utilsForDB.getUserIdByEmail(login);
        String referenceRandom = utilsForDB.getRandomDocumentReference(userId, fuelReceipts);

        loginLocalSitePage.userValidLogIn(login, pass);
        dashboardLocalSitePage.goToDocumentPage();
        documentsLocalSitePage.enterReferenceInPlaceHolder(referenceRandom);
        waitABit(5);
        documentsLocalSitePage.clickOnDocumentInRow(referenceRandom);
        waitABit(5);

    }
}
