package libs;


import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;

public class UtilsForDB {
     Logger log = Logger.getLogger(getClass());
     Database dBMySQL;



    public String getOrderIdForFleet(String id) throws SQLException, IOException, ClassNotFoundException {
        log.info("--- Conect MySQL DB --------");
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        log.info("--- Conected to MySQL --------");
        String tempIdOrder = dBMySQL.selectValue("SELECT id FROM eld_orders WHERE fleetId = " + id + " ORDER BY orderDate desc LIMIT 1;");
        dBMySQL.quit();
        return tempIdOrder;

    }

    public void getSetCurrentDueForFleet(String valueCurrentDue, String idCarrier) throws SQLException, IOException, ClassNotFoundException {
        log.info("--- Conect MySQL DB --------");
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        log.info("--- Conected to MySQL --------");
        dBMySQL.changeTable("UPDATE ez_finances SET `currentDue`=" + valueCurrentDue + " WHERE `carrierId`= " + idCarrier + ";");
        dBMySQL.quit();
    }
}
