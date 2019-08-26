package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FinancesPage extends ParentPage {

    public FinancesPage(WebDriver webDriver) {
        super(webDriver, "/dash/finances/");
    }

    @FindBy(xpath = ".//*[@id='current_due']")
    private WebElement financeBalanceText;

    public String getFinanceBalance(){
        return financeBalanceText.getText();
    }

    public boolean compareBalance(String currentDue, String dueForLastOrder) {
        double tempBalance = Double.parseDouble(currentDue) - Double.parseDouble(dueForLastOrder);
        if (Integer.parseInt(currentDue) > 0) {
            return Double.parseDouble(getFinanceBalance().substring(1)) == tempBalance;
        } else if (Integer.parseInt(currentDue) == 0) {
            return Double.parseDouble(getFinanceBalance().substring(1)) == 0;
        } else return false;
    }
    public boolean compareBalanceIfCanceled(String currentDue, String dueForLastOrder, String quantityOfDevices) {
        double tempBalance1 = Integer.parseInt(currentDue);
        if (Integer.parseInt(quantityOfDevices) > 0) {
            if (Integer.parseInt(currentDue) > 0) {
                return Double.parseDouble(getFinanceBalance().substring(1)) == tempBalance1;
            } else if (Integer.parseInt(currentDue) == 0) {
                return Double.parseDouble(getFinanceBalance().substring(1)) == Double.parseDouble(dueForLastOrder.substring(1));
            } else return false;
        } else return true;

    }

    public boolean compareBalanceIfCanceledNewOrder(String currentDue) {
        return Double.parseDouble(getFinanceBalance().substring(1)) == Double.parseDouble(currentDue);
        }

    public boolean comparePaidOrderStatus(String orderStatus) {
        return orderStatus.equals("3");
    }

    public boolean compareCancelOrderStatus(String orderStatus) {
        return orderStatus.equals("2");
    }

    public boolean compareCompletedOrderStatus(String orderStatus) {
        return orderStatus.equals("1");
    }

    public boolean compareNewOrderStatus(String orderStatus) {
        return orderStatus.equals("0");
    }

}
