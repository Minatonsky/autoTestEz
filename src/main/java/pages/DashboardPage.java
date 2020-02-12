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

    @FindBy(xpath = ".//a[@href='/dash/']")
    private WebElement menuPageStatus;

    @FindBy(xpath = ".//a[@href='/dash/reports/ifta/']")
    private WebElement menuPageReports;

    @FindBy(xpath = ".//a[@href='/dash/trucks/']")
    private WebElement menuPageDvir;

    @FindBy(xpath = ".//a[@href='/dash/live_scan/']")
    private WebElement menuPageDocuments;

    @FindBy(xpath = ".//a[@href='/dash/equipment/']")
    private WebElement menuPageEquipment;

    @FindBy(xpath = ".//a[@href='/dash/finances/']")
    private WebElement menuPageFinances;

    @FindBy(xpath = ".//a[@href='/dash/ezchat/']")
    private WebElement menuPageEzChat;

    @FindBy(xpath = ".//a[@href='/dash/calendar/']")
    private WebElement menuPageCalendar;

    @FindBy(xpath = ".//a[@href='/dash/video_tutorial/']")
    private WebElement menuPageHelp;

    @FindBy(xpath = ".//a[@href='/dash/settings/account/']")
    private WebElement menuPageSettings;

    @FindBy(xpath = ".//a[@href='/dash/drivers/']")
    private WebElement menuPageLogs;

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

    public void clickOnMenuPageStatus(){
        actionsWithOurElements.clickOnElement(menuPageStatus);
    }
    public void clickOnMenuPageReports(){
        actionsWithOurElements.clickOnElement(menuPageReports);
    }
    public void clickOnMenuPageLogs(){
        actionsWithOurElements.clickOnElement(menuPageLogs);
    }
    public void clickOnMenuPageDVIR(){
        actionsWithOurElements.clickOnElement(menuPageDvir);
    }
    public void clickOnMenuPageDocuments(){
        actionsWithOurElements.clickOnElement(menuPageDocuments);
    }
    public void clickOnMenuPageEquipment(){actionsWithOurElements.clickOnElement(menuPageEquipment);}
    public void clickOnMenuPageEzChat(){actionsWithOurElements.clickOnElement(menuPageEzChat);}
    public void clickOnMenuPageELD(){
        actionsWithOurElements.clickOnElement(menuPageELD);
    }
    public void clickOnMenuPageFinances(){
        actionsWithOurElements.clickOnElement(menuPageFinances);
    }
    public void clickOnMenuPageCalendar(){actionsWithOurElements.clickOnElement(menuPageCalendar);}
    public void clickOnMenuPageHelpAndTraining(){actionsWithOurElements.clickOnElement(menuPageHelp);}
    public void clickOnMenuPageSettings(){
        actionsWithOurElements.clickOnElement(menuPageSettings);
    }

    public void clickMenuSizeButton(){
        actionsWithOurElements.clickOnElement(menuSizeButton);
    }

    public boolean ifPhoneVerificationPopUpExist(){return actionsWithOurElements.isElementDisplay(phoneVerificationClose);}
    public void clickOnCloseButton(){actionsWithOurElements.clickOnElement(phoneVerificationClose);}

    @Step
    public void openMenuDash(){

        waitABit(3);
        closePhoneVerificationPopUp();
        waitABit(3);
        clickOnMenuDash();
        waitABit(3);
        clickMenuSizeButton();
    }
    @Step
    public void goToStatusPage(){
        waitABit(2);
        clickOnMenuPageStatus();
    }
    @Step
    public void goToReportsPage(){
        waitABit(2);
        clickOnMenuPageReports();
    }
    @Step
    public void goToDVIRPage(){
        waitABit(2);
        clickOnMenuPageDVIR();
    }
    @Step
    public void goToDocumentsPage(){
        waitABit(2);
        clickOnMenuPageDocuments();
    }
    @Step
    public void goToEzChatPage(){
        waitABit(2);
        clickOnMenuPageEzChat();
    }
    @Step
    public void goToCalendarPage(){
        waitABit(2);
        clickOnMenuPageCalendar();
    }
    @Step
    public void goToHelpAndTrainingPage(){
        waitABit(2);
        clickOnMenuPageHelpAndTraining();
    }
    @Step
    public void goToEldPage(){
        waitABit(2);
        clickOnMenuPageELD();
    }
    @Step
    public void goToFinancesPage(){
        waitABit(2);
        clickOnMenuPageFinances();
    }
    @Step
    public void goToSettingPage(){
        waitABit(2);
        clickOnMenuPageSettings();
    }
    @Step
    public void goToEquipmentPage(){
        waitABit(2);
        clickOnMenuPageEquipment();
    }
    @Step
    public void goToLogsPage(){
        waitABit(2);
        clickOnMenuPageLogs();
    }
    @Step
    public void closePhoneVerificationPopUp(){
        if (ifPhoneVerificationPopUpExist() == true){
            clickOnCloseButton();
        } else {
            logger.info("There is not phone verification pop up");
        }

    }
}
