package pagesLocal;

import io.qameta.allure.Step;
import libs.UtilsForDB;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static libs.Utils.waitABit;

public class DashboardLocalSitePage extends ParentLocalSitePage {
    public DashboardLocalSitePage(WebDriver webDriver, UtilsForDB utilsForDB) {

        super(webDriver, "/dash", utilsForDB);
    }
    @FindBy(xpath = "//*[@id='dash_head']")
    private WebElement dashboard;

    @FindBy(xpath = ".//div[@class='dash_nav']")
    private WebElement menuDash;

    @FindBy(xpath = ".//a[@href='/dash/eld/']")
    private WebElement menuPageELD;

    @FindBy(xpath = ".//a[@href='/dash/finances/']")
    private WebElement menuPageFinances;

    @FindBy(xpath = ".//a[@href='/dash/equipment']")
    private WebElement menuPageEquipment;

    @FindBy(xpath = ".//a[@href='/dash/documents']")
    private WebElement menuPageDocument;

    @FindBy(xpath = ".//a[@href='/dash/settings']")
    private WebElement menuPageSettings;

    @FindBy(xpath = ".//i[@class='fa fa-angle-double-left minimize-arrow']")
    private WebElement menuSizeButton;

    @FindBy(xpath = ".//a[@href='/dash/logbook']")
    private WebElement pageLogs;

    @FindBy(xpath = ".//a[@href='/safety/documents']")
    private WebElement pageDocuments;

    @FindBy(xpath = ".//a[@href='/safety']")
    private WebElement safetyPage;




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
    public void clickOnMenuPageSettings(){
        actionsWithOurElements.clickOnElement(menuPageSettings);
    }
    public void clickMenuSizeButton(){
        actionsWithOurElements.clickOnElement(menuSizeButton);
    }
    public void clickOnMenuPageEquipment(){actionsWithOurElements.clickOnElement(menuPageEquipment);}
    public void clickOnMenuPageDocuments(){actionsWithOurElements.clickOnElement(menuPageDocument);}

    @Step
    public void openMenuDash(){
        waitABit(3);
        clickOnMenuDash();
        waitABit(3);
        clickMenuSizeButton();
        waitABit(3);
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
    @Step
    public void goToSettingPage(){
        waitABit(3);
        clickOnMenuPageSettings();
        waitABit(3);
    }
    @Step
    public void goToEquipmentPage(){
        waitABit(3);
        clickOnMenuPageEquipment();
        waitABit(3);
    }
    @Step
    public void goToDocumentPage(){
        waitABit(3);
        clickOnMenuPageDocuments();
        waitABit(3);
    }
    @Step
    public void goToLogsPage(){
        waitABit(2);
        actionsWithOurElements.clickOnElement(pageLogs);
    }
    @Step
    public void goToSafetyPage(){
        waitABit(2);
        actionsWithOurElements.clickOnElement(safetyPage);
    }
    @Step
    public void goToDocumentsPage(){
        waitABit(2);
        actionsWithOurElements.clickOnElement(pageDocuments);
    }

}
