package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EldPage extends ParentPage {
    public EldPage(WebDriver webDriver) {
        super(webDriver, "/dash/eld/");
    }
    @FindBy(xpath = "//a[@href='/dash/paper_logs/']")
    private WebElement paperLogPermissionsPage;

    public void goToPaperLogPermissions(){actionsWithOurElements.clickOnElement(paperLogPermissionsPage);}
}
