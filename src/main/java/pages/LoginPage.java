package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage {
    DashboardPage dashboardPage;

    @FindBy(xpath = ".//*[@class=\"log_in btn-login\"]")
    private WebElement loginButton;

    @FindBy(xpath = ".//*[@id=\"formLogin\"]/div[1]/input")
    private WebElement userLoginInput;

    @FindBy(xpath = ".//*[@id=\"loginPass\"]")
    private WebElement userPasswordInput;

    @FindBy(xpath = ".//*[@id=\"btnLogin\"]")
    private WebElement submitButton;



    public LoginPage(WebDriver webDriver) {
        super(webDriver, "/");
        dashboardPage = new DashboardPage(webDriver);
    }

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

        dashboardPage.isDashboardPresent();
        //dashboardPage.checkCurrentUrl();

    }
}