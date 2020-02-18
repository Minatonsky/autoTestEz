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

    @FindBy(xpath = ".//*[@id='validatePhone']//../*[@aria-label=\"Close\"]")
    private WebElement phoneVerificationClose;

    public DashboardPage(WebDriver webDriver) {

        super(webDriver, "/dash");
    }

    public boolean isDashboardPresent(){
        return actionsWithOurElements.isElementEnable(dashboard);
    }
    public void clickOnMenuDash() {
        actionsWithOurElements.clickOnElement(menuDash);
    }

    public void goToStatusPage(){
        waitABit(2);
        actionsWithOurElements.clickOnElement(pageStatus);
    }
    public void goToFleetPage(){
        closePhoneVerificationPopUp();
        waitABit(2);
        actionsWithOurElements.clickOnElement(pageFleet);
    }
    public void goToReportsPage(){
        closePhoneVerificationPopUp();
        waitABit(2);
        actionsWithOurElements.clickOnElement(pageReports);
    }
    public void goToLogsPage(){
        closePhoneVerificationPopUp();
        waitABit(2);
        actionsWithOurElements.clickOnElement(pageLogs);
    }
    public void goToDVIRPage(){
        closePhoneVerificationPopUp();
        waitABit(2);
        actionsWithOurElements.clickOnElement(pageDvir);
    }
    public void goToDocumentsPage(){
        closePhoneVerificationPopUp();
        waitABit(2);
        actionsWithOurElements.clickOnElement(pageDocuments);
    }
    public void goToEquipmentPage(){
        closePhoneVerificationPopUp();
        waitABit(2);
        actionsWithOurElements.clickOnElement(pageEquipment);}
    public void goToEzChatPage(){
        closePhoneVerificationPopUp();
        waitABit(2);
        actionsWithOurElements.clickOnElement(pageEzChat);}
    public void goToEldPage(){
        closePhoneVerificationPopUp();
        waitABit(2);
        actionsWithOurElements.clickOnElement(pageELD);
    }
    public void goToFinancesPage(){
        closePhoneVerificationPopUp();
        waitABit(2);
        actionsWithOurElements.clickOnElement(pageFinances);
    }
    public void goToCalendarPage(){
        closePhoneVerificationPopUp();
        waitABit(2);
        actionsWithOurElements.clickOnElement(pageCalendar);}
    public void goToHelpAndTrainingPage(){
        closePhoneVerificationPopUp();
        waitABit(2);
        actionsWithOurElements.clickOnElement(pageHelp);}
    public void goToSettingPage(){
        closePhoneVerificationPopUp();
        waitABit(2);
        actionsWithOurElements.clickOnElement(pageSettings);
    }

    public void clickMenuSizeButton(){
        actionsWithOurElements.clickOnElement(menuSizeButton);
    }

    public boolean ifPhoneVerificationPopUpExist(){return actionsWithOurElements.isElementDisplay(phoneVerificationClose);}
    public void clickOnCloseButton(){actionsWithOurElements.clickOnElement(phoneVerificationClose);}

//    @Step
//    public void openMenuDash(){
//
//        waitABit(3);
//        closePhoneVerificationPopUp();
//        waitABit(3);
//        clickOnMenuDash();
//        waitABit(3);
//        clickMenuSizeButton();
//    }

    @Step
    public void closePhoneVerificationPopUp(){
        if (ifPhoneVerificationPopUpExist() == true){
            clickOnCloseButton();
        } else {
            logger.info("There is not phone verification pop up");
        }

    }
}
