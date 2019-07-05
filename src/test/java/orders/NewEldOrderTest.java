package orders;

import org.junit.Test;
import pages.ModalEldPage;
import parentTest.ParentTest;

public class NewEldOrderTest extends ParentTest {
    final String quantityOfDevices = "1";
    final String firstName = "Autotest";
    final String lastName = "Autotest";
    final String phone = "0676475006";
    final String addressLine = "Street";
    final String aptNumber = "2";
    final String deliveryCity = "Phoenix";
    final String zipCode = "30606";

    @Test
    public void addNewOrder() throws InterruptedException {
        loginPage.userValidLogIn("rose@emailate.com", "testtest");
        dashboardPage.clickOnMenuDash();
        dashboardPage.clickOnMenuPageELD();
        dashboardPage.clickOnOrderELD();
        modalEldPage.checkCurrentUrl();
        modalEldPage.enterQuantityDevices(quantityOfDevices);
        modalEldPage.enterFirstName(firstName);
        modalEldPage.enterLastName(lastName);
        modalEldPage.enterPhone(phone);
        modalEldPage.enterPrimaryAddressLine(addressLine);
        modalEldPage.enterAptNumber(aptNumber);
        modalEldPage.enterDeliveryCity(deliveryCity);
        modalEldPage.selectState("1");
        modalEldPage.enterZipCode(zipCode);
        modalEldPage.clickPaymentMethods();
        modalEldPage.clickAgreement();
        modalEldPage.clickButtonFastMove();
        modalEldPage.clickButtonAgree();
        //modalEldPage.clickButtonOrder();

        //checkAC("New order wasn`t added", ModalEldPage.isNewOrderAdded(idOfOrder), true);


    }
}
