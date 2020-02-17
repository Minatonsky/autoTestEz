package parentTest;

import io.qameta.allure.Step;
import libs.ConfigProperties;
import libs.ExcelDriver;
import libs.UtilsForDB;
import org.aeonbits.owner.ConfigFactory;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import pages.*;
import pagesLocal.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static libs.Utils.waitABit;

public class ParentTest {
    WebDriver webDriver;

    Logger logger = Logger.getLogger(getClass());
    protected UtilsForDB utilsForDB;
    protected ExcelDriver excelDriver;
    protected LoginPage loginPage;
    protected DashboardPage dashboardPage;
    protected ModalEldPage modalEldPage;
    protected FinancesPage financesPage;
    protected ManagerEldPage managerEldPage;
    protected OrderInfoPage orderInfoPage;
    protected ManagerModalEldPage managerModalEldPage;
    protected ChargePage chargePage;
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

    protected static ConfigProperties configProperties = ConfigFactory.create(ConfigProperties.class);


    @Before
    public void setUp() throws SQLException, IOException, ClassNotFoundException {
        utilsForDB = new UtilsForDB();
        excelDriver = new ExcelDriver();
        initDriver(browser);
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        loginPage = new LoginPage(webDriver);
        dashboardPage = new DashboardPage(webDriver);
        modalEldPage = new ModalEldPage(webDriver);
        financesPage = new FinancesPage(webDriver);
        managerEldPage = new ManagerEldPage(webDriver);
        orderInfoPage = new OrderInfoPage(webDriver);
        managerModalEldPage = new ManagerModalEldPage(webDriver);
        chargePage = new ChargePage(webDriver);
        settingsPage = new SettingsPage(webDriver);
        equipmentPage = new EquipmentPage(webDriver);
        dashboardLocalSitePage = new DashboardLocalSitePage(webDriver);
        documentsLocalSitePage = new DocumentsLocalSitePage(webDriver);
        equipmentLocalSitePage = new EquipmentLocalSitePage(webDriver);
        loginLocalSitePage = new LoginLocalSitePage(webDriver);
        driverSettingsLocalSitePage = new DriverSettingsLocalSitePage(webDriver);
        accountSettingsLocalSitePage = new AccountSettingsLocalSitePage(webDriver);
        logsPage = new LogsPage(webDriver);
        eldPage = new EldPage(webDriver);
        helpAndTrainingPage = new HelpAndTrainingPage(webDriver);
        accountSettingsPage = new AccountSettingsPage(webDriver);
        logsLocalSitePage = new LogsLocalSitePage(webDriver);

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
            logger.error("AC failed: " + message + " Expected value: " + valueFront + " Actual value: " + valueFromDb);
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
    public String genRandomFuelType(){
        String[] arr={"Gasoline", "Diesel", "Gasohol", "Propane", "LNG", "CNG", "Ethanol", "Methanol", "E-85", "M-85", "A55", "Biodiesel"};
        Random r=new Random();
        int randomNumber=r.nextInt(arr.length);
        return arr[randomNumber];
    }
    public String genRandomCreditCard(){
        String[] arr={"4007000000027", "4111111111111111", "5424000000000015", "4012888818888", "370000000000002", "2223000010309711", "6011000000000012"};
        Random r=new Random();
        int randomNumber=r.nextInt(arr.length);
        return arr[randomNumber];
    }

//    public void putCookiesIntoBrowser(){
//
//        Cookie name = new Cookie();
//        try {
//            webDriver.manage().addCookie(name);
//        } catch (Exception e){
//            logger.error("Cookies failed");
//        }
//    }


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
