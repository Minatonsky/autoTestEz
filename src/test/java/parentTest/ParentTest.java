package parentTest;

import com.github.javafaker.Faker;
import libs.ConfigProperties;
import libs.Database;
import libs.UtilsForDB;
import org.aeonbits.owner.ConfigFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import pages.*;
import pagesFrankestein.*;
import pagesLocal.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class ParentTest extends ParentTestWithoutWebDriver{
    WebDriver webDriver;
    Database dBMySQL;
    String nameDB = "MySQL_PADB_DB";
    String driverDB = "MySQL";

    protected UtilsForDB utilsForDB;

    protected LoginPage loginPage;
    protected DashboardPage dashboardPage;
    protected ModalOrderPage modalOrderPage;
    protected FinancesPage financesPage;
    protected ManagerEldPage managerEldPage;
    protected OrderInfoPage orderInfoPage;
    protected ManagerModalEldPage managerModalEldPage;
    protected SettingsPage settingsPage;
    protected EquipmentPage equipmentPage;
    protected LogsPage logsPage;
    protected EldPage eldPage;
    protected FleetDriversPage fleetDriversPage;
    protected ReturnsPage returnsPage;
    protected AccountSettingsPage accountSettingsPage;

    protected DashboardLocalSitePage dashboardLocalSitePage;
    protected DocumentsLocalSitePage documentsLocalSitePage;
    protected EquipmentLocalSitePage equipmentLocalSitePage;
    protected LoginLocalSitePage loginLocalSitePage;
    protected DriverSettingsLocalSitePage driverSettingsLocalSitePage;
    protected AccountSettingsLocalSitePage accountSettingsLocalSitePage;
    protected HelpAndTrainingPage helpAndTrainingPage;
    protected LogsLocalSitePage logsLocalSitePage;

    protected LoginFPage loginFPage;
    protected DashboardFPage dashboardFPage;
    protected ModalOrderFPage modalOrderFPage;
    protected FinancesFPage financesFPage;
    protected ManagerEldFPage managerEldFPage;
    protected OrderInfoFPage orderInfoFPage;
    protected ManagerModalEldFPage managerModalEldFPage;
    protected SettingsFPage settingsFPage;
    protected EquipmentFPage equipmentFPage;
    protected LogsFPage logsFPage;
    protected EldFPage eldFPage;
    protected FleetDriversFPage fleetDriversFPage;
    protected ReturnsFPage returnsFPage;
    protected AccountSettingsFPage accountSettingsFPage;
    protected DocumentsFPage documentsFPage;

    protected Faker faker;

    String browser = System.getProperty("browser");

    protected static ConfigProperties configProperties = ConfigFactory.create(ConfigProperties.class);

    @Before
    public void setUp() throws SQLException, IOException, ClassNotFoundException {
        initDriver(browser);
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        dBMySQL = new Database(nameDB, driverDB);
        utilsForDB = new UtilsForDB(dBMySQL);
        faker = new Faker();

        loginPage = new LoginPage(webDriver, utilsForDB);
        dashboardPage = new DashboardPage(webDriver, utilsForDB);
        modalOrderPage = new ModalOrderPage(webDriver, utilsForDB);
        financesPage = new FinancesPage(webDriver, utilsForDB);
        managerEldPage = new ManagerEldPage(webDriver, utilsForDB);
        orderInfoPage = new OrderInfoPage(webDriver, utilsForDB);
        managerModalEldPage = new ManagerModalEldPage(webDriver, utilsForDB);
        settingsPage = new SettingsPage(webDriver, utilsForDB);
        equipmentPage = new EquipmentPage(webDriver, utilsForDB);
        logsPage = new LogsPage(webDriver, utilsForDB);
        eldPage = new EldPage(webDriver, utilsForDB);
        helpAndTrainingPage = new HelpAndTrainingPage(webDriver, utilsForDB);
        accountSettingsPage = new AccountSettingsPage(webDriver, utilsForDB);
        fleetDriversPage = new FleetDriversPage(webDriver, utilsForDB);
        returnsPage = new ReturnsPage(webDriver, utilsForDB);

        dashboardLocalSitePage = new DashboardLocalSitePage(webDriver, utilsForDB);
        documentsLocalSitePage = new DocumentsLocalSitePage(webDriver, utilsForDB);
        equipmentLocalSitePage = new EquipmentLocalSitePage(webDriver, utilsForDB);
        loginLocalSitePage = new LoginLocalSitePage(webDriver, utilsForDB);
        driverSettingsLocalSitePage = new DriverSettingsLocalSitePage(webDriver, utilsForDB);
        accountSettingsLocalSitePage = new AccountSettingsLocalSitePage(webDriver, utilsForDB);
        logsLocalSitePage = new LogsLocalSitePage(webDriver, utilsForDB);

        loginFPage = new LoginFPage(webDriver, utilsForDB);
        dashboardFPage = new DashboardFPage(webDriver, utilsForDB);
        modalOrderFPage = new ModalOrderFPage(webDriver, utilsForDB);
        financesFPage = new FinancesFPage(webDriver, utilsForDB);
        managerEldFPage = new ManagerEldFPage(webDriver, utilsForDB);
        orderInfoFPage = new OrderInfoFPage(webDriver, utilsForDB);
        managerModalEldFPage = new ManagerModalEldFPage(webDriver, utilsForDB);
        settingsFPage = new SettingsFPage(webDriver, utilsForDB);
        equipmentFPage = new EquipmentFPage(webDriver, utilsForDB);
        logsFPage = new LogsFPage(webDriver, utilsForDB);
        eldFPage = new EldFPage(webDriver, utilsForDB);
        accountSettingsFPage = new AccountSettingsFPage(webDriver, utilsForDB);
        fleetDriversFPage = new FleetDriversFPage(webDriver, utilsForDB);
        returnsFPage = new ReturnsFPage(webDriver, utilsForDB);
        documentsFPage = new DocumentsFPage(webDriver, utilsForDB);


    }

    private void initDriver(String browserName) {
        if ( browserName == null || browserName.equals("chrome")) {
            logger.info("Chrome will be started");
            File file = new File("./src/drivers/chromedriver.exe");
            System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
            webDriver = new ChromeDriver();
            ChromeOptions options = new ChromeOptions();
            options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
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

        } else if ("remote".equals(browser)){
            logger.info("Remote Driver will be started");
            try {
                webDriver = new RemoteWebDriver(
                        new URL("http://localhost:4444/wd/hub"),
                        DesiredCapabilities.chrome());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        else {
            logger.error("Can`t init driver");
            Assert.fail("Can`t init driver");
        }
    }

    @After
    public void tearDown() throws SQLException {
        logger.info(" DB connection closed ");
        dBMySQL.quit();
        webDriver.quit();
    }


    public void selectBrowserWindow(String window){
        Set<String> windowIds = webDriver.getWindowHandles();
        Iterator<String> iter = windowIds.iterator();
        String mainWindow = iter.next();
        String childWindow = iter.next();
    }

    public void switchToModalWindow(){
        webDriver.switchTo().activeElement();
    }

    public String[] getCookieValues(){
        Set<Cookie> cks = webDriver.manage().getCookies();
        String[] cookieValues = new String[cks.size()];

        int i = 0;
        for (Cookie ck : cks) {
            cookieValues[i] = ck.getValue();
            i++;
        }
        i = 0;
        return cookieValues;
    }

    public String getValueCookieNamed(String cookieName){
        try{
        String value = webDriver.manage().getCookieNamed(cookieName).getValue();
        logger.info(" Value from cookies = " + value);
            return value;
        }catch (Exception e){
            Assert.fail();
            return "Some trouble with cookies get value";
        }
    }

    public void writeCookiesInFile(){
        File file = new File("Cookies.data");
        try
        {
            // Delete old file if exists
            file.delete();
            file.createNewFile();
            FileWriter fileWrite = new FileWriter(file);
            BufferedWriter Bwrite = new BufferedWriter(fileWrite);
            // loop for getting the cookie information

            // loop for getting the cookie information
            for(Cookie ck : webDriver.manage().getCookies())
            {
                Bwrite.write((ck.getName()+";"+ck.getValue()+";"+ck.getDomain()+";"+ck.getPath()+";"+ck.getExpiry()+";"+ck.isSecure()));
                Bwrite.newLine();
            }
            Bwrite.close();
            fileWrite.close();

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }


//       This is for do screenshot on failed test
//    @Rule
//    public TestWatcher watchman = new TestWatcher() {
//        String fileName;
//
//        @Override
//        protected void failed(Throwable e, Description description) {
//            screenshot();
//        }
//
//        @Attachment(value = "Page screenshot", type = "image/png")
//        public byte[] saveScreenshot(byte[] screenShot) {
//            return screenShot;
//        }
//
//        public void screenshot() {
//            if (webDriver == null) {
//                logger.info("Driver for screenshot not found");
//                return;
//            }
//
//            saveScreenshot(((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES));
//
//        }
//        @Override
//        protected void finished(Description description) {
//            logger.info(String.format("Finished test: %s::%s", description.getClassName(), description.getMethodName()));
//            try {
//                webDriver.quit();
//            } catch (Exception e) {
//                logger.error(e);
//            }
//        }
//    };

}
