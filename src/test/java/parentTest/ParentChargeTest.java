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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.ChargePage;

import java.io.File;

import static libs.Utils.waitABit;

public class ParentChargeTest {
    protected ExcelDriver excelDriver;
    protected UtilsForDB utilsForDB;
    protected ChargePage chargePage;


    Logger logger = Logger.getLogger(getClass());


    protected static ConfigProperties configProperties = ConfigFactory.create(ConfigProperties.class);
    WebDriver webDriver;



    @Before
    public void setUp(){
        File file = new File("./src/drivers/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
        webDriver = new ChromeDriver();
        excelDriver = new ExcelDriver();
        utilsForDB = new UtilsForDB();



    }
    @After
    public void tearDown(){
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


}

