package pages;

import libs.UtilsForDB;
import org.junit.Assert;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

import static libs.Utils.listArrayToMap;
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

    public void openLoginForm() {
        actionsWithOurElements.clickOnElement(loginButton);
    }

    public void enterLogin(String login) {
       actionsWithOurElements.enterTextToElement(userLoginInput, login);
    }

    public void enterPass(String pass) {
        actionsWithOurElements.enterTextToElement(userPasswordInput, pass);
    }

    public void clickOnSubmitButton() { actionsWithOurElements.clickOnElement(submitButton); }

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

    public void closePhoneVerificationPopUp(){
        if (actionsWithOurElements.isElementDisplay(phoneVerificationClose)){
            actionsWithOurElements.clickOnElement(phoneVerificationClose);
            logger.info("Phone verification pop up Closed");
        } else {
            logger.info("There is not phone verification pop up");
        }

    }

    public void userValidLogIn(String login, String passWord) throws SQLException {
        verificationPhone(login);
        openPage();
        openLoginForm();
        enterLogin(login);
        enterPass(passWord);
        waitABit(3);
        clickOnSubmitButton();
        openDashBoardMenuByCookies();
        waitABit(3);

    }

    public void userPhoneVerifiedValidLogIn(String login, String passWord) {
        openPage();
        openLoginForm();
        enterLogin(login);
        enterPass(passWord);
        waitABit(3);
        clickOnSubmitButton();
        waitABit(1);
        openDashBoardMenuByCookies();
        waitABit(5);
    }

    public void verificationPhone(String login) throws SQLException {
        String userId = utilsForDB.getUserIdByEmail(login);
        String userPhone = utilsForDB.getUserPhoneById(userId);
        String date = "2021-06-03 12:23:10";
        String phone = "067 557 5011";
        Map<String, Object> tempStatusData = listArrayToMap(utilsForDB.getUserPhoneVerificationData(userId));
        if (!userPhone.isEmpty()){
            if (!userPhone.equals(tempStatusData.get("phone").toString())){
                utilsForDB.setVerificationPhone(userId, userPhone);
                utilsForDB.setVerificationExpireDatePhone(userId, date);

            } else {
                logger.info("Phone verified already");

            }
        } else {
            utilsForDB.setUserPhone(userId, phone);
            if (tempStatusData.get("phone").toString().isEmpty()){
                utilsForDB.insertVerificationPhone(Integer.parseInt(userId), phone, date);
                utilsForDB.insertVerificationExpireDatePhone(Integer.parseInt(userId), date);
            } else {
                utilsForDB.setVerificationPhone(userId, userPhone);
                utilsForDB.setVerificationExpireDatePhone(userId, date);
            }

        }
    }
}