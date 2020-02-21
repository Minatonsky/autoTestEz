package pages;

import libs.ActionsWithOurElements;
import libs.ConfigProperties;
import org.aeonbits.owner.ConfigFactory;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ParentPage {
    Logger logger = Logger.getLogger(getClass());

    WebDriver webDriver;
    JavascriptExecutor js = (JavascriptExecutor) webDriver;
    String expectedUrl;
    protected static ConfigProperties configProperties = ConfigFactory.create(ConfigProperties.class);
    String baseUrl;
    ActionsWithOurElements actionsWithOurElements;

    double ELDDevicePriceSmart = 169.99;
    double ELDDevicePriceHard = 149.99;
    double eldMonthToMonthPrice = 29.99;
    double eld1YearSubscriptionPrice = 329.89;
    double eld2YearsSubscriptionPrice = 629.79;
    double eld1YearSubscriptionDiscountPrice = 299.88;
    double eld2YearsSubscriptionDiscountPrice = 599.76;
    double pinCablePrice = 34.99;
    double pinCableOBDIIPrice = 34.99;
    double stickerLabelPrice = 3.00;
    double ezSmartCamCP2Price = 564.99;
    double ezSmartCamSVAPrice = 399.99;
    double sD32GbPrice = 39.99;
    double sD64GbPrice = 59.99;
    double sD128GbPrice = 109.99;
    double cameraInstallationFee = 100.00;
    double cameraSetupFee = 29.99;
    double cP2MonthFee = 29.99;
    double eldDepositFee = 49.99;


    public ParentPage(WebDriver webDriver, String expectedUrl) {
        this.webDriver = webDriver;
        baseUrl = configProperties.url_dev_ezlogz();
        this.expectedUrl = baseUrl + expectedUrl;
        PageFactory.initElements(webDriver, this);
        actionsWithOurElements = new ActionsWithOurElements(webDriver);
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

