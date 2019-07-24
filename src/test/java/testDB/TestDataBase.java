package testDB;

import libs.UtilsForDB;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;


public class TestDataBase {
//    static Database dbMySql;
    static Logger logger = Logger.getLogger(String.valueOf(TestDataBase.class));

//    @Before
//    public void  setUp() throws SQLException, IOException, ClassNotFoundException {
//        dbMySql = new Database("MySQL_PADB_DB", "MySQL");
//        logger.info("MySql DB connected");
//    }
//
//    @After
//    public void tearDown() throws SQLException {
//        dbMySql.quit();
//    }

    @Test
    public void testDBGetLastOrderIdFromFleet() throws SQLException, IOException, ClassNotFoundException {
        UtilsForDB utilsForDB = new UtilsForDB();
        utilsForDB.getLastOrderIdForFleet("518");
    }

    @Test
    public void testDBSetCurrentDueForFleet() throws SQLException, IOException, ClassNotFoundException {
        UtilsForDB utilsForDB = new UtilsForDB();
        utilsForDB.getSetCurrentDueForFleet("-1000", "518");
    }

    @Test
    public void testCancelEldDevices() throws SQLException, IOException, ClassNotFoundException {
        UtilsForDB utilsForDB = new UtilsForDB();
        String idLastOrderAfterTest = "2460";
        List<String> localId = utilsForDB.getLocalIdDevices(idLastOrderAfterTest);
        logger.info(" result = " + localId.get(1));
        for (String element:
                localId
             ) {
            System.out.println(element);

        }
        for (int i = 0; i < localId.size(); i++) {
            System.out.println(localId.get(i));

        }
    }


}
