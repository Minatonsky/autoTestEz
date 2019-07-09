package testDB;

import libs.Database;
import libs.UtilsForDB;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import parentTest.ParentTest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;


public class TestDataBase extends ParentTest {
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
    public void testDBGetLastOrderIdFromFleet() throws SQLException, IOException, ClassNotFoundException {
        UtilsForDB utilsForDB = new UtilsForDB();
        logger.info(utilsForDB.getOrderIdForFleet("518"));
    }

    @Test
    public void testDBSetCurrentDueForFleet() throws SQLException, IOException, ClassNotFoundException {
        UtilsForDB utilsForDB = new UtilsForDB();
        utilsForDB.getSetCurrentDueForFleet("-1000", "518");
    }
//
//        List<ArrayList> dataAdmin_ezlogz_api = dbMySql.selectTable("SELECT id FROM eld_orders WHERE fleetId = 518 ORDER BY orderDate desc LIMIT 1;");
//        logger.info(" Result = " + dataAdmin_ezlogz_api)
//        logger.info(" Result = " + dataAdmin_ezlogz_api.get(2).get(2);

}
