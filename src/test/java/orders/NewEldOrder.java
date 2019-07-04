package orders;

import org.junit.Test;
import parentTest.ParentTest;

public class NewEldOrder extends ParentTest {
    final String nameOrder = "NewOrder";

    @Test
    public void addNewOrder(){
        loginPage.userValidLogIn("rose@emailate.com", "testtest");
        dashboardPage.clickOnMenuDash();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        dashboardPage.clickOnMenuPageELD();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        dashboardPage.clickOnOrderELD();
        eldPage.checkCurrentUrl();

    }
}
