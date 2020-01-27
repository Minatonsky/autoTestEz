package parentTest;

import io.qameta.allure.Step;
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
import pages.*;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static libs.Utils.waitABit;

public class Parent2Test {
    WebDriver webDriver;


    Logger logger = Logger.getLogger(getClass());
    protected LoginPage loginPage;
    protected DashboardPage dashboardPage;
    protected ModalEldPage modalEldPage;
    protected FinancesPage financesPage;
    protected ManagerEldPage managerEldPage;
    protected OrderInfoPage orderInfoPage;
    protected UserEldPage userEldPage;
    protected ManagerModalEldPage managerModalEldPage;
    protected ChargePage chargePage;
    protected SettingsPage settingsPage;
    protected EquipmentPage equipmentPage;

    String browser = System.getProperty("browser");

    protected static ConfigProperties configProperties = ConfigFactory.create(ConfigProperties.class);


    @Before
    public void setUp() throws SQLException, IOException, ClassNotFoundException {
        initDriver(browser);
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        loginPage = new LoginPage(webDriver);
        dashboardPage = new DashboardPage(webDriver);
        modalEldPage = new ModalEldPage(webDriver);
        financesPage = new FinancesPage(webDriver);
        managerEldPage = new ManagerEldPage(webDriver);
        orderInfoPage = new OrderInfoPage(webDriver);
        userEldPage = new UserEldPage(webDriver);
        managerModalEldPage = new ManagerModalEldPage(webDriver);
        chargePage = new ChargePage(webDriver);
        settingsPage = new SettingsPage(webDriver);
        equipmentPage = new EquipmentPage(webDriver);

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
    public void tearDown() throws SQLException {
        webDriver.quit();
    }

    @Step
    protected void checkAC(String message, boolean actual, boolean expected){
        waitABit(1);
        if (actual != expected){
            logger.error("AC failed: " + message);
        }
        Assert.assertEquals(message,expected,actual);
        logger.info("AC passed");
    }
    @Step
    protected void checkACWithLogger(String message, boolean actual, boolean expected, String valueFront, String valueFromDb){
        waitABit(1);
        if (actual != expected){
            logger.error("AC failed: " + message + "expected value: " + valueFront + " actual value: " + valueFromDb);
        }
        Assert.assertEquals(message,expected,actual);
        logger.info("AC passed");
    }

    public void selectBrowserWindow(String window){
        Set<String> windowIds = webDriver.getWindowHandles();
        Iterator<String> iter = windowIds.iterator();
        String mainWindow = iter.next();
        String childWindow = iter.next();
    }

}