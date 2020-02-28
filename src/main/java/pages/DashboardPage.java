package pages;

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
    private WebElement pageELD;

    @FindBy(xpath = ".//a[@href='/dash/']")
    private WebElement pageStatus;

    @FindBy(xpath = ".//a[@href='/dash/reports/ifta/']")
    private WebElement pageReports;

    @FindBy(xpath = ".//a[@href='/dash/trucks/']")
    private WebElement pageDvir;

    @FindBy(xpath = ".//a[@href='/dash/live_scan/']")
    private WebElement pageDocuments;

    @FindBy(xpath = ".//a[@href='/dash/equipment/']")
    private WebElement pageEquipment;

    @FindBy(xpath = ".//a[@href='/dash/finances/']")
    private WebElement pageFinances;

    @FindBy(xpath = ".//a[@href='/dash/ezchat/']")
    private WebElement pageEzChat;

    @FindBy(xpath = ".//a[@href='/dash/calendar/']")
    private WebElement pageCalendar;

    @FindBy(xpath = ".//a[@href='/dash/video_tutorial/']")
    private WebElement pageHelp;

    @FindBy(xpath = ".//a[@href='/dash/settings/account/']")
    private WebElement pageSettings;

    @FindBy(xpath = ".//a[@href='/dash/drivers/']")
    private WebElement pageLogs;

    @FindBy(xpath = ".//a[@href='/dash/fleet/equipment/']")
    private WebElement pageFleet;

    @FindBy(xpath = ".//i[@class='fa fa-angle-double-left minimize-arrow']")
    private WebElement menuSizeButton;

    public DashboardPage(WebDriver webDriver) {

        super(webDriver, "/dash");
    }

    public boolean isDashboardPresent(){
        return actionsWithOurElements.isElementEnable(dashboard);
    }

    public void goToStatusPage(){
        waitABit(2);
        actionsWithOurElements.clickOnElement(pageStatus);
    }
    public void goToFleetPage(){
        waitABit(2);
        actionsWithOurElements.clickOnElement(pageFleet);
    }
    public void goToReportsPage(){
        waitABit(2);
        actionsWithOurElements.clickOnElement(pageReports);
    }
    public void goToLogsPage(){
        waitABit(2);
        actionsWithOurElements.clickOnElement(pageLogs);
    }
    public void goToDVIRPage(){
        waitABit(2);
        actionsWithOurElements.clickOnElement(pageDvir);
    }
    public void goToDocumentsPage(){
        waitABit(2);
        actionsWithOurElements.clickOnElement(pageDocuments);
    }
    public void goToEquipmentPage(){
        waitABit(2);
        actionsWithOurElements.clickOnElement(pageEquipment);}
    public void goToEzChatPage(){
        waitABit(2);
        actionsWithOurElements.clickOnElement(pageEzChat);}
    public void goToEldPage(){
        waitABit(2);
        actionsWithOurElements.clickOnElement(pageELD);
    }
    public void goToFinancesPage(){
        waitABit(2);
        actionsWithOurElements.clickOnElement(pageFinances);
    }
    public void goToCalendarPage(){
        waitABit(2);
        actionsWithOurElements.clickOnElement(pageCalendar);}
    public void goToHelpAndTrainingPage(){
        waitABit(2);
        actionsWithOurElements.clickOnElement(pageHelp);}
    public void goToSettingPage(){
        waitABit(2);
        actionsWithOurElements.clickOnElement(pageSettings);
    }

}
