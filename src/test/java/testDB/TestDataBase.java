package testDB;

import libs.UtilsForDB;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;
// This test page is not using on test project, it just for check some methods

public class TestDataBase {

    static Logger logger = Logger.getLogger(String.valueOf(TestDataBase.class));


    @Test
    public void testDBGetLastOrderIdFromFleet() throws SQLException, IOException, ClassNotFoundException {
        UtilsForDB utilsForDB = new UtilsForDB();
        utilsForDB.getLastOrderIdForFleet("518");
    }

    @Test
    public void testDBSetCurrentDueForFleet() throws SQLException, IOException, ClassNotFoundException {
        UtilsForDB utilsForDB = new UtilsForDB();
        utilsForDB.setCurrentDueForFleet("-1000", "518");
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

    @Test
    public void testGetRowNumberNewOrder() throws SQLException, IOException, ClassNotFoundException {
        UtilsForDB utilsForDB = new UtilsForDB();
        String userId = "518";
        int tempCountOrder = utilsForDB.getCountNewOrderForFleet(userId);
        System.out.println(tempCountOrder);
    }

    @Test
    public void testIsEldBlinded() throws SQLException, IOException, ClassNotFoundException {
        UtilsForDB utilsForDB = new UtilsForDB();
        String orderId = "3066";
        System.out.println(utilsForDB.isEldBlinded(orderId));
    }
    @Test
    public void testGetEldId() throws SQLException, IOException, ClassNotFoundException {
        UtilsForDB utilsForDB = new UtilsForDB();
        String idOrder = "3064";
        List<String> tempIdEld = utilsForDB.getIdEldFromOrder(idOrder);
        for (String element: tempIdEld
        ) {
            System.out.println(element);
        }
    }
    @Test
    public void testActionNewOrder() throws SQLException, IOException, ClassNotFoundException {
        UtilsForDB utilsForDB = new UtilsForDB();
        String idOrder = "3075";
        utilsForDB.deleteEventNewOrder(idOrder);
    }


}
