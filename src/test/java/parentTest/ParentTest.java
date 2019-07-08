package parentTest;

import libs.ConfigProperties;
import org.aeonbits.owner.ConfigFactory;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import pages.DashboardPage;
import pages.ModalEldPage;
import pages.LoginPage;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class ParentTest {
    WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass());
    protected LoginPage loginPage;
    protected DashboardPage dashboardPage;
    protected ModalEldPage modalEldPage;
    String browser = System.getProperty("browser");
    protected static ConfigProperties configProperties = ConfigFactory.create(ConfigProperties.class);


    @Before
    public void setUp() {
        initDriver(browser);
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        loginPage = new LoginPage(webDriver);
        dashboardPage = new DashboardPage(webDriver);
        modalEldPage = new ModalEldPage(webDriver);


    }

    private void initDriver(String browserName) {
        if ( browserName == null || browserName.equals("chrome")) {
            logger.info("Chrome will be started");
            File file = new File("./src/drivers/chromedriver.exe");
            System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
            webDriver = new ChromeDriver();
            logger.info("Chrome is started");
        } else if ("fireFox".equals(browserName)){
            logger.info("FireFox will be started");
            File fileFF = new File("./src/drivers/geckodriver.exe");
            System.setProperty("webdriver.gecko.driver", fileFF.getAbsolutePath());
            FirefoxOptions profile = new FirefoxOptions();
            profile.addPreference("browser.startup.page", 0); // Empty start page
            profile.addPreference("browser.startup.homepage_override.mstone", "ignore"); // Suppress the "What's new" page
            webDriver = new FirefoxDriver();
            logger.info("FireFox is started");

        }
        else {
            logger.error("Can`t init driver");
            Assert.fail("Can`t init driver");
        }
    }

    @After
    public void tearDown(){ webDriver.quit();}

    protected void checkAC(String message, boolean actual, boolean expected){
        if (actual != expected){
            logger.error("AC failed: " + message);
        }
        Assert.assertEquals(message,expected,actual);
    }

}
