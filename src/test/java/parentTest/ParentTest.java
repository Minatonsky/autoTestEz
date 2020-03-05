package parentTest;

import libs.ChargeMethods;
import libs.ConfigProperties;
import org.aeonbits.owner.ConfigFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import pages.*;
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

    protected LoginPage loginPage;
    protected DashboardPage dashboardPage;
    protected ModalOrderPage modalOrderPage;
    protected FinancesPage financesPage;
    protected ManagerEldPage managerEldPage;
    protected OrderInfoPage orderInfoPage;
    protected ManagerModalEldPage managerModalEldPage;
    protected ChargeMethods chargeMethods;
    protected SettingsPage settingsPage;
    protected EquipmentPage equipmentPage;
    protected DashboardLocalSitePage dashboardLocalSitePage;
    protected DocumentsLocalSitePage documentsLocalSitePage;
    protected EquipmentLocalSitePage equipmentLocalSitePage;
    protected LoginLocalSitePage loginLocalSitePage;
    protected DriverSettingsLocalSitePage driverSettingsLocalSitePage;
    protected AccountSettingsLocalSitePage accountSettingsLocalSitePage;
    protected LogsPage logsPage;
    protected EldPage eldPage;
    protected HelpAndTrainingPage helpAndTrainingPage;
    protected AccountSettingsPage accountSettingsPage;
    protected LogsLocalSitePage logsLocalSitePage;

    String browser = System.getProperty("browser");

// type of devices
    public String IOSXMonthTariffId = "0";
    public String geometricsMonthTariffId = "9";
    public String ezHardMonthTariffId = "12";
    public String oneYearIOSXTariffId = "1";
    public String twoYearsIOSXTariffId = "2";
    public String oneYearGeometricsTariffId = "10";
    public String twoYearsGeometricsTariffId = "11";
    public String monthEzHardTariffId = "12";
    public String oneYearEzHardTariffId = "13";
    public String twoYearsEzHardTariffId = "14";

    public String carrierIdString = "carrierId";
    public String fleetString = "fleet";
    public String userIdString = "userId";

    protected static ConfigProperties configProperties = ConfigFactory.create(ConfigProperties.class);

    @Before
    public void setUp() throws SQLException, IOException, ClassNotFoundException {

        initDriver(browser);
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        loginPage = new LoginPage(webDriver, dBMySQL);
        dashboardPage = new DashboardPage(webDriver, dBMySQL);
        modalOrderPage = new ModalOrderPage(webDriver, dBMySQL);
        financesPage = new FinancesPage(webDriver, dBMySQL);
        managerEldPage = new ManagerEldPage(webDriver, dBMySQL);
        orderInfoPage = new OrderInfoPage(webDriver, dBMySQL);
        managerModalEldPage = new ManagerModalEldPage(webDriver, dBMySQL);
        chargeMethods = new ChargeMethods(webDriver, dBMySQL);
        settingsPage = new SettingsPage(webDriver, dBMySQL);
        equipmentPage = new EquipmentPage(webDriver, dBMySQL);
        dashboardLocalSitePage = new DashboardLocalSitePage(webDriver, dBMySQL);
        documentsLocalSitePage = new DocumentsLocalSitePage(webDriver, dBMySQL);
        equipmentLocalSitePage = new EquipmentLocalSitePage(webDriver, dBMySQL);
        loginLocalSitePage = new LoginLocalSitePage(webDriver, dBMySQL);
        driverSettingsLocalSitePage = new DriverSettingsLocalSitePage(webDriver, dBMySQL);
        accountSettingsLocalSitePage = new AccountSettingsLocalSitePage(webDriver, dBMySQL);
        logsPage = new LogsPage(webDriver, dBMySQL);
        eldPage = new EldPage(webDriver, dBMySQL);
        helpAndTrainingPage = new HelpAndTrainingPage(webDriver, dBMySQL);
        accountSettingsPage = new AccountSettingsPage(webDriver, dBMySQL);
        logsLocalSitePage = new LogsLocalSitePage(webDriver, dBMySQL);


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
