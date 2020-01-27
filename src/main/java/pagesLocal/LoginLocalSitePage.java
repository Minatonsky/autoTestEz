package pagesLocal;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginLocalSitePage extends ParentLocalSitePage {

    @FindBy(xpath = ".//button[text()='Login ']")
    private WebElement loginButton;

    @FindBy(name = "email")
    private WebElement userLoginInput;

    @FindBy(name = "password")
    private WebElement userPasswordInput;

    @FindBy(xpath = ".//button[@class='btn btn-transparent' and @type='submit']")
    private WebElement submitButton;

    public LoginLocalSitePage(WebDriver webDriver) {
        super(webDriver, "/");
    }

    @Step
    public void openPage() {
        try {
            webDriver.get(baseUrl + "/");
            checkCurrentUrl();
            logger.info("Login Page was opened");
        } catch (Exception e) {
            logger.error("Can not open LoginPage");
            Assert.fail("Can not open LoginPage");
        }
    }
    @Step
    public void openLoginForm() {
        actionsWithOurElements.clickOnElement(loginButton);
    }

    @Step
    public void enterLogin(String login) {
       actionsWithOurElements.enterTextToElement(userLoginInput, login);
    }

    public void clickOnBlankArea(){actionsWithOurElements.clickOnBlankArea();}

    @Step
    public void enterPass(String pass) {
        actionsWithOurElements.enterTextToElement(userPasswordInput, pass);
    }

    @Step
    public void clickOnSubmitButton() { actionsWithOurElements.clickOnElement(submitButton); }

    /**
     * Method valid Login
     * @param login (ONLY Valid Login)
     * @param passWord (ONLY Valid Pass)
     */

    public void userValidLogIn(String login, String passWord) {
        openPage();
        openLoginForm();
        enterLogin(login);
        enterPass(passWord);
        clickOnSubmitButton();

    }
}