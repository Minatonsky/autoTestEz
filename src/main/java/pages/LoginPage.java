package pages;

import io.qameta.allure.Step;
import libs.UtilsForDB;
import org.junit.Assert;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Date;

import static libs.Utils.waitABit;

public class LoginPage extends ParentPage {
    public LoginPage(WebDriver webDriver, UtilsForDB utilsForDB) {
        super(webDriver, "/", utilsForDB);
    }


    @FindBy(xpath = ".//*[@class='log_in btn-login']")
    private WebElement loginButton;

    @FindBy(xpath = ".//*[@id='formLogin']/div[1]/input")
    private WebElement userLoginInput;

    @FindBy(xpath = ".//*[@id='loginPass']")
    private WebElement userPasswordInput;

    @FindBy(id = "btnLogin")
    private WebElement submitButton;

    @FindBy(xpath = ".//*[@id='validatePhone']//../*[@aria-label=\"Close\"]")
    private WebElement phoneVerificationClose;




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

    @Step
    public void enterPass(String pass) {
        actionsWithOurElements.enterTextToElement(userPasswordInput, pass);
    }

    @Step
    public void clickOnSubmitButton() { actionsWithOurElements.clickOnElement(submitButton); }

    @Step
    public void openDashBoardMenuByCookies(){
        Cookie cookie = new Cookie.Builder("minimize-menu", "1")
                .domain(baseUrl.substring(8))
                .expiresOn(new Date(2021, 10, 28))
                .isHttpOnly(true)
                .isSecure(false)
                .path("/")
                .build();
        webDriver.manage().addCookie(cookie);
    }

    @Step
    public void closePhoneVerificationPopUp(){
        if (actionsWithOurElements.isElementDisplay(phoneVerificationClose)){
            actionsWithOurElements.clickOnElement(phoneVerificationClose);
            logger.info("Phone verification pop up Closed");
        } else {
            logger.info("There is not phone verification pop up");
        }

    }

    public void userValidLogIn(String login, String passWord) {
        openPage();
        openLoginForm();
        enterLogin(login);
        enterPass(passWord);
        waitABit(2);
        clickOnSubmitButton();
        openDashBoardMenuByCookies();
        waitABit(4);
        closePhoneVerificationPopUp();
    }

    public void userPhoneVerifiedValidLogIn(String login, String passWord) {
        openPage();
        openLoginForm();
        enterLogin(login);
        enterPass(passWord);
        waitABit(2);
        clickOnSubmitButton();
        openDashBoardMenuByCookies();
        waitABit(4);
    }
}