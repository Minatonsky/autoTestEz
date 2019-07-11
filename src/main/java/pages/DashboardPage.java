package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage extends ParentPage {
    @FindBy(xpath = "//*[@id='dash_head']")
    private WebElement dashboard;

    @FindBy(xpath = ".//*[@class='stub']")
    private WebElement menuDash;

    @FindBy(xpath = ".//div[@class='dash_nav']//a[@href='/dash/eld/']")
    private WebElement menuPageELD;

    @FindBy(xpath = ".//button[@class='btn btn-primary']")
    private WebElement orderELD;

    @FindBy(xpath = ".//div[@class='dash_nav']//a[@href='/dash/finances/']")
    private WebElement menuPageFinances;

    public DashboardPage(WebDriver webDriver) {
        super(webDriver, "/dash");
    }

    public boolean isDashboardPresent(){
        return actionsWithOurElements.isElementEnable(dashboard);
    }

    public void clickOnMenuDash() {
        actionsWithOurElements.clickOnElement(menuDash);
    }
    public void clickOnMenuPageELD(){
        actionsWithOurElements.clickOnElement(menuPageELD);
    }
    public void clickOnOrderELD(){
        actionsWithOurElements.clickOnElement(orderELD);
    }
    public void clickOnMenuPageFinances(){
        actionsWithOurElements.clickOnElement(menuPageFinances);
    }

}
