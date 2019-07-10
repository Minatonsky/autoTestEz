package orders;

import libs.UtilsForDB;
import org.junit.Test;
import parentTest.ParentTest;

import java.io.IOException;
import java.sql.SQLException;

public class NewEldOrderTest extends ParentTest {
    final String quantityOfDevices = "1";
    final String firstName = "Autotest";
    final String lastName = "Autotest";
    final String phone = "0676475006";
    final String addressLine = "Street";
    final String aptNumber = "2";
    final String deliveryCity = "Phoenix";
    final String zipCode = "30606";
    final String fleetId = "518";
    final String currentDue = "-1000";
    final String defaultTotalOrder = "120.77";
    final String defaultBalance = "879.23";


    @Test
    public void addNewOrder() throws InterruptedException, SQLException, IOException, ClassNotFoundException {

        UtilsForDB utilsForDB = new UtilsForDB();
        String idLastOrderBeforeTest = utilsForDB.getLastOrderIdForFleet(fleetId);
        utilsForDB.getSetCurrentDueForFleet(currentDue, fleetId);

        loginPage.userValidLogIn("rose@emailate.com", "testtest");
        dashboardPage.clickOnMenuDash();
        dashboardPage.clickOnMenuPageELD();
        Thread.sleep(1000);
        dashboardPage.clickOnOrderELD();
        modalEldPage.checkCurrentUrl();
        modalEldPage.enterPrimaryAddressLine(addressLine);
        modalEldPage.enterAptNumber(aptNumber);
        modalEldPage.enterDeliveryCity(deliveryCity);
        modalEldPage.selectState("1");
        modalEldPage.enterZipCode(zipCode);
        modalEldPage.enterQuantityDevices(quantityOfDevices);
        modalEldPage.enterFirstName(firstName);
        modalEldPage.enterLastName(lastName);
        modalEldPage.enterPhone(phone);
//        modalEldPage.clickPaymentMethods();

        modalEldPage.compareTotalOrder(defaultTotalOrder);
        checkAC("Total Order is not correct", modalEldPage.compareTotalOrder(defaultTotalOrder), true);

        modalEldPage.clickAgreement();
        modalEldPage.clickButtonFastMove();
        modalEldPage.clickButtonAgree();
        modalEldPage.clickButtonOrder();

        String idLastOrderAfterTest = utilsForDB.getLastOrderIdForFleet(fleetId);
        checkAC("New order wasn`t created", idLastOrderBeforeTest.equals(idLastOrderAfterTest) , false);

        dashboardPage.clickOnMenuDash();
        dashboardPage.clickOnMenuPageELD();
        dashboardPage.checkCurrentUrl();

        financesPage.compareBalance(defaultBalance);
        checkAC("Balance is not correct", financesPage.compareBalance(defaultBalance), true);



    }
}
