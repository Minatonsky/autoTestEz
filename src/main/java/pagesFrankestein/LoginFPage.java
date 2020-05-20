package pagesFrankestein;

import io.qameta.allure.Step;
import libs.UtilsForDB;
import org.junit.Assert;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Date;

import static libs.Utils.waitABit;

public class LoginFPage extends ParentFPage {
    public LoginFPage(WebDriver webDriver, UtilsForDB utilsForDB) {
        super(webDriver, "/", utilsForDB);
    }


    @FindBy(xpath = ".//*[@class='log_in btn-login']")
    private WebElement loginButton;

    @FindBy(xpath = ".//*[@id='formLogin']//.//input[@name='email']")
    private WebElement userLoginInput;

    @FindBy(xpath = ".//*[@id='formLogin']//.//input[@name='pass']")
    private WebElement userPasswordInput;

    @FindBy(id = "btnLogin")
    private WebElement submitButton;

    @FindBy(xpath = ".//*[@id='validatePhone']//../*[@aria-label='Close']")
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
                .domain("staging.ezlogz.com")
                .expiresOn(new Date(2021, 10, 28))
                .isHttpOnly(false)
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

    public void logInWithOutOpenMenu(String login, String passWord) {
        openPage();
        openLoginForm();
        enterLogin(login);
        enterPass(passWord);
        clickOnSubmitButton();
        waitABit(6);
//        closePhoneVerificationPopUp();
    }
    public void logInAndOpenMenu(String login, String passWord) {
        openPage();
        openLoginForm();
        enterLogin(login);
        enterPass(passWord);
        clickOnSubmitButton();
        openDashBoardMenuByCookies();
        waitABit(3);
        closePhoneVerificationPopUp();
    }
}