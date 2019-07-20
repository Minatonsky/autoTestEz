package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EldManagerPage extends ParentPage {


    @FindBy(xpath = ".//a[@href='/dash/eld_orders/']")
    private WebElement eldOrders;

    @FindBy(xpath = ".//*[@placeholder='Id']")
    private WebElement idHolder;

    public EldManagerPage(WebDriver webDriver) {

        super(webDriver, "dash/eld/");
    }

    public void clickOnEldOrders(){actionsWithOurElements.clickOnElement(eldOrders);}

    public void enterIdOrder(String idOrder){actionsWithOurElements.enterTextToElement(idHolder, idOrder);}


    public void clickOnOrderOnList(String idOrder) {
        actionsWithOurElements.clickOnElement(".//*[@id='eld_orders_table']//td[text()='" + idOrder + "']");
    }

}
