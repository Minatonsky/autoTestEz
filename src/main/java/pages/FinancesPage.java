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

    public boolean compareBalance(String defaultBalance) {
        return getFinanceBalance().equals("$" + defaultBalance);
    }

}
