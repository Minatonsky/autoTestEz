package pagesLocal;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DocumentsLocalSitePage extends ParentLocalSitePage {

    public DocumentsLocalSitePage(WebDriver webDriver) {
        super(webDriver, "/dash/settings/account/");
    }

    @FindBy(xpath = ".//button[text() = 'Create document']")
    private WebElement addDocumentButton;

    public void clickOnAddTruckButton(){actionsWithOurElements.clickOnElement(addDocumentButton);}

}
