package pages;

import libs.UtilsForDB;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static libs.Utils.waitABit;

public class ManagerEldPage extends ParentPage {


    @FindBy(xpath = ".//a[@href='/dash/eld_orders/']")
    private WebElement eldOrders;

    @FindBy(xpath = ".//*[@placeholder='Id']")
    private WebElement idHolder;

    @FindBy(xpath = ".//button[text()='New Order']")
    private WebElement newOrderButton;

    public ManagerEldPage(WebDriver webDriver, UtilsForDB utilsForDB) {

        super(webDriver, "dash/eld/", utilsForDB);
    }

    public void clickOnEldOrders(){actionsWithOurElements.clickOnElement(eldOrders);}

    public void enterIdOrder(String idOrder){actionsWithOurElements.enterTextToElement(idHolder, idOrder);}


    public void clickOnOrderOnList(String idOrder) {
        actionsWithOurElements.clickOnElement(".//*[@id='eld_orders_table']//td[text()='" + idOrder + "']");
    }

    public void clickOnNewOrderButton(){
        actionsWithOurElements.clickOnElement(newOrderButton);
    }

    public void openOrderInfo(String orderId){
        waitABit(2);
        clickOnEldOrders();
        waitABit(2);
        enterIdOrder(orderId);
        waitABit(2);
        clickOnOrderOnList(orderId);
        waitABit(2);
    }

}
