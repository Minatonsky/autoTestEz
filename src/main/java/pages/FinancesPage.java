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

    @FindBy(xpath = ".//*[@id='paymentHistoryTable']/tbody/tr[1]/td[7]/span")
    private WebElement orderPriceText;

    public String getFinanceBalance(){
        return financeBalanceText.getText();
    }

    public String getOrderPrice(){
        return orderPriceText.getText();
    }


    public boolean compareBalance(String currentDue, String dueForLastOrder) {
        double tempBalance = Double.parseDouble(currentDue) - Double.parseDouble(dueForLastOrder);
        if (Integer.parseInt(currentDue) > 0)
            return Double.parseDouble(getFinanceBalance().substring(1)) == tempBalance;
        else if (Integer.parseInt(currentDue) == 0)
            return Double.parseDouble(getFinanceBalance().substring(1)) == 0;
        else return false;
    }
    public boolean compareBalanceIfCanceled(String currentDue, String dueForLastOrder, String quantityOfDevices) {
        double tempBalance1 = Integer.parseInt(currentDue);
        if (Integer.parseInt(quantityOfDevices) > 0)
            if (Integer.parseInt(currentDue) > 0)
                return Double.parseDouble(getFinanceBalance().substring(1)) == tempBalance1;
            else if (Integer.parseInt(currentDue) == 0)
                return Double.parseDouble(getFinanceBalance().substring(1)) == Double.parseDouble(dueForLastOrder.substring(1));
            else return false;
        else return true;

    }

}
