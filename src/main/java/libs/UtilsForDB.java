package libs;


import io.qameta.allure.Step;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UtilsForDB {
     Database dBMySQL;


    @Step
    public String getLastOrderIdForFleet(String id) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        String tempIdOrder = dBMySQL.selectValue("SELECT id FROM eld_orders WHERE fleetId = " + id + " ORDER BY orderDate desc LIMIT 1;");
        dBMySQL.quit();
        return tempIdOrder;

    }
    @Step
    public void getSetCurrentDueForFleet(String valueCurrentDue, String idCarrier) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        dBMySQL.changeTable("UPDATE ez_finances SET `currentDue`=" + valueCurrentDue + " WHERE `carrierId`= " + idCarrier + ";");
        dBMySQL.quit();
    }
    @Step
    public void getSetCurrentDueForSolo(String valueCurrentDue, String idSolo) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        dBMySQL.changeTable("UPDATE eld_personal_finances SET `currentDue`=" + valueCurrentDue + " WHERE `userId`= " + idSolo + ";");
        dBMySQL.quit();
    }
    @Step
    public String getLastOrderIdForSolo(String id) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        String tempIdOrder = dBMySQL.selectValue("SELECT id FROM eld_orders WHERE userId = " + id + " ORDER BY orderDate desc LIMIT 1;");
        dBMySQL.quit();
        return tempIdOrder;
    }

    @Step
    public String getOrderStatus(String idOrder) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        String tempDeviceStatus = dBMySQL.selectValue("SELECT status FROM eld_orders WHERE id = " + idOrder + ";");
        dBMySQL.quit();
        return tempDeviceStatus;
    }

    @Step
    public List<ArrayList> getLocalIdDevices(String idOrder) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        List<ArrayList> tempLocalIdDevices = dBMySQL.selectTable("SELECT localId FROM eld_scanners WHERE id IN (SELECT scannerId FROM eld_orders_ids WHERE orderId = " + idOrder + ");");
        dBMySQL.quit();
        return tempLocalIdDevices;
    }

}
