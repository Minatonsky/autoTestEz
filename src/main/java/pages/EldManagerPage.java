package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EldManagerPage extends ParentPage {


    @FindBy(xpath = ".//a[@href='/dash/eld_orders/']")
    private WebElement eldOrders;

    @FindBy(xpath = ".//*[@placeholder='Id']")
    private WebElement idHolder;

    @FindBy(xpath = ".//*[@id='eld_orders_table']//td[text()='2422']")
    private WebElement orderOnList;

    public EldManagerPage(WebDriver webDriver) {
        super(webDriver, "dash/eld/");
    }

    public void clickOnEldOrders(){actionsWithOurElements.clickOnElement(eldOrders);}

    public void enterIdOrder(String idOrder){actionsWithOurElements.enterTextToElement(idHolder, idOrder);}

    public void clickOnOrderOnList(){actionsWithOurElements.clickOnElement(orderOnList);}


}
