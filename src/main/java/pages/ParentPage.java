package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class ParentPage {
    Logger logger = Logger.getLogger(getClass());
    WebDriver webDriver;
    String expectedUrl;
    final String baseUrl = "https://dev.ezlogz.com";

    public ParentPage(WebDriver webDriver, String expectedUrl) {
        this.webDriver = webDriver;
        this.expectedUrl = baseUrl + expectedUrl;
    }

    public String getCurrentUrl(){
        return  webDriver.getCurrentUrl();
    }

    public void checkCurrentUrl(){
        try {
            Assert.assertEquals("Url is not expected", expectedUrl, getCurrentUrl());

        } catch (Exception e){
            logger.error("Cannot work with Url");
            Assert.fail("Cannot work with Url");

        }
    }


}
