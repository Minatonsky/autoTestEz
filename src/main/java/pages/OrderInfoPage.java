package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrderInfoPage extends ParentPage {

    @FindBy(xpath = ".//select[@id='order_status']")
    private WebElement orderStatus;

    @FindBy(xpath = ".//button[@class='btn btn-default btn-sm save_order_edit']")
    private WebElement buttonSave;

    @FindBy(xpath = "//*[text()='Full order Price']//..//span[@class='form-group-content']")
    private WebElement fullOrderPrice;

    public OrderInfoPage(WebDriver webDriver) {
        super(webDriver, "dash/eld_orders/");
    }

    public void selectOrderStatus(String value) {actionsWithOurElements.selectValueInDropDown(orderStatus, value);}

    public void clickButtonSave(){actionsWithOurElements.clickOnElement(buttonSave);}

    public String getFullOrderPrice(){
        return fullOrderPrice.getText().substring(1);
    }


    public void completedOrder() {
        selectOrderStatus("4");
        clickButtonSave();
        selectOrderStatus("1");
        clickButtonSave();
    }

    public boolean compareFullOrderPrice(String dueForLastOrder){
        if (Double.parseDouble(getFullOrderPrice()) == Double.parseDouble(dueForLastOrder))
            return true;
        else return false;

    }

}
