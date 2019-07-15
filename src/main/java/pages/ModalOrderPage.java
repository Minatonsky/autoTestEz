package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Iterator;
import java.util.Set;

public class ModalOrderPage extends ParentPage {

    @FindBy(xpath = ".//select[@id='order_status']")
    private WebElement orderStatus;

    @FindBy(xpath = ".//button[@class='btn btn-default btn-sm save_order_edit']")
    private WebElement buttonSave;

    public ModalOrderPage(WebDriver webDriver) {
        super(webDriver, "dash/eld_orders/");
    }

    public void selectOrderStatus(String value) {actionsWithOurElements.selectValueInDropDown(orderStatus, value);}

    public void clickButtonSave(){actionsWithOurElements.clickOnElement(buttonSave);}

    public void selectBrowserWindow(String window){
        Set<String> windowIds = webDriver.getWindowHandles();
        Iterator<String> iter = windowIds.iterator();
        String mainWindow = iter.next();
        String childWindow = iter.next();
    }

}
