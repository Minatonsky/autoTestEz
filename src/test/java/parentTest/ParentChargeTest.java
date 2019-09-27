package parentTest;

import libs.ConfigProperties;
import libs.ExcelDriver;
import libs.UtilsForDB;
import org.aeonbits.owner.ConfigFactory;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import pages.ChargePage;

import java.io.IOException;
import java.sql.SQLException;

public class ParentChargeTest {
    WebDriver webDriver;
    protected ExcelDriver excelDriver;
    protected UtilsForDB utilsForDB;

    Logger logger = Logger.getLogger(getClass());
    protected ChargePage chargePage;

    protected static ConfigProperties configProperties = ConfigFactory.create(ConfigProperties.class);

    @Before
    public void init() throws SQLException, IOException, ClassNotFoundException {
        chargePage = new ChargePage();
        excelDriver = new ExcelDriver();
        utilsForDB = new UtilsForDB();
    }
}
