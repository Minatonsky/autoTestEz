package pagesLocal;

import libs.ActionsWithOurElements;
import libs.ConfigProperties;
import libs.UtilsForDB;
import org.aeonbits.owner.ConfigFactory;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ParentLocalSitePage {
    Logger logger = Logger.getLogger(getClass());

    WebDriver webDriver;
    String expectedUrl;
    UtilsForDB utilsForDB;
    protected static ConfigProperties configProperties = ConfigFactory.create(ConfigProperties.class);
    String baseUrl;
    ActionsWithOurElements actionsWithOurElements;

    public ParentLocalSitePage(WebDriver webDriver, String expectedUrl, UtilsForDB utilsForDB) {
        this.webDriver = webDriver;
        baseUrl = configProperties.url_dev_frontend();
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
