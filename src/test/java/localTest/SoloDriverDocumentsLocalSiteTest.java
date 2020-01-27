package localTest;

import libs.UtilsForDB;
import org.junit.Test;
import parentTest.Parent3Test;

import static libs.Utils.waitABit;


public class SoloDriverDocumentsLocalSiteTest extends Parent3Test {
    UtilsForDB utilsForDB = new UtilsForDB();
    String login = "den36@gmail.com";
    String pass = "testtest";
    String userId = "4401";
    @Test
    public void createFuelReceipts(){
        loginLocalSitePage.userValidLogIn(login, pass);
        dashboardLocalSitePage.goToDocumentPage();
        documentsLocalSitePage.clickOnCreateButton();
        documentsLocalSitePage.selectTypeDocument("0");
//        documentsLocalSitePage.reference();
//        documentsLocalSitePage.documentDate();
//        documentsLocalSitePage.truckValue();
//        documentsLocalSitePage.driverValue();
//        documentsLocalSitePage.amount();
//        documentsLocalSitePage.gallons();
//        documentsLocalSitePage.reeferAmount();
//        documentsLocalSitePage.reeferGallons();
//        documentsLocalSitePage.selectState("2");
//        documentsLocalSitePage.notesText();
        documentsLocalSitePage.clickOnImageButton();
        documentsLocalSitePage.sendImages("2222");
//        documentsLocalSitePage.clickOnSaveButton();

        waitABit(5);
    }
}
