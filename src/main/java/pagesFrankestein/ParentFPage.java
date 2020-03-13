package pagesFrankestein;

import libs.ActionsWithOurElements;
import libs.ConfigProperties;
import libs.UtilsForDB;
import org.aeonbits.owner.ConfigFactory;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ParentFPage {
    Logger logger = Logger.getLogger(getClass());

    WebDriver webDriver;
    UtilsForDB utilsForDB;
    JavascriptExecutor js = (JavascriptExecutor) webDriver;
    String expectedUrl;
    protected static ConfigProperties configProperties = ConfigFactory.create(ConfigProperties.class);
    String baseUrl;
    ActionsWithOurElements actionsWithOurElements;


    public ParentFPage(WebDriver webDriver, String expectedUrl, UtilsForDB utilsForDB) {
        this.webDriver = webDriver;
        baseUrl = configProperties.url_staging();
        this.expectedUrl = baseUrl + expectedUrl;
        PageFactory.initElements(webDriver, this);
        actionsWithOurElements = new ActionsWithOurElements(webDriver);
        this.utilsForDB = utilsForDB;
    }

    public String getCurrentUrl() {
        return webDriver.getCurrentUrl();
    }


    public void checkCurrentUrl() {
        try {
            Assert.assertEquals("Url is not expected", expectedUrl, getCurrentUrl());

        } catch (Exception e) {
            logger.error("Cannot work with Url");
            Assert.fail("Cannot work with Url");

        }
    }
}

