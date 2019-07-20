package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static libs.Utils.waitABit;

public class DashboardPage extends ParentPage {
    @FindBy(xpath = "//*[@id='dash_head']")
    private WebElement dashboard;

    @FindBy(xpath = ".//div[@class='dash_nav']")
    private WebElement menuDash;

    @FindBy(xpath = ".//a[@href='/dash/eld/']")
    private WebElement menuPageELD;

    @FindBy(xpath = ".//a[@href='/dash/finances/']")
    private WebElement menuPageFinances;

    @FindBy(xpath = ".//i[@class='fa fa-angle-double-left minimize-arrow']")
    private WebElement menuSizeButton;

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

    public void clickOnMenuPageFinances(){
        actionsWithOurElements.clickOnElement(menuPageFinances);
    }

    public void clickMenuSizeButton(){
        actionsWithOurElements.clickOnElement(menuSizeButton);
    }


    @Step
    public void goToEldPage(){
        waitABit(3);
        clickOnMenuPageELD();
        waitABit(3);
    }

    @Step
    public void goToFinancesPage(){
        waitABit(3);
        clickOnMenuPageFinances();
        waitABit(3);
    }
}
