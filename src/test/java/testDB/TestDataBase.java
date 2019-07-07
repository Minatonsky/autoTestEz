package testDB;

import libs.Database;
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
    public void testDataBase() throws SQLException {
        List<Map<String, String>> dataAdmin_ezlogz_api = dbMySql.selectTable("select * from agri_centers");
        logger.info(" Result = " + dataAdmin_ezlogz_api);
    }
}
