package pagesFrankestein;

import libs.UtilsForDB;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static libs.Utils.waitABit;

public class HelpAndTrainingFPage extends ParentFPage {
    public HelpAndTrainingFPage(WebDriver webDriver, UtilsForDB utilsForDB) {
        super(webDriver, "/dash/video_tutorial/", utilsForDB);
    }
    @FindBy(xpath = "//a[@href='/dash/user_reviews_offers/']")
    private WebElement userReviewsOffersPage;

    @FindBy(xpath = "//a[@href='/dash/client_support_tickets/']")
    private WebElement supportTicketsPage;

    @FindBy(xpath = "//a[@href='/dash/faq/']")
    private WebElement faqPage;

    public void goToUserReviewsOffersPage(){waitABit(2);actionsWithOurElements.clickOnElement(userReviewsOffersPage);}
    public void goToSupportTicketsPage(){waitABit(2);actionsWithOurElements.clickOnElement(supportTicketsPage);}
    public void goToFaqPage(){waitABit(2);actionsWithOurElements.clickOnElement(faqPage);}
}
