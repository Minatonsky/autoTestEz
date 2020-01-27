package localTest;

import libs.UtilsForDB;
import org.junit.Test;
import parentTest.Parent3Test;


public class SoloDriverDocumentsLocalSiteTest extends Parent3Test {
    UtilsForDB utilsForDB = new UtilsForDB();
    String login = "den36@gmail.com";
    String pass = "testtest";
    String userId = "4401";
    @Test
    public void addDocument(){
        loginLocalSitePage.userValidLogIn(login, pass);
        dashboardLocalSitePage.goToSettingPage();
    }
}
