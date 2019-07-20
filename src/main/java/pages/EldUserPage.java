package pages;

import libs.UtilsForDB;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class EldUserPage extends ParentPage {

    @FindBy(xpath = ".//button [text()='Order ELD']")
    private WebElement orderELD;

    @FindBy(xpath = ".//*[@placeholder='Id']")
    private WebElement idHolder;


    public EldUserPage(WebDriver webDriver) {
        super(webDriver, "dash/eld/");
    }

    public void clickOnOrderELD(){

        actionsWithOurElements.clickOnElement(orderELD);
    }


    public void cancelEldDevices(String idOrder) throws SQLException, IOException, ClassNotFoundException {
        UtilsForDB utilsForDB = new UtilsForDB();

        for (ArrayList<String> line : utilsForDB.getLocalIdDevices(idOrder)) {
            for (String idLocalDevice : line
            ) {
                enterIdOrder(idOrder);
                clickOnOrderOnList(idLocalDevice);
                clickOnButtonCancelOrderDevice();
                clickOnOrderEldConfirm();

            }

        }
    }

    private void clickOnOrderEldConfirm() {
        actionsWithOurElements.clickOnElement("//*[@class='btn btn-primary changeStatus' and text()='Confirm']");
    }

    private void clickOnButtonCancelOrderDevice() {
        actionsWithOurElements.clickOnElement("//*[@class='btn btn-default' and text()='Cancel Order Device']");
    }

    private void clickOnOrderOnList(String idLocalDevice) {
        actionsWithOurElements.clickOnElement(".//*[@id='eld_table']//td[text()='" + idLocalDevice + "']");
    }

    public void enterIdOrder(String idLocalDevice){
        actionsWithOurElements.enterTextToElement(idHolder, idLocalDevice);
    }
}
