package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends ParentPage {
    public LoginPage(WebDriver webDriver) {
        super(webDriver, "/dash");
    }

    public void openPage() {
        try {
            webDriver.get(baseUrl);
            checkCurrentUrl();
            logger.info("Login Page was opened");
        } catch (Exception e) {
            logger.error("Can not open LoginPage");
            Assert.fail("Can not open LoginPage");
        }
    }

    public void openLoginForm() {
        try {
            WebElement webElement = webDriver.findElement(By.xpath(".//*[@class=\"log_in btn-login\"]"));
            webElement.click();
            logger.info("Button Login was clicked");

        } catch (Exception e) {
            logger.error("Can not work with element");
            Assert.fail("Can not work with element");
        }
    }

    public void enterLogin(String login) {
        try {
            WebElement webElement = webDriver.findElement(By.xpath(".//*[@id=\"formLogin\"]/div[1]/input"));
            webElement.clear();
            webElement.sendKeys(login);
            logger.info(login + " was input into input Login");

        } catch (Exception e) {
            logger.error("Can not work with element");
            Assert.fail("Can not work with element");
        }
    }

    public void enterPass(String pass) {
        try {
            WebElement webElement = webDriver.findElement(By.xpath(".//*[@id=\"loginPass\"]"));
            webElement.clear();
            webElement.sendKeys(pass);
            logger.info(pass + " was input into input Pass");

        } catch (Exception e) {
            logger.error("Can not work with element");
            Assert.fail("Can not work with element");
        }
    }

    public void clickOnSubmitButton() {
        try {
            WebElement webElement = webDriver.findElement(By.xpath("//*[@id=\"btnLogin\"]"));
            webElement.click();
            logger.info("Button submit was clicked");

        } catch (Exception e) {
            logger.error("Can not work with element");
            Assert.fail("Can not work with element");
        }
    }
}