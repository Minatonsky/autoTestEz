package pages;

import libs.UtilsForDB;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static libs.Utils.waitABit;

public class DashboardPage extends ParentPage {

    public DashboardPage(WebDriver webDriver, UtilsForDB utilsForDB) {
        super(webDriver, "/dash", utilsForDB);
    }

    @FindBy(xpath = "//*[@id='dash_head']")
    private WebElement dashboard;

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

    @FindBy(xpath = ".//a[@href='/dash/returns/']")
    private WebElement pageReturns;

    @FindBy(xpath = ".//i[@class='fa fa-angle-double-left minimize-arrow']")
    private WebElement menuSizeButton;

    @FindBy(xpath = ".//*[@class='dashboard-content__main-navigation mobile-hiden']//../a[@href='/dash/fleet/equipment/']")
    private WebElement pageSafety;

    @FindBy(xpath = ".//*[@class='icon-icons-navigation-ic-arrow']")
    private WebElement menuDash;



    public boolean isDashboardPresent(){
        return actionsWithOurElements.isElementEnable(dashboard);
    }

    public void goToStatusPage(){
        waitABit(3);
        actionsWithOurElements.clickOnElement(pageStatus);
    }
    public void goToFleetPage(){
        waitABit(3);
        actionsWithOurElements.clickOnElement(pageFleet);
    }
    public void goToReportsPage(){
        waitABit(3);
        actionsWithOurElements.clickOnElement(pageReports);
    }
    public void goToLogsPage(){
        waitABit(3);
        actionsWithOurElements.clickOnElement(pageLogs);
    }
    public void goToDVIRPage(){
        waitABit(3);
        actionsWithOurElements.clickOnElement(pageDvir);
    }
    public void goToDocumentsPage(){
        waitABit(3);
        actionsWithOurElements.clickOnElement(pageDocuments);
    }
    public void goToEquipmentPage(){
        waitABit(3);
        actionsWithOurElements.clickOnElement(pageEquipment);}
    public void goToEzChatPage(){
        waitABit(3);
        actionsWithOurElements.clickOnElement(pageEzChat);}
    public void goToEldPage(){
        waitABit(3);
        actionsWithOurElements.clickOnElement(pageELD);
    }
    public void goToFinancesPage(){
        waitABit(3);
        actionsWithOurElements.clickOnElement(pageFinances);
    }
    public void goToCalendarPage(){
        waitABit(3);
        actionsWithOurElements.clickOnElement(pageCalendar);}
    public void goToHelpAndTrainingPage(){
        waitABit(3);
        actionsWithOurElements.clickOnElement(pageHelp);}
    public void goToSettingPage(){
        waitABit(3);
        actionsWithOurElements.clickOnElement(pageSettings);
    }
    public void goToReturnsPage() {
        waitABit(3);
        actionsWithOurElements.clickOnElement(pageReturns);
    }
    public void goToSafetyPage(){
        waitABit(3);
        actionsWithOurElements.clickOnElement(pageSafety);
    }
    public void openDashHeadMenu(){
        actionsWithOurElements.clickOnElement(menuDash);
    }

}
