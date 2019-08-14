package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrderInfoPage extends ParentPage {

    @FindBy(xpath = ".//select[@id='order_status']")
    private WebElement orderStatus;

    @FindBy(xpath = ".//button[@class='btn btn-default btn-sm save_order_edit']")
    private WebElement buttonSave;

    public OrderInfoPage(WebDriver webDriver) {
        super(webDriver, "dash/eld_orders/");
    }

    public void selectOrderStatus(String value) {actionsWithOurElements.selectValueInDropDown(orderStatus, value);}

    public void clickButtonSave(){actionsWithOurElements.clickOnElement(buttonSave);}


    public void completedOrder() {
        selectOrderStatus("4");
        clickButtonSave();
        selectOrderStatus("1");
        clickButtonSave();
    }
}
