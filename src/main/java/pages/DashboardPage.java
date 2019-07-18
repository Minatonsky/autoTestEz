package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage extends ParentPage {
    @FindBy(xpath = "//*[@id='dash_head']")
    private WebElement dashboard;

    @FindBy(xpath = ".//*[@class='stub']")
    private WebElement menuDash;

    @FindBy(xpath = ".//a[@href='/dash/eld/']")
    private WebElement menuPageELD;

    @FindBy(xpath = ".//button [text()='Order ELD']")
    private WebElement orderELD;

    @FindBy(xpath = ".//a[@href='/dash/finances/']")
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

    @Step
    public void goToEldPageAndClickOrderEld() throws InterruptedException {
        clickOnMenuDash();
        Thread.sleep(1000);
        clickOnMenuPageELD();
        Thread.sleep(1000);
        clickOnOrderELD();
    }

}
