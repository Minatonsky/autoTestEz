package parentTest;

import io.qameta.allure.Step;
import libs.*;
import org.aeonbits.owner.ConfigFactory;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

import java.io.IOException;
import java.sql.SQLException;

import static libs.Utils.waitABit;

public class ParentTestWithoutWebDriver {
    Database dBMySQL;
    String nameDB = "MySQL_PADB_DB";
    String driverDB = "MySQL";

    protected ExcelDriver excelDriver;
    protected UtilsForDB utilsForDB;
    protected ChargeSmartSafetyMethods chargeSmartSafetyMethods;

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
    Logger logger = Logger.getLogger(getClass());

    @Before
    public void setUp() throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database(nameDB, driverDB);
        utilsForDB = new UtilsForDB(dBMySQL);
        excelDriver = new ExcelDriver();
        chargeSmartSafetyMethods = new ChargeSmartSafetyMethods();
    }

    @After
    public void tearDown() throws SQLException {
        logger.info(" DB connection closed ");
        dBMySQL.quit();
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

}
