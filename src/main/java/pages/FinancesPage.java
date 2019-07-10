package pages;

import org.openqa.selenium.WebDriver;

public class FinancesPage extends ParentPage {

    public FinancesPage(WebDriver webDriver) {
        super(webDriver, "/dash/finances/");
    }


    public boolean compareBalance(String defaultBalance) {
        return actionsWithOurElements.isElementInOrder(".//*[@id=\"current_due\" and text()=\"'$" + defaultBalance + "'\"]");
    }
}
