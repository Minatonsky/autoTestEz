package testDB;

import libs.Database;
import libs.UtilsForDB;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;


public class TestDataBase {
    static Database dbMySql;
    static Logger logger = Logger.getLogger(String.valueOf(TestDataBase.class));

    @Before
    public void  setUp() throws SQLException, IOException, ClassNotFoundException {
        dbMySql = new Database("MySQL_PADB_DB", "MySQL");
        logger.info("MySql DB connected");

    }

    @After
    public void tearDown() throws SQLException {
        dbMySql.quit();

    }

    @Test
    public void testDataBase() throws SQLException, IOException, ClassNotFoundException {
        List<Map<String, String>> dataAdmin_ezlogz_api = dbMySql.selectTable("SELECT id FROM eld_orders WHERE fleetId = 518 ORDER BY orderDate desc LIMIT 1;");
        logger.info(" Result = " + dataAdmin_ezlogz_api);
        //int effectedRows = dbMySql.changeTable("UPDATE ez_finances SET `currentDue`='-1000' WHERE `carrierId`='518'");
        UtilsForDB utilsForDB = new UtilsForDB();
        logger.info(utilsForDB.getOrderIdForFleet("518"));
    }

}
